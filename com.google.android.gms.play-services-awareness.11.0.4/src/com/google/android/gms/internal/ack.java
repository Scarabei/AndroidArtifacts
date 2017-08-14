package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class ack extends adj {
   private static volatile ack[] zzcqA;
   public String zzbxU = "";
   public String type = "";
   public byte[] content;

   public static ack[] zzLo() {
      if (zzcqA == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzcqA == null) {
               zzcqA = new ack[0];
            }
         }
      }

      return zzcqA;
   }

   public ack() {
      this.content = ads.zzcsI;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ack)) {
         return false;
      } else {
         ack var2 = (ack)var1;
         if (this.zzbxU == null) {
            if (var2.zzbxU != null) {
               return false;
            }
         } else if (!this.zzbxU.equals(var2.zzbxU)) {
            return false;
         }

         if (this.type == null) {
            if (var2.type != null) {
               return false;
            }
         } else if (!this.type.equals(var2.type)) {
            return false;
         }

         if (!Arrays.equals(this.content, var2.content)) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((527 + this.getClass().getName().hashCode()) * 31 + (this.zzbxU == null ? 0 : this.zzbxU.hashCode())) * 31 + (this.type == null ? 0 : this.type.hashCode())) * 31 + Arrays.hashCode(this.content)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzbxU != null && !this.zzbxU.equals("")) {
         var1.zzl(1, this.zzbxU);
      }

      if (this.type != null && !this.type.equals("")) {
         var1.zzl(2, this.type);
      }

      if (!Arrays.equals(this.content, ads.zzcsI)) {
         var1.zzb(3, this.content);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzbxU != null && !this.zzbxU.equals("")) {
         var1 += adh.zzm(1, this.zzbxU);
      }

      if (this.type != null && !this.type.equals("")) {
         var1 += adh.zzm(2, this.type);
      }

      if (!Arrays.equals(this.content, ads.zzcsI)) {
         var1 += adh.zzc(3, this.content);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      ack var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzbxU = var3.readString();
            break;
         case 18:
            var2.type = var3.readString();
            break;
         case 26:
            var2.content = var3.readBytes();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
