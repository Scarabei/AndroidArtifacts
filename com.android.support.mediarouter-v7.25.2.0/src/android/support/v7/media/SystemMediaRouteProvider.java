package android.support.v7.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.support.v7.mediarouter.R.string;
import android.view.Display;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

abstract class SystemMediaRouteProvider extends MediaRouteProvider {
   private static final String TAG = "SystemMediaRouteProvider";
   public static final String PACKAGE_NAME = "android";
   public static final String DEFAULT_ROUTE_ID = "DEFAULT_ROUTE";

   protected SystemMediaRouteProvider(Context context) {
      super(context, new MediaRouteProvider.ProviderMetadata(new ComponentName("android", SystemMediaRouteProvider.class.getName())));
   }

   public static SystemMediaRouteProvider obtain(Context context, SystemMediaRouteProvider.SyncCallback syncCallback) {
      if (VERSION.SDK_INT >= 24) {
         return new SystemMediaRouteProvider.Api24Impl(context, syncCallback);
      } else if (VERSION.SDK_INT >= 18) {
         return new SystemMediaRouteProvider.JellybeanMr2Impl(context, syncCallback);
      } else if (VERSION.SDK_INT >= 17) {
         return new SystemMediaRouteProvider.JellybeanMr1Impl(context, syncCallback);
      } else {
         return (SystemMediaRouteProvider)(VERSION.SDK_INT >= 16 ? new SystemMediaRouteProvider.JellybeanImpl(context, syncCallback) : new SystemMediaRouteProvider.LegacyImpl(context));
      }
   }

   public void onSyncRouteAdded(MediaRouter.RouteInfo route) {
   }

   public void onSyncRouteRemoved(MediaRouter.RouteInfo route) {
   }

   public void onSyncRouteChanged(MediaRouter.RouteInfo route) {
   }

   public void onSyncRouteSelected(MediaRouter.RouteInfo route) {
   }

   protected Object getDefaultRoute() {
      return null;
   }

   protected Object getSystemRoute(MediaRouter.RouteInfo route) {
      return null;
   }

   private static class Api24Impl extends SystemMediaRouteProvider.JellybeanMr2Impl {
      public Api24Impl(Context context, SystemMediaRouteProvider.SyncCallback syncCallback) {
         super(context, syncCallback);
      }

      protected void onBuildSystemRouteDescriptor(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record, MediaRouteDescriptor.Builder builder) {
         super.onBuildSystemRouteDescriptor(record, builder);
         builder.setDeviceType(MediaRouterApi24.RouteInfo.getDeviceType(record.mRouteObj));
      }
   }

   private static class JellybeanMr2Impl extends SystemMediaRouteProvider.JellybeanMr1Impl {
      public JellybeanMr2Impl(Context context, SystemMediaRouteProvider.SyncCallback syncCallback) {
         super(context, syncCallback);
      }

      protected void onBuildSystemRouteDescriptor(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record, MediaRouteDescriptor.Builder builder) {
         super.onBuildSystemRouteDescriptor(record, builder);
         CharSequence description = MediaRouterJellybeanMr2.RouteInfo.getDescription(record.mRouteObj);
         if (description != null) {
            builder.setDescription(description.toString());
         }

      }

      protected void selectRoute(Object routeObj) {
         MediaRouterJellybean.selectRoute(this.mRouterObj, 8388611, routeObj);
      }

      protected Object getDefaultRoute() {
         return MediaRouterJellybeanMr2.getDefaultRoute(this.mRouterObj);
      }

      protected void updateUserRouteProperties(SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord record) {
         super.updateUserRouteProperties(record);
         MediaRouterJellybeanMr2.UserRouteInfo.setDescription(record.mRouteObj, record.mRoute.getDescription());
      }

