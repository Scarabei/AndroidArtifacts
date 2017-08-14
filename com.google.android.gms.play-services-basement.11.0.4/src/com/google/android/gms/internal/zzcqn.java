package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zzcqn extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzcqt();
   private static byte[][] zzazi = new byte[0][];
   private static zzcqn zzbAc;
   private String zzbAd;
   private byte[] zzbAe;
   private byte[][] zzbAf;
   private byte[][] zzbAg;
   private byte[][] zzbAh;
   private byte[][] zzbAi;
   private int[] zzbAj;
   private byte[][] zzbAk;
   private static final zzcqs zzbAl;
   private static final zzcqs zzbAm;
   private static final zzcqs zzbAn;
   private static final zzcqs zzbAo;

   public zzcqn(String var1, byte[] var2, byte[][] var3, byte[][] var4, byte[][] var5, byte[][] var6, int[] var7, byte[][] var8) {
      this.zzbAd = var1;
      this.zzbAe = var2;
      this.zzbAf = var3;
      this.zzbAg = var4;
      this.zzbAh = var5;
      this.zzbAi = var6;
      this.zzbAj = var7;
      this.zzbAk = var8;
   }

   public final String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder("ExperimentTokens")).append("(");
      String var10001;
      if (this.zzbAd == null) {
         var10001 = "null";
      } else {
         String var2 = String.valueOf("'");
         String var3 = this.zzbAd;
         String var4 = String.valueOf("'");
         var10001 = (new StringBuilder(String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length())).append(var2).append(var3).append(var4).toString();
      }

      var1.append(var10001);
      var1.append(", ");
      byte[] var7 = this.zzbAe;
      String var6 = "direct";
      var1.append(var6);
      var1.append("=");
      if (var7 == null) {
         var1.append("null");
      } else {
         var1.append("'");
         var1.append(Base64.encodeToString(var7, 3));
         var1.append("'");
      }

      var1.append(", ");
      zza(var1, "GAIA", this.zzbAf);
      var1.append(", ");
      zza(var1, "PSEUDO", this.zzbAg);
      var1.append(", ");
      zza(var1, "ALWAYS", this.zzbAh);
      var1.append(", ");
      zza(var1, "OTHER", this.zzbAi);
      var1.append(", ");
      zza(var1, "weak", this.zzbAj);
      var1.append(", ");
      zza(var1, "directs", this.zzbAk);
      var1.append(")");
      return var1.toString();
   }

   private static void zza(StringBuilder var0, String var1, byte[][] var2) {
      var0.append(var1);
      var0.append("=");
      if (var2 == null) {
         var0.append("null");
      } else {
         boolean var3 = true;
         var0.append("(");
         byte[][] var4 = var2;
         int var5 = var2.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            byte[] var7 = var4[var6];
            if (!var3) {
               var0.append(", ");
            }

            var3 = false;
            var0.append("'");
            var0.append(Base64.encodeToString(var7, 3));
            var0.append("'");
         }

         var0.append(")");
      }
   }

   private static void zza(StringBuilder var0, String var1, int[] var2) {
      var0.append(var1);
      var0.append("=");
      if (var2 == null) {
         var0.append("null");
      } else {
         boolean var3 = true;
         var0.append("(");
         int[] var4 = var2;
         int var5 = var2.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            int var7 = var4[var6];
            if (!var3) {
               var0.append(", ");
            }

            var3 = false;
            var0.append(var7);
         }

         var0.append(")");
      }
   }

   public final boolean equals(Object var1) {
      if (var1 instanceof zzcqn) {
         zzcqn var2 = (zzcqn)var1;
         return zzcqu.equals(this.zzbAd, var2.zzbAd) && Arrays.equals(this.zzbAe, var2.zzbAe) && zzcqu.equals(zzb(this.zzbAf), zzb(var2.zzbAf)) && zzcqu.equals(zzb(this.zzbAg), zzb(var2.zzbAg)) && zzcqu.equals(zzb(this.zzbAh), zzb(var2.zzbAh)) && zzcqu.equals(zzb(this.zzbAi), zzb(var2.zzbAi)) && zzcqu.equals(zzc(this.zzbAj), zzc(var2.zzbAj)) && zzcqu.equals(zzb(this.zzbAk), zzb(var2.zzbAk));
      } else {
         return false;
      }
   }

   private static List zzb(byte[][] var0) {
      if (var0 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var1 = new ArrayList(var0.length);
         byte[][] var2 = var0;
         int var3 = var0.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            byte[] var5 = var2[var4];
            var1.add(Base64.encodeToString(var5, 3));
         }

         Collections.sort(var1);
         return var1;
      }
   }

   private static List zzc(int[] var0) {
      if (var0 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var1 = new ArrayList(var0.length);
         int[] var2 = var0;
         int var3 = var0.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            int var5 = var2[var4];
            var1.add(var5);
         }

         Collections.sort(var1);
         return var1;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, (String)this.zzbAd, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, (byte[])this.zzbAe, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, (byte[][])this.zzbAf, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, (byte[][])this.zzbAg, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, (byte[][])this.zzbAh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, (byte[][])this.zzbAi, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, (int[])this.zzbAj, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, (byte[][])this.zzbAk, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static {
      zzbAc = new zzcqn("", (byte[])null, zzazi, zzazi, zzazi, zzazi, (int[])null, (byte[][])null);
      zzbAl = new zzcqo();
      zzbAm = new zzcqp();
      zzbAn = new zzcqq();
      zzbAo = new zzcqr();
   }
}
