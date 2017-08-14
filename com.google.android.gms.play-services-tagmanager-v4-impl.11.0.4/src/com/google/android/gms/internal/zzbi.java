package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbi extends adj {
   private int level = 1;
   private int zzkx = 0;
   private int zzky = 0;

   public zzbi() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbi)) {
         return false;
      } else {
         zzbi var2 = (zzbi)var1;
         if (this.level != var2.level) {
            return false;
         } else if (this.zzkx != var2.zzkx) {
            return false;
         } else if (this.zzky != var2.zzky) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((527 + this.getClass().getName().hashCode()) * 31 + this.level) * 31 + this.zzkx) * 31 + this.zzky) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.level != 1) {
         var1.zzr(1, this.level);
      }

      if (this.zzkx != 0) {
         var1.zzr(2, this.zzkx);
      }

      if (this.zzky != 0) {
         var1.zzr(3, this.zzky);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.level != 1) {
         var1 += adh.zzs(1, this.level);
      }

      if (this.zzkx != 0) {
         var1 += adh.zzs(2, this.zzkx);
      }

      if (this.zzky != 0) {
         var1 += adh.zzs(3, this.zzky);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbi var2 = this;

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
               var2.level = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 16:
            var2.zzkx = var3.zzLF();
            break;
         case 24:
            var2.zzky = var3.zzLF();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
