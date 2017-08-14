package android.support.v7.media;

import android.content.Context;
import android.os.Build.VERSION;
import java.lang.ref.WeakReference;

abstract class RemoteControlClientCompat {
   protected final Context mContext;
   protected final Object mRcc;
   protected RemoteControlClientCompat.VolumeCallback mVolumeCallback;

   protected RemoteControlClientCompat(Context context, Object rcc) {
      this.mContext = context;
      this.mRcc = rcc;
   }

   public static RemoteControlClientCompat obtain(Context context, Object rcc) {
      return (RemoteControlClientCompat)(VERSION.SDK_INT >= 16 ? new RemoteControlClientCompat.JellybeanImpl(context, rcc) : new RemoteControlClientCompat.LegacyImpl(context, rcc));
   }

   public Object getRemoteControlClient() {
      return this.mRcc;
   }

   public void setPlaybackInfo(RemoteControlClientCompat.PlaybackInfo info) {
   }

   public void setVolumeCallback(RemoteControlClientCompat.VolumeCallback callback) {
      this.mVolumeCallback = callback;
   }

   static class JellybeanImpl extends RemoteControlClientCompat {
      private final Object mRouterObj;
      private final Object mUserRouteCategoryObj;
      private final Object mUserRouteObj;
      private boolean mRegistered;

      public JellybeanImpl(Context context, Object rcc) {
         super(context, rcc);
         this.mRouterObj = MediaRouterJellybean.getMediaRouter(context);
         this.mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(this.mRouterObj, "", false);
         this.mUserRouteObj = MediaRouterJellybean.createUserRoute(this.mRouterObj, this.mUserRouteCategoryObj);
      }

      public void setPlaybackInfo(RemoteControlClientCompat.PlaybackInfo info) {
         MediaRouterJellybean.UserRouteInfo.setVolume(this.mUserRouteObj, info.volume);
         MediaRouterJellybean.UserRouteInfo.setVolumeMax(this.mUserRouteObj, info.volumeMax);
         MediaRouterJellybean.UserRouteInfo.setVolumeHandling(this.mUserRouteObj, info.volumeHandling);
         MediaRouterJellybean.UserRouteInfo.setPlaybackStream(this.mUserRouteObj, info.playbackStream);
         MediaRouterJellybean.UserRouteInfo.setPlaybackType(this.mUserRouteObj, info.playbackType);
         if (!this.mRegistered) {
            this.mRegistered = true;
            MediaRouterJellybean.UserRouteInfo.setVolumeCallback(this.mUserRouteObj, MediaRouterJellybean.createVolumeCallback(new RemoteControlClientCompat.JellybeanImpl.VolumeCallbackWrapper(this)));
            MediaRouterJellybean.UserRouteInfo.setRemoteControlClient(this.mUserRouteObj, this.mRcc);
         }

      }

      private static final class VolumeCallbackWrapper implements MediaRouterJellybean.VolumeCallback {
         private final WeakReference mImplWeak;

         public VolumeCallbackWrapper(RemoteControlClientCompat.JellybeanImpl impl) {
            this.mImplWeak = new WeakReference(impl);
         }

         public void onVolumeUpdateRequest(Object routeObj, int direction) {
            RemoteControlClientCompat.JellybeanImpl impl = (RemoteControlClientCompat.JellybeanImpl)this.mImplWeak.get();
            if (impl != null && impl.mVolumeCallback != null) {
               impl.mVolumeCallback.onVolumeUpdateRequest(direction);
            }

         }

         public void onVolumeSetRequest(Object routeObj, int volume) {
            RemoteControlClientCompat.JellybeanImpl impl = (RemoteControlClientCompat.JellybeanImpl)this.mImplWeak.get();
            if (impl != null && impl.mVolumeCallback != null) {
               impl.mVolumeCallback.onVolumeSetRequest(volume);
            }

         }
      }
   }

   static class LegacyImpl extends RemoteControlClientCompat {
      public LegacyImpl(Context context, Object rcc) {
         super(context, rcc);
      }
   }

   public interface VolumeCallback {
      void onVolumeUpdateRequest(int var1);

      void onVolumeSetRequest(int var1);
   }

   public static final class PlaybackInfo {
      public int volume;
      public int volumeMax;
      public int volumeHandling = 0;
      public int playbackStream = 3;
      public int playbackType = 1;
   }
}
