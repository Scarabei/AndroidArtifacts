package android.support.v4.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Build.VERSION;

public abstract class NotificationCompatSideChannelService extends Service {
   public IBinder onBind(Intent intent) {
      if (intent.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL")) {
         return VERSION.SDK_INT > 19 ? null : new NotificationCompatSideChannelService.NotificationSideChannelStub();
      } else {
         return null;
      }
   }

   public abstract void notify(String var1, int var2, String var3, Notification var4);

   public abstract void cancel(String var1, int var2, String var3);

   public abstract void cancelAll(String var1);

   void checkPermission(int callingUid, String packageName) {
      String[] var3 = this.getPackageManager().getPackagesForUid(callingUid);
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String validPackage = var3[var5];
         if (validPackage.equals(packageName)) {
            return;
         }
      }

      throw new SecurityException("NotificationSideChannelService: Uid " + callingUid + " is not authorized for package " + packageName);
   }

   private class NotificationSideChannelStub extends INotificationSideChannel.Stub {
      public void notify(String packageName, int id, String tag, Notification notification) throws RemoteException {
         NotificationCompatSideChannelService.this.checkPermission(getCallingUid(), packageName);
         long idToken = clearCallingIdentity();

         try {
            NotificationCompatSideChannelService.this.notify(packageName, id, tag, notification);
         } finally {
            restoreCallingIdentity(idToken);
         }

      }

      public void cancel(String packageName, int id, String tag) throws RemoteException {
         NotificationCompatSideChannelService.this.checkPermission(getCallingUid(), packageName);
         long idToken = clearCallingIdentity();

         try {
            NotificationCompatSideChannelService.this.cancel(packageName, id, tag);
         } finally {
            restoreCallingIdentity(idToken);
         }

      }

      public void cancelAll(String packageName) {
         NotificationCompatSideChannelService.this.checkPermission(getCallingUid(), packageName);
         long idToken = clearCallingIdentity();

         try {
            NotificationCompatSideChannelService.this.cancelAll(packageName);
         } finally {
            restoreCallingIdentity(idToken);
         }

      }
   }
}
