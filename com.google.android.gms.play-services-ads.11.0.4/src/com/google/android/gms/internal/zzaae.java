package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@zzzn
public final class zzaae extends zza {
   public static final Creator CREATOR = new zzaag();
   public final int versionCode;
   @Nullable
   public final Bundle zzSy;
   public final zzir zzSz;
   public final zziv zzvX;
   public final String zzvR;
   public final ApplicationInfo applicationInfo;
   @Nullable
   public final PackageInfo zzSA;
   public final String zzSB;
   public final String zzSC;
   public final String zzSD;
   public final zzaje zzvT;
   public final Bundle zzSE;
   public final int zzSF;
   public final List zzwq;
   public final Bundle zzSG;
   public final boolean zzSH;
   public final int zzSI;
   public final int zzSJ;
   public final float zzxR;
   public final String zzSK;
   public final long zzSL;
   public final String zzSM;
   @Nullable
   public final List zzSN;
   private String zzvQ;
   public final zzon zzwj;
   public final List zzSO;
   public final long zzSP;
   public final String zzSQ;
   public final float zzSR;
   public final int zzSS;
   public final int zzST;
   public final boolean zzSU;
   public final boolean zzSV;
   public final String zzSW;
   public final boolean zzSX;
   public final String zzSY;
   public final boolean zzMe;
   public final int zzSZ;
   public final Bundle zzTa;
   public final String zzTb;
   @Nullable
   public final zzky zzwl;
   public final boolean zzTc;
   public final Bundle zzTd;
   @Nullable
   public final String zzTe;
   @Nullable
   public final String zzTf;
   @Nullable
   public final String zzTg;
   public final boolean zzTh;
   public final List zzwn;
   public final String zzTi;
   public final List zzTj;

   private zzaae(@Nullable Bundle var1, zzir var2, zziv var3, String var4, ApplicationInfo var5, @Nullable PackageInfo var6, String var7, String var8, String var9, zzaje var10, Bundle var11, int var12, List var13, List var14, Bundle var15, boolean var16, int var17, int var18, float var19, String var20, long var21, String var23, @Nullable List var24, String var25, zzon var26, long var27, String var29, float var30, boolean var31, int var32, int var33, boolean var34, boolean var35, String var36, String var37, boolean var38, int var39, Bundle var40, String var41, @Nullable zzky var42, boolean var43, Bundle var44, String var45, String var46, String var47, boolean var48, List var49, String var50, List var51) {
      this(23, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var15, var16, var17, var18, var19, var20, var21, var23, var24, var25, var26, var14, var27, var29, var30, var31, var32, var33, var34, var35, var36, var37, var38, var39, var40, var41, var42, var43, var44, var45, var46, var47, var48, var49, var50, var51);
   }

   public zzaae(zzaaf var1, long var2, String var4, String var5, String var6) {
      this(var1.zzSy, var1.zzSz, var1.zzvX, var1.zzvR, var1.applicationInfo, var1.zzSA, (String)zzaji.zza((Future)var1.zzTl, (Object)""), var1.zzSC, var1.zzSD, var1.zzvT, var1.zzSE, var1.zzSF, var1.zzwq, var1.zzSO, var1.zzSG, var1.zzSH, var1.zzSI, var1.zzSJ, var1.zzxR, var1.zzSK, var1.zzSL, var1.zzSM, var1.zzSN, var1.zzvQ, var1.zzwj, var2, var1.zzSQ, var1.zzSR, var1.zzSX, var1.zzSS, var1.zzST, var1.zzSU, var1.zzSV, (String)zzaji.zza(var1.zzTk, "", 1L, TimeUnit.SECONDS), var1.zzSY, var1.zzMe, var1.zzSZ, var1.zzTa, var1.zzTb, var1.zzwl, var1.zzTc, var1.zzTd, var4, var5, var6, var1.zzTh, var1.zzwn, var1.zzTi, var1.zzTj);
   }

