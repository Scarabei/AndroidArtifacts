package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import java.util.Arrays;

public final class zzcox extends zza {
   public static final Creator CREATOR = new zzcoy();
   @Nullable
   private final zzcnl zzbxL;
   @Nullable
   private final zzcmm zzbxM;
   private final String name;
   private final String zzbwr;
   private final long durationMillis;
   private final AdvertisingOptions zzbxN;
   @Nullable
   private final zzcms zzbxI;

   public zzcox(@Nullable IBinder var1, @Nullable IBinder var2, String var3, String var4, long var5, AdvertisingOptions var7, @Nullable IBinder var8) {
      IInterface var10;
      this((zzcnl)(var1 == null ? null : ((var10 = var1.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener")) instanceof zzcnl ? (zzcnl)var10 : new zzcnn(var1))), (zzcmm)(var2 == null ? null : ((var10 = var2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IAdvertisingCallback")) instanceof zzcmm ? (zzcmm)var10 : new zzcmo(var2))), var3, var4, var5, var7, (zzcms)(var8 == null ? null : ((var10 = var8.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener")) instanceof zzcms ? (zzcms)var10 : new zzcmu(var8))));
   }

   private zzcox(@Nullable zzcnl var1, @Nullable zzcmm var2, String var3, String var4, long var5, AdvertisingOptions var7, @Nullable zzcms var8) {
      this.zzbxL = var1;
      this.zzbxM = var2;
      this.name = var3;
      this.zzbwr = var4;
      this.durationMillis = var5;
      this.zzbxN = var7;
      this.zzbxI = var8;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbxL, this.zzbxM, this.name, this.zzbwr, this.durationMillis, this.zzbxN, this.zzbxI});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcox) {
         zzcox var2 = (zzcox)var1;
         return zzbe.equal(this.zzbxL, var2.zzbxL) && zzbe.equal(this.zzbxM, var2.zzbxM) && zzbe.equal(this.name, var2.name) && zzbe.equal(this.zzbwr, var2.zzbwr) && zzbe.equal(this.durationMillis, var2.durationMillis) && zzbe.equal(this.zzbxN, var2.zzbxN) && zzbe.equal(this.zzbxI, var2.zzbxI);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbxL == null ? null : this.zzbxL.asBinder(), false);
      zzd.zza(var1, 2, this.zzbxM == null ? null : this.zzbxM.asBinder(), false);
      zzd.zza(var1, 3, this.name, false);
      zzd.zza(var1, 4, this.zzbwr, false);
      zzd.zza(var1, 5, this.durationMillis);
      zzd.zza(var1, 6, this.zzbxN, var2, false);
      zzd.zza(var1, 7, this.zzbxI == null ? null : this.zzbxI.asBinder(), false);
      zzd.zzI(var1, var5);
   }
}
