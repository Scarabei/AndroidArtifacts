package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public final class zzh extends zzb {
   private Object zzaFQ;

   public zzh(DataBuffer var1) {
      super(var1);
   }

   public final Object next() {
      if (!this.hasNext()) {
         int var2 = this.zzaFv;
         throw new NoSuchElementException((new StringBuilder(46)).append("Cannot advance the iterator beyond ").append(var2).toString());
      } else {
         ++this.zzaFv;
         if (this.zzaFv == 0) {
            this.zzaFQ = this.zzaFu.get(0);
            if (!(this.zzaFQ instanceof zzc)) {
               String var1 = String.valueOf(this.zzaFQ.getClass());
               throw new IllegalStateException((new StringBuilder(44 + String.valueOf(var1).length())).append("DataBuffer reference of type ").append(var1).append(" is not movable").toString());
            }
         } else {
            ((zzc)this.zzaFQ).zzar(this.zzaFv);
         }

         return this.zzaFQ;
      }
   }
}
