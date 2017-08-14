package com.google.android.gms.wearable;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.wearable.internal.zzaa;
import com.google.android.gms.wearable.internal.zzai;
import com.google.android.gms.wearable.internal.zzdl;
import com.google.android.gms.wearable.internal.zzdx;
import com.google.android.gms.wearable.internal.zzeg;
import com.google.android.gms.wearable.internal.zzgh;
import java.util.List;

public class WearableListenerService extends Service implements CapabilityApi.CapabilityListener, ChannelApi.ChannelListener, DataApi.DataListener, MessageApi.MessageListener, NodeApi.NodeListener {
   public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
   private ComponentName zzbRq;
   private WearableListenerService.zzb zzbRr;
   private IBinder zzaHj;
   private Intent zzbRs;
   private Looper zzbRt;
   private final Object zzbRu = new Object();
   private boolean zzbRv;

   public void onCreate() {
      super.onCreate();
      this.zzbRq = new ComponentName(this, this.getClass().getName());
      if (Log.isLoggable("WearableLS", 3)) {
         String var1 = String.valueOf(this.zzbRq);
         Log.d("WearableLS", (new StringBuilder(10 + String.valueOf(var1).length())).append("onCreate: ").append(var1).toString());
      }

      this.zzbRr = new WearableListenerService.zzb(this.getLooper());
      this.zzbRs = new Intent("com.google.android.gms.wearable.BIND_LISTENER");
      this.zzbRs.setComponent(this.zzbRq);
      this.zzaHj = new WearableListenerService.zzc((zzk)null);
   }

   public void onDestroy() {
      if (Log.isLoggable("WearableLS", 3)) {
         String var1 = String.valueOf(this.zzbRq);
         Log.d("WearableLS", (new StringBuilder(11 + String.valueOf(var1).length())).append("onDestroy: ").append(var1).toString());
      }

      Object var5 = this.zzbRu;
      synchronized(this.zzbRu) {
         this.zzbRv = true;
         if (this.zzbRr == null) {
            String var2 = String.valueOf(this.zzbRq);
            throw new IllegalStateException((new StringBuilder(111 + String.valueOf(var2).length())).append("onDestroy: mServiceHandler not set, did you override onCreate() but forget to call super.onCreate()? component=").append(var2).toString());
         }

         this.zzbRr.quit();
      }

      super.onDestroy();
   }

   public final IBinder onBind(Intent var1) {
      return "com.google.android.gms.wearable.BIND_LISTENER".equals(var1.getAction()) ? this.zzaHj : null;
   }

   public Looper getLooper() {
      if (this.zzbRt == null) {
         HandlerThread var1;
         (var1 = new HandlerThread("WearableListenerService")).start();
         this.zzbRt = var1.getLooper();
      }

      return this.zzbRt;
   }

   public void onDataChanged(DataEventBuffer var1) {
   }

   public void onMessageReceived(MessageEvent var1) {
   }

   public void onPeerConnected(Node var1) {
   }

   public void onPeerDisconnected(Node var1) {
   }

   public void onConnectedNodes(List var1) {
   }

   public void onCapabilityChanged(CapabilityInfo var1) {
   }

   public void onChannelOpened(Channel var1) {
   }

   public void onChannelClosed(Channel var1, int var2, int var3) {
   }

   public void onInputClosed(Channel var1, int var2, int var3) {
   }

   public void onOutputClosed(Channel var1, int var2, int var3) {
   }

   public void onNotificationReceived(zzd var1) {
   }

   public void onEntityUpdate(zzb var1) {
   }

   final class zzc extends zzdl {
      private volatile int zzbRy;

      private zzc() {
         this.zzbRy = -1;
      }

      public final void zzS(DataHolder var1) {
         zzl var2 = new zzl(this, var1);
         boolean var8 = false;

         boolean var3;
         try {
            var8 = true;
            String var4 = String.valueOf(var1);
            int var5 = var1.getCount();
            var3 = this.zza(var2, "onDataItemChanged", (new StringBuilder(18 + String.valueOf(var4).length())).append(var4).append(", rows=").append(var5).toString());
            var8 = false;
         } finally {
            if (var8) {
               var1.close();
            }
         }

         if (!var3) {
            var1.close();
         }
      }

      public final void zza(zzdx var1) {
         zzm var2 = new zzm(this, var1);
         this.zza(var2, "onMessageReceived", var1);
      }

      public final void zza(zzeg var1) {
         zzn var2 = new zzn(this, var1);
         this.zza(var2, "onPeerConnected", var1);
      }

