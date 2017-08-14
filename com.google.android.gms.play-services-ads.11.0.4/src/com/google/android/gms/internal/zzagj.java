package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

final class zzagj extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzags zzZb;

   zzagj(Context var1, zzags var2) {
      this.zztF = var1;
      this.zzZb = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      SharedPreferences var1 = this.zztF.getSharedPreferences("admob", 0);
      Bundle var2;
      (var2 = new Bundle()).putInt("request_in_session_count", var1.getInt("request_in_session_count", -1));
      if (this.zzZb != null) {
         this.zzZb.zzf(var2);
      }

   }
}
