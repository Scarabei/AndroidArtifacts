package android.support.v7.util;

interface ThreadUtil {
   ThreadUtil.MainThreadCallback getMainThreadProxy(ThreadUtil.MainThreadCallback var1);

   ThreadUtil.BackgroundCallback getBackgroundProxy(ThreadUtil.BackgroundCallback var1);

   public interface BackgroundCallback {
      void refresh(int var1);

      void updateRange(int var1, int var2, int var3, int var4, int var5);

      void loadTile(int var1, int var2);

      void recycleTile(TileList.Tile var1);
   }

   public interface MainThreadCallback {
      void updateItemCount(int var1, int var2);

      void addTile(int var1, TileList.Tile var2);

      void removeTile(int var1, int var2);
   }
}
