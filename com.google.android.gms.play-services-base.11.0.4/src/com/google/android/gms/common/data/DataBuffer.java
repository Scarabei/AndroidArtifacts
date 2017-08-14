package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;
import java.util.Iterator;

public interface DataBuffer extends Releasable, Iterable {
   int getCount();

   Object get(int var1);

   Bundle zzqN();

   /** @deprecated */
   @Deprecated
   void close();

   /** @deprecated */
   @Deprecated
   boolean isClosed();

   Iterator iterator();

   Iterator singleRefIterator();

   void release();
}
