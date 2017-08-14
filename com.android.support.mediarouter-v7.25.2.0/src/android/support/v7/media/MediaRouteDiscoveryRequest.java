package android.support.v7.media;

import android.os.Bundle;

public final class MediaRouteDiscoveryRequest {
   private static final String KEY_SELECTOR = "selector";
   private static final String KEY_ACTIVE_SCAN = "activeScan";
   private final Bundle mBundle;
   private MediaRouteSelector mSelector;

   public MediaRouteDiscoveryRequest(MediaRouteSelector selector, boolean activeScan) {
      if (selector == null) {
         throw new IllegalArgumentException("selector must not be null");
      } else {
         this.mBundle = new Bundle();
         this.mSelector = selector;
         this.mBundle.putBundle("selector", selector.asBundle());
         this.mBundle.putBoolean("activeScan", activeScan);
      }
   }

   private MediaRouteDiscoveryRequest(Bundle bundle) {
      this.mBundle = bundle;
   }

   public MediaRouteSelector getSelector() {
      this.ensureSelector();
      return this.mSelector;
   }

   private void ensureSelector() {
      if (this.mSelector == null) {
         this.mSelector = MediaRouteSelector.fromBundle(this.mBundle.getBundle("selector"));
         if (this.mSelector == null) {
            this.mSelector = MediaRouteSelector.EMPTY;
         }
      }

   }

   public boolean isActiveScan() {
      return this.mBundle.getBoolean("activeScan");
   }

   public boolean isValid() {
      this.ensureSelector();
      return this.mSelector.isValid();
   }

   public boolean equals(Object o) {
      if (!(o instanceof MediaRouteDiscoveryRequest)) {
         return false;
      } else {
         MediaRouteDiscoveryRequest other = (MediaRouteDiscoveryRequest)o;
         return this.getSelector().equals(other.getSelector()) && this.isActiveScan() == other.isActiveScan();
      }
   }

   public int hashCode() {
      return this.getSelector().hashCode() ^ (this.isActiveScan() ? 1 : 0);
   }

   public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("DiscoveryRequest{ selector=").append(this.getSelector());
      result.append(", activeScan=").append(this.isActiveScan());
      result.append(", isValid=").append(this.isValid());
      result.append(" }");
      return result.toString();
   }

   public Bundle asBundle() {
      return this.mBundle;
   }

   public static MediaRouteDiscoveryRequest fromBundle(Bundle bundle) {
      return bundle != null ? new MediaRouteDiscoveryRequest(bundle) : null;
   }
}
