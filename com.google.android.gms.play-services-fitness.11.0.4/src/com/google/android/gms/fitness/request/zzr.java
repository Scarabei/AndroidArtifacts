package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbwb;
import com.google.android.gms.internal.zzbwc;
import java.util.Arrays;

public final class zzr extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final String mName;
   private final zzbwb zzaWM;
   public static final Creator CREATOR = new zzs();

   zzr(int var1, String var2, IBinder var3) {
      this.zzaku = var1;
      this.mName = var2;
      this.zzaWM = zzbwc.zzR(var3);
   }

   public zzr(String var1, zzbwb var2) {
      this.zzaku = 3;
      this.mName = var1;
      this.zzaWM = var2;
   }

   public final boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof zzr) {
            zzr var2 = (zzr)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.mName, var2.mName)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.mName});
   }

   public final String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("name", this.mName).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.mName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaWM.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
