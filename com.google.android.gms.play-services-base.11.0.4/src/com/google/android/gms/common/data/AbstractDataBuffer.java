package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer implements DataBuffer {
   protected final DataHolder zzaCX;

   protected AbstractDataBuffer(DataHolder var1) {
      this.zzaCX = var1;
   }

   public int getCount() {
      return this.zzaCX == null ? 0 : this.zzaCX.zzaFG;
   }

   public abstract Object get(int var1);

   /** @deprecated */
   @Deprecated
   public final void close() {
      this.release();
   }

   /** @deprecated */
   @Deprecated
   public boolean isClosed() {
      return this.zzaCX == null || this.zzaCX.isClosed();
   }

   public final Bundle zzqN() {
      return this.zzaCX.zzqN();
   }

   public Iterator iterator() {
      return new zzb(this);
   }

   public Iterator singleRefIterator() {
      return new zzh(this);
   }

   public void release() {
      if (this.zzaCX != null) {
         this.zzaCX.close();
      }

   }
}
