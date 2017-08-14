package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

final class zzagm extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private zzags zzZb;

   zzagm(Context var1, zzags var2) {
      this.zztF = var1;
      this.zzZb = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      SharedPreferences var1 = this.zztF.getSharedPreferences("admob", 0);
      Bundle var2;
      (var2 = new Bundle()).putInt("webview_cache_version", var1.getInt("webview_cache_version", 0));
      if (this.zzZb != null) {
         this.zzZb.zzf(var2);
      }

   }
}
