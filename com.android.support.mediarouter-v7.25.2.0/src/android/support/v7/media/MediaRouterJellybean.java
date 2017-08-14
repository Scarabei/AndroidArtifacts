package android.support.v7.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(16)
@TargetApi(16)
final class MediaRouterJellybean {
   private static final String TAG = "MediaRouterJellybean";
   public static final int DEVICE_OUT_BLUETOOTH = 896;
   public static final int ROUTE_TYPE_LIVE_AUDIO = 1;
   public static final int ROUTE_TYPE_LIVE_VIDEO = 2;
   public static final int ROUTE_TYPE_USER = 8388608;
   public static final int ALL_ROUTE_TYPES = 8388611;

   public static Object getMediaRouter(Context context) {
      return context.getSystemService("media_router");
   }

   public static List getRoutes(Object routerObj) {
      android.media.MediaRouter router = (android.media.MediaRouter)routerObj;
      int count = router.getRouteCount();
      List out = new ArrayList(count);

      for(int i = 0; i < count; ++i) {
         out.add(router.getRouteAt(i));
      }

      return out;
   }

   public static List getCategories(Object routerObj) {
      android.media.MediaRouter router = (android.media.MediaRouter)routerObj;
      int count = router.getCategoryCount();
      List out = new ArrayList(count);

      for(int i = 0; i < count; ++i) {
         out.add(router.getCategoryAt(i));
      }

      return out;
   }

   public static Object getSelectedRoute(Object routerObj, int type) {
      return ((android.media.MediaRouter)routerObj).getSelectedRoute(type);
   }

   public static void selectRoute(Object routerObj, int types, Object routeObj) {
      ((android.media.MediaRouter)routerObj).selectRoute(types, (android.media.MediaRouter.RouteInfo)routeObj);
   }

   public static void addCallback(Object routerObj, int types, Object callbackObj) {
      ((android.media.MediaRouter)routerObj).addCallback(types, (android.media.MediaRouter.Callback)callbackObj);
   }

   public static void removeCallback(Object routerObj, Object callbackObj) {
      ((android.media.MediaRouter)routerObj).removeCallback((android.media.MediaRouter.Callback)callbackObj);
   }

   public static Object createRouteCategory(Object routerObj, String name, boolean isGroupable) {
      return ((android.media.MediaRouter)routerObj).createRouteCategory(name, isGroupable);
   }

   public static Object createUserRoute(Object routerObj, Object categoryObj) {
      return ((android.media.MediaRouter)routerObj).createUserRoute((android.media.MediaRouter.RouteCategory)categoryObj);
   }

   public static void addUserRoute(Object routerObj, Object routeObj) {
      ((android.media.MediaRouter)routerObj).addUserRoute((android.media.MediaRouter.UserRouteInfo)routeObj);
   }

   public static void removeUserRoute(Object routerObj, Object routeObj) {
      ((android.media.MediaRouter)routerObj).removeUserRoute((android.media.MediaRouter.UserRouteInfo)routeObj);
   }

   public static Object createCallback(MediaRouterJellybean.Callback callback) {
      return new MediaRouterJellybean.CallbackProxy(callback);
   }

   public static Object createVolumeCallback(MediaRouterJellybean.VolumeCallback callback) {
      return new MediaRouterJellybean.VolumeCallbackProxy(callback);
   }

   static boolean checkRoutedToBluetooth(Context context) {
      try {
         AudioManager audioManager = (AudioManager)context.getSystemService("audio");
         Method method = audioManager.getClass().getDeclaredMethod("getDevicesForStream", Integer.TYPE);
         int device = ((Integer)method.invoke(audioManager, Integer.valueOf(3))).intValue();
         return (device & 896) != 0;
      } catch (Exception var4) {
         return false;
      }
   }

   static class VolumeCallbackProxy extends android.media.MediaRouter.VolumeCallback {
      protected final MediaRouterJellybean.VolumeCallback mCallback;

      public VolumeCallbackProxy(MediaRouterJellybean.VolumeCallback callback) {
         this.mCallback = callback;
      }

      public void onVolumeSetRequest(android.media.MediaRouter.RouteInfo route, int volume) {
         this.mCallback.onVolumeSetRequest(route, volume);
      }

