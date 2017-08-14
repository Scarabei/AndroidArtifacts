package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.util.zzn;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class zzcon implements Runnable {
   // $FF: synthetic field
   private InputStream zzbxy;
   // $FF: synthetic field
   private OutputStream zzbxz;
   // $FF: synthetic field
   private long zzbxA;
   // $FF: synthetic field
   private OutputStream zzbxB;
   // $FF: synthetic field
   private zzcom zzbxC;

   zzcon(zzcom var1, InputStream var2, OutputStream var3, long var4, OutputStream var6) {
      this.zzbxC = var1;
      this.zzbxy = var2;
      this.zzbxz = var3;
      this.zzbxA = var4;
      this.zzbxB = var6;
      super();
   }

   public final void run() {
      zzcom.zza(this.zzbxC, this.zzbxy);
      boolean var1 = false;
      boolean var6 = false;

      label45: {
         try {
            var6 = true;
            zzn.zza(this.zzbxy, this.zzbxz, false, 65536);
            var6 = false;
            break label45;
         } catch (IOException var7) {
            var1 = true;
            if (!zzcom.zza(this.zzbxC)) {
               Log.w("NearbyConnections", String.format("Exception copying stream for Payload %d", this.zzbxA), var7);
               var6 = false;
            } else {
               Log.d("NearbyConnections", String.format("Terminating copying stream for Payload %d due to shutdown of OutgoingPayloadStreamer.", this.zzbxA));
               var6 = false;
            }
         } finally {
            if (var6) {
               zzn.closeQuietly(this.zzbxy);
               zzcom.zza(this.zzbxC, this.zzbxB, var1, this.zzbxA);
               zzn.closeQuietly(this.zzbxz);
               zzcom.zza(this.zzbxC, (InputStream)null);
            }
         }

         zzn.closeQuietly(this.zzbxy);
         zzcom.zza(this.zzbxC, this.zzbxB, true, this.zzbxA);
         zzn.closeQuietly(this.zzbxz);
         zzcom.zza(this.zzbxC, (InputStream)null);
         return;
      }

      zzn.closeQuietly(this.zzbxy);
      zzcom.zza(this.zzbxC, this.zzbxB, false, this.zzbxA);
      zzn.closeQuietly(this.zzbxz);
      zzcom.zza(this.zzbxC, (InputStream)null);
   }
}
