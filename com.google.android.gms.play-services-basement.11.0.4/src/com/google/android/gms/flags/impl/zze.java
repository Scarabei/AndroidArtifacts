package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zze implements Callable {
   // $FF: synthetic field
   private SharedPreferences zzaXK;
   // $FF: synthetic field
   private String zzaXL;
   // $FF: synthetic field
   private Integer zzaXN;

   zze(SharedPreferences var1, String var2, Integer var3) {
      this.zzaXK = var1;
      this.zzaXL = var2;
      this.zzaXN = var3;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zzaXK.getInt(this.zzaXL, this.zzaXN.intValue());
   }
}
