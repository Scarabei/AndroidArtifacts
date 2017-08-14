package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.util.ArrayList;

public final class zzazn {
   private static zzf zzajR = new zzf();
   private static zza zzajS = new zzazo();
   /** @deprecated */
   @Deprecated
   public static final Api API;
   private static final zzcqn[] zzazg;
   private static final String[] zzazh;
   private static final byte[][] zzazi;
   private final String packageName;
   private final int zzazj;
   private String zzazk;
   private int zzazl;
   private String zzazm;
   private String zzazn;
   private final boolean zzazo;
   private int zzazp;
   private final zzazt zzazq;
   private final zze zzazr;
   private zzazs zzazs;
   private final zzazq zzazt;

   public zzazn(Context var1, String var2, String var3) {
      this(var1, -1, var2, (String)null, (String)null, false, zzazw.zzaq(var1), zzi.zzrY(), (zzazs)null, new zzbah(var1));
   }

   public zzazn(Context var1, int var2, String var3, String var4, String var5, boolean var6, zzazt var7, zze var8, zzazs var9, zzazq var10) {
      this.zzazl = -1;
      this.zzazp = 0;
      this.packageName = var1.getPackageName();
      this.zzazj = zzap(var1);
      this.zzazl = -1;
      this.zzazk = var3;
      this.zzazm = null;
      this.zzazn = null;
      this.zzazo = var6;
      this.zzazq = var7;
      this.zzazr = var8;
      this.zzazs = new zzazs();
      this.zzazp = 0;
      this.zzazt = var10;
      if (var6) {
         zzbo.zzb(true, "can't be anonymous with an upload account");
      }

   }

   private static int zzap(Context var0) {
      int var1 = 0;

      try {
         var1 = var0.getPackageManager().getPackageInfo(var0.getPackageName(), 0).versionCode;
      } catch (NameNotFoundException var2) {
         Log.wtf("ClearcutLogger", "This can't happen.");
      }

      return var1;
   }

   public final zzazp zze(byte[] var1) {
      return new zzazp(this, var1, (zzazo)null);
   }

   private static int[] zzb(ArrayList var0) {
      if (var0 == null) {
         return null;
      } else {
         int[] var1 = new int[var0.size()];
         int var2 = 0;
         ArrayList var4;
         int var5 = (var4 = (ArrayList)var0).size();

         int var3;
         for(int var6 = 0; var6 < var5; var1[var2++] = var3) {
            Object var10000 = var4.get(var6);
            ++var6;
            var3 = ((Integer)var10000).intValue();
         }

         return var1;
      }
   }

   // $FF: synthetic method
   static int zza(zzazn var0) {
      return var0.zzazl;
   }

   // $FF: synthetic method
   static String zzb(zzazn var0) {
      return var0.zzazk;
   }

   // $FF: synthetic method
   static String zzc(zzazn var0) {
      return var0.zzazm;
   }

   // $FF: synthetic method
   static zze zzd(zzazn var0) {
      return var0.zzazr;
   }

   // $FF: synthetic method
   static zzazs zze(zzazn var0) {
      return var0.zzazs;
   }

   // $FF: synthetic method
   static boolean zzf(zzazn var0) {
      return var0.zzazo;
   }

   // $FF: synthetic method
   static String zzg(zzazn var0) {
      return var0.packageName;
   }

   // $FF: synthetic method
   static int zzh(zzazn var0) {
      return var0.zzazj;
   }

   // $FF: synthetic method
   static int[] zzc(ArrayList var0) {
      return zzb((ArrayList)null);
   }

   // $FF: synthetic method
   static zzazq zzi(zzazn var0) {
      return var0.zzazt;
   }

   // $FF: synthetic method
   static zzazt zzj(zzazn var0) {
      return var0.zzazq;
   }

   static {
      API = new Api("ClearcutLogger.API", zzajS, zzajR);
      zzazg = new zzcqn[0];
      zzazh = new String[0];
      zzazi = new byte[0][];
   }
}
