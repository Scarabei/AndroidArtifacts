package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;

final class zzgh implements Logger {
   public final void error(String var1) {
      zzdj.e(var1);
   }

   public final void error(Exception var1) {
      zzdj.zzb("", var1);
   }

   public final void info(String var1) {
      zzdj.zzaS(var1);
   }

   public final void verbose(String var1) {
      zzdj.v(var1);
   }

   public final void warn(String var1) {
      zzdj.zzaT(var1);
   }

   public final void setLogLevel(int var1) {
      zzdj.zzaT("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
   }

   public final int getLogLevel() {
      switch(zzdj.zzbFq) {
      case 2:
         return 0;
      case 3:
      case 4:
         return 1;
      case 5:
         return 2;
      case 6:
         return 3;
      default:
         return 3;
      }
   }
}
