package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.zzbe;

public class PlacePhotoResult extends com.google.android.gms.common.internal.safeparcel.zza implements Result {
   public static final Creator CREATOR = new zzk();
   private final Status mStatus;
   private BitmapTeleporter zzbjH;
   private final Bitmap mBitmap;

   public PlacePhotoResult(Status var1, BitmapTeleporter var2) {
      this.mStatus = var1;
      this.zzbjH = var2;
      if (this.zzbjH != null) {
         this.mBitmap = var2.zzqO();
      } else {
         this.mBitmap = null;
      }
   }

   public Status getStatus() {
      return this.mStatus;
   }

   public Bitmap getBitmap() {
      return this.mBitmap;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getStatus(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbjH, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String toString() {
      return zzbe.zzt(this).zzg("status", this.mStatus).zzg("bitmap", this.mBitmap).toString();
   }
}
