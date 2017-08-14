package android.support.v7.media;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class MediaRouteSelector {
   static final String KEY_CONTROL_CATEGORIES = "controlCategories";
   private final Bundle mBundle;
   List mControlCategories;
   public static final MediaRouteSelector EMPTY = new MediaRouteSelector(new Bundle(), (List)null);

   MediaRouteSelector(Bundle bundle, List controlCategories) {
      this.mBundle = bundle;
      this.mControlCategories = controlCategories;
   }

   public List getControlCategories() {
      this.ensureControlCategories();
      return this.mControlCategories;
   }

   void ensureControlCategories() {
      if (this.mControlCategories == null) {
         this.mControlCategories = this.mBundle.getStringArrayList("controlCategories");
         if (this.mControlCategories == null || this.mControlCategories.isEmpty()) {
            this.mControlCategories = Collections.emptyList();
         }
      }

   }

   public boolean hasControlCategory(String category) {
      if (category != null) {
         this.ensureControlCategories();
         int categoryCount = this.mControlCategories.size();

         for(int i = 0; i < categoryCount; ++i) {
            if (((String)this.mControlCategories.get(i)).equals(category)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean matchesControlFilters(List filters) {
      if (filters != null) {
         this.ensureControlCategories();
         int categoryCount = this.mControlCategories.size();
         if (categoryCount != 0) {
            int filterCount = filters.size();

            for(int i = 0; i < filterCount; ++i) {
               IntentFilter filter = (IntentFilter)filters.get(i);
               if (filter != null) {
                  for(int j = 0; j < categoryCount; ++j) {
                     if (filter.hasCategory((String)this.mControlCategories.get(j))) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   public boolean contains(MediaRouteSelector selector) {
      if (selector != null) {
         this.ensureControlCategories();
         selector.ensureControlCategories();
         return this.mControlCategories.containsAll(selector.mControlCategories);
      } else {
         return false;
      }
   }

   public boolean isEmpty() {
      this.ensureControlCategories();
      return this.mControlCategories.isEmpty();
   }

   public boolean isValid() {
      this.ensureControlCategories();
      return !this.mControlCategories.contains((Object)null);
   }

   public boolean equals(Object o) {
      if (o instanceof MediaRouteSelector) {
         MediaRouteSelector other = (MediaRouteSelector)o;
         this.ensureControlCategories();
         other.ensureControlCategories();
         return this.mControlCategories.equals(other.mControlCategories);
      } else {
         return false;
      }
   }

   public int hashCode() {
      this.ensureControlCategories();
      return this.mControlCategories.hashCode();
   }

   public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("MediaRouteSelector{ ");
      result.append("controlCategories=").append(Arrays.toString(this.getControlCategories().toArray()));
      result.append(" }");
      return result.toString();
   }

   public Bundle asBundle() {
      return this.mBundle;
   }

   public static MediaRouteSelector fromBundle(@Nullable Bundle bundle) {
      return bundle != null ? new MediaRouteSelector(bundle, (List)null) : null;
   }

   public static final class Builder {
      private ArrayList mControlCategories;

      public Builder() {
      }

      public Builder(@NonNull MediaRouteSelector selector) {
         if (selector == null) {
            throw new IllegalArgumentException("selector must not be null");
         } else {
            selector.ensureControlCategories();
            if (!selector.mControlCategories.isEmpty()) {
               this.mControlCategories = new ArrayList(selector.mControlCategories);
            }

         }
      }

      @NonNull
      public MediaRouteSelector.Builder addControlCategory(@NonNull String category) {
         if (category == null) {
            throw new IllegalArgumentException("category must not be null");
         } else {
            if (this.mControlCategories == null) {
               this.mControlCategories = new ArrayList();
            }

            if (!this.mControlCategories.contains(category)) {
               this.mControlCategories.add(category);
            }

            return this;
         }
      }

      @NonNull
      public MediaRouteSelector.Builder addControlCategories(@NonNull Collection categories) {
         if (categories == null) {
            throw new IllegalArgumentException("categories must not be null");
         } else {
            if (!categories.isEmpty()) {
               Iterator var2 = categories.iterator();

               while(var2.hasNext()) {
                  String category = (String)var2.next();
                  this.addControlCategory(category);
               }
            }

            return this;
         }
      }

      @NonNull
      public MediaRouteSelector.Builder addSelector(@NonNull MediaRouteSelector selector) {
         if (selector == null) {
            throw new IllegalArgumentException("selector must not be null");
         } else {
            this.addControlCategories(selector.getControlCategories());
            return this;
         }
      }

      @NonNull
      public MediaRouteSelector build() {
         if (this.mControlCategories == null) {
            return MediaRouteSelector.EMPTY;
         } else {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("controlCategories", this.mControlCategories);
            return new MediaRouteSelector(bundle, this.mControlCategories);
         }
      }
   }
}
