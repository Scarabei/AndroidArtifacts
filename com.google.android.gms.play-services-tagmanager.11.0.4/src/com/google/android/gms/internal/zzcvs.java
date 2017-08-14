package com.google.android.gms.internal;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class zzcvs {
   private static zzcvs zzbID;
   private volatile int zzbIE;
   private volatile String zzbDw;
   private volatile String zzbFI;

   zzcvs() {
      this.zzbIE = zzcvs.zza.zzbIF;
      this.zzbFI = null;
      this.zzbDw = null;
   }

   public static zzcvs zzCw() {
      Class var0 = zzcvs.class;
      synchronized(zzcvs.class) {
         if (zzbID == null) {
            zzbID = new zzcvs();
         }

         return zzbID;
      }
   }

   public final synchronized boolean zzc(String var1, Uri var2) {
      String var3;
      String var5;
      try {
         var3 = URLDecoder.decode(var2.toString(), "UTF-8");
      } catch (UnsupportedEncodingException var7) {
         var5 = String.valueOf(var7);
         zzcvk.zzaT((new StringBuilder(32 + String.valueOf(var5).length())).append("Error decoding the preview url: ").append(var5).toString());
         return false;
      }

      String var10000;
      String var10001;
      String var10002;
      if (!var3.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\S+")) {
         var10001 = String.valueOf(var3);
         if (var10001.length() != 0) {
            var10000 = "Bad preview url: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Bad preview url: ");
         }

         zzcvk.zzaT(var10000);
         return false;
      } else {
         String var4 = var2.getQueryParameter("id");
         var5 = var2.getQueryParameter("gtm_auth");
         String var6 = var2.getQueryParameter("gtm_preview");
         if (!var1.equals(var4)) {
            zzcvk.zzaT("Preview fails (container doesn't match the container specified by the asset)");
            return false;
         } else if (var4 != null && var4.length() > 0) {
            if (var6 != null && var6.length() == 0) {
               if (!var4.equals(this.zzbDw) || this.zzbIE == zzcvs.zza.zzbIF) {
                  zzcvk.zzaT("Error in exiting preview mode. The container is not in preview.");
                  return false;
               }

               var10001 = String.valueOf(this.zzbDw);
               if (var10001.length() != 0) {
                  var10000 = "Exit preview mode for container: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Exit preview mode for container: ");
               }

               zzcvk.v(var10000);
               this.zzbIE = zzcvs.zza.zzbIF;
               this.zzbDw = null;
               this.zzbFI = null;
            } else {
               if (var6 == null || var6.length() <= 0 || var5 == null || var5.length() <= 0) {
                  var10001 = String.valueOf(var3);
                  if (var10001.length() != 0) {
                     var10000 = "Bad preview url: ".concat(var10001);
                  } else {
                     var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("Bad preview url: ");
                  }

                  zzcvk.zzaT(var10000);
                  return false;
               }

               this.zzbIE = zzcvs.zza.zzbIG;
               this.zzbFI = var2.getQuery();
               this.zzbDw = var4;
            }

            return true;
         } else {
            var10001 = String.valueOf(var3);
            if (var10001.length() != 0) {
               var10000 = "Bad preview url: ".concat(var10001);
            } else {
               var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Bad preview url: ");
            }

            zzcvk.zzaT(var10000);
            return false;
         }
      }
   }

   public final boolean isPreview() {
      return this.zzbIE == zzcvs.zza.zzbIG;
   }

   public final boolean zzfG(String var1) {
      return this.isPreview() && this.zzbDw.equals(var1);
   }

   public final String zzCx() {
      return this.zzbFI;
   }

   public final String getContainerId() {
      return this.zzbDw;
   }

   static enum zza {
      zzbIF = 1,
      zzbIG = 2;

      // $FF: synthetic field
      private static final int[] zzbIH = new int[]{1, 2};
   }
}
