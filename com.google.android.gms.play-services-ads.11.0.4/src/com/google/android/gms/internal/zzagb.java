package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

final class zzagb extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzags zzZb;

   zzagb(Context var1, zzags var2) {
      this.zztF = var1;
      this.zzZb = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      SharedPreferences var1 = this.zztF.getSharedPreferences("admob", 0);
      Bundle var2;
      (var2 = new Bundle()).putString("app_settings_json", var1.getString("app_settings_json", ""));
      var2.putLong("app_settings_last_update_ms", var1.getLong("app_settings_last_update_ms", 0L));
      if (this.zzZb != null) {
         this.zzZb.zzf(var2);
      }

   }
}
