package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public class PlacePhotoMetadataResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   public static final Creator CREATOR = new zzj();
   private final Status mStatus;
   private DataHolder zzbjF;
   private final PlacePhotoMetadataBuffer zzbjG;

   public PlacePhotoMetadataResult(Status var1, DataHolder var2) {
      this.mStatus = var1;
      this.zzbjF = var2;
      if (var2 == null) {
         this.zzbjG = null;
      } else {
         this.zzbjG = new PlacePhotoMetadataBuffer(this.zzbjF);
      }
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public PlacePhotoMetadataBuffer getPhotoMetadata() {
      return this.zzbjG;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbjF, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
