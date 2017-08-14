package com.google.android.gms.internal;

import com.google.android.gms.nearby.messages.internal.zzy;

public final class zzcpv extends zzy implements zzcpn {
   private final zzbdw zzbzE;

   zzcpv(zzbdw var1) {
      this.zzbzE = var1;
   }

   public final void onPermissionChanged(boolean var1) {
      this.zzbzE.zza(new zzcpw(this, var1));
   }

   public final zzbdw zzzX() {
      return this.zzbzE;
   }
}
