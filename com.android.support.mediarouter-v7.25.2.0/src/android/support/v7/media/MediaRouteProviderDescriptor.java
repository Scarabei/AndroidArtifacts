package android.support.v7.media;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class MediaRouteProviderDescriptor {
   static final String KEY_ROUTES = "routes";
   final Bundle mBundle;
   List mRoutes;

   MediaRouteProviderDescriptor(Bundle bundle, List routes) {
      this.mBundle = bundle;
      this.mRoutes = routes;
   }

   public List getRoutes() {
      this.ensureRoutes();
      return this.mRoutes;
   }

   void ensureRoutes() {
      if (this.mRoutes == null) {
         ArrayList routeBundles = this.mBundle.getParcelableArrayList("routes");
         if (routeBundles != null && !routeBundles.isEmpty()) {
            int count = routeBundles.size();
            this.mRoutes = new ArrayList(count);

            for(int i = 0; i < count; ++i) {
               this.mRoutes.add(MediaRouteDescriptor.fromBundle((Bundle)routeBundles.get(i)));
            }
         } else {
            this.mRoutes = Collections.emptyList();
         }
      }

   }

   public boolean isValid() {
      this.ensureRoutes();
      int routeCount = this.mRoutes.size();

      for(int i = 0; i < routeCount; ++i) {
         MediaRouteDescriptor route = (MediaRouteDescriptor)this.mRoutes.get(i);
         if (route == null || !route.isValid()) {
            return false;
         }
      }

      return true;
   }

   public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("MediaRouteProviderDescriptor{ ");
      result.append("routes=").append(Arrays.toString(this.getRoutes().toArray()));
      result.append(", isValid=").append(this.isValid());
      result.append(" }");
      return result.toString();
   }

   public Bundle asBundle() {
      return this.mBundle;
   }

   public static MediaRouteProviderDescriptor fromBundle(Bundle bundle) {
      return bundle != null ? new MediaRouteProviderDescriptor(bundle, (List)null) : null;
   }

   public static final class Builder {
      private final Bundle mBundle;
      private ArrayList mRoutes;

      public Builder() {
         this.mBundle = new Bundle();
      }

      public Builder(MediaRouteProviderDescriptor descriptor) {
         if (descriptor == null) {
            throw new IllegalArgumentException("descriptor must not be null");
         } else {
            this.mBundle = new Bundle(descriptor.mBundle);
            descriptor.ensureRoutes();
            if (!descriptor.mRoutes.isEmpty()) {
               this.mRoutes = new ArrayList(descriptor.mRoutes);
            }

         }
      }

      public MediaRouteProviderDescriptor.Builder addRoute(MediaRouteDescriptor route) {
         if (route == null) {
            throw new IllegalArgumentException("route must not be null");
         } else {
            if (this.mRoutes == null) {
               this.mRoutes = new ArrayList();
            } else if (this.mRoutes.contains(route)) {
               throw new IllegalArgumentException("route descriptor already added");
            }

            this.mRoutes.add(route);
            return this;
         }
      }

      public MediaRouteProviderDescriptor.Builder addRoutes(Collection routes) {
         if (routes == null) {
            throw new IllegalArgumentException("routes must not be null");
         } else {
            if (!routes.isEmpty()) {
               Iterator var2 = routes.iterator();

               while(var2.hasNext()) {
                  MediaRouteDescriptor route = (MediaRouteDescriptor)var2.next();
                  this.addRoute(route);
               }
            }

            return this;
         }
      }

      public MediaRouteProviderDescriptor build() {
         if (this.mRoutes != null) {
            int count = this.mRoutes.size();
            ArrayList routeBundles = new ArrayList(count);

            for(int i = 0; i < count; ++i) {
               routeBundles.add(((MediaRouteDescriptor)this.mRoutes.get(i)).asBundle());
            }

            this.mBundle.putParcelableArrayList("routes", routeBundles);
         }

         return new MediaRouteProviderDescriptor(this.mBundle, this.mRoutes);
      }
   }
}
