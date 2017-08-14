package com.google.android.gms.internal;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.google.android.gms.common.internal.zzbo;

public final class al implements zzcxo {
   private final Context mContext;

   public al(Context var1) {
      this.mContext = (Context)zzbo.zzu(var1);
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      TelephonyManager var3 = (TelephonyManager)this.mContext.getSystemService("phone");
      Object var4 = dv.zzbLu;
      String var5;
      if (var3 != null && (var5 = var3.getNetworkOperatorName()) != null) {
         var4 = new eb(var5);
      }

      return (dp)var4;
   }
}
