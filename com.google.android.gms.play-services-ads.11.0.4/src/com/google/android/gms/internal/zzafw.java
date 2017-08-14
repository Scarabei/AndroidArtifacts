package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzafw extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private boolean zzZa;

   zzafw(Context var1, boolean var2) {
      this.zztF = var1;
      this.zzZa = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).putBoolean("auto_collect_location", this.zzZa);
      var1.apply();
   }
}
