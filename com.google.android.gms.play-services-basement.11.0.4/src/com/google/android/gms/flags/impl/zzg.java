package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzg implements Callable {
   // $FF: synthetic field
   private SharedPreferences zzaXK;
   // $FF: synthetic field
   private String zzaXL;
   // $FF: synthetic field
   private Long zzaXO;

   zzg(SharedPreferences var1, String var2, Long var3) {
      this.zzaXK = var1;
      this.zzaXL = var2;
      this.zzaXO = var3;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zzaXK.getLong(this.zzaXL, this.zzaXO.longValue());
   }
}
