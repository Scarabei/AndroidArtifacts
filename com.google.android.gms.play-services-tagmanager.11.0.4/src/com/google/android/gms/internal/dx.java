package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class dx implements Iterator {
   private int zzbLy;
   // $FF: synthetic field
   private dw zzbLz;

   dx(dw var1) {
      this.zzbLz = var1;
      super();
      this.zzbLy = 0;
   }

   public final boolean hasNext() {
      for(int var1 = this.zzbLy; var1 < dw.zza(this.zzbLz).size(); ++var1) {
         if (dw.zza(this.zzbLz).get(var1) != null) {
            return true;
         }
      }

      return false;
   }

   public final void remove() {
      throw new UnsupportedOperationException();
   }

   // $FF: synthetic method
   public final Object next() {
      dx var1 = this;
      if (this.zzbLy >= dw.zza(this.zzbLz).size()) {
         throw new NoSuchElementException();
      } else {
         for(int var2 = this.zzbLy; var2 < dw.zza(var1.zzbLz).size(); ++var2) {
            if (dw.zza(var1.zzbLz).get(var2) != null) {
               var1.zzbLy = var2;
               return new dt((double)(var1.zzbLy++));
            }
         }

         throw new NoSuchElementException();
      }
   }
}
