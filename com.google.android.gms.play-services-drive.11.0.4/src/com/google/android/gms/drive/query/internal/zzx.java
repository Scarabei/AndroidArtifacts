package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzx extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzy();
   public static final zzx zzaRu = new zzx("=");
   public static final zzx zzaRv = new zzx("<");
   public static final zzx zzaRw = new zzx("<=");
   public static final zzx zzaRx = new zzx(">");
   public static final zzx zzaRy = new zzx(">=");
   public static final zzx zzaRz = new zzx("and");
   public static final zzx zzaRA = new zzx("or");
   private static zzx zzaRB = new zzx("not");
   public static final zzx zzaRC = new zzx("contains");
   private String mTag;

   zzx(String var1) {
      this.mTag = var1;
   }

   public final String getTag() {
      return this.mTag;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.mTag, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return 31 + (this.mTag == null ? 0 : this.mTag.hashCode());
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         zzx var2 = (zzx)var1;
         if (this.mTag == null) {
            if (var2.mTag != null) {
               return false;
            }
         } else if (!this.mTag.equals(var2.mTag)) {
            return false;
         }

         return true;
      }
   }
}
