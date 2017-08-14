package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class zzd {
   private static void zzb(Parcel var0, int var1, int var2) {
      if (var2 >= 65535) {
         var0.writeInt(-65536 | var1);
         var0.writeInt(var2);
      } else {
         var0.writeInt(var2 << 16 | var1);
      }
   }

   private static int zzG(Parcel var0, int var1) {
      var0.writeInt(-65536 | var1);
      var0.writeInt(0);
      return var0.dataPosition();
   }

   private static void zzH(Parcel var0, int var1) {
      int var2;
      int var3 = (var2 = var0.dataPosition()) - var1;
      var0.setDataPosition(var1 - 4);
      var0.writeInt(var3);
      var0.setDataPosition(var2);
   }

   public static int zze(Parcel var0) {
      return zzG(var0, 20293);
   }

   public static void zzI(Parcel var0, int var1) {
      zzH(var0, var1);
   }

   public static void zza(Parcel var0, int var1, boolean var2) {
      zzb(var0, var1, 4);
      var0.writeInt(var2 ? 1 : 0);
   }

   public static void zza(Parcel var0, int var1, Boolean var2, boolean var3) {
      if (var2 != null) {
         zzb(var0, 3, 4);
         var0.writeInt(var2.booleanValue() ? 1 : 0);
      }
   }

   public static void zza(Parcel var0, int var1, byte var2) {
      zzb(var0, var1, 4);
      var0.writeInt(var2);
   }

   public static void zza(Parcel var0, int var1, short var2) {
      zzb(var0, 3, 4);
      var0.writeInt(var2);
   }

   public static void zzc(Parcel var0, int var1, int var2) {
      zzb(var0, var1, 4);
      var0.writeInt(var2);
   }

   public static void zza(Parcel var0, int var1, Integer var2, boolean var3) {
      if (var2 != null) {
         zzb(var0, var1, 4);
         var0.writeInt(var2.intValue());
      }
   }

   public static void zza(Parcel var0, int var1, long var2) {
      zzb(var0, var1, 8);
      var0.writeLong(var2);
   }

   public static void zza(Parcel var0, int var1, Long var2, boolean var3) {
      if (var2 != null) {
         zzb(var0, var1, 8);
         var0.writeLong(var2.longValue());
      }
   }

   public static void zza(Parcel var0, int var1, float var2) {
      zzb(var0, var1, 4);
      var0.writeFloat(var2);
   }

   public static void zza(Parcel var0, int var1, Float var2, boolean var3) {
      if (var2 != null) {
         zzb(var0, var1, 4);
         var0.writeFloat(var2.floatValue());
      }
   }

   public static void zza(Parcel var0, int var1, double var2) {
      zzb(var0, var1, 8);
      var0.writeDouble(var2);
   }

   public static void zza(Parcel var0, int var1, Double var2, boolean var3) {
      if (var2 != null) {
         zzb(var0, 8, 8);
         var0.writeDouble(var2.doubleValue());
      }
   }

   public static void zza(Parcel var0, int var1, String var2, boolean var3) {
      if (var2 == null) {
         if (var3) {
            zzb(var0, var1, 0);
         }

      } else {
         int var4 = zzG(var0, var1);
         var0.writeString(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, IBinder var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         var0.writeStrongBinder(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, Parcelable var2, int var3, boolean var4) {
      if (var2 == null) {
         if (var4) {
            zzb(var0, var1, 0);
         }

      } else {
         int var5 = zzG(var0, var1);
         var2.writeToParcel(var0, var3);
         zzH(var0, var5);
      }
   }

   public static void zza(Parcel var0, int var1, Bundle var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         var0.writeBundle(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, byte[] var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         var0.writeByteArray(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, byte[][] var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         int var5 = var2.length;
         var0.writeInt(var5);

         for(int var6 = 0; var6 < var5; ++var6) {
            var0.writeByteArray(var2[var6]);
         }

         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, boolean[] var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         var0.writeBooleanArray(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, int[] var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         var0.writeIntArray(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, long[] var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         var0.writeLongArray(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, float[] var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, 7);
         var0.writeFloatArray(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, String[] var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         var0.writeStringArray(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, List var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         int var5 = var2.size();
         var0.writeInt(var5);

         for(int var6 = 0; var6 < var5; ++var6) {
            var0.writeInt(((Integer)var2.get(var6)).intValue());
         }

         zzH(var0, var4);
      }
   }

   public static void zzb(Parcel var0, int var1, List var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         var0.writeStringList(var2);
         zzH(var0, var4);
      }
   }

   public static void zza(Parcel var0, int var1, Parcelable[] var2, int var3, boolean var4) {
      if (var2 != null) {
         int var5 = zzG(var0, var1);
         int var6 = var2.length;
         var0.writeInt(var6);

         for(int var7 = 0; var7 < var6; ++var7) {
            Parcelable var8;
            if ((var8 = var2[var7]) == null) {
               var0.writeInt(0);
            } else {
               zza(var0, var8, var3);
            }
         }

         zzH(var0, var5);
      }
   }

   public static void zzc(Parcel var0, int var1, List var2, boolean var3) {
      if (var2 == null) {
         if (var3) {
            zzb(var0, var1, 0);
         }

      } else {
         int var4 = zzG(var0, var1);
         int var5 = var2.size();
         var0.writeInt(var5);

         for(int var6 = 0; var6 < var5; ++var6) {
            Parcelable var7;
            if ((var7 = (Parcelable)var2.get(var6)) == null) {
               var0.writeInt(0);
            } else {
               zza(var0, var7, 0);
            }
         }

         zzH(var0, var4);
      }
   }

   private static void zza(Parcel var0, Parcelable var1, int var2) {
      int var3 = var0.dataPosition();
      var0.writeInt(1);
      int var4 = var0.dataPosition();
      var1.writeToParcel(var0, var2);
      int var5 = var0.dataPosition();
      var0.setDataPosition(var3);
      var0.writeInt(var5 - var4);
      var0.setDataPosition(var5);
   }

   public static void zza(Parcel var0, int var1, Parcel var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, 2);
         var0.appendFrom(var2, 0, var2.dataSize());
         zzH(var0, var4);
      }
   }

   public static void zzd(Parcel var0, int var1, List var2, boolean var3) {
      if (var2 != null) {
         int var4 = zzG(var0, var1);
         var0.writeList(var2);
         zzH(var0, var4);
      }
   }
}
