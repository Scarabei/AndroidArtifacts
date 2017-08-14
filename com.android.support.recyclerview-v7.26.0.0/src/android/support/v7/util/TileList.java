package android.support.v7.util;

import android.util.SparseArray;
import java.lang.reflect.Array;

class TileList {
   final int mTileSize;
   private final SparseArray mTiles = new SparseArray(10);
   TileList.Tile mLastAccessedTile;

   public TileList(int tileSize) {
      this.mTileSize = tileSize;
   }

   public Object getItemAt(int pos) {
      if (this.mLastAccessedTile == null || !this.mLastAccessedTile.containsPosition(pos)) {
         int startPosition = pos - pos % this.mTileSize;
         int index = this.mTiles.indexOfKey(startPosition);
         if (index < 0) {
            return null;
         }

         this.mLastAccessedTile = (TileList.Tile)this.mTiles.valueAt(index);
      }

      return this.mLastAccessedTile.getByPosition(pos);
   }

   public int size() {
      return this.mTiles.size();
   }

   public void clear() {
      this.mTiles.clear();
   }

   public TileList.Tile getAtIndex(int index) {
      return (TileList.Tile)this.mTiles.valueAt(index);
   }

   public TileList.Tile addOrReplace(TileList.Tile newTile) {
      int index = this.mTiles.indexOfKey(newTile.mStartPosition);
      if (index < 0) {
         this.mTiles.put(newTile.mStartPosition, newTile);
         return null;
      } else {
         TileList.Tile oldTile = (TileList.Tile)this.mTiles.valueAt(index);
         this.mTiles.setValueAt(index, newTile);
         if (this.mLastAccessedTile == oldTile) {
            this.mLastAccessedTile = newTile;
         }

         return oldTile;
      }
   }

   public TileList.Tile removeAtPos(int startPosition) {
      TileList.Tile tile = (TileList.Tile)this.mTiles.get(startPosition);
      if (this.mLastAccessedTile == tile) {
         this.mLastAccessedTile = null;
      }

      this.mTiles.delete(startPosition);
      return tile;
   }

   public static class Tile {
      public final Object[] mItems;
      public int mStartPosition;
      public int mItemCount;
      TileList.Tile mNext;

      public Tile(Class klass, int size) {
         this.mItems = (Object[])((Object[])Array.newInstance(klass, size));
      }

      boolean containsPosition(int pos) {
         return this.mStartPosition <= pos && pos < this.mStartPosition + this.mItemCount;
      }

      Object getByPosition(int pos) {
         return this.mItems[pos - this.mStartPosition];
      }
   }
}
