package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.util.zzn;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class zzcom {
   private final ExecutorService zzbxv = Executors.newSingleThreadExecutor();
   private volatile InputStream zzbxw = null;
   private volatile boolean zzbxx = false;

   final void zza(InputStream var1, OutputStream var2, OutputStream var3, long var4) {
      this.zzbxv.execute(new zzcon(this, var1, var2, var4, var3));
   }

   private static void zza(OutputStream var0, boolean var1, long var2) {
      try {
         var0.write(var1 ? 1 : 0);
         return;
      } catch (IOException var8) {
         Log.w("NearbyConnections", String.format("Unable to deliver status for Payload %d", var2), var8);
      } finally {
         zzn.closeQuietly(var0);
      }

   }

   final void shutdown() {
      this.zzbxx = true;
      this.zzbxv.shutdownNow();
      zzn.closeQuietly(this.zzbxw);
   }

   // $FF: synthetic method
   static InputStream zza(zzcom var0, InputStream var1) {
      return var0.zzbxw = var1;
   }

   // $FF: synthetic method
   static boolean zza(zzcom var0) {
      return var0.zzbxx;
   }

   // $FF: synthetic method
   static void zza(zzcom var0, OutputStream var1, boolean var2, long var3) {
      zza(var1, var2, var3);
   }
}
