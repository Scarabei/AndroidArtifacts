package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;

public final class zzbkl extends zza {
   public static final Creator CREATOR = new zzbkm();
   private ArrayList zzaLO;

   zzbkl(ArrayList var1) {
      this.zzaLO = var1;
   }

   public zzbkl() {
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 3, this.zzaLO, false);
      zzd.zzI(var1, var5);
   }
}
