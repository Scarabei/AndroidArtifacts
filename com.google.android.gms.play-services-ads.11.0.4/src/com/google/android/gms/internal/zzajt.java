package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzzn
public class zzajt implements zzajp {
   private final Object mLock = new Object();
   private int zzLe = 0;
   private BlockingQueue zzabg = new LinkedBlockingQueue();
   private Object zzabh;

   public void zza(zzajs var1, zzajq var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzLe == 1) {
            var1.zzc(this.zzabh);
         } else if (this.zzLe == -1) {
            var2.run();
         } else if (this.zzLe == 0) {
            this.zzabg.add(new zzaju(this, var1, var2));
         }

      }
   }

   public void zzf(Object var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzLe != 0) {
            throw new UnsupportedOperationException();
         } else {
            this.zzabh = var1;
            this.zzLe = 1;
            Iterator var3 = this.zzabg.iterator();

            while(var3.hasNext()) {
               ((zzaju)var3.next()).zzabi.zzc(var1);
            }

            this.zzabg.clear();
         }
      }
   }

   public void reject() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzLe != 0) {
            throw new UnsupportedOperationException();
         } else {
            this.zzLe = -1;
            Iterator var2 = this.zzabg.iterator();

            while(var2.hasNext()) {
               ((zzaju)var2.next()).zzabj.run();
            }

            this.zzabg.clear();
         }
      }
   }

   public int getStatus() {
      return this.zzLe;
   }
}
