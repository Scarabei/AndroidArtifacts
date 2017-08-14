package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public final class zzay extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final Session zzaTe;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzaz();

   zzay(int var1, Session var2, IBinder var3) {
      this.zzaku = var1;
      this.zzaTe = var2;
      this.zzaWo = zzbxh.zzW(var3);
   }

   public zzay(Session var1, zzbxg var2) {
      zzbo.zzb(var1.getStartTime(TimeUnit.MILLISECONDS) < System.currentTimeMillis(), "Cannot start a session in the future");
      zzbo.zzb(var1.isOngoing(), "Cannot start a session which has already ended");
      this.zzaku = 3;
      this.zzaTe = var1;
      this.zzaWo = var2;
   }

   public final boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof zzay) {
            zzay var2 = (zzay)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.zzaTe, var2.zzaTe)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaTe});
   }

   public final String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("session", this.zzaTe).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaTe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
