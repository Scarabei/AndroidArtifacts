package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class aej extends adj implements Cloneable {
   public long zzctQ = 0L;
   public long zzctR = 0L;
   private long zzctS = 0L;
   private String tag = "";
   public int zzctT = 0;
   public int zzrB = 0;
   private boolean zzccZ = false;
   private aek[] zzctU = aek.zzMe();
   private byte[] zzctV;
   private aeh zzctW;
   public byte[] zzctX;
   private String zzctY;
   private String zzctZ;
   private aeg zzcua;
   private String zzcub;
   public long zzcuc;
   private aei zzcud;
   public byte[] zzcue;
   private String zzcuf;
   private int zzcug;
   private int[] zzcuh;
   private long zzcui;
   private ael zzcmX;

   public aej() {
      this.zzctV = ads.zzcsI;
      this.zzctW = null;
      this.zzctX = ads.zzcsI;
      this.zzctY = "";
      this.zzctZ = "";
      this.zzcua = null;
      this.zzcub = "";
      this.zzcuc = 180000L;
      this.zzcud = null;
      this.zzcue = ads.zzcsI;
      this.zzcuf = "";
      this.zzcug = 0;
      this.zzcuh = ads.zzcsC;
      this.zzcui = 0L;
      this.zzcmX = null;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   private final aej zzMd() {
      aej var1;
      try {
         var1 = (aej)super.zzLN();
      } catch (CloneNotSupportedException var3) {
         throw new AssertionError(var3);
      }

      if (this.zzctU != null && this.zzctU.length > 0) {
         var1.zzctU = new aek[this.zzctU.length];

         for(int var2 = 0; var2 < this.zzctU.length; ++var2) {
            if (this.zzctU[var2] != null) {
               var1.zzctU[var2] = (aek)this.zzctU[var2].clone();
            }
         }
      }

      if (this.zzctW != null) {
         var1.zzctW = (aeh)this.zzctW.clone();
      }

      if (this.zzcua != null) {
         var1.zzcua = (aeg)this.zzcua.clone();
      }

      if (this.zzcud != null) {
         var1.zzcud = (aei)this.zzcud.clone();
      }

      if (this.zzcuh != null && this.zzcuh.length > 0) {
         var1.zzcuh = (int[])this.zzcuh.clone();
      }

      if (this.zzcmX != null) {
         var1.zzcmX = (ael)this.zzcmX.clone();
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof aej)) {
         return false;
      } else {
         aej var2 = (aej)var1;
         if (this.zzctQ != var2.zzctQ) {
            return false;
         } else if (this.zzctR != var2.zzctR) {
            return false;
         } else if (this.zzctS != var2.zzctS) {
            return false;
         } else {
            if (this.tag == null) {
               if (var2.tag != null) {
                  return false;
               }
            } else if (!this.tag.equals(var2.tag)) {
               return false;
            }

            if (this.zzctT != var2.zzctT) {
               return false;
            } else if (this.zzrB != var2.zzrB) {
               return false;
            } else if (this.zzccZ != var2.zzccZ) {
               return false;
            } else if (!adn.equals(this.zzctU, var2.zzctU)) {
               return false;
            } else if (!Arrays.equals(this.zzctV, var2.zzctV)) {
               return false;
            } else {
               if (this.zzctW == null) {
                  if (var2.zzctW != null) {
                     return false;
                  }
               } else if (!this.zzctW.equals(var2.zzctW)) {
                  return false;
               }

               if (!Arrays.equals(this.zzctX, var2.zzctX)) {
                  return false;
               } else {
                  if (this.zzctY == null) {
                     if (var2.zzctY != null) {
                        return false;
                     }
                  } else if (!this.zzctY.equals(var2.zzctY)) {
                     return false;
                  }

                  if (this.zzctZ == null) {
                     if (var2.zzctZ != null) {
                        return false;
                     }
                  } else if (!this.zzctZ.equals(var2.zzctZ)) {
                     return false;
                  }

                  if (this.zzcua == null) {
                     if (var2.zzcua != null) {
                        return false;
                     }
                  } else if (!this.zzcua.equals(var2.zzcua)) {
                     return false;
                  }

                  if (this.zzcub == null) {
                     if (var2.zzcub != null) {
                        return false;
                     }
                  } else if (!this.zzcub.equals(var2.zzcub)) {
                     return false;
                  }

                  if (this.zzcuc != var2.zzcuc) {
                     return false;
                  } else {
                     if (this.zzcud == null) {
                        if (var2.zzcud != null) {
                           return false;
                        }
                     } else if (!this.zzcud.equals(var2.zzcud)) {
                        return false;
                     }

                     if (!Arrays.equals(this.zzcue, var2.zzcue)) {
                        return false;
                     } else {
                        if (this.zzcuf == null) {
                           if (var2.zzcuf != null) {
                              return false;
                           }
                        } else if (!this.zzcuf.equals(var2.zzcuf)) {
                           return false;
                        }

                        if (this.zzcug != var2.zzcug) {
                           return false;
                        } else if (!adn.equals(this.zzcuh, var2.zzcuh)) {
                           return false;
                        } else if (this.zzcui != var2.zzcui) {
                           return false;
                        } else {
                           if (this.zzcmX == null) {
                              if (var2.zzcmX != null) {
                                 return false;
                              }
                           } else if (!this.zzcmX.equals(var2.zzcmX)) {
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
               }
            }
         }
      }
   }

   public final int hashCode() {
      return ((((((((((((((((((((((((527 + this.getClass().getName().hashCode()) * 31 + (int)(this.zzctQ ^ this.zzctQ >>> 32)) * 31 + (int)(this.zzctR ^ this.zzctR >>> 32)) * 31 + (int)(this.zzctS ^ this.zzctS >>> 32)) * 31 + (this.tag == null ? 0 : this.tag.hashCode())) * 31 + this.zzctT) * 31 + this.zzrB) * 31 + (this.zzccZ ? 1231 : 1237)) * 31 + adn.hashCode(this.zzctU)) * 31 + Arrays.hashCode(this.zzctV)) * 31 + (this.zzctW == null ? 0 : this.zzctW.hashCode())) * 31 + Arrays.hashCode(this.zzctX)) * 31 + (this.zzctY == null ? 0 : this.zzctY.hashCode())) * 31 + (this.zzctZ == null ? 0 : this.zzctZ.hashCode())) * 31 + (this.zzcua == null ? 0 : this.zzcua.hashCode())) * 31 + (this.zzcub == null ? 0 : this.zzcub.hashCode())) * 31 + (int)(this.zzcuc ^ this.zzcuc >>> 32)) * 31 + (this.zzcud == null ? 0 : this.zzcud.hashCode())) * 31 + Arrays.hashCode(this.zzcue)) * 31 + (this.zzcuf == null ? 0 : this.zzcuf.hashCode())) * 31 + this.zzcug) * 31 + adn.hashCode(this.zzcuh)) * 31 + (int)(this.zzcui ^ this.zzcui >>> 32)) * 31 + (this.zzcmX == null ? 0 : this.zzcmX.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzctQ != 0L) {
         var1.zzb(1, this.zzctQ);
      }

      if (this.tag != null && !this.tag.equals("")) {
         var1.zzl(2, this.tag);
      }

      int var2;
      if (this.zzctU != null && this.zzctU.length > 0) {
         for(var2 = 0; var2 < this.zzctU.length; ++var2) {
            aek var3;
            if ((var3 = this.zzctU[var2]) != null) {
               var1.zza(3, var3);
            }
         }
      }

      if (!Arrays.equals(this.zzctV, ads.zzcsI)) {
         var1.zzb(4, this.zzctV);
      }

      if (!Arrays.equals(this.zzctX, ads.zzcsI)) {
         var1.zzb(6, this.zzctX);
      }

      if (this.zzcua != null) {
         var1.zza(7, this.zzcua);
      }

      if (this.zzctY != null && !this.zzctY.equals("")) {
         var1.zzl(8, this.zzctY);
      }

      if (this.zzctW != null) {
         var1.zza(9, this.zzctW);
      }

      if (this.zzccZ) {
         var1.zzk(10, this.zzccZ);
      }

      if (this.zzctT != 0) {
         var1.zzr(11, this.zzctT);
      }

      if (this.zzrB != 0) {
         var1.zzr(12, this.zzrB);
      }

      if (this.zzctZ != null && !this.zzctZ.equals("")) {
         var1.zzl(13, this.zzctZ);
      }

      if (this.zzcub != null && !this.zzcub.equals("")) {
         var1.zzl(14, this.zzcub);
      }

      if (this.zzcuc != 180000L) {
         var1.zzd(15, this.zzcuc);
      }

      if (this.zzcud != null) {
         var1.zza(16, this.zzcud);
      }

      if (this.zzctR != 0L) {
         var1.zzb(17, this.zzctR);
      }

      if (!Arrays.equals(this.zzcue, ads.zzcsI)) {
         var1.zzb(18, this.zzcue);
      }

      if (this.zzcug != 0) {
         var1.zzr(19, this.zzcug);
      }

      if (this.zzcuh != null && this.zzcuh.length > 0) {
         for(var2 = 0; var2 < this.zzcuh.length; ++var2) {
            var1.zzr(20, this.zzcuh[var2]);
         }
      }

      if (this.zzctS != 0L) {
         var1.zzb(21, this.zzctS);
      }

      if (this.zzcui != 0L) {
         var1.zzb(22, this.zzcui);
      }

      if (this.zzcmX != null) {
         var1.zza(23, this.zzcmX);
      }

      if (this.zzcuf != null && !this.zzcuf.equals("")) {
         var1.zzl(24, this.zzcuf);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzctQ != 0L) {
         var1 += adh.zze(1, this.zzctQ);
      }

      if (this.tag != null && !this.tag.equals("")) {
         var1 += adh.zzm(2, this.tag);
      }

      int var2;
      if (this.zzctU != null && this.zzctU.length > 0) {
         for(var2 = 0; var2 < this.zzctU.length; ++var2) {
            aek var3;
            if ((var3 = this.zzctU[var2]) != null) {
               var1 += adh.zzb(3, var3);
            }
         }
      }

      if (!Arrays.equals(this.zzctV, ads.zzcsI)) {
         var1 += adh.zzc(4, this.zzctV);
      }

      if (!Arrays.equals(this.zzctX, ads.zzcsI)) {
         var1 += adh.zzc(6, this.zzctX);
      }

      if (this.zzcua != null) {
         var1 += adh.zzb(7, this.zzcua);
      }

      if (this.zzctY != null && !this.zzctY.equals("")) {
         var1 += adh.zzm(8, this.zzctY);
      }

      if (this.zzctW != null) {
         var1 += adh.zzb(9, this.zzctW);
      }

      if (this.zzccZ) {
         var1 += adh.zzct(10) + 1;
      }

      if (this.zzctT != 0) {
         var1 += adh.zzs(11, this.zzctT);
      }

      if (this.zzrB != 0) {
         var1 += adh.zzs(12, this.zzrB);
      }

      if (this.zzctZ != null && !this.zzctZ.equals("")) {
         var1 += adh.zzm(13, this.zzctZ);
      }

      if (this.zzcub != null && !this.zzcub.equals("")) {
         var1 += adh.zzm(14, this.zzcub);
      }

      if (this.zzcuc != 180000L) {
         var1 += adh.zzf(15, this.zzcuc);
      }

      if (this.zzcud != null) {
         var1 += adh.zzb(16, this.zzcud);
      }

      if (this.zzctR != 0L) {
         var1 += adh.zze(17, this.zzctR);
      }

      if (!Arrays.equals(this.zzcue, ads.zzcsI)) {
         var1 += adh.zzc(18, this.zzcue);
      }

      if (this.zzcug != 0) {
         var1 += adh.zzs(19, this.zzcug);
      }

      if (this.zzcuh != null && this.zzcuh.length > 0) {
         var2 = 0;

         for(int var5 = 0; var5 < this.zzcuh.length; ++var5) {
            int var4 = this.zzcuh[var5];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 2 * this.zzcuh.length;
      }

      if (this.zzctS != 0L) {
         var1 += adh.zze(21, this.zzctS);
      }

      if (this.zzcui != 0L) {
         var1 += adh.zze(22, this.zzcui);
      }

      if (this.zzcmX != null) {
         var1 += adh.zzb(23, this.zzcmX);
      }

      if (this.zzcuf != null && !this.zzcuf.equals("")) {
         var1 += adh.zzm(24, this.zzcuf);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adj zzLN() throws CloneNotSupportedException {
      return (aej)this.clone();
   }

   // $FF: synthetic method
   public final adp zzLO() throws CloneNotSupportedException {
      return (aej)this.clone();
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aej var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         aek[] var12;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzctQ = var3.zzLB();
            continue;
         case 18:
            var2.tag = var3.readString();
            continue;
         case 26:
            var5 = ads.zzb(var3, 26);
            var12 = new aek[(var6 = var2.zzctU == null ? 0 : var2.zzctU.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzctU, 0, var12, 0, var6);
            }
            break;
         case 34:
            var2.zzctV = var3.readBytes();
            continue;
         case 50:
            var2.zzctX = var3.readBytes();
            continue;
         case 58:
            if (var2.zzcua == null) {
               var2.zzcua = new aeg();
            }

            var3.zza(var2.zzcua);
            continue;
         case 66:
            var2.zzctY = var3.readString();
            continue;
         case 74:
            if (var2.zzctW == null) {
               var2.zzctW = new aeh();
            }

            var3.zza(var2.zzctW);
            continue;
         case 80:
            var2.zzccZ = var3.zzLD();
            continue;
         case 88:
            var2.zzctT = var3.zzLC();
            continue;
         case 96:
            var2.zzrB = var3.zzLC();
            continue;
         case 106:
            var2.zzctZ = var3.readString();
            continue;
         case 114:
            var2.zzcub = var3.readString();
            continue;
         case 120:
            var2.zzcuc = var3.zzLE();
            continue;
         case 130:
            if (var2.zzcud == null) {
               var2.zzcud = new aei();
            }

            var3.zza(var2.zzcud);
            continue;
         case 136:
            var2.zzctR = var3.zzLB();
            continue;
         case 146:
            var2.zzcue = var3.readBytes();
            continue;
         case 152:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLC()) {
            case 0:
            case 1:
            case 2:
               var2.zzcug = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 160:
            var5 = ads.zzb(var3, 160);
            int[] var11 = new int[(var6 = var2.zzcuh == null ? 0 : var2.zzcuh.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcuh, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLC();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLC();
            var2.zzcuh = var11;
            continue;
         case 162:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            int var7 = 0;

            int var8;
            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLC();
            }

            var3.zzcp(var8);
            int var9;
            int[] var10 = new int[(var9 = var2.zzcuh == null ? 0 : var2.zzcuh.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzcuh, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLC();
               ++var9;
            }

            var2.zzcuh = var10;
            var3.zzco(var6);
            continue;
         case 168:
            var2.zzctS = var3.zzLB();
            continue;
         case 176:
            var2.zzcui = var3.zzLB();
            continue;
         case 186:
            if (var2.zzcmX == null) {
               var2.zzcmX = new ael();
            }

            var3.zza(var2.zzcmX);
            continue;
         case 194:
            var2.zzcuf = var3.readString();
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var12.length - 1) {
            var12[var6] = new aek();
            var3.zza(var12[var6]);
            var3.zzLA();
            ++var6;
         }

         var12[var6] = new aek();
         var3.zza(var12[var6]);
         var2.zzctU = var12;
      }
   }

   // $FF: synthetic method
   public final Object clone() throws CloneNotSupportedException {
      return this.zzMd();
   }
}
