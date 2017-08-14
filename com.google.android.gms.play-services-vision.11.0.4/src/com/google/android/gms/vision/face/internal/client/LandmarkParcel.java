package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.apps.common.proguard.UsedByNative;

@UsedByNative("wrapper.cc")
public final class LandmarkParcel extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzi();
   private int versionCode;
   public final float x;
   public final float y;
   public final int type;

   public LandmarkParcel(int var1, float var2, float var3, int var4) {
      this.versionCode = var1;
      this.x = var2;
      this.y = var3;
      this.type = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.x);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.y);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.type);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
