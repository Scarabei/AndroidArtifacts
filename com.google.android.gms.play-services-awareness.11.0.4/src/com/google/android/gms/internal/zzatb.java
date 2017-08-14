package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.ArrayList;
import java.util.List;

public final class zzatb extends zza {
   public static final Creator CREATOR = new zzatd();
   private final ArrayList zzanX;

   zzatb(ArrayList var1) {
      this.zzanX = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzanX, false);
      zzd.zzI(var1, var5);
   }

   public final List getPlaceLikelihoods() {
      return this.zzanX;
   }
}
