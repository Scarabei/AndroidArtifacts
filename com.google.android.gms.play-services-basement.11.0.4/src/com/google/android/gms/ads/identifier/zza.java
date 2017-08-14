package com.google.android.gms.ads.identifier;

import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

final class zza extends Thread {
   // $FF: synthetic field
   private String zzsD;

   zza(AdvertisingIdClient var1, String var2) {
      this.zzsD = var2;
      super();
   }

   public final void run() {
      new zzb();
      String var1 = this.zzsD;

      String var3;
      try {
         HttpURLConnection var2 = (HttpURLConnection)(new URL(var1)).openConnection();

         try {
            int var12;
            if ((var12 = var2.getResponseCode()) < 200 || var12 >= 300) {
               Log.w("HttpUrlPinger", (new StringBuilder(65 + String.valueOf(var1).length())).append("Received non-success response code ").append(var12).append(" from pinging URL: ").append(var1).toString());
            }
         } finally {
            var2.disconnect();
         }

      } catch (IndexOutOfBoundsException var10) {
         var3 = String.valueOf(var10.getMessage());
         Log.w("HttpUrlPinger", (new StringBuilder(32 + String.valueOf(var1).length() + String.valueOf(var3).length())).append("Error while parsing ping URL: ").append(var1).append(". ").append(var3).toString(), var10);
      } catch (RuntimeException | IOException var11) {
         var3 = String.valueOf(var11.getMessage());
         Log.w("HttpUrlPinger", (new StringBuilder(27 + String.valueOf(var1).length() + String.valueOf(var3).length())).append("Error while pinging URL: ").append(var1).append(". ").append(var3).toString(), var11);
      }
   }
}
