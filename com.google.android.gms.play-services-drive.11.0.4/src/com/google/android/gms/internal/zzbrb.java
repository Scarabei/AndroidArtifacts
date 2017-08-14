package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbrb extends adj {
   public long zzaPz = -1L;
   public long zzaPw = -1L;

   public zzbrb() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbrb)) {
         return false;
      } else {
         zzbrb var2 = (zzbrb)var1;
         if (this.zzaPz != var2.zzaPz) {
            return false;
         } else if (this.zzaPw != var2.zzaPw) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((527 + this.getClass().getName().hashCode()) * 31 + (int)(this.zzaPz ^ this.zzaPz >>> 32)) * 31 + (int)(this.zzaPw ^ this.zzaPw >>> 32)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      var1.zzd(1, this.zzaPz);
      var1.zzd(2, this.zzaPw);
      super.zza(var1);
   }

   protected final int zzn() {
      return super.zzn() + adh.zzf(1, this.zzaPz) + adh.zzf(2, this.zzaPw);
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbrb var2 = this;

      while(true) {
         int var4;
         long var5;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzaPz = (var5 = var3.zzLG()) >>> 1 ^ -(var5 & 1L);
            break;
         case 16:
            var2.zzaPw = (var5 = var3.zzLG()) >>> 1 ^ -(var5 & 1L);
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
