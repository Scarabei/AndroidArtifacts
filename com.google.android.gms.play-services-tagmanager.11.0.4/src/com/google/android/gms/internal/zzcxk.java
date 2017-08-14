package com.google.android.gms.internal;

import com.google.android.gms.analytics.Logger;

final class zzcxk implements Logger {
   public final void error(String var1) {
      zzcvk.e(var1);
   }

   public final void error(Exception var1) {
      zzcvk.zzb("", var1);
   }

   public final void info(String var1) {
      zzcvk.zzaS(var1);
   }

   public final void verbose(String var1) {
      zzcvk.v(var1);
   }

   public final void warn(String var1) {
      zzcvk.zzaT(var1);
   }

   public final void setLogLevel(int var1) {
      zzcvk.zzaT("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
   }

   public final int getLogLevel() {
      zzcvk.getLogLevel();
      return 3;
   }
}
