package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class ec implements Iterator {
   private int zzbLy;
   // $FF: synthetic field
   private eb zzbLF;

   ec(eb var1) {
      this.zzbLF = var1;
      super();
      this.zzbLy = 0;
   }

   public final boolean hasNext() {
      return this.zzbLy < eb.zza(this.zzbLF).length();
   }

   public final void remove() {
      throw new UnsupportedOperationException();
   }

   // $FF: synthetic method
   public final Object next() {
      if (this.zzbLy >= eb.zza(this.zzbLF).length()) {
         throw new NoSuchElementException();
      } else {
         return new dt((double)(this.zzbLy++));
      }
   }
}
