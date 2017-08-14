package com.google.android.gms.internal;

@zzzn
final class zzrj {
   private final zzrk zzJx;
   private final boolean zzJy;
   private final String zzJz;

   public zzrj(zzre var1, boolean var2, zzrk var3, String var4) {
      this.zzJy = var2;
      this.zzJx = var3;
      this.zzJz = var4;
   }

   public final String getReason() {
      return this.zzJz;
   }

   public final zzrk zzeC() {
      return this.zzJx;
   }

   public final boolean isSuccess() {
      return this.zzJy;
   }
}
