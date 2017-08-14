package android.support.v4.content;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.AppOpsManagerCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionChecker {
   public static final int PERMISSION_GRANTED = 0;
   public static final int PERMISSION_DENIED = -1;
   public static final int PERMISSION_DENIED_APP_OP = -2;

   public static int checkPermission(@NonNull Context context, @NonNull String permission, int pid, int uid, String packageName) {
      if (context.checkPermission(permission, pid, uid) == -1) {
         return -1;
      } else {
         String op = AppOpsManagerCompat.permissionToOp(permission);
         if (op == null) {
            return 0;
         } else {
            if (packageName == null) {
               String[] packageNames = context.getPackageManager().getPackagesForUid(uid);
               if (packageNames == null || packageNames.length <= 0) {
                  return -1;
               }

               packageName = packageNames[0];
            }

            return AppOpsManagerCompat.noteProxyOp(context, op, packageName) != 0 ? -2 : 0;
         }
      }
   }

   public static int checkSelfPermission(@NonNull Context context, @NonNull String permission) {
      return checkPermission(context, permission, Process.myPid(), Process.myUid(), context.getPackageName());
   }

   public static int checkCallingPermission(@NonNull Context context, @NonNull String permission, String packageName) {
      return Binder.getCallingPid() == Process.myPid() ? -1 : checkPermission(context, permission, Binder.getCallingPid(), Binder.getCallingUid(), packageName);
   }

   public static int checkCallingOrSelfPermission(@NonNull Context context, @NonNull String permission) {
      String packageName = Binder.getCallingPid() == Process.myPid() ? context.getPackageName() : null;
      return checkPermission(context, permission, Binder.getCallingPid(), Binder.getCallingUid(), packageName);
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface PermissionResult {
   }
}
