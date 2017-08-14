package com.google.android.gms.internal;

import java.io.IOException;

public final class acp extends adj {
   private int year = 0;
   private int month = 0;
   private int day = 0;
   private int hour = 0;
   private int minutes = 0;
   private int seconds = 0;

   public acp() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acp)) {
         return false;
      } else {
         acp var2 = (acp)var1;
         if (this.year != var2.year) {
            return false;
         } else if (this.month != var2.month) {
            return false;
         } else if (this.day != var2.day) {
            return false;
         } else if (this.hour != var2.hour) {
            return false;
         } else if (this.minutes != var2.minutes) {
            return false;
         } else if (this.seconds != var2.seconds) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((((((527 + this.getClass().getName().hashCode()) * 31 + this.year) * 31 + this.month) * 31 + this.day) * 31 + this.hour) * 31 + this.minutes) * 31 + this.seconds) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.year != 0) {
         var1.zzr(1, this.year);
      }

      if (this.month != 0) {
         var1.zzr(2, this.month);
      }

      if (this.day != 0) {
         var1.zzr(3, this.day);
      }

      if (this.hour != 0) {
         var1.zzr(4, this.hour);
      }

      if (this.minutes != 0) {
         var1.zzr(5, this.minutes);
      }

      if (this.seconds != 0) {
         var1.zzr(6, this.seconds);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.year != 0) {
         var1 += adh.zzs(1, this.year);
      }

      if (this.month != 0) {
         var1 += adh.zzs(2, this.month);
      }

      if (this.day != 0) {
         var1 += adh.zzs(3, this.day);
      }

      if (this.hour != 0) {
         var1 += adh.zzs(4, this.hour);
      }

      if (this.minutes != 0) {
         var1 += adh.zzs(5, this.minutes);
      }

      if (this.seconds != 0) {
         var1 += adh.zzs(6, this.seconds);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acp var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.year = var3.zzLF();
            break;
         case 16:
            var2.month = var3.zzLF();
            break;
         case 24:
            var2.day = var3.zzLF();
            break;
         case 32:
            var2.hour = var3.zzLF();
            break;
         case 40:
            var2.minutes = var3.zzLF();
            break;
         case 48:
            var2.seconds = var3.zzLF();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
