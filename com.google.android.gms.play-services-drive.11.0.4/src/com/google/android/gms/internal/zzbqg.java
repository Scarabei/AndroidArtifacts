package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbqg extends zza {
   public static final Creator CREATOR = new zzbqh();
   private int zzaOL;
   private int zzaOM;
   private boolean zzaPr;

   zzbqg(int var1, int var2, boolean var3) {
      this.zzaOL = var1;
      this.zzaOM = var2;
      this.zzaPr = var3;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaOL);
      zzd.zzc(var1, 3, this.zzaOM);
      zzd.zza(var1, 4, this.zzaPr);
      zzd.zzI(var1, var5);
   }
}
