package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcny extends zza {
   public static final Creator CREATOR = new zzcnz();
   private final String zzbxq;
   private final String zzbwr;
   private final String zzbwo;

   public zzcny(String var1, String var2, String var3) {
      this.zzbxq = var1;
      this.zzbwr = var2;
      this.zzbwo = var3;
   }

   public final String zzzJ() {
      return this.zzbxq;
   }

   public final String getServiceId() {
      return this.zzbwr;
   }

   public final String getEndpointName() {
      return this.zzbwo;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbxq, this.zzbwr, this.zzbwo});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcny) {
         zzcny var2 = (zzcny)var1;
         return zzbe.equal(this.zzbxq, var2.zzbxq) && zzbe.equal(this.zzbwr, var2.zzbwr) && zzbe.equal(this.zzbwo, var2.zzbwo);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbxq, false);
      zzd.zza(var1, 2, this.zzbwr, false);
      zzd.zza(var1, 3, this.zzbwo, false);
      zzd.zzI(var1, var5);
   }
}
