package com.google.android.gms.internal;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.common.internal.zzbo;

public final class bd implements zzcxo {
   private final Context mContext;

   public bd(Context var1) {
      this.mContext = (Context)zzbo.zzu(var1);
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      String var3;
      return (dp)((var3 = Secure.getString(this.mContext.getContentResolver(), "android_id")) != null ? new eb(var3) : dv.zzbLu);
   }
}
