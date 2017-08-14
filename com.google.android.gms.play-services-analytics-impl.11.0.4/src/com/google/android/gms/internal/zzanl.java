package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

final class zzanl implements Logger {
   private int zzagX = 2;
   private boolean zzadH;

   public final void verbose(String var1) {
   }

   public final void info(String var1) {
   }

   public final void warn(String var1) {
   }

   public final void error(String var1) {
   }

   public final void error(Exception var1) {
   }

   public final void setLogLevel(int var1) {
      this.zzagX = var1;
      if (!this.zzadH) {
         String var2;
         Log.i(var2 = (String)zzans.zzahg.get(), (new StringBuilder(91 + String.valueOf(var2).length())).append("Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(var2).append(" DEBUG").toString());
         this.zzadH = true;
      }

   }

   public final int getLogLevel() {
      return this.zzagX;
   }
}
