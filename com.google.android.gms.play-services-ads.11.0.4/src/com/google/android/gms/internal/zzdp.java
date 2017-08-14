package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class zzdp extends zzec {
   public zzdp(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, 24);
   }

   public final Void zzV() throws Exception {
      if (this.zzpJ.isInitialized()) {
         return super.zzV();
      } else {
         if (this.zzpJ.zzH()) {
            this.zzW();
         }

         return null;
      }
   }

   protected final void zzT() throws IllegalAccessException, InvocationTargetException {
      if (this.zzpJ.zzH()) {
         this.zzW();
      } else {
         zzdp var1 = this;
         zzax var2 = this.zzro;
         synchronized(this.zzro) {
            var1.zzro.zzbU = (String)var1.zzrx.invoke((Object)null, var1.zzpJ.getApplicationContext());
         }
      }
   }

   private final void zzW() {
      AdvertisingIdClient var1;
      if ((var1 = this.zzpJ.zzO()) != null) {
         try {
            Info var2;
            String var3;
            if ((var3 = zzdg.zzn((var2 = var1.getInfo()).getId())) != null) {
               zzax var4 = this.zzro;
               synchronized(this.zzro) {
                  this.zzro.zzbU = var3;
                  this.zzro.zzbW = var2.isLimitAdTrackingEnabled();
                  this.zzro.zzbV = Integer.valueOf(5);
               }
            }
         } catch (IOException var7) {
            ;
         }
      }
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zzV();
   }
}
