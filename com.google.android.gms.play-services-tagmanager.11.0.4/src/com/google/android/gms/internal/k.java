package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class k extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 1 || var2.length == 2);
      zzbo.zzaf(var2[0] instanceof eb);
      String var3 = (String)((eb)var2[0]).value();
      Matcher var4;
      return (var4 = Pattern.compile(var2.length < 2 ? "" : zzcxp.zzd(var2[1])).matcher(var3)).find() ? new dt((double)var4.start()) : new dt(-1.0D);
   }
}
