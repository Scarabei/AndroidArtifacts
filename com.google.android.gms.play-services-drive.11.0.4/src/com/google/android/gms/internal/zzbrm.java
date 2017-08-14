package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.zzb;

public final class zzbrm extends zzb implements SearchableMetadataField {
   public zzbrm(String var1, int var2) {
      super(var1, 4100000);
   }

   protected final Boolean zze(DataHolder var1, int var2, int var3) {
      return var1.zzc(this.getName(), var2, var3) != 0;
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      return this.zze(var1, var2, var3);
   }
}
