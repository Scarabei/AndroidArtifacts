package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.ArrayList;

@zzzn
public final class zzgt {
   private final int zzxW;
   private final int zzxX;
   private final int zzxY;
   private final zzhg zzxZ;
   private final zzhp zzya;
   private final Object mLock = new Object();
   private ArrayList zzyb = new ArrayList();
   private ArrayList zzyc = new ArrayList();
   private ArrayList zzyd = new ArrayList();
   private int zzye = 0;
   private int zzyf = 0;
   private int zzyg = 0;
   private int zzyh;
   private String zzyi = "";
   private String zzyj = "";
   private String zzyk = "";

   public zzgt(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.zzxW = var1;
      this.zzxX = var2;
      this.zzxY = var3;
      this.zzxZ = new zzhg(var4);
      this.zzya = new zzhp(var5, var6, var7);
   }

   public final boolean zzcC() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzyg == 0;
      }
   }

   public final String zzcD() {
      return this.zzyi;
   }

   public final String zzcE() {
      return this.zzyj;
   }

   public final String zzcF() {
      return this.zzyk;
   }

   public final void zzcG() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzyh -= 100;
      }
   }

   public final void zzcH() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         --this.zzyg;
      }
   }

   public final void zzcI() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         ++this.zzyg;
      }
   }

   public final void zza(String var1, boolean var2, float var3, float var4, float var5, float var6) {
      this.zzc(var1, var2, var3, var4, var5, var6);
      Object var7 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzyg < 0) {
            zzafr.zzaC("ActivityContent: negative number of WebViews.");
         }

         this.zzcJ();
      }
   }

   public final void zzb(String var1, boolean var2, float var3, float var4, float var5, float var6) {
      this.zzc(var1, var2, var3, var4, var5, var6);
   }

   private final void zzc(@Nullable String var1, boolean var2, float var3, float var4, float var5, float var6) {
      if (var1 != null && var1.length() >= this.zzxY) {
         Object var7 = this.mLock;
         synchronized(this.mLock) {
            this.zzyb.add(var1);
            this.zzye += var1.length();
            if (var2) {
               this.zzyc.add(var1);
               this.zzyd.add(new zzhe(var3, var4, var5, var6, this.zzyc.size() - 1));
            }

         }
      }
   }

   public final void zzcJ() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         int var6 = this.zzyf;
         int var5 = this.zzye;
         int var2;
         if ((var2 = var5 * this.zzxW + var6 * this.zzxX) > this.zzyh) {
            this.zzyh = var2;
            zzme var4 = zzmo.zzCZ;
            if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue() && !com.google.android.gms.ads.internal.zzbs.zzbD().zzhn()) {
               this.zzyi = this.zzxZ.zza(this.zzyb);
               this.zzyj = this.zzxZ.zza(this.zzyc);
            }

            var4 = zzmo.zzDb;
            if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var4)).booleanValue() && !com.google.android.gms.ads.internal.zzbs.zzbD().zzho()) {
               this.zzyk = this.zzya.zza(this.zzyc, this.zzyd);
            }
         }

      }
   }

   public final int getScore() {
      return this.zzyh;
   }

   public final void zzj(int var1) {
      this.zzyf = var1;
   }

   private static String zza(ArrayList var0, int var1) {
      if (var0.isEmpty()) {
         return "";
      } else {
         StringBuffer var2 = new StringBuffer();
         ArrayList var5;
         int var6 = (var5 = (ArrayList)var0).size();
         int var7 = 0;

         while(var7 < var6) {
            Object var10000 = var5.get(var7);
            ++var7;
            String var4 = (String)var10000;
            var2.append(var4);
            var2.append(' ');
            if (var2.length() > 100) {
               break;
            }
         }

         var2.deleteCharAt(var2.length() - 1);
         String var3;
         return (var3 = var2.toString()).length() < 100 ? var3 : var3.substring(0, 100);
      }
   }

   public final String toString() {
      int var1 = this.zzyf;
      int var2 = this.zzyh;
      int var3 = this.zzye;
      String var4 = String.valueOf(zza(this.zzyb, 100));
      String var5 = String.valueOf(zza(this.zzyc, 100));
      String var6 = this.zzyi;
      String var7 = this.zzyj;
      String var8 = this.zzyk;
      return (new StringBuilder(165 + String.valueOf(var4).length() + String.valueOf(var5).length() + String.valueOf(var6).length() + String.valueOf(var7).length() + String.valueOf(var8).length())).append("ActivityContent fetchId: ").append(var1).append(" score:").append(var2).append(" total_length:").append(var3).append("\n text: ").append(var4).append("\n viewableText").append(var5).append("\n signture: ").append(var6).append("\n viewableSignture: ").append(var7).append("\n viewableSignatureForVertical: ").append(var8).toString();
   }

   final int zzcK() {
      return this.zzye;
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zzgt)) {
         return false;
      } else if (var1 == this) {
         return true;
      } else {
         zzgt var2;
         return (var2 = (zzgt)var1).zzyi != null && var2.zzyi.equals(this.zzyi);
      }
   }

   public final int hashCode() {
      return this.zzyi.hashCode();
   }
}
