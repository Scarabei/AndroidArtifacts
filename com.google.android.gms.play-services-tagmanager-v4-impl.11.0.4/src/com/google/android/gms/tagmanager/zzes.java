package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.adp;
import com.google.android.gms.internal.eg;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.es;
import com.google.android.gms.internal.et;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

final class zzes implements Runnable {
   private final Context mContext;
   private final es zzbFT;
   private final String zzbDw;
   private final String zzbFU;
   private zzdi zzbFV;
   private volatile zzal zzbFW;
   private volatile String zzbDU;
   private volatile String zzbFX;

   public zzes(Context var1, String var2, zzal var3) {
      this(var1, var2, new es(), var3);
   }

   private zzes(Context var1, String var2, es var3, zzal var4) {
      this.mContext = var1;
      this.zzbFT = var3;
      this.zzbDw = var2;
      this.zzbFW = var4;
      String var10001 = String.valueOf("/r?id=");
      String var10002 = String.valueOf(var2);
      if (var10002.length() != 0) {
         var10001 = var10001.concat(var10002);
      } else {
         String var10003 = new String;
         var10002 = var10001;
         var10001 = var10003;
         var10003.<init>(var10002);
      }

      this.zzbFU = var10001;
      this.zzbDU = this.zzbFU;
      this.zzbFX = null;
   }

   public final void run() {
      if (this.zzbFV == null) {
         throw new IllegalStateException("callback must be set before execute");
      } else {
         zzes var1 = this;
         NetworkInfo var11;
         boolean var10000;
         if ((var11 = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo()) != null && var11.isConnected()) {
            var10000 = true;
         } else {
            zzdj.v("...no network connectivity");
            var10000 = false;
         }

         if (!var10000) {
            this.zzbFV.zzbw(zzda.zzbFh);
         } else {
            zzdj.v("Start loading resource from network ...");
            String var25 = String.valueOf(this.zzbFW.zzAX());
            String var12 = this.zzbDU;
            String var13 = String.valueOf("&v=a65833898");
            String var10 = (new StringBuilder(String.valueOf(var25).length() + String.valueOf(var12).length() + String.valueOf(var13).length())).append(var25).append(var12).append(var13).toString();
            if (this.zzbFX != null && !this.zzbFX.trim().equals("")) {
               var25 = String.valueOf(var10);
               var12 = String.valueOf("&pv=");
               var13 = this.zzbFX;
               var10 = (new StringBuilder(String.valueOf(var25).length() + String.valueOf(var12).length() + String.valueOf(var13).length())).append(var25).append(var12).append(var13).toString();
            }

            String var10001;
            String var10002;
            String var26;
            if (zzei.zzBD().zzBE().equals(zzei.zza.zzbFL)) {
               var26 = String.valueOf(var10);
               var10001 = String.valueOf("&gtm_debug=x");
               if (var10001.length() != 0) {
                  var26 = var26.concat(var10001);
               } else {
                  var10002 = new String;
                  var10001 = var26;
                  var26 = var10002;
                  var10002.<init>(var10001);
               }

               var10 = var26;
            }

            String var2 = var10;
            er var3 = es.zzDF();
            InputStream var4 = null;

            try {
               String var6;
               try {
                  var4 = var3.zzfU(var2);
               } catch (FileNotFoundException var20) {
                  var6 = var1.zzbDw;
                  zzdj.zzaT((new StringBuilder(79 + String.valueOf(var2).length() + String.valueOf(var6).length())).append("No data is retrieved from the given url: ").append(var2).append(". Make sure container_id: ").append(var6).append(" is correct.").toString());
                  var1.zzbFV.zzbw(zzda.zzbFj);
                  return;
               } catch (et var21) {
                  var10001 = String.valueOf(var2);
                  if (var10001.length() != 0) {
                     var26 = "Error when loading resource for url: ".concat(var10001);
                  } else {
                     var10002 = new String;
                     var26 = var10002;
                     var10002.<init>("Error when loading resource for url: ");
                  }

                  zzdj.zzaT(var26);
                  var1.zzbFV.zzbw(zzda.zzbFk);
               } catch (IOException var22) {
                  var6 = String.valueOf(var22.getMessage());
                  zzdj.zzc((new StringBuilder(40 + String.valueOf(var2).length() + String.valueOf(var6).length())).append("Error when loading resources from url: ").append(var2).append(" ").append(var6).toString(), var22);
                  var1.zzbFV.zzbw(zzda.zzbFi);
                  return;
               }

               try {
                  ByteArrayOutputStream var5 = new ByteArrayOutputStream();
                  eg.zzb(var4, var5);
                  byte[] var9 = var5.toByteArray();
                  com.google.android.gms.internal.zzbq var24;
                  String var7 = String.valueOf(var24 = (com.google.android.gms.internal.zzbq)adp.zza(new com.google.android.gms.internal.zzbq(), var9));
                  zzdj.v((new StringBuilder(43 + String.valueOf(var7).length())).append("Successfully loaded supplemented resource: ").append(var7).toString());
                  if (var24.zzlB == null && var24.zzlA.length == 0) {
                     var10001 = String.valueOf(var1.zzbDw);
                     if (var10001.length() != 0) {
                        var26 = "No change for container: ".concat(var10001);
                     } else {
                        var10002 = new String;
                        var26 = var10002;
                        var10002.<init>("No change for container: ");
                     }

                     zzdj.v(var26);
                  }

                  var1.zzbFV.onSuccess(var24);
               } catch (IOException var19) {
                  var6 = String.valueOf(var19.getMessage());
                  zzdj.zzc((new StringBuilder(51 + String.valueOf(var2).length() + String.valueOf(var6).length())).append("Error when parsing downloaded resources from url: ").append(var2).append(" ").append(var6).toString(), var19);
                  var1.zzbFV.zzbw(zzda.zzbFj);
                  return;
               }
            } finally {
               var3.close();
            }

            zzdj.v("Load resource from network finished.");
         }
      }
   }

   final void zza(zzdi var1) {
      this.zzbFV = var1;
   }

   final void zzfb(String var1) {
      if (var1 == null) {
         this.zzbDU = this.zzbFU;
      } else {
         String var10001 = String.valueOf(var1);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Setting CTFE URL path: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Setting CTFE URL path: ");
         }

         zzdj.zzaC(var10000);
         this.zzbDU = var1;
      }
   }

   final void zzfr(String var1) {
      String var10001 = String.valueOf(var1);
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Setting previous container version: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Setting previous container version: ");
      }

      zzdj.zzaC(var10000);
      this.zzbFX = var1;
   }
}
