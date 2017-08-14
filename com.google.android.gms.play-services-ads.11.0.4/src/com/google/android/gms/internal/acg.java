package com.google.android.gms.internal;

public final class acg {
   private final byte[] zzcqn = new byte[256];
   private int zzcqo;
   private int zzcqp;

   public acg(byte[] var1) {
      int var2;
      for(var2 = 0; var2 < 256; ++var2) {
         this.zzcqn[var2] = (byte)var2;
      }

      var2 = 0;

      for(int var3 = 0; var2 < 256; ++var2) {
         var3 = var3 + this.zzcqn[var2] + var1[var2 % var1.length] & 255;
         byte var4 = this.zzcqn[var2];
         this.zzcqn[var2] = this.zzcqn[var3];
         this.zzcqn[var3] = var4;
      }

      this.zzcqo = 0;
      this.zzcqp = 0;
   }

   public final void zzG(byte[] var1) {
      int var2 = this.zzcqo;
      int var3 = this.zzcqp;

      for(int var4 = 0; var4 < var1.length; ++var4) {
         var2 = var2 + 1 & 255;
         var3 = var3 + this.zzcqn[var2] & 255;
         byte var5 = this.zzcqn[var2];
         this.zzcqn[var2] = this.zzcqn[var3];
         this.zzcqn[var3] = var5;
         var1[var4] ^= this.zzcqn[this.zzcqn[var2] + this.zzcqn[var3] & 255];
      }

      this.zzcqo = var2;
      this.zzcqp = var3;
   }
}
