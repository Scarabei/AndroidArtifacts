package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.zzbe;
import java.util.Arrays;

final class zzb {
   public final Uri uri;

   public zzb(Uri var1) {
      this.uri = var1;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.uri});
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zzb)) {
         return false;
      } else {
         return this == var1 ? true : zzbe.equal(((zzb)var1).uri, this.uri);
      }
   }
}
