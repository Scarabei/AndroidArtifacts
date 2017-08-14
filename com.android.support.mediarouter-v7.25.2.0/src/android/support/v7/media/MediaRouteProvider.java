package android.support.v7.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

public abstract class MediaRouteProvider {
   static final int MSG_DELIVER_DESCRIPTOR_CHANGED = 1;
   static final int MSG_DELIVER_DISCOVERY_REQUEST_CHANGED = 2;
   private final Context mContext;
   private final MediaRouteProvider.ProviderMetadata mMetadata;
   private final MediaRouteProvider.ProviderHandler mHandler;
   private MediaRouteProvider.Callback mCallback;
   private MediaRouteDiscoveryRequest mDiscoveryRequest;
   private boolean mPendingDiscoveryRequestChange;
   private MediaRouteProviderDescriptor mDescriptor;
   private boolean mPendingDescriptorChange;

   public MediaRouteProvider(@NonNull Context context) {
      this(context, (MediaRouteProvider.ProviderMetadata)null);
   }

   MediaRouteProvider(Context context, MediaRouteProvider.ProviderMetadata metadata) {
      this.mHandler = new MediaRouteProvider.ProviderHandler();
      if (context == null) {
         throw new IllegalArgumentException("context must not be null");
      } else {
         this.mContext = context;
         if (metadata == null) {
            this.mMetadata = new MediaRouteProvider.ProviderMetadata(new ComponentName(context, this.getClass()));
         } else {
            this.mMetadata = metadata;
         }

      }
   }

   public final Context getContext() {
      return this.mContext;
   }

   public final Handler getHandler() {
      return this.mHandler;
   }

   public final MediaRouteProvider.ProviderMetadata getMetadata() {
      return this.mMetadata;
   }

   public final void setCallback(@Nullable MediaRouteProvider.Callback callback) {
      MediaRouter.checkCallingThread();
      this.mCallback = callback;
   }

   @Nullable
   public final MediaRouteDiscoveryRequest getDiscoveryRequest() {
      return this.mDiscoveryRequest;
   }

   public final void setDiscoveryRequest(MediaRouteDiscoveryRequest request) {
      MediaRouter.checkCallingThread();
      if (this.mDiscoveryRequest != request && (this.mDiscoveryRequest == null || !this.mDiscoveryRequest.equals(request))) {
         this.mDiscoveryRequest = request;
         if (!this.mPendingDiscoveryRequestChange) {
            this.mPendingDiscoveryRequestChange = true;
            this.mHandler.sendEmptyMessage(2);
         }

      }
   }

   void deliverDiscoveryRequestChanged() {
      this.mPendingDiscoveryRequestChange = false;
      this.onDiscoveryRequestChanged(this.mDiscoveryRequest);
   }

   public void onDiscoveryRequestChanged(@Nullable MediaRouteDiscoveryRequest request) {
   }

   @Nullable
   public final MediaRouteProviderDescriptor getDescriptor() {
      return this.mDescriptor;
   }

   public final void setDescriptor(@Nullable MediaRouteProviderDescriptor descriptor) {
      MediaRouter.checkCallingThread();
      if (this.mDescriptor != descriptor) {
         this.mDescriptor = descriptor;
         if (!this.mPendingDescriptorChange) {
            this.mPendingDescriptorChange = true;
            this.mHandler.sendEmptyMessage(1);
         }
      }

   }

   void deliverDescriptorChanged() {
      this.mPendingDescriptorChange = false;
      if (this.mCallback != null) {
         this.mCallback.onDescriptorChanged(this, this.mDescriptor);
      }

   }

   @Nullable
   public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String routeId) {
      if (routeId == null) {
         throw new IllegalArgumentException("routeId cannot be null");
      } else {
         return null;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @Nullable
   public MediaRouteProvider.RouteController onCreateRouteController(@NonNull String routeId, @NonNull String routeGroupId) {
      if (routeId == null) {
         throw new IllegalArgumentException("routeId cannot be null");
      } else if (routeGroupId == null) {
         throw new IllegalArgumentException("routeGroupId cannot be null");
      } else {
         return this.onCreateRouteController(routeId);
      }
   }

   private final class ProviderHandler extends Handler {
      public void handleMessage(Message msg) {
         switch(msg.what) {
         case 1:
            MediaRouteProvider.this.deliverDescriptorChanged();
            break;
         case 2:
            MediaRouteProvider.this.deliverDiscoveryRequestChanged();
         }

      }
   }

   public abstract static class Callback {
      public void onDescriptorChanged(@NonNull MediaRouteProvider provider, @Nullable MediaRouteProviderDescriptor descriptor) {
      }
   }

   public abstract static class RouteController {
      public void onRelease() {
      }

      public void onSelect() {
      }

      public void onUnselect() {
      }

      public void onUnselect(int reason) {
         this.onUnselect();
      }

      public void onSetVolume(int volume) {
      }

      public void onUpdateVolume(int delta) {
      }

      public boolean onControlRequest(Intent intent, @Nullable MediaRouter.ControlRequestCallback callback) {
         return false;
      }
   }

   public static final class ProviderMetadata {
      private final ComponentName mComponentName;

      ProviderMetadata(ComponentName componentName) {
         if (componentName == null) {
            throw new IllegalArgumentException("componentName must not be null");
         } else {
            this.mComponentName = componentName;
         }
      }

      public String getPackageName() {
         return this.mComponentName.getPackageName();
      }

      public ComponentName getComponentName() {
         return this.mComponentName;
      }

      public String toString() {
         return "ProviderMetadata{ componentName=" + this.mComponentName.flattenToShortString() + " }";
      }
   }
}
