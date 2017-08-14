package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.Callable;

final class zzfx implements Callable {
   // $FF: synthetic field
   private ParcelFileDescriptor zzbTp;
   // $FF: synthetic field
   private byte[] zzbKQ;

   zzfx(zzfw var1, ParcelFileDescriptor var2, byte[] var3) {
      this.zzbTp = var2;
      this.zzbKQ = var3;
      super();
   }

   private final Boolean zzDX() {
      if (Log.isLoggable("WearableClient", 3)) {
         String var1 = String.valueOf(this.zzbTp);
         Log.d("WearableClient", (new StringBuilder(36 + String.valueOf(var1).length())).append("processAssets: writing data to FD : ").append(var1).toString());
      }

      AutoCloseOutputStream var17 = new AutoCloseOutputStream(this.zzbTp);
      boolean var11 = false;

      Boolean var18;
      String var3;
      label119: {
         String var2;
         try {
            var11 = true;
            var17.write(this.zzbKQ);
            var17.flush();
            if (Log.isLoggable("WearableClient", 3)) {
               var2 = String.valueOf(this.zzbTp);
               Log.d("WearableClient", (new StringBuilder(27 + String.valueOf(var2).length())).append("processAssets: wrote data: ").append(var2).toString());
            }

            var18 = true;
            var11 = false;
            break label119;
         } catch (IOException var15) {
            var3 = String.valueOf(this.zzbTp);
            Log.w("WearableClient", (new StringBuilder(36 + String.valueOf(var3).length())).append("processAssets: writing data failed: ").append(var3).toString());
            var11 = false;
         } finally {
            if (var11) {
               try {
                  if (Log.isLoggable("WearableClient", 3)) {
                     String var5 = String.valueOf(this.zzbTp);
                     Log.d("WearableClient", (new StringBuilder(24 + String.valueOf(var5).length())).append("processAssets: closing: ").append(var5).toString());
                  }

                  var17.close();
               } catch (IOException var13) {
                  ;
               }

            }
         }

         try {
            if (Log.isLoggable("WearableClient", 3)) {
               var2 = String.valueOf(this.zzbTp);
               Log.d("WearableClient", (new StringBuilder(24 + String.valueOf(var2).length())).append("processAssets: closing: ").append(var2).toString());
            }

            var17.close();
         } catch (IOException var12) {
            ;
         }

         return false;
      }

      try {
         if (Log.isLoggable("WearableClient", 3)) {
            var3 = String.valueOf(this.zzbTp);
            Log.d("WearableClient", (new StringBuilder(24 + String.valueOf(var3).length())).append("processAssets: closing: ").append(var3).toString());
         }

         var17.close();
      } catch (IOException var14) {
         ;
      }

      return var18;
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zzDX();
   }
}
