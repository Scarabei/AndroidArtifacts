package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Set;

final class zzi {
   static MetadataField zza(MetadataBundle var0) {
      Set var1;
      if ((var1 = var0.zztr()).size() != 1) {
         throw new IllegalArgumentException("bundle should have exactly 1 populated field");
      } else {
         return (MetadataField)var1.iterator().next();
      }
   }
}
