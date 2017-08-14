package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;

public abstract class zzbgl extends zzbgi implements SafeParcelable {
   public Object zzcH(String var1) {
      return null;
   }

   public boolean zzcI(String var1) {
      return false;
   }

   public final int describeContents() {
      return 0;
   }

   public int hashCode() {
      int var1 = 0;
      Iterator var2 = this.zzrL().values().iterator();

      while(var2.hasNext()) {
         zzbgj var3 = (zzbgj)var2.next();
         if (this.zza(var3)) {
            var1 = var1 * 31 + this.zzb(var3).hashCode();
         }
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!this.getClass().isInstance(var1)) {
         return false;
      } else {
         zzbgi var2 = (zzbgi)var1;
         Iterator var3 = this.zzrL().values().iterator();

         while(var3.hasNext()) {
            zzbgj var4 = (zzbgj)var3.next();
            if (this.zza(var4)) {
               if (!var2.zza(var4)) {
                  return false;
               }

               if (!this.zzb(var4).equals(var2.zzb(var4))) {
                  return false;
               }
            } else if (var2.zza(var4)) {
               return false;
            }
         }

         return true;
      }
   }
}
