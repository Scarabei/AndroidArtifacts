package com.google.android.gms.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

final class zzcwj implements zzcus {
   private final String zzJP;
   private final Context mContext;
   private final zzcwm zzbJc;
   private final zzcwl zzbJd;

   private zzcwj(zzcwm var1, Context var2, zzcwl var3) {
      this.zzbJc = var1;
      this.mContext = var2.getApplicationContext();
      this.zzbJd = var3;
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
      String var11 = "5.05";
      String var10 = "GoogleTagManager";
      this.zzJP = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", var10, var11, var6, var7, var8, var9);
   }

   zzcwj(Context var1, zzcwl var2) {
      this(new zzcwk(), var1, var2);
   }

   public final boolean zzBf() {
      NetworkInfo var1;
      if ((var1 = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo()) != null && var1.isConnected()) {
         return true;
      } else {
         zzcvk.v("...no network connectivity");
         return false;
      }
   }

   public final void zzK(List var1) {
      int var2 = Math.min(var1.size(), 40);
      boolean var3 = true;

      for(int var4 = 0; var4 < var2; ++var4) {
         zzcuw var5;
         URL var6 = zzd(var5 = (zzcuw)var1.get(var4));
         String var7 = var5.zzCo();
         Map var8 = var5.zzCp();
         String var9 = var5.zzCq();
         if (var6 == null) {
            zzcvk.zzaT("No destination: discarding hit.");
            this.zzbJd.zzb(var5);
         } else {
            InputStream var11 = null;

            String var13;
            String var14;
            try {
               HttpURLConnection var10 = this.zzbJc.zzc(var6);
               boolean var21 = false;

               label438: {
                  label423: {
                     try {
                        var21 = true;
                        if (var3) {
                           zzcvm.zzbt(this.mContext);
                           var3 = false;
                        }

                        var10.setRequestProperty("User-Agent", this.zzJP);
                        if (var8 != null) {
                           Iterator var12 = var8.entrySet().iterator();

                           while(var12.hasNext()) {
                              Entry var30 = (Entry)var12.next();
                              var10.setRequestProperty((String)var30.getKey(), (String)var30.getValue());
                           }
                        }

                        if (var7 != null) {
                           if (!var7.equals("GET") && !var7.equals("HEAD") && !var7.equals("POST") && !var7.equals("PUT")) {
                              zzcvk.zzaT(String.format("Unrecongnized HTTP method %s. Supported methods are GET, HEAD, PUT and/or POST", var7));
                              this.zzbJd.zzb(var5);
                              var21 = false;
                              break label438;
                           }

                           byte var31 = -1;
                           switch(var7.hashCode()) {
                           case 70454:
                              if (var7.equals("GET")) {
                                 var31 = 0;
                              }
                              break;
                           case 79599:
                              if (var7.equals("PUT")) {
                                 var31 = 3;
                              }
                              break;
                           case 2213344:
                              if (var7.equals("HEAD")) {
                                 var31 = 1;
                              }
                              break;
                           case 2461856:
                              if (var7.equals("POST")) {
                                 var31 = 2;
                              }
                           }

                           switch(var31) {
                           case 0:
                           case 1:
                              if (var9 != null) {
                                 zzcvk.zzaT(String.format("Body of %s hit is ignored: %s.", var7, var9));
                              }

                              var10.setRequestMethod(var7);
                              break;
                           case 2:
                           case 3:
                              var10.setRequestMethod(var7);
                              if (var9 != null) {
                                 var10.setDoOutput(true);
                                 byte[] var32 = var9.getBytes(Charset.forName("UTF-8"));
                                 var10.setFixedLengthStreamingMode(var32.length);
                                 BufferedOutputStream var15;
                                 (var15 = new BufferedOutputStream(var10.getOutputStream())).write(var32);
                                 var15.flush();
                                 var15.close();
                              }
                           }

                           int var29;
                           if ((var29 = var10.getResponseCode()) != 200) {
                              var13 = String.valueOf(var6);
                              zzcvk.zzaT((new StringBuilder(39 + String.valueOf(var13).length())).append("Bad response received for ").append(var13).append(": ").append(var29).toString());
                              StringBuilder var34 = new StringBuilder();
                              BufferedReader var33 = null;
                              boolean var25 = false;

                              try {
                                 var25 = true;
                                 var33 = new BufferedReader(new InputStreamReader(var10.getErrorStream()));

                                 while(true) {
                                    if ((var14 = var33.readLine()) == null) {
                                       String var10001 = String.valueOf(var34.toString());
                                       String var10000;
                                       if (var10001.length() != 0) {
                                          var10000 = "Error Message: ".concat(var10001);
                                       } else {
                                          String var10002 = new String;
                                          var10000 = var10002;
                                          var10002.<init>("Error Message: ");
                                       }

                                       zzcvk.zzaT(var10000);
                                       var25 = false;
                                       break;
                                    }

                                    var34.append(var14);
                                 }
                              } finally {
                                 if (var25) {
                                    if (var33 != null) {
                                       var33.close();
                                    }

                                 }
                              }

                              var33.close();
                              this.zzbJd.zzc(var5);
                              var21 = false;
                           } else {
                              var11 = var10.getInputStream();
                              var13 = String.valueOf(var6);
                              zzcvk.v((new StringBuilder(23 + String.valueOf(var13).length() + String.valueOf(var7).length())).append("Hit sent to ").append(var13).append("(method = ").append(var7).append(")").toString());
                              this.zzbJd.zza(var5);
                              var21 = false;
                           }
                           break label423;
                        }

                        zzcvk.zzaT(String.format("Hit %d retrieved from the store has null HTTP method.", var5.zzBm()));
                        this.zzbJd.zzb(var5);
                        var21 = false;
                     } finally {
                        if (var21) {
                           if (var11 != null) {
                              var11.close();
                           }

                           var10.disconnect();
                        }
                     }

                     var10.disconnect();
                     continue;
                  }

                  if (var11 != null) {
                     var11.close();
                  }

                  var10.disconnect();
                  continue;
               }

               var10.disconnect();
            } catch (IOException var28) {
               var13 = String.valueOf(var6);
               var14 = String.valueOf(var28.getClass().getSimpleName());
               zzcvk.zzaT((new StringBuilder(27 + String.valueOf(var13).length() + String.valueOf(var14).length())).append("Exception sending hit to ").append(var13).append(": ").append(var14).toString());
               zzcvk.zzaT(var28.getMessage());
               this.zzbJd.zzc(var5);
            }
         }
      }

   }

   private static URL zzd(zzcuw var0) {
      String var1 = var0.zzBo();

      try {
         return new URL(var1);
      } catch (MalformedURLException var2) {
         zzcvk.e("Error trying to parse the GTM url.");
         return null;
      }
   }
}
