package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcns extends zza {
   public static final Creator CREATOR = new zzcnt();
   private final String zzbwG;
   private final int statusCode;
   @Nullable
   private final byte[] zzbwH;

   public zzcns(String var1, int var2, @Nullable byte[] var3) {
      this.zzbwG = var1;
      this.statusCode = var2;
      this.zzbwH = var3;
   }

   public final String zzzF() {
      return this.zzbwG;
   }

   public final int getStatusCode() {
      return this.statusCode;
   }

   @Nullable
   public final byte[] zzzI() {
      return this.zzbwH;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwG, this.statusCode, this.zzbwH});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcns) {
         zzcns var2 = (zzcns)var1;
         return zzbe.equal(this.zzbwG, var2.zzbwG) && zzbe.equal(this.statusCode, var2.statusCode) && zzbe.equal(this.zzbwH, var2.zzbwH);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbwG, false);
      zzd.zzc(var1, 2, this.statusCode);
      zzd.zza(var1, 3, this.zzbwH, false);
      zzd.zzI(var1, var5);
   }
}
