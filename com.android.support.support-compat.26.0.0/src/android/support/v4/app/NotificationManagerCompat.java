package android.support.v4.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.Build.VERSION;
import android.os.Handler.Callback;
import android.provider.Settings.Secure;
import android.support.annotation.GuardedBy;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public final class NotificationManagerCompat {
   private static final String TAG = "NotifManCompat";
   private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
   private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
   public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
   public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
   static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
   private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
   private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
   private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
   private static final Object sEnabledNotificationListenersLock = new Object();
   @GuardedBy("sEnabledNotificationListenersLock")
   private static String sEnabledNotificationListeners;
   @GuardedBy("sEnabledNotificationListenersLock")
   private static Set sEnabledNotificationListenerPackages = new HashSet();
   private final Context mContext;
   private final NotificationManager mNotificationManager;
   private static final Object sLock = new Object();
   @GuardedBy("sLock")
   private static NotificationManagerCompat.SideChannelManager sSideChannelManager;
   public static final int IMPORTANCE_UNSPECIFIED = -1000;
   public static final int IMPORTANCE_NONE = 0;
   public static final int IMPORTANCE_MIN = 1;
   public static final int IMPORTANCE_LOW = 2;
   public static final int IMPORTANCE_DEFAULT = 3;
   public static final int IMPORTANCE_HIGH = 4;
   public static final int IMPORTANCE_MAX = 5;

   public static NotificationManagerCompat from(Context context) {
      return new NotificationManagerCompat(context);
   }

   private NotificationManagerCompat(Context context) {
      this.mContext = context;
      this.mNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
   }

   public void cancel(int id) {
      this.cancel((String)null, id);
   }

   public void cancel(String tag, int id) {
      this.mNotificationManager.cancel(tag, id);
      if (VERSION.SDK_INT <= 19) {
         this.pushSideChannelQueue(new NotificationManagerCompat.CancelTask(this.mContext.getPackageName(), id, tag));
      }

   }

   public void cancelAll() {
      this.mNotificationManager.cancelAll();
      if (VERSION.SDK_INT <= 19) {
         this.pushSideChannelQueue(new NotificationManagerCompat.CancelTask(this.mContext.getPackageName()));
      }

   }

   public void notify(int id, Notification notification) {
      this.notify((String)null, id, notification);
   }

   public void notify(String tag, int id, Notification notification) {
      if (useSideChannelForNotification(notification)) {
         this.pushSideChannelQueue(new NotificationManagerCompat.NotifyTask(this.mContext.getPackageName(), id, tag, notification));
         this.mNotificationManager.cancel(tag, id);
      } else {
         this.mNotificationManager.notify(tag, id, notification);
      }

   }

   public boolean areNotificationsEnabled() {
      if (VERSION.SDK_INT >= 24) {
         return this.mNotificationManager.areNotificationsEnabled();
      } else if (VERSION.SDK_INT >= 19) {
         AppOpsManager appOps = (AppOpsManager)this.mContext.getSystemService("appops");
         ApplicationInfo appInfo = this.mContext.getApplicationInfo();
         String pkg = this.mContext.getApplicationContext().getPackageName();
         int uid = appInfo.uid;

         try {
            Class appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
            int value = ((Integer)opPostNotificationValue.get(Integer.class)).intValue();
            return ((Integer)checkOpNoThrowMethod.invoke(appOps, value, uid, pkg)).intValue() == 0;
         } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | RuntimeException | ClassNotFoundException var9) {
            return true;
         }
      } else {
         return true;
      }
   }

   public int getImportance() {
      return VERSION.SDK_INT >= 24 ? this.mNotificationManager.getImportance() : -1000;
   }

   public static Set getEnabledListenerPackages(Context context) {
      String enabledNotificationListeners = Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
      Object var2 = sEnabledNotificationListenersLock;
      synchronized(sEnabledNotificationListenersLock) {
         if (enabledNotificationListeners != null && !enabledNotificationListeners.equals(sEnabledNotificationListeners)) {
            String[] components = enabledNotificationListeners.split(":");
            Set packageNames = new HashSet(components.length);
            String[] var5 = components;
            int var6 = components.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               String component = var5[var7];
               ComponentName componentName = ComponentName.unflattenFromString(component);
               if (componentName != null) {
                  packageNames.add(componentName.getPackageName());
               }
            }

            sEnabledNotificationListenerPackages = packageNames;
            sEnabledNotificationListeners = enabledNotificationListeners;
         }

         return sEnabledNotificationListenerPackages;
      }
   }

   private static boolean useSideChannelForNotification(Notification notification) {
      Bundle extras = NotificationCompat.getExtras(notification);
      return extras != null && extras.getBoolean("android.support.useSideChannel");
   }

   private void pushSideChannelQueue(NotificationManagerCompat.Task task) {
      Object var2 = sLock;
      synchronized(sLock) {
         if (sSideChannelManager == null) {
            sSideChannelManager = new NotificationManagerCompat.SideChannelManager(this.mContext.getApplicationContext());
         }

         sSideChannelManager.queueTask(task);
      }
   }

   private static class CancelTask implements NotificationManagerCompat.Task {
      final String packageName;
      final int id;
      final String tag;
      final boolean all;

      CancelTask(String packageName) {
         this.packageName = packageName;
         this.id = 0;
         this.tag = null;
         this.all = true;
      }

      CancelTask(String packageName, int id, String tag) {
         this.packageName = packageName;
         this.id = id;
         this.tag = tag;
         this.all = false;
      }

      public void send(INotificationSideChannel service) throws RemoteException {
         if (this.all) {
            service.cancelAll(this.packageName);
         } else {
            service.cancel(this.packageName, this.id, this.tag);
         }

      }

      public String toString() {
         StringBuilder sb = new StringBuilder("CancelTask[");
         sb.append("packageName:").append(this.packageName);
         sb.append(", id:").append(this.id);
         sb.append(", tag:").append(this.tag);
         sb.append(", all:").append(this.all);
         sb.append("]");
         return sb.toString();
      }
   }

   private static class NotifyTask implements NotificationManagerCompat.Task {
      final String packageName;
      final int id;
      final String tag;
      final Notification notif;

      NotifyTask(String packageName, int id, String tag, Notification notif) {
         this.packageName = packageName;
         this.id = id;
         this.tag = tag;
         this.notif = notif;
      }

      public void send(INotificationSideChannel service) throws RemoteException {
         service.notify(this.packageName, this.id, this.tag, this.notif);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("NotifyTask[");
         sb.append("packageName:").append(this.packageName);
         sb.append(", id:").append(this.id);
         sb.append(", tag:").append(this.tag);
         sb.append("]");
         return sb.toString();
      }
   }

   private interface Task {
      void send(INotificationSideChannel var1) throws RemoteException;
   }

   private static class ServiceConnectedEvent {
      final ComponentName componentName;
      final IBinder iBinder;

      ServiceConnectedEvent(ComponentName componentName, IBinder iBinder) {
         this.componentName = componentName;
         this.iBinder = iBinder;
      }
   }

   private static class SideChannelManager implements Callback, ServiceConnection {
      private static final int MSG_QUEUE_TASK = 0;
      private static final int MSG_SERVICE_CONNECTED = 1;
      private static final int MSG_SERVICE_DISCONNECTED = 2;
      private static final int MSG_RETRY_LISTENER_QUEUE = 3;
      private final Context mContext;
      private final HandlerThread mHandlerThread;
      private final Handler mHandler;
      private final Map mRecordMap = new HashMap();
      private Set mCachedEnabledPackages = new HashSet();

      public SideChannelManager(Context context) {
         this.mContext = context;
         this.mHandlerThread = new HandlerThread("NotificationManagerCompat");
         this.mHandlerThread.start();
         this.mHandler = new Handler(this.mHandlerThread.getLooper(), this);
      }

      public void queueTask(NotificationManagerCompat.Task task) {
         this.mHandler.obtainMessage(0, task).sendToTarget();
      }

      public boolean handleMessage(Message msg) {
         switch(msg.what) {
         case 0:
            this.handleQueueTask((NotificationManagerCompat.Task)msg.obj);
            return true;
         case 1:
            NotificationManagerCompat.ServiceConnectedEvent event = (NotificationManagerCompat.ServiceConnectedEvent)msg.obj;
            this.handleServiceConnected(event.componentName, event.iBinder);
            return true;
         case 2:
            this.handleServiceDisconnected((ComponentName)msg.obj);
            return true;
         case 3:
            this.handleRetryListenerQueue((ComponentName)msg.obj);
            return true;
         default:
            return false;
         }
      }

      private void handleQueueTask(NotificationManagerCompat.Task task) {
         this.updateListenerMap();
         Iterator var2 = this.mRecordMap.values().iterator();

         while(var2.hasNext()) {
            NotificationManagerCompat.SideChannelManager.ListenerRecord record = (NotificationManagerCompat.SideChannelManager.ListenerRecord)var2.next();
            record.taskQueue.add(task);
            this.processListenerQueue(record);
         }

      }

      private void handleServiceConnected(ComponentName componentName, IBinder iBinder) {
         NotificationManagerCompat.SideChannelManager.ListenerRecord record = (NotificationManagerCompat.SideChannelManager.ListenerRecord)this.mRecordMap.get(componentName);
         if (record != null) {
            record.service = INotificationSideChannel.Stub.asInterface(iBinder);
            record.retryCount = 0;
            this.processListenerQueue(record);
         }

      }

      private void handleServiceDisconnected(ComponentName componentName) {
         NotificationManagerCompat.SideChannelManager.ListenerRecord record = (NotificationManagerCompat.SideChannelManager.ListenerRecord)this.mRecordMap.get(componentName);
         if (record != null) {
            this.ensureServiceUnbound(record);
         }

      }

      private void handleRetryListenerQueue(ComponentName componentName) {
         NotificationManagerCompat.SideChannelManager.ListenerRecord record = (NotificationManagerCompat.SideChannelManager.ListenerRecord)this.mRecordMap.get(componentName);
         if (record != null) {
            this.processListenerQueue(record);
         }

      }

      public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
         if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Connected to service " + componentName);
         }

         this.mHandler.obtainMessage(1, new NotificationManagerCompat.ServiceConnectedEvent(componentName, iBinder)).sendToTarget();
      }

      public void onServiceDisconnected(ComponentName componentName) {
         if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Disconnected from service " + componentName);
         }

         this.mHandler.obtainMessage(2, componentName).sendToTarget();
      }

      private void updateListenerMap() {
         Set enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
         if (!enabledPackages.equals(this.mCachedEnabledPackages)) {
            this.mCachedEnabledPackages = enabledPackages;
            List resolveInfos = this.mContext.getPackageManager().queryIntentServices((new Intent()).setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            Set enabledComponents = new HashSet();
            Iterator it = resolveInfos.iterator();

            while(it.hasNext()) {
               ResolveInfo resolveInfo = (ResolveInfo)it.next();
               if (enabledPackages.contains(resolveInfo.serviceInfo.packageName)) {
                  ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                  if (resolveInfo.serviceInfo.permission != null) {
                     Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                  } else {
                     enabledComponents.add(componentName);
                  }
               }
            }

            it = enabledComponents.iterator();

            while(it.hasNext()) {
               ComponentName componentName = (ComponentName)it.next();
               if (!this.mRecordMap.containsKey(componentName)) {
                  if (Log.isLoggable("NotifManCompat", 3)) {
                     Log.d("NotifManCompat", "Adding listener record for " + componentName);
                  }

                  this.mRecordMap.put(componentName, new NotificationManagerCompat.SideChannelManager.ListenerRecord(componentName));
               }
            }

            it = this.mRecordMap.entrySet().iterator();

            while(it.hasNext()) {
               Entry entry = (Entry)it.next();
               if (!enabledComponents.contains(entry.getKey())) {
                  if (Log.isLoggable("NotifManCompat", 3)) {
                     Log.d("NotifManCompat", "Removing listener record for " + entry.getKey());
                  }

                  this.ensureServiceUnbound((NotificationManagerCompat.SideChannelManager.ListenerRecord)entry.getValue());
                  it.remove();
               }
            }

         }
      }

      private boolean ensureServiceBound(NotificationManagerCompat.SideChannelManager.ListenerRecord record) {
         if (record.bound) {
            return true;
         } else {
            Intent intent = (new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL")).setComponent(record.componentName);
            record.bound = this.mContext.bindService(intent, this, 33);
            if (record.bound) {
               record.retryCount = 0;
            } else {
               Log.w("NotifManCompat", "Unable to bind to listener " + record.componentName);
               this.mContext.unbindService(this);
            }

            return record.bound;
         }
      }

      private void ensureServiceUnbound(NotificationManagerCompat.SideChannelManager.ListenerRecord record) {
         if (record.bound) {
            this.mContext.unbindService(this);
            record.bound = false;
         }

         record.service = null;
      }

      private void scheduleListenerRetry(NotificationManagerCompat.SideChannelManager.ListenerRecord record) {
         if (!this.mHandler.hasMessages(3, record.componentName)) {
            ++record.retryCount;
            if (record.retryCount > 6) {
               Log.w("NotifManCompat", "Giving up on delivering " + record.taskQueue.size() + " tasks to " + record.componentName + " after " + record.retryCount + " retries");
               record.taskQueue.clear();
            } else {
               int delayMs = 1000 * (1 << record.retryCount - 1);
               if (Log.isLoggable("NotifManCompat", 3)) {
                  Log.d("NotifManCompat", "Scheduling retry for " + delayMs + " ms");
               }

               Message msg = this.mHandler.obtainMessage(3, record.componentName);
               this.mHandler.sendMessageDelayed(msg, (long)delayMs);
            }
         }
      }

      private void processListenerQueue(NotificationManagerCompat.SideChannelManager.ListenerRecord record) {
         if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Processing component " + record.componentName + ", " + record.taskQueue.size() + " queued tasks");
         }

         if (!record.taskQueue.isEmpty()) {
            if (this.ensureServiceBound(record) && record.service != null) {
               while(true) {
                  NotificationManagerCompat.Task task = (NotificationManagerCompat.Task)record.taskQueue.peek();
                  if (task == null) {
                     break;
                  }

                  try {
                     if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Sending task " + task);
                     }

                     task.send(record.service);
                     record.taskQueue.remove();
                  } catch (DeadObjectException var4) {
                     if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Remote service has died: " + record.componentName);
                     }
                     break;
                  } catch (RemoteException var5) {
                     Log.w("NotifManCompat", "RemoteException communicating with " + record.componentName, var5);
                     break;
                  }
               }

               if (!record.taskQueue.isEmpty()) {
                  this.scheduleListenerRetry(record);
               }

            } else {
               this.scheduleListenerRetry(record);
            }
         }
      }

      private static class ListenerRecord {
         public final ComponentName componentName;
         public boolean bound = false;
         public INotificationSideChannel service;
         public LinkedList taskQueue = new LinkedList();
         public int retryCount = 0;

         public ListenerRecord(ComponentName componentName) {
            this.componentName = componentName;
         }
      }
   }
}
