package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Collections;
import java.util.List;

public final class zzbpd extends zza {
   private static final List zzaOX = Collections.emptyList();
   public static final Creator CREATOR = new zzbpe();
   final long zzaOY;
   final long zzaOZ;
   private int zzLe;
   private List zzaPa;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaOY);
      zzd.zza(var1, 3, this.zzaOZ);
      zzd.zzc(var1, 4, this.zzLe);
      zzd.zzc(var1, 5, this.zzaPa, false);
      zzd.zzI(var1, var5);
   }

   public zzbpd(long var1, long var3, int var5, List var6) {
      this.zzaOY = var1;
      this.zzaOZ = var3;
      this.zzLe = var5;
      this.zzaPa = var6;
   }
}
