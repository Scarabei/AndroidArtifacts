package com.google.android.gms.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class zzaq extends ByteArrayOutputStream {
   private final zzae zzap;

   public zzaq(zzae var1, int var2) {
      this.zzap = var1;
      this.buf = this.zzap.zzb(Math.max(var2, 256));
   }

   public final void close() throws IOException {
      this.zzap.zza(this.buf);
      this.buf = null;
      super.close();
   }

   public final void finalize() {
      this.zzap.zza(this.buf);
   }

   private final void zzc(int var1) {
      if (this.count + var1 > this.buf.length) {
         byte[] var2 = this.zzap.zzb(this.count + var1 << 1);
         System.arraycopy(this.buf, 0, var2, 0, this.count);
         this.zzap.zza(this.buf);
         this.buf = var2;
      }
   }

   public final synchronized void write(byte[] var1, int var2, int var3) {
      this.zzc(var3);
      super.write(var1, var2, var3);
   }

   public final synchronized void write(int var1) {
      this.zzc(1);
      super.write(var1);
   }
}
