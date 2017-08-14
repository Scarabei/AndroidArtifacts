package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.awareness.fence.FenceState;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbjd extends FenceState {
   public static final Creator CREATOR = new zzbje();
   private int zzaLd;
   private long zzaLe;
   private String zzaLf;
   private int zzaLg;

   public zzbjd(int var1, long var2, String var4, int var5) {
      this.zzaLd = var1;
      this.zzaLe = var2;
      this.zzaLf = var4;
      this.zzaLg = var5;
   }

   public final int getCurrentState() {
      return this.zzaLd;
   }

   public final int getPreviousState() {
      return this.zzaLg;
   }

   public final long getLastFenceUpdateTimeMillis() {
      return this.zzaLe;
   }

   public final String getFenceKey() {
      return this.zzaLf;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaLd);
      zzd.zza(var1, 3, this.zzaLe);
      zzd.zza(var1, 4, this.zzaLf, false);
      zzd.zzc(var1, 5, this.zzaLg);
      zzd.zzI(var1, var5);
   }
}
