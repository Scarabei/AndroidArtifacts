package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.util.Log;
import java.util.HashMap;

final class zzag extends zzae implements Callback {
   private final HashMap zzaHP = new HashMap();
   private final Context mApplicationContext;
   private final Handler mHandler;
   private final com.google.android.gms.common.stats.zza zzaHQ;
   private final long zzaHR;
   private final long zzaHS;

   zzag(Context var1) {
      this.mApplicationContext = var1.getApplicationContext();
      this.mHandler = new Handler(var1.getMainLooper(), this);
      this.zzaHQ = com.google.android.gms.common.stats.zza.zzrU();
      this.zzaHR = 5000L;
      this.zzaHS = 300000L;
   }

   protected final boolean zza(zzaf var1, ServiceConnection var2, String var3) {
      zzbo.zzb(var2, "ServiceConnection must not be null");
      HashMap var4 = this.zzaHP;
      synchronized(this.zzaHP) {
         zzah var5;
         if ((var5 = (zzah)this.zzaHP.get(var1)) == null) {
            (var5 = new zzah(this, var1)).zza(var2, var3);
            var5.zzcB(var3);
            this.zzaHP.put(var1, var5);
         } else {
            this.mHandler.removeMessages(0, var1);
            if (var5.zza(var2)) {
               String var6 = String.valueOf(var1);
               throw new IllegalStateException((new StringBuilder(81 + String.valueOf(var6).length())).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(var6).toString());
            }

            var5.zza(var2, var3);
            switch(var5.getState()) {
            case 1:
               var2.onServiceConnected(var5.getComponentName(), var5.getBinder());
               break;
            case 2:
               var5.zzcB(var3);
            }
         }

         return var5.isBound();
      }
   }

   protected final void zzb(zzaf var1, ServiceConnection var2, String var3) {
      zzbo.zzb(var2, "ServiceConnection must not be null");
      HashMap var4 = this.zzaHP;
      synchronized(this.zzaHP) {
         zzah var5;
         String var9;
         if ((var5 = (zzah)this.zzaHP.get(var1)) == null) {
            var9 = String.valueOf(var1);
            throw new IllegalStateException((new StringBuilder(50 + String.valueOf(var9).length())).append("Nonexistent connection status for service config: ").append(var9).toString());
         } else if (!var5.zza(var2)) {
            var9 = String.valueOf(var1);
            throw new IllegalStateException((new StringBuilder(76 + String.valueOf(var9).length())).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(var9).toString());
         } else {
            var5.zzb(var2, var3);
            if (var5.zzrC()) {
               Message var6 = this.mHandler.obtainMessage(0, var1);
               this.mHandler.sendMessageDelayed(var6, this.zzaHR);
            }

         }
      }
   }

   public final boolean handleMessage(Message var1) {
      HashMap var2;
      zzaf var3;
      zzah var4;
      switch(var1.what) {
      case 0:
         var2 = this.zzaHP;
         synchronized(this.zzaHP) {
            var3 = (zzaf)var1.obj;
            if ((var4 = (zzah)this.zzaHP.get(var3)) != null && var4.zzrC()) {
               if (var4.isBound()) {
                  var4.zzcC("GmsClientSupervisor");
               }

               this.zzaHP.remove(var3);
            }

            return true;
         }
      case 1:
         var2 = this.zzaHP;
         synchronized(this.zzaHP) {
            var3 = (zzaf)var1.obj;
            if ((var4 = (zzah)this.zzaHP.get(var3)) != null && var4.getState() == 3) {
               String var5 = String.valueOf(var3);
               Log.wtf("GmsClientSupervisor", (new StringBuilder(47 + String.valueOf(var5).length())).append("Timeout waiting for ServiceConnection callback ").append(var5).toString(), new Exception());
               ComponentName var9;
               if ((var9 = var4.getComponentName()) == null) {
                  var9 = var3.getComponentName();
               }

               if (var9 == null) {
                  var9 = new ComponentName(var3.getPackage(), "unknown");
               }

               var4.onServiceDisconnected(var9);
            }

            return true;
         }
      default:
         return false;
      }
   }

   // $FF: synthetic method
   static HashMap zza(zzag var0) {
      return var0.zzaHP;
   }

   // $FF: synthetic method
   static Handler zzb(zzag var0) {
      return var0.mHandler;
   }

   // $FF: synthetic method
   static Context zzc(zzag var0) {
      return var0.mApplicationContext;
   }

   // $FF: synthetic method
   static com.google.android.gms.common.stats.zza zzd(zzag var0) {
      return var0.zzaHQ;
   }

   // $FF: synthetic method
   static long zze(zzag var0) {
      return var0.zzaHS;
   }
}
