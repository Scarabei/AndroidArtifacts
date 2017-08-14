package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.ChannelIOException;
import java.io.IOException;
import java.io.InputStream;

public final class zzav extends InputStream {
   private final InputStream zzbSq;
   private volatile zzah zzbSr;

   public zzav(InputStream var1) {
      this.zzbSq = (InputStream)com.google.android.gms.common.internal.zzbo.zzu(var1);
   }

   final void zza(zzah var1) {
      this.zzbSr = (zzah)com.google.android.gms.common.internal.zzbo.zzu(var1);
   }

   public final int available() throws IOException {
      return this.zzbSq.available();
   }

   public final void close() throws IOException {
      this.zzbSq.close();
   }

   public final void mark(int var1) {
      this.zzbSq.mark(var1);
   }

   public final boolean markSupported() {
      return this.zzbSq.markSupported();
   }

   public final int read() throws IOException {
      return this.zzbR(this.zzbSq.read());
   }

   public final int read(byte[] var1) throws IOException {
      return this.zzbR(this.zzbSq.read(var1));
   }

   public final int read(byte[] var1, int var2, int var3) throws IOException {
      return this.zzbR(this.zzbSq.read(var1, var2, var3));
   }

   public final void reset() throws IOException {
      this.zzbSq.reset();
   }

   public final long skip(long var1) throws IOException {
      return this.zzbSq.skip(var1);
   }

   private final int zzbR(int var1) throws ChannelIOException {
      if (var1 == -1) {
         zzah var2 = this.zzbSr;
         if (this.zzbSr != null) {
            throw new ChannelIOException("Channel closed unexpectedly before stream was finished", var2.zzbSh, var2.zzbSi);
         }
      }

      return var1;
   }
}
