package com.google.android.gms.internal;

import com.google.android.gms.nearby.messages.internal.zzv;

public final class zzcpt extends zzv implements zzcpn {
   private static final zzcps zzbzH = new zzcpu();
   private final zzbdw zzbzE;

   public zzcpt(zzbdw var1) {
      this.zzbzE = var1;
   }

   public final void onExpired() {
      this.zzbzE.zza(zzbzH);
   }

   public final zzbdw zzzX() {
      return this.zzbzE;
   }
}
