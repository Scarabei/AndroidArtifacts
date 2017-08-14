package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.List;

@RequiresApi(24)
class MediaBrowserCompatApi24 {
   public static Object createSubscriptionCallback(MediaBrowserCompatApi24.SubscriptionCallback callback) {
      return new MediaBrowserCompatApi24.SubscriptionCallbackProxy(callback);
   }

   public static void subscribe(Object browserObj, String parentId, Bundle options, Object subscriptionCallbackObj) {
      ((MediaBrowser)browserObj).subscribe(parentId, options, (android.media.browse.MediaBrowser.SubscriptionCallback)subscriptionCallbackObj);
   }

   public static void unsubscribe(Object browserObj, String parentId, Object subscriptionCallbackObj) {
      ((MediaBrowser)browserObj).unsubscribe(parentId, (android.media.browse.MediaBrowser.SubscriptionCallback)subscriptionCallbackObj);
   }

   static class SubscriptionCallbackProxy extends MediaBrowserCompatApi21.SubscriptionCallbackProxy {
      public SubscriptionCallbackProxy(MediaBrowserCompatApi24.SubscriptionCallback callback) {
         super(callback);
      }

      public void onChildrenLoaded(@NonNull String parentId, List children, @NonNull Bundle options) {
         ((MediaBrowserCompatApi24.SubscriptionCallback)this.mSubscriptionCallback).onChildrenLoaded(parentId, children, options);
      }

      public void onError(@NonNull String parentId, @NonNull Bundle options) {
         ((MediaBrowserCompatApi24.SubscriptionCallback)this.mSubscriptionCallback).onError(parentId, options);
      }
   }

   interface SubscriptionCallback extends MediaBrowserCompatApi21.SubscriptionCallback {
      void onChildrenLoaded(@NonNull String var1, List var2, @NonNull Bundle var3);

      void onError(@NonNull String var1, @NonNull Bundle var2);
   }
}
