package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbtr extends zza {
   public static final Creator CREATOR = new zzbts();
   private int mIndex;
   private int zzaSh;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.mIndex);
      zzd.zzc(var1, 3, this.zzaSh);
      zzd.zzI(var1, var5);
   }

   public zzbtr(int var1, int var2) {
      this.mIndex = var1;
      this.zzaSh = var2;
   }
}
