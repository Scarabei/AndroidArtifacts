package com.google.android.gms.internal;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Set;

public final class zzbav {
   private final ArrayMap zzaAB = new ArrayMap();
   private final TaskCompletionSource zzaBG = new TaskCompletionSource();
   private int zzaBH;
   private boolean zzaBI = false;

   public zzbav(Iterable var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         GoogleApi var3 = (GoogleApi)var2.next();
         this.zzaAB.put(var3.zzph(), (Object)null);
      }

      this.zzaBH = this.zzaAB.keySet().size();
   }

   public final Set zzpt() {
      return this.zzaAB.keySet();
   }

   public final Task getTask() {
      return this.zzaBG.getTask();
   }

   public final void zzpu() {
      this.zzaBG.setResult((Object)null);
   }

   public final void zza(zzbat var1, ConnectionResult var2) {
      this.zzaAB.put(var1, var2);
      --this.zzaBH;
      if (!var2.isSuccess()) {
         this.zzaBI = true;
      }

      if (this.zzaBH == 0) {
         if (this.zzaBI) {
            zza var3 = new zza(this.zzaAB);
            this.zzaBG.setException(var3);
            return;
         }

         this.zzaBG.setResult((Object)null);
      }

   }
}
