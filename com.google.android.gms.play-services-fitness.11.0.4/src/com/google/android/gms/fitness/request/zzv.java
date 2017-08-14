package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.Arrays;

public final class zzv extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final PendingIntent mPendingIntent;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzw();

   zzv(int var1, PendingIntent var2, IBinder var3) {
      this.zzaku = var1;
      this.mPendingIntent = var2;
      this.zzaWo = zzbxh.zzW(var3);
   }

   public zzv(PendingIntent var1, IBinder var2) {
      this.zzaku = 1;
      this.mPendingIntent = var1;
      this.zzaWo = zzbxh.zzW(var2);
   }

   public final String toString() {
      return "DataUpdateListenerUnregistrationRequest";
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.mPendingIntent, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof zzv) {
            zzv var2 = (zzv)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.mPendingIntent, var2.mPendingIntent)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.mPendingIntent});
   }
}
