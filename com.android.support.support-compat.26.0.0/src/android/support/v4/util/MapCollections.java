package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;

abstract class MapCollections {
   MapCollections.EntrySet mEntrySet;
   MapCollections.KeySet mKeySet;
   MapCollections.ValuesCollection mValues;

   public static boolean containsAllHelper(Map map, Collection collection) {
      Iterator it = collection.iterator();

      do {
         if (!it.hasNext()) {
            return true;
         }
      } while(map.containsKey(it.next()));

      return false;
   }

   public static boolean removeAllHelper(Map map, Collection collection) {
      int oldSize = map.size();
      Iterator it = collection.iterator();

      while(it.hasNext()) {
         map.remove(it.next());
      }

      return oldSize != map.size();
   }

   public static boolean retainAllHelper(Map map, Collection collection) {
      int oldSize = map.size();
      Iterator it = map.keySet().iterator();

      while(it.hasNext()) {
         if (!collection.contains(it.next())) {
            it.remove();
         }
      }

      return oldSize != map.size();
   }

   public Object[] toArrayHelper(int offset) {
      int N = this.colGetSize();
      Object[] result = new Object[N];

      for(int i = 0; i < N; ++i) {
         result[i] = this.colGetEntry(i, offset);
      }

      return result;
   }

   public Object[] toArrayHelper(Object[] array, int offset) {
      int N = this.colGetSize();
      if (array.length < N) {
         Object[] newArray = (Object[])((Object[])Array.newInstance(array.getClass().getComponentType(), N));
         array = newArray;
      }

      for(int i = 0; i < N; ++i) {
         array[i] = this.colGetEntry(i, offset);
      }

      if (array.length > N) {
         array[N] = null;
      }

      return array;
   }

   public static boolean equalsSetHelper(Set set, Object object) {
      if (set == object) {
         return true;
      } else if (object instanceof Set) {
         Set s = (Set)object;

         try {
            return set.size() == s.size() && set.containsAll(s);
         } catch (NullPointerException var4) {
            return false;
         } catch (ClassCastException var5) {
            return false;
         }
      } else {
         return false;
      }
   }

   public Set getEntrySet() {
      if (this.mEntrySet == null) {
         this.mEntrySet = new MapCollections.EntrySet();
      }

      return this.mEntrySet;
   }

   public Set getKeySet() {
      if (this.mKeySet == null) {
         this.mKeySet = new MapCollections.KeySet();
      }

      return this.mKeySet;
   }

   public Collection getValues() {
      if (this.mValues == null) {
         this.mValues = new MapCollections.ValuesCollection();
      }

      return this.mValues;
   }

   protected abstract int colGetSize();

   protected abstract Object colGetEntry(int var1, int var2);

   protected abstract int colIndexOfKey(Object var1);

   protected abstract int colIndexOfValue(Object var1);

   protected abstract Map colGetMap();

   protected abstract void colPut(Object var1, Object var2);

   protected abstract Object colSetValue(int var1, Object var2);

   protected abstract void colRemoveAt(int var1);

   protected abstract void colClear();

