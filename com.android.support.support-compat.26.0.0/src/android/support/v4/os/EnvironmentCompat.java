package android.support.v4.os;

import android.os.Environment;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public final class EnvironmentCompat {
   private static final String TAG = "EnvironmentCompat";
   public static final String MEDIA_UNKNOWN = "unknown";

   public static String getStorageState(File path) {
      if (VERSION.SDK_INT >= 19) {
         return Environment.getStorageState(path);
      } else {
         try {
            String canonicalPath = path.getCanonicalPath();
            String canonicalExternal = Environment.getExternalStorageDirectory().getCanonicalPath();
            if (canonicalPath.startsWith(canonicalExternal)) {
               return Environment.getExternalStorageState();
            }
         } catch (IOException var3) {
            Log.w("EnvironmentCompat", "Failed to resolve canonical path: " + var3);
         }

         return "unknown";
      }
   }
}
