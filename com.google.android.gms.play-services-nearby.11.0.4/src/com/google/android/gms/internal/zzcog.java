package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcog extends zza {
   public static final Creator CREATOR = new zzcoh();
   private final int statusCode;
   private final String zzbwY;

   public zzcog(int var1, String var2) {
      this.statusCode = var1;
      this.zzbwY = var2;
   }

   public final int getStatusCode() {
      return this.statusCode;
   }

   public final String getLocalEndpointName() {
      return this.zzbwY;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.statusCode, this.zzbwY});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcog) {
         zzcog var2 = (zzcog)var1;
         return zzbe.equal(this.statusCode, var2.statusCode) && zzbe.equal(this.zzbwY, var2.zzbwY);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.statusCode);
      zzd.zza(var1, 2, this.zzbwY, false);
      zzd.zzI(var1, var5);
   }
}
