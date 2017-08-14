package com.google.android.gms.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zzn;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class cy implements Runnable {
   private final Context mContext;
   private final cc zzbKT;
   private final cx zzbKU;
   private final cl zzbKt;
   private final cu zzbKV;

   public cy(Context var1, cl var2, cc var3) {
      this(var1, var2, var3, new cx(), new cu());
   }

   private cy(Context var1, cl var2, cc var3, cx var4, cu var5) {
      this.mContext = (Context)zzbo.zzu(var1);
      this.zzbKT = (cc)zzbo.zzu(var3);
      this.zzbKt = var2;
      this.zzbKU = var4;
      this.zzbKV = var5;
   }

   public final void run() {
      cy var1 = this;
      boolean var10000;
      if (!this.zzbv("android.permission.INTERNET")) {
         zzcvk.e("Missing android.permission.INTERNET. Please add the following declaration to your AndroidManifest.xml: <uses-permission android:name=\"android.permission.INTERNET\" />");
         var10000 = false;
      } else if (!this.zzbv("android.permission.ACCESS_NETWORK_STATE")) {
         zzcvk.e("Missing android.permission.ACCESS_NETWORK_STATE. Please add the following declaration to your AndroidManifest.xml: <uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />");
         var10000 = false;
      } else {
         NetworkInfo var10;
         if ((var10 = ((ConnectivityManager)this.mContext.getSystemService("connectivity")).getActiveNetworkInfo()) != null && var10.isConnected()) {
            var10000 = true;
         } else {
            zzcvk.zzaT("No network connectivity - Offline");
            var10000 = false;
         }
      }

      if (!var10000) {
         this.zzbKT.zzk(0, 0);
      } else {
         zzcvk.v("Starting to load resource from Network.");
         cv var2 = new cv();
         InputStream var3 = null;

         try {
            String var4 = var1.zzbKV.zzb(var1.zzbKt.zzCP());
            String var10001 = String.valueOf(var4);
            String var10002;
            String var21;
            if (var10001.length() != 0) {
               var21 = "Loading resource from ".concat(var10001);
            } else {
               var10002 = new String;
               var21 = var10002;
               var10002.<init>("Loading resource from ");
            }

            zzcvk.v(var21);

            String var6;
            try {
               var3 = var2.zzfU(var4);
            } catch (FileNotFoundException var17) {
               var10001 = String.valueOf(var4);
               if (var10001.length() != 0) {
                  var21 = "NetworkLoader: No data was retrieved from the given url: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var21 = var10002;
                  var10002.<init>("NetworkLoader: No data was retrieved from the given url: ");
               }

               zzcvk.e(var21);
               var1.zzbKT.zzk(2, 0);
               return;
            } catch (da var18) {
               var10001 = String.valueOf(var4);
               if (var10001.length() != 0) {
                  var21 = "NetworkLoader: Error when loading resource for url: ".concat(var10001);
               } else {
                  var10002 = new String;
                  var21 = var10002;
                  var10002.<init>("NetworkLoader: Error when loading resource for url: ");
               }

               zzcvk.e(var21);
               var1.zzbKT.zzk(3, 0);
            } catch (IOException var19) {
               var6 = String.valueOf(var19.getMessage());
               zzcvk.zzb((new StringBuilder(54 + String.valueOf(var4).length() + String.valueOf(var6).length())).append("NetworkLoader: Error when loading resource from url: ").append(var4).append(" ").append(var6).toString(), var19);
               var1.zzbKT.zzk(1, 0);
               return;
            }

            try {
               ByteArrayOutputStream var5 = new ByteArrayOutputStream();
               zzn.zza(var3, var5, false);
               var1.zzbKT.zzv(var5.toByteArray());
            } catch (IOException var16) {
               var6 = String.valueOf(var16.getMessage());
               zzcvk.zzb((new StringBuilder(66 + String.valueOf(var4).length() + String.valueOf(var6).length())).append("NetworkLoader: Error when parsing downloaded resources from url: ").append(var4).append(" ").append(var6).toString(), var16);
               var1.zzbKT.zzk(2, 0);
            }
         } finally {
            var2.close();
         }
      }
   }

   private final boolean zzbv(String var1) {
      return this.mContext.getPackageManager().checkPermission(var1, this.mContext.getPackageName()) == 0;
   }
}
