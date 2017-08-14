package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzcaj {
   private Object zzbbF = new Object();
   private Handler zzbbG;
   private boolean zzbbH;
   private HashMap zzbbI;
   private int zzbbJ;

   public zzcaj(Looper var1, int var2) {
      this.zzbbG = new Handler(var1);
      this.zzbbI = new HashMap();
      this.zzbbJ = 1000;
   }

   protected abstract void zzq(String var1, int var2);

   public final void zzr(String var1, int var2) {
      Object var3 = this.zzbbF;
      synchronized(this.zzbbF) {
         if (!this.zzbbH) {
            this.zzbbH = true;
            this.zzbbG.postDelayed(new zzcak(this), (long)this.zzbbJ);
         }

         AtomicInteger var4;
         if ((var4 = (AtomicInteger)this.zzbbI.get(var1)) == null) {
            var4 = new AtomicInteger();
            this.zzbbI.put(var1, var4);
         }

         var4.addAndGet(var2);
      }
   }

   public final void flush() {
      Object var1 = this.zzbbF;
      synchronized(this.zzbbF) {
         Iterator var2 = this.zzbbI.entrySet().iterator();

         while(var2.hasNext()) {
            Entry var3 = (Entry)var2.next();
            this.zzq((String)var3.getKey(), ((AtomicInteger)var3.getValue()).get());
         }

         this.zzbbI.clear();
      }
   }

   private final void zzuX() {
      Object var1 = this.zzbbF;
      synchronized(this.zzbbF) {
         this.zzbbH = false;
         this.flush();
      }
   }

   // $FF: synthetic method
   static void zza(zzcaj var0) {
      var0.zzuX();
   }
}
