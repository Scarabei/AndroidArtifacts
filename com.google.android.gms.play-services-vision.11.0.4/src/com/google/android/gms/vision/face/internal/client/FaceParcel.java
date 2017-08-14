package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.apps.common.proguard.UsedByNative;

@UsedByNative("wrapper.cc")
public class FaceParcel extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private int versionCode;
   public final int id;
   public final float centerX;
   public final float centerY;
   public final float width;
   public final float height;
   public final float zzbNA;
   public final float zzbNB;
   public final LandmarkParcel[] zzbNC;
   public final float zzbND;
   public final float zzbNE;
   public final float zzbNF;

   public FaceParcel(int var1, int var2, float var3, float var4, float var5, float var6, float var7, float var8, LandmarkParcel[] var9, float var10, float var11, float var12) {
      this.versionCode = var1;
      this.id = var2;
      this.centerX = var3;
      this.centerY = var4;
      this.width = var5;
      this.height = var6;
      this.zzbNA = var7;
      this.zzbNB = var8;
      this.zzbNC = var9;
      this.zzbND = var10;
      this.zzbNE = var11;
      this.zzbNF = var12;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.id);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.centerX);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.centerY);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.width);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.height);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbNA);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbNB);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbNC, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzbND);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzbNE);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzbNF);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
