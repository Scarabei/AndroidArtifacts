package com.google.android.gms.drive.metadata;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;

public abstract class zzb extends zza {
   protected zzb(String var1, Collection var2, Collection var3, int var4) {
      super(var1, var2, var3, var4);
   }

   protected Collection zzd(DataHolder var1, int var2, int var3) {
      throw new UnsupportedOperationException("Cannot read collections from a dataHolder.");
   }

   // $FF: synthetic method
   protected Object zzc(DataHolder var1, int var2, int var3) {
      return this.zzd(var1, var2, var3);
   }
}
