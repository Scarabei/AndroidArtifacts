package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class zze extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzf();
   private String zzbQH;
   private String zzbQI;
   private ArrayList zzbQJ;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbQH, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbQI, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbQJ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   zze(String var1, String var2, ArrayList var3) {
      this.zzbQH = var1;
      this.zzbQI = var2;
      this.zzbQJ = var3;
   }

   zze() {
      this.zzbQJ = new ArrayList();
   }
}
