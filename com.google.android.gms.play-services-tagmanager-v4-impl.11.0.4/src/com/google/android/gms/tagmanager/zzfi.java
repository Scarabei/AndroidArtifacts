package com.google.android.gms.tagmanager;

final class zzfi {
   private zzea zzbGu;
   private com.google.android.gms.internal.zzbr zzbGv;

   public zzfi(zzea var1, com.google.android.gms.internal.zzbr var2) {
      this.zzbGu = var1;
      this.zzbGv = var2;
   }

   public final zzea zzBM() {
      return this.zzbGu;
   }

   public final com.google.android.gms.internal.zzbr zzBN() {
      return this.zzbGv;
   }

   public final int getSize() {
      return ((com.google.android.gms.internal.zzbr)this.zzbGu.getObject()).zzLU() + (this.zzbGv == null ? 0 : this.zzbGv.zzLU());
   }
}
