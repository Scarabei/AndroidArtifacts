package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzafy extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private int zzZc;

   zzafy(Context var1, int var2) {
      this.zztF = var1;
      this.zzZc = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).putInt("version_code", this.zzZc);
      var1.apply();
   }
}
