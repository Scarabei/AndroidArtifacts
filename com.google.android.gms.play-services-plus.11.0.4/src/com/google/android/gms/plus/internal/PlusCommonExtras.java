package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

@KeepName
public class PlusCommonExtras extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzl();
   private final int zzaku;
   private String zzbAE;
   private String zzbAF;

   PlusCommonExtras(int var1, String var2, String var3) {
      this.zzaku = var1;
      this.zzbAE = var2;
      this.zzbAF = var3;
   }

   public PlusCommonExtras() {
      this.zzaku = 1;
      this.zzbAE = "";
      this.zzbAF = "";
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaku, this.zzbAE, this.zzbAF});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("versionCode", this.zzaku).zzg("Gpsrc", this.zzbAE).zzg("ClientCallingPackage", this.zzbAF).toString();
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof PlusCommonExtras)) {
         return false;
      } else {
         PlusCommonExtras var2 = (PlusCommonExtras)var1;
         return this.zzaku == var2.zzaku && zzbe.equal(this.zzbAE, var2.zzbAE) && zzbe.equal(this.zzbAF, var2.zzbAF);
      }
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbAE, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbAF, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