      public void onVolumeUpdateRequest(android.media.MediaRouter.RouteInfo route, int direction) {
         this.mCallback.onVolumeUpdateRequest(route, direction);
      }
   }

   static class CallbackProxy extends android.media.MediaRouter.Callback {
      protected final MediaRouterJellybean.Callback mCallback;

      public CallbackProxy(MediaRouterJellybean.Callback callback) {
         this.mCallback = callback;
      }

      public void onRouteSelected(android.media.MediaRouter router, int type, android.media.MediaRouter.RouteInfo route) {
         this.mCallback.onRouteSelected(type, route);
      }

      public void onRouteUnselected(android.media.MediaRouter router, int type, android.media.MediaRouter.RouteInfo route) {
         this.mCallback.onRouteUnselected(type, route);
      }

      public void onRouteAdded(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo route) {
         this.mCallback.onRouteAdded(route);
      }

      public void onRouteRemoved(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo route) {
         this.mCallback.onRouteRemoved(route);
      }

      public void onRouteChanged(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo route) {
         this.mCallback.onRouteChanged(route);
      }

      public void onRouteGrouped(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo route, android.media.MediaRouter.RouteGroup group, int index) {
         this.mCallback.onRouteGrouped(route, group, index);
      }

      public void onRouteUngrouped(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo route, android.media.MediaRouter.RouteGroup group) {
         this.mCallback.onRouteUngrouped(route, group);
      }

      public void onRouteVolumeChanged(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo route) {
         this.mCallback.onRouteVolumeChanged(route);
      }
   }

   public static final class GetDefaultRouteWorkaround {
      private Method mGetSystemAudioRouteMethod;

      public GetDefaultRouteWorkaround() {
         if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 17) {
            try {
               this.mGetSystemAudioRouteMethod = android.media.MediaRouter.class.getMethod("getSystemAudioRoute");
            } catch (NoSuchMethodException var2) {
               ;
            }

         } else {
            throw new UnsupportedOperationException();
         }
      }

