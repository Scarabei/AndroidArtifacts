package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.DriveId;
import java.util.Arrays;

public final class zzbkp extends zza {
   public static final Creator CREATOR = new zzbkq();
   final int zzaNm;
   final DriveId zzaLV;
   final int zzLe;
   final long zzaNp;
   final long zzaNq;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaNm);
      zzd.zza(var1, 3, this.zzaLV, var2, false);
      zzd.zzc(var1, 4, this.zzLe);
      zzd.zza(var1, 5, this.zzaNp);
      zzd.zza(var1, 6, this.zzaNq);
      zzd.zzI(var1, var5);
   }

   public zzbkp(int var1, DriveId var2, int var3, long var4, long var6) {
      this.zzaNm = var1;
      this.zzaLV = var2;
      this.zzLe = var3;
      this.zzaNp = var4;
      this.zzaNq = var6;
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zzbkp var2 = (zzbkp)var1;
            return this.zzaNm == var2.zzaNm && zzbe.equal(this.zzaLV, var2.zzaLV) && this.zzLe == var2.zzLe && this.zzaNp == var2.zzaNp && this.zzaNq == var2.zzaNq;
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaNm, this.zzaLV, this.zzLe, this.zzaNp, this.zzaNq});
   }

   public final String toString() {
      return String.format("TransferProgressData[TransferType: %d, DriveId: %s, status: %d, bytes transferred: %d, total bytes: %d]", this.zzaNm, this.zzaLV, this.zzLe, this.zzaNp, this.zzaNq);
   }
}
