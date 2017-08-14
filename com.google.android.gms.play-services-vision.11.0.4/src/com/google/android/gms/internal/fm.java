package com.google.android.gms.internal;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class fm extends zza {
   public static final Creator CREATOR = new fn();
   public final Rect zzbOh;

   public fm() {
      this.zzbOh = new Rect();
   }

   public fm(Rect var1) {
      this.zzbOh = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzbOh, var2, false);
      zzd.zzI(var1, var5);
   }
}