      protected void updateCallback() {
         if (this.mCallbackRegistered) {
            MediaRouterJellybean.removeCallback(this.mRouterObj, this.mCallbackObj);
         }

         this.mCallbackRegistered = true;
         MediaRouterJellybeanMr2.addCallback(this.mRouterObj, this.mRouteTypes, this.mCallbackObj, 2 | (this.mActiveScan ? 1 : 0));
      }

      protected boolean isConnecting(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record) {
         return MediaRouterJellybeanMr2.RouteInfo.isConnecting(record.mRouteObj);
      }
   }

   private static class JellybeanMr1Impl extends SystemMediaRouteProvider.JellybeanImpl implements MediaRouterJellybeanMr1.Callback {
      private MediaRouterJellybeanMr1.ActiveScanWorkaround mActiveScanWorkaround;
      private MediaRouterJellybeanMr1.IsConnectingWorkaround mIsConnectingWorkaround;

      public JellybeanMr1Impl(Context context, SystemMediaRouteProvider.SyncCallback syncCallback) {
         super(context, syncCallback);
      }

      public void onRoutePresentationDisplayChanged(Object routeObj) {
         int index = this.findSystemRouteRecord(routeObj);
         if (index >= 0) {
            SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(index);
            Display newPresentationDisplay = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(routeObj);
            int newPresentationDisplayId = newPresentationDisplay != null ? newPresentationDisplay.getDisplayId() : -1;
            if (newPresentationDisplayId != record.mRouteDescriptor.getPresentationDisplayId()) {
               record.mRouteDescriptor = (new MediaRouteDescriptor.Builder(record.mRouteDescriptor)).setPresentationDisplayId(newPresentationDisplayId).build();
               this.publishRoutes();
            }
         }

      }

      protected void onBuildSystemRouteDescriptor(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record, MediaRouteDescriptor.Builder builder) {
         super.onBuildSystemRouteDescriptor(record, builder);
         if (!MediaRouterJellybeanMr1.RouteInfo.isEnabled(record.mRouteObj)) {
            builder.setEnabled(false);
         }

         if (this.isConnecting(record)) {
            builder.setConnecting(true);
         }

         Display presentationDisplay = MediaRouterJellybeanMr1.RouteInfo.getPresentationDisplay(record.mRouteObj);
         if (presentationDisplay != null) {
            builder.setPresentationDisplayId(presentationDisplay.getDisplayId());
         }

      }

      protected void updateCallback() {
         super.updateCallback();
         if (this.mActiveScanWorkaround == null) {
            this.mActiveScanWorkaround = new MediaRouterJellybeanMr1.ActiveScanWorkaround(this.getContext(), this.getHandler());
         }

         this.mActiveScanWorkaround.setActiveScanRouteTypes(this.mActiveScan ? this.mRouteTypes : 0);
      }

      protected Object createCallbackObj() {
         return MediaRouterJellybeanMr1.createCallback(this);
      }

      protected boolean isConnecting(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record) {
         if (this.mIsConnectingWorkaround == null) {
            this.mIsConnectingWorkaround = new MediaRouterJellybeanMr1.IsConnectingWorkaround();
         }

         return this.mIsConnectingWorkaround.isConnecting(record.mRouteObj);
      }
   }

   static class JellybeanImpl extends SystemMediaRouteProvider implements MediaRouterJellybean.Callback, MediaRouterJellybean.VolumeCallback {
      private static final ArrayList LIVE_AUDIO_CONTROL_FILTERS;
      private static final ArrayList LIVE_VIDEO_CONTROL_FILTERS;
      private final SystemMediaRouteProvider.SyncCallback mSyncCallback;
      protected final Object mRouterObj;
      protected final Object mCallbackObj;
      protected final Object mVolumeCallbackObj;
      protected final Object mUserRouteCategoryObj;
      protected int mRouteTypes;
      protected boolean mActiveScan;
      protected boolean mCallbackRegistered;
      protected final ArrayList mSystemRouteRecords = new ArrayList();
      protected final ArrayList mUserRouteRecords = new ArrayList();
      private MediaRouterJellybean.SelectRouteWorkaround mSelectRouteWorkaround;
      private MediaRouterJellybean.GetDefaultRouteWorkaround mGetDefaultRouteWorkaround;

