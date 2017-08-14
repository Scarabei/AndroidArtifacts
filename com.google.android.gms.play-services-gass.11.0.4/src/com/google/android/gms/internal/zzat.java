package com.google.android.gms.internal;

import java.io.IOException;

public final class zzat extends adj {
   public String zzaH = null;
   public Long zzaI = null;
   private String stackTrace = null;
   private String zzaJ = null;
   private String zzaK = null;
   private Long zzaL = null;
   private Long zzaM = null;
   private String zzaN = null;
   private Long zzaO = null;
   private String zzaP = null;

   public zzat() {
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzaH != null) {
         var1.zzl(1, this.zzaH);
      }

      if (this.zzaI != null) {
         var1.zzb(2, this.zzaI.longValue());
      }

      if (this.stackTrace != null) {
         var1.zzl(3, this.stackTrace);
      }

      if (this.zzaJ != null) {
         var1.zzl(4, this.zzaJ);
      }

      if (this.zzaK != null) {
         var1.zzl(5, this.zzaK);
      }

      if (this.zzaL != null) {
         var1.zzb(6, this.zzaL.longValue());
      }

      if (this.zzaM != null) {
         var1.zzb(7, this.zzaM.longValue());
      }

      if (this.zzaN != null) {
         var1.zzl(8, this.zzaN);
      }

      if (this.zzaO != null) {
         var1.zzb(9, this.zzaO.longValue());
      }

      if (this.zzaP != null) {
         var1.zzl(10, this.zzaP);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzaH != null) {
         var1 += adh.zzm(1, this.zzaH);
      }

      if (this.zzaI != null) {
         var1 += adh.zze(2, this.zzaI.longValue());
      }

      if (this.stackTrace != null) {
         var1 += adh.zzm(3, this.stackTrace);
      }

      if (this.zzaJ != null) {
         var1 += adh.zzm(4, this.zzaJ);
      }

      if (this.zzaK != null) {
         var1 += adh.zzm(5, this.zzaK);
      }

      if (this.zzaL != null) {
         var1 += adh.zze(6, this.zzaL.longValue());
      }

      if (this.zzaM != null) {
         var1 += adh.zze(7, this.zzaM.longValue());
      }

      if (this.zzaN != null) {
         var1 += adh.zzm(8, this.zzaN);
      }

      if (this.zzaO != null) {
         var1 += adh.zze(9, this.zzaO.longValue());
      }

      if (this.zzaP != null) {
         var1 += adh.zzm(10, this.zzaP);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzat var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzaH = var3.readString();
            break;
         case 16:
            var2.zzaI = var3.zzLG();
            break;
         case 26:
            var2.stackTrace = var3.readString();
            break;
         case 34:
            var2.zzaJ = var3.readString();
            break;
         case 42:
            var2.zzaK = var3.readString();
            break;
         case 48:
            var2.zzaL = var3.zzLG();
            break;
         case 56:
            var2.zzaM = var3.zzLG();
            break;
         case 66:
            var2.zzaN = var3.readString();
            break;
         case 72:
            var2.zzaO = var3.zzLG();
            break;
         case 82:
            var2.zzaP = var3.readString();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
