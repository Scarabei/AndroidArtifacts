package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzc implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new SnapshotEntity[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      SnapshotMetadataEntity var4 = null;
      zza var5 = null;

      while(var2.dataPosition() < var3) {
         int var6;
         switch((var6 = var2.readInt()) & 65535) {
         case 1:
            var4 = (SnapshotMetadataEntity)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var6, SnapshotMetadataEntity.CREATOR);
            break;
         case 3:
            var5 = (zza)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var6, zza.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var6);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new SnapshotEntity(var4, var5);
   }
}
