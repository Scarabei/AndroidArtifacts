package android.support.v7.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;

public final class AppCompatResources {
   private static final String LOG_TAG = "AppCompatResources";
   private static final ThreadLocal TL_TYPED_VALUE = new ThreadLocal();
   private static final WeakHashMap sColorStateCaches = new WeakHashMap(0);
   private static final Object sColorStateCacheLock = new Object();

   public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int resId) {
      if (VERSION.SDK_INT >= 23) {
         return context.getColorStateList(resId);
      } else {
         ColorStateList csl = getCachedColorStateList(context, resId);
         if (csl != null) {
            return csl;
         } else {
            csl = inflateColorStateList(context, resId);
            if (csl != null) {
               addColorStateListToCache(context, resId, csl);
               return csl;
            } else {
               return ContextCompat.getColorStateList(context, resId);
            }
         }
      }
   }

   @Nullable
   public static Drawable getDrawable(@NonNull Context context, @DrawableRes int resId) {
      return AppCompatDrawableManager.get().getDrawable(context, resId);
   }

   @Nullable
   private static ColorStateList inflateColorStateList(Context context, int resId) {
      if (isColorInt(context, resId)) {
         return null;
      } else {
         Resources r = context.getResources();
         XmlResourceParser xml = r.getXml(resId);

         try {
            return AppCompatColorStateListInflater.createFromXml(r, xml, context.getTheme());
         } catch (Exception var5) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", var5);
            return null;
         }
      }
   }

   @Nullable
   private static ColorStateList getCachedColorStateList(@NonNull Context context, @ColorRes int resId) {
      Object var2 = sColorStateCacheLock;
      synchronized(sColorStateCacheLock) {
         SparseArray entries = (SparseArray)sColorStateCaches.get(context);
         if (entries != null && entries.size() > 0) {
            AppCompatResources.ColorStateListCacheEntry entry = (AppCompatResources.ColorStateListCacheEntry)entries.get(resId);
            if (entry != null) {
               if (entry.configuration.equals(context.getResources().getConfiguration())) {
                  return entry.value;
               }

               entries.remove(resId);
            }
         }

         return null;
      }
   }

   private static void addColorStateListToCache(@NonNull Context context, @ColorRes int resId, @NonNull ColorStateList value) {
      Object var3 = sColorStateCacheLock;
      synchronized(sColorStateCacheLock) {
         SparseArray entries = (SparseArray)sColorStateCaches.get(context);
         if (entries == null) {
            entries = new SparseArray();
            sColorStateCaches.put(context, entries);
         }

         entries.append(resId, new AppCompatResources.ColorStateListCacheEntry(value, context.getResources().getConfiguration()));
      }
   }

   private static boolean isColorInt(@NonNull Context context, @ColorRes int resId) {
      Resources r = context.getResources();
      TypedValue value = getTypedValue();
      r.getValue(resId, value, true);
      return value.type >= 28 && value.type <= 31;
   }

   @NonNull
   private static TypedValue getTypedValue() {
      TypedValue tv = (TypedValue)TL_TYPED_VALUE.get();
      if (tv == null) {
         tv = new TypedValue();
         TL_TYPED_VALUE.set(tv);
      }

      return tv;
   }

   private static class ColorStateListCacheEntry {
      final ColorStateList value;
      final Configuration configuration;

      ColorStateListCacheEntry(@NonNull ColorStateList value, @NonNull Configuration configuration) {
         this.value = value;
         this.configuration = configuration;
      }
   }
}
