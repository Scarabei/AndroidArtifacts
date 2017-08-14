package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

final class zzhi {
   private ByteArrayOutputStream zzzb = new ByteArrayOutputStream(4096);
   private Base64OutputStream zzzc;

   public zzhi() {
      this.zzzc = new Base64OutputStream(this.zzzb, 10);
   }

   public final void write(byte[] var1) throws IOException {
      this.zzzc.write(var1);
   }

   public final String toString() {
      try {
         this.zzzc.close();
      } catch (IOException var7) {
         zzafr.zzb("HashManager: Unable to convert to Base64.", var7);
      }

      String var2;
      try {
         this.zzzb.close();
         String var1 = this.zzzb.toString();
         return var1;
      } catch (IOException var8) {
         zzafr.zzb("HashManager: Unable to convert to Base64.", var8);
         var2 = "";
      } finally {
         this.zzzb = null;
         this.zzzc = null;
      }

      return var2;
   }
}
