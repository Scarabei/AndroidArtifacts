package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzagi extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private int zzZh;

   zzagi(Context var1, int var2) {
      this.zztF = var1;
      this.zzZh = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).putInt("request_in_session_count", this.zzZh);
      var1.apply();
   }
}
