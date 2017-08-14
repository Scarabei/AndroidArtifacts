package android.support.v4.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.OnFinished;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import java.util.Iterator;

public class ShortcutManagerCompat {
   @VisibleForTesting
   static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
   @VisibleForTesting
   static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

   public static boolean isRequestPinShortcutSupported(@NonNull Context context) {
      if (VERSION.SDK_INT >= 26) {
         return ((ShortcutManager)context.getSystemService(ShortcutManager.class)).isRequestPinShortcutSupported();
      } else if (ContextCompat.checkSelfPermission(context, "com.android.launcher.permission.INSTALL_SHORTCUT") != 0) {
         return false;
      } else {
         Iterator var1 = context.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0).iterator();

         String permission;
         do {
            if (!var1.hasNext()) {
               return false;
            }

            ResolveInfo info = (ResolveInfo)var1.next();
            permission = info.activityInfo.permission;
         } while(!TextUtils.isEmpty(permission) && !"com.android.launcher.permission.INSTALL_SHORTCUT".equals(permission));

         return true;
      }
   }

   public static boolean requestPinShortcut(@NonNull Context context, @NonNull ShortcutInfoCompat shortcut, @Nullable final IntentSender callback) {
      if (VERSION.SDK_INT >= 26) {
         return ((ShortcutManager)context.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcut.toShortcutInfo(), callback);
      } else if (!isRequestPinShortcutSupported(context)) {
         return false;
      } else {
         Intent intent = shortcut.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
         if (callback == null) {
            context.sendBroadcast(intent);
            return true;
         } else {
            context.sendOrderedBroadcast(intent, (String)null, new BroadcastReceiver() {
               public void onReceive(Context context, Intent intent) {
                  try {
                     callback.sendIntent(context, 0, (Intent)null, (OnFinished)null, (Handler)null);
                  } catch (SendIntentException var4) {
                     ;
                  }

               }
            }, (Handler)null, -1, (String)null, (Bundle)null);
            return true;
         }
      }
   }

   @NonNull
   public static Intent createShortcutResultIntent(@NonNull Context context, @NonNull ShortcutInfoCompat shortcut) {
      Intent result = null;
      if (VERSION.SDK_INT >= 26) {
         result = ((ShortcutManager)context.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcut.toShortcutInfo());
      }

      if (result == null) {
         result = new Intent();
      }

      return shortcut.addToIntent(result);
   }
}
