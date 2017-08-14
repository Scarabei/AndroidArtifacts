package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzbx;
import java.util.Iterator;

public final class zzbca implements zzbcw {
   private final zzbcx zzaCZ;
   private boolean zzaDa = false;

   public zzbca(zzbcx var1) {
      this.zzaCZ = var1;
   }

   public final void begin() {
   }

   public final zzbay zzd(zzbay var1) {
      return this.zze(var1);
   }

   public final zzbay zze(zzbay var1) {
      try {
         this.zzaCZ.zzaCl.zzaDL.zzb(var1);
         zzbcp var10000 = this.zzaCZ.zzaCl;
         Api.zzc var6 = var1.zzpd();
         Api.zze var7;
         zzbo.zzb(var7 = (Api.zze)var10000.zzaDF.get(var6), "Appropriate Api was not requested.");
         if (!var7.isConnected() && this.zzaCZ.zzaDU.containsKey(var1.zzpd())) {
            var1.zzr(new Status(17));
         } else {
            Api.zze var5;
            if (var7 instanceof zzbx) {
               zzbx var8 = (zzbx)var7;
               var5 = null;
            } else {
               var5 = var7;
            }

            var1.zzb(var5);
         }
      } catch (DeadObjectException var9) {
         this.zzaCZ.zza((zzbcy)(new zzbcb(this, this)));
      }

      return var1;
   }

   public final boolean disconnect() {
      if (this.zzaDa) {
         return false;
      } else if (!this.zzaCZ.zzaCl.zzqf()) {
         this.zzaCZ.zzg((ConnectionResult)null);
         return true;
      } else {
         this.zzaDa = true;
         Iterator var1 = this.zzaCZ.zzaCl.zzaDK.iterator();

         while(var1.hasNext()) {
            ((zzbes)var1.next()).zzqK();
         }

         return false;
      }
   }

   public final void connect() {
      if (this.zzaDa) {
         this.zzaDa = false;
         this.zzaCZ.zza((zzbcy)(new zzbcc(this, this)));
      }

   }

   public final void onConnected(Bundle var1) {
   }

   public final void zza(ConnectionResult var1, Api var2, boolean var3) {
   }

   public final void onConnectionSuspended(int var1) {
      this.zzaCZ.zzg((ConnectionResult)null);
      this.zzaCZ.zzaDY.zze(var1, this.zzaDa);
   }

   final void zzpU() {
      if (this.zzaDa) {
         this.zzaDa = false;
         this.zzaCZ.zzaCl.zzaDL.release();
         this.disconnect();
      }

   }

   // $FF: synthetic method
   static zzbcx zza(zzbca var0) {
      return var0.zzaCZ;
   }
}
