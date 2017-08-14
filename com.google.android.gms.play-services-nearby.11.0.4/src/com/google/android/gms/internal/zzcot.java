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

public final class zzcot extends zza {
   public static final Creator CREATOR = new zzcou();
   @Nullable
   private final zzcni zzbwE;
   @Nullable
   private final zzcmp zzbwF;
   @Nullable
   private final zzcmv zzbxH;
   @Nullable
   private final String name;
   private final String zzbwG;
   @Nullable
   private final byte[] zzbwH;
   @Nullable
   private final zzcms zzbxI;

   public zzcot(@Nullable IBinder var1, @Nullable IBinder var2, @Nullable IBinder var3, @Nullable String var4, String var5, @Nullable byte[] var6, @Nullable IBinder var7) {
      IInterface var9;
      this((zzcni)(var1 == null ? null : ((var9 = var1.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener")) instanceof zzcni ? (zzcni)var9 : new zzcnk(var1))), (zzcmp)(var2 == null ? null : ((var9 = var2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionEventListener")) instanceof zzcmp ? (zzcmp)var9 : new zzcmr(var2))), (zzcmv)(var3 == null ? null : ((var9 = var3.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionResponseListener")) instanceof zzcmv ? (zzcmv)var9 : new zzcmx(var3))), var4, var5, var6, (zzcms)(var7 == null ? null : ((var9 = var7.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener")) instanceof zzcms ? (zzcms)var9 : new zzcmu(var7))));
   }

   private zzcot(@Nullable zzcni var1, @Nullable zzcmp var2, @Nullable zzcmv var3, @Nullable String var4, String var5, @Nullable byte[] var6, @Nullable zzcms var7) {
      this.zzbwE = var1;
      this.zzbwF = var2;
      this.zzbxH = var3;
      this.name = var4;
      this.zzbwG = var5;
      this.zzbwH = var6;
      this.zzbxI = var7;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwE, this.zzbwF, this.zzbxH, this.name, this.zzbwG, this.zzbwH, this.zzbxI});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcot) {
         zzcot var2 = (zzcot)var1;
         return zzbe.equal(this.zzbwE, var2.zzbwE) && zzbe.equal(this.zzbwF, var2.zzbwF) && zzbe.equal(this.zzbxH, var2.zzbxH) && zzbe.equal(this.name, var2.name) && zzbe.equal(this.zzbwG, var2.zzbwG) && zzbe.equal(this.zzbwH, var2.zzbwH) && zzbe.equal(this.zzbxI, var2.zzbxI);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbwE == null ? null : this.zzbwE.asBinder(), false);
      zzd.zza(var1, 2, this.zzbwF == null ? null : this.zzbwF.asBinder(), false);
      zzd.zza(var1, 3, this.zzbxH == null ? null : this.zzbxH.asBinder(), false);
      zzd.zza(var1, 4, this.name, false);
      zzd.zza(var1, 5, this.zzbwG, false);
      zzd.zza(var1, 6, this.zzbwH, false);
      zzd.zza(var1, 7, this.zzbxI == null ? null : this.zzbxI.asBinder(), false);
      zzd.zzI(var1, var5);
   }
}
