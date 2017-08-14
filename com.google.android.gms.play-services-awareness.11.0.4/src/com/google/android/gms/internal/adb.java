package com.google.android.gms.internal;

import java.io.IOException;

public final class adb extends adj {
   public int zzcqq = 0;
   public long zzcrD = 0L;
   public long zzcrE = 0L;

   public adb() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof adb)) {
         return false;
      } else {
         adb var2 = (adb)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else if (this.zzcrD != var2.zzcrD) {
            return false;
         } else if (this.zzcrE != var2.zzcrE) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + (int)(this.zzcrD ^ this.zzcrD >>> 32)) * 31 + (int)(this.zzcrE ^ this.zzcrE >>> 32)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.zzcrD != 0L) {
         var1.zzb(3, this.zzcrD);
      }

      if (this.zzcrE != 0L) {
         var1.zzb(4, this.zzcrE);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcqq != 0) {
         var1 += adh.zzs(1, this.zzcqq);
      }

      if (this.zzcrD != 0L) {
         var1 += adh.zze(3, this.zzcrD);
      }

      if (this.zzcrE != 0L) {
         var1 += adh.zze(4, this.zzcrE);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      adb var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            int var5 = var3.getPosition();
            int var6;
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
               var2.zzcqq = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 24:
            var2.zzcrD = var3.zzLG();
            break;
         case 32:
            var2.zzcrE = var3.zzLG();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
