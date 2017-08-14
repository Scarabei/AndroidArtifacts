package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcoc extends zza {
   public static final Creator CREATOR = new zzcod();
   private final String zzbwG;
   private final zzcoo zzbxr;
   private final boolean zzbxs;

   public zzcoc(String var1, zzcoo var2, boolean var3) {
      this.zzbwG = var1;
      this.zzbxr = var2;
      this.zzbxs = var3;
   }

   public final String zzzF() {
      return this.zzbwG;
   }

   public final zzcoo zzzK() {
      return this.zzbxr;
   }

   public final boolean zzzL() {
      return this.zzbxs;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwG, this.zzbxr, this.zzbxs});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcoc) {
         zzcoc var2 = (zzcoc)var1;
         return zzbe.equal(this.zzbwG, var2.zzbwG) && zzbe.equal(this.zzbxr, var2.zzbxr) && zzbe.equal(this.zzbxs, var2.zzbxs);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbwG, false);
      zzd.zza(var1, 2, this.zzbxr, var2, false);
      zzd.zza(var1, 3, this.zzbxs);
      zzd.zzI(var1, var5);
   }
}
