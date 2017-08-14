package com.google.android.gms.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;

public final class zzare {
   public static String zzbN(String var0) {
      zzbo.zzb(!TextUtils.isEmpty(var0), "account type cannot be empty");
      String var1 = Uri.parse(var0).getScheme();
      zzbo.zzb("http".equalsIgnoreCase(var1) || "https".equalsIgnoreCase(var1), "Account type must be an http or https URI");
      return var0;
   }
}
