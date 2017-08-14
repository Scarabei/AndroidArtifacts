package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public final class zzbdk extends BroadcastReceiver {
   private Context mContext;
   private final zzbdl zzaEA;

   public zzbdk(zzbdl var1) {
      this.zzaEA = var1;
   }

   public final void setContext(Context var1) {
      this.mContext = var1;
   }

   public final synchronized void unregister() {
      if (this.mContext != null) {
         this.mContext.unregisterReceiver(this);
      }

      this.mContext = null;
   }

   public final void onReceive(Context var1, Intent var2) {
      Uri var3 = var2.getData();
      String var4 = null;
      if (var3 != null) {
         var4 = var3.getSchemeSpecificPart();
      }

      if ("com.google.android.gms".equals(var4)) {
         this.zzaEA.zzpA();
         this.unregister();
      }

   }
}
