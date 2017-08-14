package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.util.zzu;
import java.io.File;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;

public final class zzh {
   private SharedPreferences zzbho;
   private Context zzqD;

   public zzh(Context var1) {
      this(var1, "com.google.android.gms.appid");
   }

   private zzh(Context var1, String var2) {
      this.zzqD = var1;
      this.zzbho = var1.getSharedPreferences(var2, 0);
      String var10001 = String.valueOf(var2);
      String var10002 = String.valueOf("-no-backup");
      String var10003;
      if (var10002.length() != 0) {
         var10001 = var10001.concat(var10002);
      } else {
         var10003 = new String;
         var10002 = var10001;
         var10001 = var10003;
         var10003.<init>(var10002);
      }

      String var4 = var10001;
      zzh var3 = this;
      File var5 = zzu.getNoBackupFilesDir(this.zzqD);
      File var6;
      if (!(var6 = new File(var5, var4)).exists()) {
         try {
            if (var6.createNewFile() && !var3.isEmpty()) {
               Log.i("InstanceID/Store", "App restored, clearing state");
               InstanceIDListenerService.zza(var3.zzqD, var3);
            }

            return;
         } catch (IOException var8) {
            if (Log.isLoggable("InstanceID/Store", 3)) {
               var10002 = String.valueOf(var8.getMessage());
               if (var10002.length() != 0) {
                  var10001 = "Error creating file in no backup dir: ".concat(var10002);
               } else {
                  var10003 = new String;
                  var10001 = var10003;
                  var10003.<init>("Error creating file in no backup dir: ");
               }

               Log.d("InstanceID/Store", var10001);
            }
         }
      }

   }

   public final boolean isEmpty() {
      return this.zzbho.getAll().isEmpty();
   }

   private static String zze(String var0, String var1, String var2) {
      String var3 = String.valueOf("|T|");
      return (new StringBuilder(1 + String.valueOf(var0).length() + String.valueOf(var3).length() + String.valueOf(var1).length() + String.valueOf(var2).length())).append(var0).append(var3).append(var1).append("|").append(var2).toString();
   }

   final synchronized String get(String var1, String var2) {
      SharedPreferences var10000 = this.zzbho;
      String var3 = String.valueOf("|S|");
      return var10000.getString((new StringBuilder(String.valueOf(var1).length() + String.valueOf(var3).length() + String.valueOf(var2).length())).append(var1).append(var3).append(var2).toString(), (String)null);
   }

   final synchronized String get(String var1) {
      return this.zzbho.getString(var1, (String)null);
   }

   private final synchronized void zza(Editor var1, String var2, String var3, String var4) {
      String var5 = String.valueOf("|S|");
      var1.putString((new StringBuilder(String.valueOf(var2).length() + String.valueOf(var5).length() + String.valueOf(var3).length())).append(var2).append(var5).append(var3).toString(), var4);
   }

   public final synchronized void zzdq(String var1) {
      Editor var2 = this.zzbho.edit();
      Iterator var3 = this.zzbho.getAll().keySet().iterator();

      while(var3.hasNext()) {
         String var4;
         if ((var4 = (String)var3.next()).startsWith(var1)) {
            var2.remove(var4);
         }
      }

      var2.commit();
   }

   public final synchronized void zzvP() {
      this.zzbho.edit().clear().commit();
   }

   public final synchronized String zzf(String var1, String var2, String var3) {
      String var4 = zze(var1, var2, var3);
      return this.zzbho.getString(var4, (String)null);
   }

   public final synchronized void zza(String var1, String var2, String var3, String var4, String var5) {
      String var6 = zze(var1, var2, var3);
      Editor var7;
      (var7 = this.zzbho.edit()).putString(var6, var4);
      var7.putString("appVersion", var5);
      var7.putString("lastToken", Long.toString(System.currentTimeMillis() / 1000L));
      var7.commit();
   }

   public final synchronized void zzg(String var1, String var2, String var3) {
      String var4 = zze(var1, var2, var3);
      Editor var5;
      (var5 = this.zzbho.edit()).remove(var4);
      var5.commit();
   }

   final synchronized KeyPair zzc(String var1, long var2) {
      KeyPair var4 = zza.zzvJ();
      Editor var5 = this.zzbho.edit();
      this.zza(var5, var1, "|P|", InstanceID.zzj(var4.getPublic().getEncoded()));
      this.zza(var5, var1, "|K|", InstanceID.zzj(var4.getPrivate().getEncoded()));
      this.zza(var5, var1, "cre", Long.toString(var2));
      var5.commit();
      return var4;
   }

   public final void zzdr(String var1) {
      this.zzdq(String.valueOf(var1).concat("|T|"));
   }

   final KeyPair zzds(String var1) {
      String var2 = this.get(var1, "|P|");
      String var3 = this.get(var1, "|K|");
      if (var2 != null && var3 != null) {
         try {
            byte[] var4 = Base64.decode(var2, 8);
            byte[] var10 = Base64.decode(var3, 8);
            KeyFactory var6;
            PublicKey var7 = (var6 = KeyFactory.getInstance("RSA")).generatePublic(new X509EncodedKeySpec(var4));
            PrivateKey var8 = var6.generatePrivate(new PKCS8EncodedKeySpec(var10));
            return new KeyPair(var7, var8);
         } catch (NoSuchAlgorithmException | InvalidKeySpecException var9) {
            String var5 = String.valueOf(var9);
            Log.w("InstanceID/Store", (new StringBuilder(19 + String.valueOf(var5).length())).append("Invalid key stored ").append(var5).toString());
            InstanceIDListenerService.zza(this.zzqD, this);
            return null;
         }
      } else {
         return null;
      }
   }
}
