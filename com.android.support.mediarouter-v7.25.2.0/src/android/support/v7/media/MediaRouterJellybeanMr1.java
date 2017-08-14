package android.support.v7.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Display;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(17)
@TargetApi(17)
final class MediaRouterJellybeanMr1 {
   private static final String TAG = "MediaRouterJellybeanMr1";

   public static Object createCallback(MediaRouterJellybeanMr1.Callback callback) {
      return new MediaRouterJellybeanMr1.CallbackProxy(callback);
   }

   static class CallbackProxy extends MediaRouterJellybean.CallbackProxy {
      public CallbackProxy(MediaRouterJellybeanMr1.Callback callback) {
         super(callback);
      }

      public void onRoutePresentationDisplayChanged(android.media.MediaRouter router, android.media.MediaRouter.RouteInfo route) {
         ((MediaRouterJellybeanMr1.Callback)this.mCallback).onRoutePresentationDisplayChanged(route);
      }
   }

   public static final class IsConnectingWorkaround {
      private Method mGetStatusCodeMethod;
      private int mStatusConnecting;

      public IsConnectingWorkaround() {
         if (VERSION.SDK_INT != 17) {
            throw new UnsupportedOperationException();
         } else {
            try {
               Field statusConnectingField = android.media.MediaRouter.RouteInfo.class.getField("STATUS_CONNECTING");
               this.mStatusConnecting = statusConnectingField.getInt((Object)null);
               this.mGetStatusCodeMethod = android.media.MediaRouter.RouteInfo.class.getMethod("getStatusCode");
            } catch (NoSuchFieldException var2) {
               ;
            } catch (NoSuchMethodException var3) {
               ;
            } catch (IllegalAccessException var4) {
               ;
            }

         }
      }

      public boolean isConnecting(Object routeObj) {
         android.media.MediaRouter.RouteInfo route = (android.media.MediaRouter.RouteInfo)routeObj;
         if (this.mGetStatusCodeMethod != null) {
            try {
               int statusCode = ((Integer)this.mGetStatusCodeMethod.invoke(route)).intValue();
               return statusCode == this.mStatusConnecting;
            } catch (IllegalAccessException var4) {
               ;
            } catch (InvocationTargetException var5) {
               ;
            }
         }

         return false;
      }
   }

   public static final class ActiveScanWorkaround implements Runnable {
      private static final int WIFI_DISPLAY_SCAN_INTERVAL = 15000;
      private final DisplayManager mDisplayManager;
      private final Handler mHandler;
      private Method mScanWifiDisplaysMethod;
      private boolean mActivelyScanningWifiDisplays;

      public ActiveScanWorkaround(Context context, Handler handler) {
         if (VERSION.SDK_INT != 17) {
            throw new UnsupportedOperationException();
         } else {
            this.mDisplayManager = (DisplayManager)context.getSystemService("display");
            this.mHandler = handler;

            try {
               this.mScanWifiDisplaysMethod = DisplayManager.class.getMethod("scanWifiDisplays");
            } catch (NoSuchMethodException var4) {
               ;
            }

         }
      }

      public void setActiveScanRouteTypes(int routeTypes) {
         if ((routeTypes & 2) != 0) {
            if (!this.mActivelyScanningWifiDisplays) {
               if (this.mScanWifiDisplaysMethod != null) {
                  this.mActivelyScanningWifiDisplays = true;
                  this.mHandler.post(this);
               } else {
                  Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays because the DisplayManager.scanWifiDisplays() method is not available on this device.");
               }
            }
         } else if (this.mActivelyScanningWifiDisplays) {
            this.mActivelyScanningWifiDisplays = false;
            this.mHandler.removeCallbacks(this);
         }

      }

      public void run() {
         if (this.mActivelyScanningWifiDisplays) {
            try {
               this.mScanWifiDisplaysMethod.invoke(this.mDisplayManager);
            } catch (IllegalAccessException var2) {
               Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", var2);
            } catch (InvocationTargetException var3) {
               Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", var3);
            }

            this.mHandler.postDelayed(this, 15000L);
         }

      }
   }

   public interface Callback extends MediaRouterJellybean.Callback {
      void onRoutePresentationDisplayChanged(Object var1);
   }

   public static final class RouteInfo {
      public static boolean isEnabled(Object routeObj) {
         return ((android.media.MediaRouter.RouteInfo)routeObj).isEnabled();
      }

      public static Display getPresentationDisplay(Object routeObj) {
         try {
            return ((android.media.MediaRouter.RouteInfo)routeObj).getPresentationDisplay();
         } catch (NoSuchMethodError var2) {
            Log.w("MediaRouterJellybeanMr1", "Cannot get presentation display for the route.", var2);
            return null;
         }
      }
   }
}
