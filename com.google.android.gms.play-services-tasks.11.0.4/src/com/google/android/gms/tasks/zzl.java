package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

final class zzl {
   private final Object mLock = new Object();
   private Queue zzbMc;
   private boolean zzbMd;

   public final void zza(@NonNull zzk var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzbMc == null) {
            this.zzbMc = new ArrayDeque();
         }

         this.zzbMc.add(var1);
      }
   }

   public final void zza(@NonNull Task var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzbMc == null || this.zzbMd) {
            return;
         }

         this.zzbMd = true;
      }

      while(true) {
         Object var3 = this.mLock;
         zzk var7;
         synchronized(this.mLock) {
            if ((var7 = (zzk)this.zzbMc.poll()) == null) {
               this.zzbMd = false;
               return;
            }
         }

         var7.onComplete(var1);
      }
   }
}
