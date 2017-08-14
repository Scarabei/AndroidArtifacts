package com.google.android.gms.internal;

import java.util.Iterator;

final class zzgx implements Runnable {
   // $FF: synthetic field
   private zzgw zzyw;

   zzgx(zzgw var1) {
      this.zzyw = var1;
      super();
   }

   public final void run() {
      synchronized(zzgw.zza(this.zzyw)) {
         if (zzgw.zzb(this.zzyw) && zzgw.zzc(this.zzyw)) {
            zzgw.zza(this.zzyw, false);
            zzafr.zzaC("App went background");
            Iterator var2 = zzgw.zzd(this.zzyw).iterator();

            while(var2.hasNext()) {
               zzgy var3 = (zzgy)var2.next();

               try {
                  var3.zzf(false);
               } catch (Exception var6) {
                  zzafr.zzb("OnForegroundStateChangedListener threw exception.", var6);
               }
            }
         } else {
            zzafr.zzaC("App is still foreground");
         }

      }
   }
}
