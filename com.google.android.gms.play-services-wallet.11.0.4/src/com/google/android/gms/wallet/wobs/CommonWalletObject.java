package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import java.util.ArrayList;

@KeepName
public class CommonWalletObject extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   String zzkv;
   private String zzbPc;
   private String name;
   private String zzbOW;
   private String zzbOY;
   private String zzbOZ;
   private String zzbPa;
   private String zzbPb;
   private int state;
   private ArrayList zzbPd;
   private zzm zzbPe;
   private ArrayList zzbPf;
   private String zzbPg;
   private String zzbPh;
   private ArrayList zzbPi;
   private boolean zzbPj;
   private ArrayList zzbPk;
   private ArrayList zzbPl;
   private ArrayList zzbPm;

   public static CommonWalletObject.zza zzDU() {
      return new CommonWalletObject().new zza((zza)null);
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzkv, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbPc, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.name, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbOW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbOY, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbOZ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbPa, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbPb, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.state);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.zzbPd, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzbPe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 13, this.zzbPf, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.zzbPg, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.zzbPh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 16, this.zzbPi, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 17, this.zzbPj);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 18, this.zzbPk, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 19, this.zzbPl, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 20, this.zzbPm, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   CommonWalletObject(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, int var9, ArrayList var10, zzm var11, ArrayList var12, String var13, String var14, ArrayList var15, boolean var16, ArrayList var17, ArrayList var18, ArrayList var19) {
      this.zzkv = var1;
      this.zzbPc = var2;
      this.name = var3;
      this.zzbOW = var4;
      this.zzbOY = var5;
      this.zzbOZ = var6;
      this.zzbPa = var7;
      this.zzbPb = var8;
      this.state = var9;
      this.zzbPd = var10;
      this.zzbPe = var11;
      this.zzbPf = var12;
      this.zzbPg = var13;
      this.zzbPh = var14;
      this.zzbPi = var15;
      this.zzbPj = var16;
      this.zzbPk = var17;
      this.zzbPl = var18;
      this.zzbPm = var19;
   }

   CommonWalletObject() {
      this.zzbPd = new ArrayList();
      this.zzbPf = new ArrayList();
      this.zzbPi = new ArrayList();
      this.zzbPk = new ArrayList();
      this.zzbPl = new ArrayList();
      this.zzbPm = new ArrayList();
   }

   public final String getId() {
      return this.zzkv;
   }

   public final class zza {
      // $FF: synthetic field
      private CommonWalletObject zzbQG;

      private zza() {
         this.zzbQG = CommonWalletObject.this;
         super();
      }

      public final CommonWalletObject.zza zzgi(String var1) {
         this.zzbQG.zzkv = var1;
         return this;
      }

      public final CommonWalletObject zzDV() {
         return this.zzbQG;
      }

      // $FF: synthetic method
      zza(zza var2) {
         this();
      }
   }
}
