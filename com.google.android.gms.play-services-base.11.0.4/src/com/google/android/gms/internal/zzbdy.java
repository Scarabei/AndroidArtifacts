package com.google.android.gms.internal;

public final class zzbdy {
   private final Object mListener;
   private final String zzaEP;

   zzbdy(Object var1, String var2) {
      this.mListener = var1;
      this.zzaEP = var2;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzbdy)) {
         return false;
      } else {
         zzbdy var2 = (zzbdy)var1;
         return this.mListener == var2.mListener && this.zzaEP.equals(var2.zzaEP);
      }
   }

   public final int hashCode() {
      return System.identityHashCode(this.mListener) * 31 + this.zzaEP.hashCode();
   }
}
