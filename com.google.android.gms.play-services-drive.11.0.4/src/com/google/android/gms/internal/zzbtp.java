package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbtp extends zza {
   public static final Creator CREATOR = new zzbtq();
   private String zzaSd;
   private String zzaSe;
   private int zzaSf;
   private int zzaSg;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaSd, false);
      zzd.zza(var1, 3, this.zzaSe, false);
      zzd.zzc(var1, 4, this.zzaSf);
      zzd.zzc(var1, 5, this.zzaSg);
      zzd.zzI(var1, var5);
   }

   public zzbtp(String var1, String var2, int var3, int var4) {
      this.zzaSd = var1;
      this.zzaSe = var2;
      this.zzaSf = var3;
      this.zzaSg = var4;
   }
}
