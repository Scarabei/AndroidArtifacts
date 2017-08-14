package com.google.android.gms.internal;

import com.google.android.gms.common.util.zzn;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class zzaav implements Runnable {
   // $FF: synthetic field
   private OutputStream zzTS;
   // $FF: synthetic field
   private byte[] zzTT;

   zzaav(zzaau var1, OutputStream var2, byte[] var3) {
      this.zzTS = var2;
      this.zzTT = var3;
      super();
   }

   public final void run() {
      DataOutputStream var1 = null;
      boolean var6 = false;

      label62: {
         try {
            var6 = true;
            (var1 = new DataOutputStream(this.zzTS)).writeInt(this.zzTT.length);
            var1.write(this.zzTT);
            var6 = false;
            break label62;
         } catch (IOException var7) {
            zzafr.zzb("Error transporting the ad response", var7);
            com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var7, (String)"LargeParcelTeleporter.pipeData.1");
            var6 = false;
         } finally {
            if (var6) {
               if (var1 == null) {
                  zzn.closeQuietly(this.zzTS);
               } else {
                  zzn.closeQuietly(var1);
               }

            }
         }

         if (var1 == null) {
            zzn.closeQuietly(this.zzTS);
            return;
         }

         zzn.closeQuietly(var1);
         return;
      }

      zzn.closeQuietly(var1);
   }
}
