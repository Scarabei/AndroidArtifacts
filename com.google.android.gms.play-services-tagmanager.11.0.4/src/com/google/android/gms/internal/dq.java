package com.google.android.gms.internal;

import java.util.Iterator;

final class dq implements Iterator {
   // $FF: synthetic field
   private Iterator zzbLm;

   dq(dp var1, Iterator var2) {
      this.zzbLm = var2;
      super();
   }

   public final boolean hasNext() {
      return this.zzbLm.hasNext();
   }

   public final void remove() {
      this.zzbLm.remove();
   }

   // $FF: synthetic method
   public final Object next() {
      return new eb((String)this.zzbLm.next());
   }
}
