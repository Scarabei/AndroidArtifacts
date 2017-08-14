package com.google.android.gms.internal;

import java.io.IOException;

public final class acn extends adj {
   private static volatile acn[] zzcqM;
   public int type = 0;
   public acn[] zzcqN = zzLp();
   public add zzcqO = null;
   public acs zzcqP = null;
   private acw zzcqQ = null;
   public ach zzcqR = null;
   private acz zzcqS = null;
   private acx zzcqT = null;
   private acv zzcqU = null;
   public aci zzcqV = null;
   public acj zzcqW = null;
   private act zzcqX = null;
   private ada zzcqY = null;
   private adf zzcqZ = null;
   public ade zzcra = null;
   private acq zzcrb = null;
   private acu zzcrc = null;
   private acy zzcrd = null;
   public adb zzcre = null;
   public add zzcrf = null;

   private static acn[] zzLp() {
      if (zzcqM == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzcqM == null) {
               zzcqM = new acn[0];
            }
         }
      }

      return zzcqM;
   }

   public acn() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acn)) {
         return false;
      } else {
         acn var2 = (acn)var1;
         if (this.type != var2.type) {
            return false;
         } else if (!adn.equals(this.zzcqN, var2.zzcqN)) {
            return false;
         } else {
            if (this.zzcqO == null) {
               if (var2.zzcqO != null) {
                  return false;
               }
            } else if (!this.zzcqO.equals(var2.zzcqO)) {
               return false;
            }

            if (this.zzcqP == null) {
               if (var2.zzcqP != null) {
                  return false;
               }
            } else if (!this.zzcqP.equals(var2.zzcqP)) {
               return false;
            }

            if (this.zzcqQ == null) {
               if (var2.zzcqQ != null) {
                  return false;
               }
            } else if (!this.zzcqQ.equals(var2.zzcqQ)) {
               return false;
            }

            if (this.zzcqR == null) {
               if (var2.zzcqR != null) {
                  return false;
               }
            } else if (!this.zzcqR.equals(var2.zzcqR)) {
               return false;
            }

            if (this.zzcqS == null) {
               if (var2.zzcqS != null) {
                  return false;
               }
            } else if (!this.zzcqS.equals(var2.zzcqS)) {
               return false;
            }

            if (this.zzcqT == null) {
               if (var2.zzcqT != null) {
                  return false;
               }
            } else if (!this.zzcqT.equals(var2.zzcqT)) {
               return false;
            }

            if (this.zzcqU == null) {
               if (var2.zzcqU != null) {
                  return false;
               }
            } else if (!this.zzcqU.equals(var2.zzcqU)) {
               return false;
            }

            if (this.zzcqV == null) {
               if (var2.zzcqV != null) {
                  return false;
               }
            } else if (!this.zzcqV.equals(var2.zzcqV)) {
               return false;
            }

            if (this.zzcqW == null) {
               if (var2.zzcqW != null) {
                  return false;
               }
            } else if (!this.zzcqW.equals(var2.zzcqW)) {
               return false;
            }

            if (this.zzcqX == null) {
               if (var2.zzcqX != null) {
                  return false;
               }
            } else if (!this.zzcqX.equals(var2.zzcqX)) {
               return false;
            }

            if (this.zzcqY == null) {
               if (var2.zzcqY != null) {
                  return false;
               }
            } else if (!this.zzcqY.equals(var2.zzcqY)) {
               return false;
            }

            if (this.zzcqZ == null) {
               if (var2.zzcqZ != null) {
                  return false;
               }
            } else if (!this.zzcqZ.equals(var2.zzcqZ)) {
               return false;
            }

            if (this.zzcra == null) {
               if (var2.zzcra != null) {
                  return false;
               }
            } else if (!this.zzcra.equals(var2.zzcra)) {
               return false;
            }

            if (this.zzcrb == null) {
               if (var2.zzcrb != null) {
                  return false;
               }
            } else if (!this.zzcrb.equals(var2.zzcrb)) {
               return false;
            }

            if (this.zzcrc == null) {
               if (var2.zzcrc != null) {
                  return false;
               }
            } else if (!this.zzcrc.equals(var2.zzcrc)) {
               return false;
            }

            if (this.zzcrd == null) {
               if (var2.zzcrd != null) {
                  return false;
               }
            } else if (!this.zzcrd.equals(var2.zzcrd)) {
               return false;
            }

            if (this.zzcre == null) {
               if (var2.zzcre != null) {
                  return false;
               }
            } else if (!this.zzcre.equals(var2.zzcre)) {
               return false;
            }

            if (this.zzcrf == null) {
               if (var2.zzcrf != null) {
                  return false;
               }
            } else if (!this.zzcrf.equals(var2.zzcrf)) {
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
      return (((((((((((((((((((((527 + this.getClass().getName().hashCode()) * 31 + this.type) * 31 + adn.hashCode(this.zzcqN)) * 31 + (this.zzcqO == null ? 0 : this.zzcqO.hashCode())) * 31 + (this.zzcqP == null ? 0 : this.zzcqP.hashCode())) * 31 + (this.zzcqQ == null ? 0 : this.zzcqQ.hashCode())) * 31 + (this.zzcqR == null ? 0 : this.zzcqR.hashCode())) * 31 + (this.zzcqS == null ? 0 : this.zzcqS.hashCode())) * 31 + (this.zzcqT == null ? 0 : this.zzcqT.hashCode())) * 31 + (this.zzcqU == null ? 0 : this.zzcqU.hashCode())) * 31 + (this.zzcqV == null ? 0 : this.zzcqV.hashCode())) * 31 + (this.zzcqW == null ? 0 : this.zzcqW.hashCode())) * 31 + (this.zzcqX == null ? 0 : this.zzcqX.hashCode())) * 31 + (this.zzcqY == null ? 0 : this.zzcqY.hashCode())) * 31 + (this.zzcqZ == null ? 0 : this.zzcqZ.hashCode())) * 31 + (this.zzcra == null ? 0 : this.zzcra.hashCode())) * 31 + (this.zzcrb == null ? 0 : this.zzcrb.hashCode())) * 31 + (this.zzcrc == null ? 0 : this.zzcrc.hashCode())) * 31 + (this.zzcrd == null ? 0 : this.zzcrd.hashCode())) * 31 + (this.zzcre == null ? 0 : this.zzcre.hashCode())) * 31 + (this.zzcrf == null ? 0 : this.zzcrf.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.type != 0) {
         var1.zzr(1, this.type);
      }

      if (this.zzcqN != null && this.zzcqN.length > 0) {
         for(int var2 = 0; var2 < this.zzcqN.length; ++var2) {
            acn var3;
            if ((var3 = this.zzcqN[var2]) != null) {
               var1.zza(2, var3);
            }
         }
      }

      if (this.zzcqO != null) {
         var1.zza(3, this.zzcqO);
      }

      if (this.zzcqP != null) {
         var1.zza(4, this.zzcqP);
      }

      if (this.zzcqQ != null) {
         var1.zza(5, this.zzcqQ);
      }

      if (this.zzcqR != null) {
         var1.zza(6, this.zzcqR);
      }

      if (this.zzcqS != null) {
         var1.zza(7, this.zzcqS);
      }

      if (this.zzcqT != null) {
         var1.zza(8, this.zzcqT);
      }

      if (this.zzcqU != null) {
         var1.zza(9, this.zzcqU);
      }

      if (this.zzcqV != null) {
         var1.zza(10, this.zzcqV);
      }

      if (this.zzcqW != null) {
         var1.zza(11, this.zzcqW);
      }

      if (this.zzcqX != null) {
         var1.zza(12, this.zzcqX);
      }

      if (this.zzcqY != null) {
         var1.zza(13, this.zzcqY);
      }

      if (this.zzcqZ != null) {
         var1.zza(14, this.zzcqZ);
      }

      if (this.zzcra != null) {
         var1.zza(15, this.zzcra);
      }

      if (this.zzcrb != null) {
         var1.zza(16, this.zzcrb);
      }

      if (this.zzcrc != null) {
         var1.zza(17, this.zzcrc);
      }

      if (this.zzcrd != null) {
         var1.zza(18, this.zzcrd);
      }

      if (this.zzcre != null) {
         var1.zza(19, this.zzcre);
      }

      if (this.zzcrf != null) {
         var1.zza(20, this.zzcrf);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.type != 0) {
         var1 += adh.zzs(1, this.type);
      }

      if (this.zzcqN != null && this.zzcqN.length > 0) {
         for(int var2 = 0; var2 < this.zzcqN.length; ++var2) {
            acn var3;
            if ((var3 = this.zzcqN[var2]) != null) {
               var1 += adh.zzb(2, var3);
            }
         }
      }

      if (this.zzcqO != null) {
         var1 += adh.zzb(3, this.zzcqO);
      }

      if (this.zzcqP != null) {
         var1 += adh.zzb(4, this.zzcqP);
      }

      if (this.zzcqQ != null) {
         var1 += adh.zzb(5, this.zzcqQ);
      }

      if (this.zzcqR != null) {
         var1 += adh.zzb(6, this.zzcqR);
      }

      if (this.zzcqS != null) {
         var1 += adh.zzb(7, this.zzcqS);
      }

      if (this.zzcqT != null) {
         var1 += adh.zzb(8, this.zzcqT);
      }

      if (this.zzcqU != null) {
         var1 += adh.zzb(9, this.zzcqU);
      }

      if (this.zzcqV != null) {
         var1 += adh.zzb(10, this.zzcqV);
      }

      if (this.zzcqW != null) {
         var1 += adh.zzb(11, this.zzcqW);
      }

      if (this.zzcqX != null) {
         var1 += adh.zzb(12, this.zzcqX);
      }

      if (this.zzcqY != null) {
         var1 += adh.zzb(13, this.zzcqY);
      }

      if (this.zzcqZ != null) {
         var1 += adh.zzb(14, this.zzcqZ);
      }

      if (this.zzcra != null) {
         var1 += adh.zzb(15, this.zzcra);
      }

      if (this.zzcrb != null) {
         var1 += adh.zzb(16, this.zzcrb);
      }

      if (this.zzcrc != null) {
         var1 += adh.zzb(17, this.zzcrc);
      }

      if (this.zzcrd != null) {
         var1 += adh.zzb(18, this.zzcrd);
      }

      if (this.zzcre != null) {
         var1 += adh.zzb(19, this.zzcre);
      }

      if (this.zzcrf != null) {
         var1 += adh.zzb(20, this.zzcrf);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acn var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         acn[] var7;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
               var2.type = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 18:
            var5 = ads.zzb(var3, 18);
            var7 = new acn[(var6 = var2.zzcqN == null ? 0 : var2.zzcqN.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcqN, 0, var7, 0, var6);
            }
            break;
         case 26:
            if (var2.zzcqO == null) {
               var2.zzcqO = new add();
            }

            var3.zza(var2.zzcqO);
            continue;
         case 34:
            if (var2.zzcqP == null) {
               var2.zzcqP = new acs();
            }

            var3.zza(var2.zzcqP);
            continue;
         case 42:
            if (var2.zzcqQ == null) {
               var2.zzcqQ = new acw();
            }

            var3.zza(var2.zzcqQ);
            continue;
         case 50:
            if (var2.zzcqR == null) {
               var2.zzcqR = new ach();
            }

            var3.zza(var2.zzcqR);
            continue;
         case 58:
            if (var2.zzcqS == null) {
               var2.zzcqS = new acz();
            }

            var3.zza(var2.zzcqS);
            continue;
         case 66:
            if (var2.zzcqT == null) {
               var2.zzcqT = new acx();
            }

            var3.zza(var2.zzcqT);
            continue;
         case 74:
            if (var2.zzcqU == null) {
               var2.zzcqU = new acv();
            }

            var3.zza(var2.zzcqU);
            continue;
         case 82:
            if (var2.zzcqV == null) {
               var2.zzcqV = new aci();
            }

            var3.zza(var2.zzcqV);
            continue;
         case 90:
            if (var2.zzcqW == null) {
               var2.zzcqW = new acj();
            }

            var3.zza(var2.zzcqW);
            continue;
         case 98:
            if (var2.zzcqX == null) {
               var2.zzcqX = new act();
            }

            var3.zza(var2.zzcqX);
            continue;
         case 106:
            if (var2.zzcqY == null) {
               var2.zzcqY = new ada();
            }

            var3.zza(var2.zzcqY);
            continue;
         case 114:
            if (var2.zzcqZ == null) {
               var2.zzcqZ = new adf();
            }

            var3.zza(var2.zzcqZ);
            continue;
         case 122:
            if (var2.zzcra == null) {
               var2.zzcra = new ade();
            }

            var3.zza(var2.zzcra);
            continue;
         case 130:
            if (var2.zzcrb == null) {
               var2.zzcrb = new acq();
            }

            var3.zza(var2.zzcrb);
            continue;
         case 138:
            if (var2.zzcrc == null) {
               var2.zzcrc = new acu();
            }

            var3.zza(var2.zzcrc);
            continue;
         case 146:
            if (var2.zzcrd == null) {
               var2.zzcrd = new acy();
            }

            var3.zza(var2.zzcrd);
            continue;
         case 154:
            if (var2.zzcre == null) {
               var2.zzcre = new adb();
            }

            var3.zza(var2.zzcre);
            continue;
         case 162:
            if (var2.zzcrf == null) {
               var2.zzcrf = new add();
            }

            var3.zza(var2.zzcrf);
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var7.length - 1) {
            var7[var6] = new acn();
            var3.zza(var7[var6]);
            var3.zzLA();
            ++var6;
         }

         var7[var6] = new acn();
         var3.zza(var7[var6]);
         var2.zzcqN = var7;
      }
   }
}
