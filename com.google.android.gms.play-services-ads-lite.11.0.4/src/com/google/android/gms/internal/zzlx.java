package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public final class zzlx extends zza {
   public static final Creator CREATOR = new zzly();
   public final boolean zzBJ;
   public final boolean zzBK;

   public zzlx(VideoOptions var1) {
      this(var1.getStartMuted(), var1.getCustomControlsRequested());
   }

   public zzlx(boolean var1, boolean var2) {
      this.zzBJ = var1;
      this.zzBK = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzBJ);
      zzd.zza(var1, 3, this.zzBK);
      zzd.zzI(var1, var5);
   }
}
