package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.DriveId;
import java.util.Locale;

public final class ChangeEvent extends com.google.android.gms.common.internal.safeparcel.zza implements ResourceEvent {
   public static final Creator CREATOR = new zza();
   private DriveId zzaLV;
   private int zzaMQ;

   public ChangeEvent(DriveId var1, int var2) {
      this.zzaLV = var1;
      this.zzaMQ = var2;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaLV, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzaMQ);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final int getType() {
      return 1;
   }

   public final DriveId getDriveId() {
      return this.zzaLV;
   }

   public final boolean hasMetadataChanged() {
      return (this.zzaMQ & 1) != 0;
   }

   public final boolean hasContentChanged() {
      return (this.zzaMQ & 2) != 0;
   }

   public final boolean hasBeenDeleted() {
      return (this.zzaMQ & 4) != 0;
   }

   public final String toString() {
      return String.format(Locale.US, "ChangeEvent [id=%s,changeFlags=%x]", this.zzaLV, this.zzaMQ);
   }
}
