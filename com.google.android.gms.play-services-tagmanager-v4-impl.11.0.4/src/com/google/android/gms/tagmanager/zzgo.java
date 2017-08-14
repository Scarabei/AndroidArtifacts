package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

final class zzgo {
   static zzea zza(zzea var0, int... var1) {
      zzea var2 = var0;
      int[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         int var6 = var3[var5];
         zzea var10000;
         if (!(zzgk.zzg((com.google.android.gms.internal.zzbr)var2.getObject()) instanceof String)) {
            zzdj.e("Escaping can only be applied to strings.");
            var10000 = var2;
         } else {
            switch(var6) {
            case 12:
               var10000 = zza(var2);
               break;
            default:
               zzdj.e((new StringBuilder(39)).append("Unsupported Value Escaping: ").append(var6).toString());
               var10000 = var2;
            }
         }

         var2 = var10000;
      }

      return var2;
   }

   static String zzfC(String var0) throws UnsupportedEncodingException {
      return URLEncoder.encode(var0, "UTF-8").replaceAll("\\+", "%20");
   }

   private static zzea zza(zzea var0) {
      try {
         String var1 = zzfC(zzgk.zzb((com.google.android.gms.internal.zzbr)var0.getObject()));
         return new zzea(zzgk.zzI(var1), var0.zzBz());
      } catch (UnsupportedEncodingException var2) {
         zzdj.zzb("Escape URI: unsupported encoding", var2);
         return var0;
      }
   }
}
