package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzcbz extends zza {
   private int uid;
   private String packageName;
   public static final Creator CREATOR = new zzcca();

   public zzcbz(int var1, String var2) {
      this.uid = var1;
      this.packageName = var2;
   }

   public final int hashCode() {
      return this.uid;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 != null && var1 instanceof zzcbz) {
         zzcbz var2;
         return (var2 = (zzcbz)var1).uid == this.uid && zzbe.equal(var2.packageName, this.packageName);
      } else {
         return false;
      }
   }

   public final String toString() {
      return String.format("%d:%s", this.uid, this.packageName);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.uid);
      zzd.zza(var1, 2, this.packageName, false);
      zzd.zzI(var1, var5);
   }
}
