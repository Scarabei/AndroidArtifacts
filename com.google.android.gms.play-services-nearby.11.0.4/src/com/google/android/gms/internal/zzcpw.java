package com.google.android.gms.internal;

import com.google.android.gms.nearby.messages.StatusCallback;

final class zzcpw extends zzcps {
   // $FF: synthetic field
   private boolean zzbzI;

   zzcpw(zzcpv var1, boolean var2) {
      this.zzbzI = var2;
      super();
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      StatusCallback var3 = (StatusCallback)var1;
      var3.onPermissionChanged(this.zzbzI);
   }
}
