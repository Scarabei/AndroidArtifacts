package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class zzr {
   private static String zzaJW = null;
   private static final int zzaJX = Process.myPid();

   public static String zzsf() {
      if (zzaJW == null) {
         zzaJW = zzaD(zzaJX);
      }

      return zzaJW;
   }

   private static String zzaD(int var0) {
      if (var0 <= 0) {
         return null;
      } else {
         BufferedReader var1 = null;
         String var2 = null;

         try {
            ThreadPolicy var3 = StrictMode.allowThreadDiskReads();

            try {
               var1 = new BufferedReader(new FileReader((new StringBuilder(25)).append("/proc/").append(var0).append("/cmdline").toString()));
            } finally {
               StrictMode.setThreadPolicy(var3);
            }

            var2 = var1.readLine().trim();
         } catch (IOException var13) {
            ;
         } finally {
            zzn.closeQuietly(var1);
         }

         return var2;
      }
   }
}
