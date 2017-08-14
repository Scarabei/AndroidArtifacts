package com.google.android.gms.gcm;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzw;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public abstract class GcmTaskService extends Service {
   public static final String SERVICE_PERMISSION = "com.google.android.gms.permission.BIND_NETWORK_TASK_SERVICE";
   public static final String SERVICE_ACTION_EXECUTE_TASK = "com.google.android.gms.gcm.ACTION_TASK_READY";
   public static final String SERVICE_ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
   private final Object lock = new Object();
   private final Set zzbfE = new HashSet();
   private int zzbfF;
   private ExecutorService zzqF;
   private Messenger zzbfG;
   private ComponentName componentName;

   @CallSuper
   public void onCreate() {
      super.onCreate();
      this.zzqF = Executors.newFixedThreadPool(2, new zzb(this));
      this.zzbfG = new Messenger(new GcmTaskService.zza(Looper.getMainLooper()));
      this.componentName = new ComponentName(this, this.getClass());
   }

   @CallSuper
   public void onDestroy() {
      super.onDestroy();
      List var1;
      if (!(var1 = this.zzqF.shutdownNow()).isEmpty()) {
         int var2 = var1.size();
         Log.e("GcmTaskService", (new StringBuilder(79)).append("Shutting down, but not all tasks are finished executing. Remaining: ").append(var2).toString());
      }

   }

   @CallSuper
   public int onStartCommand(Intent var1, int var2, int var3) {
      if (var1 == null) {
         this.zzbf(var3);
         return 2;
      } else {
         try {
            var1.setExtrasClassLoader(PendingCallback.class.getClassLoader());
            String var4 = var1.getAction();
            if (!"com.google.android.gms.gcm.ACTION_TASK_READY".equals(var4)) {
               if ("com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(var4)) {
                  this.onInitializeTasks();
               } else {
                  Log.e("GcmTaskService", (new StringBuilder(37 + String.valueOf(var4).length())).append("Unknown action received ").append(var4).append(", terminating").toString());
               }

               return 2;
            }

            String var5 = var1.getStringExtra("tag");
            Parcelable var6 = var1.getParcelableExtra("callback");
            Bundle var7 = var1.getBundleExtra("extras");
            ArrayList var8 = var1.getParcelableArrayListExtra("triggered_uris");
            if (var6 instanceof PendingCallback) {
               Object var17 = this.lock;
               synchronized(this.lock) {
                  if (!this.zzbfE.add(var5)) {
                     String var19 = String.valueOf(this.getPackageName());
                     Log.w("GcmTaskService", (new StringBuilder(44 + String.valueOf(var19).length() + String.valueOf(var5).length())).append(var19).append(" ").append(var5).append(": Task already running, won't start another").toString());
                     return 2;
                  }
               }

               IBinder var18 = ((PendingCallback)var6).zzaHj;
               GcmTaskService.zzb var10 = new GcmTaskService.zzb(var5, var18, var7, var8);
               this.zza(var10);
               return 2;
            }

            String var9 = String.valueOf(this.getPackageName());
            Log.e("GcmTaskService", (new StringBuilder(47 + String.valueOf(var9).length() + String.valueOf(var5).length())).append(var9).append(" ").append(var5).append(": Could not process request, invalid callback.").toString());
         } finally {
            this.zzbf(var3);
         }

         return 2;
      }
   }

   private final void zzbf(int var1) {
      Object var2 = this.lock;
      synchronized(this.lock) {
         this.zzbfF = var1;
         if (this.zzbfE.isEmpty()) {
            this.stopSelf(this.zzbfF);
         }

      }
   }

   private final void zzdp(String var1) {
      Object var2 = this.lock;
      synchronized(this.lock) {
         this.zzbfE.remove(var1);
         if (this.zzbfE.isEmpty()) {
            this.stopSelf(this.zzbfF);
         }

      }
   }

   @CallSuper
   public IBinder onBind(Intent var1) {
      return var1 != null && zzq.zzse() && "com.google.android.gms.gcm.ACTION_TASK_READY".equals(var1.getAction()) ? this.zzbfG.getBinder() : null;
   }

   private final void zza(GcmTaskService.zzb var1) {
      try {
         this.zzqF.execute(var1);
      } catch (RejectedExecutionException var3) {
         Log.e("GcmTaskService", "Executor is shutdown. onDestroy was called but main looper had an unprocessed start task message. The task will be retried with backoff delay.", var3);
         GcmTaskService.zzb.zza(var1, 1);
      }
   }

   public abstract int onRunTask(TaskParams var1);

   public void onInitializeTasks() {
   }

   class zzb implements Runnable {
      private final String mTag;
      private final Bundle mExtras;
      private final List zzbfJ;
      @Nullable
      private final zzd zzbfK;
      @Nullable
      private final Messenger mMessenger;
      // $FF: synthetic field
      private GcmTaskService zzbfI;

      zzb(String var2, IBinder var3, Bundle var4, List var5) {
         this.zzbfI = GcmTaskService.this;
         super();
         this.mTag = var2;
         IInterface var7;
         this.zzbfK = (zzd)(var3 == null ? null : ((var7 = var3.queryLocalInterface("com.google.android.gms.gcm.INetworkTaskCallback")) instanceof zzd ? (zzd)var7 : new zze(var3)));
         this.mExtras = var4;
         this.zzbfJ = var5;
         this.mMessenger = null;
      }

      zzb(String var2, Messenger var3, Bundle var4, List var5) {
         this.zzbfI = GcmTaskService.this;
         super();
         this.mTag = var2;
         this.mMessenger = var3;
         this.mExtras = var4;
         this.zzbfJ = var5;
         this.zzbfK = null;
      }

      public final void run() {
         TaskParams var1 = new TaskParams(this.mTag, this.mExtras, this.zzbfJ);
         int var2 = this.zzbfI.onRunTask(var1);
         this.zzbg(var2);
      }

      private final void zzbg(int var1) {
         synchronized(this.zzbfI.lock) {
            try {
               if (this.zzvC()) {
                  Messenger var10000 = this.mMessenger;
                  Message var7;
                  (var7 = Message.obtain()).what = 3;
                  var7.arg1 = var1;
                  Bundle var8;
                  (var8 = new Bundle()).putParcelable("component", this.zzbfI.componentName);
                  var8.putString("tag", this.mTag);
                  var7.setData(var8);
                  var10000.send(var7);
               } else {
                  this.zzbfK.zzbh(var1);
               }
            } catch (RemoteException var12) {
               String var10002 = String.valueOf(this.mTag);
               String var10001;
               if (var10002.length() != 0) {
                  var10001 = "Error reporting result of operation to scheduler for ".concat(var10002);
               } else {
                  String var10003 = new String;
                  var10001 = var10003;
                  var10003.<init>("Error reporting result of operation to scheduler for ");
               }

               Log.e("GcmTaskService", var10001);
            } finally {
               if (!this.zzvC()) {
                  this.zzbfI.zzdp(this.mTag);
               }

            }

         }
      }

      private final boolean zzvC() {
         return this.mMessenger != null;
      }

      // $FF: synthetic method
      static void zza(GcmTaskService.zzb var0, int var1) {
         var0.zzbg(1);
      }
   }

   @TargetApi(21)
   class zza extends Handler {
      // $FF: synthetic field
      private GcmTaskService zzbfI;

      zza(Looper var2) {
         this.zzbfI = GcmTaskService.this;
         super(var2);
      }

      public final void handleMessage(Message var1) {
         if (!zzw.zzb(this.zzbfI, var1.sendingUid, "com.google.android.gms")) {
            Log.e("GcmTaskService", "unable to verify presence of Google Play Services");
         } else {
            switch(var1.what) {
            case 1:
               Bundle var9;
               if ((var9 = var1.getData()) != null) {
                  Messenger var6 = var1.replyTo;
                  if (var1.replyTo != null) {
                     String var7 = var9.getString("tag");
                     ArrayList var8 = var9.getParcelableArrayList("triggered_uris");
                     this.zzbfI.zza(this.zzbfI.new zzb(var7, var6, var9.getBundle("extras"), var8));
                  }
               }

               return;
            case 2:
               if (Log.isLoggable("GcmTaskService", 3)) {
                  String var5 = String.valueOf(var1);
                  Log.d("GcmTaskService", (new StringBuilder(45 + String.valueOf(var5).length())).append("ignoring unimplemented stop message for now: ").append(var5).toString());
               }

               return;
            case 3:
            default:
               String var2 = String.valueOf(var1);
               Log.e("GcmTaskService", (new StringBuilder(31 + String.valueOf(var2).length())).append("Unrecognized message received: ").append(var2).toString());
               return;
            case 4:
               this.zzbfI.onInitializeTasks();
            }
         }
      }
   }
}
