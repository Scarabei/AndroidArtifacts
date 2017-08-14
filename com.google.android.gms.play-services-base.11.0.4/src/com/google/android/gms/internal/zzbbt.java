package com.google.android.gms.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;

public final class zzbbt {
   private final Map zzaCR = Collections.synchronizedMap(new WeakHashMap());
   private final Map zzaCS = Collections.synchronizedMap(new WeakHashMap());

   final void zza(zzbbe var1, boolean var2) {
      this.zzaCR.put(var1, var2);
      var1.zza((PendingResult.zza)(new zzbbu(this, var1)));
   }

   final void zza(TaskCompletionSource var1, boolean var2) {
      this.zzaCS.put(var1, var2);
      var1.getTask().addOnCompleteListener(new zzbbv(this, var1));
   }

   final boolean zzpO() {
      return !this.zzaCR.isEmpty() || !this.zzaCS.isEmpty();
   }

   public final void zzpP() {
      this.zza(false, zzbdb.zzaEc);
   }

   public final void zzpQ() {
      this.zza(true, zzbev.zzaFj);
   }

   private final void zza(boolean var1, Status var2) {
      Map var5 = this.zzaCR;
      HashMap var3;
      synchronized(this.zzaCR) {
         var3 = new HashMap(this.zzaCR);
      }

      var5 = this.zzaCS;
      HashMap var4;
      synchronized(this.zzaCS) {
         var4 = new HashMap(this.zzaCS);
      }

      Iterator var10 = var3.entrySet().iterator();

      while(true) {
         Entry var6;
         do {
            if (!var10.hasNext()) {
               var10 = var4.entrySet().iterator();

               while(true) {
                  do {
                     if (!var10.hasNext()) {
                        return;
                     }

                     var6 = (Entry)var10.next();
                  } while(!var1 && !((Boolean)var6.getValue()).booleanValue());

                  ((TaskCompletionSource)var6.getKey()).trySetException(new ApiException(var2));
               }
            }

            var6 = (Entry)var10.next();
         } while(!var1 && !((Boolean)var6.getValue()).booleanValue());

         ((zzbbe)var6.getKey()).zzs(var2);
      }
   }

   // $FF: synthetic method
   static Map zza(zzbbt var0) {
      return var0.zzaCR;
   }

   // $FF: synthetic method
   static Map zzb(zzbbt var0) {
      return var0.zzaCS;
   }
}
