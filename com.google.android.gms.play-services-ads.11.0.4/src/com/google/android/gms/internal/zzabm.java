package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.js.zzl;
import com.google.android.gms.common.util.zzn;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzabm extends zzaan {
   private static final Object zzuF = new Object();
   private static zzabm zzUx;
   private final Context mContext;
   private final zzabl zzUy;
   private final zzmb zzUz;
   private final zzl zzLG;

   public static zzabm zza(Context var0, zzmb var1, zzabl var2) {
      Object var3 = zzuF;
      synchronized(zzuF) {
         if (zzUx == null) {
            Context var10000 = var0.getApplicationContext() != null ? var0.getApplicationContext() : var0;
            var0 = var10000;
            zzmo.initialize(var10000);
            zzUx = new zzabm(var0, var1, var2);
         }

         return zzUx;
      }
   }

   public final void zza(zzaax var1, zzaas var2) {
      zzafr.v("Nonagon code path entered in octagon");
      throw new IllegalArgumentException();
   }

   private static zzaai zza(Context var0, zzl var1, zzmb var2, zzabl var3, zzaae var4) {
      zzafr.zzaC("Starting ad request from service using: AFMA_getAd");
      zzme var34 = zzmo.zzCQ;
      zznb var5 = new zznb(((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var34)).booleanValue(), "load_ad", var4.zzvX.zzAs);
      zzmz var6;
      if (var4.versionCode > 10 && var4.zzSP != -1L) {
         var6 = var5.zzc(var4.zzSP);
         var5.zza(var6, "cts");
      }

      var6 = var5.zzdS();
      zzajm var7 = var3.zzUv.zzj(var0);
      Future var8 = var3.zzUu.zzo(var0);
      zzajm var9 = var3.zzUp.zzaD(var4.zzSA.packageName);
      zzajm var10 = var3.zzUw.zze(var4);
      Future var11 = com.google.android.gms.ads.internal.zzbs.zzbI().zzn(var0);
      Object var12 = new zzajh((Object)null);
      Bundle var13 = var4.zzSz.extras;
      boolean var14 = var4.zzSz.extras != null && var13.getString("_ad") != null;
      if (var4.zzSV && !var14) {
         var12 = var3.zzUs.zza(var4.applicationInfo);
      }

      Object var15 = new zzajh((Object)null);
      var34 = zzmo.zzDM;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var34)).booleanValue()) {
         var15 = var3.zzUw.zzz(var0);
      }

      Bundle var16 = null;
      if (var4.versionCode >= 4 && var4.zzSG != null) {
         var16 = var4.zzSG;
      }

      var34 = zzmo.zzDg;
      ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var34)).booleanValue();
      com.google.android.gms.ads.internal.zzbs.zzbz();
      if (zzagz.zzc(var0, var0.getPackageName(), "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager)var0.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
         zzafr.zzaC("Device is offline.");
      }

      String var17;
      if (var4.versionCode >= 7) {
         var17 = var4.zzSM;
      } else {
         var17 = UUID.randomUUID().toString();
      }

      zzabu var18 = new zzabu(var17, var4.applicationInfo.packageName);
      String var19;
      if (var4.zzSz.extras != null && (var19 = var4.zzSz.extras.getString("_ad")) != null) {
         return zzabt.zza(var0, var4, var19);
      } else {
         List var42 = var3.zzUq.zza(var4);
         var34 = zzmo.zzGb;
         Bundle var20 = (Bundle)zzaji.zza(var7, (Object)null, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var34)).longValue(), TimeUnit.MILLISECONDS);
         var34 = zzmo.zzEF;
         zzacn var21 = (zzacn)zzaji.zza(var8, (Object)null, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var34)).longValue(), TimeUnit.MILLISECONDS);
         var34 = zzmo.zzFK;
         Location var22 = (Location)zzaji.zza((Future)var12, (Object)null, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var34)).longValue(), TimeUnit.MILLISECONDS);
         var34 = zzmo.zzDN;
         Info var23 = (Info)zzaji.zza((Future)var15, (Object)null, ((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var34)).longValue(), TimeUnit.MILLISECONDS);
         String var24 = (String)zzaji.zza((Future)var10, (Object)null);
         String var25 = (String)zzaji.zza((Future)var9, (Object)null);
         zzacb var26;
         if ((var26 = (zzacb)zzaji.zza((Future)var11, (Object)null)) == null) {
            zzafr.zzaT("Error fetching device info. This is not recoverable.");
            return new zzaai(0);
         } else {
            zzabk var44;
            (var44 = new zzabk()).zzUj = var4;
            (var44 = var44).zzUk = var26;
            (var44 = var44).zzUh = var21;
            (var44 = var44).zzzV = var22;
            (var44 = var44).zzUg = var20;
            (var44 = var44).zzSB = var24;
            (var44 = var44).zzqi = var23;
            if (var42 == null) {
               var44.zzSN.clear();
            }

            var44.zzSN = var42;
            (var44 = var44).zzSG = var16;
            (var44 = var44).zzUi = var25;
            JSONObject var35 = var3.zzUo.zzf(var0);
            var44.zzUl = var35;
            boolean var43 = var3.zzUm;
            var44.zzUm = var43;
            JSONObject var27;
            if ((var27 = zzabt.zza(var0, var44)) == null) {
               return new zzaai(0);
            } else {
               if (var4.versionCode < 7) {
                  try {
                     var27.put("request_id", var17);
                  } catch (JSONException var39) {
                     ;
                  }
               }

               String var28 = var27.toString();
               var5.zza(var6, "arc");
               zzmz var29 = var5.zzdS();
               zzagz.zzZr.post(new zzabn(var1, var18, var5, var29, var28));

               zzaai var31;
               try {
                  zzaca var30;
                  zzaai var32;
                  try {
                     var30 = (zzaca)var18.zzgG().get(10L, TimeUnit.SECONDS);
                  } catch (Exception var40) {
                     var32 = new zzaai(0);
                     return var32;
                  }

                  if (var30 != null) {
                     if (var30.getErrorCode() != -2) {
                        var31 = new zzaai(var30.getErrorCode());
                        return var31;
                     }

                     if (var5.zzdW() != null) {
                        var5.zza(var5.zzdW(), "rur");
                     }

                     var31 = null;
                     if (!TextUtils.isEmpty(var30.zzgL())) {
                        var31 = zzabt.zza(var0, var4, var30.zzgL());
                     }

                     if (var31 == null && !TextUtils.isEmpty(var30.getUrl())) {
                        var31 = zza(var4, var0, var4.zzvT.zzaP, var30.getUrl(), var25, var30, var5, var3);
                     }

                     if (var31 == null) {
                        var31 = new zzaai(0);
                     }

                     var5.zza(var6, "tts");
                     var31.zzTB = var5.zzdU();
                     var32 = var31;
                     return var32;
                  }

                  var31 = new zzaai(0);
               } finally {
                  zzagz.zzZr.post(new zzabq(var3, var0, var18, var4));
               }

               return var31;
            }
         }
      }
   }

   private static void zza(String var0, Map var1, String var2, int var3) {
      if (zzafr.zzz(2)) {
         zzafr.v((new StringBuilder(39 + String.valueOf(var0).length())).append("Http Response: {\n  URL:\n    ").append(var0).append("\n  Headers:").toString());
         if (var1 != null) {
            Iterator var4 = var1.keySet().iterator();

            while(var4.hasNext()) {
               String var5 = (String)var4.next();
               zzafr.v((new StringBuilder(5 + String.valueOf(var5).length())).append("    ").append(var5).append(":").toString());

               String var10000;
               for(Iterator var6 = ((List)var1.get(var5)).iterator(); var6.hasNext(); zzafr.v(var10000)) {
                  String var7 = (String)var6.next();
                  String var10001 = String.valueOf(var7);
                  if (var10001.length() != 0) {
                     var10000 = "      ".concat(var10001);
                  } else {
                     String var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("      ");
                  }
               }
            }
         }

         zzafr.v("  Body:");
         if (var2 != null) {
            for(int var8 = 0; var8 < Math.min(var2.length(), 100000); var8 += 1000) {
               zzafr.v(var2.substring(var8, Math.min(var2.length(), var8 + 1000)));
            }
         } else {
            zzafr.v("    null");
         }

         zzafr.v((new StringBuilder(34)).append("  Response Code:\n    ").append(var3).append("\n}").toString());
      }

   }

   public static zzaai zza(zzaae var0, Context var1, String var2, String var3, String var4, zzaca var5, zznb var6, zzabl var7) {
      zzmz var8 = var6 != null ? var6.zzdS() : null;

      String var10000;
      String var10001;
      String var10002;
      try {
         zzaby var9 = new zzaby(var0, var5.zzgI());
         var10001 = String.valueOf(var3);
         if (var10001.length() != 0) {
            var10000 = "AdRequestServiceImpl: Sending request: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("AdRequestServiceImpl: Sending request: ");
         }

         zzafr.zzaC(var10000);
         URL var10 = new URL(var3);
         int var14 = 0;
         long var15 = com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime();

         while(true) {
            HttpURLConnection var17 = (HttpURLConnection)var10.openConnection();

            try {
               com.google.android.gms.ads.internal.zzbs.zzbz().zza(var1, var2, false, var17);
               if (!TextUtils.isEmpty(var4) && var5.zzgK()) {
                  var17.addRequestProperty("x-afma-drt-cookie", var4);
               }

               String var18 = var0.zzSW;
               if (!TextUtils.isEmpty(var0.zzSW)) {
                  zzafr.zzaC("Sending webview cookie in ad request header.");
                  var17.addRequestProperty("Cookie", var18);
               }

               if (var5 != null && !TextUtils.isEmpty(var5.zzgJ())) {
                  var17.setDoOutput(true);
                  byte[] var19 = var5.zzgJ().getBytes();
                  var17.setFixedLengthStreamingMode(var19.length);
                  BufferedOutputStream var20 = null;

                  try {
                     (var20 = new BufferedOutputStream(var17.getOutputStream())).write(var19);
                  } finally {
                     zzn.closeQuietly(var20);
                  }
               }

               int var13 = var17.getResponseCode();
               Map var12 = var17.getHeaderFields();
               String var41;
               if (var13 >= 200 && var13 < 300) {
                  var41 = var10.toString();
                  InputStreamReader var44 = null;

                  String var11;
                  try {
                     var44 = new InputStreamReader(var17.getInputStream());
                     com.google.android.gms.ads.internal.zzbs.zzbz();
                     var11 = zzagz.zza(var44);
                  } finally {
                     zzn.closeQuietly(var44);
                  }

                  zza(var41, var12, var11, var13);
                  var9.zza(var41, var12, var11);
                  if (var6 != null) {
                     var6.zza(var8, "ufe");
                  }

                  zzaai var21 = var9.zze(var15);
                  return var21;
               }

               zza(var10.toString(), var12, (String)null, var13);
               zzaai var42;
               if (var13 < 300 || var13 >= 400) {
                  zzafr.zzaT((new StringBuilder(46)).append("Received error HTTP response code: ").append(var13).toString());
                  var42 = new zzaai(0);
                  return var42;
               }

               if (TextUtils.isEmpty(var41 = var17.getHeaderField("Location"))) {
                  zzafr.zzaT("No location header to follow redirect.");
                  zzaai var43 = new zzaai(0);
                  return var43;
               }

               var10 = new URL(var41);
               ++var14;
               zzme var24 = zzmo.zzGG;
               if (var14 > ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var24)).intValue()) {
                  zzafr.zzaT("Too many redirects.");
                  var42 = new zzaai(0);
                  return var42;
               }

               var9.zzh(var12);
            } finally {
               var17.disconnect();
            }

            if (var7 != null) {
               ;
            }
         }
      } catch (IOException var40) {
         var10001 = String.valueOf(var40.getMessage());
         if (var10001.length() != 0) {
            var10000 = "Error while connecting to ad server: ".concat(var10001);
         } else {
            var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Error while connecting to ad server: ");
         }

         zzafr.zzaT(var10000);
         return new zzaai(2);
      }
   }

   private zzabm(Context var1, zzmb var2, zzabl var3) {
      this(var1, var2, var3, com.google.android.gms.ads.internal.zzbs.zzbO().zzb(var1, new zzaje(11020208, 11020208, true)).zzff());
   }

   private zzabm(Context var1, zzmb var2, zzabl var3, zzl var4) {
      this.mContext = var1;
      this.zzUy = var3;
      this.zzUz = var2;
      this.zzLG = var4;
   }

   public final zzaai zzc(zzaae var1) {
      return zza(this.mContext, this.zzLG, this.zzUz, this.zzUy, var1);
   }

   public final void zza(zzaae var1, zzaap var2) {
      com.google.android.gms.ads.internal.zzbs.zzbD().zzd(this.mContext, var1.zzvT);
      zzajm var3 = zzagt.zza((Runnable)(new zzabr(this, var1, var2)));
      com.google.android.gms.ads.internal.zzbs.zzbP().zzie();
      com.google.android.gms.ads.internal.zzbs.zzbP().getHandler().postDelayed(new zzabs(this, var3), 60000L);
   }
}