   zzaae(int var1, Bundle var2, zzir var3, zziv var4, String var5, ApplicationInfo var6, PackageInfo var7, String var8, String var9, String var10, zzaje var11, Bundle var12, int var13, List var14, Bundle var15, boolean var16, int var17, int var18, float var19, String var20, long var21, String var23, List var24, String var25, zzon var26, List var27, long var28, String var30, float var31, boolean var32, int var33, int var34, boolean var35, boolean var36, String var37, String var38, boolean var39, int var40, Bundle var41, String var42, zzky var43, boolean var44, Bundle var45, String var46, String var47, String var48, boolean var49, List var50, String var51, List var52) {
      this.versionCode = var1;
      this.zzSy = var2;
      this.zzSz = var3;
      this.zzvX = var4;
      this.zzvR = var5;
      this.applicationInfo = var6;
      this.zzSA = var7;
      this.zzSB = var8;
      this.zzSC = var9;
      this.zzSD = var10;
      this.zzvT = var11;
      this.zzSE = var12;
      this.zzSF = var13;
      this.zzwq = var14;
      this.zzSO = var27 == null ? Collections.emptyList() : Collections.unmodifiableList(var27);
      this.zzSG = var15;
      this.zzSH = var16;
      this.zzSI = var17;
      this.zzSJ = var18;
      this.zzxR = var19;
      this.zzSK = var20;
      this.zzSL = var21;
      this.zzSM = var23;
      this.zzSN = var24 == null ? Collections.emptyList() : Collections.unmodifiableList(var24);
      this.zzvQ = var25;
      this.zzwj = var26;
      this.zzSP = var28;
      this.zzSQ = var30;
      this.zzSR = var31;
      this.zzSX = var32;
      this.zzSS = var33;
      this.zzST = var34;
      this.zzSU = var35;
      this.zzSV = var36;
      this.zzSW = var37;
      this.zzSY = var38;
      this.zzMe = var39;
      this.zzSZ = var40;
      this.zzTa = var41;
      this.zzTb = var42;
      this.zzwl = var43;
      this.zzTc = var44;
      this.zzTd = var45;
      this.zzTe = var46;
      this.zzTf = var47;
      this.zzTg = var48;
      this.zzTh = var49;
      this.zzwn = var50;
      this.zzTi = var51;
      this.zzTj = var52;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 2, this.zzSy, false);
      zzd.zza(var1, 3, this.zzSz, var2, false);
      zzd.zza(var1, 4, this.zzvX, var2, false);
      zzd.zza(var1, 5, this.zzvR, false);
      zzd.zza(var1, 6, this.applicationInfo, var2, false);
      zzd.zza(var1, 7, this.zzSA, var2, false);
      zzd.zza(var1, 8, this.zzSB, false);
      zzd.zza(var1, 9, this.zzSC, false);
      zzd.zza(var1, 10, this.zzSD, false);
      zzd.zza(var1, 11, this.zzvT, var2, false);
      zzd.zza(var1, 12, this.zzSE, false);
      zzd.zzc(var1, 13, this.zzSF);
      zzd.zzb(var1, 14, this.zzwq, false);
      zzd.zza(var1, 15, this.zzSG, false);
      zzd.zza(var1, 16, this.zzSH);
      zzd.zzc(var1, 18, this.zzSI);
      zzd.zzc(var1, 19, this.zzSJ);
      zzd.zza(var1, 20, this.zzxR);
      zzd.zza(var1, 21, this.zzSK, false);
      zzd.zza(var1, 25, this.zzSL);
      zzd.zza(var1, 26, this.zzSM, false);
      zzd.zzb(var1, 27, this.zzSN, false);
      zzd.zza(var1, 28, this.zzvQ, false);
      zzd.zza(var1, 29, this.zzwj, var2, false);
      zzd.zzb(var1, 30, this.zzSO, false);
      zzd.zza(var1, 31, this.zzSP);
      zzd.zza(var1, 33, this.zzSQ, false);
      zzd.zza(var1, 34, this.zzSR);
      zzd.zzc(var1, 35, this.zzSS);
      zzd.zzc(var1, 36, this.zzST);
      zzd.zza(var1, 37, this.zzSU);
      zzd.zza(var1, 38, this.zzSV);
      zzd.zza(var1, 39, this.zzSW, false);
      zzd.zza(var1, 40, this.zzSX);
      zzd.zza(var1, 41, this.zzSY, false);
      zzd.zza(var1, 42, this.zzMe);
      zzd.zzc(var1, 43, this.zzSZ);
      zzd.zza(var1, 44, this.zzTa, false);
      zzd.zza(var1, 45, this.zzTb, false);
      zzd.zza(var1, 46, this.zzwl, var2, false);
      zzd.zza(var1, 47, this.zzTc);
      zzd.zza(var1, 48, this.zzTd, false);
      zzd.zza(var1, 49, this.zzTe, false);
      zzd.zza(var1, 50, this.zzTf, false);
      zzd.zza(var1, 51, this.zzTg, false);
      zzd.zza(var1, 52, this.zzTh);
      zzd.zza(var1, 53, this.zzwn, false);
      zzd.zza(var1, 54, this.zzTi, false);
      zzd.zzb(var1, 55, this.zzTj, false);
      zzd.zzI(var1, var5);
   }
}
