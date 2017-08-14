package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzc implements Callable {
   // $FF: synthetic field
   private SharedPreferences zzaXK;
   // $FF: synthetic field
   private String zzaXL;
   // $FF: synthetic field
   private Boolean zzaXM;

   zzc(SharedPreferences var1, String var2, Boolean var3) {
      this.zzaXK = var1;
      this.zzaXL = var2;
      this.zzaXM = var3;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zzaXK.getBoolean(this.zzaXL, this.zzaXM.booleanValue());
   }
}
