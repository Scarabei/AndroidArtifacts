package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzagc extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private String zzZf;

   zzagc(Context var1, String var2) {
      this.zztF = var1;
      this.zzZf = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).putString("native_advanced_settings", this.zzZf);
      var1.apply();
   }
}
