package com.google.android.gms.internal;

import java.util.Iterator;

final class dy implements Iterator {
   // $FF: synthetic field
   private Iterator zzbLA;
   // $FF: synthetic field
   private Iterator zzbLB;

   dy(dw var1, Iterator var2, Iterator var3) {
      this.zzbLA = var2;
      this.zzbLB = var3;
      super();
   }

   public final boolean hasNext() {
      return this.zzbLA.hasNext() || this.zzbLB.hasNext();
   }

   public final void remove() {
      throw new UnsupportedOperationException();
   }

   // $FF: synthetic method
   public final Object next() {
      return this.zzbLA.hasNext() ? (dp)this.zzbLA.next() : (dp)this.zzbLB.next();
   }
}
