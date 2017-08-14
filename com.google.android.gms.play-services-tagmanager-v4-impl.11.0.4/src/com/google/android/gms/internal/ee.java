package com.google.android.gms.internal;

import java.io.IOException;

public final class ee extends adj {
   public long zzbLG = 0L;
   public zzbn zzlB = null;
   public zzbq zzbLH = null;

   public ee() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ee)) {
         return false;
      } else {
         ee var2 = (ee)var1;
         if (this.zzbLG != var2.zzbLG) {
            return false;
         } else {
            if (this.zzlB == null) {
               if (var2.zzlB != null) {
                  return false;
               }
            } else if (!this.zzlB.equals(var2.zzlB)) {
               return false;
            }

            if (this.zzbLH == null) {
               if (var2.zzbLH != null) {
                  return false;
               }
            } else if (!this.zzbLH.equals(var2.zzbLH)) {
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
      return ((((527 + this.getClass().getName().hashCode()) * 31 + (int)(this.zzbLG ^ this.zzbLG >>> 32)) * 31 + (this.zzlB == null ? 0 : this.zzlB.hashCode())) * 31 + (this.zzbLH == null ? 0 : this.zzbLH.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      var1.zzb(1, this.zzbLG);
      if (this.zzlB != null) {
         var1.zza(2, this.zzlB);
      }

      if (this.zzbLH != null) {
         var1.zza(3, this.zzbLH);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn() + adh.zze(1, this.zzbLG);
      if (this.zzlB != null) {
         var1 += adh.zzb(2, this.zzlB);
      }

      if (this.zzbLH != null) {
         var1 += adh.zzb(3, this.zzbLH);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      ee var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzbLG = var3.zzLG();
            break;
         case 18:
            if (var2.zzlB == null) {
               var2.zzlB = new zzbn();
            }

            var3.zza(var2.zzlB);
            break;
         case 26:
            if (var2.zzbLH == null) {
               var2.zzbLH = new zzbq();
            }

            var3.zza(var2.zzbLH);
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
