package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.List;

public final class zzbtd extends zza {
   public static final Creator CREATOR = new zzbte();
   private long zzayS;
   private List zztH;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzayS);
      zzd.zzc(var1, 3, this.zztH, false);
      zzd.zzI(var1, var5);
   }

   public zzbtd(long var1, List var3) {
      this.zzayS = var1;
      this.zztH = var3;
   }
}
