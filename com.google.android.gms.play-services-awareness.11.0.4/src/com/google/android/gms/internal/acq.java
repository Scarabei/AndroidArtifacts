package com.google.android.gms.internal;

import java.io.IOException;

public final class acq extends adj {
   private int zzcqq = 0;
   private String packageName = "";

   public acq() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acq)) {
         return false;
      } else {
         acq var2 = (acq)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else {
            if (this.packageName == null) {
               if (var2.packageName != null) {
                  return false;
               }
            } else if (!this.packageName.equals(var2.packageName)) {
               return false;
            }

            if (this.zzcso != null && !this.zzcso.isEmpty()) {
               return this.zzcso.equals(var2.zzcso);
            } else {
               return var2.zzcso == null || var2.zzcso.isEmpty();
            }
         }
      }
   }

   public final int hashCode() {
      return (((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.packageName != null && !this.packageName.equals("")) {
         var1.zzl(2, this.packageName);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcqq != 0) {
         var1 += adh.zzs(1, this.zzcqq);
      }

      if (this.packageName != null && !this.packageName.equals("")) {
         var1 += adh.zzm(2, this.packageName);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acq var2 = this;

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
         case 18:
            var2.packageName = var3.readString();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