      public Object getDefaultRoute(Object routerObj) {
         android.media.MediaRouter router = (android.media.MediaRouter)routerObj;
         if (this.mGetSystemAudioRouteMethod != null) {
            try {
               return this.mGetSystemAudioRouteMethod.invoke(router);
            } catch (IllegalAccessException var4) {
               ;
            } catch (InvocationTargetException var5) {
               ;
            }
         }

         return router.getRouteAt(0);
      }
   }

   public static final class SelectRouteWorkaround {
      private Method mSelectRouteIntMethod;

      public SelectRouteWorkaround() {
         if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 17) {
            try {
               this.mSelectRouteIntMethod = android.media.MediaRouter.class.getMethod("selectRouteInt", Integer.TYPE, android.media.MediaRouter.RouteInfo.class);
            } catch (NoSuchMethodException var2) {
               ;
            }

         } else {
            throw new UnsupportedOperationException();
         }
      }

      public void selectRoute(Object routerObj, int types, Object routeObj) {
         android.media.MediaRouter router = (android.media.MediaRouter)routerObj;
         android.media.MediaRouter.RouteInfo route = (android.media.MediaRouter.RouteInfo)routeObj;
         int routeTypes = route.getSupportedTypes();
         if ((routeTypes & 8388608) == 0) {
            if (this.mSelectRouteIntMethod != null) {
               try {
                  this.mSelectRouteIntMethod.invoke(router, types, route);
                  return;
               } catch (IllegalAccessException var8) {
                  Log.w("MediaRouterJellybean", "Cannot programmatically select non-user route.  Media routing may not work.", var8);
               } catch (InvocationTargetException var9) {
                  Log.w("MediaRouterJellybean", "Cannot programmatically select non-user route.  Media routing may not work.", var9);
               }
            } else {
               Log.w("MediaRouterJellybean", "Cannot programmatically select non-user route because the platform is missing the selectRouteInt() method.  Media routing may not work.");
            }
         }

         router.selectRoute(types, route);
      }
   }

   public interface VolumeCallback {
      void onVolumeSetRequest(Object var1, int var2);

      void onVolumeUpdateRequest(Object var1, int var2);
   }

   public interface Callback {
      void onRouteSelected(int var1, Object var2);

      void onRouteUnselected(int var1, Object var2);

      void onRouteAdded(Object var1);

      void onRouteRemoved(Object var1);

      void onRouteChanged(Object var1);

      void onRouteGrouped(Object var1, Object var2, int var3);

      void onRouteUngrouped(Object var1, Object var2);

      void onRouteVolumeChanged(Object var1);
   }

   public static final class RouteCategory {
      public static CharSequence getName(Object categoryObj, Context context) {
         return ((android.media.MediaRouter.RouteCategory)categoryObj).getName(context);
      }

      public static List getRoutes(Object categoryObj) {
         ArrayList out = new ArrayList();
         ((android.media.MediaRouter.RouteCategory)categoryObj).getRoutes(out);
         return out;
      }

      public static int getSupportedTypes(Object categoryObj) {
         return ((android.media.MediaRouter.RouteCategory)categoryObj).getSupportedTypes();
      }

      public static boolean isGroupable(Object categoryObj) {
         return ((android.media.MediaRouter.RouteCategory)categoryObj).isGroupable();
      }
   }

   public static final class UserRouteInfo {
      public static void setName(Object routeObj, CharSequence name) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setName(name);
      }

      public static void setStatus(Object routeObj, CharSequence status) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setStatus(status);
      }

      public static void setIconDrawable(Object routeObj, Drawable icon) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setIconDrawable(icon);
      }

      public static void setPlaybackType(Object routeObj, int type) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setPlaybackType(type);
      }

      public static void setPlaybackStream(Object routeObj, int stream) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setPlaybackStream(stream);
      }

      public static void setVolume(Object routeObj, int volume) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setVolume(volume);
      }

      public static void setVolumeMax(Object routeObj, int volumeMax) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setVolumeMax(volumeMax);
      }

      public static void setVolumeHandling(Object routeObj, int volumeHandling) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setVolumeHandling(volumeHandling);
      }

      public static void setVolumeCallback(Object routeObj, Object volumeCallbackObj) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setVolumeCallback((android.media.MediaRouter.VolumeCallback)volumeCallbackObj);
      }

      public static void setRemoteControlClient(Object routeObj, Object rccObj) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setRemoteControlClient((RemoteControlClient)rccObj);
      }
   }

   public static final class RouteGroup {
      public static List getGroupedRoutes(Object groupObj) {
         android.media.MediaRouter.RouteGroup group = (android.media.MediaRouter.RouteGroup)groupObj;
         int count = group.getRouteCount();
         List out = new ArrayList(count);

         for(int i = 0; i < count; ++i) {
            out.add(group.getRouteAt(i));
         }

         return out;
      }
   }

   public static final class RouteInfo {
      public static CharSequence getName(Object routeObj, Context context) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getName(context);
      }

      public static CharSequence getStatus(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getStatus();
      }

      public static int getSupportedTypes(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getSupportedTypes();
      }

      public static Object getCategory(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getCategory();
      }

      public static Drawable getIconDrawable(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getIconDrawable();
      }

      public static int getPlaybackType(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getPlaybackType();
      }

      public static int getPlaybackStream(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getPlaybackStream();
      }

      public static int getVolume(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getVolume();
      }

      public static int getVolumeMax(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getVolumeMax();
      }

      public static int getVolumeHandling(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getVolumeHandling();
      }

      public static Object getTag(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getTag();
      }

      public static void setTag(Object routeObj, Object tag) {
         ((android.media.MediaRouter.RouteInfo)routeObj).setTag(tag);
      }

      public static void requestSetVolume(Object routeObj, int volume) {
         ((android.media.MediaRouter.RouteInfo)routeObj).requestSetVolume(volume);
      }

      public static void requestUpdateVolume(Object routeObj, int direction) {
         ((android.media.MediaRouter.RouteInfo)routeObj).requestUpdateVolume(direction);
      }

      public static Object getGroup(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getGroup();
      }

      public static boolean isGroup(Object routeObj) {
         return routeObj instanceof android.media.MediaRouter.RouteGroup;
      }
   }
}
