package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;
import java.util.List;

/** @deprecated */
@Deprecated
public final class zzcdy extends zza {
   public static final Creator CREATOR = new zzcea();
   private final String zzakh;
   private final String zzbjI;
   private final List zzbll;

   zzcdy(String var1, String var2, List var3) {
      this.zzakh = var1;
      this.zzbjI = var2;
      this.zzbll = var3;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzakh, this.zzbjI, this.zzbll});
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("accountName", this.zzakh).zzg("placeId", this.zzbjI).zzg("placeAliases", this.zzbll).toString();
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzcdy)) {
         return false;
      } else {
         zzcdy var2 = (zzcdy)var1;
         return this.zzakh.equals(var2.zzakh) && this.zzbjI.equals(var2.zzbjI) && this.zzbll.equals(var2.zzbll);
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzakh, false);
      zzd.zza(var1, 2, this.zzbjI, false);
      zzd.zzc(var1, 6, this.zzbll, false);
      zzd.zzI(var1, var5);
   }
}
