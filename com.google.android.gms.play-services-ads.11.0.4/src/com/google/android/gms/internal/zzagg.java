package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzagg extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private long zzZg;

   zzagg(Context var1, long var2) {
      this.zztF = var1;
      this.zzZg = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).putLong("app_last_background_time_ms", this.zzZg);
      var1.apply();
   }
}
