package com.google.android.gms.internal;

import java.io.IOException;

public abstract class adp {
   protected volatile int zzcsx = -1;

   public final int zzLU() {
      if (this.zzcsx < 0) {
         this.zzLV();
      }

      return this.zzcsx;
   }

   public final int zzLV() {
      int var1 = this.zzn();
      this.zzcsx = var1;
      return var1;
   }

   protected int zzn() {
      return 0;
   }

   public void zza(adh var1) throws IOException {
   }

   public abstract adp zza(adg var1) throws IOException;

   public static final byte[] zzc(adp var0) {
      byte[] var1 = new byte[var0.zzLV()];
      int var4 = var1.length;
      byte[] var3 = var1;
      adp var2 = var0;

      try {
         adh var5 = adh.zzc(var3, 0, var4);
         var2.zza(var5);
         var5.zzLM();
         return var1;
      } catch (IOException var6) {
         throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", var6);
      }
   }

   public static final adp zza(adp var0, byte[] var1) throws ado {
      return zza(var0, var1, 0, var1.length);
   }

   private static adp zza(adp var0, byte[] var1, int var2, int var3) throws ado {
      try {
         adg var4 = adg.zzb(var1, 0, var3);
         var0.zza(var4);
         var4.zzcl(0);
         return var0;
      } catch (ado var5) {
         throw var5;
      } catch (IOException var6) {
         throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
      }
   }

   public String toString() {
      return adq.zzd(this);
   }

   public adp zzLO() throws CloneNotSupportedException {
      return (adp)super.clone();
   }

   // $FF: synthetic method
   public Object clone() throws CloneNotSupportedException {
      return this.zzLO();
   }
}
