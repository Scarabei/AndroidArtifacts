package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import java.util.Date;

public class zze extends com.google.android.gms.drive.metadata.zzd {
   public zze(String var1, int var2) {
      super(var1, var2);
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      return new Date(var1.zzb(this.getName(), var2, var3));
   }

   // $FF: synthetic method
   protected final void zza(Bundle var1, Object var2) {
      Date var5 = (Date)var2;
      var1.putLong(this.getName(), var5.getTime());
   }

   // $FF: synthetic method
   protected final Object zzq(Bundle var1) {
      return new Date(var1.getLong(this.getName()));
   }
}
