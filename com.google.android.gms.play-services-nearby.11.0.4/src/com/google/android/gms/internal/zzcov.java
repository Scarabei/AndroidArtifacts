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

public final class zzcov extends zza {
   public static final Creator CREATOR = new zzcow();
   @Nullable
   private final zzcni zzbwE;
   private final String[] zzbxJ;
   @Nullable
   private final zzcoo zzbxr;
   private final boolean zzbxK;

   public zzcov(@Nullable IBinder var1, String[] var2, @Nullable zzcoo var3, boolean var4) {
      IInterface var6;
      this((zzcni)(var1 == null ? null : ((var6 = var1.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener")) instanceof zzcni ? (zzcni)var6 : new zzcnk(var1))), var2, var3, var4);
   }

   private zzcov(@Nullable zzcni var1, String[] var2, @Nullable zzcoo var3, boolean var4) {
      this.zzbwE = var1;
      this.zzbxJ = var2;
      this.zzbxr = var3;
      this.zzbxK = var4;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwE, this.zzbxJ, this.zzbxr, this.zzbxK});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcov) {
         zzcov var2 = (zzcov)var1;
         return zzbe.equal(this.zzbwE, var2.zzbwE) && Arrays.equals(this.zzbxJ, var2.zzbxJ) && zzbe.equal(this.zzbxr, var2.zzbxr) && zzbe.equal(this.zzbxK, var2.zzbxK);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbwE == null ? null : this.zzbwE.asBinder(), false);
      zzd.zza(var1, 2, this.zzbxJ, false);
      zzd.zza(var1, 3, this.zzbxr, var2, false);
      zzd.zza(var1, 4, this.zzbxK);
      zzd.zzI(var1, var5);
   }
}
