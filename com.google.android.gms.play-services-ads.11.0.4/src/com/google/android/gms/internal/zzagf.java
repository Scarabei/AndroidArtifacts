package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzagf extends zzagr {
   // $FF: synthetic field
   private Context zztF;

   zzagf(Context var1) {
      this.zztF = var1;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).remove("native_advanced_settings");
      var1.apply();
   }
}
