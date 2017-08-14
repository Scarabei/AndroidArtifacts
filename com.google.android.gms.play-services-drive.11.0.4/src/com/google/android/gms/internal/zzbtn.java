package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.List;

public final class zzbtn extends zza {
   public static final Creator CREATOR = new zzbto();
   private List zztH;
   private DataHolder zzaRZ;
   private boolean zzaSa;
   private List zzaSb;
   private zzbtd zzaSc;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zztH, false);
      zzd.zza(var1, 3, this.zzaRZ, var2, false);
      zzd.zza(var1, 4, this.zzaSa);
      zzd.zzb(var1, 5, this.zzaSb, false);
      zzd.zza(var1, 6, this.zzaSc, var2, false);
      zzd.zzI(var1, var5);
   }

   public zzbtn(List var1, DataHolder var2, boolean var3, List var4, zzbtd var5) {
      this.zztH = var1;
      this.zzaRZ = var2;
      this.zzaSa = var3;
      this.zzaSb = var4;
      this.zzaSc = var5;
   }
}
