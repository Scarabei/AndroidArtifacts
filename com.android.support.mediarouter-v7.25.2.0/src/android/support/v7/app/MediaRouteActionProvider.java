package android.support.v7.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ActionProvider;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.lang.ref.WeakReference;

public class MediaRouteActionProvider extends ActionProvider {
   private static final String TAG = "MediaRouteActionProvider";
   private final MediaRouter mRouter;
   private final MediaRouteActionProvider.MediaRouterCallback mCallback;
   private MediaRouteSelector mSelector;
   private MediaRouteDialogFactory mDialogFactory;
   private MediaRouteButton mButton;

   public MediaRouteActionProvider(Context context) {
      super(context);
      this.mSelector = MediaRouteSelector.EMPTY;
      this.mDialogFactory = MediaRouteDialogFactory.getDefault();
      this.mRouter = MediaRouter.getInstance(context);
      this.mCallback = new MediaRouteActionProvider.MediaRouterCallback(this);
   }

   @NonNull
   public MediaRouteSelector getRouteSelector() {
      return this.mSelector;
   }

   public void setRouteSelector(@NonNull MediaRouteSelector selector) {
      if (selector == null) {
         throw new IllegalArgumentException("selector must not be null");
      } else {
         if (!this.mSelector.equals(selector)) {
            if (!this.mSelector.isEmpty()) {
               this.mRouter.removeCallback(this.mCallback);
            }

            if (!selector.isEmpty()) {
               this.mRouter.addCallback(selector, this.mCallback);
            }

            this.mSelector = selector;
            this.refreshRoute();
            if (this.mButton != null) {
               this.mButton.setRouteSelector(selector);
            }
         }

      }
   }

   @NonNull
   public MediaRouteDialogFactory getDialogFactory() {
      return this.mDialogFactory;
   }

   public void setDialogFactory(@NonNull MediaRouteDialogFactory factory) {
      if (factory == null) {
         throw new IllegalArgumentException("factory must not be null");
      } else {
         if (this.mDialogFactory != factory) {
            this.mDialogFactory = factory;
            if (this.mButton != null) {
               this.mButton.setDialogFactory(factory);
            }
         }

      }
   }

   @Nullable
   public MediaRouteButton getMediaRouteButton() {
      return this.mButton;
   }

   public MediaRouteButton onCreateMediaRouteButton() {
      return new MediaRouteButton(this.getContext());
   }

   public View onCreateActionView() {
      if (this.mButton != null) {
         Log.e("MediaRouteActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item...");
      }

      this.mButton = this.onCreateMediaRouteButton();
      this.mButton.setCheatSheetEnabled(true);
      this.mButton.setRouteSelector(this.mSelector);
      this.mButton.setDialogFactory(this.mDialogFactory);
      this.mButton.setLayoutParams(new LayoutParams(-2, -1));
      return this.mButton;
   }

   public boolean onPerformDefaultAction() {
      return this.mButton != null ? this.mButton.showDialog() : false;
   }

   public boolean overridesItemVisibility() {
      return true;
   }

   public boolean isVisible() {
      return this.mRouter.isRouteAvailable(this.mSelector, 1);
   }

   void refreshRoute() {
      this.refreshVisibility();
   }

   private static final class MediaRouterCallback extends MediaRouter.Callback {
      private final WeakReference mProviderWeak;

      public MediaRouterCallback(MediaRouteActionProvider provider) {
         this.mProviderWeak = new WeakReference(provider);
      }

      public void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo info) {
         this.refreshRoute(router);
      }

      public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo info) {
         this.refreshRoute(router);
      }

      public void onRouteChanged(MediaRouter router, MediaRouter.RouteInfo info) {
         this.refreshRoute(router);
      }

      public void onProviderAdded(MediaRouter router, MediaRouter.ProviderInfo provider) {
         this.refreshRoute(router);
      }

      public void onProviderRemoved(MediaRouter router, MediaRouter.ProviderInfo provider) {
         this.refreshRoute(router);
      }

      public void onProviderChanged(MediaRouter router, MediaRouter.ProviderInfo provider) {
         this.refreshRoute(router);
      }

      private void refreshRoute(MediaRouter router) {
         MediaRouteActionProvider provider = (MediaRouteActionProvider)this.mProviderWeak.get();
         if (provider != null) {
            provider.refreshRoute();
         } else {
            router.removeCallback(this);
         }

      }
   }
}
