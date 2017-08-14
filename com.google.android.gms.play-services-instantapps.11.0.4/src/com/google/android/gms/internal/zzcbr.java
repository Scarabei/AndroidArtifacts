package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.instantapps.PackageManagerWrapper;

public final class zzcbr implements PackageManagerWrapper {
   private static zzcbr zzbhs;
   private final Context zzqD;
   private final boolean zzbht;

   public static synchronized zzcbr zzi(Context var0, boolean var1) {
      Context var2 = var0.getApplicationContext();
      if (zzbhs == null || zzbhs.zzqD != var2 || zzbhs.zzbht != var1) {
         zzbhs = new zzcbr(var2, var1);
      }

      return zzbhs;
   }

   private zzcbr(Context var1, boolean var2) {
      this.zzqD = var1;
      this.zzbht = var2;
   }

   public final String[] getPackagesForUid(int var1) {
      String[] var2;
      if (this.zzbht && (var2 = this.zzqD.getPackageManager().getPackagesForUid(var1)) != null) {
         return var2;
      } else {
         zzcbp var5;
         if ((var5 = zzcbp.zzbf(this.zzqD)) != null) {
            try {
               String var3;
               return (var3 = var5.zzbi(var1)) == null ? null : new String[]{var3};
            } catch (RemoteException var4) {
               Log.e("InstantAppsPMW", "Error getting app package for UID", var4);
               return null;
            }
         } else {
            return null;
         }
      }
   }

   public final PackageInfo getPackageInfo(String var1, int var2) throws NameNotFoundException {
      if (this.zzbht) {
         try {
            return this.zzqD.getPackageManager().getPackageInfo(var1, var2);
         } catch (NameNotFoundException var6) {
            ;
         }
      }

      zzcbp var3;
      if ((var3 = zzcbp.zzbf(this.zzqD)) != null) {
         try {
            PackageInfo var4;
            if ((var4 = var3.getPackageInfo(var1, var2)) != null) {
               return var4;
            }
         } catch (RemoteException var5) {
            Log.e("InstantAppsPMW", "Error getting package info", var5);
         }
      }

      throw new NameNotFoundException();
   }

   public final ApplicationInfo getApplicationInfo(String var1, int var2) throws NameNotFoundException {
      if (this.zzbht) {
         try {
            return this.zzqD.getPackageManager().getApplicationInfo(var1, var2);
         } catch (NameNotFoundException var6) {
            ;
         }
      }

      zzcbp var3;
      if ((var3 = zzcbp.zzbf(this.zzqD)) != null) {
         try {
            ApplicationInfo var4;
            if ((var4 = var3.getApplicationInfo(var1, var2)) != null) {
               return var4;
            }
         } catch (RemoteException var5) {
            Log.e("InstantAppsPMW", "Error getting application info", var5);
         }
      }

      throw new NameNotFoundException();
   }

   public final CharSequence getApplicationLabel(ApplicationInfo var1) {
      if (this.zzbht && this.zzqD.getPackageManager().getPackagesForUid(var1.uid) != null) {
         return this.zzqD.getPackageManager().getApplicationLabel(var1);
      } else {
         zzcbp var2;
         if ((var2 = zzcbp.zzbf(this.zzqD)) != null) {
            try {
               return var2.zzdt(var1.packageName);
            } catch (RemoteException var4) {
               Log.e("InstantAppsPMW", "Error getting application label", var4);
            }
         }

         return null;
      }
   }

   public final String getCallingPackage(Activity var1) {
      return (new zzcbk(var1)).getCallingPackage();
   }

   public final ComponentName getCallingActivity(Activity var1) {
      return (new zzcbk(var1)).getCallingActivity();
   }

   public final boolean isInstantApp() {
      return this.isInstantApp(this.zzqD.getPackageName());
   }

   public final boolean isInstantApp(String var1) {
      zzcbp var2;
      if ((var2 = zzcbp.zzbf(this.zzqD)) != null) {
         try {
            return var2.isInstantApp(var1);
         } catch (RemoteException var4) {
            Log.e("InstantAppsPMW", "Error checking if app is instant app", var4);
         }
      }

      return false;
   }

   public final boolean isInstantApp(int var1) {
      if (Process.myUid() == var1) {
         return zzbgy.zzaN(this.zzqD);
      } else {
         zzcbp var2;
         if ((var2 = zzcbp.zzbf(this.zzqD)) != null) {
            try {
               if (var2.zzbi(var1) != null) {
                  return true;
               }

               return false;
            } catch (RemoteException var4) {
               Log.e("InstantAppsPMW", "Error checking if app is instant app", var4);
            }
         }

         return false;
      }
   }
}
