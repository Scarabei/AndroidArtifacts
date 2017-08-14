package com.google.android.gms.internal;

import java.io.IOException;

public final class add extends adj {
   public int zzcqq = 0;
   public String zzcrF = "";
   public long zzaTb = 0L;
   public long zzcrG = 0L;
   private acp zzcrH = null;
   public boolean zzcrI = false;

   public add() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof add)) {
         return false;
      } else {
         add var2 = (add)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else {
            if (this.zzcrF == null) {
               if (var2.zzcrF != null) {
                  return false;
               }
            } else if (!this.zzcrF.equals(var2.zzcrF)) {
               return false;
            }

            if (this.zzaTb != var2.zzaTb) {
               return false;
            } else if (this.zzcrG != var2.zzcrG) {
               return false;
            } else {
               if (this.zzcrH == null) {
                  if (var2.zzcrH != null) {
                     return false;
                  }
               } else if (!this.zzcrH.equals(var2.zzcrH)) {
                  return false;
               }

               if (this.zzcrI != var2.zzcrI) {
                  return false;
               } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
                  return this.zzcso.equals(var2.zzcso);
               } else {
                  return var2.zzcso == null || var2.zzcso.isEmpty();
               }
            }
         }
      }
   }

   public final int hashCode() {
      return (((((((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + (this.zzcrF == null ? 0 : this.zzcrF.hashCode())) * 31 + (int)(this.zzaTb ^ this.zzaTb >>> 32)) * 31 + (int)(this.zzcrG ^ this.zzcrG >>> 32)) * 31 + (this.zzcrH == null ? 0 : this.zzcrH.hashCode())) * 31 + (this.zzcrI ? 1231 : 1237)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.zzcrF != null && !this.zzcrF.equals("")) {
         var1.zzl(2, this.zzcrF);
      }

      if (this.zzaTb != 0L) {
         var1.zzb(3, this.zzaTb);
      }

      if (this.zzcrG != 0L) {
         var1.zzb(4, this.zzcrG);
      }

      if (this.zzcrH != null) {
         var1.zza(5, this.zzcrH);
      }

      if (this.zzcrI) {
         var1.zzk(6, this.zzcrI);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcqq != 0) {
         var1 += adh.zzs(1, this.zzcqq);
      }

      if (this.zzcrF != null && !this.zzcrF.equals("")) {
         var1 += adh.zzm(2, this.zzcrF);
      }

      if (this.zzaTb != 0L) {
         var1 += adh.zze(3, this.zzaTb);
      }

      if (this.zzcrG != 0L) {
         var1 += adh.zze(4, this.zzcrG);
      }

      if (this.zzcrH != null) {
         var1 += adh.zzb(5, this.zzcrH);
      }

      if (this.zzcrI) {
         var1 += adh.zzct(6) + 1;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      add var2 = this;

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
               var2.zzcqq = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 18:
            var2.zzcrF = var3.readString();
            break;
         case 24:
            var2.zzaTb = var3.zzLG();
            break;
         case 32:
            var2.zzcrG = var3.zzLG();
            break;
         case 42:
            if (var2.zzcrH == null) {
               var2.zzcrH = new acp();
            }

            var3.zza(var2.zzcrH);
            break;
         case 48:
            var2.zzcrI = var3.zzLD();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
