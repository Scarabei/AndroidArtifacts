package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.Arrays;

public final class zzaw extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final PendingIntent mPendingIntent;
   private final zzbxg zzaWo;
   private final int zzaXn;
   public static final Creator CREATOR = new zzax();

   zzaw(int var1, PendingIntent var2, IBinder var3, int var4) {
      this.zzaku = var1;
      this.mPendingIntent = var2;
      this.zzaWo = var3 == null ? null : zzbxh.zzW(var3);
      this.zzaXn = var4;
   }

   public zzaw(PendingIntent var1, zzbxg var2, int var3) {
      this.zzaku = 6;
      this.mPendingIntent = var1;
      this.zzaWo = var2;
      this.zzaXn = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.mPendingIntent, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaXn);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("pendingIntent", this.mPendingIntent).zzg("sessionRegistrationOption", this.zzaXn).toString();
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof zzaw) {
            zzaw var3 = (zzaw)var1;
            if (this.zzaXn == var3.zzaXn && com.google.android.gms.common.internal.zzbe.equal(this.mPendingIntent, var3.mPendingIntent)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.mPendingIntent, this.zzaXn});
   }
}
