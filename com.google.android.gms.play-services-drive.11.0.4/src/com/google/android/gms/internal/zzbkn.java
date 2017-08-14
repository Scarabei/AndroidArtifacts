package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.drive.events.zzk;
import java.util.Arrays;

public final class zzbkn {
   private final zzk zzaNo;
   private final long zzaNp;
   private final long zzaNq;

   public zzbkn(zzbkp var1) {
      this.zzaNo = new zzbko(var1);
      this.zzaNp = var1.zzaNp;
      this.zzaNq = var1.zzaNq;
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            zzbkn var2 = (zzbkn)var1;
            return zzbe.equal(this.zzaNo, var2.zzaNo) && this.zzaNp == var2.zzaNp && this.zzaNq == var2.zzaNq;
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaNq, this.zzaNp, this.zzaNq});
   }

   public final String toString() {
      return String.format("FileTransferProgress[FileTransferState: %s, BytesTransferred: %d, TotalBytes: %d]", this.zzaNo.toString(), this.zzaNp, this.zzaNq);
   }
}
