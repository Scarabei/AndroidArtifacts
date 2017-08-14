package com.google.android.gms.internal;

final class cr implements Runnable {
   // $FF: synthetic field
   private String zzbKM;
   // $FF: synthetic field
   private String zzbKP;
   // $FF: synthetic field
   private cc zzbKN;
   // $FF: synthetic field
   private co zzbKO;

   cr(co var1, String var2, String var3, cc var4) {
      this.zzbKO = var1;
      this.zzbKM = var2;
      this.zzbKP = var3;
      this.zzbKN = var4;
      super();
   }

   public final void run() {
      this.zzbKO.zzb(this.zzbKM, this.zzbKP, this.zzbKN);
   }
}
