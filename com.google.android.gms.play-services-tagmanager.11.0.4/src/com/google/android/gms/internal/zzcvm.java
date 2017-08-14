package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

class zzcvm extends BroadcastReceiver {
   private static String zzaiq = zzcvm.class.getName();
   private final zzcwc zzbIu;

   zzcvm(zzcwc var1) {
      this.zzbIu = var1;
   }

   public void onReceive(Context var1, Intent var2) {
      String var3 = var2.getAction();
      if ("android.net.conn.CONNECTIVITY_CHANGE".equals(var3)) {
         Bundle var4 = var2.getExtras();
         Boolean var5 = Boolean.FALSE;
         if (var4 != null) {
            var5 = var2.getExtras().getBoolean("noConnectivity");
         }

         this.zzbIu.zzas(!var5.booleanValue());
      } else {
         if ("com.google.analytics.RADIO_POWERED".equals(var3) && !var2.hasExtra(zzaiq)) {
            this.zzbIu.zzBU();
         }

      }
   }

   public static void zzbt(Context var0) {
      Intent var1;
      (var1 = new Intent("com.google.analytics.RADIO_POWERED")).addCategory(var0.getPackageName());
      var1.putExtra(zzaiq, true);
      var0.sendBroadcast(var1);
   }
}
