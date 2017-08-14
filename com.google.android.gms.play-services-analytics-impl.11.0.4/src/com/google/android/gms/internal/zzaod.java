package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.internal.zzbo;

class zzaod extends BroadcastReceiver {
   private static String zzaiq = zzaod.class.getName();
   private final zzamj zzafJ;
   private boolean mRegistered;
   private boolean zzair;

   zzaod(zzamj var1) {
      zzbo.zzu(var1);
      this.zzafJ = var1;
   }

   public void onReceive(Context var1, Intent var2) {
      this.zzlO();
      String var3 = var2.getAction();
      this.zzafJ.zzkr().zza("NetworkBroadcastReceiver received action", var3);
      zzaly var5;
      if ("android.net.conn.CONNECTIVITY_CHANGE".equals(var3)) {
         boolean var4 = this.zzlQ();
         if (this.zzair != var4) {
            this.zzair = var4;
            (var5 = this.zzafJ.zzkv()).zza("Network connectivity status changed", var4);
            var5.zzkt().zzf(new zzama(var5, var4));
         }

      } else if ("com.google.analytics.RADIO_POWERED".equals(var3)) {
         if (!var2.hasExtra(zzaiq)) {
            (var5 = this.zzafJ.zzkv()).zzbo("Radio powered up");
            var5.zzkl();
         }

      } else {
         this.zzafJ.zzkr().zzd("NetworkBroadcastReceiver received unknown action", var3);
      }
   }

   public final void zzlN() {
      this.zzlO();
      if (!this.mRegistered) {
         Context var1 = this.zzafJ.getContext();
         IntentFilter var2 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
         var1.registerReceiver(this, var2);
         String var3 = "com.google.analytics.RADIO_POWERED";
         IntentFilter var4;
         (var4 = new IntentFilter(var3)).addCategory(var1.getPackageName());
         var1.registerReceiver(this, var4);
         this.zzair = this.zzlQ();
         this.zzafJ.zzkr().zza("Registering connectivity change receiver. Network connected", this.zzair);
         this.mRegistered = true;
      }
   }

   private final void zzlO() {
      this.zzafJ.zzkr();
      this.zzafJ.zzkv();
   }

   public final void unregister() {
      if (this.mRegistered) {
         this.zzafJ.zzkr().zzbo("Unregistering connectivity change receiver");
         this.mRegistered = false;
         this.zzair = false;
         Context var1 = this.zzafJ.getContext();

         try {
            var1.unregisterReceiver(this);
         } catch (IllegalArgumentException var3) {
            this.zzafJ.zzkr().zze("Failed to unregister the network broadcast receiver", var3);
         }
      }
   }

   public final void zzlP() {
      Context var1 = this.zzafJ.getContext();
      Intent var2;
      (var2 = new Intent("com.google.analytics.RADIO_POWERED")).addCategory(var1.getPackageName());
      var2.putExtra(zzaiq, true);
      var1.sendOrderedBroadcast(var2, (String)null);
   }

   public final boolean isConnected() {
      if (!this.mRegistered) {
         this.zzafJ.zzkr().zzbr("Connectivity unknown. Receiver not registered");
      }

      return this.zzair;
   }

   private final boolean zzlQ() {
      ConnectivityManager var1 = (ConnectivityManager)this.zzafJ.getContext().getSystemService("connectivity");

      try {
         NetworkInfo var2;
         return (var2 = var1.getActiveNetworkInfo()) != null && var2.isConnected();
      } catch (SecurityException var3) {
         return false;
      }
   }
}
