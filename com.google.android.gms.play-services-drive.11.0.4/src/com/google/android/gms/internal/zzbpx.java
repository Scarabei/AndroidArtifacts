package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbpx extends zza {
   public static final Creator CREATOR = new zzbpy();
   private ParcelFileDescriptor zzaPm;
   private IBinder zzaPn;
   private String zzyi;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = var2 | 1;
      int var6 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaPm, var5, false);
      zzd.zza(var1, 3, this.zzaPn, false);
      zzd.zza(var1, 4, this.zzyi, false);
      zzd.zzI(var1, var6);
   }

   zzbpx(ParcelFileDescriptor var1, IBinder var2, String var3) {
      this.zzaPm = var1;
      this.zzaPn = var2;
      this.zzyi = var3;
   }
}
