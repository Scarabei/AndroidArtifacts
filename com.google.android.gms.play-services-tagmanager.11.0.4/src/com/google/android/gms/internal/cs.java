package com.google.android.gms.internal;

final class cs implements Runnable {
   // $FF: synthetic field
   private String zzbKM;
   // $FF: synthetic field
   private byte[] zzbKQ;
   // $FF: synthetic field
   private co zzbKO;

   cs(co var1, String var2, byte[] var3) {
      this.zzbKO = var1;
      this.zzbKM = var2;
      this.zzbKQ = var3;
      super();
   }

   public final void run() {
      this.zzbKO.zze(this.zzbKM, this.zzbKQ);
   }
}
