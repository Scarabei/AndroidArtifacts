package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

@zzzn
public abstract class zzme {
   private final int zzBM;
   private final String zzBN;
   private final Object zzBO;

   private zzme(int var1, String var2, Object var3) {
      this.zzBM = var1;
      this.zzBN = var2;
      this.zzBO = var3;
      com.google.android.gms.ads.internal.zzbs.zzbK().zza(this);
   }

   public final String getKey() {
      return this.zzBN;
   }

   public final Object zzdI() {
      return this.zzBO;
   }

   public static zzme zza(int var0, String var1, Boolean var2) {
      return new zzmf(var0, var1, var2);
   }

   public static zzme zza(int var0, String var1, int var2) {
      return new zzmg(var0, var1, var2);
   }

   public static zzme zza(int var0, String var1, long var2) {
      return new zzmh(var0, var1, var2);
   }

   public static zzme zza(int var0, String var1, float var2) {
      return new zzmi(var0, var1, 0.0F);
   }

   public static zzme zza(int var0, String var1, String var2) {
      return new zzmj(var0, var1, var2);
   }

   public static zzme zza(int var0, String var1) {
      zzme var2 = zza(var0, var1, (String)null);
      com.google.android.gms.ads.internal.zzbs.zzbK().zzb(var2);
      return var2;
   }

   public static zzme zzb(int var0, String var1) {
      zzme var2 = zza(var0, var1, (String)null);
      com.google.android.gms.ads.internal.zzbs.zzbK().zzc(var2);
      return var2;
   }

   public final int getSource() {
      return this.zzBM;
   }

   protected abstract Object zza(SharedPreferences var1);

   protected abstract Object zzb(JSONObject var1);

   public abstract void zza(Editor var1, Object var2);

   // $FF: synthetic method
   zzme(int var1, String var2, Object var3, zzmf var4) {
      this(var1, var2, var3);
   }
}
