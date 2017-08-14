package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

public abstract class zzp implements Comparable {
   private final zzab.zza zzB;
   private final int zzC;
   private final String zzD;
   private final int zzE;
   private final zzu zzF;
   private Integer zzG;
   private zzs zzH;
   private boolean zzI;
   private boolean zzJ;
   private boolean zzK;
   private boolean zzL;
   private zzx zzM;
   private zzc zzN;

   public zzp(int var1, String var2, zzu var3) {
      this.zzB = zzab.zza.zzai ? new zzab.zza() : null;
      this.zzI = true;
      this.zzJ = false;
      this.zzK = false;
      this.zzL = false;
      this.zzN = null;
      this.zzC = var1;
      this.zzD = var2;
      this.zzF = var3;
      this.zzM = new zzg();
      Uri var5;
      String var6;
      this.zzE = !TextUtils.isEmpty(var2) && (var5 = Uri.parse(var2)) != null && (var6 = var5.getHost()) != null ? var6.hashCode() : 0;
   }

   public final int getMethod() {
      return this.zzC;
   }

   public final int zzc() {
      return this.zzE;
   }

   public final void zzb(String var1) {
      if (zzab.zza.zzai) {
         this.zzB.zza(var1, Thread.currentThread().getId());
      }

   }

   final void zzc(String var1) {
      if (this.zzH != null) {
         this.zzH.zzd(this);
      }

      if (zzab.zza.zzai) {
         long var2 = Thread.currentThread().getId();
         if (Looper.myLooper() != Looper.getMainLooper()) {
            (new Handler(Looper.getMainLooper())).post(new zzq(this, var1, var2));
            return;
         }

         this.zzB.zza(var1, var2);
         this.zzB.zzc(this.toString());
      }

   }

   public final zzp zza(zzs var1) {
      this.zzH = var1;
      return this;
   }

   public final zzp zza(int var1) {
      this.zzG = var1;
      return this;
   }

   public final String getUrl() {
      return this.zzD;
   }

   public final String zzd() {
      return this.zzD;
   }

   public final zzp zza(zzc var1) {
      this.zzN = var1;
      return this;
   }

   public final zzc zze() {
      return this.zzN;
   }

   public Map getHeaders() throws zza {
      return Collections.emptyMap();
   }

   public static String zzf() {
      String var10001 = String.valueOf("UTF-8");
      return var10001.length() != 0 ? "application/x-www-form-urlencoded; charset=".concat(var10001) : new String("application/x-www-form-urlencoded; charset=");
   }

   public byte[] zzg() throws zza {
      return null;
   }

   public final boolean zzh() {
      return this.zzI;
   }

   public final int zzi() {
      return this.zzM.zza();
   }

   public final zzx zzj() {
      return this.zzM;
   }

   public final void zzk() {
      this.zzK = true;
   }

   public final boolean zzl() {
      return this.zzK;
   }

   protected abstract zzt zza(zzn var1);

   protected abstract void zza(Object var1);

   public final void zzb(zzaa var1) {
      if (this.zzF != null) {
         this.zzF.zzd(var1);
      }

   }

   public String toString() {
      String var10001 = String.valueOf(Integer.toHexString(this.zzE));
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "0x".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("0x");
      }

      String var1 = var10000;
      String var2 = "[ ] ";
      String var3 = String.valueOf(this.zzD);
      String var4 = String.valueOf(zzr.zzS);
      String var5 = String.valueOf(this.zzG);
      return (new StringBuilder(3 + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var1).length() + String.valueOf(var4).length() + String.valueOf(var5).length())).append(var2).append(var3).append(" ").append(var1).append(" ").append(var4).append(" ").append(var5).toString();
   }

   // $FF: synthetic method
   public int compareTo(Object var1) {
      zzp var3 = (zzp)var1;
      zzr var4 = zzr.zzS;
      zzr var5 = zzr.zzS;
      return var4 == var5 ? this.zzG.intValue() - var3.zzG.intValue() : var5.ordinal() - var4.ordinal();
   }

   // $FF: synthetic method
   static zzab.zza zzb(zzp var0) {
      return var0.zzB;
   }
}
