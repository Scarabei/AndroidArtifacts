package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzj;
import java.util.Iterator;
import java.util.Map;

final class zzbcg extends zzbcn {
   private final Map zzaDr;
   // $FF: synthetic field
   final zzbcd zzaDp;

   public zzbcg(zzbcd var1, Map var2) {
      super(var1, (zzbce)null);
      this.zzaDp = var1;
      this.zzaDr = var2;
   }

   @WorkerThread
   public final void zzpV() {
      boolean var1 = false;
      boolean var2 = false;
      boolean var3 = true;
      Iterator var4 = this.zzaDr.keySet().iterator();

      while(var4.hasNext()) {
         Api.zze var5;
         if ((var5 = (Api.zze)var4.next()).zzpe()) {
            var2 = true;
            if (!zzbcf.zza((zzbcf)this.zzaDr.get(var5))) {
               var1 = true;
               break;
            }
         } else {
            var3 = false;
         }
      }

      int var10000 = var2 ? zzbcd.zzb(this.zzaDp).isGooglePlayServicesAvailable(zzbcd.zza(this.zzaDp)) : 0;
      int var8 = var10000;
      if (var10000 != 0 && (var1 || var3)) {
         ConnectionResult var10 = new ConnectionResult(var8, (PendingIntent)null);
         zzbcd.zzd(this.zzaDp).zza((zzbcy)(new zzbch(this, this.zzaDp, var10)));
      } else {
         if (zzbcd.zze(this.zzaDp)) {
            zzbcd.zzf(this.zzaDp).connect();
         }

         Iterator var9 = this.zzaDr.keySet().iterator();

         while(true) {
            while(var9.hasNext()) {
               Api.zze var6 = (Api.zze)var9.next();
               zzj var7 = (zzj)this.zzaDr.get(var6);
               if (var6.zzpe() && var8 != 0) {
                  zzbcd.zzd(this.zzaDp).zza((zzbcy)(new zzbci(this, this.zzaDp, var7)));
               } else {
                  var6.zza(var7);
               }
            }

            return;
         }
      }
   }
}
