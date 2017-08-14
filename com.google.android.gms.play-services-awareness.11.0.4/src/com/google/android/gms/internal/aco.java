package com.google.android.gms.internal;

import java.io.IOException;

public final class aco extends adj {
   private long zzcrg = 0L;
   private long zzcrh = 0L;
   public int version = 0;
   private String zzcri = "";
   private String moduleId = "";

   public aco() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof aco)) {
         return false;
      } else {
         aco var2 = (aco)var1;
         if (this.zzcrg != var2.zzcrg) {
            return false;
         } else if (this.zzcrh != var2.zzcrh) {
            return false;
         } else if (this.version != var2.version) {
            return false;
         } else {
            if (this.zzcri == null) {
               if (var2.zzcri != null) {
                  return false;
               }
            } else if (!this.zzcri.equals(var2.zzcri)) {
               return false;
            }

            if (this.moduleId == null) {
               if (var2.moduleId != null) {
                  return false;
               }
            } else if (!this.moduleId.equals(var2.moduleId)) {
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
      return ((((((527 + this.getClass().getName().hashCode()) * 31 + (int)(this.zzcrg ^ this.zzcrg >>> 32)) * 31 + (int)(this.zzcrh ^ this.zzcrh >>> 32)) * 31 + this.version) * 31 + (this.zzcri == null ? 0 : this.zzcri.hashCode())) * 31 + (this.moduleId == null ? 0 : this.moduleId.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcrg != 0L) {
         var1.zzb(1, this.zzcrg);
      }

      if (this.zzcrh != 0L) {
         var1.zzb(2, this.zzcrh);
      }

      if (this.version != 0) {
         var1.zzr(3, this.version);
      }

      if (this.zzcri != null && !this.zzcri.equals("")) {
         var1.zzl(4, this.zzcri);
      }

      if (this.moduleId != null && !this.moduleId.equals("")) {
         var1.zzl(5, this.moduleId);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcrg != 0L) {
         var1 += adh.zze(1, this.zzcrg);
      }

      if (this.zzcrh != 0L) {
         var1 += adh.zze(2, this.zzcrh);
      }

      if (this.version != 0) {
         var1 += adh.zzs(3, this.version);
      }

      if (this.zzcri != null && !this.zzcri.equals("")) {
         var1 += adh.zzm(4, this.zzcri);
      }

      if (this.moduleId != null && !this.moduleId.equals("")) {
         var1 += adh.zzm(5, this.moduleId);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aco var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzcrg = var3.zzLG();
            break;
         case 16:
            var2.zzcrh = var3.zzLG();
            break;
         case 24:
            var2.version = var3.zzLF();
            break;
         case 34:
            var2.zzcri = var3.readString();
            break;
         case 42:
            var2.moduleId = var3.readString();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
