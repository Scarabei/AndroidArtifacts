package com.google.android.gms.internal;

import java.io.IOException;

public interface zzbh {
   public static final class zza extends adj {
      public static final adk zzlo = adk.zza(11, zzbh.zza.class, 810L);
      private static final zzbh.zza[] zzlp = new zzbh.zza[0];
      public int[] zzlq;
      public int[] zzlr;
      public int[] zzls;
      private int zzlt;
      public int[] zzlu;
      public int zzlv;
      private int zzlw;

      public zza() {
         this.zzlq = ads.zzcsC;
         this.zzlr = ads.zzcsC;
         this.zzls = ads.zzcsC;
         this.zzlt = 0;
         this.zzlu = ads.zzcsC;
         this.zzlv = 0;
         this.zzlw = 0;
         this.zzcso = null;
         this.zzcsx = -1;
      }

      public final boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof zzbh.zza)) {
            return false;
         } else {
            zzbh.zza var2 = (zzbh.zza)var1;
            if (!adn.equals(this.zzlq, var2.zzlq)) {
               return false;
            } else if (!adn.equals(this.zzlr, var2.zzlr)) {
               return false;
            } else if (!adn.equals(this.zzls, var2.zzls)) {
               return false;
            } else if (this.zzlt != var2.zzlt) {
               return false;
            } else if (!adn.equals(this.zzlu, var2.zzlu)) {
               return false;
            } else if (this.zzlv != var2.zzlv) {
               return false;
            } else if (this.zzlw != var2.zzlw) {
               return false;
            } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
               return this.zzcso.equals(var2.zzcso);
            } else {
               return var2.zzcso == null || var2.zzcso.isEmpty();
            }
         }
      }

      public final int hashCode() {
         return ((((((((527 + this.getClass().getName().hashCode()) * 31 + adn.hashCode(this.zzlq)) * 31 + adn.hashCode(this.zzlr)) * 31 + adn.hashCode(this.zzls)) * 31 + this.zzlt) * 31 + adn.hashCode(this.zzlu)) * 31 + this.zzlv) * 31 + this.zzlw) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
      }

      public final void zza(adh var1) throws IOException {
         int var2;
         if (this.zzlq != null && this.zzlq.length > 0) {
            for(var2 = 0; var2 < this.zzlq.length; ++var2) {
               var1.zzr(1, this.zzlq[var2]);
            }
         }

         if (this.zzlr != null && this.zzlr.length > 0) {
            for(var2 = 0; var2 < this.zzlr.length; ++var2) {
               var1.zzr(2, this.zzlr[var2]);
            }
         }

         if (this.zzls != null && this.zzls.length > 0) {
            for(var2 = 0; var2 < this.zzls.length; ++var2) {
               var1.zzr(3, this.zzls[var2]);
            }
         }

         if (this.zzlt != 0) {
            var1.zzr(4, this.zzlt);
         }

         if (this.zzlu != null && this.zzlu.length > 0) {
            for(var2 = 0; var2 < this.zzlu.length; ++var2) {
               var1.zzr(5, this.zzlu[var2]);
            }
         }

         if (this.zzlv != 0) {
            var1.zzr(6, this.zzlv);
         }

         if (this.zzlw != 0) {
            var1.zzr(7, this.zzlw);
         }

         super.zza(var1);
      }

      protected final int zzn() {
         int var1 = super.zzn();
         int var2;
         int var3;
         int var4;
         if (this.zzlq != null && this.zzlq.length > 0) {
            var2 = 0;

            for(var3 = 0; var3 < this.zzlq.length; ++var3) {
               var4 = this.zzlq[var3];
               var2 += adh.zzcr(var4);
            }

            var1 = var1 + var2 + 1 * this.zzlq.length;
         }

         if (this.zzlr != null && this.zzlr.length > 0) {
            var2 = 0;

            for(var3 = 0; var3 < this.zzlr.length; ++var3) {
               var4 = this.zzlr[var3];
               var2 += adh.zzcr(var4);
            }

            var1 = var1 + var2 + 1 * this.zzlr.length;
         }

         if (this.zzls != null && this.zzls.length > 0) {
            var2 = 0;

            for(var3 = 0; var3 < this.zzls.length; ++var3) {
               var4 = this.zzls[var3];
               var2 += adh.zzcr(var4);
            }

            var1 = var1 + var2 + 1 * this.zzls.length;
         }

         if (this.zzlt != 0) {
            var1 += adh.zzs(4, this.zzlt);
         }

         if (this.zzlu != null && this.zzlu.length > 0) {
            var2 = 0;

            for(var3 = 0; var3 < this.zzlu.length; ++var3) {
               var4 = this.zzlu[var3];
               var2 += adh.zzcr(var4);
            }

            var1 = var1 + var2 + 1 * this.zzlu.length;
         }

         if (this.zzlv != 0) {
            var1 += adh.zzs(6, this.zzlv);
         }

         if (this.zzlw != 0) {
            var1 += adh.zzs(7, this.zzlw);
         }

         return var1;
      }

      // $FF: synthetic method
      public final adp zza(adg var1) throws IOException {
         adg var3 = var1;
         zzbh.zza var2 = this;

         while(true) {
            int var4;
            int var5;
            int var6;
            int var7;
            int var8;
            int var9;
            int[] var10;
            int[] var11;
            switch(var4 = var3.zzLA()) {
            case 0:
               return var2;
            case 8:
               var5 = ads.zzb(var3, 8);
               var11 = new int[(var6 = var2.zzlq == null ? 0 : var2.zzlq.length) + var5];
               if (var6 != 0) {
                  System.arraycopy(var2.zzlq, 0, var11, 0, var6);
               }
               break;
            case 10:
               var5 = var3.zzLF();
               var6 = var3.zzcn(var5);
               var7 = 0;

               for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
                  var3.zzLF();
               }

               var3.zzcp(var8);
               var10 = new int[(var9 = var2.zzlq == null ? 0 : var2.zzlq.length) + var7];
               if (var9 != 0) {
                  System.arraycopy(var2.zzlq, 0, var10, 0, var9);
               }

               while(var9 < var10.length) {
                  var10[var9] = var3.zzLF();
                  ++var9;
               }

               var2.zzlq = var10;
               var3.zzco(var6);
               continue;
            case 16:
               var5 = ads.zzb(var3, 16);
               var11 = new int[(var6 = var2.zzlr == null ? 0 : var2.zzlr.length) + var5];
               if (var6 != 0) {
                  System.arraycopy(var2.zzlr, 0, var11, 0, var6);
               }

               while(var6 < var11.length - 1) {
                  var11[var6] = var3.zzLF();
                  var3.zzLA();
                  ++var6;
               }

               var11[var6] = var3.zzLF();
               var2.zzlr = var11;
               continue;
            case 18:
               var5 = var3.zzLF();
               var6 = var3.zzcn(var5);
               var7 = 0;

               for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
                  var3.zzLF();
               }

               var3.zzcp(var8);
               var10 = new int[(var9 = var2.zzlr == null ? 0 : var2.zzlr.length) + var7];
               if (var9 != 0) {
                  System.arraycopy(var2.zzlr, 0, var10, 0, var9);
               }

               while(var9 < var10.length) {
                  var10[var9] = var3.zzLF();
                  ++var9;
               }

               var2.zzlr = var10;
               var3.zzco(var6);
               continue;
            case 24:
               var5 = ads.zzb(var3, 24);
               var11 = new int[(var6 = var2.zzls == null ? 0 : var2.zzls.length) + var5];
               if (var6 != 0) {
                  System.arraycopy(var2.zzls, 0, var11, 0, var6);
               }

               while(var6 < var11.length - 1) {
                  var11[var6] = var3.zzLF();
                  var3.zzLA();
                  ++var6;
               }

               var11[var6] = var3.zzLF();
               var2.zzls = var11;
               continue;
            case 26:
               var5 = var3.zzLF();
               var6 = var3.zzcn(var5);
               var7 = 0;

               for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
                  var3.zzLF();
               }

               var3.zzcp(var8);
               var10 = new int[(var9 = var2.zzls == null ? 0 : var2.zzls.length) + var7];
               if (var9 != 0) {
                  System.arraycopy(var2.zzls, 0, var10, 0, var9);
               }

               while(var9 < var10.length) {
                  var10[var9] = var3.zzLF();
                  ++var9;
               }

               var2.zzls = var10;
               var3.zzco(var6);
               continue;
            case 32:
               var2.zzlt = var3.zzLF();
               continue;
            case 40:
               var5 = ads.zzb(var3, 40);
               var11 = new int[(var6 = var2.zzlu == null ? 0 : var2.zzlu.length) + var5];
               if (var6 != 0) {
                  System.arraycopy(var2.zzlu, 0, var11, 0, var6);
               }

               while(var6 < var11.length - 1) {
                  var11[var6] = var3.zzLF();
                  var3.zzLA();
                  ++var6;
               }

               var11[var6] = var3.zzLF();
               var2.zzlu = var11;
               continue;
            case 42:
               var5 = var3.zzLF();
               var6 = var3.zzcn(var5);
               var7 = 0;

               for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
                  var3.zzLF();
               }

               var3.zzcp(var8);
               var10 = new int[(var9 = var2.zzlu == null ? 0 : var2.zzlu.length) + var7];
               if (var9 != 0) {
                  System.arraycopy(var2.zzlu, 0, var10, 0, var9);
               }

               while(var9 < var10.length) {
                  var10[var9] = var3.zzLF();
                  ++var9;
               }

               var2.zzlu = var10;
               var3.zzco(var6);
               continue;
            case 48:
               var2.zzlv = var3.zzLF();
               continue;
            case 56:
               var2.zzlw = var3.zzLF();
               continue;
            default:
               if (!var2.zza(var3, var4)) {
                  return var2;
               }
               continue;
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzlq = var11;
         }
      }
   }
}
