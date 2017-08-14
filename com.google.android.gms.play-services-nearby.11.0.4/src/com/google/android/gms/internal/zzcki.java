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

public final class zzcki extends zza {
   public static final Creator CREATOR = new zzckj();
   @Nullable
   private final zzcni zzbwE;
   @Nullable
   private final zzcmp zzbwF;
   private final String zzbwG;
   @Nullable
   private final byte[] zzbwH;
   @Nullable
   private final zzcnf zzbwI;

   public zzcki(@Nullable IBinder var1, @Nullable IBinder var2, String var3, @Nullable byte[] var4, @Nullable IBinder var5) {
      IInterface var7;
      this((zzcni)(var1 == null ? null : ((var7 = var1.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener")) instanceof zzcni ? (zzcni)var7 : new zzcnk(var1))), (zzcmp)(var2 == null ? null : ((var7 = var2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionEventListener")) instanceof zzcmp ? (zzcmp)var7 : new zzcmr(var2))), var3, var4, (zzcnf)(var5 == null ? null : ((var7 = var5.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IPayloadListener")) instanceof zzcnf ? (zzcnf)var7 : new zzcnh(var5))));
   }

   private zzcki(@Nullable zzcni var1, @Nullable zzcmp var2, String var3, @Nullable byte[] var4, @Nullable zzcnf var5) {
      this.zzbwE = var1;
      this.zzbwF = var2;
      this.zzbwG = var3;
      this.zzbwH = var4;
      this.zzbwI = var5;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwE, this.zzbwF, this.zzbwG, this.zzbwH, this.zzbwI});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcki) {
         zzcki var2 = (zzcki)var1;
         return zzbe.equal(this.zzbwE, var2.zzbwE) && zzbe.equal(this.zzbwF, var2.zzbwF) && zzbe.equal(this.zzbwG, var2.zzbwG) && zzbe.equal(this.zzbwH, var2.zzbwH) && zzbe.equal(this.zzbwI, var2.zzbwI);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbwE == null ? null : this.zzbwE.asBinder(), false);
      zzd.zza(var1, 2, this.zzbwF == null ? null : this.zzbwF.asBinder(), false);
      zzd.zza(var1, 3, this.zzbwG, false);
      zzd.zza(var1, 4, this.zzbwH, false);
      zzd.zza(var1, 5, this.zzbwI == null ? null : this.zzbwI.asBinder(), false);
      zzd.zzI(var1, var5);
   }
}
