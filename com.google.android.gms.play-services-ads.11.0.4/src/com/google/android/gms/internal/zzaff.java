package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@zzzn
public final class zzaff {
   public final zzir zzSz;
   @Nullable
   public final zzaka zzPg;
   public final List zzMa;
   public final int errorCode;
   public final List zzMb;
   public final List zzTq;
   public final int orientation;
   public final long zzMg;
   public final String zzSC;
   public final JSONObject zzXL;
   public final boolean zzTG;
   private zzaak zzTH;
   public boolean zzXM;
   public final boolean zzTo;
   @Nullable
   public final zzua zzMG;
   @Nullable
   public final zzut zzMH;
   @Nullable
   public final String zzMI;
   public final zzub zzXN;
   @Nullable
   public final zzud zzMJ;
   private long zzTp;
   @Nullable
   public final String zzXO;
   public final zziv zzXP;
   private long zzTn;
   @Nullable
   public final zzaee zzTD;
   @Nullable
   public final List zzXQ;
   @Nullable
   public final List zzTF;
   public final long zzXR;
   public final long zzXS;
   public final String zzTt;
   public final String zzTK;
   @Nullable
   public final zzoa zzXT;
   public boolean zzXU;
   public boolean zzXV;
   public boolean zzXW;
   @Nullable
   public final List zzMd;
   private zzig zzXX;

   public zzaff(zzir var1, @Nullable zzaka var2, List var3, int var4, List var5, List var6, int var7, long var8, String var10, boolean var11, @Nullable zzua var12, @Nullable zzut var13, @Nullable String var14, zzub var15, @Nullable zzud var16, long var17, zziv var19, long var20, long var22, long var24, String var26, JSONObject var27, @Nullable zzoa var28, zzaee var29, List var30, List var31, boolean var32, zzaak var33, @Nullable String var34, List var35, String var36, zzig var37) {
      this.zzXU = false;
      this.zzXV = false;
      this.zzXW = false;
      this.zzSz = var1;
      this.zzPg = var2;
      this.zzMa = zzn(var3);
      this.errorCode = var4;
      this.zzMb = zzn(var5);
      this.zzTq = zzn(var6);
      this.orientation = var7;
      this.zzMg = var8;
      this.zzSC = var10;
      this.zzTo = var11;
      this.zzMG = var12;
      this.zzMH = var13;
      this.zzMI = var14;
      this.zzXN = var15;
      this.zzMJ = var16;
      this.zzTp = var17;
      this.zzXP = var19;
      this.zzTn = var20;
      this.zzXR = var22;
      this.zzXS = var24;
      this.zzTt = var26;
      this.zzXL = var27;
      this.zzXT = var28;
      this.zzTD = var29;
      this.zzXQ = zzn(var30);
      this.zzTF = zzn(var31);
      this.zzTG = var32;
      this.zzTH = var33;
      this.zzXO = var34;
      this.zzMd = zzn(var35);
      this.zzTK = var36;
      this.zzXX = var37;
   }

   public zzaff(zzafg var1, @Nullable zzaka var2, @Nullable zzua var3, @Nullable zzut var4, @Nullable String var5, @Nullable zzud var6, @Nullable zzoa var7, @Nullable String var8) {
      this(var1.zzUj.zzSz, (zzaka)null, var1.zzXY.zzMa, var1.errorCode, var1.zzXY.zzMb, var1.zzXY.zzTq, var1.zzXY.orientation, var1.zzXY.zzMg, var1.zzUj.zzSC, var1.zzXY.zzTo, (zzua)null, (zzut)null, (String)null, var1.zzXN, (zzud)null, var1.zzXY.zzTp, var1.zzvX, var1.zzXY.zzTn, var1.zzXR, var1.zzXS, var1.zzXY.zzTt, var1.zzXL, (zzoa)null, var1.zzXY.zzTD, var1.zzXY.zzTE, var1.zzXY.zzTE, var1.zzXY.zzTG, var1.zzXY.zzTH, (String)null, var1.zzXY.zzMd, var1.zzXY.zzTK, var1.zzXX);
   }

   public final boolean zzcn() {
      return this.zzPg != null && this.zzPg.zziw() != null ? this.zzPg.zziw().zzcn() : false;
   }

   @Nullable
   private static List zzn(@Nullable List var0) {
      return var0 == null ? null : Collections.unmodifiableList(var0);
   }
}
