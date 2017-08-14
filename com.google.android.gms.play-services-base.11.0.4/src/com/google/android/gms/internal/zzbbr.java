package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

final class zzbbr implements OnCompleteListener {
   // $FF: synthetic field
   private zzbbp zzaCP;

   private zzbbr(zzbbp var1) {
      this.zzaCP = var1;
      super();
   }

   public final void onComplete(@NonNull Task var1) {
      zzbbp.zza(this.zzaCP).lock();

      try {
         if (zzbbp.zzb(this.zzaCP)) {
            if (var1.isSuccessful()) {
               zzbbp.zza((zzbbp)this.zzaCP, (Map)(new ArrayMap(zzbbp.zzc(this.zzaCP).size())));
               Iterator var2 = zzbbp.zzc(this.zzaCP).values().iterator();

               while(var2.hasNext()) {
                  zzbbo var3 = (zzbbo)var2.next();
                  zzbbp.zzd(this.zzaCP).put(var3.zzph(), ConnectionResult.zzazX);
               }
            } else if (var1.getException() instanceof zza) {
               zza var10 = (zza)var1.getException();
               if (zzbbp.zze(this.zzaCP)) {
                  zzbbp.zza((zzbbp)this.zzaCP, (Map)(new ArrayMap(zzbbp.zzc(this.zzaCP).size())));
                  Iterator var11 = zzbbp.zzc(this.zzaCP).values().iterator();

                  while(var11.hasNext()) {
                     zzbbo var4;
                     zzbat var5 = (var4 = (zzbbo)var11.next()).zzph();
                     ConnectionResult var6 = var10.zza(var4);
                     if (zzbbp.zza(this.zzaCP, var4, var6)) {
                        zzbbp.zzd(this.zzaCP).put(var5, new ConnectionResult(16));
                     } else {
                        zzbbp.zzd(this.zzaCP).put(var5, var6);
                     }
                  }
               } else {
                  zzbbp.zza((zzbbp)this.zzaCP, (Map)var10.zzpf());
               }

               zzbbp.zza(this.zzaCP, zzbbp.zzf(this.zzaCP));
            } else {
               Log.e("ConnectionlessGAC", "Unexpected availability exception", var1.getException());
               zzbbp.zza(this.zzaCP, Collections.emptyMap());
               zzbbp.zza(this.zzaCP, new ConnectionResult(8));
            }

            if (zzbbp.zzg(this.zzaCP) != null) {
               zzbbp.zzd(this.zzaCP).putAll(zzbbp.zzg(this.zzaCP));
               zzbbp.zza(this.zzaCP, zzbbp.zzf(this.zzaCP));
            }

            if (zzbbp.zzh(this.zzaCP) == null) {
               zzbbp.zzi(this.zzaCP);
               zzbbp.zzj(this.zzaCP);
            } else {
               zzbbp.zza(this.zzaCP, false);
               zzbbp.zzk(this.zzaCP).zzc(zzbbp.zzh(this.zzaCP));
            }

            zzbbp.zzl(this.zzaCP).signalAll();
            return;
         }
      } finally {
         zzbbp.zza(this.zzaCP).unlock();
      }

   }

   // $FF: synthetic method
   zzbbr(zzbbp var1, zzbbq var2) {
      this(var1);
   }
}
