package android.support.v7.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class SortedList {
   public static final int INVALID_POSITION = -1;
   private static final int MIN_CAPACITY = 10;
   private static final int CAPACITY_GROWTH = 10;
   private static final int INSERTION = 1;
   private static final int DELETION = 2;
   private static final int LOOKUP = 4;
   Object[] mData;
   private Object[] mOldData;
   private int mOldDataStart;
   private int mOldDataSize;
   private int mMergedSize;
   private SortedList.Callback mCallback;
   private SortedList.BatchedCallback mBatchedCallback;
   private int mSize;
   private final Class mTClass;

   public SortedList(Class klass, SortedList.Callback callback) {
      this(klass, callback, 10);
   }

   public SortedList(Class klass, SortedList.Callback callback, int initialCapacity) {
      this.mTClass = klass;
      this.mData = (Object[])((Object[])Array.newInstance(klass, initialCapacity));
      this.mCallback = callback;
      this.mSize = 0;
   }

   public int size() {
      return this.mSize;
   }

   public int add(Object item) {
      this.throwIfMerging();
      return this.add(item, true);
   }

   public void addAll(Object[] items, boolean mayModifyInput) {
      this.throwIfMerging();
      if (items.length != 0) {
         if (mayModifyInput) {
            this.addAllInternal(items);
         } else {
            Object[] copy = (Object[])((Object[])Array.newInstance(this.mTClass, items.length));
            System.arraycopy(items, 0, copy, 0, items.length);
            this.addAllInternal(copy);
         }

      }
   }

   public void addAll(Object... items) {
      this.addAll(items, false);
   }

   public void addAll(Collection items) {
      Object[] copy = (Object[])((Object[])Array.newInstance(this.mTClass, items.size()));
      this.addAll(items.toArray(copy), true);
   }

   private void addAllInternal(Object[] newItems) {
      boolean forceBatchedUpdates = !(this.mCallback instanceof SortedList.BatchedCallback);
      if (forceBatchedUpdates) {
         this.beginBatchedUpdates();
      }

      this.mOldData = this.mData;
      this.mOldDataStart = 0;
      this.mOldDataSize = this.mSize;
      Arrays.sort(newItems, this.mCallback);
      int newSize = this.deduplicate(newItems);
      if (this.mSize == 0) {
         this.mData = newItems;
         this.mSize = newSize;
         this.mMergedSize = newSize;
         this.mCallback.onInserted(0, newSize);
      } else {
         this.merge(newItems, newSize);
      }

      this.mOldData = null;
      if (forceBatchedUpdates) {
         this.endBatchedUpdates();
      }

   }

   private int deduplicate(Object[] items) {
      if (items.length == 0) {
         throw new IllegalArgumentException("Input array must be non-empty");
      } else {
         int rangeStart = 0;
         int rangeEnd = 1;

         for(int i = 1; i < items.length; ++i) {
            Object currentItem = items[i];
            int compare = this.mCallback.compare(items[rangeStart], currentItem);
            if (compare > 0) {
               throw new IllegalArgumentException("Input must be sorted in ascending order.");
            }

            if (compare == 0) {
               int sameItemPos = this.findSameItem(currentItem, items, rangeStart, rangeEnd);
               if (sameItemPos != -1) {
                  items[sameItemPos] = currentItem;
               } else {
                  if (rangeEnd != i) {
                     items[rangeEnd] = currentItem;
                  }

                  ++rangeEnd;
               }
            } else {
               if (rangeEnd != i) {
                  items[rangeEnd] = currentItem;
               }

               rangeStart = rangeEnd++;
            }
         }

         return rangeEnd;
      }
   }

   private int findSameItem(Object item, Object[] items, int from, int to) {
      for(int pos = from; pos < to; ++pos) {
         if (this.mCallback.areItemsTheSame(items[pos], item)) {
            return pos;
         }
      }

      return -1;
   }

   private void merge(Object[] newData, int newDataSize) {
      int mergedCapacity = this.mSize + newDataSize + 10;
      this.mData = (Object[])((Object[])Array.newInstance(this.mTClass, mergedCapacity));
      this.mMergedSize = 0;
      int newDataStart = 0;

      while(this.mOldDataStart < this.mOldDataSize || newDataStart < newDataSize) {
         int itemCount;
         if (this.mOldDataStart == this.mOldDataSize) {
            itemCount = newDataSize - newDataStart;
            System.arraycopy(newData, newDataStart, this.mData, this.mMergedSize, itemCount);
            this.mMergedSize += itemCount;
            this.mSize += itemCount;
            this.mCallback.onInserted(this.mMergedSize - itemCount, itemCount);
            break;
         }

         if (newDataStart == newDataSize) {
            itemCount = this.mOldDataSize - this.mOldDataStart;
            System.arraycopy(this.mOldData, this.mOldDataStart, this.mData, this.mMergedSize, itemCount);
            this.mMergedSize += itemCount;
            break;
         }

         Object oldItem = this.mOldData[this.mOldDataStart];
         Object newItem = newData[newDataStart];
         int compare = this.mCallback.compare(oldItem, newItem);
         if (compare > 0) {
            this.mData[this.mMergedSize++] = newItem;
            ++this.mSize;
            ++newDataStart;
            this.mCallback.onInserted(this.mMergedSize - 1, 1);
         } else if (compare == 0 && this.mCallback.areItemsTheSame(oldItem, newItem)) {
            this.mData[this.mMergedSize++] = newItem;
            ++newDataStart;
            ++this.mOldDataStart;
            if (!this.mCallback.areContentsTheSame(oldItem, newItem)) {
               this.mCallback.onChanged(this.mMergedSize - 1, 1);
            }
         } else {
            this.mData[this.mMergedSize++] = oldItem;
            ++this.mOldDataStart;
         }
      }

   }

   private void throwIfMerging() {
      if (this.mOldData != null) {
         throw new IllegalStateException("Cannot call this method from within addAll");
      }
   }

   public void beginBatchedUpdates() {
      this.throwIfMerging();
      if (!(this.mCallback instanceof SortedList.BatchedCallback)) {
         if (this.mBatchedCallback == null) {
            this.mBatchedCallback = new SortedList.BatchedCallback(this.mCallback);
         }

         this.mCallback = this.mBatchedCallback;
      }
   }

   public void endBatchedUpdates() {
      this.throwIfMerging();
      if (this.mCallback instanceof SortedList.BatchedCallback) {
         ((SortedList.BatchedCallback)this.mCallback).dispatchLastEvent();
      }

      if (this.mCallback == this.mBatchedCallback) {
         this.mCallback = this.mBatchedCallback.mWrappedCallback;
      }

   }

   private int add(Object item, boolean notify) {
      int index = this.findIndexOf(item, this.mData, 0, this.mSize, 1);
      if (index == -1) {
         index = 0;
      } else if (index < this.mSize) {
         Object existing = this.mData[index];
         if (this.mCallback.areItemsTheSame(existing, item)) {
            if (this.mCallback.areContentsTheSame(existing, item)) {
               this.mData[index] = item;
               return index;
            }

            this.mData[index] = item;
            this.mCallback.onChanged(index, 1);
            return index;
         }
      }

      this.addToData(index, item);
      if (notify) {
         this.mCallback.onInserted(index, 1);
      }

      return index;
   }

   public boolean remove(Object item) {
      this.throwIfMerging();
      return this.remove(item, true);
   }

   public Object removeItemAt(int index) {
      this.throwIfMerging();
      Object item = this.get(index);
      this.removeItemAtIndex(index, true);
      return item;
   }

   private boolean remove(Object item, boolean notify) {
      int index = this.findIndexOf(item, this.mData, 0, this.mSize, 2);
      if (index == -1) {
         return false;
      } else {
         this.removeItemAtIndex(index, notify);
         return true;
      }
   }

   private void removeItemAtIndex(int index, boolean notify) {
      System.arraycopy(this.mData, index + 1, this.mData, index, this.mSize - index - 1);
      --this.mSize;
      this.mData[this.mSize] = null;
      if (notify) {
         this.mCallback.onRemoved(index, 1);
      }

   }

   public void updateItemAt(int index, Object item) {
      this.throwIfMerging();
      Object existing = this.get(index);
      boolean contentsChanged = existing == item || !this.mCallback.areContentsTheSame(existing, item);
      int newIndex;
      if (existing != item) {
         newIndex = this.mCallback.compare(existing, item);
         if (newIndex == 0) {
            this.mData[index] = item;
            if (contentsChanged) {
               this.mCallback.onChanged(index, 1);
            }

            return;
         }
      }

      if (contentsChanged) {
         this.mCallback.onChanged(index, 1);
      }

      this.removeItemAtIndex(index, false);
      newIndex = this.add(item, false);
      if (index != newIndex) {
         this.mCallback.onMoved(index, newIndex);
      }

   }

   public void recalculatePositionOfItemAt(int index) {
      this.throwIfMerging();
      Object item = this.get(index);
      this.removeItemAtIndex(index, false);
      int newIndex = this.add(item, false);
      if (index != newIndex) {
         this.mCallback.onMoved(index, newIndex);
      }

   }

   public Object get(int index) throws IndexOutOfBoundsException {
      if (index < this.mSize && index >= 0) {
         return this.mOldData != null && index >= this.mMergedSize ? this.mOldData[index - this.mMergedSize + this.mOldDataStart] : this.mData[index];
      } else {
         throw new IndexOutOfBoundsException("Asked to get item at " + index + " but size is " + this.mSize);
      }
   }

   public int indexOf(Object item) {
      if (this.mOldData != null) {
         int index = this.findIndexOf(item, this.mData, 0, this.mMergedSize, 4);
         if (index != -1) {
            return index;
         } else {
            index = this.findIndexOf(item, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
            return index != -1 ? index - this.mOldDataStart + this.mMergedSize : -1;
         }
      } else {
         return this.findIndexOf(item, this.mData, 0, this.mSize, 4);
      }
   }

   private int findIndexOf(Object item, Object[] mData, int left, int right, int reason) {
      while(left < right) {
         int middle = (left + right) / 2;
         Object myItem = mData[middle];
         int cmp = this.mCallback.compare(myItem, item);
         if (cmp < 0) {
            left = middle + 1;
         } else {
            if (cmp == 0) {
               if (this.mCallback.areItemsTheSame(myItem, item)) {
                  return middle;
               }

               int exact = this.linearEqualitySearch(item, middle, left, right);
               if (reason == 1) {
                  return exact == -1 ? middle : exact;
               }

               return exact;
            }

            right = middle;
         }
      }

      return reason == 1 ? left : -1;
   }

   private int linearEqualitySearch(Object item, int middle, int left, int right) {
      int next;
      Object nextItem;
      int cmp;
      for(next = middle - 1; next >= left; --next) {
         nextItem = this.mData[next];
         cmp = this.mCallback.compare(nextItem, item);
         if (cmp != 0) {
            break;
         }

         if (this.mCallback.areItemsTheSame(nextItem, item)) {
            return next;
         }
      }

      for(next = middle + 1; next < right; ++next) {
         nextItem = this.mData[next];
         cmp = this.mCallback.compare(nextItem, item);
         if (cmp != 0) {
            break;
         }

         if (this.mCallback.areItemsTheSame(nextItem, item)) {
            return next;
         }
      }

      return -1;
   }

   private void addToData(int index, Object item) {
      if (index > this.mSize) {
         throw new IndexOutOfBoundsException("cannot add item to " + index + " because size is " + this.mSize);
      } else {
         if (this.mSize == this.mData.length) {
            Object[] newData = (Object[])((Object[])Array.newInstance(this.mTClass, this.mData.length + 10));
            System.arraycopy(this.mData, 0, newData, 0, index);
            newData[index] = item;
            System.arraycopy(this.mData, index, newData, index + 1, this.mSize - index);
            this.mData = newData;
         } else {
            System.arraycopy(this.mData, index, this.mData, index + 1, this.mSize - index);
            this.mData[index] = item;
         }

         ++this.mSize;
      }
   }

   public void clear() {
      this.throwIfMerging();
      if (this.mSize != 0) {
         int prevSize = this.mSize;
         Arrays.fill(this.mData, 0, prevSize, (Object)null);
         this.mSize = 0;
         this.mCallback.onRemoved(0, prevSize);
      }
   }

   public static class BatchedCallback extends SortedList.Callback {
      final SortedList.Callback mWrappedCallback;
      private final BatchingListUpdateCallback mBatchingListUpdateCallback;

      public BatchedCallback(SortedList.Callback wrappedCallback) {
         this.mWrappedCallback = wrappedCallback;
         this.mBatchingListUpdateCallback = new BatchingListUpdateCallback(this.mWrappedCallback);
      }

      public int compare(Object o1, Object o2) {
         return this.mWrappedCallback.compare(o1, o2);
      }

      public void onInserted(int position, int count) {
         this.mBatchingListUpdateCallback.onInserted(position, count);
      }

      public void onRemoved(int position, int count) {
         this.mBatchingListUpdateCallback.onRemoved(position, count);
      }

      public void onMoved(int fromPosition, int toPosition) {
         this.mBatchingListUpdateCallback.onMoved(fromPosition, toPosition);
      }

      public void onChanged(int position, int count) {
         this.mBatchingListUpdateCallback.onChanged(position, count, (Object)null);
      }

      public boolean areContentsTheSame(Object oldItem, Object newItem) {
         return this.mWrappedCallback.areContentsTheSame(oldItem, newItem);
      }

      public boolean areItemsTheSame(Object item1, Object item2) {
         return this.mWrappedCallback.areItemsTheSame(item1, item2);
      }

      public void dispatchLastEvent() {
         this.mBatchingListUpdateCallback.dispatchLastEvent();
      }
   }

   public abstract static class Callback implements Comparator, ListUpdateCallback {
      public abstract int compare(Object var1, Object var2);

      public abstract void onChanged(int var1, int var2);

      public void onChanged(int position, int count, Object payload) {
         this.onChanged(position, count);
      }

      public abstract boolean areContentsTheSame(Object var1, Object var2);

      public abstract boolean areItemsTheSame(Object var1, Object var2);
   }
}
