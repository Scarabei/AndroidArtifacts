package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbxg;
import com.google.android.gms.internal.zzbxh;
import java.util.Arrays;

public final class zzbm extends com.google.android.gms.common.internal.safeparcel.zza {
   private final int zzaku;
   private final DataType zzaUe;
   private final DataSource zzaUd;
   private final zzbxg zzaWo;
   public static final Creator CREATOR = new zzbn();

   zzbm(int var1, DataType var2, DataSource var3, IBinder var4) {
      this.zzaku = var1;
      this.zzaUe = var2;
      this.zzaUd = var3;
      this.zzaWo = zzbxh.zzW(var4);
   }

   public zzbm(DataType var1, DataSource var2, zzbxg var3) {
      this.zzaku = 3;
      this.zzaUe = var1;
      this.zzaUd = var2;
      this.zzaWo = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzaUe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaUd, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaWo == null ? null : this.zzaWo.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof zzbm) {
            zzbm var3 = (zzbm)var1;
            if (com.google.android.gms.common.internal.zzbe.equal(this.zzaUd, var3.zzaUd) && com.google.android.gms.common.internal.zzbe.equal(this.zzaUe, var3.zzaUe)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUd, this.zzaUe});
   }
}
