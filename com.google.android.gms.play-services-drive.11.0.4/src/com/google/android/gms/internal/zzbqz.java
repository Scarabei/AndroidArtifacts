package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbqz extends adj {
   public int versionCode = 1;
   public long sequenceNumber = -1L;
   public long zzaPw = -1L;
   public long zzaPx = -1L;

   public zzbqz() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbqz)) {
         return false;
      } else {
         zzbqz var2 = (zzbqz)var1;
         if (this.versionCode != var2.versionCode) {
            return false;
         } else if (this.sequenceNumber != var2.sequenceNumber) {
            return false;
         } else if (this.zzaPw != var2.zzaPw) {
            return false;
         } else if (this.zzaPx != var2.zzaPx) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((((527 + this.getClass().getName().hashCode()) * 31 + this.versionCode) * 31 + (int)(this.sequenceNumber ^ this.sequenceNumber >>> 32)) * 31 + (int)(this.zzaPw ^ this.zzaPw >>> 32)) * 31 + (int)(this.zzaPx ^ this.zzaPx >>> 32)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      var1.zzr(1, this.versionCode);
      var1.zzd(2, this.sequenceNumber);
      var1.zzd(3, this.zzaPw);
      var1.zzd(4, this.zzaPx);
      super.zza(var1);
   }

   protected final int zzn() {
      return super.zzn() + adh.zzs(1, this.versionCode) + adh.zzf(2, this.sequenceNumber) + adh.zzf(3, this.zzaPw) + adh.zzf(4, this.zzaPx);
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbqz var2 = this;

      while(true) {
         int var4;
         long var5;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.versionCode = var3.zzLF();
            break;
         case 16:
            var2.sequenceNumber = (var5 = var3.zzLG()) >>> 1 ^ -(var5 & 1L);
            break;
         case 24:
            var2.zzaPw = (var5 = var3.zzLG()) >>> 1 ^ -(var5 & 1L);
            break;
         case 32:
            var2.zzaPx = (var5 = var3.zzLG()) >>> 1 ^ -(var5 & 1L);
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