      public final void zzb(zzeg var1) {
         zzo var2 = new zzo(this, var1);
         this.zza(var2, "onPeerDisconnected", var1);
      }

      public final void onConnectedNodes(List var1) {
         zzp var2 = new zzp(this, var1);
         this.zza(var2, "onConnectedNodes", var1);
      }

      public final void zza(zzaa var1) {
         zzq var2 = new zzq(this, var1);
         this.zza(var2, "onConnectedCapabilityChanged", var1);
      }

      public final void zza(com.google.android.gms.wearable.internal.zzl var1) {
         zzr var2 = new zzr(this, var1);
         this.zza(var2, "onNotificationReceived", var1);
      }

      public final void zza(com.google.android.gms.wearable.internal.zzi var1) {
         zzs var2 = new zzs(this, var1);
         this.zza(var2, "onEntityUpdate", var1);
      }

      public final void zza(zzai var1) {
         zzt var2 = new zzt(this, var1);
         this.zza(var2, "onChannelEvent", var1);
      }

      private final boolean zza(Runnable var1, String var2, Object var3) {
         if (Log.isLoggable("WearableLS", 3)) {
            Log.d("WearableLS", String.format("%s: %s %s", var2, WearableListenerService.this.zzbRq.toString(), var3));
         }

         boolean var10000;
         int var7;
         if ((var7 = Binder.getCallingUid()) == this.zzbRy) {
            var10000 = true;
         } else if (zzgh.zzbz(WearableListenerService.this).zzgm("com.google.android.wearable.app.cn") && zzw.zzb(WearableListenerService.this, var7, "com.google.android.wearable.app.cn")) {
            this.zzbRy = var7;
            var10000 = true;
         } else if (zzw.zzf(WearableListenerService.this, var7)) {
            this.zzbRy = var7;
            var10000 = true;
         } else {
            Log.e("WearableLS", (new StringBuilder(57)).append("Caller is not GooglePlayServices; caller UID: ").append(var7).toString());
            var10000 = false;
         }

         if (!var10000) {
            return false;
         } else {
            synchronized(WearableListenerService.this.zzbRu) {
               if (WearableListenerService.this.zzbRv) {
                  return false;
               } else {
                  WearableListenerService.this.zzbRr.post(var1);
                  return true;
               }
            }
         }
      }

      // $FF: synthetic method
      zzc(zzk var2) {
         this();
      }
   }

   final class zzb extends Handler {
      private boolean started;
      private final WearableListenerService.zza zzbRw;
      // $FF: synthetic field
      private WearableListenerService zzbRx;

      zzb(Looper var2) {
         this.zzbRx = WearableListenerService.this;
         super(var2);
         this.zzbRw = this.zzbRx.new zza((zzk)null);
      }

      public final void dispatchMessage(Message var1) {
         this.zzDW();

         try {
            super.dispatchMessage(var1);
         } finally {
            if (!this.hasMessages(0)) {
               this.zzgk("dispatch");
            }

         }

      }

      final void quit() {
         this.getLooper().quit();
         this.zzgk("quit");
      }

      @SuppressLint({"UntrackedBindService"})
      private final synchronized void zzDW() {
         if (!this.started) {
            if (Log.isLoggable("WearableLS", 2)) {
               String var1 = String.valueOf(this.zzbRx.zzbRq);
               Log.v("WearableLS", (new StringBuilder(13 + String.valueOf(var1).length())).append("bindService: ").append(var1).toString());
            }

            this.zzbRx.bindService(this.zzbRx.zzbRs, this.zzbRw, 1);
            this.started = true;
         }
      }

      @SuppressLint({"UntrackedBindService"})
      private final synchronized void zzgk(String var1) {
         if (this.started) {
            if (Log.isLoggable("WearableLS", 2)) {
               String var2 = String.valueOf(this.zzbRx.zzbRq);
               Log.v("WearableLS", (new StringBuilder(17 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("unbindService: ").append(var1).append(", ").append(var2).toString());
            }

            try {
               this.zzbRx.unbindService(this.zzbRw);
            } catch (RuntimeException var3) {
               Log.e("WearableLS", "Exception when unbinding from local service", var3);
            }

            this.started = false;
         }
      }
   }

   class zza implements ServiceConnection {
      private zza() {
      }

      public final void onServiceConnected(ComponentName var1, IBinder var2) {
      }

      public final void onServiceDisconnected(ComponentName var1) {
      }

      // $FF: synthetic method
      zza(zzk var2) {
         this();
      }
   }
}
