package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;

public final class AppOpsManagerCompat {
   public static final int MODE_ALLOWED = 0;
   public static final int MODE_IGNORED = 1;
   public static final int MODE_DEFAULT = 3;

   public static String permissionToOp(@NonNull String permission) {
      return VERSION.SDK_INT >= 23 ? AppOpsManager.permissionToOp(permission) : null;
   }

   public static int noteOp(@NonNull Context context, @NonNull String op, int uid, @NonNull String packageName) {
      if (VERSION.SDK_INT >= 23) {
         AppOpsManager appOpsManager = (AppOpsManager)context.getSystemService(AppOpsManager.class);
         return appOpsManager.noteOp(op, uid, packageName);
      } else {
         return 1;
      }
   }

   public static int noteProxyOp(@NonNull Context context, @NonNull String op, @NonNull String proxiedPackageName) {
      if (VERSION.SDK_INT >= 23) {
         AppOpsManager appOpsManager = (AppOpsManager)context.getSystemService(AppOpsManager.class);
         return appOpsManager.noteProxyOp(op, proxiedPackageName);
      } else {
         return 1;
      }
   }
}
