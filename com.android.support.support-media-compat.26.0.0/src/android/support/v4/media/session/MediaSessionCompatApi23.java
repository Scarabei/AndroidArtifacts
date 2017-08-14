package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

@RequiresApi(23)
class MediaSessionCompatApi23 {
   public static Object createCallback(MediaSessionCompatApi23.Callback callback) {
      return new MediaSessionCompatApi23.CallbackProxy(callback);
   }

   static class CallbackProxy extends MediaSessionCompatApi21.CallbackProxy {
      public CallbackProxy(MediaSessionCompatApi23.Callback callback) {
         super(callback);
      }

      public void onPlayFromUri(Uri uri, Bundle extras) {
         ((MediaSessionCompatApi23.Callback)this.mCallback).onPlayFromUri(uri, extras);
      }
   }

   public interface Callback extends MediaSessionCompatApi21.Callback {
      void onPlayFromUri(Uri var1, Bundle var2);
   }
}
