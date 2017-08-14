package com.google.android.gms.internal;

import java.io.IOException;

public final class aeh extends adj implements Cloneable {
   private int zzbpb = 0;
   private String zzctL = "";
   private String version = "";

   public aeh() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   private aeh zzMb() {
      try {
         aeh var1 = (aeh)super.zzLN();
         return var1;
      } catch (CloneNotSupportedException var3) {
         throw new AssertionError(var3);
      }
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof aeh)) {
         return false;
      } else {
         aeh var2 = (aeh)var1;
         if (this.zzbpb != var2.zzbpb) {
            return false;
         } else {
            if (this.zzctL == null) {
               if (var2.zzctL != null) {
                  return false;
               }
            } else if (!this.zzctL.equals(var2.zzctL)) {
               return false;
            }

            if (this.version == null) {
               if (var2.version != null) {
                  return false;
               }
            } else if (!this.version.equals(var2.version)) {
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
      return ((((527 + this.getClass().getName().hashCode()) * 31 + this.zzbpb) * 31 + (this.zzctL == null ? 0 : this.zzctL.hashCode())) * 31 + (this.version == null ? 0 : this.version.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzbpb != 0) {
         var1.zzr(1, this.zzbpb);
      }

      if (this.zzctL != null && !this.zzctL.equals("")) {
         var1.zzl(2, this.zzctL);
      }

      if (this.version != null && !this.version.equals("")) {
         var1.zzl(3, this.version);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzbpb != 0) {
         var1 += adh.zzs(1, this.zzbpb);
      }

      if (this.zzctL != null && !this.zzctL.equals("")) {
         var1 += adh.zzm(2, this.zzctL);
      }

      if (this.version != null && !this.version.equals("")) {
         var1 += adh.zzm(3, this.version);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adj zzLN() throws CloneNotSupportedException {
      return (aeh)this.clone();
   }

   // $FF: synthetic method
   public final adp zzLO() throws CloneNotSupportedException {
      return (aeh)this.clone();
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aeh var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzbpb = var3.zzLC();
            break;
         case 18:
            var2.zzctL = var3.readString();
            break;
         case 26:
            var2.version = var3.readString();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }

   // $FF: synthetic method
   public final Object clone() throws CloneNotSupportedException {
      return this.zzMb();
   }
}
