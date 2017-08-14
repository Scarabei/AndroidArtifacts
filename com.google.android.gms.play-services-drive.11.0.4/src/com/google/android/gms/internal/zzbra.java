package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbra extends adj {
   public int versionCode = 1;
   public String zzaPy = "";
   public long zzaPz = -1L;
   public long zzaPw = -1L;
   public int zzaPA = -1;

   public zzbra() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbra)) {
         return false;
      } else {
         zzbra var2 = (zzbra)var1;
         if (this.versionCode != var2.versionCode) {
            return false;
         } else {
            if (this.zzaPy == null) {
               if (var2.zzaPy != null) {
                  return false;
               }
            } else if (!this.zzaPy.equals(var2.zzaPy)) {
               return false;
            }

            if (this.zzaPz != var2.zzaPz) {
               return false;
            } else if (this.zzaPw != var2.zzaPw) {
               return false;
            } else if (this.zzaPA != var2.zzaPA) {
               return false;
            } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
               return this.zzcso.equals(var2.zzcso);
            } else {
               return var2.zzcso == null || var2.zzcso.isEmpty();
            }
         }
      }
   }

   public final int hashCode() {
      return ((((((527 + this.getClass().getName().hashCode()) * 31 + this.versionCode) * 31 + (this.zzaPy == null ? 0 : this.zzaPy.hashCode())) * 31 + (int)(this.zzaPz ^ this.zzaPz >>> 32)) * 31 + (int)(this.zzaPw ^ this.zzaPw >>> 32)) * 31 + this.zzaPA) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      var1.zzr(1, this.versionCode);
      var1.zzl(2, this.zzaPy);
      var1.zzd(3, this.zzaPz);
      var1.zzd(4, this.zzaPw);
      if (this.zzaPA != -1) {
         var1.zzr(5, this.zzaPA);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn() + adh.zzs(1, this.versionCode) + adh.zzm(2, this.zzaPy) + adh.zzf(3, this.zzaPz) + adh.zzf(4, this.zzaPw);
      if (this.zzaPA != -1) {
         var1 += adh.zzs(5, this.zzaPA);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbra var2 = this;

      while(true) {
         int var4;
         long var5;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.versionCode = var3.zzLF();
            break;
         case 18:
            var2.zzaPy = var3.readString();
            break;
         case 24:
            var2.zzaPz = (var5 = var3.zzLG()) >>> 1 ^ -(var5 & 1L);
            break;
         case 32:
            var2.zzaPw = (var5 = var3.zzLG()) >>> 1 ^ -(var5 & 1L);
            break;
         case 40:
            var2.zzaPA = var3.zzLF();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
