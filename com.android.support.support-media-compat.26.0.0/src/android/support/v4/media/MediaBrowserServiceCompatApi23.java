package android.support.v4.media;

import android.content.Context;
import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
class MediaBrowserServiceCompatApi23 {
   public static Object createService(Context context, MediaBrowserServiceCompatApi23.ServiceCompatProxy serviceProxy) {
      return new MediaBrowserServiceCompatApi23.MediaBrowserServiceAdaptor(context, serviceProxy);
   }

   static class MediaBrowserServiceAdaptor extends MediaBrowserServiceCompatApi21.MediaBrowserServiceAdaptor {
      MediaBrowserServiceAdaptor(Context context, MediaBrowserServiceCompatApi23.ServiceCompatProxy serviceWrapper) {
         super(context, serviceWrapper);
      }

      public void onLoadItem(String itemId, Result result) {
         ((MediaBrowserServiceCompatApi23.ServiceCompatProxy)this.mServiceProxy).onLoadItem(itemId, new MediaBrowserServiceCompatApi21.ResultWrapper(result));
      }
   }

   public interface ServiceCompatProxy extends MediaBrowserServiceCompatApi21.ServiceCompatProxy {
      void onLoadItem(String var1, MediaBrowserServiceCompatApi21.ResultWrapper var2);
   }
}
