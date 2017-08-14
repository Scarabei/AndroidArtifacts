package com.google.android.gms.drive.events;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.internal.zzbng;
import com.google.android.gms.internal.zzbor;
import com.google.android.gms.internal.zzbph;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DriveEventService extends Service implements ChangeListener, CompletionListener, zzd, zzi {
   public static final String ACTION_HANDLE_EVENT = "com.google.android.gms.drive.events.HANDLE_EVENT";
   private final String mName;
   private CountDownLatch zzaNe;
   DriveEventService.zza zzaNf;
   boolean zzaNg;
   private int zzaGG;

   protected DriveEventService(String var1) {
      this.zzaNg = false;
      this.zzaGG = -1;
      this.mName = var1;
   }

   protected DriveEventService() {
      this("DriveEventService");
   }

   public final synchronized IBinder onBind(Intent var1) {
      if ("com.google.android.gms.drive.events.HANDLE_EVENT".equals(var1.getAction())) {
         if (this.zzaNf == null && !this.zzaNg) {
            this.zzaNg = true;
            CountDownLatch var2 = new CountDownLatch(1);
            this.zzaNe = new CountDownLatch(1);
            (new zzh(this, var2)).start();

            try {
               if (!var2.await(5000L, TimeUnit.MILLISECONDS)) {
                  zzbng.zzz("DriveEventService", "Failed to synchronously initialize event handler.");
               }
            } catch (InterruptedException var4) {
               throw new RuntimeException("Unable to start event handler", var4);
            }
         }

         return (new DriveEventService.zzb()).asBinder();
      } else {
         return null;
      }
   }

   public boolean onUnbind(Intent var1) {
      return true;
   }

   public synchronized void onDestroy() {
      zzbng.zzx("DriveEventService", "onDestroy");
      if (this.zzaNf != null) {
         Message var1 = this.zzaNf.zzta();
         this.zzaNf.sendMessage(var1);
         this.zzaNf = null;

         try {
            if (!this.zzaNe.await(5000L, TimeUnit.MILLISECONDS)) {
               zzbng.zzy("DriveEventService", "Failed to synchronously quit event handler. Will quit itself");
            }
         } catch (InterruptedException var2) {
            ;
         }

         this.zzaNe = null;
      }

      super.onDestroy();
   }

   public void onChange(ChangeEvent var1) {
      String var10000 = this.mName;
      String var2 = String.valueOf(var1);
      zzbng.zzy(var10000, (new StringBuilder(24 + String.valueOf(var2).length())).append("Unhandled change event: ").append(var2).toString());
   }

   public final void zza(zzb var1) {
      String var10000 = this.mName;
      String var2 = String.valueOf(var1);
      zzbng.zzy(var10000, (new StringBuilder(35 + String.valueOf(var2).length())).append("Unhandled changes available event: ").append(var2).toString());
   }

   public void onCompletion(CompletionEvent var1) {
      String var10000 = this.mName;
      String var2 = String.valueOf(var1);
      zzbng.zzy(var10000, (new StringBuilder(28 + String.valueOf(var2).length())).append("Unhandled completion event: ").append(var2).toString());
   }

   protected int getCallingUid() {
      return Binder.getCallingUid();
   }

   private final void zza(zzbph var1) {
      DriveEvent var2 = var1.zztj();
      String var3 = String.valueOf(var2);
      zzbng.zzx("DriveEventService", (new StringBuilder(20 + String.valueOf(var3).length())).append("handleEventMessage: ").append(var3).toString());

      String var10000;
      try {
         switch(var2.getType()) {
         case 1:
            this.onChange((ChangeEvent)var2);
            return;
         case 2:
            this.onCompletion((CompletionEvent)var2);
            return;
         case 3:
         case 5:
         case 6:
         default:
            var10000 = this.mName;
            var3 = String.valueOf(var2);
            zzbng.zzy(var10000, (new StringBuilder(17 + String.valueOf(var3).length())).append("Unhandled event: ").append(var3).toString());
            return;
         case 4:
            this.zza((zzb)var2);
            return;
         case 7:
            zzr var5 = (zzr)var2;
            var10000 = this.mName;
            String var6 = String.valueOf(var5);
            zzbng.zzy(var10000, (new StringBuilder(32 + String.valueOf(var6).length())).append("Unhandled transfer state event: ").append(var6).toString());
         }
      } catch (Exception var7) {
         var10000 = this.mName;
         String var4 = String.valueOf(var2);
         zzbng.zza(var10000, var7, (new StringBuilder(22 + String.valueOf(var4).length())).append("Error handling event: ").append(var4).toString());
      }
   }

   private final void zzsZ() throws SecurityException {
      int var1;
      if ((var1 = this.getCallingUid()) != this.zzaGG) {
         if (zzw.zzf(this, var1)) {
            this.zzaGG = var1;
         } else {
            throw new SecurityException("Caller is not GooglePlayServices");
         }
      }
   }

   // $FF: synthetic method
   static CountDownLatch zzb(DriveEventService var0) {
      return var0.zzaNe;
   }

   final class zzb extends zzbor {
      // $FF: synthetic field
      private DriveEventService zzaNi;

      zzb() {
         this.zzaNi = DriveEventService.this;
         super();
      }

      public final void zzc(zzbph var1) throws RemoteException {
         DriveEventService var2 = this.zzaNi;
         synchronized(this.zzaNi) {
            String var3 = String.valueOf(var1);
            zzbng.zzx("DriveEventService", (new StringBuilder(9 + String.valueOf(var3).length())).append("onEvent: ").append(var3).toString());
            this.zzaNi.zzsZ();
            if (this.zzaNi.zzaNf != null) {
               Message var6 = this.zzaNi.zzaNf.zzb(var1);
               this.zzaNi.zzaNf.sendMessage(var6);
            } else {
               zzbng.zzz("DriveEventService", "Receiving event before initialize is completed.");
            }

         }
      }
   }

   final class zza extends Handler {
      // $FF: synthetic field
      private DriveEventService zzaNi;

      zza() {
         this.zzaNi = DriveEventService.this;
         super();
      }

      private final Message zzb(zzbph var1) {
         return this.obtainMessage(1, var1);
      }

      private final Message zzta() {
         return this.obtainMessage(2);
      }

      public final void handleMessage(Message var1) {
         int var2 = var1.what;
         zzbng.zzx("DriveEventService", (new StringBuilder(38)).append("handleMessage message type:").append(var2).toString());
         switch(var1.what) {
         case 1:
            this.zzaNi.zza((zzbph)var1.obj);
            return;
         case 2:
            this.getLooper().quit();
            return;
         default:
            var2 = var1.what;
            zzbng.zzy("DriveEventService", (new StringBuilder(35)).append("Unexpected message type:").append(var2).toString());
         }
      }
   }
}
