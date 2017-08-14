package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.io.UnsupportedEncodingException;

public final class v extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      String var3 = zzcxp.zzd((dp)(var2.length > 0 ? (dp)zzbo.zzu(var2[0]) : dv.zzbLu));

      try {
         return new eb(u.decode(var3, ""));
      } catch (UnsupportedEncodingException var4) {
         return dv.zzbLu;
      }
   }
}
