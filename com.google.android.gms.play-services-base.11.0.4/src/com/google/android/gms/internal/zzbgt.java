package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.common.util.zzp;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class zzbgt extends zzbgl {
   private final int zzaku;
   private final Parcel zzaIW;
   private final int zzaIX;
   private final zzbgo zzaIP;
   private final String mClassName;
   private int zzaIY;
   private int zzaIZ;
   public static final Creator CREATOR = new zzbgu();

   zzbgt(int var1, Parcel var2, zzbgo var3) {
      this.zzaku = var1;
      this.zzaIW = (Parcel)zzbo.zzu(var2);
      this.zzaIX = 2;
      this.zzaIP = var3;
      if (this.zzaIP == null) {
         this.mClassName = null;
      } else {
         this.mClassName = this.zzaIP.zzrR();
      }

      this.zzaIY = 2;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zza(var1, 2, this.zzrT(), false);
      zzbgo var10002;
      switch(this.zzaIX) {
      case 0:
         var10002 = null;
         break;
      case 1:
         var10002 = this.zzaIP;
         break;
      case 2:
         var10002 = this.zzaIP;
         break;
      default:
         int var7 = this.zzaIX;
         throw new IllegalStateException((new StringBuilder(34)).append("Invalid creation type: ").append(var7).toString());
      }

      zzd.zza(var1, 3, var10002, var2, false);
      zzd.zzI(var1, var5);
   }

   private Parcel zzrT() {
      switch(this.zzaIY) {
      case 0:
         this.zzaIZ = zzd.zze(this.zzaIW);
      case 1:
         zzd.zzI(this.zzaIW, this.zzaIZ);
         this.zzaIY = 2;
      default:
         return this.zzaIW;
      }
   }

   public final Map zzrL() {
      return this.zzaIP == null ? null : this.zzaIP.zzcJ(this.mClassName);
   }

   public final Object zzcH(String var1) {
      throw new UnsupportedOperationException("Converting to JSON does not require this method.");
   }

   public final boolean zzcI(String var1) {
      throw new UnsupportedOperationException("Converting to JSON does not require this method.");
   }

   public String toString() {
      zzbo.zzb(this.zzaIP, "Cannot convert to JSON on client side.");
      Parcel var1;
      (var1 = this.zzrT()).setDataPosition(0);
      StringBuilder var2 = new StringBuilder(100);
      this.zza(var2, this.zzaIP.zzcJ(this.mClassName), var1);
      return var2.toString();
   }

   private final void zza(StringBuilder var1, Map var2, Parcel var3) {
      SparseArray var11 = new SparseArray();
      Iterator var12 = var2.entrySet().iterator();

      while(var12.hasNext()) {
         Entry var13 = (Entry)var12.next();
         var11.put(((zzbgj)var13.getValue()).zzaIM, var13);
      }

      SparseArray var4 = var11;
      var1.append('{');
      int var5 = zzb.zzd(var3);
      boolean var6 = false;

      while(var3.dataPosition() < var5) {
         int var7;
         int var8 = (var7 = var3.readInt()) & '\uffff';
         Entry var9;
         if ((var9 = (Entry)var4.get(var8)) != null) {
            if (var6) {
               var1.append(",");
            }

            String var10002 = (String)var9.getKey();
            zzbgj var32 = (zzbgj)var9.getValue();
            String var31 = var10002;
            var1.append("\"").append(var31).append("\":");
            if (var32.zzrO()) {
               int var30;
               switch(var32.zzaIJ) {
               case 0:
                  var30 = zzb.zzg(var3, var7);
                  this.zzb(var1, var32, zza(var32, var30));
                  break;
               case 1:
                  BigInteger var29 = zzb.zzk(var3, var7);
                  this.zzb(var1, var32, zza(var32, var29));
                  break;
               case 2:
                  long var28 = zzb.zzi(var3, var7);
                  this.zzb(var1, var32, zza(var32, var28));
                  break;
               case 3:
                  float var27 = zzb.zzl(var3, var7);
                  this.zzb(var1, var32, zza(var32, var27));
                  break;
               case 4:
                  double var26 = zzb.zzn(var3, var7);
                  this.zzb(var1, var32, zza(var32, var26));
                  break;
               case 5:
                  BigDecimal var25 = zzb.zzp(var3, var7);
                  this.zzb(var1, var32, zza(var32, var25));
                  break;
               case 6:
                  boolean var24 = zzb.zzc(var3, var7);
                  this.zzb(var1, var32, zza(var32, var24));
                  break;
               case 7:
                  String var23 = zzb.zzq(var3, var7);
                  this.zzb(var1, var32, zza(var32, var23));
                  break;
               case 8:
               case 9:
                  byte[] var21 = zzb.zzt(var3, var7);
                  this.zzb(var1, var32, zza(var32, var21));
                  break;
               case 10:
                  HashMap var22 = zzo(zzb.zzs(var3, var7));
                  this.zzb(var1, var32, zza(var32, var22));
                  break;
               case 11:
                  throw new IllegalArgumentException("Method does not accept concrete type.");
               default:
                  var30 = var32.zzaIJ;
                  throw new IllegalArgumentException((new StringBuilder(36)).append("Unknown field out type = ").append(var30).toString());
               }
            } else {
               this.zza(var1, var32, var3, var7);
            }

            var6 = true;
         }
      }

      if (var3.dataPosition() != var5) {
         throw new zzc((new StringBuilder(37)).append("Overread allowed size end=").append(var5).toString(), var3);
      } else {
         var1.append('}');
      }
   }

   private final void zza(StringBuilder var1, zzbgj var2, Parcel var3, int var4) {
      if (!var2.zzaIK) {
         byte[] var29;
         switch(var2.zzaIJ) {
         case 0:
            int var38 = zzb.zzg(var3, var4);
            var1.append(var38);
            return;
         case 1:
            BigInteger var36 = zzb.zzk(var3, var4);
            var1.append(var36);
            return;
         case 2:
            long var35 = zzb.zzi(var3, var4);
            var1.append(var35);
            return;
         case 3:
            float var34 = zzb.zzl(var3, var4);
            var1.append(var34);
            return;
         case 4:
            double var33 = zzb.zzn(var3, var4);
            var1.append(var33);
            return;
         case 5:
            BigDecimal var32 = zzb.zzp(var3, var4);
            var1.append(var32);
            return;
         case 6:
            boolean var31 = zzb.zzc(var3, var4);
            var1.append(var31);
            return;
         case 7:
            String var30 = zzb.zzq(var3, var4);
            var1.append("\"").append(zzo.zzcK(var30)).append("\"");
            return;
         case 8:
            var29 = zzb.zzt(var3, var4);
            var1.append("\"").append(com.google.android.gms.common.util.zzc.encode(var29)).append("\"");
            return;
         case 9:
            var29 = zzb.zzt(var3, var4);
            var1.append("\"").append(com.google.android.gms.common.util.zzc.zzg(var29));
            var1.append("\"");
            return;
         case 10:
            Set var21;
            Bundle var28;
            (var21 = (var28 = zzb.zzs(var3, var4)).keySet()).size();
            var1.append("{");
            boolean var8 = true;
            Iterator var9 = var21.iterator();

            while(var9.hasNext()) {
               String var10 = (String)var9.next();
               if (!var8) {
                  var1.append(",");
               }

               var8 = false;
               var1.append("\"").append(var10).append("\"");
               var1.append(":");
               var1.append("\"").append(zzo.zzcK(var28.getString(var10))).append("\"");
            }

            var1.append("}");
            return;
         case 11:
            Parcel var27;
            (var27 = zzb.zzD(var3, var4)).setDataPosition(0);
            this.zza(var1, var2.zzrP(), var27);
            return;
         default:
            throw new IllegalStateException("Unknown field type out");
         }
      } else {
         var1.append("[");
         int var13;
         int var14;
         label90:
         switch(var2.zzaIJ) {
         case 0:
            int[] var26 = zzb.zzw(var3, var4);
            int[] var12 = var26;
            StringBuilder var37 = var1;
            var13 = var26.length;
            var14 = 0;

            while(true) {
               if (var14 >= var13) {
                  break label90;
               }

               if (var14 != 0) {
                  var37.append(",");
               }

               var37.append(Integer.toString(var12[var14]));
               ++var14;
            }
         case 1:
            Parcel var11 = var3;
            var13 = zzb.zza(var3, var4);
            var14 = var3.dataPosition();
            BigInteger[] var40;
            if (var13 == 0) {
               var40 = null;
            } else {
               int var39;
               BigInteger[] var16 = new BigInteger[var39 = var3.readInt()];

               for(int var17 = 0; var17 < var39; ++var17) {
                  var16[var17] = new BigInteger(var11.createByteArray());
               }

               var11.setDataPosition(var14 + var13);
               var40 = var16;
            }

            BigInteger[] var25 = var40;
            com.google.android.gms.common.util.zzb.zza(var1, var25);
            break;
         case 2:
            long[] var24 = zzb.zzx(var3, var4);
            com.google.android.gms.common.util.zzb.zza(var1, var24);
            break;
         case 3:
            float[] var23 = zzb.zzy(var3, var4);
            com.google.android.gms.common.util.zzb.zza(var1, var23);
            break;
         case 4:
            var13 = zzb.zza(var3, var4);
            var14 = var3.dataPosition();
            double[] var10000;
            if (var13 == 0) {
               var10000 = null;
            } else {
               double[] var15 = var3.createDoubleArray();
               var3.setDataPosition(var14 + var13);
               var10000 = var15;
            }

            double[] var22 = var10000;
            com.google.android.gms.common.util.zzb.zza(var1, var22);
            break;
         case 5:
            BigDecimal[] var20 = zzb.zzz(var3, var4);
            com.google.android.gms.common.util.zzb.zza(var1, var20);
            break;
         case 6:
            boolean[] var19 = zzb.zzv(var3, var4);
            com.google.android.gms.common.util.zzb.zza(var1, var19);
            break;
         case 7:
            String[] var18 = zzb.zzA(var3, var4);
            com.google.android.gms.common.util.zzb.zza(var1, var18);
            break;
         case 8:
         case 9:
         case 10:
            throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
         case 11:
            Parcel[] var5;
            int var6 = (var5 = zzb.zzE(var3, var4)).length;
            int var7 = 0;

            while(true) {
               if (var7 >= var6) {
                  break label90;
               }

               if (var7 > 0) {
                  var1.append(",");
               }

               var5[var7].setDataPosition(0);
               this.zza(var1, var2.zzrP(), var5[var7]);
               ++var7;
            }
         default:
            throw new IllegalStateException("Unknown field type out.");
         }

         var1.append("]");
      }
   }

   private final void zzb(StringBuilder var1, zzbgj var2, Object var3) {
      if (var2.zzaII) {
         ArrayList var6 = (ArrayList)var3;
         zzbgj var5 = var2;
         StringBuilder var4 = var1;
         var1.append("[");
         int var7 = var6.size();

         for(int var8 = 0; var8 < var7; ++var8) {
            if (var8 != 0) {
               var4.append(",");
            }

            zza(var4, var5.zzaIH, var6.get(var8));
         }

         var4.append("]");
      } else {
         zza(var1, var2.zzaIH, var3);
      }
   }

   private static void zza(StringBuilder var0, int var1, Object var2) {
      switch(var1) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
         var0.append(var2);
         return;
      case 7:
         var0.append("\"").append(zzo.zzcK(var2.toString())).append("\"");
         return;
      case 8:
         var0.append("\"").append(com.google.android.gms.common.util.zzc.encode((byte[])var2)).append("\"");
         return;
      case 9:
         var0.append("\"").append(com.google.android.gms.common.util.zzc.zzg((byte[])var2));
         var0.append("\"");
         return;
      case 10:
         zzp.zza(var0, (HashMap)var2);
         return;
      case 11:
         throw new IllegalArgumentException("Method does not accept concrete type.");
      default:
         throw new IllegalArgumentException((new StringBuilder(26)).append("Unknown type = ").append(var1).toString());
      }
   }

   private static HashMap zzo(Bundle var0) {
      HashMap var1 = new HashMap();
      Iterator var2 = var0.keySet().iterator();

      while(var2.hasNext()) {
         String var3 = (String)var2.next();
         var1.put(var3, var0.getString(var3));
      }

      return var1;
   }
}
