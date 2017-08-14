package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.drive.DriveId;
import java.util.Arrays;

public final class zzbko {
   private final DriveId zzaLV;
   private final int zzaNm;
   private final int zzLe;

   public zzbko(zzbkp var1) {
      this.zzaLV = var1.zzaLV;
      this.zzaNm = var1.zzaNm;
      this.zzLe = var1.zzLe;
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zzbko var2 = (zzbko)var1;
            return zzbe.equal(this.zzaLV, var2.zzaLV) && this.zzaNm == var2.zzaNm && this.zzLe == var2.zzLe;
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaLV, this.zzaNm, this.zzLe});
   }

   public final String toString() {
      return String.format("FileTransferState[TransferType: %d, DriveId: %s, status: %d]", this.zzaNm, this.zzaLV, this.zzLe);
   }
}
