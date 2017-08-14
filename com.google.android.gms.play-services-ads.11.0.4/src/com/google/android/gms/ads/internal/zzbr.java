package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

final class zzbr {
   private final String zzvg;
   private final Map zzvh;
   private String zzvi;
   private String zzvj;

   public zzbr(String var1) {
      this.zzvg = var1;
      this.zzvh = new TreeMap();
   }

   public final String zzbr() {
      return this.zzvj;
   }

   public final String getQuery() {
      return this.zzvi;
   }

   public final String zzbs() {
      return this.zzvg;
   }

   public final Map zzbt() {
      return this.zzvh;
   }

   public final void zza(zzir var1, zzaje var2) {
      this.zzvi = var1.zzzU.zzBI;
      Bundle var3 = null;
      if (var1.zzzX != null) {
         var3 = var1.zzzX.getBundle(AdMobAdapter.class.getName());
      }

      if (var3 != null) {
         zzme var7 = zzmo.zzFW;
         String var4 = (String)zzbs.zzbL().zzd(var7);
         Iterator var5 = var3.keySet().iterator();

         while(var5.hasNext()) {
            String var6 = (String)var5.next();
            if (var4.equals(var6)) {
               this.zzvj = var3.getString(var6);
            } else if (var6.startsWith("csa_")) {
               this.zzvh.put(var6.substring(4), var3.getString(var6));
            }
         }

         this.zzvh.put("SDKVersion", var2.zzaP);
      }
   }
}
