package com.google.android.gms.internal;

import java.io.IOException;

public final class ael extends adj implements Cloneable {
   private int zzcuk = -1;
   private int zzcul = 0;

   public ael() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   private ael zzMg() {
      try {
         ael var1 = (ael)super.zzLN();
         return var1;
      } catch (CloneNotSupportedException var3) {
         throw new AssertionError(var3);
      }
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ael)) {
         return false;
      } else {
         ael var2 = (ael)var1;
         if (this.zzcuk != var2.zzcuk) {
            return false;
         } else if (this.zzcul != var2.zzcul) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((527 + this.getClass().getName().hashCode()) * 31 + this.zzcuk) * 31 + this.zzcul) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcuk != -1) {
         var1.zzr(1, this.zzcuk);
      }

      if (this.zzcul != 0) {
         var1.zzr(2, this.zzcul);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcuk != -1) {
         var1 += adh.zzs(1, this.zzcuk);
      }

      if (this.zzcul != 0) {
         var1 += adh.zzs(2, this.zzcul);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adj zzLN() throws CloneNotSupportedException {
      return (ael)this.clone();
   }

   // $FF: synthetic method
   public final adp zzLO() throws CloneNotSupportedException {
      return (ael)this.clone();
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      ael var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLC()) {
            case -1:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
               var2.zzcuk = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 16:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLC()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 100:
               var2.zzcul = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }

   // $FF: synthetic method
   public final Object clone() throws CloneNotSupportedException {
      return this.zzMg();
   }
}
