package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

final class zzafz extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzags zzZb;

   zzafz(Context var1, zzags var2) {
      this.zztF = var1;
      this.zzZb = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      SharedPreferences var1 = this.zztF.getSharedPreferences("admob", 0);
      Bundle var2;
      (var2 = new Bundle()).putInt("version_code", var1.getInt("version_code", 0));
      if (this.zzZb != null) {
         this.zzZb.zzf(var2);
      }

   }
}
