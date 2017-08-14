package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;

class ViewInfoStore {
   private static final boolean DEBUG = false;
   @VisibleForTesting
   final ArrayMap mLayoutHolderMap = new ArrayMap();
   @VisibleForTesting
   final LongSparseArray mOldChangedHolders = new LongSparseArray();

   void clear() {
      this.mLayoutHolderMap.clear();
      this.mOldChangedHolders.clear();
   }

   void addToPreLayout(RecyclerView.ViewHolder holder, RecyclerView.ItemAnimator.ItemHolderInfo info) {
      ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.get(holder);
      if (record == null) {
         record = ViewInfoStore.InfoRecord.obtain();
         this.mLayoutHolderMap.put(holder, record);
      }

      record.preInfo = info;
      record.flags |= 4;
   }

   boolean isDisappearing(RecyclerView.ViewHolder holder) {
      ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.get(holder);
      return record != null && (record.flags & 1) != 0;
   }

   @Nullable
   RecyclerView.ItemAnimator.ItemHolderInfo popFromPreLayout(RecyclerView.ViewHolder vh) {
      return this.popFromLayoutStep(vh, 4);
   }

   @Nullable
   RecyclerView.ItemAnimator.ItemHolderInfo popFromPostLayout(RecyclerView.ViewHolder vh) {
      return this.popFromLayoutStep(vh, 8);
   }

   private RecyclerView.ItemAnimator.ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder vh, int flag) {
      int index = this.mLayoutHolderMap.indexOfKey(vh);
      if (index < 0) {
         return null;
      } else {
         ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.valueAt(index);
         if (record != null && (record.flags & flag) != 0) {
            record.flags &= ~flag;
            RecyclerView.ItemAnimator.ItemHolderInfo info;
            if (flag == 4) {
               info = record.preInfo;
            } else {
               if (flag != 8) {
                  throw new IllegalArgumentException("Must provide flag PRE or POST");
               }

               info = record.postInfo;
            }

            if ((record.flags & 12) == 0) {
               this.mLayoutHolderMap.removeAt(index);
               ViewInfoStore.InfoRecord.recycle(record);
            }

            return info;
         } else {
            return null;
         }
      }
   }

   void addToOldChangeHolders(long key, RecyclerView.ViewHolder holder) {
      this.mOldChangedHolders.put(key, holder);
   }

   void addToAppearedInPreLayoutHolders(RecyclerView.ViewHolder holder, RecyclerView.ItemAnimator.ItemHolderInfo info) {
      ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.get(holder);
      if (record == null) {
         record = ViewInfoStore.InfoRecord.obtain();
         this.mLayoutHolderMap.put(holder, record);
      }

      record.flags |= 2;
      record.preInfo = info;
   }

   boolean isInPreLayout(RecyclerView.ViewHolder viewHolder) {
      ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.get(viewHolder);
      return record != null && (record.flags & 4) != 0;
   }

   RecyclerView.ViewHolder getFromOldChangeHolders(long key) {
      return (RecyclerView.ViewHolder)this.mOldChangedHolders.get(key);
   }

   void addToPostLayout(RecyclerView.ViewHolder holder, RecyclerView.ItemAnimator.ItemHolderInfo info) {
      ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.get(holder);
      if (record == null) {
         record = ViewInfoStore.InfoRecord.obtain();
         this.mLayoutHolderMap.put(holder, record);
      }

      record.postInfo = info;
      record.flags |= 8;
   }

   void addToDisappearedInLayout(RecyclerView.ViewHolder holder) {
      ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.get(holder);
      if (record == null) {
         record = ViewInfoStore.InfoRecord.obtain();
         this.mLayoutHolderMap.put(holder, record);
      }

      record.flags |= 1;
   }

   void removeFromDisappearedInLayout(RecyclerView.ViewHolder holder) {
      ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.get(holder);
      if (record != null) {
         record.flags &= -2;
      }
   }

   void process(ViewInfoStore.ProcessCallback callback) {
      for(int index = this.mLayoutHolderMap.size() - 1; index >= 0; --index) {
         RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)this.mLayoutHolderMap.keyAt(index);
         ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.removeAt(index);
         if ((record.flags & 3) == 3) {
            callback.unused(viewHolder);
         } else if ((record.flags & 1) != 0) {
            if (record.preInfo == null) {
               callback.unused(viewHolder);
            } else {
               callback.processDisappeared(viewHolder, record.preInfo, record.postInfo);
            }
         } else if ((record.flags & 14) == 14) {
            callback.processAppeared(viewHolder, record.preInfo, record.postInfo);
         } else if ((record.flags & 12) == 12) {
            callback.processPersistent(viewHolder, record.preInfo, record.postInfo);
         } else if ((record.flags & 4) != 0) {
            callback.processDisappeared(viewHolder, record.preInfo, (RecyclerView.ItemAnimator.ItemHolderInfo)null);
         } else if ((record.flags & 8) != 0) {
            callback.processAppeared(viewHolder, record.preInfo, record.postInfo);
         } else if ((record.flags & 2) != 0) {
            ;
         }

         ViewInfoStore.InfoRecord.recycle(record);
      }

   }

   void removeViewHolder(RecyclerView.ViewHolder holder) {
      for(int i = this.mOldChangedHolders.size() - 1; i >= 0; --i) {
         if (holder == this.mOldChangedHolders.valueAt(i)) {
            this.mOldChangedHolders.removeAt(i);
            break;
         }
      }

      ViewInfoStore.InfoRecord info = (ViewInfoStore.InfoRecord)this.mLayoutHolderMap.remove(holder);
      if (info != null) {
         ViewInfoStore.InfoRecord.recycle(info);
      }

   }

   void onDetach() {
      ViewInfoStore.InfoRecord.drainCache();
   }

   public void onViewDetached(RecyclerView.ViewHolder viewHolder) {
      this.removeFromDisappearedInLayout(viewHolder);
   }

   static class InfoRecord {
      static final int FLAG_DISAPPEARED = 1;
      static final int FLAG_APPEAR = 2;
      static final int FLAG_PRE = 4;
      static final int FLAG_POST = 8;
      static final int FLAG_APPEAR_AND_DISAPPEAR = 3;
      static final int FLAG_PRE_AND_POST = 12;
      static final int FLAG_APPEAR_PRE_AND_POST = 14;
      int flags;
      @Nullable
      RecyclerView.ItemAnimator.ItemHolderInfo preInfo;
      @Nullable
      RecyclerView.ItemAnimator.ItemHolderInfo postInfo;
      static Pool sPool = new SimplePool(20);

      static ViewInfoStore.InfoRecord obtain() {
         ViewInfoStore.InfoRecord record = (ViewInfoStore.InfoRecord)sPool.acquire();
         return record == null ? new ViewInfoStore.InfoRecord() : record;
      }

      static void recycle(ViewInfoStore.InfoRecord record) {
         record.flags = 0;
         record.preInfo = null;
         record.postInfo = null;
         sPool.release(record);
      }

      static void drainCache() {
         while(sPool.acquire() != null) {
            ;
         }

      }
   }

   interface ProcessCallback {
      void processDisappeared(RecyclerView.ViewHolder var1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo var2, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo var3);

      void processAppeared(RecyclerView.ViewHolder var1, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo var2, RecyclerView.ItemAnimator.ItemHolderInfo var3);

      void processPersistent(RecyclerView.ViewHolder var1, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo var2, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo var3);

      void unused(RecyclerView.ViewHolder var1);
   }
}
