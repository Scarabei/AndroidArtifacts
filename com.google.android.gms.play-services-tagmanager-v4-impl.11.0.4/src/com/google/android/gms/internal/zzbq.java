package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbq extends adj {
   public zzbp[] zzlA = zzbp.zzt();
   public zzbn zzlB = null;
   public String zzlC = "";

   public zzbq() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbq)) {
         return false;
      } else {
         zzbq var2 = (zzbq)var1;
         if (!adn.equals(this.zzlA, var2.zzlA)) {
            return false;
         } else {
            if (this.zzlB == null) {
               if (var2.zzlB != null) {
                  return false;
               }
            } else if (!this.zzlB.equals(var2.zzlB)) {
               return false;
            }

            if (this.zzlC == null) {
               if (var2.zzlC != null) {
                  return false;
               }
            } else if (!this.zzlC.equals(var2.zzlC)) {
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
      return ((((527 + this.getClass().getName().hashCode()) * 31 + adn.hashCode(this.zzlA)) * 31 + (this.zzlB == null ? 0 : this.zzlB.hashCode())) * 31 + (this.zzlC == null ? 0 : this.zzlC.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzlA != null && this.zzlA.length > 0) {
         for(int var2 = 0; var2 < this.zzlA.length; ++var2) {
            zzbp var3;
            if ((var3 = this.zzlA[var2]) != null) {
               var1.zza(1, var3);
            }
         }
      }

      if (this.zzlB != null) {
         var1.zza(2, this.zzlB);
      }

      if (this.zzlC != null && !this.zzlC.equals("")) {
         var1.zzl(3, this.zzlC);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzlA != null && this.zzlA.length > 0) {
         for(int var2 = 0; var2 < this.zzlA.length; ++var2) {
            zzbp var3;
            if ((var3 = this.zzlA[var2]) != null) {
               var1 += adh.zzb(1, var3);
            }
         }
      }

      if (this.zzlB != null) {
         var1 += adh.zzb(2, this.zzlB);
      }

      if (this.zzlC != null && !this.zzlC.equals("")) {
         var1 += adh.zzm(3, this.zzlC);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbq var2 = this;

      while(true) {
         int var4;
         int var6;
         zzbp[] var7;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            int var5 = ads.zzb(var3, 10);
            var7 = new zzbp[(var6 = var2.zzlA == null ? 0 : var2.zzlA.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlA, 0, var7, 0, var6);
            }
            break;
         case 18:
            if (var2.zzlB == null) {
               var2.zzlB = new zzbn();
            }

            var3.zza(var2.zzlB);
            continue;
         case 26:
            var2.zzlC = var3.readString();
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var7.length - 1) {
            var7[var6] = new zzbp();
            var3.zza(var7[var6]);
            var3.zzLA();
            ++var6;
         }

         var7[var6] = new zzbp();
         var3.zza(var7[var6]);
         var2.zzlA = var7;
      }
   }
}
