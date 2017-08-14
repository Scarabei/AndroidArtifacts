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

final class zzbbs implements OnCompleteListener {
   private zzbei zzaCQ;
   // $FF: synthetic field
   private zzbbp zzaCP;

   zzbbs(zzbbp var1, zzbei var2) {
      this.zzaCP = var1;
      super();
      this.zzaCQ = var2;
   }

   final void cancel() {
      this.zzaCQ.zzmF();
   }

   public final void onComplete(@NonNull Task var1) {
      zzbbp.zza(this.zzaCP).lock();

      try {
         if (zzbbp.zzb(this.zzaCP)) {
            if (var1.isSuccessful()) {
               zzbbp.zzb(this.zzaCP, new ArrayMap(zzbbp.zzm(this.zzaCP).size()));
               Iterator var2 = zzbbp.zzm(this.zzaCP).values().iterator();

               while(var2.hasNext()) {
                  zzbbo var3 = (zzbbo)var2.next();
                  zzbbp.zzg(this.zzaCP).put(var3.zzph(), ConnectionResult.zzazX);
               }
            } else if (var1.getException() instanceof zza) {
               zza var10 = (zza)var1.getException();
               if (zzbbp.zze(this.zzaCP)) {
                  zzbbp.zzb(this.zzaCP, new ArrayMap(zzbbp.zzm(this.zzaCP).size()));
                  Iterator var11 = zzbbp.zzm(this.zzaCP).values().iterator();

                  while(var11.hasNext()) {
                     zzbbo var4;
                     zzbat var5 = (var4 = (zzbbo)var11.next()).zzph();
                     ConnectionResult var6 = var10.zza(var4);
                     if (zzbbp.zza(this.zzaCP, var4, var6)) {
                        zzbbp.zzg(this.zzaCP).put(var5, new ConnectionResult(16));
                     } else {
                        zzbbp.zzg(this.zzaCP).put(var5, var6);
                     }
                  }
               } else {
                  zzbbp.zzb(this.zzaCP, var10.zzpf());
               }
            } else {
               Log.e("ConnectionlessGAC", "Unexpected availability exception", var1.getException());
               zzbbp.zzb(this.zzaCP, Collections.emptyMap());
            }

            if (this.zzaCP.isConnected()) {
               zzbbp.zzd(this.zzaCP).putAll(zzbbp.zzg(this.zzaCP));
               if (zzbbp.zzf(this.zzaCP) == null) {
                  zzbbp.zzi(this.zzaCP);
                  zzbbp.zzj(this.zzaCP);
                  zzbbp.zzl(this.zzaCP).signalAll();
               }
            }

            this.zzaCQ.zzmF();
            return;
         }

         this.zzaCQ.zzmF();
      } finally {
         zzbbp.zza(this.zzaCP).unlock();
      }

   }
}
