package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzagp extends zzagr {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private String zzYZ;

   zzagp(Context var1, String var2) {
      this.zztF = var1;
      this.zzYZ = var2;
      super((zzafu)null);
   }

   public final void zzbd() {
      Editor var1;
      (var1 = this.zztF.getSharedPreferences("admob", 0).edit()).putString("content_url_hashes", this.zzYZ);
      var1.apply();
   }
}
