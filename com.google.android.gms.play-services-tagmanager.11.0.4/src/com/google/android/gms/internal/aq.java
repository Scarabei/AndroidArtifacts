package com.google.android.gms.internal;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.common.internal.zzbo;

public final class aq implements zzcxo {
   private final Context mContext;

   public aq(Context var1) {
      this.mContext = var1;
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      String var3;
      if ((var3 = Secure.getString(this.mContext.getContentResolver(), "android_id")) == null) {
         var3 = "";
      }

      return new eb(var3);
   }
}
