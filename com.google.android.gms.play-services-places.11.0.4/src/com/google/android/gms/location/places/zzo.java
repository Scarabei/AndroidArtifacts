package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import java.util.Set;

public final class zzo extends com.google.android.gms.common.internal.safeparcel.zza {
   private static zzo zzbjW = zzt("test_type", 1);
   private static zzo zzbjX = zzt("labeled_place", 6);
   private static zzo zzbjY = zzt("here_content", 7);
   private static Set zzbjZ;
   public static final Creator CREATOR;
   private String zzVB;
   private int zzbka;

   zzo(String var1, int var2) {
      zzbo.zzcF(var1);
      this.zzVB = var1;
      this.zzbka = var2;
   }

   private static zzo zzt(String var0, int var1) {
      return new zzo(var0, var1);
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzo)) {
         return false;
      } else {
         zzo var2 = (zzo)var1;
         return this.zzVB.equals(var2.zzVB) && this.zzbka == var2.zzbka;
      }
   }

   public final int hashCode() {
      return this.zzVB.hashCode();
   }

   public final String toString() {
      return this.zzVB;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzVB, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbka);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static {
      zzbjZ = com.google.android.gms.common.util.zzf.zza(zzbjW, zzbjX, zzbjY);
      CREATOR = new zzp();
   }
}
