package com.google.android.gms.iid;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class InstanceID {
   public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
   public static final String ERROR_TIMEOUT = "TIMEOUT";
   public static final String ERROR_MISSING_INSTANCEID_SERVICE = "MISSING_INSTANCEID_SERVICE";
   public static final String ERROR_BACKOFF = "RETRY_LATER";
   public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
   private static Map zzbgQ = new HashMap();
   private Context mContext;
   private static zzh zzbgR;
   private static zze zzbgS;
   private KeyPair zzbgT;
   private String zzbgU = "";
   private long zzbgV;
   private static String zzbgW;

   private InstanceID(Context var1, String var2, Bundle var3) {
      this.mContext = var1.getApplicationContext();
      this.zzbgU = var2;
   }

   static int zzba(Context var0) {
      try {
         return var0.getPackageManager().getPackageInfo(var0.getPackageName(), 0).versionCode;
      } catch (NameNotFoundException var3) {
         String var2 = String.valueOf(var3);
         Log.w("InstanceID", (new StringBuilder(38 + String.valueOf(var2).length())).append("Never happens: can't find own package ").append(var2).toString());
         return 0;
      }
   }

   static String zzbb(Context var0) {
      try {
         return var0.getPackageManager().getPackageInfo(var0.getPackageName(), 0).versionName;
      } catch (NameNotFoundException var3) {
         String var2 = String.valueOf(var3);
         Log.w("InstanceID", (new StringBuilder(38 + String.valueOf(var2).length())).append("Never happens: can't find own package ").append(var2).toString());
         return null;
      }
   }

   public static InstanceID getInstance(Context var0) {
      return zza(var0, (Bundle)null);
   }

   public static synchronized InstanceID zza(Context var0, Bundle var1) {
      String var2;
      if ((var2 = var1 == null ? "" : var1.getString("subtype")) == null) {
         var2 = "";
      }

      var0 = var0.getApplicationContext();
      if (zzbgR == null) {
         zzbgR = new zzh(var0);
         zzbgS = new zze(var0);
      }

      zzbgW = Integer.toString(zzba(var0));
      InstanceID var3;
      if ((var3 = (InstanceID)zzbgQ.get(var2)) == null) {
         var3 = new InstanceID(var0, var2, var1);
         zzbgQ.put(var2, var3);
      }

      return var3;
   }

   static String zzj(byte[] var0) {
      return Base64.encodeToString(var0, 11);
   }

   private final KeyPair zzvK() {
      if (this.zzbgT == null) {
         String var1 = this.zzbgU;
         this.zzbgT = zzbgR.zzds(this.zzbgU);
      }

      if (this.zzbgT == null) {
         this.zzbgV = System.currentTimeMillis();
         this.zzbgT = zzbgR.zzc(this.zzbgU, this.zzbgV);
      }

      return this.zzbgT;
   }

   public String getId() {
      return zza(this.zzvK());
   }

   static String zza(KeyPair var0) {
      byte[] var1 = var0.getPublic().getEncoded();

      try {
         byte[] var2;
         byte var3 = (var2 = MessageDigest.getInstance("SHA1").digest(var1))[0];
         int var5 = 112 + (15 & var3);
         var2[0] = (byte)var5;
         return Base64.encodeToString(var2, 0, 8, 11);
      } catch (NoSuchAlgorithmException var4) {
         Log.w("InstanceID", "Unexpected error, device missing required alghorithms");
         return null;
      }
   }

   public long getCreationTime() {
      String var1;
      if (this.zzbgV == 0L && (var1 = zzbgR.get(this.zzbgU, "cre")) != null) {
         this.zzbgV = Long.parseLong(var1);
      }

      return this.zzbgV;
   }

   public void deleteInstanceID() throws IOException {
      this.zzb("*", "*", (Bundle)null);
      this.zzvL();
   }

   public final void zzvL() {
      this.zzbgV = 0L;
      String var1 = this.zzbgU;
      zzbgR.zzdq(String.valueOf(this.zzbgU).concat("|"));
      this.zzbgT = null;
   }

   public void deleteToken(String var1, String var2) throws IOException {
      this.zzb(var1, var2, (Bundle)null);
   }

   public final void zzb(String var1, String var2, Bundle var3) throws IOException {
      if (Looper.getMainLooper() == Looper.myLooper()) {
         throw new IOException("MAIN_THREAD");
      } else {
         zzbgR.zzg(this.zzbgU, var1, var2);
         if (var3 == null) {
            var3 = new Bundle();
         }

         var3.putString("sender", var1);
         if (var2 != null) {
            var3.putString("scope", var2);
         }

         var3.putString("subscription", var1);
         var3.putString("delete", "1");
         var3.putString("X-delete", "1");
         var3.putString("subtype", "".equals(this.zzbgU) ? var1 : this.zzbgU);
         var3.putString("X-subtype", "".equals(this.zzbgU) ? var1 : this.zzbgU);
         zze.zzh(zzbgS.zza(var3, this.zzvK()));
      }
   }

   public static zzh zzvM() {
      return zzbgR;
   }

   public String getToken(String var1, String var2) throws IOException {
      return this.getToken(var1, var2, (Bundle)null);
   }

   public String getToken(String var1, String var2, Bundle var3) throws IOException {
      if (Looper.getMainLooper() == Looper.myLooper()) {
         throw new IOException("MAIN_THREAD");
      } else {
         boolean var4 = true;
         boolean var10000;
         String var6;
         if ((var6 = zzbgR.get("appVersion")) != null && var6.equals(zzbgW)) {
            String var7;
            if ((var7 = zzbgR.get("lastToken")) == null) {
               var10000 = true;
            } else {
               Long var8 = Long.parseLong(var7);
               var10000 = System.currentTimeMillis() / 1000L - var8.longValue() > 604800L;
            }
         } else {
            var10000 = true;
         }

         String var9 = var10000 ? null : zzbgR.zzf(this.zzbgU, var1, var2);
         String var5 = var9;
         if (var9 != null) {
            return var5;
         } else {
            if (var3 == null) {
               var3 = new Bundle();
            }

            if (var3.getString("ttl") != null) {
               var4 = false;
            }

            if ("jwt".equals(var3.getString("type"))) {
               var4 = false;
            }

            if ((var5 = this.zzc(var1, var2, var3)) != null && var4) {
               zzbgR.zza(this.zzbgU, var1, var2, var5, zzbgW);
            }

            return var5;
         }
      }
   }

   public final String zzc(String var1, String var2, Bundle var3) throws IOException {
      if (var2 != null) {
         var3.putString("scope", var2);
      }

      var3.putString("sender", var1);
      String var4 = "".equals(this.zzbgU) ? var1 : this.zzbgU;
      if (!var3.containsKey("legacy.register")) {
         var3.putString("subscription", var1);
         var3.putString("subtype", var4);
         var3.putString("X-subscription", var1);
         var3.putString("X-subtype", var4);
      }

      return zze.zzh(zzbgS.zza(var3, this.zzvK()));
   }
}
