package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class ft extends zza {
   public static final Creator CREATOR = new fu();
   private fo[] zzbOi;
   public final fe zzbNY;
   private fe zzbNZ;
   public final String zzbOb;
   private float zzbOc;
   public final String zzbNS;
   private boolean zzbOj;

   public ft(fo[] var1, fe var2, fe var3, String var4, float var5, String var6, boolean var7) {
      this.zzbOi = var1;
      this.zzbNY = var2;
      this.zzbNZ = var3;
      this.zzbOb = var4;
      this.zzbOc = var5;
      this.zzbNS = var6;
      this.zzbOj = var7;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzbOi, var2, false);
      zzd.zza(var1, 3, this.zzbNY, var2, false);
      zzd.zza(var1, 4, this.zzbNZ, var2, false);
      zzd.zza(var1, 5, this.zzbOb, false);
      zzd.zza(var1, 6, this.zzbOc);
      zzd.zza(var1, 7, this.zzbNS, false);
      zzd.zza(var1, 8, this.zzbOj);
      zzd.zzI(var1, var5);
   }
}