   final class ValuesCollection implements Collection {
      public boolean add(Object object) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection collection) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         MapCollections.this.colClear();
      }

      public boolean contains(Object object) {
         return MapCollections.this.colIndexOfValue(object) >= 0;
      }

      public boolean containsAll(Collection collection) {
         Iterator it = collection.iterator();

         do {
            if (!it.hasNext()) {
               return true;
            }
         } while(this.contains(it.next()));

         return false;
      }

      public boolean isEmpty() {
         return MapCollections.this.colGetSize() == 0;
      }

      public Iterator iterator() {
         return MapCollections.this.new ArrayIterator(1);
      }

      public boolean remove(Object object) {
         int index = MapCollections.this.colIndexOfValue(object);
         if (index >= 0) {
            MapCollections.this.colRemoveAt(index);
            return true;
         } else {
            return false;
         }
      }

      public boolean removeAll(Collection collection) {
         int N = MapCollections.this.colGetSize();
         boolean changed = false;

         for(int i = 0; i < N; ++i) {
            Object cur = MapCollections.this.colGetEntry(i, 1);
            if (collection.contains(cur)) {
               MapCollections.this.colRemoveAt(i);
               --i;
               --N;
               changed = true;
            }
         }

         return changed;
      }

      public boolean retainAll(Collection collection) {
         int N = MapCollections.this.colGetSize();
         boolean changed = false;

         for(int i = 0; i < N; ++i) {
            Object cur = MapCollections.this.colGetEntry(i, 1);
            if (!collection.contains(cur)) {
               MapCollections.this.colRemoveAt(i);
               --i;
               --N;
               changed = true;
            }
         }

         return changed;
      }

      public int size() {
         return MapCollections.this.colGetSize();
      }

      public Object[] toArray() {
         return MapCollections.this.toArrayHelper(1);
      }

      public Object[] toArray(Object[] array) {
         return MapCollections.this.toArrayHelper(array, 1);
      }
   }

   final class KeySet implements Set {
      public boolean add(Object object) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection collection) {
         throw new UnsupportedOperationException();
      }

      public void clear() {
         MapCollections.this.colClear();
      }

      public boolean contains(Object object) {
         return MapCollections.this.colIndexOfKey(object) >= 0;
      }

      public boolean containsAll(Collection collection) {
         return MapCollections.containsAllHelper(MapCollections.this.colGetMap(), collection);
      }

      public boolean isEmpty() {
         return MapCollections.this.colGetSize() == 0;
      }

      public Iterator iterator() {
         return MapCollections.this.new ArrayIterator(0);
      }

      public boolean remove(Object object) {
         int index = MapCollections.this.colIndexOfKey(object);
         if (index >= 0) {
            MapCollections.this.colRemoveAt(index);
            return true;
         } else {
            return false;
         }
      }

      public boolean removeAll(Collection collection) {
         return MapCollections.removeAllHelper(MapCollections.this.colGetMap(), collection);
      }

      public boolean retainAll(Collection collection) {
         return MapCollections.retainAllHelper(MapCollections.this.colGetMap(), collection);
      }

      public int size() {
         return MapCollections.this.colGetSize();
      }

      public Object[] toArray() {
         return MapCollections.this.toArrayHelper(0);
      }

      public Object[] toArray(Object[] array) {
         return MapCollections.this.toArrayHelper(array, 0);
      }

      public boolean equals(Object object) {
         return MapCollections.equalsSetHelper(this, object);
      }

      public int hashCode() {
         int result = 0;

         for(int i = MapCollections.this.colGetSize() - 1; i >= 0; --i) {
            Object obj = MapCollections.this.colGetEntry(i, 0);
            result += obj == null ? 0 : obj.hashCode();
         }

         return result;
      }
   }

   final class EntrySet implements Set {
      public boolean add(Entry object) {
         throw new UnsupportedOperationException();
      }

      public boolean addAll(Collection collection) {
         int oldSize = MapCollections.this.colGetSize();
         Iterator var3 = collection.iterator();

         while(var3.hasNext()) {
            Entry entry = (Entry)var3.next();
            MapCollections.this.colPut(entry.getKey(), entry.getValue());
         }

         return oldSize != MapCollections.this.colGetSize();
      }

      public void clear() {
         MapCollections.this.colClear();
      }

      public boolean contains(Object o) {
         if (!(o instanceof Entry)) {
            return false;
         } else {
            Entry e = (Entry)o;
            int index = MapCollections.this.colIndexOfKey(e.getKey());
            if (index < 0) {
               return false;
            } else {
               Object foundVal = MapCollections.this.colGetEntry(index, 1);
               return ContainerHelpers.equal(foundVal, e.getValue());
            }
         }
      }

      public boolean containsAll(Collection collection) {
         Iterator it = collection.iterator();

         do {
            if (!it.hasNext()) {
               return true;
            }
         } while(this.contains(it.next()));

         return false;
      }

      public boolean isEmpty() {
         return MapCollections.this.colGetSize() == 0;
      }

      public Iterator iterator() {
         return MapCollections.this.new MapIterator();
      }

      public boolean remove(Object object) {
         throw new UnsupportedOperationException();
      }

      public boolean removeAll(Collection collection) {
         throw new UnsupportedOperationException();
      }

      public boolean retainAll(Collection collection) {
         throw new UnsupportedOperationException();
      }

      public int size() {
         return MapCollections.this.colGetSize();
      }

      public Object[] toArray() {
         throw new UnsupportedOperationException();
      }

      public Object[] toArray(Object[] array) {
         throw new UnsupportedOperationException();
      }

      public boolean equals(Object object) {
         return MapCollections.equalsSetHelper(this, object);
      }

      public int hashCode() {
         int result = 0;

         for(int i = MapCollections.this.colGetSize() - 1; i >= 0; --i) {
            Object key = MapCollections.this.colGetEntry(i, 0);
            Object value = MapCollections.this.colGetEntry(i, 1);
            result += (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
         }

         return result;
      }
   }

   final class MapIterator implements Iterator, Entry {
      int mEnd = MapCollections.this.colGetSize() - 1;
      int mIndex = -1;
      boolean mEntryValid = false;

      public boolean hasNext() {
         return this.mIndex < this.mEnd;
      }

      public Entry next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            ++this.mIndex;
            this.mEntryValid = true;
            return this;
         }
      }

      public void remove() {
         if (!this.mEntryValid) {
            throw new IllegalStateException();
         } else {
            MapCollections.this.colRemoveAt(this.mIndex);
            --this.mIndex;
            --this.mEnd;
            this.mEntryValid = false;
         }
      }

      public Object getKey() {
         if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         } else {
            return MapCollections.this.colGetEntry(this.mIndex, 0);
         }
      }

      public Object getValue() {
         if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         } else {
            return MapCollections.this.colGetEntry(this.mIndex, 1);
         }
      }

      public Object setValue(Object object) {
         if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         } else {
            return MapCollections.this.colSetValue(this.mIndex, object);
         }
      }

      public final boolean equals(Object o) {
         if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         } else if (!(o instanceof Entry)) {
            return false;
         } else {
            Entry e = (Entry)o;
            return ContainerHelpers.equal(e.getKey(), MapCollections.this.colGetEntry(this.mIndex, 0)) && ContainerHelpers.equal(e.getValue(), MapCollections.this.colGetEntry(this.mIndex, 1));
         }
      }

      public final int hashCode() {
         if (!this.mEntryValid) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
         } else {
            Object key = MapCollections.this.colGetEntry(this.mIndex, 0);
            Object value = MapCollections.this.colGetEntry(this.mIndex, 1);
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
         }
      }

      public final String toString() {
         return this.getKey() + "=" + this.getValue();
      }
   }

   final class ArrayIterator implements Iterator {
      final int mOffset;
      int mSize;
      int mIndex;
      boolean mCanRemove = false;

      ArrayIterator(int offset) {
         this.mOffset = offset;
         this.mSize = MapCollections.this.colGetSize();
      }

      public boolean hasNext() {
         return this.mIndex < this.mSize;
      }

      public Object next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            Object res = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
            ++this.mIndex;
            this.mCanRemove = true;
            return res;
         }
      }

      public void remove() {
         if (!this.mCanRemove) {
            throw new IllegalStateException();
         } else {
            --this.mIndex;
            --this.mSize;
            this.mCanRemove = false;
            MapCollections.this.colRemoveAt(this.mIndex);
         }
      }
   }
}
