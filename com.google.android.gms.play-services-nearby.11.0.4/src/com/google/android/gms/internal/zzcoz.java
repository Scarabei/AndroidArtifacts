package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import java.util.Arrays;

public final class zzcoz extends zza {
   public static final Creator CREATOR = new zzcpa();
   @Nullable
   private final zzcni zzbwE;
   @Nullable
   private final zzcmy zzbxO;
   private final String zzbwr;
   private final long durationMillis;
   private final DiscoveryOptions zzbxP;
   @Nullable
   private final zzcna zzbxQ;

   public zzcoz(@Nullable IBinder var1, @Nullable IBinder var2, String var3, long var4, DiscoveryOptions var6, @Nullable IBinder var7) {
      IInterface var9;
      this((zzcni)(var1 == null ? null : ((var9 = var1.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener")) instanceof zzcni ? (zzcni)var9 : new zzcnk(var1))), (zzcmy)(var2 == null ? null : ((var9 = var2.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IDiscoveryCallback")) instanceof zzcmy ? (zzcmy)var9 : new zzcmz(var2))), var3, var4, var6, (zzcna)(var7 == null ? null : ((var9 = var7.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IDiscoveryListener")) instanceof zzcna ? (zzcna)var9 : new zzcnc(var7))));
   }

   private zzcoz(@Nullable zzcni var1, @Nullable zzcmy var2, String var3, long var4, DiscoveryOptions var6, @Nullable zzcna var7) {
      this.zzbwE = var1;
      this.zzbxO = var2;
      this.zzbwr = var3;
      this.durationMillis = var4;
      this.zzbxP = var6;
      this.zzbxQ = var7;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwE, this.zzbxO, this.zzbwr, this.durationMillis, this.zzbxP, this.zzbxQ});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcoz) {
         zzcoz var2 = (zzcoz)var1;
         return zzbe.equal(this.zzbwE, var2.zzbwE) && zzbe.equal(this.zzbxO, var2.zzbxO) && zzbe.equal(this.zzbwr, var2.zzbwr) && zzbe.equal(this.durationMillis, var2.durationMillis) && zzbe.equal(this.zzbxP, var2.zzbxP) && zzbe.equal(this.zzbxQ, var2.zzbxQ);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbwE == null ? null : this.zzbwE.asBinder(), false);
      zzd.zza(var1, 2, this.zzbxO == null ? null : this.zzbxO.asBinder(), false);
      zzd.zza(var1, 3, this.zzbwr, false);
      zzd.zza(var1, 4, this.durationMillis);
      zzd.zza(var1, 5, this.zzbxP, var2, false);
      zzd.zza(var1, 6, this.zzbxQ == null ? null : this.zzbxQ.asBinder(), false);
      zzd.zzI(var1, var5);
   }
}
