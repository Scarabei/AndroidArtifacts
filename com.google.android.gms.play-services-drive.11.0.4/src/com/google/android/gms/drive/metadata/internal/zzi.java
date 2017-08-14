package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;

public class zzi extends com.google.android.gms.drive.metadata.zza {
   public zzi(String var1, int var2) {
      super(var1, 4300000);
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      return var1.zzb(this.getName(), var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(Bundle var1, Object var2) {
      Long var5 = (Long)var2;
      var1.putLong(this.getName(), var5.longValue());
   }

   // $FF: synthetic method
   protected final Object zzq(Bundle var1) {
      return var1.getLong(this.getName());
   }
}
