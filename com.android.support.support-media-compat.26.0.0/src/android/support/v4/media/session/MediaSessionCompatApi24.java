package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(24)
class MediaSessionCompatApi24 {
   private static final String TAG = "MediaSessionCompatApi24";

   public static Object createCallback(MediaSessionCompatApi24.Callback callback) {
      return new MediaSessionCompatApi24.CallbackProxy(callback);
   }

   public static String getCallingPackage(Object sessionObj) {
      MediaSession session = (MediaSession)sessionObj;

      try {
         Method getCallingPackageMethod = session.getClass().getMethod("getCallingPackage");
         return (String)getCallingPackageMethod.invoke(session);
      } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var3) {
         Log.e("MediaSessionCompatApi24", "Cannot execute MediaSession.getCallingPackage()", var3);
         return null;
      }
   }

   static class CallbackProxy extends MediaSessionCompatApi23.CallbackProxy {
      public CallbackProxy(MediaSessionCompatApi24.Callback callback) {
         super(callback);
      }

      public void onPrepare() {
         ((MediaSessionCompatApi24.Callback)this.mCallback).onPrepare();
      }

      public void onPrepareFromMediaId(String mediaId, Bundle extras) {
         ((MediaSessionCompatApi24.Callback)this.mCallback).onPrepareFromMediaId(mediaId, extras);
      }

      public void onPrepareFromSearch(String query, Bundle extras) {
         ((MediaSessionCompatApi24.Callback)this.mCallback).onPrepareFromSearch(query, extras);
      }

      public void onPrepareFromUri(Uri uri, Bundle extras) {
         ((MediaSessionCompatApi24.Callback)this.mCallback).onPrepareFromUri(uri, extras);
      }
   }

   public interface Callback extends MediaSessionCompatApi23.Callback {
      void onPrepare();

      void onPrepareFromMediaId(String var1, Bundle var2);

      void onPrepareFromSearch(String var1, Bundle var2);

      void onPrepareFromUri(Uri var1, Bundle var2);
   }
}
