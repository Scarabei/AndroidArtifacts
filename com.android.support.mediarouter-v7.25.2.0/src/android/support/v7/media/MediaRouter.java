package android.support.v7.media;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.ActivityManagerCompat;
import android.support.v4.hardware.display.DisplayManagerCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.OnActiveChangeListener;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public final class MediaRouter {
   static final String TAG = "MediaRouter";
   static final boolean DEBUG = Log.isLoggable("MediaRouter", 3);
   public static final int UNSELECT_REASON_UNKNOWN = 0;
   public static final int UNSELECT_REASON_DISCONNECTED = 1;
   public static final int UNSELECT_REASON_STOPPED = 2;
   public static final int UNSELECT_REASON_ROUTE_CHANGED = 3;
   static MediaRouter.GlobalMediaRouter sGlobal;
   final Context mContext;
   final ArrayList mCallbackRecords = new ArrayList();
   public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
   public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
   public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
   public static final int CALLBACK_FLAG_FORCE_DISCOVERY = 8;
   public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
   public static final int AVAILABILITY_FLAG_REQUIRE_MATCH = 2;

   MediaRouter(Context context) {
      this.mContext = context;
   }

   public static MediaRouter getInstance(@NonNull Context context) {
      if (context == null) {
         throw new IllegalArgumentException("context must not be null");
      } else {
         checkCallingThread();
         if (sGlobal == null) {
            sGlobal = new MediaRouter.GlobalMediaRouter(context.getApplicationContext());
            sGlobal.start();
         }

         return sGlobal.getRouter(context);
      }
   }

   public List getRoutes() {
      checkCallingThread();
      return sGlobal.getRoutes();
   }

   public List getProviders() {
      checkCallingThread();
      return sGlobal.getProviders();
   }

   @NonNull
   public MediaRouter.RouteInfo getDefaultRoute() {
      checkCallingThread();
      return sGlobal.getDefaultRoute();
   }

   public MediaRouter.RouteInfo getBluetoothRoute() {
      checkCallingThread();
      return sGlobal.getBluetoothRoute();
   }

   @NonNull
   public MediaRouter.RouteInfo getSelectedRoute() {
      checkCallingThread();
      return sGlobal.getSelectedRoute();
   }

   @NonNull
   public MediaRouter.RouteInfo updateSelectedRoute(@NonNull MediaRouteSelector selector) {
      if (selector == null) {
         throw new IllegalArgumentException("selector must not be null");
      } else {
         checkCallingThread();
         if (DEBUG) {
            Log.d("MediaRouter", "updateSelectedRoute: " + selector);
         }

         MediaRouter.RouteInfo route = sGlobal.getSelectedRoute();
         if (!route.isDefaultOrBluetooth() && !route.matchesSelector(selector)) {
            route = sGlobal.chooseFallbackRoute();
            sGlobal.selectRoute(route);
         }

         return route;
      }
   }

   public void selectRoute(@NonNull MediaRouter.RouteInfo route) {
      if (route == null) {
         throw new IllegalArgumentException("route must not be null");
      } else {
         checkCallingThread();
         if (DEBUG) {
            Log.d("MediaRouter", "selectRoute: " + route);
         }

         sGlobal.selectRoute(route);
      }
   }

   public void unselect(int reason) {
      if (reason >= 0 && reason <= 3) {
         checkCallingThread();
         MediaRouter.RouteInfo fallbackRoute = sGlobal.chooseFallbackRoute();
         if (sGlobal.getSelectedRoute() != fallbackRoute) {
            sGlobal.selectRoute(fallbackRoute, reason);
         } else {
            sGlobal.selectRoute(sGlobal.getDefaultRoute(), reason);
         }

      } else {
         throw new IllegalArgumentException("Unsupported reason to unselect route");
      }
   }

   public boolean isRouteAvailable(@NonNull MediaRouteSelector selector, int flags) {
      if (selector == null) {
         throw new IllegalArgumentException("selector must not be null");
      } else {
         checkCallingThread();
         return sGlobal.isRouteAvailable(selector, flags);
      }
   }

   public void addCallback(MediaRouteSelector selector, MediaRouter.Callback callback) {
      this.addCallback(selector, callback, 0);
   }

   public void addCallback(@NonNull MediaRouteSelector selector, @NonNull MediaRouter.Callback callback, int flags) {
      if (selector == null) {
         throw new IllegalArgumentException("selector must not be null");
      } else if (callback == null) {
         throw new IllegalArgumentException("callback must not be null");
      } else {
         checkCallingThread();
         if (DEBUG) {
            Log.d("MediaRouter", "addCallback: selector=" + selector + ", callback=" + callback + ", flags=" + Integer.toHexString(flags));
         }

         int index = this.findCallbackRecord(callback);
         MediaRouter.CallbackRecord record;
         if (index < 0) {
            record = new MediaRouter.CallbackRecord(this, callback);
            this.mCallbackRecords.add(record);
         } else {
            record = (MediaRouter.CallbackRecord)this.mCallbackRecords.get(index);
         }

         boolean updateNeeded = false;
         if ((flags & ~record.mFlags) != 0) {
            record.mFlags |= flags;
            updateNeeded = true;
         }

         if (!record.mSelector.contains(selector)) {
            record.mSelector = (new MediaRouteSelector.Builder(record.mSelector)).addSelector(selector).build();
            updateNeeded = true;
         }

         if (updateNeeded) {
            sGlobal.updateDiscoveryRequest();
         }

      }
   }

   public void removeCallback(@NonNull MediaRouter.Callback callback) {
      if (callback == null) {
         throw new IllegalArgumentException("callback must not be null");
      } else {
         checkCallingThread();
         if (DEBUG) {
            Log.d("MediaRouter", "removeCallback: callback=" + callback);
         }

         int index = this.findCallbackRecord(callback);
         if (index >= 0) {
            this.mCallbackRecords.remove(index);
            sGlobal.updateDiscoveryRequest();
         }

      }
   }

   private int findCallbackRecord(MediaRouter.Callback callback) {
      int count = this.mCallbackRecords.size();

      for(int i = 0; i < count; ++i) {
         if (((MediaRouter.CallbackRecord)this.mCallbackRecords.get(i)).mCallback == callback) {
            return i;
         }
      }

      return -1;
   }

   public void addProvider(@NonNull MediaRouteProvider providerInstance) {
      if (providerInstance == null) {
         throw new IllegalArgumentException("providerInstance must not be null");
      } else {
         checkCallingThread();
         if (DEBUG) {
            Log.d("MediaRouter", "addProvider: " + providerInstance);
         }

         sGlobal.addProvider(providerInstance);
      }
   }

   public void removeProvider(@NonNull MediaRouteProvider providerInstance) {
      if (providerInstance == null) {
         throw new IllegalArgumentException("providerInstance must not be null");
      } else {
         checkCallingThread();
         if (DEBUG) {
            Log.d("MediaRouter", "removeProvider: " + providerInstance);
         }

         sGlobal.removeProvider(providerInstance);
      }
   }

   public void addRemoteControlClient(@NonNull Object remoteControlClient) {
      if (remoteControlClient == null) {
         throw new IllegalArgumentException("remoteControlClient must not be null");
      } else {
         checkCallingThread();
         if (DEBUG) {
            Log.d("MediaRouter", "addRemoteControlClient: " + remoteControlClient);
         }

         sGlobal.addRemoteControlClient(remoteControlClient);
      }
   }

   public void removeRemoteControlClient(@NonNull Object remoteControlClient) {
      if (remoteControlClient == null) {
         throw new IllegalArgumentException("remoteControlClient must not be null");
      } else {
         if (DEBUG) {
            Log.d("MediaRouter", "removeRemoteControlClient: " + remoteControlClient);
         }

         sGlobal.removeRemoteControlClient(remoteControlClient);
      }
   }

   public void setMediaSession(Object mediaSession) {
      if (DEBUG) {
         Log.d("MediaRouter", "addMediaSession: " + mediaSession);
      }

      sGlobal.setMediaSession(mediaSession);
   }

   public void setMediaSessionCompat(MediaSessionCompat mediaSession) {
      if (DEBUG) {
         Log.d("MediaRouter", "addMediaSessionCompat: " + mediaSession);
      }

      sGlobal.setMediaSessionCompat(mediaSession);
   }

   public Token getMediaSessionToken() {
      return sGlobal.getMediaSessionToken();
   }

   static void checkCallingThread() {
      if (Looper.myLooper() != Looper.getMainLooper()) {
         throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
      }
   }

   static boolean equal(Object a, Object b) {
      return a == b || a != null && b != null && a.equals(b);
   }

   private static final class GlobalMediaRouter implements SystemMediaRouteProvider.SyncCallback, RegisteredMediaRouteProviderWatcher.Callback {
      final Context mApplicationContext;
      final ArrayList mRouters = new ArrayList();
      private final ArrayList mRoutes = new ArrayList();
      private final Map mUniqueIdMap = new HashMap();
      private final ArrayList mProviders = new ArrayList();
      private final ArrayList mRemoteControlClients = new ArrayList();
      final RemoteControlClientCompat.PlaybackInfo mPlaybackInfo = new RemoteControlClientCompat.PlaybackInfo();
      private final MediaRouter.GlobalMediaRouter.ProviderCallback mProviderCallback = new MediaRouter.GlobalMediaRouter.ProviderCallback();
      final MediaRouter.GlobalMediaRouter.CallbackHandler mCallbackHandler = new MediaRouter.GlobalMediaRouter.CallbackHandler();
      private final DisplayManagerCompat mDisplayManager;
      final SystemMediaRouteProvider mSystemProvider;
      private final boolean mLowRam;
      private RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
      private MediaRouter.RouteInfo mDefaultRoute;
      private MediaRouter.RouteInfo mBluetoothRoute;
      MediaRouter.RouteInfo mSelectedRoute;
      private MediaRouteProvider.RouteController mSelectedRouteController;
      private final Map mRouteControllerMap = new HashMap();
      private MediaRouteDiscoveryRequest mDiscoveryRequest;
      private MediaRouter.GlobalMediaRouter.MediaSessionRecord mMediaSession;
      MediaSessionCompat mRccMediaSession;
      private MediaSessionCompat mCompatSession;
      private OnActiveChangeListener mSessionActiveListener = new OnActiveChangeListener() {
         public void onActiveChanged() {
            if (GlobalMediaRouter.this.mRccMediaSession != null) {
               if (GlobalMediaRouter.this.mRccMediaSession.isActive()) {
                  GlobalMediaRouter.this.addRemoteControlClient(GlobalMediaRouter.this.mRccMediaSession.getRemoteControlClient());
               } else {
                  GlobalMediaRouter.this.removeRemoteControlClient(GlobalMediaRouter.this.mRccMediaSession.getRemoteControlClient());
               }
            }

         }
      };

      GlobalMediaRouter(Context applicationContext) {
         this.mApplicationContext = applicationContext;
         this.mDisplayManager = DisplayManagerCompat.getInstance(applicationContext);
         this.mLowRam = ActivityManagerCompat.isLowRamDevice((ActivityManager)applicationContext.getSystemService("activity"));
         this.mSystemProvider = SystemMediaRouteProvider.obtain(applicationContext, this);
         this.addProvider(this.mSystemProvider);
      }

      public void start() {
         this.mRegisteredProviderWatcher = new RegisteredMediaRouteProviderWatcher(this.mApplicationContext, this);
         this.mRegisteredProviderWatcher.start();
      }

      public MediaRouter getRouter(Context context) {
         int i = this.mRouters.size();

         while(true) {
            --i;
            MediaRouter router;
            if (i < 0) {
               router = new MediaRouter(context);
               this.mRouters.add(new WeakReference(router));
               return router;
            }

            router = (MediaRouter)((WeakReference)this.mRouters.get(i)).get();
            if (router == null) {
               this.mRouters.remove(i);
            } else if (router.mContext == context) {
               return router;
            }
         }
      }

      public ContentResolver getContentResolver() {
         return this.mApplicationContext.getContentResolver();
      }

      public Context getProviderContext(String packageName) {
         if (packageName.equals("android")) {
            return this.mApplicationContext;
         } else {
            try {
               return this.mApplicationContext.createPackageContext(packageName, 4);
            } catch (NameNotFoundException var3) {
               return null;
            }
         }
      }

      public Display getDisplay(int displayId) {
         return this.mDisplayManager.getDisplay(displayId);
      }

      public void sendControlRequest(MediaRouter.RouteInfo route, Intent intent, MediaRouter.ControlRequestCallback callback) {
         if (route != this.mSelectedRoute || this.mSelectedRouteController == null || !this.mSelectedRouteController.onControlRequest(intent, callback)) {
            if (callback != null) {
               callback.onError((String)null, (Bundle)null);
            }

         }
      }

      public void requestSetVolume(MediaRouter.RouteInfo route, int volume) {
         if (route == this.mSelectedRoute && this.mSelectedRouteController != null) {
            this.mSelectedRouteController.onSetVolume(volume);
         } else if (!this.mRouteControllerMap.isEmpty()) {
            MediaRouteProvider.RouteController controller = (MediaRouteProvider.RouteController)this.mRouteControllerMap.get(route.mDescriptorId);
            if (controller != null) {
               controller.onSetVolume(volume);
            }
         }

      }

      public void requestUpdateVolume(MediaRouter.RouteInfo route, int delta) {
         if (route == this.mSelectedRoute && this.mSelectedRouteController != null) {
            this.mSelectedRouteController.onUpdateVolume(delta);
         }

      }

      public MediaRouter.RouteInfo getRoute(String uniqueId) {
         Iterator var2 = this.mRoutes.iterator();

         MediaRouter.RouteInfo info;
         do {
            if (!var2.hasNext()) {
               return null;
            }

            info = (MediaRouter.RouteInfo)var2.next();
         } while(!info.mUniqueId.equals(uniqueId));

         return info;
      }

      public List getRoutes() {
         return this.mRoutes;
      }

      public List getProviders() {
         return this.mProviders;
      }

      public MediaRouter.RouteInfo getDefaultRoute() {
         if (this.mDefaultRoute == null) {
            throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
         } else {
            return this.mDefaultRoute;
         }
      }

      public MediaRouter.RouteInfo getBluetoothRoute() {
         return this.mBluetoothRoute;
      }

      public MediaRouter.RouteInfo getSelectedRoute() {
         if (this.mSelectedRoute == null) {
            throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
         } else {
            return this.mSelectedRoute;
         }
      }

      public void selectRoute(MediaRouter.RouteInfo route) {
         this.selectRoute(route, 3);
      }

      public void selectRoute(MediaRouter.RouteInfo route, int unselectReason) {
         if (!this.mRoutes.contains(route)) {
            Log.w("MediaRouter", "Ignoring attempt to select removed route: " + route);
         } else if (!route.mEnabled) {
            Log.w("MediaRouter", "Ignoring attempt to select disabled route: " + route);
         } else {
            this.setSelectedRouteInternal(route, unselectReason);
         }
      }

      public boolean isRouteAvailable(MediaRouteSelector selector, int flags) {
         if (selector.isEmpty()) {
            return false;
         } else if ((flags & 2) == 0 && this.mLowRam) {
            return true;
         } else {
            int routeCount = this.mRoutes.size();

            for(int i = 0; i < routeCount; ++i) {
               MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)this.mRoutes.get(i);
               if (((flags & 1) == 0 || !route.isDefaultOrBluetooth()) && route.matchesSelector(selector)) {
                  return true;
               }
            }

            return false;
         }
      }

      public void updateDiscoveryRequest() {
         boolean discover = false;
         boolean activeScan = false;
         MediaRouteSelector.Builder builder = new MediaRouteSelector.Builder();
         int i = this.mRouters.size();

         while(true) {
            while(true) {
               --i;
               int i;
               if (i < 0) {
                  MediaRouteSelector selector = discover ? builder.build() : MediaRouteSelector.EMPTY;
                  if (this.mDiscoveryRequest != null && this.mDiscoveryRequest.getSelector().equals(selector) && this.mDiscoveryRequest.isActiveScan() == activeScan) {
                     return;
                  }

                  if (selector.isEmpty() && !activeScan) {
                     if (this.mDiscoveryRequest == null) {
                        return;
                     }

                     this.mDiscoveryRequest = null;
                  } else {
                     this.mDiscoveryRequest = new MediaRouteDiscoveryRequest(selector, activeScan);
                  }

                  if (MediaRouter.DEBUG) {
                     Log.d("MediaRouter", "Updated discovery request: " + this.mDiscoveryRequest);
                  }

                  if (discover && !activeScan && this.mLowRam) {
                     Log.i("MediaRouter", "Forcing passive route discovery on a low-RAM device, system performance may be affected.  Please consider using CALLBACK_FLAG_REQUEST_DISCOVERY instead of CALLBACK_FLAG_FORCE_DISCOVERY.");
                  }

                  int providerCount = this.mProviders.size();

                  for(i = 0; i < providerCount; ++i) {
                     ((MediaRouter.ProviderInfo)this.mProviders.get(i)).mProviderInstance.setDiscoveryRequest(this.mDiscoveryRequest);
                  }

                  return;
               }

               MediaRouter router = (MediaRouter)((WeakReference)this.mRouters.get(i)).get();
               if (router == null) {
                  this.mRouters.remove(i);
               } else {
                  i = router.mCallbackRecords.size();

                  for(int j = 0; j < i; ++j) {
                     MediaRouter.CallbackRecord callback = (MediaRouter.CallbackRecord)router.mCallbackRecords.get(j);
                     builder.addSelector(callback.mSelector);
                     if ((callback.mFlags & 1) != 0) {
                        activeScan = true;
                        discover = true;
                     }

                     if ((callback.mFlags & 4) != 0 && !this.mLowRam) {
                        discover = true;
                     }

                     if ((callback.mFlags & 8) != 0) {
                        discover = true;
                     }
                  }
               }
            }
         }
      }

      public void addProvider(MediaRouteProvider providerInstance) {
         int index = this.findProviderInfo(providerInstance);
         if (index < 0) {
            MediaRouter.ProviderInfo provider = new MediaRouter.ProviderInfo(providerInstance);
            this.mProviders.add(provider);
            if (MediaRouter.DEBUG) {
               Log.d("MediaRouter", "Provider added: " + provider);
            }

            this.mCallbackHandler.post(513, provider);
            this.updateProviderContents(provider, providerInstance.getDescriptor());
            providerInstance.setCallback(this.mProviderCallback);
            providerInstance.setDiscoveryRequest(this.mDiscoveryRequest);
         }

      }

      public void removeProvider(MediaRouteProvider providerInstance) {
         int index = this.findProviderInfo(providerInstance);
         if (index >= 0) {
            providerInstance.setCallback((MediaRouteProvider.Callback)null);
            providerInstance.setDiscoveryRequest((MediaRouteDiscoveryRequest)null);
            MediaRouter.ProviderInfo provider = (MediaRouter.ProviderInfo)this.mProviders.get(index);
            this.updateProviderContents(provider, (MediaRouteProviderDescriptor)null);
            if (MediaRouter.DEBUG) {
               Log.d("MediaRouter", "Provider removed: " + provider);
            }

            this.mCallbackHandler.post(514, provider);
            this.mProviders.remove(index);
         }

      }

      void updateProviderDescriptor(MediaRouteProvider providerInstance, MediaRouteProviderDescriptor descriptor) {
         int index = this.findProviderInfo(providerInstance);
         if (index >= 0) {
            MediaRouter.ProviderInfo provider = (MediaRouter.ProviderInfo)this.mProviders.get(index);
            this.updateProviderContents(provider, descriptor);
         }

      }

      private int findProviderInfo(MediaRouteProvider providerInstance) {
         int count = this.mProviders.size();

         for(int i = 0; i < count; ++i) {
            if (((MediaRouter.ProviderInfo)this.mProviders.get(i)).mProviderInstance == providerInstance) {
               return i;
            }
         }

         return -1;
      }

      private void updateProviderContents(MediaRouter.ProviderInfo provider, MediaRouteProviderDescriptor providerDescriptor) {
         if (provider.updateDescriptor(providerDescriptor)) {
            int targetIndex = 0;
            boolean selectedRouteDescriptorChanged = false;
            if (providerDescriptor != null) {
               if (!providerDescriptor.isValid()) {
                  Log.w("MediaRouter", "Ignoring invalid provider descriptor: " + providerDescriptor);
               } else {
                  List routeDescriptors = providerDescriptor.getRoutes();
                  int routeCount = routeDescriptors.size();
                  List addedGroups = new ArrayList();
                  List updatedGroups = new ArrayList();

                  for(int i = 0; i < routeCount; ++i) {
                     MediaRouteDescriptor routeDescriptor = (MediaRouteDescriptor)routeDescriptors.get(i);
                     String id = routeDescriptor.getId();
                     int sourceIndex = provider.findRouteByDescriptorId(id);
                     if (sourceIndex < 0) {
                        String uniqueId = this.assignRouteUniqueId(provider, id);
                        boolean isGroup = routeDescriptor.getGroupMemberIds() != null;
                        MediaRouter.RouteInfo route = isGroup ? new MediaRouter.RouteGroup(provider, id, uniqueId) : new MediaRouter.RouteInfo(provider, id, uniqueId);
                        provider.mRoutes.add(targetIndex++, route);
                        this.mRoutes.add(route);
                        if (isGroup) {
                           addedGroups.add(new Pair(route, routeDescriptor));
                        } else {
                           ((MediaRouter.RouteInfo)route).maybeUpdateDescriptor(routeDescriptor);
                           if (MediaRouter.DEBUG) {
                              Log.d("MediaRouter", "Route added: " + route);
                           }

                           this.mCallbackHandler.post(257, route);
                        }
                     } else if (sourceIndex < targetIndex) {
                        Log.w("MediaRouter", "Ignoring route descriptor with duplicate id: " + routeDescriptor);
                     } else {
                        MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)provider.mRoutes.get(sourceIndex);
                        Collections.swap(provider.mRoutes, sourceIndex, targetIndex++);
                        if (route instanceof MediaRouter.RouteGroup) {
                           updatedGroups.add(new Pair(route, routeDescriptor));
                        } else if (this.updateRouteDescriptorAndNotify(route, routeDescriptor) != 0 && route == this.mSelectedRoute) {
                           selectedRouteDescriptorChanged = true;
                        }
                     }
                  }

                  Iterator var18;
                  Pair pair;
                  MediaRouter.RouteInfo route;
                  for(var18 = addedGroups.iterator(); var18.hasNext(); this.mCallbackHandler.post(257, route)) {
                     pair = (Pair)var18.next();
                     route = (MediaRouter.RouteInfo)pair.first;
                     route.maybeUpdateDescriptor((MediaRouteDescriptor)pair.second);
                     if (MediaRouter.DEBUG) {
                        Log.d("MediaRouter", "Route added: " + route);
                     }
                  }

                  var18 = updatedGroups.iterator();

                  while(var18.hasNext()) {
                     pair = (Pair)var18.next();
                     route = (MediaRouter.RouteInfo)pair.first;
                     if (this.updateRouteDescriptorAndNotify(route, (MediaRouteDescriptor)pair.second) != 0 && route == this.mSelectedRoute) {
                        selectedRouteDescriptorChanged = true;
                     }
                  }
               }
            }

            int i;
            MediaRouter.RouteInfo route;
            for(i = provider.mRoutes.size() - 1; i >= targetIndex; --i) {
               route = (MediaRouter.RouteInfo)provider.mRoutes.get(i);
               route.maybeUpdateDescriptor((MediaRouteDescriptor)null);
               this.mRoutes.remove(route);
            }

            this.updateSelectedRouteIfNeeded(selectedRouteDescriptorChanged);

            for(i = provider.mRoutes.size() - 1; i >= targetIndex; --i) {
               route = (MediaRouter.RouteInfo)provider.mRoutes.remove(i);
               if (MediaRouter.DEBUG) {
                  Log.d("MediaRouter", "Route removed: " + route);
               }

               this.mCallbackHandler.post(258, route);
            }

            if (MediaRouter.DEBUG) {
               Log.d("MediaRouter", "Provider changed: " + provider);
            }

            this.mCallbackHandler.post(515, provider);
         }

      }

      private int updateRouteDescriptorAndNotify(MediaRouter.RouteInfo route, MediaRouteDescriptor routeDescriptor) {
         int changes = route.maybeUpdateDescriptor(routeDescriptor);
         if (changes != 0) {
            if ((changes & 1) != 0) {
               if (MediaRouter.DEBUG) {
                  Log.d("MediaRouter", "Route changed: " + route);
               }

               this.mCallbackHandler.post(259, route);
            }

            if ((changes & 2) != 0) {
               if (MediaRouter.DEBUG) {
                  Log.d("MediaRouter", "Route volume changed: " + route);
               }

               this.mCallbackHandler.post(260, route);
            }

            if ((changes & 4) != 0) {
               if (MediaRouter.DEBUG) {
                  Log.d("MediaRouter", "Route presentation display changed: " + route);
               }

               this.mCallbackHandler.post(261, route);
            }
         }

         return changes;
      }

      private String assignRouteUniqueId(MediaRouter.ProviderInfo provider, String routeDescriptorId) {
         String componentName = provider.getComponentName().flattenToShortString();
         String uniqueId = componentName + ":" + routeDescriptorId;
         if (this.findRouteByUniqueId(uniqueId) < 0) {
            this.mUniqueIdMap.put(new Pair(componentName, routeDescriptorId), uniqueId);
            return uniqueId;
         } else {
            Log.w("MediaRouter", "Either " + routeDescriptorId + " isn't unique in " + componentName + " or we're trying to assign a unique ID for an already added route");
            int i = 2;

            while(true) {
               String newUniqueId = String.format(Locale.US, "%s_%d", uniqueId, i);
               if (this.findRouteByUniqueId(newUniqueId) < 0) {
                  this.mUniqueIdMap.put(new Pair(componentName, routeDescriptorId), newUniqueId);
                  return newUniqueId;
               }

               ++i;
            }
         }
      }

      private int findRouteByUniqueId(String uniqueId) {
         int count = this.mRoutes.size();

         for(int i = 0; i < count; ++i) {
            if (((MediaRouter.RouteInfo)this.mRoutes.get(i)).mUniqueId.equals(uniqueId)) {
               return i;
            }
         }

         return -1;
      }

      private String getUniqueId(MediaRouter.ProviderInfo provider, String routeDescriptorId) {
         String componentName = provider.getComponentName().flattenToShortString();
         return (String)this.mUniqueIdMap.get(new Pair(componentName, routeDescriptorId));
      }

      private void updateSelectedRouteIfNeeded(boolean selectedRouteDescriptorChanged) {
         if (this.mDefaultRoute != null && !this.isRouteSelectable(this.mDefaultRoute)) {
            Log.i("MediaRouter", "Clearing the default route because it is no longer selectable: " + this.mDefaultRoute);
            this.mDefaultRoute = null;
         }

         Iterator var2;
         MediaRouter.RouteInfo route;
         if (this.mDefaultRoute == null && !this.mRoutes.isEmpty()) {
            var2 = this.mRoutes.iterator();

            while(var2.hasNext()) {
               route = (MediaRouter.RouteInfo)var2.next();
               if (this.isSystemDefaultRoute(route) && this.isRouteSelectable(route)) {
                  this.mDefaultRoute = route;
                  Log.i("MediaRouter", "Found default route: " + this.mDefaultRoute);
                  break;
               }
            }
         }

         if (this.mBluetoothRoute != null && !this.isRouteSelectable(this.mBluetoothRoute)) {
            Log.i("MediaRouter", "Clearing the bluetooth route because it is no longer selectable: " + this.mBluetoothRoute);
            this.mBluetoothRoute = null;
         }

         if (this.mBluetoothRoute == null && !this.mRoutes.isEmpty()) {
            var2 = this.mRoutes.iterator();

            while(var2.hasNext()) {
               route = (MediaRouter.RouteInfo)var2.next();
               if (this.isSystemLiveAudioOnlyRoute(route) && this.isRouteSelectable(route)) {
                  this.mBluetoothRoute = route;
                  Log.i("MediaRouter", "Found bluetooth route: " + this.mBluetoothRoute);
                  break;
               }
            }
         }

         if (this.mSelectedRoute != null && !this.isRouteSelectable(this.mSelectedRoute)) {
            Log.i("MediaRouter", "Unselecting the current route because it is no longer selectable: " + this.mSelectedRoute);
            this.setSelectedRouteInternal((MediaRouter.RouteInfo)null, 0);
         }

         if (this.mSelectedRoute == null) {
            this.setSelectedRouteInternal(this.chooseFallbackRoute(), 0);
         } else if (selectedRouteDescriptorChanged) {
            if (this.mSelectedRoute instanceof MediaRouter.RouteGroup) {
               List routes = ((MediaRouter.RouteGroup)this.mSelectedRoute).getRoutes();
               Set idSet = new HashSet();
               Iterator iter = routes.iterator();

               while(iter.hasNext()) {
                  MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)iter.next();
                  idSet.add(route.mDescriptorId);
               }

               iter = this.mRouteControllerMap.entrySet().iterator();

               while(iter.hasNext()) {
                  Entry entry = (Entry)iter.next();
                  if (!idSet.contains(entry.getKey())) {
                     MediaRouteProvider.RouteController controller = (MediaRouteProvider.RouteController)entry.getValue();
                     controller.onUnselect();
                     controller.onRelease();
                     iter.remove();
                  }
               }

               Iterator var11 = routes.iterator();

               while(var11.hasNext()) {
                  MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)var11.next();
                  if (!this.mRouteControllerMap.containsKey(route.mDescriptorId)) {
                     MediaRouteProvider.RouteController controller = route.getProviderInstance().onCreateRouteController(route.mDescriptorId, this.mSelectedRoute.mDescriptorId);
                     controller.onSelect();
                     this.mRouteControllerMap.put(route.mDescriptorId, controller);
                  }
               }
            }

            this.updatePlaybackInfoFromSelectedRoute();
         }

      }

      MediaRouter.RouteInfo chooseFallbackRoute() {
         Iterator var1 = this.mRoutes.iterator();

         MediaRouter.RouteInfo route;
         do {
            if (!var1.hasNext()) {
               return this.mDefaultRoute;
            }

            route = (MediaRouter.RouteInfo)var1.next();
         } while(route == this.mDefaultRoute || !this.isSystemLiveAudioOnlyRoute(route) || !this.isRouteSelectable(route));

         return route;
      }

      private boolean isSystemLiveAudioOnlyRoute(MediaRouter.RouteInfo route) {
         return route.getProviderInstance() == this.mSystemProvider && route.supportsControlCategory("android.media.intent.category.LIVE_AUDIO") && !route.supportsControlCategory("android.media.intent.category.LIVE_VIDEO");
      }

      private boolean isRouteSelectable(MediaRouter.RouteInfo route) {
         return route.mDescriptor != null && route.mEnabled;
      }

      private boolean isSystemDefaultRoute(MediaRouter.RouteInfo route) {
         return route.getProviderInstance() == this.mSystemProvider && route.mDescriptorId.equals("DEFAULT_ROUTE");
      }

      private void setSelectedRouteInternal(MediaRouter.RouteInfo route, int unselectReason) {
         if (this.mSelectedRoute != route) {
            if (this.mSelectedRoute != null) {
               if (MediaRouter.DEBUG) {
                  Log.d("MediaRouter", "Route unselected: " + this.mSelectedRoute + " reason: " + unselectReason);
               }

               this.mCallbackHandler.post(263, this.mSelectedRoute, unselectReason);
               if (this.mSelectedRouteController != null) {
                  this.mSelectedRouteController.onUnselect(unselectReason);
                  this.mSelectedRouteController.onRelease();
                  this.mSelectedRouteController = null;
               }

               if (!this.mRouteControllerMap.isEmpty()) {
                  Iterator var3 = this.mRouteControllerMap.values().iterator();

                  while(var3.hasNext()) {
                     MediaRouteProvider.RouteController controller = (MediaRouteProvider.RouteController)var3.next();
                     controller.onUnselect(unselectReason);
                     controller.onRelease();
                  }

                  this.mRouteControllerMap.clear();
               }
            }

            this.mSelectedRoute = route;
            if (this.mSelectedRoute != null) {
               this.mSelectedRouteController = route.getProviderInstance().onCreateRouteController(route.mDescriptorId);
               if (this.mSelectedRouteController != null) {
                  this.mSelectedRouteController.onSelect();
               }

               if (MediaRouter.DEBUG) {
                  Log.d("MediaRouter", "Route selected: " + this.mSelectedRoute);
               }

               this.mCallbackHandler.post(262, this.mSelectedRoute);
               if (this.mSelectedRoute instanceof MediaRouter.RouteGroup) {
                  List routes = ((MediaRouter.RouteGroup)this.mSelectedRoute).getRoutes();
                  this.mRouteControllerMap.clear();
                  Iterator var8 = routes.iterator();

                  while(var8.hasNext()) {
                     MediaRouter.RouteInfo r = (MediaRouter.RouteInfo)var8.next();
                     MediaRouteProvider.RouteController controller = r.getProviderInstance().onCreateRouteController(r.mDescriptorId, this.mSelectedRoute.mDescriptorId);
                     controller.onSelect();
                     this.mRouteControllerMap.put(r.mDescriptorId, controller);
                  }
               }
            }

            this.updatePlaybackInfoFromSelectedRoute();
         }

      }

      public MediaRouter.RouteInfo getSystemRouteByDescriptorId(String id) {
         int providerIndex = this.findProviderInfo(this.mSystemProvider);
         if (providerIndex >= 0) {
            MediaRouter.ProviderInfo provider = (MediaRouter.ProviderInfo)this.mProviders.get(providerIndex);
            int routeIndex = provider.findRouteByDescriptorId(id);
            if (routeIndex >= 0) {
               return (MediaRouter.RouteInfo)provider.mRoutes.get(routeIndex);
            }
         }

         return null;
      }

      public void addRemoteControlClient(Object rcc) {
         int index = this.findRemoteControlClientRecord(rcc);
         if (index < 0) {
            MediaRouter.GlobalMediaRouter.RemoteControlClientRecord record = new MediaRouter.GlobalMediaRouter.RemoteControlClientRecord(rcc);
            this.mRemoteControlClients.add(record);
         }

      }

      public void removeRemoteControlClient(Object rcc) {
         int index = this.findRemoteControlClientRecord(rcc);
         if (index >= 0) {
            MediaRouter.GlobalMediaRouter.RemoteControlClientRecord record = (MediaRouter.GlobalMediaRouter.RemoteControlClientRecord)this.mRemoteControlClients.remove(index);
            record.disconnect();
         }

      }

      public void setMediaSession(Object session) {
         this.setMediaSessionRecord(session != null ? new MediaRouter.GlobalMediaRouter.MediaSessionRecord(session) : null);
      }

      public void setMediaSessionCompat(MediaSessionCompat session) {
         this.mCompatSession = session;
         if (VERSION.SDK_INT >= 21) {
            this.setMediaSessionRecord(session != null ? new MediaRouter.GlobalMediaRouter.MediaSessionRecord(session) : null);
         } else if (VERSION.SDK_INT >= 14) {
            if (this.mRccMediaSession != null) {
               this.removeRemoteControlClient(this.mRccMediaSession.getRemoteControlClient());
               this.mRccMediaSession.removeOnActiveChangeListener(this.mSessionActiveListener);
            }

            this.mRccMediaSession = session;
            if (session != null) {
               session.addOnActiveChangeListener(this.mSessionActiveListener);
               if (session.isActive()) {
                  this.addRemoteControlClient(session.getRemoteControlClient());
               }
            }
         }

      }

      private void setMediaSessionRecord(MediaRouter.GlobalMediaRouter.MediaSessionRecord mediaSessionRecord) {
         if (this.mMediaSession != null) {
            this.mMediaSession.clearVolumeHandling();
         }

         this.mMediaSession = mediaSessionRecord;
         if (mediaSessionRecord != null) {
            this.updatePlaybackInfoFromSelectedRoute();
         }

      }

      public Token getMediaSessionToken() {
         if (this.mMediaSession != null) {
            return this.mMediaSession.getToken();
         } else {
            return this.mCompatSession != null ? this.mCompatSession.getSessionToken() : null;
         }
      }

      private int findRemoteControlClientRecord(Object rcc) {
         int count = this.mRemoteControlClients.size();

         for(int i = 0; i < count; ++i) {
            MediaRouter.GlobalMediaRouter.RemoteControlClientRecord record = (MediaRouter.GlobalMediaRouter.RemoteControlClientRecord)this.mRemoteControlClients.get(i);
            if (record.getRemoteControlClient() == rcc) {
               return i;
            }
         }

         return -1;
      }

      private void updatePlaybackInfoFromSelectedRoute() {
         if (this.mSelectedRoute != null) {
            this.mPlaybackInfo.volume = this.mSelectedRoute.getVolume();
            this.mPlaybackInfo.volumeMax = this.mSelectedRoute.getVolumeMax();
            this.mPlaybackInfo.volumeHandling = this.mSelectedRoute.getVolumeHandling();
            this.mPlaybackInfo.playbackStream = this.mSelectedRoute.getPlaybackStream();
            this.mPlaybackInfo.playbackType = this.mSelectedRoute.getPlaybackType();
            int count = this.mRemoteControlClients.size();

            for(int i = 0; i < count; ++i) {
               MediaRouter.GlobalMediaRouter.RemoteControlClientRecord record = (MediaRouter.GlobalMediaRouter.RemoteControlClientRecord)this.mRemoteControlClients.get(i);
               record.updatePlaybackInfo();
            }

            if (this.mMediaSession != null) {
               if (this.mSelectedRoute == this.getDefaultRoute()) {
                  this.mMediaSession.clearVolumeHandling();
               } else {
                  int controlType = 0;
                  if (this.mPlaybackInfo.volumeHandling == 1) {
                     controlType = 2;
                  }

                  this.mMediaSession.configureVolume(controlType, this.mPlaybackInfo.volumeMax, this.mPlaybackInfo.volume);
               }
            }
         } else if (this.mMediaSession != null) {
            this.mMediaSession.clearVolumeHandling();
         }

      }

      private final class CallbackHandler extends Handler {
         private final ArrayList mTempCallbackRecords = new ArrayList();
         private static final int MSG_TYPE_MASK = 65280;
         private static final int MSG_TYPE_ROUTE = 256;
         private static final int MSG_TYPE_PROVIDER = 512;
         public static final int MSG_ROUTE_ADDED = 257;
         public static final int MSG_ROUTE_REMOVED = 258;
         public static final int MSG_ROUTE_CHANGED = 259;
         public static final int MSG_ROUTE_VOLUME_CHANGED = 260;
         public static final int MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED = 261;
         public static final int MSG_ROUTE_SELECTED = 262;
         public static final int MSG_ROUTE_UNSELECTED = 263;
         public static final int MSG_PROVIDER_ADDED = 513;
         public static final int MSG_PROVIDER_REMOVED = 514;
         public static final int MSG_PROVIDER_CHANGED = 515;

         public void post(int msg, Object obj) {
            this.obtainMessage(msg, obj).sendToTarget();
         }

         public void post(int msg, Object obj, int arg) {
            Message message = this.obtainMessage(msg, obj);
            message.arg1 = arg;
            message.sendToTarget();
         }

         public void handleMessage(Message msg) {
            int what = msg.what;
            Object obj = msg.obj;
            int arg = msg.arg1;
            if (what == 259 && GlobalMediaRouter.this.getSelectedRoute().getId().equals(((MediaRouter.RouteInfo)obj).getId())) {
               GlobalMediaRouter.this.updateSelectedRouteIfNeeded(true);
            }

            this.syncWithSystemProvider(what, obj);

            try {
               int ix = GlobalMediaRouter.this.mRouters.size();

               while(true) {
                  --ix;
                  if (ix < 0) {
                     ix = this.mTempCallbackRecords.size();

                     for(int i = 0; i < ix; ++i) {
                        this.invokeCallback((MediaRouter.CallbackRecord)this.mTempCallbackRecords.get(i), what, obj, arg);
                     }
                     break;
                  }

                  MediaRouter router = (MediaRouter)((WeakReference)GlobalMediaRouter.this.mRouters.get(ix)).get();
                  if (router == null) {
                     GlobalMediaRouter.this.mRouters.remove(ix);
                  } else {
                     this.mTempCallbackRecords.addAll(router.mCallbackRecords);
                  }
               }
            } finally {
               this.mTempCallbackRecords.clear();
            }

         }

         private void syncWithSystemProvider(int what, Object obj) {
            switch(what) {
            case 257:
               GlobalMediaRouter.this.mSystemProvider.onSyncRouteAdded((MediaRouter.RouteInfo)obj);
               break;
            case 258:
               GlobalMediaRouter.this.mSystemProvider.onSyncRouteRemoved((MediaRouter.RouteInfo)obj);
               break;
            case 259:
               GlobalMediaRouter.this.mSystemProvider.onSyncRouteChanged((MediaRouter.RouteInfo)obj);
            case 260:
            case 261:
            default:
               break;
            case 262:
               GlobalMediaRouter.this.mSystemProvider.onSyncRouteSelected((MediaRouter.RouteInfo)obj);
            }

         }

         private void invokeCallback(MediaRouter.CallbackRecord record, int what, Object obj, int arg) {
            MediaRouter router = record.mRouter;
            MediaRouter.Callback callback = record.mCallback;
            switch(what & 65280) {
            case 256:
               MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)obj;
               if (record.filterRouteEvent(route)) {
                  switch(what) {
                  case 257:
                     callback.onRouteAdded(router, route);
                     break;
                  case 258:
                     callback.onRouteRemoved(router, route);
                     break;
                  case 259:
                     callback.onRouteChanged(router, route);
                     break;
                  case 260:
                     callback.onRouteVolumeChanged(router, route);
                     break;
                  case 261:
                     callback.onRoutePresentationDisplayChanged(router, route);
                     break;
                  case 262:
                     callback.onRouteSelected(router, route);
                     break;
                  case 263:
                     callback.onRouteUnselected(router, route, arg);
                  }
               }
               break;
            case 512:
               MediaRouter.ProviderInfo provider = (MediaRouter.ProviderInfo)obj;
               switch(what) {
               case 513:
                  callback.onProviderAdded(router, provider);
                  break;
               case 514:
                  callback.onProviderRemoved(router, provider);
                  break;
               case 515:
                  callback.onProviderChanged(router, provider);
               }
            }

         }
      }

      private final class RemoteControlClientRecord implements RemoteControlClientCompat.VolumeCallback {
         private final RemoteControlClientCompat mRccCompat;
         private boolean mDisconnected;

         public RemoteControlClientRecord(Object rcc) {
            this.mRccCompat = RemoteControlClientCompat.obtain(GlobalMediaRouter.this.mApplicationContext, rcc);
            this.mRccCompat.setVolumeCallback(this);
            this.updatePlaybackInfo();
         }

         public Object getRemoteControlClient() {
            return this.mRccCompat.getRemoteControlClient();
         }

         public void disconnect() {
            this.mDisconnected = true;
            this.mRccCompat.setVolumeCallback((RemoteControlClientCompat.VolumeCallback)null);
         }

         public void updatePlaybackInfo() {
            this.mRccCompat.setPlaybackInfo(GlobalMediaRouter.this.mPlaybackInfo);
         }

         public void onVolumeSetRequest(int volume) {
            if (!this.mDisconnected && GlobalMediaRouter.this.mSelectedRoute != null) {
               GlobalMediaRouter.this.mSelectedRoute.requestSetVolume(volume);
            }

         }

         public void onVolumeUpdateRequest(int direction) {
            if (!this.mDisconnected && GlobalMediaRouter.this.mSelectedRoute != null) {
               GlobalMediaRouter.this.mSelectedRoute.requestUpdateVolume(direction);
            }

         }
      }

      private final class MediaSessionRecord {
         private final MediaSessionCompat mMsCompat;
         private int mControlType;
         private int mMaxVolume;
         private VolumeProviderCompat mVpCompat;

         public MediaSessionRecord(Object mediaSession) {
            this.mMsCompat = MediaSessionCompat.fromMediaSession(GlobalMediaRouter.this.mApplicationContext, mediaSession);
         }

         public MediaSessionRecord(MediaSessionCompat mediaSessionCompat) {
            this.mMsCompat = mediaSessionCompat;
         }

         public void configureVolume(int controlType, int max, int current) {
            if (this.mVpCompat != null && controlType == this.mControlType && max == this.mMaxVolume) {
               this.mVpCompat.setCurrentVolume(current);
            } else {
               this.mVpCompat = new VolumeProviderCompat(controlType, max, current) {
                  public void onSetVolumeTo(final int volume) {
                     GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                           if (GlobalMediaRouter.this.mSelectedRoute != null) {
                              GlobalMediaRouter.this.mSelectedRoute.requestSetVolume(volume);
                           }

                        }
                     });
                  }

                  public void onAdjustVolume(final int direction) {
                     GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() {
                        public void run() {
                           if (GlobalMediaRouter.this.mSelectedRoute != null) {
                              GlobalMediaRouter.this.mSelectedRoute.requestUpdateVolume(direction);
                           }

                        }
                     });
                  }
               };
               this.mMsCompat.setPlaybackToRemote(this.mVpCompat);
            }

         }

         public void clearVolumeHandling() {
            this.mMsCompat.setPlaybackToLocal(GlobalMediaRouter.this.mPlaybackInfo.playbackStream);
            this.mVpCompat = null;
         }

         public Token getToken() {
            return this.mMsCompat.getSessionToken();
         }
      }

      private final class ProviderCallback extends MediaRouteProvider.Callback {
         public void onDescriptorChanged(MediaRouteProvider provider, MediaRouteProviderDescriptor descriptor) {
            GlobalMediaRouter.this.updateProviderDescriptor(provider, descriptor);
         }
      }
   }

   private static final class CallbackRecord {
      public final MediaRouter mRouter;
      public final MediaRouter.Callback mCallback;
      public MediaRouteSelector mSelector;
      public int mFlags;

      public CallbackRecord(MediaRouter router, MediaRouter.Callback callback) {
         this.mRouter = router;
         this.mCallback = callback;
         this.mSelector = MediaRouteSelector.EMPTY;
      }

      public boolean filterRouteEvent(MediaRouter.RouteInfo route) {
         return (this.mFlags & 2) != 0 || route.matchesSelector(this.mSelector);
      }
   }

   public abstract static class ControlRequestCallback {
      public void onResult(Bundle data) {
      }

      public void onError(String error, Bundle data) {
      }
   }

   public abstract static class Callback {
      public void onRouteSelected(MediaRouter router, MediaRouter.RouteInfo route) {
      }

      public void onRouteUnselected(MediaRouter router, MediaRouter.RouteInfo route) {
      }

      public void onRouteUnselected(MediaRouter router, MediaRouter.RouteInfo route, int reason) {
         this.onRouteUnselected(router, route);
      }

      public void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo route) {
      }

      public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo route) {
      }

      public void onRouteChanged(MediaRouter router, MediaRouter.RouteInfo route) {
      }

      public void onRouteVolumeChanged(MediaRouter router, MediaRouter.RouteInfo route) {
      }

      public void onRoutePresentationDisplayChanged(MediaRouter router, MediaRouter.RouteInfo route) {
      }

      public void onProviderAdded(MediaRouter router, MediaRouter.ProviderInfo provider) {
      }

      public void onProviderRemoved(MediaRouter router, MediaRouter.ProviderInfo provider) {
      }

      public void onProviderChanged(MediaRouter router, MediaRouter.ProviderInfo provider) {
      }
   }

   public static final class ProviderInfo {
      private final MediaRouteProvider mProviderInstance;
      private final List mRoutes = new ArrayList();
      private final MediaRouteProvider.ProviderMetadata mMetadata;
      private MediaRouteProviderDescriptor mDescriptor;
      private Resources mResources;
      private boolean mResourcesNotAvailable;

      ProviderInfo(MediaRouteProvider provider) {
         this.mProviderInstance = provider;
         this.mMetadata = provider.getMetadata();
      }

      public MediaRouteProvider getProviderInstance() {
         MediaRouter.checkCallingThread();
         return this.mProviderInstance;
      }

      public String getPackageName() {
         return this.mMetadata.getPackageName();
      }

      public ComponentName getComponentName() {
         return this.mMetadata.getComponentName();
      }

      public List getRoutes() {
         MediaRouter.checkCallingThread();
         return this.mRoutes;
      }

      Resources getResources() {
         if (this.mResources == null && !this.mResourcesNotAvailable) {
            String packageName = this.getPackageName();
            Context context = MediaRouter.sGlobal.getProviderContext(packageName);
            if (context != null) {
               this.mResources = context.getResources();
            } else {
               Log.w("MediaRouter", "Unable to obtain resources for route provider package: " + packageName);
               this.mResourcesNotAvailable = true;
            }
         }

         return this.mResources;
      }

      boolean updateDescriptor(MediaRouteProviderDescriptor descriptor) {
         if (this.mDescriptor != descriptor) {
            this.mDescriptor = descriptor;
            return true;
         } else {
            return false;
         }
      }

      int findRouteByDescriptorId(String id) {
         int count = this.mRoutes.size();

         for(int i = 0; i < count; ++i) {
            if (((MediaRouter.RouteInfo)this.mRoutes.get(i)).mDescriptorId.equals(id)) {
               return i;
            }
         }

         return -1;
      }

      public String toString() {
         return "MediaRouter.RouteProviderInfo{ packageName=" + this.getPackageName() + " }";
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static class RouteGroup extends MediaRouter.RouteInfo {
      private List mRoutes = new ArrayList();

      RouteGroup(MediaRouter.ProviderInfo provider, String descriptorId, String uniqueId) {
         super(provider, descriptorId, uniqueId);
      }

      public int getRouteCount() {
         return this.mRoutes.size();
      }

      public MediaRouter.RouteInfo getRouteAt(int index) {
         return (MediaRouter.RouteInfo)this.mRoutes.get(index);
      }

      public List getRoutes() {
         return this.mRoutes;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder(super.toString());
         sb.append('[');
         int count = this.mRoutes.size();

         for(int i = 0; i < count; ++i) {
            if (i > 0) {
               sb.append(", ");
            }

            sb.append(this.mRoutes.get(i));
         }

         sb.append(']');
         return sb.toString();
      }

      int maybeUpdateDescriptor(MediaRouteDescriptor descriptor) {
         boolean changed = false;
         if (this.mDescriptor != descriptor) {
            this.mDescriptor = descriptor;
            if (descriptor != null) {
               List groupMemberIds = descriptor.getGroupMemberIds();
               List routes = new ArrayList();
               changed = groupMemberIds.size() != this.mRoutes.size();
               Iterator var5 = groupMemberIds.iterator();

               while(var5.hasNext()) {
                  String groupMemberId = (String)var5.next();
                  String uniqueId = MediaRouter.sGlobal.getUniqueId(this.getProvider(), groupMemberId);
                  MediaRouter.RouteInfo groupMember = MediaRouter.sGlobal.getRoute(uniqueId);
                  if (groupMember != null) {
                     routes.add(groupMember);
                     if (!changed && !this.mRoutes.contains(groupMember)) {
                        changed = true;
                     }
                  }
               }

               if (changed) {
                  this.mRoutes = routes;
               }
            }
         }

         return (changed ? 1 : 0) | super.updateDescriptor(descriptor);
      }
   }

   public static class RouteInfo {
      private final MediaRouter.ProviderInfo mProvider;
      private final String mDescriptorId;
      private final String mUniqueId;
      private String mName;
      private String mDescription;
      private Uri mIconUri;
      private boolean mEnabled;
      private boolean mConnecting;
      private int mConnectionState;
      private boolean mCanDisconnect;
      private final ArrayList mControlFilters = new ArrayList();
      private int mPlaybackType;
      private int mPlaybackStream;
      private int mDeviceType;
      private int mVolumeHandling;
      private int mVolume;
      private int mVolumeMax;
      private Display mPresentationDisplay;
      private int mPresentationDisplayId = -1;
      private Bundle mExtras;
      private IntentSender mSettingsIntent;
      MediaRouteDescriptor mDescriptor;
      public static final int CONNECTION_STATE_DISCONNECTED = 0;
      public static final int CONNECTION_STATE_CONNECTING = 1;
      public static final int CONNECTION_STATE_CONNECTED = 2;
      public static final int PLAYBACK_TYPE_LOCAL = 0;
      public static final int PLAYBACK_TYPE_REMOTE = 1;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public static final int DEVICE_TYPE_UNKNOWN = 0;
      public static final int DEVICE_TYPE_TV = 1;
      public static final int DEVICE_TYPE_SPEAKER = 2;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public static final int DEVICE_TYPE_BLUETOOTH = 3;
      public static final int PLAYBACK_VOLUME_FIXED = 0;
      public static final int PLAYBACK_VOLUME_VARIABLE = 1;
      @RestrictTo({Scope.LIBRARY_GROUP})
      public static final int PRESENTATION_DISPLAY_ID_NONE = -1;
      static final int CHANGE_GENERAL = 1;
      static final int CHANGE_VOLUME = 2;
      static final int CHANGE_PRESENTATION_DISPLAY = 4;
      static final String SYSTEM_MEDIA_ROUTE_PROVIDER_PACKAGE_NAME = "android";

      RouteInfo(MediaRouter.ProviderInfo provider, String descriptorId, String uniqueId) {
         this.mProvider = provider;
         this.mDescriptorId = descriptorId;
         this.mUniqueId = uniqueId;
      }

      public MediaRouter.ProviderInfo getProvider() {
         return this.mProvider;
      }

      @NonNull
      public String getId() {
         return this.mUniqueId;
      }

      public String getName() {
         return this.mName;
      }

      @Nullable
      public String getDescription() {
         return this.mDescription;
      }

      public Uri getIconUri() {
         return this.mIconUri;
      }

      public boolean isEnabled() {
         return this.mEnabled;
      }

      public boolean isConnecting() {
         return this.mConnecting;
      }

      public int getConnectionState() {
         return this.mConnectionState;
      }

      public boolean isSelected() {
         MediaRouter.checkCallingThread();
         return MediaRouter.sGlobal.getSelectedRoute() == this;
      }

      public boolean isDefault() {
         MediaRouter.checkCallingThread();
         return MediaRouter.sGlobal.getDefaultRoute() == this;
      }

      public boolean isBluetooth() {
         MediaRouter.checkCallingThread();
         return MediaRouter.sGlobal.getBluetoothRoute() == this;
      }

      public boolean isDeviceSpeaker() {
         int defaultAudioRouteNameResourceId = Resources.getSystem().getIdentifier("default_audio_route_name", "string", "android");
         return this.isDefault() && Resources.getSystem().getText(defaultAudioRouteNameResourceId).equals(this.mName);
      }

      public List getControlFilters() {
         return this.mControlFilters;
      }

      public boolean matchesSelector(@NonNull MediaRouteSelector selector) {
         if (selector == null) {
            throw new IllegalArgumentException("selector must not be null");
         } else {
            MediaRouter.checkCallingThread();
            return selector.matchesControlFilters(this.mControlFilters);
         }
      }

      public boolean supportsControlCategory(@NonNull String category) {
         if (category == null) {
            throw new IllegalArgumentException("category must not be null");
         } else {
            MediaRouter.checkCallingThread();
            int count = this.mControlFilters.size();

            for(int i = 0; i < count; ++i) {
               if (((IntentFilter)this.mControlFilters.get(i)).hasCategory(category)) {
                  return true;
               }
            }

            return false;
         }
      }

      public boolean supportsControlAction(@NonNull String category, @NonNull String action) {
         if (category == null) {
            throw new IllegalArgumentException("category must not be null");
         } else if (action == null) {
            throw new IllegalArgumentException("action must not be null");
         } else {
            MediaRouter.checkCallingThread();
            int count = this.mControlFilters.size();

            for(int i = 0; i < count; ++i) {
               IntentFilter filter = (IntentFilter)this.mControlFilters.get(i);
               if (filter.hasCategory(category) && filter.hasAction(action)) {
                  return true;
               }
            }

            return false;
         }
      }

      public boolean supportsControlRequest(@NonNull Intent intent) {
         if (intent == null) {
            throw new IllegalArgumentException("intent must not be null");
         } else {
            MediaRouter.checkCallingThread();
            ContentResolver contentResolver = MediaRouter.sGlobal.getContentResolver();
            int count = this.mControlFilters.size();

            for(int i = 0; i < count; ++i) {
               if (((IntentFilter)this.mControlFilters.get(i)).match(contentResolver, intent, true, "MediaRouter") >= 0) {
                  return true;
               }
            }

            return false;
         }
      }

      public void sendControlRequest(@NonNull Intent intent, @Nullable MediaRouter.ControlRequestCallback callback) {
         if (intent == null) {
            throw new IllegalArgumentException("intent must not be null");
         } else {
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.sendControlRequest(this, intent, callback);
         }
      }

      public int getPlaybackType() {
         return this.mPlaybackType;
      }

      public int getPlaybackStream() {
         return this.mPlaybackStream;
      }

      public int getDeviceType() {
         return this.mDeviceType;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public boolean isDefaultOrBluetooth() {
         if (!this.isDefault() && this.mDeviceType != 3) {
            return isSystemMediaRouteProvider(this) && this.supportsControlCategory("android.media.intent.category.LIVE_AUDIO") && !this.supportsControlCategory("android.media.intent.category.LIVE_VIDEO");
         } else {
            return true;
         }
      }

      private static boolean isSystemMediaRouteProvider(MediaRouter.RouteInfo route) {
         return TextUtils.equals(route.getProviderInstance().getMetadata().getPackageName(), "android");
      }

      public int getVolumeHandling() {
         return this.mVolumeHandling;
      }

      public int getVolume() {
         return this.mVolume;
      }

      public int getVolumeMax() {
         return this.mVolumeMax;
      }

      public boolean canDisconnect() {
         return this.mCanDisconnect;
      }

      public void requestSetVolume(int volume) {
         MediaRouter.checkCallingThread();
         MediaRouter.sGlobal.requestSetVolume(this, Math.min(this.mVolumeMax, Math.max(0, volume)));
      }

      public void requestUpdateVolume(int delta) {
         MediaRouter.checkCallingThread();
         if (delta != 0) {
            MediaRouter.sGlobal.requestUpdateVolume(this, delta);
         }

      }

      @Nullable
      public Display getPresentationDisplay() {
         MediaRouter.checkCallingThread();
         if (this.mPresentationDisplayId >= 0 && this.mPresentationDisplay == null) {
            this.mPresentationDisplay = MediaRouter.sGlobal.getDisplay(this.mPresentationDisplayId);
         }

         return this.mPresentationDisplay;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public int getPresentationDisplayId() {
         return this.mPresentationDisplayId;
      }

      @Nullable
      public Bundle getExtras() {
         return this.mExtras;
      }

      @Nullable
      public IntentSender getSettingsIntent() {
         return this.mSettingsIntent;
      }

      public void select() {
         MediaRouter.checkCallingThread();
         MediaRouter.sGlobal.selectRoute(this);
      }

      public String toString() {
         return "MediaRouter.RouteInfo{ uniqueId=" + this.mUniqueId + ", name=" + this.mName + ", description=" + this.mDescription + ", iconUri=" + this.mIconUri + ", enabled=" + this.mEnabled + ", connecting=" + this.mConnecting + ", connectionState=" + this.mConnectionState + ", canDisconnect=" + this.mCanDisconnect + ", playbackType=" + this.mPlaybackType + ", playbackStream=" + this.mPlaybackStream + ", deviceType=" + this.mDeviceType + ", volumeHandling=" + this.mVolumeHandling + ", volume=" + this.mVolume + ", volumeMax=" + this.mVolumeMax + ", presentationDisplayId=" + this.mPresentationDisplayId + ", extras=" + this.mExtras + ", settingsIntent=" + this.mSettingsIntent + ", providerPackageName=" + this.mProvider.getPackageName() + " }";
      }

      int maybeUpdateDescriptor(MediaRouteDescriptor descriptor) {
         int changes = 0;
         if (this.mDescriptor != descriptor) {
            changes = this.updateDescriptor(descriptor);
         }

         return changes;
      }

      int updateDescriptor(MediaRouteDescriptor descriptor) {
         int changes = 0;
         this.mDescriptor = descriptor;
         if (descriptor != null) {
            if (!MediaRouter.equal(this.mName, descriptor.getName())) {
               this.mName = descriptor.getName();
               changes |= 1;
            }

            if (!MediaRouter.equal(this.mDescription, descriptor.getDescription())) {
               this.mDescription = descriptor.getDescription();
               changes |= 1;
            }

            if (!MediaRouter.equal(this.mIconUri, descriptor.getIconUri())) {
               this.mIconUri = descriptor.getIconUri();
               changes |= 1;
            }

            if (this.mEnabled != descriptor.isEnabled()) {
               this.mEnabled = descriptor.isEnabled();
               changes |= 1;
            }

            if (this.mConnecting != descriptor.isConnecting()) {
               this.mConnecting = descriptor.isConnecting();
               changes |= 1;
            }

            if (this.mConnectionState != descriptor.getConnectionState()) {
               this.mConnectionState = descriptor.getConnectionState();
               changes |= 1;
            }

            if (!this.mControlFilters.equals(descriptor.getControlFilters())) {
               this.mControlFilters.clear();
               this.mControlFilters.addAll(descriptor.getControlFilters());
               changes |= 1;
            }

            if (this.mPlaybackType != descriptor.getPlaybackType()) {
               this.mPlaybackType = descriptor.getPlaybackType();
               changes |= 1;
            }

            if (this.mPlaybackStream != descriptor.getPlaybackStream()) {
               this.mPlaybackStream = descriptor.getPlaybackStream();
               changes |= 1;
            }

            if (this.mDeviceType != descriptor.getDeviceType()) {
               this.mDeviceType = descriptor.getDeviceType();
               changes |= 1;
            }

            if (this.mVolumeHandling != descriptor.getVolumeHandling()) {
               this.mVolumeHandling = descriptor.getVolumeHandling();
               changes |= 3;
            }

            if (this.mVolume != descriptor.getVolume()) {
               this.mVolume = descriptor.getVolume();
               changes |= 3;
            }

            if (this.mVolumeMax != descriptor.getVolumeMax()) {
               this.mVolumeMax = descriptor.getVolumeMax();
               changes |= 3;
            }

            if (this.mPresentationDisplayId != descriptor.getPresentationDisplayId()) {
               this.mPresentationDisplayId = descriptor.getPresentationDisplayId();
               this.mPresentationDisplay = null;
               changes |= 5;
            }

            if (!MediaRouter.equal(this.mExtras, descriptor.getExtras())) {
               this.mExtras = descriptor.getExtras();
               changes |= 1;
            }

            if (!MediaRouter.equal(this.mSettingsIntent, descriptor.getSettingsActivity())) {
               this.mSettingsIntent = descriptor.getSettingsActivity();
               changes |= 1;
            }

            if (this.mCanDisconnect != descriptor.canDisconnectAndKeepPlaying()) {
               this.mCanDisconnect = descriptor.canDisconnectAndKeepPlaying();
               changes |= 5;
            }
         }

         return changes;
      }

      String getDescriptorId() {
         return this.mDescriptorId;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public MediaRouteProvider getProviderInstance() {
         return this.mProvider.getProviderInstance();
      }
   }
}
