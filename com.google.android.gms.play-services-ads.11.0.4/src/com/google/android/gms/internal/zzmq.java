package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build.VERSION;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

@zzzn
public final class zzmq {
   private boolean zzGI;
   private String zzGJ;
   private Map zzGK;
   private Context mContext = null;
   private String zzwH = null;

   public zzmq(Context var1, String var2) {
      this.mContext = var1;
      this.zzwH = var2;
      zzme var5 = zzmo.zzCQ;
      this.zzGI = ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5)).booleanValue();
      var5 = zzmo.zzCR;
      this.zzGJ = (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5);
      this.zzGK = new LinkedHashMap();
      this.zzGK.put("s", "gmob_sdk");
      this.zzGK.put("v", "3");
      this.zzGK.put("os", VERSION.RELEASE);
      this.zzGK.put("sdk", VERSION.SDK);
      Map var10000 = this.zzGK;
      com.google.android.gms.ads.internal.zzbs.zzbz();
      var10000.put("device", zzagz.zzhQ());
      this.zzGK.put("app", var1.getApplicationContext() != null ? var1.getApplicationContext().getPackageName() : var1.getPackageName());
      var10000 = this.zzGK;
      com.google.android.gms.ads.internal.zzbs.zzbz();
      var10000.put("is_lite_sdk", zzagz.zzO(var1) ? "1" : "0");
      Future var3 = com.google.android.gms.ads.internal.zzbs.zzbI().zzn(this.mContext);

      try {
         var3.get();
         this.zzGK.put("network_coarse", Integer.toString(((zzacb)var3.get()).zzVS));
         this.zzGK.put("network_fine", Integer.toString(((zzacb)var3.get()).zzVT));
      } catch (Exception var6) {
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var6, (String)"CsiConfiguration.CsiConfiguration");
      }
   }

   final boolean zzdL() {
      return this.zzGI;
   }

   final String zzdM() {
      return this.zzGJ;
   }

   final Context getContext() {
      return this.mContext;
   }

   final String zzck() {
      return this.zzwH;
   }

   final Map zzdN() {
      return this.zzGK;
   }
}
