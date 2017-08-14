package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class dr implements Iterator {
   private dr() {
   }

   public final boolean hasNext() {
      return false;
   }

   public final void remove() {
      throw new UnsupportedOperationException();
   }

   // $FF: synthetic method
   public final Object next() {
      throw new NoSuchElementException();
   }

   // $FF: synthetic method
   dr(dq var1) {
      this();
   }
}
