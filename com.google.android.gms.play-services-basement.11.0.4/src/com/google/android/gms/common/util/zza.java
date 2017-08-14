package com.google.android.gms.common.util;

import android.support.v4.util.ArrayMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

/** @deprecated */
@Deprecated
public final class zza extends AbstractSet {
   private final ArrayMap zzaJC;

   public zza() {
      this.zzaJC = new ArrayMap();
   }

   public zza(int var1) {
      this.zzaJC = new ArrayMap(var1);
   }

   public final boolean add(Object var1) {
      if (this.zzaJC.containsKey(var1)) {
         return false;
      } else {
         this.zzaJC.put(var1, var1);
         return true;
      }
   }

   public final boolean addAll(Collection var1) {
      if (var1 instanceof zza) {
         zza var3 = (zza)var1;
         int var4 = this.size();
         this.zzaJC.putAll(var3.zzaJC);
         return this.size() > var4;
      } else {
         return super.addAll(var1);
      }
   }

   public final boolean remove(Object var1) {
      if (!this.zzaJC.containsKey(var1)) {
         return false;
      } else {
         this.zzaJC.remove(var1);
         return true;
      }
   }

   public final void clear() {
      this.zzaJC.clear();
   }

   public final boolean contains(Object var1) {
      return this.zzaJC.containsKey(var1);
   }

   public final Iterator iterator() {
      return this.zzaJC.keySet().iterator();
   }

   public final int size() {
      return this.zzaJC.size();
   }
}
