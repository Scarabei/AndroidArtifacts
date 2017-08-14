package com.google.android.gms.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzblj extends Metadata {
   private final MetadataBundle zzaNJ;

   public zzblj(MetadataBundle var1) {
      this.zzaNJ = var1;
   }

   public final Object zza(MetadataField var1) {
      return this.zzaNJ.zza(var1);
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzaNJ);
      return (new StringBuilder(17 + String.valueOf(var1).length())).append("Metadata [mImpl=").append(var1).append("]").toString();
   }

   public final boolean isDataValid() {
      return this.zzaNJ != null;
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new zzblj(this.zzaNJ.zztq());
   }
}
