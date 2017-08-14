package com.google.android.gms.internal;

import com.google.android.gms.nearby.messages.internal.zzab;

public final class zzcpx extends zzab implements zzcpn {
   private static final zzcps zzbzH = new zzcpy();
   private final zzbdw zzbzE;

   public zzcpx(zzbdw var1) {
      this.zzbzE = var1;
   }

   public final void onExpired() {
      this.zzbzE.zza(zzbzH);
   }

   public final zzbdw zzzX() {
      return this.zzbzE;
   }
}
