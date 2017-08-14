package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public final class LoyaltyWalletObject extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzo();
   private String zzkv;
   private String zzbOV;
   private String zzbOW;
   private String zzbOX;
   private String zzaLx;
   private String zzbOY;
   private String zzbOZ;
   private String zzbPa;
   private String zzbPb;
   private String zzbPc;
   private int state;
   private ArrayList zzbPd;
   private com.google.android.gms.wallet.wobs.zzm zzbPe;
   private ArrayList zzbPf;
   private String zzbPg;
   private String zzbPh;
   private ArrayList zzbPi;
   private boolean zzbPj;
   private ArrayList zzbPk;
   private ArrayList zzbPl;
   private ArrayList zzbPm;
   private com.google.android.gms.wallet.wobs.zzg zzbPn;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzkv, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbOV, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbOW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbOX, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaLx, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbOY, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbOZ, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbPa, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzbPb, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzbPc, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 12, this.state);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 13, this.zzbPd, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.zzbPe, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 15, this.zzbPf, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 16, this.zzbPg, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 17, this.zzbPh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 18, this.zzbPi, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 19, this.zzbPj);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 20, this.zzbPk, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 21, this.zzbPl, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 22, this.zzbPm, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 23, this.zzbPn, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   LoyaltyWalletObject(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, int var11, ArrayList var12, com.google.android.gms.wallet.wobs.zzm var13, ArrayList var14, String var15, String var16, ArrayList var17, boolean var18, ArrayList var19, ArrayList var20, ArrayList var21, com.google.android.gms.wallet.wobs.zzg var22) {
      this.zzkv = var1;
      this.zzbOV = var2;
      this.zzbOW = var3;
      this.zzbOX = var4;
      this.zzaLx = var5;
      this.zzbOY = var6;
      this.zzbOZ = var7;
      this.zzbPa = var8;
      this.zzbPb = var9;
      this.zzbPc = var10;
      this.state = var11;
      this.zzbPd = var12;
      this.zzbPe = var13;
      this.zzbPf = var14;
      this.zzbPg = var15;
      this.zzbPh = var16;
      this.zzbPi = var17;
      this.zzbPj = var18;
      this.zzbPk = var19;
      this.zzbPl = var20;
      this.zzbPm = var21;
      this.zzbPn = var22;
   }

   LoyaltyWalletObject() {
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

   public final String getAccountId() {
      return this.zzbOV;
   }

   public final String getIssuerName() {
      return this.zzbOW;
   }

   public final String getProgramName() {
      return this.zzbOX;
   }

   public final String getAccountName() {
      return this.zzaLx;
   }

   public final String getBarcodeAlternateText() {
      return this.zzbOY;
   }

   public final String getBarcodeType() {
      return this.zzbOZ;
   }

   public final String getBarcodeValue() {
      return this.zzbPa;
   }
}
