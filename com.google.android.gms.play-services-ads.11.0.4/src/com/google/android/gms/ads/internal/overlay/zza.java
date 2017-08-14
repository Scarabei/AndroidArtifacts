package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zza {
   public static boolean zza(Context var0, zzc var1, zzag var2) {
      if (var1 == null) {
         zzafr.zzaT("No intent data for launcher overlay.");
         return false;
      } else {
         zzmo.initialize(var0);
         if (var1.intent != null) {
            return zza(var0, var1.intent, var2);
         } else {
            Intent var3 = new Intent();
            if (TextUtils.isEmpty(var1.url)) {
               zzafr.zzaT("Open GMSG did not contain a URL.");
               return false;
            } else {
               if (!TextUtils.isEmpty(var1.mimeType)) {
                  var3.setDataAndType(Uri.parse(var1.url), var1.mimeType);
               } else {
                  var3.setData(Uri.parse(var1.url));
               }

               var3.setAction("android.intent.action.VIEW");
               if (!TextUtils.isEmpty(var1.packageName)) {
                  var3.setPackage(var1.packageName);
               }

               if (!TextUtils.isEmpty(var1.zzOh)) {
                  String[] var4;
                  if ((var4 = var1.zzOh.split("/", 2)).length < 2) {
                     String var10001 = String.valueOf(var1.zzOh);
                     String var10000;
                     if (var10001.length() != 0) {
                        var10000 = "Could not parse component name from open GMSG: ".concat(var10001);
                     } else {
                        String var10002 = new String;
                        var10000 = var10002;
                        var10002.<init>("Could not parse component name from open GMSG: ");
                     }

                     zzafr.zzaT(var10000);
                     return false;
                  }

                  var3.setClassName(var4[0], var4[1]);
               }

               String var8 = var1.zzOi;
               if (!TextUtils.isEmpty(var1.zzOi)) {
                  int var5 = 0;

                  try {
                     var5 = Integer.parseInt(var8);
                  } catch (NumberFormatException var7) {
                     zzafr.zzaT("Could not parse intent flags.");
                  }

                  var3.addFlags(var5);
               }

               zzme var6 = zzmo.zzGq;
               if (((Boolean)zzbs.zzbL().zzd(var6)).booleanValue()) {
                  var3.addFlags(268435456);
                  var3.putExtra("android.support.customtabs.extra.user_opt_out", true);
               } else {
                  var6 = zzmo.zzGp;
                  if (((Boolean)zzbs.zzbL().zzd(var6)).booleanValue()) {
                     zzbs.zzbz();
                     zzagz.zzc(var0, var3);
                  }
               }

               return zza(var0, var3, var2);
            }
         }
      }
   }

   private static boolean zza(Context var0, Intent var1, zzag var2) {
      try {
         String var10001 = String.valueOf(var1.toURI());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Launching an intent: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Launching an intent: ");
         }

         zzafr.v(var10000);
         zzbs.zzbz();
         zzagz.zzb(var0, var1);
         if (var2 != null) {
            var2.zzan();
         }

         return true;
      } catch (ActivityNotFoundException var3) {
         zzafr.zzaT(var3.getMessage());
         return false;
      }
   }
}
