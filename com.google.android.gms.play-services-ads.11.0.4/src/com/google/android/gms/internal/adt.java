package com.google.android.gms.internal;

import java.io.IOException;

public final class adt extends adj {
   public Integer zzcsJ = null;
   private Integer zzcsK = null;
   public String url = null;
   public String zzcsL = null;
   private String zzcsM = null;
   public adu zzcsN = null;
   public aeb[] zzcsO = aeb.zzLX();
   public String zzcsP = null;
   public aea zzcsQ = null;
   private Boolean zzcsR = null;
   private String[] zzcsS;
   private String zzcsT;
   private Boolean zzcsU;
   private Boolean zzcsV;
   private byte[] zzcsW;
   public aec zzcsX;

   public adt() {
      this.zzcsS = ads.EMPTY_STRING_ARRAY;
      this.zzcsT = null;
      this.zzcsU = null;
      this.zzcsV = null;
      this.zzcsW = null;
      this.zzcsX = null;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.url != null) {
         var1.zzl(1, this.url);
      }

      if (this.zzcsL != null) {
         var1.zzl(2, this.zzcsL);
      }

      if (this.zzcsM != null) {
         var1.zzl(3, this.zzcsM);
      }

      int var2;
      if (this.zzcsO != null && this.zzcsO.length > 0) {
         for(var2 = 0; var2 < this.zzcsO.length; ++var2) {
            aeb var3;
            if ((var3 = this.zzcsO[var2]) != null) {
               var1.zza(4, var3);
            }
         }
      }

      if (this.zzcsR != null) {
         var1.zzk(5, this.zzcsR.booleanValue());
      }

      if (this.zzcsS != null && this.zzcsS.length > 0) {
         for(var2 = 0; var2 < this.zzcsS.length; ++var2) {
            String var4;
            if ((var4 = this.zzcsS[var2]) != null) {
               var1.zzl(6, var4);
            }
         }
      }

      if (this.zzcsT != null) {
         var1.zzl(7, this.zzcsT);
      }

      if (this.zzcsU != null) {
         var1.zzk(8, this.zzcsU.booleanValue());
      }

      if (this.zzcsV != null) {
         var1.zzk(9, this.zzcsV.booleanValue());
      }

      if (this.zzcsJ != null) {
         var1.zzr(10, this.zzcsJ.intValue());
      }

      if (this.zzcsK != null) {
         var1.zzr(11, this.zzcsK.intValue());
      }

      if (this.zzcsN != null) {
         var1.zza(12, this.zzcsN);
      }

      if (this.zzcsP != null) {
         var1.zzl(13, this.zzcsP);
      }

      if (this.zzcsQ != null) {
         var1.zza(14, this.zzcsQ);
      }

      if (this.zzcsW != null) {
         var1.zzb(15, this.zzcsW);
      }

      if (this.zzcsX != null) {
         var1.zza(17, this.zzcsX);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.url != null) {
         var1 += adh.zzm(1, this.url);
      }

      if (this.zzcsL != null) {
         var1 += adh.zzm(2, this.zzcsL);
      }

      if (this.zzcsM != null) {
         var1 += adh.zzm(3, this.zzcsM);
      }

      int var2;
      if (this.zzcsO != null && this.zzcsO.length > 0) {
         for(var2 = 0; var2 < this.zzcsO.length; ++var2) {
            aeb var3;
            if ((var3 = this.zzcsO[var2]) != null) {
               var1 += adh.zzb(4, var3);
            }
         }
      }

      if (this.zzcsR != null) {
         this.zzcsR.booleanValue();
         var1 += adh.zzct(5) + 1;
      }

      if (this.zzcsS != null && this.zzcsS.length > 0) {
         var2 = 0;
         int var6 = 0;

         for(int var4 = 0; var4 < this.zzcsS.length; ++var4) {
            String var5;
            if ((var5 = this.zzcsS[var4]) != null) {
               ++var2;
               var6 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var6 + 1 * var2;
      }

      if (this.zzcsT != null) {
         var1 += adh.zzm(7, this.zzcsT);
      }

      if (this.zzcsU != null) {
         this.zzcsU.booleanValue();
         var1 += adh.zzct(8) + 1;
      }

      if (this.zzcsV != null) {
         this.zzcsV.booleanValue();
         var1 += adh.zzct(9) + 1;
      }

      if (this.zzcsJ != null) {
         var1 += adh.zzs(10, this.zzcsJ.intValue());
      }

      if (this.zzcsK != null) {
         var1 += adh.zzs(11, this.zzcsK.intValue());
      }

      if (this.zzcsN != null) {
         var1 += adh.zzb(12, this.zzcsN);
      }

      if (this.zzcsP != null) {
         var1 += adh.zzm(13, this.zzcsP);
      }

      if (this.zzcsQ != null) {
         var1 += adh.zzb(14, this.zzcsQ);
      }

      if (this.zzcsW != null) {
         var1 += adh.zzc(15, this.zzcsW);
      }

      if (this.zzcsX != null) {
         var1 += adh.zzb(17, this.zzcsX);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      adt var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         aeb[] var8;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.url = var3.readString();
            continue;
         case 18:
            var2.zzcsL = var3.readString();
            continue;
         case 26:
            var2.zzcsM = var3.readString();
            continue;
         case 34:
            var5 = ads.zzb(var3, 34);
            var8 = new aeb[(var6 = var2.zzcsO == null ? 0 : var2.zzcsO.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcsO, 0, var8, 0, var6);
            }
            break;
         case 40:
            var2.zzcsR = var3.zzLD();
            continue;
         case 50:
            var5 = ads.zzb(var3, 50);
            String[] var7 = new String[(var6 = var2.zzcsS == null ? 0 : var2.zzcsS.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcsS, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = var3.readString();
               var3.zzLA();
               ++var6;
            }

            var7[var6] = var3.readString();
            var2.zzcsS = var7;
            continue;
         case 58:
            var2.zzcsT = var3.readString();
            continue;
         case 64:
            var2.zzcsU = var3.zzLD();
            continue;
         case 72:
            var2.zzcsV = var3.zzLD();
            continue;
         case 80:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLC()) {
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
               var2.zzcsJ = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 88:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLC()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
               var2.zzcsK = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 98:
            if (var2.zzcsN == null) {
               var2.zzcsN = new adu();
            }

            var3.zza(var2.zzcsN);
            continue;
         case 106:
            var2.zzcsP = var3.readString();
            continue;
         case 114:
            if (var2.zzcsQ == null) {
               var2.zzcsQ = new aea();
            }

            var3.zza(var2.zzcsQ);
            continue;
         case 122:
            var2.zzcsW = var3.readBytes();
            continue;
         case 138:
            if (var2.zzcsX == null) {
               var2.zzcsX = new aec();
            }

            var3.zza(var2.zzcsX);
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var8.length - 1) {
            var8[var6] = new aeb();
            var3.zza(var8[var6]);
            var3.zzLA();
            ++var6;
         }

         var8[var6] = new aeb();
         var3.zza(var8[var6]);
         var2.zzcsO = var8;
      }
   }
}
