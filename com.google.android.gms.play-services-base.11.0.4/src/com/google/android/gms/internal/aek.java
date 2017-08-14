package com.google.android.gms.internal;

import java.io.IOException;

public final class aek extends adj implements Cloneable {
   private static volatile aek[] zzcuj;
   private String key = "";
   private String value = "";

   public static aek[] zzMe() {
      if (zzcuj == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzcuj == null) {
               zzcuj = new aek[0];
            }
         }
      }

      return zzcuj;
   }

   public aek() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   private aek zzMf() {
      try {
         aek var1 = (aek)super.zzLN();
         return var1;
      } catch (CloneNotSupportedException var3) {
         throw new AssertionError(var3);
      }
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof aek)) {
         return false;
      } else {
         aek var2 = (aek)var1;
         if (this.key == null) {
            if (var2.key != null) {
               return false;
            }
         } else if (!this.key.equals(var2.key)) {
            return false;
         }

         if (this.value == null) {
            if (var2.value != null) {
               return false;
            }
         } else if (!this.value.equals(var2.value)) {
            return false;
         }

         if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((527 + this.getClass().getName().hashCode()) * 31 + (this.key == null ? 0 : this.key.hashCode())) * 31 + (this.value == null ? 0 : this.value.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.key != null && !this.key.equals("")) {
         var1.zzl(1, this.key);
      }

      if (this.value != null && !this.value.equals("")) {
         var1.zzl(2, this.value);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.key != null && !this.key.equals("")) {
         var1 += adh.zzm(1, this.key);
      }

      if (this.value != null && !this.value.equals("")) {
         var1 += adh.zzm(2, this.value);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adj zzLN() throws CloneNotSupportedException {
      return (aek)this.clone();
   }

   // $FF: synthetic method
   public final adp zzLO() throws CloneNotSupportedException {
      return (aek)this.clone();
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aek var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.key = var3.readString();
            break;
         case 18:
            var2.value = var3.readString();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }

   // $FF: synthetic method
   public final Object clone() throws CloneNotSupportedException {
      return this.zzMf();
   }
}
