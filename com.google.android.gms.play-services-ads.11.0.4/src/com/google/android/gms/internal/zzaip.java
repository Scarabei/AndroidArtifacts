package com.google.android.gms.internal;

import android.graphics.Bitmap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@zzzn
public final class zzaip {
   private Map zzaar = new ConcurrentHashMap();
   private AtomicInteger zzaas = new AtomicInteger(0);

   public final int zzb(Bitmap var1) {
      if (var1 == null) {
         zzafr.zzaC("Bitmap is null. Skipping putting into the Memory Map.");
         return -1;
      } else {
         int var2 = this.zzaas.getAndIncrement();
         this.zzaar.put(var2, var1);
         return var2;
      }
   }

   public final Bitmap zza(Integer var1) {
      return (Bitmap)this.zzaar.get(var1);
   }

   public final void zzb(Integer var1) {
      this.zzaar.remove(var1);
   }
}
