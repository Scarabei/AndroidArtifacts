package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbk extends adj {
   private static volatile zzbk[] zzkE;
   public String key = "";
   public long zzkF = 0L;
   public long zzkG = 2147483647L;
   public boolean zzkH = false;
   public long zzkI = 0L;

   public static zzbk[] zzq() {
      if (zzkE == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzkE == null) {
               zzkE = new zzbk[0];
            }
         }
      }

      return zzkE;
   }

   public zzbk() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbk)) {
         return false;
      } else {
         zzbk var2 = (zzbk)var1;
         if (this.key == null) {
            if (var2.key != null) {
               return false;
            }
         } else if (!this.key.equals(var2.key)) {
            return false;
         }

         if (this.zzkF != var2.zzkF) {
            return false;
         } else if (this.zzkG != var2.zzkG) {
            return false;
         } else if (this.zzkH != var2.zzkH) {
            return false;
         } else if (this.zzkI != var2.zzkI) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((((527 + this.getClass().getName().hashCode()) * 31 + (this.key == null ? 0 : this.key.hashCode())) * 31 + (int)(this.zzkF ^ this.zzkF >>> 32)) * 31 + (int)(this.zzkG ^ this.zzkG >>> 32)) * 31 + (this.zzkH ? 1231 : 1237)) * 31 + (int)(this.zzkI ^ this.zzkI >>> 32)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.key != null && !this.key.equals("")) {
         var1.zzl(1, this.key);
      }

      if (this.zzkF != 0L) {
         var1.zzb(2, this.zzkF);
      }

      if (this.zzkG != 2147483647L) {
         var1.zzb(3, this.zzkG);
      }

      if (this.zzkH) {
         var1.zzk(4, this.zzkH);
      }

      if (this.zzkI != 0L) {
         var1.zzb(5, this.zzkI);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.key != null && !this.key.equals("")) {
         var1 += adh.zzm(1, this.key);
      }

      if (this.zzkF != 0L) {
         var1 += adh.zze(2, this.zzkF);
      }

      if (this.zzkG != 2147483647L) {
         var1 += adh.zze(3, this.zzkG);
      }

      if (this.zzkH) {
         var1 += adh.zzct(4) + 1;
      }

      if (this.zzkI != 0L) {
         var1 += adh.zze(5, this.zzkI);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbk var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.key = var3.readString();
            break;
         case 16:
            var2.zzkF = var3.zzLG();
            break;
         case 24:
            var2.zzkG = var3.zzLG();
            break;
         case 32:
            var2.zzkH = var3.zzLD();
            break;
         case 40:
            var2.zzkI = var3.zzLG();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
