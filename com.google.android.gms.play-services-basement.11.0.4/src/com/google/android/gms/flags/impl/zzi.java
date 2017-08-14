package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzi implements Callable {
   // $FF: synthetic field
   private SharedPreferences zzaXK;
   // $FF: synthetic field
   private String zzaXL;
   // $FF: synthetic field
   private String zzaXP;

   zzi(SharedPreferences var1, String var2, String var3) {
      this.zzaXK = var1;
      this.zzaXL = var2;
      this.zzaXP = var3;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zzaXK.getString(this.zzaXL, this.zzaXP);
   }
}
