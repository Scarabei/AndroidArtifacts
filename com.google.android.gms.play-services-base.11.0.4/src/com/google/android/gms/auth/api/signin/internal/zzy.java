package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzbo;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public final class zzy {
   private static final Lock zzamD = new ReentrantLock();
   private static zzy zzamE;
   private final Lock zzamF = new ReentrantLock();
   private final SharedPreferences zzamG;

   public static zzy zzaj(Context var0) {
      zzbo.zzu(var0);
      zzamD.lock();

      zzy var1;
      try {
         if (zzamE == null) {
            zzamE = new zzy(var0.getApplicationContext());
         }

         var1 = zzamE;
      } finally {
         zzamD.unlock();
      }

      return var1;
   }

   private zzy(Context var1) {
      this.zzamG = var1.getSharedPreferences("com.google.android.gms.signin", 0);
   }

   public final void zza(GoogleSignInAccount var1, GoogleSignInOptions var2) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      this.zzr("defaultGoogleSignInAccount", var1.zzmx());
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      String var6 = var1.zzmx();
      this.zzr(zzs("googleSignInAccount", var6), var1.zzmy());
      this.zzr(zzs("googleSignInOptions", var6), var2.zzmC());
   }

   private final void zzr(String var1, String var2) {
      this.zzamF.lock();

      try {
         this.zzamG.edit().putString(var1, var2).apply();
      } finally {
         this.zzamF.unlock();
      }

   }

   public final GoogleSignInAccount zzmN() {
      String var1 = this.zzbU("defaultGoogleSignInAccount");
      return this.zzbS(var1);
   }

   private final GoogleSignInAccount zzbS(String var1) {
      if (TextUtils.isEmpty(var1)) {
         return null;
      } else {
         String var2 = this.zzbU(zzs("googleSignInAccount", var1));

         try {
            return var2 != null ? GoogleSignInAccount.zzbP(var2) : null;
         } catch (JSONException var3) {
            return null;
         }
      }
   }

   public final GoogleSignInOptions zzmO() {
      String var1 = this.zzbU("defaultGoogleSignInAccount");
      return this.zzbT(var1);
   }

   private final GoogleSignInOptions zzbT(String var1) {
      if (TextUtils.isEmpty(var1)) {
         return null;
      } else {
         String var2 = this.zzbU(zzs("googleSignInOptions", var1));

         try {
            return var2 != null ? GoogleSignInOptions.zzbQ(var2) : null;
         } catch (JSONException var3) {
            return null;
         }
      }
   }

   private final String zzbU(String var1) {
      this.zzamF.lock();

      String var2;
      try {
         var2 = this.zzamG.getString(var1, (String)null);
      } finally {
         this.zzamF.unlock();
      }

      return var2;
   }

   public final void zzmP() {
      String var1 = this.zzbU("defaultGoogleSignInAccount");
      this.zzbV("defaultGoogleSignInAccount");
      if (!TextUtils.isEmpty(var1)) {
         this.zzbV(zzs("googleSignInAccount", var1));
         this.zzbV(zzs("googleSignInOptions", var1));
      }

   }

   private final void zzbV(String var1) {
      this.zzamF.lock();

      try {
         this.zzamG.edit().remove(var1).apply();
      } finally {
         this.zzamF.unlock();
      }

   }

   private static String zzs(String var0, String var1) {
      String var2 = String.valueOf(":");
      return (new StringBuilder(String.valueOf(var0).length() + String.valueOf(var2).length() + String.valueOf(var1).length())).append(var0).append(var2).append(var1).toString();
   }
}
