package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public final class zzcdr extends zza implements Geofence {
   private final String zzQx;
   private final long zzbjf;
   private final short zzbhH;
   private final double zzbhI;
   private final double zzbhJ;
   private final float zzbhK;
   private final int zzbhF;
   private final int zzbhL;
   private final int zzbhM;
   public static final Creator CREATOR = new zzcds();

   public zzcdr(String var1, int var2, short var3, double var4, double var6, float var8, long var9, int var11, int var12) {
      if (var1 != null && var1.length() <= 100) {
         if (var8 <= 0.0F) {
            throw new IllegalArgumentException((new StringBuilder(31)).append("invalid radius: ").append(var8).toString());
         } else if (var4 <= 90.0D && var4 >= -90.0D) {
            if (var6 <= 180.0D && var6 >= -180.0D) {
               int var14;
               if ((var14 = var2 & 7) == 0) {
                  throw new IllegalArgumentException((new StringBuilder(46)).append("No supported transition specified: ").append(var2).toString());
               } else {
                  this.zzbhH = var3;
                  this.zzQx = var1;
                  this.zzbhI = var4;
                  this.zzbhJ = var6;
                  this.zzbhK = var8;
                  this.zzbjf = var9;
                  this.zzbhF = var14;
                  this.zzbhL = var11;
                  this.zzbhM = var12;
               }
            } else {
               throw new IllegalArgumentException((new StringBuilder(43)).append("invalid longitude: ").append(var6).toString());
            }
         } else {
            throw new IllegalArgumentException((new StringBuilder(42)).append("invalid latitude: ").append(var4).toString());
         }
      } else {
         IllegalArgumentException var10000 = new IllegalArgumentException;
         String var10003 = String.valueOf(var1);
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "requestId is null or too long: ".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("requestId is null or too long: ");
         }

         var10000.<init>(var10002);
         throw var10000;
      }
   }

   public final String getRequestId() {
      return this.zzQx;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getRequestId(), false);
      zzd.zza(var1, 2, this.zzbjf);
      zzd.zza(var1, 3, this.zzbhH);
      zzd.zza(var1, 4, this.zzbhI);
      zzd.zza(var1, 5, this.zzbhJ);
      zzd.zza(var1, 6, this.zzbhK);
      zzd.zzc(var1, 7, this.zzbhF);
      zzd.zzc(var1, 8, this.zzbhL);
      zzd.zzc(var1, 9, this.zzbhM);
      zzd.zzI(var1, var5);
   }

   public static zzcdr zzk(byte[] var0) {
      Parcel var1;
      (var1 = Parcel.obtain()).unmarshall(var0, 0, var0.length);
      var1.setDataPosition(0);
      zzcdr var2 = (zzcdr)CREATOR.createFromParcel(var1);
      var1.recycle();
      return var2;
   }

   public final String toString() {
      Locale var10000 = Locale.US;
      Object[] var10002 = new Object[9];
      String var10005;
      switch(this.zzbhH) {
      case 1:
         var10005 = "CIRCLE";
         break;
      default:
         var10005 = null;
      }

      var10002[0] = var10005;
      var10002[1] = this.zzQx;
      var10002[2] = this.zzbhF;
      var10002[3] = this.zzbhI;
      var10002[4] = this.zzbhJ;
      var10002[5] = this.zzbhK;
      var10002[6] = this.zzbhL / 1000;
      var10002[7] = this.zzbhM;
      var10002[8] = this.zzbjf;
      return String.format(var10000, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", var10002);
   }

   public final int hashCode() {
      long var2 = Double.doubleToLongBits(this.zzbhI);
      int var1 = 31 + (int)(var2 ^ var2 >>> 32);
      var2 = Double.doubleToLongBits(this.zzbhJ);
      return (((var1 * 31 + (int)(var2 ^ var2 >>> 32)) * 31 + Float.floatToIntBits(this.zzbhK)) * 31 + this.zzbhH) * 31 + this.zzbhF;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (!(var1 instanceof zzcdr)) {
         return false;
      } else {
         zzcdr var2 = (zzcdr)var1;
         if (this.zzbhK != var2.zzbhK) {
            return false;
         } else if (this.zzbhI != var2.zzbhI) {
            return false;
         } else if (this.zzbhJ != var2.zzbhJ) {
            return false;
         } else {
            return this.zzbhH == var2.zzbhH;
         }
      }
   }
}
