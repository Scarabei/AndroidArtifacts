package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.zzv;

public final class zzl extends zzv implements DriveEvent {
   public static final Creator CREATOR = new zzm();
   private DataHolder zzaCX;
   private boolean zzaNj;
   private int zzaNk;

   public zzl(DataHolder var1, boolean var2, int var3) {
      this.zzaCX = var1;
      this.zzaNj = var2;
      this.zzaNk = var3;
   }

   public final void zzJ(Parcel var1, int var2) {
      int var6 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaCX, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaNj);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzaNk);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var6);
   }

   public final int getType() {
      return 3;
   }

   public final DataHolder zztb() {
      return this.zzaCX;
   }

   public final boolean zztc() {
      return this.zzaNj;
   }

   public final int zztd() {
      return this.zzaNk;
   }
}
