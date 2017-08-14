package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.HashMap;

public class zzef {
   private static final ClassLoader zzrF = zzef.class.getClassLoader();

   public static boolean zza(Parcel var0) {
      return var0.readInt() == 1;
   }

   public static void zza(Parcel var0, boolean var1) {
      var0.writeInt(var1 ? 1 : 0);
   }

   public static Parcelable zza(Parcel var0, Creator var1) {
      return var0.readInt() == 0 ? null : (Parcelable)var1.createFromParcel(var0);
   }

   public static void zza(Parcel var0, Parcelable var1) {
      if (var1 == null) {
         var0.writeInt(0);
      } else {
         var0.writeInt(1);
         var1.writeToParcel(var0, 0);
      }
   }

   public static void zzb(Parcel var0, Parcelable var1) {
      if (var1 == null) {
         var0.writeInt(0);
      } else {
         var0.writeInt(1);
         var1.writeToParcel(var0, 1);
      }
   }

   public static void zza(Parcel var0, IInterface var1) {
      if (var1 == null) {
         var0.writeStrongBinder((IBinder)null);
      } else {
         var0.writeStrongBinder(var1.asBinder());
      }
   }

   public static ArrayList zzb(Parcel var0) {
      return var0.readArrayList(zzrF);
   }

   public static HashMap zzc(Parcel var0) {
      return var0.readHashMap(zzrF);
   }
}
