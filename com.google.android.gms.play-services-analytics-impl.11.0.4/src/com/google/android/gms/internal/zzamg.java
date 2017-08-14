package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzl;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;

public class zzamg {
   private final zzamj zzafJ;

   protected zzamg(zzamj var1) {
      zzbo.zzu(var1);
      this.zzafJ = var1;
   }

   public final zzamj zzkp() {
      return this.zzafJ;
   }

   protected final zze zzkq() {
      return this.zzafJ.zzkq();
   }

   protected final Context getContext() {
      return this.zzafJ.getContext();
   }

   protected final zzaoc zzkr() {
      return this.zzafJ.zzkr();
   }

   protected final zzank zzks() {
      return this.zzafJ.zzks();
   }

   protected final zzl zzkt() {
      return this.zzafJ.zzkt();
   }

   public final GoogleAnalytics zzku() {
      return this.zzafJ.zzkG();
   }

   protected final zzaly zzkv() {
      return this.zzafJ.zzkv();
   }

   protected final zzanp zzkw() {
      return this.zzafJ.zzkw();
   }

   protected final zzaot zzkx() {
      return this.zzafJ.zzkx();
   }

   protected final zzaog zzky() {
      return this.zzafJ.zzky();
   }

   protected final zzanb zzkz() {
      return this.zzafJ.zzkJ();
   }

   protected final zzalx zzkA() {
      return this.zzafJ.zzkI();
   }

   protected final zzamu zzkB() {
      return this.zzafJ.zzkB();
   }

   protected final zzano zzkC() {
      return this.zzafJ.zzkC();
   }

   public final void zzbo(String var1) {
      this.zza(2, var1, (Object)null, (Object)null, (Object)null);
   }

   public final void zza(String var1, Object var2) {
      this.zza(2, var1, var2, (Object)null, (Object)null);
   }

   public final void zza(String var1, Object var2, Object var3) {
      this.zza(2, var1, var2, var3, (Object)null);
   }

   public final void zzbp(String var1) {
      this.zza(3, var1, (Object)null, (Object)null, (Object)null);
   }

   public final void zzb(String var1, Object var2) {
      this.zza(3, var1, var2, (Object)null, (Object)null);
   }

   public final void zzb(String var1, Object var2, Object var3) {
      this.zza(3, var1, var2, var3, (Object)null);
   }

   public final void zza(String var1, Object var2, Object var3, Object var4) {
      this.zza(3, var1, var2, var3, var4);
   }

   public final void zzbq(String var1) {
      this.zza(4, var1, (Object)null, (Object)null, (Object)null);
   }

   public final void zzc(String var1, Object var2) {
      this.zza(4, var1, var2, (Object)null, (Object)null);
   }

   public final void zzbr(String var1) {
      this.zza(5, var1, (Object)null, (Object)null, (Object)null);
   }

   public final void zzd(String var1, Object var2) {
      this.zza(5, var1, var2, (Object)null, (Object)null);
   }

   public final void zzc(String var1, Object var2, Object var3) {
      this.zza(5, var1, var2, var3, (Object)null);
   }

   public final void zzb(String var1, Object var2, Object var3, Object var4) {
      this.zza(5, var1, var2, var3, var4);
   }

   public final void zzbs(String var1) {
      this.zza(6, var1, (Object)null, (Object)null, (Object)null);
   }

   public final void zze(String var1, Object var2) {
      this.zza(6, var1, var2, (Object)null, (Object)null);
   }

   public final void zzd(String var1, Object var2, Object var3) {
      this.zza(6, var1, var2, var3, (Object)null);
   }

   public static boolean zzhM() {
      return Log.isLoggable((String)zzans.zzahg.get(), 2);
   }

   private final void zza(int var1, String var2, Object var3, Object var4, Object var5) {
      zzaoc var6 = null;
      if (this.zzafJ != null) {
         var6 = this.zzafJ.zzkF();
      }

      if (var6 != null) {
         String var15;
         if (Log.isLoggable(var15 = (String)zzans.zzahg.get(), var1)) {
            String var16 = zzaoc.zzc(var2, var3, var4, var5);
            Log.println(var1, var15, var16);
         }

         if (var1 >= 5) {
            var6.zzb(var1, var2, var3, var4, var5);
         }

      } else {
         String var7;
         if (Log.isLoggable(var7 = (String)zzans.zzahg.get(), var1)) {
            String var8 = zzc(var2, var3, var4, var5);
            Log.println(var1, var7, var8);
         }

      }
   }

   protected static String zzc(String var0, Object var1, Object var2, Object var3) {
      if (var0 == null) {
         var0 = "";
      }

      String var4 = zzi(var1);
      String var5 = zzi(var2);
      String var6 = zzi(var3);
      StringBuilder var7 = new StringBuilder();
      String var8 = "";
      if (!TextUtils.isEmpty(var0)) {
         var7.append(var0);
         var8 = ": ";
      }

      if (!TextUtils.isEmpty(var4)) {
         var7.append(var8);
         var7.append(var4);
         var8 = ", ";
      }

      if (!TextUtils.isEmpty(var5)) {
         var7.append(var8);
         var7.append(var5);
         var8 = ", ";
      }

      if (!TextUtils.isEmpty(var6)) {
         var7.append(var8);
         var7.append(var6);
      }

      return var7.toString();
   }

   private static String zzi(Object var0) {
      if (var0 == null) {
         return "";
      } else if (var0 instanceof String) {
         return (String)var0;
      } else if (var0 instanceof Boolean) {
         return var0 == Boolean.TRUE ? "true" : "false";
      } else {
         return var0 instanceof Throwable ? ((Throwable)var0).toString() : var0.toString();
      }
   }
}
