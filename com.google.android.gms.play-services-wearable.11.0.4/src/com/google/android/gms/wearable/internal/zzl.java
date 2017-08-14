package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzl extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzm();
   private int mId;
   private final String mAppId;
   private final String zzbRP;
   private final String zzapS;
   private final String zzaoy;
   private final String zzbRQ;
   private final String zzalP;
   private final byte zzbRR;
   private final byte zzbRS;
   private final byte zzbRT;
   private final byte zzbRU;
   private final String mPackageName;

   public zzl(int var1, String var2, String var3, String var4, String var5, String var6, String var7, byte var8, byte var9, byte var10, byte var11, String var12) {
      this.mId = var1;
      this.mAppId = var2;
      this.zzbRP = var3;
      this.zzapS = var4;
      this.zzaoy = var5;
      this.zzbRQ = var6;
      this.zzalP = var7;
      this.zzbRR = var8;
      this.zzbRS = var9;
      this.zzbRT = var10;
      this.zzbRU = var11;
      this.mPackageName = var12;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.mId);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.mAppId, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbRP, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzapS, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaoy, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbRQ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzalP == null ? this.mAppId : this.zzalP, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbRR);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzbRS);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzbRT);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzbRU);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.mPackageName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      int var1 = this.mId;
      String var2 = this.mAppId;
      String var3 = this.zzbRP;
      String var4 = this.zzapS;
      String var5 = this.zzaoy;
      String var6 = this.zzbRQ;
      String var7 = this.zzalP;
      byte var8 = this.zzbRR;
      byte var9 = this.zzbRS;
      byte var10 = this.zzbRT;
      byte var11 = this.zzbRU;
      String var12 = this.mPackageName;
      return (new StringBuilder(211 + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length() + String.valueOf(var5).length() + String.valueOf(var6).length() + String.valueOf(var7).length() + String.valueOf(var12).length())).append("AncsNotificationParcelable{, id=").append(var1).append(", appId='").append(var2).append("', dateTime='").append(var3).append("', notificationText='").append(var4).append("', title='").append(var5).append("', subtitle='").append(var6).append("', displayName='").append(var7).append("', eventId=").append(var8).append(", eventFlags=").append(var9).append(", categoryId=").append(var10).append(", categoryCount=").append(var11).append(", packageName='").append(var12).append("'}").toString();
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         zzl var2 = (zzl)var1;
         if (this.mId != var2.mId) {
            return false;
         } else if (this.zzbRR != var2.zzbRR) {
            return false;
         } else if (this.zzbRS != var2.zzbRS) {
            return false;
         } else if (this.zzbRT != var2.zzbRT) {
            return false;
         } else if (this.zzbRU != var2.zzbRU) {
            return false;
         } else if (!this.mAppId.equals(var2.mAppId)) {
            return false;
         } else {
            if (this.zzbRP != null) {
               if (!this.zzbRP.equals(var2.zzbRP)) {
                  return false;
               }
            } else if (var2.zzbRP != null) {
               return false;
            }

            if (!this.zzapS.equals(var2.zzapS)) {
               return false;
            } else if (!this.zzaoy.equals(var2.zzaoy)) {
               return false;
            } else if (!this.zzbRQ.equals(var2.zzbRQ)) {
               return false;
            } else {
               if (this.zzalP != null) {
                  if (!this.zzalP.equals(var2.zzalP)) {
                     return false;
                  }
               } else if (var2.zzalP != null) {
                  return false;
               }

               if (this.mPackageName != null) {
                  return this.mPackageName.equals(var2.mPackageName);
               } else if (var2.mPackageName == null) {
                  return true;
               } else {
                  return false;
               }
            }
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      int var1;
      return (((((((((((var1 = 31 + this.mId) * 31 + this.mAppId.hashCode()) * 31 + (this.zzbRP != null ? this.zzbRP.hashCode() : 0)) * 31 + this.zzapS.hashCode()) * 31 + this.zzaoy.hashCode()) * 31 + this.zzbRQ.hashCode()) * 31 + (this.zzalP != null ? this.zzalP.hashCode() : 0)) * 31 + this.zzbRR) * 31 + this.zzbRS) * 31 + this.zzbRT) * 31 + this.zzbRU) * 31 + (this.mPackageName != null ? this.mPackageName.hashCode() : 0);
   }
}
