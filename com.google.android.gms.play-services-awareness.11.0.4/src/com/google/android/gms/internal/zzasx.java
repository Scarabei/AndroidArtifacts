package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.state.HeadphoneState;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzasx extends zza implements HeadphoneState {
   public static final Creator CREATOR = new zzasy();
   private final int zzanU;

   public zzasx(int var1) {
      this.zzanU = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.getState());
      zzd.zzI(var1, var5);
   }

   public final int getState() {
      return this.zzanU;
   }

   public final String toString() {
      return Integer.toString(this.zzanU);
   }
}
