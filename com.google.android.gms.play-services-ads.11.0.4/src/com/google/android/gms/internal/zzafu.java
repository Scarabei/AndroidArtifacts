package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzafu extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private boolean zzYY;

   zzafu(Context var1, boolean var2) {
      this.zztF = var1;
      this.zzYY = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).putBoolean("use_https", this.zzYY);
      var1.apply();
   }
}
