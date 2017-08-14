package android.support.v7.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;

public class MediaRouteDiscoveryFragment extends Fragment {
   private final String ARGUMENT_SELECTOR = "selector";
   private MediaRouter mRouter;
   private MediaRouteSelector mSelector;
   private MediaRouter.Callback mCallback;

   public MediaRouter getMediaRouter() {
      this.ensureRouter();
      return this.mRouter;
   }

   private void ensureRouter() {
      if (this.mRouter == null) {
         this.mRouter = MediaRouter.getInstance(this.getContext());
      }

   }

   public MediaRouteSelector getRouteSelector() {
      this.ensureRouteSelector();
      return this.mSelector;
   }

   public void setRouteSelector(MediaRouteSelector selector) {
      if (selector == null) {
         throw new IllegalArgumentException("selector must not be null");
      } else {
         this.ensureRouteSelector();
         if (!this.mSelector.equals(selector)) {
            this.mSelector = selector;
            Bundle args = this.getArguments();
            if (args == null) {
               args = new Bundle();
            }

            args.putBundle("selector", selector.asBundle());
            this.setArguments(args);
            if (this.mCallback != null) {
               this.mRouter.removeCallback(this.mCallback);
               this.mRouter.addCallback(this.mSelector, this.mCallback, this.onPrepareCallbackFlags());
            }
         }

      }
   }

   private void ensureRouteSelector() {
      if (this.mSelector == null) {
         Bundle args = this.getArguments();
         if (args != null) {
            this.mSelector = MediaRouteSelector.fromBundle(args.getBundle("selector"));
         }

         if (this.mSelector == null) {
            this.mSelector = MediaRouteSelector.EMPTY;
         }
      }

   }

   public MediaRouter.Callback onCreateCallback() {
      return new MediaRouter.Callback() {
      };
   }

   public int onPrepareCallbackFlags() {
      return 4;
   }

   public void onStart() {
      super.onStart();
      this.ensureRouteSelector();
      this.ensureRouter();
      this.mCallback = this.onCreateCallback();
      if (this.mCallback != null) {
         this.mRouter.addCallback(this.mSelector, this.mCallback, this.onPrepareCallbackFlags());
      }

   }

   public void onStop() {
      if (this.mCallback != null) {
         this.mRouter.removeCallback(this.mCallback);
         this.mCallback = null;
      }

      super.onStop();
   }
}
