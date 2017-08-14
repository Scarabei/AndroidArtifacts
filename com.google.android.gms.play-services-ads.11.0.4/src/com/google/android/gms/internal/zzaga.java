package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzaga extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private String zzZd;
   // $FF: synthetic field
   private long zzZe;

   zzaga(Context var1, String var2, long var3) {
      this.zztF = var1;
      this.zzZd = var2;
      this.zzZe = var3;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).putString("app_settings_json", this.zzZd);
      var1.putLong("app_settings_last_update_ms", this.zzZe);
      var1.apply();
   }
}
