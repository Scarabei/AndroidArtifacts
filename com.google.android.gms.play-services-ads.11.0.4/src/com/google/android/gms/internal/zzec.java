package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzec implements Callable {
   private String TAG = this.getClass().getSimpleName();
   protected final zzdb zzpJ;
   private String className;
   private String zzrv;
   protected final zzax zzro;
   protected Method zzrx;
   private int zzrB;
   private int zzrC;

   public zzec(zzdb var1, String var2, String var3, zzax var4, int var5, int var6) {
      this.zzpJ = var1;
      this.className = var2;
      this.zzrv = var3;
      this.zzro = var4;
      this.zzrB = var5;
      this.zzrC = var6;
   }

   public Void zzV() throws Exception {
      try {
         long var1 = System.nanoTime();
         this.zzrx = this.zzpJ.zzc(this.className, this.zzrv);
         if (this.zzrx == null) {
            return null;
         }

         this.zzT();
         zzcn var3;
         if ((var3 = this.zzpJ.zzI()) != null && this.zzrB != Integer.MIN_VALUE) {
            var3.zza(this.zzrC, this.zzrB, (System.nanoTime() - var1) / 1000L);
         }
      } catch (InvocationTargetException | IllegalAccessException var4) {
         ;
      }

      return null;
   }

   protected abstract void zzT() throws IllegalAccessException, InvocationTargetException;

   // $FF: synthetic method
   public Object call() throws Exception {
      return this.zzV();
   }
}
