package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zze;
import com.google.android.gms.common.zzo;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzfd;
import com.google.android.gms.internal.zzfe;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@KeepForSdkWithMembers
public class AdvertisingIdClient {
   @Nullable
   private com.google.android.gms.common.zza zzsx;
   @Nullable
   private zzfd zzsy;
   private boolean zzsz;
   private Object zzsA;
   @Nullable
   private AdvertisingIdClient.zza zzsB;
   private final Context mContext;
   private long zzsC;

   public AdvertisingIdClient(Context var1) {
      this(var1, 30000L, false);
   }

   public AdvertisingIdClient(Context var1, long var2, boolean var4) {
      this.zzsA = new Object();
      zzbo.zzu(var1);
      if (var4) {
         Context var5 = var1.getApplicationContext();
         this.mContext = var5 == null ? var1 : var5;
      } else {
         this.mContext = var1;
      }

      this.zzsz = false;
      this.zzsC = var2;
   }

   public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
      this.start(true);
   }

   private final void start(boolean var1) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
      zzbo.zzcG("Calling this from your main thread can lead to deadlock");
      synchronized(this) {
         if (this.zzsz) {
            this.finish();
         }

         this.zzsx = zzd(this.mContext);
         this.zzsy = zza(this.mContext, this.zzsx);
         this.zzsz = true;
         if (var1) {
            this.zzaj();
         }

      }
   }

   public static void setShouldSkipGmsCoreVersionCheck(boolean var0) {
   }

   private final void zzaj() {
      Object var1 = this.zzsA;
      synchronized(this.zzsA) {
         if (this.zzsB != null) {
            this.zzsB.zzsG.countDown();

            try {
               this.zzsB.join();
            } catch (InterruptedException var3) {
               ;
            }
         }

         if (this.zzsC > 0L) {
            this.zzsB = new AdvertisingIdClient.zza(this, this.zzsC);
         }

      }
   }

   public AdvertisingIdClient.Info getInfo() throws IOException {
      zzbo.zzcG("Calling this from your main thread can lead to deadlock");
      AdvertisingIdClient.Info var1;
      synchronized(this) {
         if (!this.zzsz) {
            Object var3 = this.zzsA;
            synchronized(this.zzsA) {
               if (this.zzsB == null || !this.zzsB.zzsH) {
                  throw new IOException("AdvertisingIdClient is not connected.");
               }
            }

            try {
               this.start(false);
            } catch (Exception var7) {
               throw new IOException("AdvertisingIdClient cannot reconnect.", var7);
            }

            if (!this.zzsz) {
               throw new IOException("AdvertisingIdClient cannot reconnect.");
            }
         }

         zzbo.zzu(this.zzsx);
         zzbo.zzu(this.zzsy);

         try {
            var1 = new AdvertisingIdClient.Info(this.zzsy.getId(), this.zzsy.zzb(true));
         } catch (RemoteException var6) {
            Log.i("AdvertisingIdClient", "GMS remote exception ", var6);
            throw new IOException("Remote exception");
         }
      }

      this.zzaj();
      return var1;
   }

   public void finish() {
      zzbo.zzcG("Calling this from your main thread can lead to deadlock");
      synchronized(this) {
         if (this.mContext != null && this.zzsx != null) {
            try {
               if (this.zzsz) {
                  com.google.android.gms.common.stats.zza.zzrU();
                  com.google.android.gms.common.zza var5 = this.zzsx;
                  Context var10001 = this.mContext;
                  this.mContext.unbindService(var5);
               }
            } catch (IllegalArgumentException var6) {
               Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", var6);
            } catch (Throwable var7) {
               Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", var7);
            }

            this.zzsz = false;
            this.zzsy = null;
            this.zzsx = null;
         }
      }
   }

   protected void finalize() throws Throwable {
      this.finish();
      super.finalize();
   }

   private static com.google.android.gms.common.zza zzd(Context var0) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
      try {
         var0.getPackageManager().getPackageInfo("com.android.vending", 0);
      } catch (NameNotFoundException var4) {
         throw new GooglePlayServicesNotAvailableException(9);
      }

      switch(zze.zzoW().isGooglePlayServicesAvailable(var0)) {
      case 0:
      case 2:
         com.google.android.gms.common.zza var1 = new com.google.android.gms.common.zza();
         Intent var2;
         (var2 = new Intent("com.google.android.gms.ads.identifier.service.START")).setPackage("com.google.android.gms");

         try {
            if (com.google.android.gms.common.stats.zza.zzrU().zza(var0, var2, var1, 1)) {
               return var1;
            }
         } catch (Throwable var5) {
            throw new IOException(var5);
         }

         throw new IOException("Connection failure");
      default:
         throw new IOException("Google Play services not available");
      }
   }

   @Nullable
   public static AdvertisingIdClient.Info getAdvertisingIdInfo(Context var0) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
      boolean var2 = false;
      float var3 = 0.0F;

      try {
         Context var4;
         if ((var4 = zzo.getRemoteContext(var0)) != null) {
            SharedPreferences var1;
            var2 = (var1 = var4.getSharedPreferences("google_ads_flags", 0)).getBoolean("gads:ad_id_app_context:enabled", false);
            var3 = var1.getFloat("gads:ad_id_app_context:ping_ratio", 0.0F);
         }
      } catch (Exception var11) {
         Log.w("AdvertisingIdClient", "Error while reading from SharedPreferences ", var11);
      }

      AdvertisingIdClient var14 = new AdvertisingIdClient(var0, -1L, var2);

      try {
         var14.start(false);
         AdvertisingIdClient.Info var5 = var14.getInfo();
         var14.zza(var5, var2, var3, (Throwable)null);
         AdvertisingIdClient.Info var6 = var5;
         return var6;
      } catch (Throwable var12) {
         var14.zza((AdvertisingIdClient.Info)null, var2, var3, var12);
      } finally {
         var14.finish();
      }

      return null;
   }

   private final void zza(AdvertisingIdClient.Info var1, boolean var2, float var3, Throwable var4) {
      if (Math.random() <= (double)var3) {
         Bundle var9;
         (var9 = new Bundle()).putString("app_context", var2 ? "1" : "0");
         if (var1 != null) {
            var9.putString("limit_ad_tracking", var1.isLimitAdTrackingEnabled() ? "1" : "0");
         }

         if (var1 != null && var1.getId() != null) {
            var9.putString("ad_id_size", Integer.toString(var1.getId().length()));
         }

         if (var4 != null) {
            var9.putString("error", var4.getClass().getName());
         }

         Builder var10 = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
         Iterator var11 = var9.keySet().iterator();

         while(var11.hasNext()) {
            String var12 = (String)var11.next();
            var10.appendQueryParameter(var12, var9.getString(var12));
         }

         String var5 = var10.build().toString();
         (new zza(this, var5)).start();
      }
   }

   private static zzfd zza(Context var0, com.google.android.gms.common.zza var1) throws IOException {
      try {
         return zzfe.zzc(var1.zza(10000L, TimeUnit.MILLISECONDS));
      } catch (InterruptedException var3) {
         throw new IOException("Interrupted exception");
      } catch (Throwable var4) {
         throw new IOException(var4);
      }
   }

   public static final class Info {
      private final String zzsI;
      private final boolean zzsJ;

      public Info(String var1, boolean var2) {
         this.zzsI = var1;
         this.zzsJ = var2;
      }

      public final String getId() {
         return this.zzsI;
      }

      public final boolean isLimitAdTrackingEnabled() {
         return this.zzsJ;
      }

      public final String toString() {
         String var1 = this.zzsI;
         boolean var2 = this.zzsJ;
         return (new StringBuilder(7 + String.valueOf(var1).length())).append("{").append(var1).append("}").append(var2).toString();
      }
   }

   static class zza extends Thread {
      private WeakReference zzsE;
      private long zzsF;
      CountDownLatch zzsG;
      boolean zzsH;

      public zza(AdvertisingIdClient var1, long var2) {
         this.zzsE = new WeakReference(var1);
         this.zzsF = var2;
         this.zzsG = new CountDownLatch(1);
         this.zzsH = false;
         this.start();
      }

      private final void disconnect() {
         AdvertisingIdClient var1;
         if ((var1 = (AdvertisingIdClient)this.zzsE.get()) != null) {
            var1.finish();
            this.zzsH = true;
         }

      }

      public final void run() {
         try {
            if (!this.zzsG.await(this.zzsF, TimeUnit.MILLISECONDS)) {
               this.disconnect();
            }

         } catch (InterruptedException var1) {
            this.disconnect();
         }
      }
   }
}
