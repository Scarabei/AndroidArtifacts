package android.support.v7.media;

import android.annotation.TargetApi;
import android.media.MediaRouter.Callback;
import android.support.annotation.RequiresApi;

@RequiresApi(18)
@TargetApi(18)
final class MediaRouterJellybeanMr2 {
   public static Object getDefaultRoute(Object routerObj) {
      return ((android.media.MediaRouter)routerObj).getDefaultRoute();
   }

   public static void addCallback(Object routerObj, int types, Object callbackObj, int flags) {
      ((android.media.MediaRouter)routerObj).addCallback(types, (Callback)callbackObj, flags);
   }

   public static final class UserRouteInfo {
      public static void setDescription(Object routeObj, CharSequence description) {
         ((android.media.MediaRouter.UserRouteInfo)routeObj).setDescription(description);
      }
   }

   public static final class RouteInfo {
      public static CharSequence getDescription(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).getDescription();
      }

      public static boolean isConnecting(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).isConnecting();
      }
   }
}
