package com.google.android.gms.internal;

import java.io.IOException;

public abstract class adj extends adp {
   protected adl zzcso;

   protected int zzn() {
      int var1 = 0;
      if (this.zzcso != null) {
         for(int var2 = 0; var2 < this.zzcso.size(); ++var2) {
            adm var3 = this.zzcso.zzcy(var2);
            var1 += var3.zzn();
         }
      }

      return var1;
   }

   public void zza(adh var1) throws IOException {
      if (this.zzcso != null) {
         for(int var2 = 0; var2 < this.zzcso.size(); ++var2) {
            this.zzcso.zzcy(var2).zza(var1);
         }

      }
   }

   public final Object zza(adk var1) {
      if (this.zzcso == null) {
         return null;
      } else {
         adm var2;
         return (var2 = this.zzcso.zzcx(var1.tag >>> 3)) == null ? null : var2.zzb(var1);
      }
   }

   protected final boolean zza(adg var1, int var2) throws IOException {
      int var3 = var1.getPosition();
      if (!var1.zzcm(var2)) {
         return false;
      } else {
         int var4 = var2 >>> 3;
         int var5 = var1.getPosition();
         byte[] var6 = var1.zzp(var3, var5 - var3);
         adr var9 = new adr(var2, var6);
         adm var10 = null;
         if (this.zzcso == null) {
            this.zzcso = new adl();
         } else {
            var10 = this.zzcso.zzcx(var4);
         }

         if (var10 == null) {
            var10 = new adm();
            this.zzcso.zza(var4, var10);
         }

         var10.zza(var9);
         return true;
      }
   }

   public adj zzLN() throws CloneNotSupportedException {
      adj var1 = (adj)super.zzLO();
      adn.zza(this, var1);
      return var1;
   }

   // $FF: synthetic method
   public adp zzLO() throws CloneNotSupportedException {
      return (adj)this.clone();
   }

   // $FF: synthetic method
   public Object clone() throws CloneNotSupportedException {
      return this.zzLN();
   }
}
