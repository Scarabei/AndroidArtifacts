package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbha;

public class zzp {
   private static zzp zzaAu;
   private final Context mContext;

   private zzp(Context var1) {
      this.mContext = var1.getApplicationContext();
   }

   public static zzp zzax(Context var0) {
      zzbo.zzu(var0);
      Class var1 = zzp.class;
      synchronized(zzp.class) {
         if (zzaAu == null) {
            zzf.zzav(var0);
            zzaAu = new zzp(var0);
         }
      }

      return zzaAu;
   }

   /** @deprecated */
   @Deprecated
   public final boolean zza(PackageManager var1, int var2) {
      zzp var3 = this;
      String[] var5;
      if ((var5 = zzbha.zzaP(this.mContext).getPackagesForUid(var2)) != null && var5.length != 0) {
         String[] var6 = var5;
         int var7 = var5.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            String var9 = var6[var8];
            if (var3.zzct(var9)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private final boolean zzct(String var1) {
      try {
         PackageInfo var2 = zzbha.zzaP(this.mContext).getPackageInfo(var1, 64);
         if (var2 == null) {
            return false;
         } else if (zzo.zzaw(this.mContext)) {
            return zzb(var2, true);
         } else {
            boolean var5;
            if (!(var5 = zzb(var2, false)) && zzb(var2, true)) {
               Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
            }

            return var5;
         }
      } catch (NameNotFoundException var6) {
         return false;
      }
   }

   private static boolean zza(PackageInfo var0, boolean var1) {
      return var0 != null && var0.signatures != null && (var1 ? zza(var0, zzj.zzaAk) : zza(var0, zzj.zzaAk[0])) != null;
   }

   /** @deprecated */
   @Deprecated
   public final boolean zza(PackageManager var1, PackageInfo var2) {
      if (var2 != null) {
         if (zza(var2, false)) {
            return true;
         }

         if (zza(var2, true)) {
            if (zzo.zzaw(this.mContext)) {
               return true;
            }

            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
         }
      }

      return false;
   }

   private static boolean zzb(PackageInfo var0, boolean var1) {
      if (var0.signatures.length != 1) {
         Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
         return false;
      } else {
         zzh var2 = new zzh(var0.signatures[0].toByteArray());
         String var3 = var0.packageName;
         boolean var4;
         if (var1) {
            var4 = zzf.zzb(var3, var2);
         } else {
            var4 = zzf.zza(var3, var2);
         }

         if (!var4) {
            Log.d("GoogleSignatureVerifier", (new StringBuilder(27)).append("Cert not in list. atk=").append(var1).toString());
         }

         return var4;
      }
   }

   static zzg zza(PackageInfo var0, zzg... var1) {
      if (var0.signatures == null) {
         return null;
      } else if (var0.signatures.length != 1) {
         Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
         return null;
      } else {
         zzh var2 = new zzh(var0.signatures[0].toByteArray());

         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3].equals(var2)) {
               return var1[var3];
            }
         }

         return null;
      }
   }
}
