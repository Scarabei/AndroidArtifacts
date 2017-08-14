package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@zzzn
public final class zzaji {
   public static zzajm zza(zzajm var0, zzajl var1) {
      zzajg var2 = new zzajg();
      var0.zzc(new zzajj(var2, var1, var0));
      return var2;
   }

   public static Object zza(Future var0, Object var1) {
      try {
         zzme var3 = zzmo.zzEJ;
         return var0.get(((Long)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).longValue(), TimeUnit.MILLISECONDS);
      } catch (InterruptedException var4) {
         var0.cancel(true);
         zzafr.zzc("InterruptedException caught while resolving future.", var4);
         Thread.currentThread().interrupt();
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var4, (String)"Futures.resolveFuture");
         return var1;
      } catch (Exception var5) {
         var0.cancel(true);
         zzafr.zzb("Error waiting for future.", var5);
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var5, (String)"Futures.resolveFuture");
         return var1;
      }
   }

   public static Object zza(Future var0, Object var1, long var2, TimeUnit var4) {
      try {
         return var0.get(var2, var4);
      } catch (InterruptedException var6) {
         var0.cancel(true);
         zzafr.zzc("InterruptedException caught while resolving future.", var6);
         Thread.currentThread().interrupt();
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var6, (String)"Futures.resolveFuture");
         return var1;
      } catch (Exception var7) {
         var0.cancel(true);
         zzafr.zzb("Error waiting for future.", var7);
         com.google.android.gms.ads.internal.zzbs.zzbD().zza((Throwable)var7, (String)"Futures.resolveFuture");
         return var1;
      }
   }

   public static zzajm zzp(List var0) {
      zzajg var1 = new zzajg();
      int var2 = var0.size();
      AtomicInteger var3 = new AtomicInteger(0);
      Iterator var4 = var0.iterator();

      while(var4.hasNext()) {
         ((zzajm)var4.next()).zzc(new zzajk(var3, var2, var1, var0));
      }

      return var1;
   }

   private static List zzq(List var0) throws ExecutionException, InterruptedException {
      ArrayList var1 = new ArrayList();
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         Object var3;
         if ((var3 = ((zzajm)var2.next()).get()) != null) {
            var1.add(var3);
         }
      }

      return var1;
   }

   // $FF: synthetic method
   static List zzr(List var0) throws ExecutionException, InterruptedException {
      return zzq(var0);
   }
}
