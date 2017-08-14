package com.google.android.gms.internal;

import java.io.IOException;

public final class adc extends adj {
   private int type = 3;
   private long zzaTb = 0L;
   private long zzaTc = 0L;

   public adc() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof adc)) {
         return false;
      } else {
         adc var2 = (adc)var1;
         if (this.type != var2.type) {
            return false;
         } else if (this.zzaTb != var2.zzaTb) {
            return false;
         } else if (this.zzaTc != var2.zzaTc) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((527 + this.getClass().getName().hashCode()) * 31 + this.type) * 31 + (int)(this.zzaTb ^ this.zzaTb >>> 32)) * 31 + (int)(this.zzaTc ^ this.zzaTc >>> 32)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.type != 3) {
         var1.zzr(1, this.type);
      }

      if (this.zzaTb != 0L) {
         var1.zzb(2, this.zzaTb);
      }

      if (this.zzaTc != 0L) {
         var1.zzb(3, this.zzaTc);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.type != 3) {
         var1 += adh.zzs(1, this.type);
      }

      if (this.zzaTb != 0L) {
         var1 += adh.zze(2, this.zzaTb);
      }

      if (this.zzaTc != 0L) {
         var1 += adh.zze(3, this.zzaTc);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      adc var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            int var5 = var3.getPosition();
            int var6;
            switch(var6 = var3.zzLF()) {
            case 1:
            case 2:
            case 3:
               var2.type = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 16:
            var2.zzaTb = var3.zzLG();
            break;
         case 24:
            var2.zzaTc = var3.zzLG();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
