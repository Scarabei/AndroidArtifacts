package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzea {
   private static String TAG = zzea.class.getSimpleName();
   private final zzdb zzpJ;
   private final String className;
   private final String zzrv;
   private final int zzrw = 2;
   private volatile Method zzrx = null;
   private final Class[] zzry;
   private CountDownLatch zzrz = new CountDownLatch(1);

   public zzea(zzdb var1, String var2, String var3, Class... var4) {
      this.zzpJ = var1;
      this.className = var2;
      this.zzrv = var3;
      this.zzry = var4;
      this.zzpJ.zzC().submit(new zzeb(this));
   }

   private final void zzX() {
      try {
         Class var1;
         if ((var1 = this.zzpJ.zzD().loadClass(this.zzb(this.zzpJ.zzF(), this.className))) == null) {
            return;
         }

         this.zzrx = var1.getMethod(this.zzb(this.zzpJ.zzF(), this.zzrv), this.zzry);
         if (this.zzrx != null) {
            return;
         }

         return;
      } catch (zzcx var9) {
         return;
      } catch (UnsupportedEncodingException var10) {
         return;
      } catch (ClassNotFoundException var11) {
         return;
      } catch (NoSuchMethodException var12) {
         return;
      } catch (NullPointerException var13) {
         ;
      } finally {
         this.zzrz.countDown();
      }

   }

   private final String zzb(byte[] var1, String var2) throws zzcx, UnsupportedEncodingException {
      byte[] var3 = this.zzpJ.zzE().zza(var1, var2);
      return new String(var3, "UTF-8");
   }

   public final Method zzY() {
      if (this.zzrx != null) {
         return this.zzrx;
      } else {
         try {
            return !this.zzrz.await(2L, TimeUnit.SECONDS) ? null : this.zzrx;
         } catch (InterruptedException var1) {
            return null;
         }
      }
   }

   // $FF: synthetic method
   static void zza(zzea var0) {
      var0.zzX();
   }
}
