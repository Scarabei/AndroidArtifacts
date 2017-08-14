package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public final class zzb {
   public static int zza(Parcel var0, int var1) {
      return (var1 & -65536) != -65536 ? var1 >> 16 & '\uffff' : var0.readInt();
   }

   public static void zzb(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      var0.setDataPosition(var0.dataPosition() + var2);
   }

   private static void zza(Parcel var0, int var1, int var2) {
      int var3;
      if ((var3 = zza(var0, var1)) != var2) {
         String var4 = String.valueOf(Integer.toHexString(var3));
         throw new zzc((new StringBuilder(46 + String.valueOf(var4).length())).append("Expected size ").append(var2).append(" got ").append(var3).append(" (0x").append(var4).append(")").toString(), var0);
      }
   }

   private static void zza(Parcel var0, int var1, int var2, int var3) {
      if (var2 != var3) {
         String var4 = String.valueOf(Integer.toHexString(var2));
         throw new zzc((new StringBuilder(46 + String.valueOf(var4).length())).append("Expected size ").append(var3).append(" got ").append(var2).append(" (0x").append(var4).append(")").toString(), var0);
      }
   }

   public static int zzd(Parcel var0) {
      int var1 = var0.readInt();
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if ((var1 & '\uffff') != 20293) {
         zzc var10000 = new zzc;
         String var10003 = String.valueOf(Integer.toHexString(var1));
         String var10002;
         if (var10003.length() != 0) {
            var10002 = "Expected object header. Got 0x".concat(var10003);
         } else {
            String var10004 = new String;
            var10002 = var10004;
            var10004.<init>("Expected object header. Got 0x");
         }

         var10000.<init>(var10002, var0);
         throw var10000;
      } else {
         int var4;
         if ((var4 = var3 + var2) >= var3 && var4 <= var0.dataSize()) {
            return var4;
         } else {
            throw new zzc((new StringBuilder(54)).append("Size read is invalid start=").append(var3).append(" end=").append(var4).toString(), var0);
         }
      }
   }

   public static boolean zzc(Parcel var0, int var1) {
      zza(var0, var1, 4);
      return var0.readInt() != 0;
   }

   public static Boolean zzd(Parcel var0, int var1) {
      int var2;
      if ((var2 = zza(var0, var1)) == 0) {
         return null;
      } else {
         zza(var0, var1, var2, 4);
         return var0.readInt() != 0;
      }
   }

   public static byte zze(Parcel var0, int var1) {
      zza(var0, var1, 4);
      return (byte)var0.readInt();
   }

   public static short zzf(Parcel var0, int var1) {
      zza(var0, var1, 4);
      return (short)var0.readInt();
   }

   public static int zzg(Parcel var0, int var1) {
      zza(var0, var1, 4);
      return var0.readInt();
   }

   public static Integer zzh(Parcel var0, int var1) {
      int var2;
      if ((var2 = zza(var0, var1)) == 0) {
         return null;
      } else {
         zza(var0, var1, var2, 4);
         return var0.readInt();
      }
   }

   public static long zzi(Parcel var0, int var1) {
      zza(var0, var1, 8);
      return var0.readLong();
   }

   public static Long zzj(Parcel var0, int var1) {
      int var2;
      if ((var2 = zza(var0, var1)) == 0) {
         return null;
      } else {
         zza(var0, var1, var2, 8);
         return var0.readLong();
      }
   }

   public static BigInteger zzk(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         byte[] var4 = var0.createByteArray();
         var0.setDataPosition(var3 + var2);
         return new BigInteger(var4);
      }
   }

   public static float zzl(Parcel var0, int var1) {
      zza(var0, var1, 4);
      return var0.readFloat();
   }

   public static Float zzm(Parcel var0, int var1) {
      int var2;
      if ((var2 = zza(var0, var1)) == 0) {
         return null;
      } else {
         zza(var0, var1, var2, 4);
         return var0.readFloat();
      }
   }

   public static double zzn(Parcel var0, int var1) {
      zza(var0, var1, 8);
      return var0.readDouble();
   }

   public static Double zzo(Parcel var0, int var1) {
      int var2;
      if ((var2 = zza(var0, var1)) == 0) {
         return null;
      } else {
         zza(var0, var1, var2, 8);
         return var0.readDouble();
      }
   }

   public static BigDecimal zzp(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         byte[] var4 = var0.createByteArray();
         int var5 = var0.readInt();
         var0.setDataPosition(var3 + var2);
         return new BigDecimal(new BigInteger(var4), var5);
      }
   }

   public static String zzq(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         String var4 = var0.readString();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static IBinder zzr(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         IBinder var4 = var0.readStrongBinder();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static Parcelable zza(Parcel var0, int var1, Creator var2) {
      int var3 = zza(var0, var1);
      int var4 = var0.dataPosition();
      if (var3 == 0) {
         return null;
      } else {
         Parcelable var5 = (Parcelable)var2.createFromParcel(var0);
         var0.setDataPosition(var4 + var3);
         return var5;
      }
   }

   public static Bundle zzs(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         Bundle var4 = var0.readBundle();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static byte[] zzt(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         byte[] var4 = var0.createByteArray();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static byte[][] zzu(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         int var4;
         byte[][] var5 = new byte[var4 = var0.readInt()][];

         for(int var6 = 0; var6 < var4; ++var6) {
            var5[var6] = var0.createByteArray();
         }

         var0.setDataPosition(var3 + var2);
         return var5;
      }
   }

   public static boolean[] zzv(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         boolean[] var4 = var0.createBooleanArray();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static int[] zzw(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         int[] var4 = var0.createIntArray();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static long[] zzx(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         long[] var4 = var0.createLongArray();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static float[] zzy(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         float[] var4 = var0.createFloatArray();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static BigDecimal[] zzz(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         int var4;
         BigDecimal[] var5 = new BigDecimal[var4 = var0.readInt()];

         for(int var6 = 0; var6 < var4; ++var6) {
            byte[] var7 = var0.createByteArray();
            int var8 = var0.readInt();
            var5[var6] = new BigDecimal(new BigInteger(var7), var8);
         }

         var0.setDataPosition(var3 + var2);
         return var5;
      }
   }

   public static String[] zzA(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         String[] var4 = var0.createStringArray();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static ArrayList zzB(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         ArrayList var4 = new ArrayList();
         int var5 = var0.readInt();

         for(int var6 = 0; var6 < var5; ++var6) {
            var4.add(var0.readInt());
         }

         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static ArrayList zzC(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         ArrayList var4 = var0.createStringArrayList();
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static Object[] zzb(Parcel var0, int var1, Creator var2) {
      int var3 = zza(var0, var1);
      int var4 = var0.dataPosition();
      if (var3 == 0) {
         return null;
      } else {
         Object[] var5 = var0.createTypedArray(var2);
         var0.setDataPosition(var4 + var3);
         return var5;
      }
   }

   public static ArrayList zzc(Parcel var0, int var1, Creator var2) {
      int var3 = zza(var0, var1);
      int var4 = var0.dataPosition();
      if (var3 == 0) {
         return null;
      } else {
         ArrayList var5 = var0.createTypedArrayList(var2);
         var0.setDataPosition(var4 + var3);
         return var5;
      }
   }

   public static Parcel zzD(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         Parcel var4;
         (var4 = Parcel.obtain()).appendFrom(var0, var3, var2);
         var0.setDataPosition(var3 + var2);
         return var4;
      }
   }

   public static Parcel[] zzE(Parcel var0, int var1) {
      int var2 = zza(var0, var1);
      int var3 = var0.dataPosition();
      if (var2 == 0) {
         return null;
      } else {
         int var4;
         Parcel[] var5 = new Parcel[var4 = var0.readInt()];

         for(int var6 = 0; var6 < var4; ++var6) {
            int var7;
            if ((var7 = var0.readInt()) != 0) {
               int var8 = var0.dataPosition();
               Parcel var9;
               (var9 = Parcel.obtain()).appendFrom(var0, var8, var7);
               var5[var6] = var9;
               var0.setDataPosition(var8 + var7);
            } else {
               var5[var6] = null;
            }
         }

         var0.setDataPosition(var3 + var2);
         return var5;
      }
   }

   public static void zza(Parcel var0, int var1, List var2, ClassLoader var3) {
      int var4 = zza(var0, var1);
      int var5 = var0.dataPosition();
      if (var4 != 0) {
         var0.readList(var2, var3);
         var0.setDataPosition(var5 + var4);
      }
   }

   public static void zzF(Parcel var0, int var1) {
      if (var0.dataPosition() != var1) {
         throw new zzc((new StringBuilder(37)).append("Overread allowed size end=").append(var1).toString(), var0);
      }
   }
}