      public JellybeanImpl(Context context, SystemMediaRouteProvider.SyncCallback syncCallback) {
         super(context);
         this.mSyncCallback = syncCallback;
         this.mRouterObj = MediaRouterJellybean.getMediaRouter(context);
         this.mCallbackObj = this.createCallbackObj();
         this.mVolumeCallbackObj = this.createVolumeCallbackObj();
         Resources r = context.getResources();
         this.mUserRouteCategoryObj = MediaRouterJellybean.createRouteCategory(this.mRouterObj, r.getString(string.mr_user_route_category_name), false);
         this.updateSystemRoutes();
      }

      public MediaRouteProvider.RouteController onCreateRouteController(String routeId) {
         int index = this.findSystemRouteRecordByDescriptorId(routeId);
         if (index >= 0) {
            SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(index);
            return new SystemMediaRouteProvider.JellybeanImpl.SystemRouteController(record.mRouteObj);
         } else {
            return null;
         }
      }

      public void onDiscoveryRequestChanged(MediaRouteDiscoveryRequest request) {
         int newRouteTypes = 0;
         boolean newActiveScan = false;
         if (request != null) {
            MediaRouteSelector selector = request.getSelector();
            List categories = selector.getControlCategories();
            int count = categories.size();

            for(int i = 0; i < count; ++i) {
               String category = (String)categories.get(i);
               if (category.equals("android.media.intent.category.LIVE_AUDIO")) {
                  newRouteTypes |= 1;
               } else if (category.equals("android.media.intent.category.LIVE_VIDEO")) {
                  newRouteTypes |= 2;
               } else {
                  newRouteTypes |= 8388608;
               }
            }

            newActiveScan = request.isActiveScan();
         }

         if (this.mRouteTypes != newRouteTypes || this.mActiveScan != newActiveScan) {
            this.mRouteTypes = newRouteTypes;
            this.mActiveScan = newActiveScan;
            this.updateSystemRoutes();
         }

      }

      public void onRouteAdded(Object routeObj) {
         if (this.addSystemRouteNoPublish(routeObj)) {
            this.publishRoutes();
         }

      }

      private void updateSystemRoutes() {
         this.updateCallback();
         boolean changed = false;

         Object routeObj;
         for(Iterator var2 = MediaRouterJellybean.getRoutes(this.mRouterObj).iterator(); var2.hasNext(); changed |= this.addSystemRouteNoPublish(routeObj)) {
            routeObj = var2.next();
         }

         if (changed) {
            this.publishRoutes();
         }

      }

      private boolean addSystemRouteNoPublish(Object routeObj) {
         if (this.getUserRouteRecord(routeObj) == null && this.findSystemRouteRecord(routeObj) < 0) {
            String id = this.assignRouteId(routeObj);
            SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record = new SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord(routeObj, id);
            this.updateSystemRouteDescriptor(record);
            this.mSystemRouteRecords.add(record);
            return true;
         } else {
            return false;
         }
      }

      private String assignRouteId(Object routeObj) {
         boolean isDefault = this.getDefaultRoute() == routeObj;
         String id = isDefault ? "DEFAULT_ROUTE" : String.format(Locale.US, "ROUTE_%08x", this.getRouteName(routeObj).hashCode());
         if (this.findSystemRouteRecordByDescriptorId(id) < 0) {
            return id;
         } else {
            int i = 2;

            while(true) {
               String newId = String.format(Locale.US, "%s_%d", id, i);
               if (this.findSystemRouteRecordByDescriptorId(newId) < 0) {
                  return newId;
               }

               ++i;
            }
         }
      }

      public void onRouteRemoved(Object routeObj) {
         if (this.getUserRouteRecord(routeObj) == null) {
            int index = this.findSystemRouteRecord(routeObj);
            if (index >= 0) {
               this.mSystemRouteRecords.remove(index);
               this.publishRoutes();
            }
         }

      }

