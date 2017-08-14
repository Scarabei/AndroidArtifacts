package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

final class zzfv implements zzbe {
   private final String zzJP;
   private final Context mContext;
   private final zzfy zzbGR;
   private final zzfx zzbGS;

   private zzfv(zzfy var1, Context var2, zzfx var3) {
      this.zzbGR = var1;
      this.mContext = var2.getApplicationContext();
      this.zzbGS = var3;
      String var10003 = VERSION.RELEASE;
      Locale var4;
      String var10004;
      if ((var4 = Locale.getDefault()) == null) {
         var10004 = null;
      } else if (var4.getLanguage() != null && var4.getLanguage().length() != 0) {
         StringBuilder var5;
         (var5 = new StringBuilder()).append(var4.getLanguage().toLowerCase());
         if (var4.getCountry() != null && var4.getCountry().length() != 0) {
            var5.append("-").append(var4.getCountry().toLowerCase());
         }

         var10004 = var5.toString();
      } else {
         var10004 = null;
      }

      String var9 = Build.ID;
      String var8 = Build.MODEL;
      String var7 = var10004;
      String var6 = var10003;
      String var11 = "4.00";
      String var10 = "GoogleTagManager";
      this.zzJP = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", var10, var11, var6, var7, var8, var9);
   }

   zzfv(Context var1, zzfx var2) {
      this(new zzfw(), var1, var2);
   }

   public final boolean zzBf() {
      NetworkInfo var1;
      if ((var1 = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo()) != null && var1.isConnected()) {
         return true;
      } else {
         zzdj.v("...no network connectivity");
         return false;
      }
   }

   public final void zzK(List var1) {
      int var2 = Math.min(var1.size(), 40);
      boolean var3 = true;

      for(int var4 = 0; var4 < var2; ++var4) {
         zzbx var5;
         URL var6;
         if ((var6 = zzd(var5 = (zzbx)var1.get(var4))) == null) {
            zzdj.zzaT("No destination: discarding hit.");
            this.zzbGS.zzb(var5);
         } else {
            InputStream var8 = null;

            try {
               HttpURLConnection var7 = this.zzbGR.zzc(var6);

               try {
                  if (var3) {
                     zzdo.zzbt(this.mContext);
                     var3 = false;
                  }

                  var7.setRequestProperty("User-Agent", this.zzJP);
                  int var9 = var7.getResponseCode();
                  var8 = var7.getInputStream();
                  if (var9 != 200) {
                     zzdj.zzaT((new StringBuilder(25)).append("Bad response: ").append(var9).toString());
                     this.zzbGS.zzc(var5);
                  } else {
                     this.zzbGS.zza(var5);
                  }
               } finally {
                  if (var8 != null) {
                     var8.close();
                  }

                  var7.disconnect();
               }
            } catch (IOException var14) {
               String var10001 = String.valueOf(var14.getClass().getSimpleName());
               String var10000;
               if (var10001.length() != 0) {
                  var10000 = "Exception sending hit: ".concat(var10001);
               } else {
                  String var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Exception sending hit: ");
               }

               zzdj.zzaT(var10000);
               zzdj.zzaT(var14.getMessage());
               this.zzbGS.zzc(var5);
            }
         }
      }

   }

   private static URL zzd(zzbx var0) {
      String var1 = var0.zzBo();

      try {
         return new URL(var1);
      } catch (MalformedURLException var2) {
         zzdj.e("Error trying to parse the GTM url.");
         return null;
      }
   }
}
