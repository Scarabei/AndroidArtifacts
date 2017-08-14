package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzs {
   private AtomicInteger zzW;
   private final Map zzX;
   private final Set zzY;
   private final PriorityBlockingQueue zzZ;
   private final PriorityBlockingQueue zzaa;
   private final zzb zzi;
   private final zzk zzx;
   private final zzw zzj;
   private zzl[] zzab;
   private zzd zzac;
   private List zzad;

   private zzs(zzb var1, zzk var2, int var3, zzw var4) {
      this.zzW = new AtomicInteger();
      this.zzX = new HashMap();
      this.zzY = new HashSet();
      this.zzZ = new PriorityBlockingQueue();
      this.zzaa = new PriorityBlockingQueue();
      this.zzad = new ArrayList();
      this.zzi = var1;
      this.zzx = var2;
      this.zzab = new zzl[4];
      this.zzj = var4;
   }

   private zzs(zzb var1, zzk var2, int var3) {
      this(var1, var2, 4, new zzh(new Handler(Looper.getMainLooper())));
   }

   public zzs(zzb var1, zzk var2) {
      this(var1, var2, 4);
   }

   public final void start() {
      zzs var3 = this;
      if (this.zzac != null) {
         this.zzac.quit();
      }

      for(int var4 = 0; var4 < var3.zzab.length; ++var4) {
         if (var3.zzab[var4] != null) {
            var3.zzab[var4].quit();
         }
      }

      this.zzac = new zzd(this.zzZ, this.zzaa, this.zzi, this.zzj);
      this.zzac.start();

      for(int var1 = 0; var1 < this.zzab.length; ++var1) {
         zzl var2 = new zzl(this.zzaa, this.zzx, this.zzi, this.zzj);
         this.zzab[var1] = var2;
         var2.start();
      }

   }

   public final zzp zzc(zzp var1) {
      var1.zza(this);
      Set var2 = this.zzY;
      synchronized(this.zzY) {
         this.zzY.add(var1);
      }

      var1.zza(this.zzW.incrementAndGet());
      var1.zzb("add-to-queue");
      if (!var1.zzh()) {
         this.zzaa.add(var1);
         return var1;
      } else {
         Map var8 = this.zzX;
         synchronized(this.zzX) {
            String var3 = var1.zzd();
            if (this.zzX.containsKey(var3)) {
               Object var4;
               if ((var4 = (Queue)this.zzX.get(var3)) == null) {
                  var4 = new LinkedList();
               }

               ((Queue)var4).add(var1);
               this.zzX.put(var3, var4);
               if (com.google.android.gms.internal.zzab.DEBUG) {
                  com.google.android.gms.internal.zzab.zza("Request for cacheKey=%s is in flight, putting on hold.", var3);
               }
            } else {
               this.zzX.put(var3, (Object)null);
               this.zzZ.add(var1);
            }

            return var1;
         }
      }
   }

   final void zzd(zzp var1) {
      Set var2 = this.zzY;
      synchronized(this.zzY) {
         this.zzY.remove(var1);
      }

      List var10 = this.zzad;
      synchronized(this.zzad) {
         Iterator var3 = this.zzad.iterator();

         while(true) {
            if (!var3.hasNext()) {
               break;
            }

            var3.next();
         }
      }

      if (var1.zzh()) {
         Map var11 = this.zzX;
         synchronized(this.zzX) {
            String var12 = var1.zzd();
            Queue var4;
            if ((var4 = (Queue)this.zzX.remove(var12)) != null) {
               if (com.google.android.gms.internal.zzab.DEBUG) {
                  com.google.android.gms.internal.zzab.zza("Releasing %d waiting requests for cacheKey=%s.", var4.size(), var12);
               }

               this.zzZ.addAll(var4);
            }

         }
      }
   }
}