      public void onRouteChanged(Object routeObj) {
         if (this.getUserRouteRecord(routeObj) == null) {
            int index = this.findSystemRouteRecord(routeObj);
            if (index >= 0) {
               SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(index);
               this.updateSystemRouteDescriptor(record);
               this.publishRoutes();
            }
         }

      }

      public void onRouteVolumeChanged(Object routeObj) {
         if (this.getUserRouteRecord(routeObj) == null) {
            int index = this.findSystemRouteRecord(routeObj);
            if (index >= 0) {
               SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(index);
               int newVolume = MediaRouterJellybean.RouteInfo.getVolume(routeObj);
               if (newVolume != record.mRouteDescriptor.getVolume()) {
                  record.mRouteDescriptor = (new MediaRouteDescriptor.Builder(record.mRouteDescriptor)).setVolume(newVolume).build();
                  this.publishRoutes();
               }
            }
         }

      }

      public void onRouteSelected(int type, Object routeObj) {
         if (routeObj == MediaRouterJellybean.getSelectedRoute(this.mRouterObj, 8388611)) {
            SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord userRouteRecord = this.getUserRouteRecord(routeObj);
            if (userRouteRecord != null) {
               userRouteRecord.mRoute.select();
            } else {
               int index = this.findSystemRouteRecord(routeObj);
               if (index >= 0) {
                  SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(index);
                  MediaRouter.RouteInfo route = this.mSyncCallback.getSystemRouteByDescriptorId(record.mRouteDescriptorId);
                  if (route != null) {
                     route.select();
                  }
               }
            }

         }
      }

      public void onRouteUnselected(int type, Object routeObj) {
      }

      public void onRouteGrouped(Object routeObj, Object groupObj, int index) {
      }

      public void onRouteUngrouped(Object routeObj, Object groupObj) {
      }

      public void onVolumeSetRequest(Object routeObj, int volume) {
         SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord record = this.getUserRouteRecord(routeObj);
         if (record != null) {
            record.mRoute.requestSetVolume(volume);
         }

      }

      public void onVolumeUpdateRequest(Object routeObj, int direction) {
         SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord record = this.getUserRouteRecord(routeObj);
         if (record != null) {
            record.mRoute.requestUpdateVolume(direction);
         }

      }

      public void onSyncRouteAdded(MediaRouter.RouteInfo route) {
         Object routeObj;
         if (route.getProviderInstance() != this) {
            routeObj = MediaRouterJellybean.createUserRoute(this.mRouterObj, this.mUserRouteCategoryObj);
            SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord record = new SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord(route, routeObj);
            MediaRouterJellybean.RouteInfo.setTag(routeObj, record);
            MediaRouterJellybean.UserRouteInfo.setVolumeCallback(routeObj, this.mVolumeCallbackObj);
            this.updateUserRouteProperties(record);
            this.mUserRouteRecords.add(record);
            MediaRouterJellybean.addUserRoute(this.mRouterObj, routeObj);
         } else {
            routeObj = MediaRouterJellybean.getSelectedRoute(this.mRouterObj, 8388611);
            int index = this.findSystemRouteRecord(routeObj);
            if (index >= 0) {
               SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(index);
               if (record.mRouteDescriptorId.equals(route.getDescriptorId())) {
                  route.select();
               }
            }
         }

      }

      public void onSyncRouteRemoved(MediaRouter.RouteInfo route) {
         if (route.getProviderInstance() != this) {
            int index = this.findUserRouteRecord(route);
            if (index >= 0) {
               SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord)this.mUserRouteRecords.remove(index);
               MediaRouterJellybean.RouteInfo.setTag(record.mRouteObj, (Object)null);
               MediaRouterJellybean.UserRouteInfo.setVolumeCallback(record.mRouteObj, (Object)null);
               MediaRouterJellybean.removeUserRoute(this.mRouterObj, record.mRouteObj);
            }
         }

      }

