package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.zza;
import com.google.android.gms.drive.zzv;
import java.util.List;

public final class zzbox extends zzv {
   public static final Creator CREATOR = new zzboy();
   private DataHolder zzaOR;
   private List zzaOS;
   private zza zzaOT;
   private boolean zzaOU;

   protected final void zzJ(Parcel var1, int var2) {
      int var5 = var2 | 1;
      int var6 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaOR, var5, false);
      zzd.zzc(var1, 3, this.zzaOS, false);
      zzd.zza(var1, 4, this.zzaOT, var5, false);
      zzd.zza(var1, 5, this.zzaOU);
      zzd.zzI(var1, var6);
   }

   public zzbox(DataHolder var1, List var2, zza var3, boolean var4) {
      this.zzaOR = var1;
      this.zzaOS = var2;
      this.zzaOT = var3;
      this.zzaOU = var4;
   }
}
