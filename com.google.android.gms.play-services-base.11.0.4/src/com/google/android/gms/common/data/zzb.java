package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb implements Iterator {
   protected final DataBuffer zzaFu;
   protected int zzaFv;

   public zzb(DataBuffer var1) {
      this.zzaFu = (DataBuffer)zzbo.zzu(var1);
      this.zzaFv = -1;
   }

   public boolean hasNext() {
      return this.zzaFv < this.zzaFu.getCount() - 1;
   }

   public Object next() {
      if (!this.hasNext()) {
         int var1 = this.zzaFv;
         throw new NoSuchElementException((new StringBuilder(46)).append("Cannot advance the iterator beyond ").append(var1).toString());
      } else {
         return this.zzaFu.get(++this.zzaFv);
      }
   }

   public void remove() {
      throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
   }
}
