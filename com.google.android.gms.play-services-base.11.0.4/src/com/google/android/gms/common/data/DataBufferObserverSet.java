package com.google.android.gms.common.data;

import java.util.HashSet;
import java.util.Iterator;

public final class DataBufferObserverSet implements DataBufferObserver, DataBufferObserver.Observable {
   private HashSet zzaFw = new HashSet();

   public final boolean hasObservers() {
      return !this.zzaFw.isEmpty();
   }

   public final void clear() {
      this.zzaFw.clear();
   }

   public final void addObserver(DataBufferObserver var1) {
      this.zzaFw.add(var1);
   }

   public final void removeObserver(DataBufferObserver var1) {
      this.zzaFw.remove(var1);
   }

   public final void onDataChanged() {
      Iterator var1 = this.zzaFw.iterator();

      while(var1.hasNext()) {
         ((DataBufferObserver)var1.next()).onDataChanged();
      }

   }

   public final void onDataRangeChanged(int var1, int var2) {
      Iterator var3 = this.zzaFw.iterator();

      while(var3.hasNext()) {
         ((DataBufferObserver)var3.next()).onDataRangeChanged(var1, var2);
      }

   }

   public final void onDataRangeInserted(int var1, int var2) {
      Iterator var3 = this.zzaFw.iterator();

      while(var3.hasNext()) {
         ((DataBufferObserver)var3.next()).onDataRangeInserted(var1, var2);
      }

   }

   public final void onDataRangeRemoved(int var1, int var2) {
      Iterator var3 = this.zzaFw.iterator();

      while(var3.hasNext()) {
         ((DataBufferObserver)var3.next()).onDataRangeRemoved(var1, var2);
      }

   }

   public final void onDataRangeMoved(int var1, int var2, int var3) {
      Iterator var4 = this.zzaFw.iterator();

      while(var4.hasNext()) {
         ((DataBufferObserver)var4.next()).onDataRangeMoved(var1, var2, var3);
      }

   }
}
