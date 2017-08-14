package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcor extends zza {
   public static final Creator CREATOR = new zzcos();
   @Nullable
   private final zzcni zzbwE;
   private final String zzbwG;

   public zzcor(@Nullable IBinder var1, String var2) {
      IInterface var4;
      this((zzcni)(var1 == null ? null : ((var4 = var1.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener")) instanceof zzcni ? (zzcni)var4 : new zzcnk(var1))), var2);
   }

   private zzcor(@Nullable zzcni var1, String var2) {
      this.zzbwE = var1;
      this.zzbwG = var2;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwE, this.zzbwG});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcor) {
         zzcor var2 = (zzcor)var1;
         return zzbe.equal(this.zzbwE, var2.zzbwE) && zzbe.equal(this.zzbwG, var2.zzbwG);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbwE == null ? null : this.zzbwE.asBinder(), false);
      zzd.zza(var1, 2, this.zzbwG, false);
      zzd.zzI(var1, var5);
   }
}
