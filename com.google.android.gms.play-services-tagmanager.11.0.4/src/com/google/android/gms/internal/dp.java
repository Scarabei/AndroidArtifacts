package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class dp {
   protected Map zzbLl;

   public final void zzc(String var1, dp var2) {
      if (this.zzbLl == null) {
         this.zzbLl = new HashMap();
      }

      this.zzbLl.put(var1, var2);
   }

   public final boolean zzfY(String var1) {
      return this.zzbLl != null && this.zzbLl.containsKey(var1);
   }

   public dp zzfZ(String var1) {
      return (dp)(this.zzbLl != null ? (dp)this.zzbLl.get(var1) : dv.zzbLu);
   }

   public Iterator zzDk() {
      return new dr((dq)null);
   }

   public abstract Object zzDl();

   public boolean zzga(String var1) {
      return false;
   }

   public zzcxo zzgb(String var1) {
      throw new IllegalStateException((new StringBuilder(56 + String.valueOf(var1).length())).append("Attempting to access Native Method ").append(var1).append(" on unsupported type.").toString());
   }

   public abstract String toString();

   protected final Iterator zzDm() {
      if (this.zzbLl == null) {
         return new dr((dq)null);
      } else {
         Iterator var1 = this.zzbLl.keySet().iterator();
         return new dq(this, var1);
      }
   }
}
