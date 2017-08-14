package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzbo;

public abstract class zzv extends com.google.android.gms.common.internal.safeparcel.zza {
   private transient volatile boolean zzaMP = false;

   public void writeToParcel(Parcel var1, int var2) {
      zzbo.zzae(!this.zzaMP);
      this.zzaMP = true;
      this.zzJ(var1, var2);
   }

   protected abstract void zzJ(Parcel var1, int var2);
}
