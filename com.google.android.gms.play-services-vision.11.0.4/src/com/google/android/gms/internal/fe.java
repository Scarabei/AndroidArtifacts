package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class fe extends zza {
   public static final Creator CREATOR = new ff();
   public final int left;
   public final int top;
   public final int width;
   public final int height;
   public final float zzbNW;

   public fe(int var1, int var2, int var3, int var4, float var5) {
      this.left = var1;
      this.top = var2;
      this.width = var3;
      this.height = var4;
      this.zzbNW = var5;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.left);
      zzd.zzc(var1, 3, this.top);
      zzd.zzc(var1, 4, this.width);
      zzd.zzc(var1, 5, this.height);
      zzd.zza(var1, 6, this.zzbNW);
      zzd.zzI(var1, var5);
   }
}
