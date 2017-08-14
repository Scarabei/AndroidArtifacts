package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class zzei {
   private static zzei zzbFF;
   private volatile zzei.zza zzbFG;
   private volatile String zzbDw;
   private volatile String zzbFH;
   private volatile String zzbFI;

   zzei() {
      this.zzbFG = zzei.zza.zzbFJ;
      this.zzbFH = null;
      this.zzbDw = null;
      this.zzbFI = null;
   }

   static zzei zzBD() {
      Class var0 = zzei.class;
      synchronized(zzei.class) {
         if (zzbFF == null) {
            zzbFF = new zzei();
         }

         return zzbFF;
      }
   }

   final synchronized boolean zzr(Uri var1) {
      String var2;
      try {
         var2 = URLDecoder.decode(var1.toString(), "UTF-8");
      } catch (UnsupportedEncodingException var3) {
         return false;
      }

      String var10000;
      String var10001;
      String var10002;
      if (var2.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
         var10001 = String.valueOf(var2);
         if (var10001.length() != 0) {
            var10000 = "Container preview url: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Container preview url: ");
         }

         zzdj.v(var10000);
         if (var2.matches(".*?&gtm_debug=x$")) {
            this.zzbFG = zzei.zza.zzbFL;
         } else {
            this.zzbFG = zzei.zza.zzbFK;
         }

         this.zzbFI = var1.getQuery().replace("&gtm_debug=x", "");
         if (this.zzbFG == zzei.zza.zzbFK || this.zzbFG == zzei.zza.zzbFL) {
            var10001 = String.valueOf("/r?");
            var10002 = String.valueOf(this.zzbFI);
            if (var10002.length() != 0) {
               var10001 = var10001.concat(var10002);
            } else {
               String var10003 = new String;
               var10002 = var10001;
               var10001 = var10003;
               var10003.<init>(var10002);
            }

            this.zzbFH = var10001;
         }

         this.zzbDw = zzfq(this.zzbFI);
         return true;
      } else if (var2.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
         if (zzfq(var1.getQuery()).equals(this.zzbDw)) {
            var10001 = String.valueOf(this.zzbDw);
            if (var10001.length() != 0) {
               var10000 = "Exit preview mode for container: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Exit preview mode for container: ");
            }

            zzdj.v(var10000);
            this.zzbFG = zzei.zza.zzbFJ;
            this.zzbFH = null;
            return true;
         } else {
            return false;
         }
      } else {
         var10001 = String.valueOf(var2);
         if (var10001.length() != 0) {
            var10000 = "Invalid preview uri: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Invalid preview uri: ");
         }

         zzdj.zzaT(var10000);
         return false;
      }
   }

   final zzei.zza zzBE() {
      return this.zzbFG;
   }

   final String zzBF() {
      return this.zzbFH;
   }

   final String getContainerId() {
      return this.zzbDw;
   }

   private static String zzfq(String var0) {
      return var0.split("&")[0].split("=")[1];
   }

   static enum zza {
      zzbFJ,
      zzbFK,
      zzbFL;
   }
}
