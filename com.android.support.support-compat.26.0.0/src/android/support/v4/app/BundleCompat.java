package android.support.v4.app;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class BundleCompat {
   public static IBinder getBinder(Bundle bundle, String key) {
      return VERSION.SDK_INT >= 18 ? bundle.getBinder(key) : BundleCompat.BundleCompatBaseImpl.getBinder(bundle, key);
   }

   public static void putBinder(Bundle bundle, String key, IBinder binder) {
      if (VERSION.SDK_INT >= 18) {
         bundle.putBinder(key, binder);
      } else {
         BundleCompat.BundleCompatBaseImpl.putBinder(bundle, key, binder);
      }

   }

   static class BundleCompatBaseImpl {
      private static final String TAG = "BundleCompatBaseImpl";
      private static Method sGetIBinderMethod;
      private static boolean sGetIBinderMethodFetched;
      private static Method sPutIBinderMethod;
      private static boolean sPutIBinderMethodFetched;

      public static IBinder getBinder(Bundle bundle, String key) {
         if (!sGetIBinderMethodFetched) {
            try {
               sGetIBinderMethod = Bundle.class.getMethod("getIBinder", String.class);
               sGetIBinderMethod.setAccessible(true);
            } catch (NoSuchMethodException var3) {
               Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", var3);
            }

            sGetIBinderMethodFetched = true;
         }

         if (sGetIBinderMethod != null) {
            try {
               return (IBinder)sGetIBinderMethod.invoke(bundle, key);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException var4) {
               Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", var4);
               sGetIBinderMethod = null;
            }
         }

         return null;
      }

      public static void putBinder(Bundle bundle, String key, IBinder binder) {
         if (!sPutIBinderMethodFetched) {
            try {
               sPutIBinderMethod = Bundle.class.getMethod("putIBinder", String.class, IBinder.class);
               sPutIBinderMethod.setAccessible(true);
            } catch (NoSuchMethodException var5) {
               Log.i("BundleCompatBaseImpl", "Failed to retrieve putIBinder method", var5);
            }

            sPutIBinderMethodFetched = true;
         }

         if (sPutIBinderMethod != null) {
            try {
               sPutIBinderMethod.invoke(bundle, key, binder);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException var4) {
               Log.i("BundleCompatBaseImpl", "Failed to invoke putIBinder via reflection", var4);
               sPutIBinderMethod = null;
            }
         }

      }
   }
}
