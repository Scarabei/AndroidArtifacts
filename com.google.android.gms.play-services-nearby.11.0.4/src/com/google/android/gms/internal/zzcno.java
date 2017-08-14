package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcno extends zza {
   public static final Creator CREATOR = new zzcnp();
   private final String zzbwG;
   private final String zzbxp;
   private final String zzbwp;
   private final boolean zzbwq;
   @Nullable
   private final byte[] zzbwH;

   public zzcno(String var1, String var2, String var3, boolean var4, @Nullable byte[] var5) {
      this.zzbwG = var1;
      this.zzbxp = var2;
      this.zzbwp = var3;
      this.zzbwq = var4;
      this.zzbwH = var5;
   }

   public final String zzzF() {
      return this.zzbwG;
   }

   public final String zzzG() {
      return this.zzbxp;
   }

   public final String getAuthenticationToken() {
      return this.zzbwp;
   }

   public final boolean zzzH() {
      return this.zzbwq;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbwG, this.zzbxp, this.zzbwp, this.zzbwq, this.zzbwH});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcno) {
         zzcno var2 = (zzcno)var1;
         return zzbe.equal(this.zzbwG, var2.zzbwG) && zzbe.equal(this.zzbxp, var2.zzbxp) && zzbe.equal(this.zzbwp, var2.zzbwp) && zzbe.equal(this.zzbwq, var2.zzbwq) && Arrays.equals(this.zzbwH, var2.zzbwH);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbwG, false);
      zzd.zza(var1, 2, this.zzbxp, false);
      zzd.zza(var1, 3, this.zzbwp, false);
      zzd.zza(var1, 4, this.zzbwq);
      zzd.zza(var1, 5, this.zzbwH, false);
      zzd.zzI(var1, var5);
   }
}
