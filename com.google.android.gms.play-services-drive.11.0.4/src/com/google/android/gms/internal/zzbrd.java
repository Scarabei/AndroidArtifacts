package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.zzb;
import java.util.Collection;

final class zzbrd extends zzb {
   zzbrd(String var1, Collection var2, Collection var3, int var4) {
      super(var1, var2, var3, 7000000);
   }

   protected final Boolean zze(DataHolder var1, int var2, int var3) {
      return var1.zzc("trashed", var2, var3) == 2;
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      return this.zze(var1, var2, var3);
   }
}
