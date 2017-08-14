package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbgc extends zza {
   public static final Creator CREATOR = new zzbgd();
   private int zzaku;
   private final zzbge zzaIB;

   zzbgc(int var1, zzbge var2) {
      this.zzaku = var1;
      this.zzaIB = var2;
   }

   private zzbgc(zzbge var1) {
      this.zzaku = 1;
      this.zzaIB = var1;
   }

   public static zzbgc zza(zzbgk var0) {
      if (var0 instanceof zzbge) {
         return new zzbgc((zzbge)var0);
      } else {
         throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
      }
   }

   public final zzbgk zzrK() {
      if (this.zzaIB != null) {
         return this.zzaIB;
      } else {
         throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zza(var1, 2, this.zzaIB, var2, false);
      zzd.zzI(var1, var5);
   }
}
