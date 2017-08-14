package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.analytics.zzl;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.stats.zza;

public final class zzamp implements ServiceConnection {
   private volatile zzany zzagl;
   private volatile boolean zzagm;
   // $FF: synthetic field
   final zzamn zzagk;

   protected zzamp(zzamn var1) {
      this.zzagk = var1;
   }

   public final zzany zzkR() {
      zzl.zzjC();
      Intent var1;
      (var1 = new Intent("com.google.android.gms.analytics.service.START")).setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
      Context var2 = this.zzagk.getContext();
      var1.putExtra("app_package_name", var2.getPackageName());
      zza var3 = zza.zzrU();
      synchronized(this) {
         this.zzagl = null;
         this.zzagm = true;
         boolean var5 = var3.zza(var2, var1, zzamn.zza(this.zzagk), 129);
         this.zzagk.zza((String)"Bind to service requested", (Object)var5);
         if (!var5) {
            this.zzagm = false;
            return null;
         } else {
            try {
               long var6 = ((Long)zzans.zzahP.get()).longValue();
               this.wait(var6);
            } catch (InterruptedException var9) {
               this.zzagk.zzbr("Wait for service connect was interrupted");
            }

            this.zzagm = false;
            zzany var11 = this.zzagl;
            this.zzagl = null;
            if (var11 == null) {
               this.zzagk.zzbs("Successfully bound to service but never got onServiceConnected callback");
            }

            return var11;
         }
      }
   }

   public final void onServiceConnected(ComponentName var1, IBinder var2) {
      zzbo.zzcz("AnalyticsServiceConnection.onServiceConnected");
      synchronized(this) {
         try {
            if (var2 != null) {
               Object var4 = null;

               try {
                  String var5 = var2.getInterfaceDescriptor();
                  if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(var5)) {
                     IInterface var9;
                     var4 = var2 == null ? null : ((var9 = var2.queryLocalInterface("com.google.android.gms.analytics.internal.IAnalyticsService")) instanceof zzany ? (zzany)var9 : new zzanz(var2));
                     this.zzagk.zzbo("Bound to IAnalyticsService interface");
                  } else {
                     this.zzagk.zze("Got binder with a wrong descriptor", var5);
                  }
               } catch (RemoteException var16) {
                  this.zzagk.zzbs("Service connect failed to get IAnalyticsService");
               }

               if (var4 == null) {
                  try {
                     zza.zzrU();
                     Context var10000 = this.zzagk.getContext();
                     zzamp var10 = zzamn.zza(this.zzagk);
                     var10000.unbindService(var10);
                  } catch (IllegalArgumentException var15) {
                     ;
                  }

                  return;
               } else {
                  if (!this.zzagm) {
                     this.zzagk.zzbr("onServiceConnected received after the timeout limit");
                     this.zzagk.zzkt().zzf(new zzamq(this, (zzany)var4));
                  } else {
                     this.zzagl = (zzany)var4;
                  }

                  return;
               }
            }

            this.zzagk.zzbs("Service connected with null binder");
         } finally {
            this.notifyAll();
         }

      }
   }

   public final void onServiceDisconnected(ComponentName var1) {
      zzbo.zzcz("AnalyticsServiceConnection.onServiceDisconnected");
      this.zzagk.zzkt().zzf(new zzamr(this, var1));
   }
}
