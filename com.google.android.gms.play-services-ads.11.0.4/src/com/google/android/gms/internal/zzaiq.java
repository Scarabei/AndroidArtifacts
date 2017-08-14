package com.google.android.gms.internal;

import android.content.Context;

@zzzn
public final class zzaiq extends zzafp {
   private final zzajd zzaat;
   private final String zzD;

   public zzaiq(Context var1, String var2, String var3) {
      this(var3, com.google.android.gms.ads.internal.zzbs.zzbz().zzs(var1, var2));
   }

   public zzaiq(String var1, String var2) {
      this.zzaat = new zzajd(var2);
      this.zzD = var1;
   }

   public final void onStop() {
   }

   public final void zzbd() {
      this.zzaat.zzaN(this.zzD);
   }
}
