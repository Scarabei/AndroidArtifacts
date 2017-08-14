package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;

public class zzb extends com.google.android.gms.drive.metadata.zza {
   public zzb(String var1, int var2) {
      super(var1, var2);
   }

   public zzb(String var1, Collection var2, Collection var3, int var4) {
      super(var1, var2, var3, 7000000);
   }

   protected Boolean zze(DataHolder var1, int var2, int var3) {
      return var1.zze(this.getName(), var2, var3);
   }

   // $FF: synthetic method
   protected Object zzc(DataHolder var1, int var2, int var3) {
      return this.zze(var1, var2, var3);
   }

   // $FF: synthetic method
   protected final void zza(Bundle var1, Object var2) {
      Boolean var5 = (Boolean)var2;
      var1.putBoolean(this.getName(), var5.booleanValue());
   }

   // $FF: synthetic method
   protected final Object zzq(Bundle var1) {
      return var1.getBoolean(this.getName());
   }
}
