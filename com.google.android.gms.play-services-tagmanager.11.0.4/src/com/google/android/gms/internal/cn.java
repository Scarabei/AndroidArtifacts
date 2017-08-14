package com.google.android.gms.internal;

public final class cn {
   private final byte[] zzbKI;
   private final long zzbKJ;
   private final bz zzbKK;
   private final db zzbII;

   public cn(db var1) {
      this((bz)null, (byte[])null, var1, 0L);
   }

   public cn(bz var1, byte[] var2, db var3, long var4) {
      this.zzbKK = var1;
      this.zzbKI = var2;
      this.zzbII = var3;
      this.zzbKJ = var4;
   }

   public final byte[] zzCT() {
      return this.zzbKI;
   }

   public final bz zzCU() {
      return this.zzbKK;
   }

   public final db zzCV() {
      return this.zzbII;
   }

   public final long zzCW() {
      return this.zzbKJ;
   }
}
