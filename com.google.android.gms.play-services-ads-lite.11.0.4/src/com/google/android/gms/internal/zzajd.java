package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zzzn
public final class zzajd implements zzajb {
   @Nullable
   private final String zzJP;

   public zzajd() {
      this((String)null);
   }

   public zzajd(@Nullable String var1) {
      this.zzJP = var1;
   }

   @WorkerThread
   public final void zzaN(String var1) {
      String var2;
      try {
         String var10001 = String.valueOf(var1);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Pinging URL: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Pinging URL: ");
         }

         zzajc.zzaC(var10000);
         HttpURLConnection var13 = (HttpURLConnection)(new URL(var1)).openConnection();

         try {
            zzji.zzds();
            zzaiy.zza(true, var13, this.zzJP);
            int var3;
            if ((var3 = var13.getResponseCode()) < 200 || var3 >= 300) {
               zzajc.zzaT((new StringBuilder(65 + String.valueOf(var1).length())).append("Received non-success response code ").append(var3).append(" from pinging URL: ").append(var1).toString());
            }
         } finally {
            var13.disconnect();
         }

      } catch (IndexOutOfBoundsException var10) {
         var2 = String.valueOf(var10.getMessage());
         zzajc.zzaT((new StringBuilder(32 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Error while parsing ping URL: ").append(var1).append(". ").append(var2).toString());
      } catch (IOException var11) {
         var2 = String.valueOf(var11.getMessage());
         zzajc.zzaT((new StringBuilder(27 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Error while pinging URL: ").append(var1).append(". ").append(var2).toString());
      } catch (RuntimeException var12) {
         var2 = String.valueOf(var12.getMessage());
         zzajc.zzaT((new StringBuilder(27 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("Error while pinging URL: ").append(var1).append(". ").append(var2).toString());
      }
   }
}
