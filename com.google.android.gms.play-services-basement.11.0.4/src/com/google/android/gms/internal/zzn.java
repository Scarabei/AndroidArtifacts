package com.google.android.gms.internal;

import java.util.Map;

public final class zzn {
   private int statusCode;
   public final byte[] data;
   public final Map zzy;
   public final boolean zzz;
   private long zzA;

   public zzn(int var1, byte[] var2, Map var3, boolean var4, long var5) {
      this.statusCode = var1;
      this.data = var2;
      this.zzy = var3;
      this.zzz = var4;
      this.zzA = var5;
   }

   public zzn(byte[] var1, Map var2) {
      this(200, var1, var2, false, 0L);
   }
}
