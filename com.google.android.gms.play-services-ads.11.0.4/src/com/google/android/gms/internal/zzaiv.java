package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class zzaiv implements Callable {
   // $FF: synthetic field
   private Context zzaay;
   // $FF: synthetic field
   private Context zztF;

   zzaiv(zzaiu var1, Context var2, Context var3) {
      this.zzaay = var2;
      this.zztF = var3;
      super();
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      boolean var2 = false;
      SharedPreferences var3;
      if (this.zzaay != null) {
         zzafr.v("Attempting to read user agent from Google Play Services.");
         var3 = this.zzaay.getSharedPreferences("admob_user_agent", 0);
      } else {
         zzafr.v("Attempting to read user agent from local cache.");
         var3 = this.zztF.getSharedPreferences("admob_user_agent", 0);
         var2 = true;
      }

      String var4;
      if (TextUtils.isEmpty(var4 = var3.getString("user_agent", ""))) {
         zzafr.v("Reading user agent from WebSettings");
         var4 = WebSettings.getDefaultUserAgent(this.zztF);
         if (var2) {
            var3.edit().putString("user_agent", var4).apply();
            zzafr.v("Persisting user agent.");
         }
      }

      return var4;
   }
}
