package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbxd;
import com.google.android.gms.internal.zzbxe;
import java.util.Arrays;

public final class zzba extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final String mName;
   private final String zzaVf;
   private final zzbxd zzaXo;
   public static final Creator CREATOR = new zzbb();

   zzba(int var1, String var2, String var3, IBinder var4) {
      this.zzaku = var1;
      this.mName = var2;
      this.zzaVf = var3;
      this.zzaXo = zzbxe.zzV(var4);
   }

   public zzba(String var1, String var2, zzbxd var3) {
      this.zzaku = 3;
      this.mName = var1;
      this.zzaVf = var2;
      this.zzaXo = var3;
   }

   public final boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof zzba) {
            zzba var3 = (zzba)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.mName, var3.mName) && com.google.android.gms.common.internal.zzbe.equal(this.zzaVf, var3.zzaVf)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.mName, this.zzaVf});
   }

   public final String toString() {
      return com.google.android.gms.common.internal.zzbe.zzt(this).zzg("name", this.mName).zzg("identifier", this.zzaVf).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.mName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaVf, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaXo == null ? null : this.zzaXo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
