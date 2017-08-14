package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

public final class zzg extends com.google.android.gms.common.internal.safeparcel.zza implements zze {
   public static final Creator CREATOR = new zzf();
   private int zzaku;
   private final String zzaeK;
   private final Integer zzbAb;

   zzg(int var1, String var2, Integer var3) {
      this.zzaeK = var2;
      this.zzbAb = var3;
      this.zzaku = var1;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaeK, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbAb, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean isDataValid() {
      return true;
   }

   public final String getSource() {
      return this.zzaeK;
   }

   public final Integer zzAd() {
      return this.zzbAb;
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zze)) {
         return false;
      } else if (this == var1) {
         return true;
      } else {
         zze var3 = (zze)var1;
         return zzbe.equal(this.getSource(), var3.getSource()) && zzbe.equal(this.zzAd(), var3.zzAd());
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.getSource(), this.zzAd()});
   }
}
