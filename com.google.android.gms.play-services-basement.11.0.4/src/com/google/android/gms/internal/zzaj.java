package com.google.android.gms.internal;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

final class zzaj extends FilterInputStream {
   private int zzaz;

   private zzaj(InputStream var1) {
      super(var1);
      this.zzaz = 0;
   }

   public final int read() throws IOException {
      int var1;
      if ((var1 = super.read()) != -1) {
         ++this.zzaz;
      }

      return var1;
   }

   public final int read(byte[] var1, int var2, int var3) throws IOException {
      int var4;
      if ((var4 = super.read(var1, var2, var3)) != -1) {
         this.zzaz += var4;
      }

      return var4;
   }

   // $FF: synthetic method
   zzaj(InputStream var1, zzah var2) {
      this(var1);
   }

   // $FF: synthetic method
   static int zza(zzaj var0) {
      return var0.zzaz;
   }
}
