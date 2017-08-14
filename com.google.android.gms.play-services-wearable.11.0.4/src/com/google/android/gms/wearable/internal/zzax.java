package com.google.android.gms.wearable.internal;

import android.util.Log;
import com.google.android.gms.wearable.ChannelIOException;
import java.io.IOException;
import java.io.OutputStream;

public final class zzax extends OutputStream {
   private final OutputStream zzbSt;
   private volatile zzah zzbSr;

   public zzax(OutputStream var1) {
      this.zzbSt = (OutputStream)com.google.android.gms.common.internal.zzbo.zzu(var1);
   }

   final void zzc(zzah var1) {
      this.zzbSr = var1;
   }

   public final void close() throws IOException {
      try {
         this.zzbSt.close();
      } catch (IOException var2) {
         throw this.zza(var2);
      }
   }

   public final void flush() throws IOException {
      try {
         this.zzbSt.flush();
      } catch (IOException var2) {
         throw this.zza(var2);
      }
   }

   public final void write(byte[] var1) throws IOException {
      try {
         this.zzbSt.write(var1);
      } catch (IOException var3) {
         throw this.zza(var3);
      }
   }

   public final void write(byte[] var1, int var2, int var3) throws IOException {
      try {
         this.zzbSt.write(var1, var2, var3);
      } catch (IOException var5) {
         throw this.zza(var5);
      }
   }

   public final void write(int var1) throws IOException {
      try {
         this.zzbSt.write(var1);
      } catch (IOException var3) {
         throw this.zza(var3);
      }
   }

   private final IOException zza(IOException var1) {
      zzah var2 = this.zzbSr;
      if (this.zzbSr != null) {
         if (Log.isLoggable("ChannelOutputStream", 2)) {
            Log.v("ChannelOutputStream", "Caught IOException, but channel has been closed. Translating to ChannelIOException.", var1);
         }

         return new ChannelIOException("Channel closed unexpectedly before stream was finished", var2.zzbSh, var2.zzbSi);
      } else {
         return var1;
      }
   }
}
