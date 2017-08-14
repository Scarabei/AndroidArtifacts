package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class zzb extends com.google.android.gms.games.internal.zzc implements zza {
   public static final Creator CREATOR = new zzc();
   private final String zzbbU;
   private final String zzbbV;
   private final long zzbbW;
   private final Uri zzbbX;
   private final Uri zzbbY;
   private final Uri zzbbZ;

   public zzb(zza var1) {
      this.zzbbU = var1.zzvf();
      this.zzbbV = var1.zzvg();
      this.zzbbW = var1.zzvh();
      this.zzbbX = var1.zzvi();
      this.zzbbY = var1.zzvj();
      this.zzbbZ = var1.zzvk();
   }

   zzb(String var1, String var2, long var3, Uri var5, Uri var6, Uri var7) {
      this.zzbbU = var1;
      this.zzbbV = var2;
      this.zzbbW = var3;
      this.zzbbX = var5;
      this.zzbbY = var6;
      this.zzbbZ = var7;
   }

   public final String zzvf() {
      return this.zzbbU;
   }

   public final String zzvg() {
      return this.zzbbV;
   }

   public final long zzvh() {
      return this.zzbbW;
   }

   public final Uri zzvi() {
      return this.zzbbX;
   }

   public final Uri zzvj() {
      return this.zzbbY;
   }

   public final Uri zzvk() {
      return this.zzbbZ;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(zza var0) {
      return Arrays.hashCode(new Object[]{var0.zzvf(), var0.zzvg(), var0.zzvh(), var0.zzvi(), var0.zzvj(), var0.zzvk()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(zza var0, Object var1) {
      if (!(var1 instanceof zza)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         zza var2;
         return zzbe.equal((var2 = (zza)var1).zzvf(), var0.zzvf()) && zzbe.equal(var2.zzvg(), var0.zzvg()) && zzbe.equal(var2.zzvh(), var0.zzvh()) && zzbe.equal(var2.zzvi(), var0.zzvi()) && zzbe.equal(var2.zzvj(), var0.zzvj()) && zzbe.equal(var2.zzvk(), var0.zzvk());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(zza var0) {
      return zzbe.zzt(var0).zzg("GameId", var0.zzvf()).zzg("GameName", var0.zzvg()).zzg("ActivityTimestampMillis", var0.zzvh()).zzg("GameIconUri", var0.zzvi()).zzg("GameHiResUri", var0.zzvj()).zzg("GameFeaturedUri", var0.zzvk()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbbU, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbbV, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbbW);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbbX, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbbY, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbbZ, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