      public void onSyncRouteChanged(MediaRouter.RouteInfo route) {
         if (route.getProviderInstance() != this) {
            int index = this.findUserRouteRecord(route);
            if (index >= 0) {
               SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord)this.mUserRouteRecords.get(index);
               this.updateUserRouteProperties(record);
            }
         }

      }

      public void onSyncRouteSelected(MediaRouter.RouteInfo route) {
         if (route.isSelected()) {
            int index;
            if (route.getProviderInstance() != this) {
               index = this.findUserRouteRecord(route);
               if (index >= 0) {
                  SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord)this.mUserRouteRecords.get(index);
                  this.selectRoute(record.mRouteObj);
               }
            } else {
               index = this.findSystemRouteRecordByDescriptorId(route.getDescriptorId());
               if (index >= 0) {
                  SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record = (SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(index);
                  this.selectRoute(record.mRouteObj);
               }
            }

         }
      }

      protected void publishRoutes() {
         MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder();
         int count = this.mSystemRouteRecords.size();

         for(int i = 0; i < count; ++i) {
            builder.addRoute(((SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(i)).mRouteDescriptor);
         }

         this.setDescriptor(builder.build());
      }

      protected int findSystemRouteRecord(Object routeObj) {
         int count = this.mSystemRouteRecords.size();

         for(int i = 0; i < count; ++i) {
            if (((SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(i)).mRouteObj == routeObj) {
               return i;
            }
         }

         return -1;
      }

      protected int findSystemRouteRecordByDescriptorId(String id) {
         int count = this.mSystemRouteRecords.size();

         for(int i = 0; i < count; ++i) {
            if (((SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(i)).mRouteDescriptorId.equals(id)) {
               return i;
            }
         }

         return -1;
      }

      protected int findUserRouteRecord(MediaRouter.RouteInfo route) {
         int count = this.mUserRouteRecords.size();

         for(int i = 0; i < count; ++i) {
            if (((SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord)this.mUserRouteRecords.get(i)).mRoute == route) {
               return i;
            }
         }

         return -1;
      }

      protected SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord getUserRouteRecord(Object routeObj) {
         Object tag = MediaRouterJellybean.RouteInfo.getTag(routeObj);
         return tag instanceof SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord ? (SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord)tag : null;
      }

      protected void updateSystemRouteDescriptor(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record) {
         MediaRouteDescriptor.Builder builder = new MediaRouteDescriptor.Builder(record.mRouteDescriptorId, this.getRouteName(record.mRouteObj));
         this.onBuildSystemRouteDescriptor(record, builder);
         record.mRouteDescriptor = builder.build();
      }

      protected String getRouteName(Object routeObj) {
         CharSequence name = MediaRouterJellybean.RouteInfo.getName(routeObj, this.getContext());
         return name != null ? name.toString() : "";
      }

      protected void onBuildSystemRouteDescriptor(SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord record, MediaRouteDescriptor.Builder builder) {
         int supportedTypes = MediaRouterJellybean.RouteInfo.getSupportedTypes(record.mRouteObj);
         if ((supportedTypes & 1) != 0) {
            builder.addControlFilters(LIVE_AUDIO_CONTROL_FILTERS);
         }

         if ((supportedTypes & 2) != 0) {
            builder.addControlFilters(LIVE_VIDEO_CONTROL_FILTERS);
         }

         builder.setPlaybackType(MediaRouterJellybean.RouteInfo.getPlaybackType(record.mRouteObj));
         builder.setPlaybackStream(MediaRouterJellybean.RouteInfo.getPlaybackStream(record.mRouteObj));
         builder.setVolume(MediaRouterJellybean.RouteInfo.getVolume(record.mRouteObj));
         builder.setVolumeMax(MediaRouterJellybean.RouteInfo.getVolumeMax(record.mRouteObj));
         builder.setVolumeHandling(MediaRouterJellybean.RouteInfo.getVolumeHandling(record.mRouteObj));
      }

      protected void updateUserRouteProperties(SystemMediaRouteProvider.JellybeanImpl.UserRouteRecord record) {
         MediaRouterJellybean.UserRouteInfo.setName(record.mRouteObj, record.mRoute.getName());
         MediaRouterJellybean.UserRouteInfo.setPlaybackType(record.mRouteObj, record.mRoute.getPlaybackType());
         MediaRouterJellybean.UserRouteInfo.setPlaybackStream(record.mRouteObj, record.mRoute.getPlaybackStream());
         MediaRouterJellybean.UserRouteInfo.setVolume(record.mRouteObj, record.mRoute.getVolume());
         MediaRouterJellybean.UserRouteInfo.setVolumeMax(record.mRouteObj, record.mRoute.getVolumeMax());
         MediaRouterJellybean.UserRouteInfo.setVolumeHandling(record.mRouteObj, record.mRoute.getVolumeHandling());
      }

      protected void updateCallback() {
         if (this.mCallbackRegistered) {
            this.mCallbackRegistered = false;
            MediaRouterJellybean.removeCallback(this.mRouterObj, this.mCallbackObj);
         }

         if (this.mRouteTypes != 0) {
            this.mCallbackRegistered = true;
            MediaRouterJellybean.addCallback(this.mRouterObj, this.mRouteTypes, this.mCallbackObj);
         }

      }

      protected Object createCallbackObj() {
         return MediaRouterJellybean.createCallback(this);
      }

      protected Object createVolumeCallbackObj() {
         return MediaRouterJellybean.createVolumeCallback(this);
      }

      protected void selectRoute(Object routeObj) {
         if (this.mSelectRouteWorkaround == null) {
            this.mSelectRouteWorkaround = new MediaRouterJellybean.SelectRouteWorkaround();
         }

         this.mSelectRouteWorkaround.selectRoute(this.mRouterObj, 8388611, routeObj);
      }

      protected Object getDefaultRoute() {
         if (this.mGetDefaultRouteWorkaround == null) {
            this.mGetDefaultRouteWorkaround = new MediaRouterJellybean.GetDefaultRouteWorkaround();
         }

         return this.mGetDefaultRouteWorkaround.getDefaultRoute(this.mRouterObj);
      }

      protected Object getSystemRoute(MediaRouter.RouteInfo route) {
         if (route == null) {
            return null;
         } else {
            int index = this.findSystemRouteRecordByDescriptorId(route.getDescriptorId());
            return index >= 0 ? ((SystemMediaRouteProvider.JellybeanImpl.SystemRouteRecord)this.mSystemRouteRecords.get(index)).mRouteObj : null;
         }
      }

      static {
         IntentFilter f = new IntentFilter();
         f.addCategory("android.media.intent.category.LIVE_AUDIO");
         LIVE_AUDIO_CONTROL_FILTERS = new ArrayList();
         LIVE_AUDIO_CONTROL_FILTERS.add(f);
         f = new IntentFilter();
         f.addCategory("android.media.intent.category.LIVE_VIDEO");
         LIVE_VIDEO_CONTROL_FILTERS = new ArrayList();
         LIVE_VIDEO_CONTROL_FILTERS.add(f);
      }

      protected final class SystemRouteController extends MediaRouteProvider.RouteController {
         private final Object mRouteObj;

         public SystemRouteController(Object routeObj) {
            this.mRouteObj = routeObj;
         }

         public void onSetVolume(int volume) {
            MediaRouterJellybean.RouteInfo.requestSetVolume(this.mRouteObj, volume);
         }

         public void onUpdateVolume(int delta) {
            MediaRouterJellybean.RouteInfo.requestUpdateVolume(this.mRouteObj, delta);
         }
      }

      protected static final class UserRouteRecord {
         public final MediaRouter.RouteInfo mRoute;
         public final Object mRouteObj;

         public UserRouteRecord(MediaRouter.RouteInfo route, Object routeObj) {
            this.mRoute = route;
            this.mRouteObj = routeObj;
         }
      }

      protected static final class SystemRouteRecord {
         public final Object mRouteObj;
         public final String mRouteDescriptorId;
         public MediaRouteDescriptor mRouteDescriptor;

         public SystemRouteRecord(Object routeObj, String id) {
            this.mRouteObj = routeObj;
            this.mRouteDescriptorId = id;
         }
      }
   }

   static class LegacyImpl extends SystemMediaRouteProvider {
      static final int PLAYBACK_STREAM = 3;
      private static final ArrayList CONTROL_FILTERS;
      final AudioManager mAudioManager;
      private final SystemMediaRouteProvider.LegacyImpl.VolumeChangeReceiver mVolumeChangeReceiver;
      int mLastReportedVolume = -1;

      public LegacyImpl(Context context) {
         super(context);
         this.mAudioManager = (AudioManager)context.getSystemService("audio");
         this.mVolumeChangeReceiver = new SystemMediaRouteProvider.LegacyImpl.VolumeChangeReceiver();
         context.registerReceiver(this.mVolumeChangeReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
         this.publishRoutes();
      }

      void publishRoutes() {
         Resources r = this.getContext().getResources();
         int maxVolume = this.mAudioManager.getStreamMaxVolume(3);
         this.mLastReportedVolume = this.mAudioManager.getStreamVolume(3);
         MediaRouteDescriptor defaultRoute = (new MediaRouteDescriptor.Builder("DEFAULT_ROUTE", r.getString(string.mr_system_route_name))).addControlFilters(CONTROL_FILTERS).setPlaybackStream(3).setPlaybackType(0).setVolumeHandling(1).setVolumeMax(maxVolume).setVolume(this.mLastReportedVolume).build();
         MediaRouteProviderDescriptor providerDescriptor = (new MediaRouteProviderDescriptor.Builder()).addRoute(defaultRoute).build();
         this.setDescriptor(providerDescriptor);
      }

      public MediaRouteProvider.RouteController onCreateRouteController(String routeId) {
         return routeId.equals("DEFAULT_ROUTE") ? new SystemMediaRouteProvider.LegacyImpl.DefaultRouteController() : null;
      }

      static {
         IntentFilter f = new IntentFilter();
         f.addCategory("android.media.intent.category.LIVE_AUDIO");
         f.addCategory("android.media.intent.category.LIVE_VIDEO");
         CONTROL_FILTERS = new ArrayList();
         CONTROL_FILTERS.add(f);
      }

      final class VolumeChangeReceiver extends BroadcastReceiver {
         public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
         public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
         public static final String EXTRA_VOLUME_STREAM_VALUE = "android.media.EXTRA_VOLUME_STREAM_VALUE";

         public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {
               int streamType = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
               if (streamType == 3) {
                  int volume = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1);
                  if (volume >= 0 && volume != LegacyImpl.this.mLastReportedVolume) {
                     LegacyImpl.this.publishRoutes();
                  }
               }
            }

         }
      }

      final class DefaultRouteController extends MediaRouteProvider.RouteController {
         public void onSetVolume(int volume) {
            LegacyImpl.this.mAudioManager.setStreamVolume(3, volume, 0);
            LegacyImpl.this.publishRoutes();
         }

         public void onUpdateVolume(int delta) {
            int volume = LegacyImpl.this.mAudioManager.getStreamVolume(3);
            int maxVolume = LegacyImpl.this.mAudioManager.getStreamMaxVolume(3);
            int newVolume = Math.min(maxVolume, Math.max(0, volume + delta));
            if (newVolume != volume) {
               LegacyImpl.this.mAudioManager.setStreamVolume(3, volume, 0);
            }

            LegacyImpl.this.publishRoutes();
         }
      }
   }

   public interface SyncCallback {
      MediaRouter.RouteInfo getSystemRouteByDescriptorId(String var1);
   }
}
