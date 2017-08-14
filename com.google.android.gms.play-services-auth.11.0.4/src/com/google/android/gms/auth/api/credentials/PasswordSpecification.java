package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public final class PasswordSpecification extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzi();
   public static final PasswordSpecification zzalo = (new PasswordSpecification.zza()).zzh(12, 16).zzbM("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").zze("abcdefghijkmnopqrstxyz", 1).zze("ABCDEFGHJKLMNPQRSTXY", 1).zze("3456789", 1).zzmt();
   private static PasswordSpecification zzalp = (new PasswordSpecification.zza()).zzh(12, 16).zzbM("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").zze("abcdefghijklmnopqrstuvwxyz", 1).zze("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).zze("1234567890", 1).zzmt();
   private int zzaku;
   private String zzalq;
   private List zzalr;
   private List zzals;
   private int zzalt;
   private int zzalu;
   private final int[] zzalv;
   private final Random zzAO;

   PasswordSpecification(int var1, String var2, List var3, List var4, int var5, int var6) {
      this.zzaku = var1;
      this.zzalq = var2;
      this.zzalr = Collections.unmodifiableList(var3);
      this.zzals = Collections.unmodifiableList(var4);
      this.zzalt = var5;
      this.zzalu = var6;
      int[] var8;
      Arrays.fill(var8 = new int[95], -1);
      int var9 = 0;

      for(Iterator var10 = this.zzalr.iterator(); var10.hasNext(); ++var9) {
         char[] var11;
         int var12 = (var11 = ((String)var10.next()).toCharArray()).length;

         for(int var13 = 0; var13 < var12; ++var13) {
            char var14 = var11[var13];
            var8[var14 - 32] = var9;
         }
      }

      this.zzalv = var8;
      this.zzAO = new SecureRandom();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzalq, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 2, this.zzalr, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzals, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzalt);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzalu);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   private static String zzb(Collection var0) {
      char[] var1 = new char[var0.size()];
      int var2 = 0;

      char var4;
      for(Iterator var3 = var0.iterator(); var3.hasNext(); var1[var2++] = var4) {
         var4 = ((Character)var3.next()).charValue();
      }

      return new String(var1);
   }

   private static boolean zzc(int var0, int var1, int var2) {
      return var0 < 32 || var0 > 126;
   }

   // $FF: synthetic method
   static boolean zzd(int var0, int var1, int var2) {
      return zzc(var0, 32, 126);
   }

   public static class zzb extends Error {
      public zzb(String var1) {
         super(var1);
      }
   }

   public static class zza {
      private final TreeSet zzalw = new TreeSet();
      private final List zzalr = new ArrayList();
      private final List zzals = new ArrayList();
      private int zzalt = 12;
      private int zzalu = 16;

      public final PasswordSpecification.zza zzbM(@NonNull String var1) {
         this.zzalw.addAll(zzq(var1, "allowedChars"));
         return this;
      }

      public final PasswordSpecification.zza zze(@NonNull String var1, int var2) {
         TreeSet var3 = zzq(var1, "requiredChars");
         this.zzalr.add(PasswordSpecification.zzb(var3));
         this.zzals.add(Integer.valueOf(1));
         return this;
      }

      public final PasswordSpecification.zza zzh(int var1, int var2) {
         this.zzalt = 12;
         this.zzalu = 16;
         return this;
      }

      public final PasswordSpecification zzmt() {
         if (this.zzalw.isEmpty()) {
            throw new PasswordSpecification.zzb("no allowed characters specified");
         } else {
            int var2 = 0;

            Iterator var3;
            int var4;
            for(var3 = this.zzals.iterator(); var3.hasNext(); var2 += var4) {
               var4 = ((Integer)var3.next()).intValue();
            }

            if (var2 > this.zzalu) {
               throw new PasswordSpecification.zzb("required character count cannot be greater than the max password size");
            } else {
               boolean[] var9 = new boolean[95];
               var3 = this.zzalr.iterator();

               while(var3.hasNext()) {
                  char[] var5;
                  int var6 = (var5 = ((String)var3.next()).toCharArray()).length;

                  for(int var7 = 0; var7 < var6; ++var7) {
                     char var8 = var5[var7];
                     if (var9[var8 - 32]) {
                        throw new PasswordSpecification.zzb((new StringBuilder(58)).append("character ").append(var8).append(" occurs in more than one required character set").toString());
                     }

                     var9[var8 - 32] = true;
                  }
               }

               return new PasswordSpecification(1, PasswordSpecification.zzb(this.zzalw), this.zzalr, this.zzals, this.zzalt, this.zzalu);
            }
         }
      }

      private static TreeSet zzq(String var0, String var1) {
         if (TextUtils.isEmpty(var0)) {
            throw new PasswordSpecification.zzb(String.valueOf(var1).concat(" cannot be null or empty"));
         } else {
            TreeSet var2 = new TreeSet();
            char[] var3;
            int var4 = (var3 = var0.toCharArray()).length;

            for(int var5 = 0; var5 < var4; ++var5) {
               char var6;
               if (PasswordSpecification.zzd(var6 = var3[var5], 32, 126)) {
                  throw new PasswordSpecification.zzb(String.valueOf(var1).concat(" must only contain ASCII printable characters"));
               }

               var2.add(var6);
            }

            return var2;
         }
      }
   }
}
