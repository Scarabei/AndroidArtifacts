package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher {
   private static final String TAG = "ResourcesFlusher";
   private static Field sDrawableCacheField;
   private static boolean sDrawableCacheFieldFetched;
   private static Class sThemedResourceCacheClazz;
   private static boolean sThemedResourceCacheClazzFetched;
   private static Field sThemedResourceCache_mUnthemedEntriesField;
   private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;
   private static Field sResourcesImplField;
   private static boolean sResourcesImplFieldFetched;

   static boolean flush(@NonNull Resources resources) {
      if (VERSION.SDK_INT >= 24) {
         return flushNougats(resources);
      } else if (VERSION.SDK_INT >= 23) {
         return flushMarshmallows(resources);
      } else {
         return VERSION.SDK_INT >= 21 ? flushLollipops(resources) : false;
      }
   }

   @RequiresApi(21)
   private static boolean flushLollipops(@NonNull Resources resources) {
      if (!sDrawableCacheFieldFetched) {
         try {
            sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
            sDrawableCacheField.setAccessible(true);
         } catch (NoSuchFieldException var4) {
            Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", var4);
         }

         sDrawableCacheFieldFetched = true;
      }

      if (sDrawableCacheField != null) {
         Map drawableCache = null;

         try {
            drawableCache = (Map)sDrawableCacheField.get(resources);
         } catch (IllegalAccessException var3) {
            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", var3);
         }

         if (drawableCache != null) {
            drawableCache.clear();
            return true;
         }
      }

      return false;
   }

   @RequiresApi(23)
   private static boolean flushMarshmallows(@NonNull Resources resources) {
      if (!sDrawableCacheFieldFetched) {
         try {
            sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
            sDrawableCacheField.setAccessible(true);
         } catch (NoSuchFieldException var4) {
            Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", var4);
         }

         sDrawableCacheFieldFetched = true;
      }

      Object drawableCache = null;
      if (sDrawableCacheField != null) {
         try {
            drawableCache = sDrawableCacheField.get(resources);
         } catch (IllegalAccessException var3) {
            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", var3);
         }
      }

      if (drawableCache == null) {
         return false;
      } else {
         return drawableCache != null && flushThemedResourcesCache(drawableCache);
      }
   }

   @RequiresApi(24)
   private static boolean flushNougats(@NonNull Resources resources) {
      if (!sResourcesImplFieldFetched) {
         try {
            sResourcesImplField = Resources.class.getDeclaredField("mResourcesImpl");
            sResourcesImplField.setAccessible(true);
         } catch (NoSuchFieldException var7) {
            Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", var7);
         }

         sResourcesImplFieldFetched = true;
      }

      if (sResourcesImplField == null) {
         return false;
      } else {
         Object resourcesImpl = null;

         try {
            resourcesImpl = sResourcesImplField.get(resources);
         } catch (IllegalAccessException var6) {
            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", var6);
         }

         if (resourcesImpl == null) {
            return false;
         } else {
            if (!sDrawableCacheFieldFetched) {
               try {
                  sDrawableCacheField = resourcesImpl.getClass().getDeclaredField("mDrawableCache");
                  sDrawableCacheField.setAccessible(true);
               } catch (NoSuchFieldException var5) {
                  Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", var5);
               }

               sDrawableCacheFieldFetched = true;
            }

            Object drawableCache = null;
            if (sDrawableCacheField != null) {
               try {
                  drawableCache = sDrawableCacheField.get(resourcesImpl);
               } catch (IllegalAccessException var4) {
                  Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", var4);
               }
            }

            return drawableCache != null && flushThemedResourcesCache(drawableCache);
         }
      }
   }

   @RequiresApi(16)
   private static boolean flushThemedResourcesCache(@NonNull Object cache) {
      if (!sThemedResourceCacheClazzFetched) {
         try {
            sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
         } catch (ClassNotFoundException var5) {
            Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", var5);
         }

         sThemedResourceCacheClazzFetched = true;
      }

      if (sThemedResourceCacheClazz == null) {
         return false;
      } else {
         if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {
            try {
               sThemedResourceCache_mUnthemedEntriesField = sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
               sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
            } catch (NoSuchFieldException var4) {
               Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", var4);
            }

            sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
         }

         if (sThemedResourceCache_mUnthemedEntriesField == null) {
            return false;
         } else {
            LongSparseArray unthemedEntries = null;

            try {
               unthemedEntries = (LongSparseArray)sThemedResourceCache_mUnthemedEntriesField.get(cache);
            } catch (IllegalAccessException var3) {
               Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", var3);
            }

            if (unthemedEntries != null) {
               unthemedEntries.clear();
               return true;
            } else {
               return false;
            }
         }
      }
   }
}
