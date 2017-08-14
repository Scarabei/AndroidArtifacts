package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Collections;
import java.util.List;

public final class zzal extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzao();
   private List zzbkN;
   private List zzbkO;

   zzal(List var1, List var2) {
      this.zzbkN = Collections.unmodifiableList(var1);
      this.zzbkO = Collections.unmodifiableList(var2);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbkN, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbkO, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
