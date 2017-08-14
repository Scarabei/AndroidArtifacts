package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzn {
   public static void closeQuietly(Closeable var0) {
      if (var0 != null) {
         try {
            var0.close();
            return;
         } catch (IOException var1) {
            ;
         }
      }

   }

   public static void zza(ParcelFileDescriptor var0) {
      if (var0 != null) {
         try {
            var0.close();
            return;
         } catch (IOException var1) {
            ;
         }
      }

   }

   public static long zza(InputStream var0, OutputStream var1, boolean var2) throws IOException {
      return zza(var0, var1, var2, 1024);
   }

   public static long zza(InputStream var0, OutputStream var1, boolean var2, int var3) throws IOException {
      byte[] var4 = new byte[var3];
      long var5 = 0L;

      int var7;
      try {
         while((var7 = var0.read(var4, 0, var4.length)) != -1) {
            var5 += (long)var7;
            var1.write(var4, 0, var7);
         }
      } finally {
         if (var2) {
            closeQuietly(var0);
            closeQuietly(var1);
         }

      }

      return var5;
   }

   public static byte[] zza(InputStream var0, boolean var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      zza(var0, var2, var1);
      return var2.toByteArray();
   }
}
