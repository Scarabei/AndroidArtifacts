package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzagk extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private long zzZi;

   zzagk(Context var1, long var2) {
      this.zztF = var1;
      this.zzZi = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).putLong("first_ad_req_time_ms", this.zzZi);
      var1.apply();
   }
}
