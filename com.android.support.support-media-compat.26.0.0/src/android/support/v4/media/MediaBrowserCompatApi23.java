package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
class MediaBrowserCompatApi23 {
   public static Object createItemCallback(MediaBrowserCompatApi23.ItemCallback callback) {
      return new MediaBrowserCompatApi23.ItemCallbackProxy(callback);
   }

   public static void getItem(Object browserObj, String mediaId, Object itemCallbackObj) {
      ((MediaBrowser)browserObj).getItem(mediaId, (android.media.browse.MediaBrowser.ItemCallback)itemCallbackObj);
   }

   static class ItemCallbackProxy extends android.media.browse.MediaBrowser.ItemCallback {
      protected final MediaBrowserCompatApi23.ItemCallback mItemCallback;

      public ItemCallbackProxy(MediaBrowserCompatApi23.ItemCallback callback) {
         this.mItemCallback = callback;
      }

      public void onItemLoaded(MediaItem item) {
         if (item == null) {
            this.mItemCallback.onItemLoaded((Parcel)null);
         } else {
            Parcel parcel = Parcel.obtain();
            item.writeToParcel(parcel, 0);
            this.mItemCallback.onItemLoaded(parcel);
         }

      }

      public void onError(@NonNull String itemId) {
         this.mItemCallback.onError(itemId);
      }
   }

   interface ItemCallback {
      void onItemLoaded(Parcel var1);

      void onError(@NonNull String var1);
   }
}
