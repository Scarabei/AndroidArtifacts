package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

public final class zzalx extends zzamh {
   private static boolean zzafv;
   private Info zzafw;
   private final zzaoo zzafx;
   private String zzafy;
   private boolean zzafz = false;
   private Object zzafA = new Object();

   zzalx(zzamj var1) {
      super(var1);
      this.zzafx = new zzaoo(var1.zzkq());
   }

   protected final void zzjD() {
   }

   public final boolean zzjZ() {
      this.zzkD();
      Info var1;
      if ((var1 = this.zzkh()) != null) {
         return !var1.isLimitAdTrackingEnabled();
      } else {
         return false;
      }
   }

   public final String zzkg() {
      this.zzkD();
      Info var1 = this.zzkh();
      String var2 = null;
      if (var1 != null) {
         var2 = var1.getId();
      }

      return TextUtils.isEmpty(var2) ? null : var2;
   }

   private final synchronized Info zzkh() {
      if (this.zzafx.zzu(1000L)) {
         this.zzafx.start();
         Info var1 = this.zzki();
         if (this.zza(this.zzafw, var1)) {
            this.zzafw = var1;
         } else {
            this.zzbs("Failed to reset client id on adid change. Not using adid");
            this.zzafw = new Info("", false);
         }
      }

      return this.zzafw;
   }

   private final boolean zza(Info var1, Info var2) {
      String var10000 = var2 == null ? null : var2.getId();
      String var3 = var10000;
      if (TextUtils.isEmpty(var10000)) {
         return true;
      } else {
         String var4 = this.zzkz().zzli();
         Object var5 = this.zzafA;
         synchronized(this.zzafA) {
            String var10001;
            String var10002;
            String var10003;
            String var6;
            if (!this.zzafz) {
               this.zzafy = this.zzkj();
               this.zzafz = true;
            } else if (TextUtils.isEmpty(this.zzafy)) {
               var10000 = var1 == null ? null : var1.getId();
               var6 = var10000;
               if (var10000 == null) {
                  var10001 = String.valueOf(var3);
                  var10002 = String.valueOf(var4);
                  if (var10002.length() != 0) {
                     var10001 = var10001.concat(var10002);
                  } else {
                     var10003 = new String;
                     var10002 = var10001;
                     var10001 = var10003;
                     var10003.<init>(var10002);
                  }

                  return this.zzbn(var10001);
               }

               var10001 = String.valueOf(var6);
               var10002 = String.valueOf(var4);
               if (var10002.length() != 0) {
                  var10001 = var10001.concat(var10002);
               } else {
                  var10003 = new String;
                  var10002 = var10001;
                  var10001 = var10003;
                  var10003.<init>(var10002);
               }

               this.zzafy = zzbm(var10001);
            }

            var10000 = String.valueOf(var3);
            var10001 = String.valueOf(var4);
            if (var10001.length() != 0) {
               var10000 = var10000.concat(var10001);
            } else {
               var10002 = new String;
               var10001 = var10000;
               var10000 = var10002;
               var10002.<init>(var10001);
            }

            if (TextUtils.isEmpty(var6 = zzbm(var10000))) {
               return false;
            } else if (var6.equals(this.zzafy)) {
               return true;
            } else {
               if (!TextUtils.isEmpty(this.zzafy)) {
                  this.zzbo("Resetting the client id because Advertising Id changed.");
                  var4 = this.zzkz().zzlj();
                  this.zza("New client Id", var4);
               }

               var10001 = String.valueOf(var3);
               var10002 = String.valueOf(var4);
               if (var10002.length() != 0) {
                  var10001 = var10001.concat(var10002);
               } else {
                  var10003 = new String;
                  var10002 = var10001;
                  var10001 = var10003;
                  var10003.<init>(var10002);
               }

               return this.zzbn(var10001);
            }
         }
      }
   }

   private final Info zzki() {
      Info var1 = null;

      try {
         var1 = AdvertisingIdClient.getAdvertisingIdInfo(this.getContext());
      } catch (IllegalStateException var3) {
         this.zzbr("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
      } catch (Throwable var4) {
         if (!zzafv) {
            zzafv = true;
            this.zzd("Error getting advertiser id", var4);
         }
      }

      return var1;
   }

   private static String zzbm(String var0) {
      MessageDigest var1;
      return (var1 = zzaos.zzbE("MD5")) == null ? null : String.format(Locale.US, "%032X", new BigInteger(1, var1.digest(var0.getBytes())));
   }

   private final boolean zzbn(String var1) {
      try {
         String var2 = zzbm(var1);
         this.zzbo("Storing hashed adid.");
         FileOutputStream var3;
         (var3 = this.getContext().openFileOutput("gaClientIdData", 0)).write(var2.getBytes());
         var3.close();
         this.zzafy = var2;
         return true;
      } catch (IOException var4) {
         this.zze("Error creating hash file", var4);
         return false;
      }
   }

   private final String zzkj() {
      String var1 = null;

      try {
         FileInputStream var2 = this.getContext().openFileInput("gaClientIdData");
         byte[] var3 = new byte[128];
         int var4 = var2.read(var3, 0, 128);
         if (var2.available() > 0) {
            this.zzbr("Hash file seems corrupted, deleting it.");
            var2.close();
            this.getContext().deleteFile("gaClientIdData");
         } else if (var4 <= 0) {
            this.zzbo("Hash file is empty.");
            var2.close();
         } else {
            var1 = new String(var3, 0, var4);
            var2.close();
         }
      } catch (FileNotFoundException var5) {
         ;
      } catch (IOException var6) {
         this.zzd("Error reading Hash file, deleting it", var6);
         this.getContext().deleteFile("gaClientIdData");
      }

      return var1;
   }
}
