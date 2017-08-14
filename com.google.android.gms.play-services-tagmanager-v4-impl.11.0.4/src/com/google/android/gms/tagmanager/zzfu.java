package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;

final class zzfu {
   @SuppressLint({"CommitPrefEdits"})
   static void zzd(Context var0, String var1, String var2, String var3) {
      Editor var4;
      (var4 = var0.getSharedPreferences(var1, 0).edit()).putString(var2, var3);
      var4.apply();
   }
}
