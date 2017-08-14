package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.List;

public final class zzcyh extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzu(var2);
      zzbo.zzaf(var2.length == 1 || var2.length == 2);
      zzbo.zzaf(var2[0] instanceof dw);
      dw var3 = (dw)var2[0];
      du var4;
      if (var2.length == 2) {
         zzbo.zzaf(var2[1] instanceof du);
         var4 = (du)var2[1];
      } else {
         var4 = new du(new zzcyk((zzcyi)null));
      }

      Collections.sort((List)var3.zzDs(), new zzcyj(this, var4, var1));
      return var2[0];
   }
}
