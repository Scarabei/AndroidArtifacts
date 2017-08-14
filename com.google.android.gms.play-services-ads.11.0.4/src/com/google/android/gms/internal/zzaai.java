package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Collections;
import java.util.List;

@zzzn
public final class zzaai extends zza {
   public static final Creator CREATOR = new zzaaj();
   private zzaae zzMM;
   private int versionCode;
   public final String zzPi;
   public String body;
   public final List zzMa;
   public final int errorCode;
   public final List zzMb;
   public final long zzTn;
   public final boolean zzTo;
   public final long zzTp;
   public final List zzTq;
   public final long zzMg;
   public final int orientation;
   public final String zzTr;
   public final long zzTs;
   public final String zzTt;
   public final boolean zzTu;
   public final String zzTv;
   public final String zzTw;
   public final boolean zzTx;
   public final boolean zzAv;
   public final boolean zzSH;
   public final boolean zzTy;
   public final boolean zzTz;
   private zzaau zzTA;
   public String zzTB;
   public final String zzTC;
   public final boolean zzAw;
   public final boolean zzAx;
   @Nullable
   public final zzaee zzTD;
   @Nullable
   public final List zzTE;
   @Nullable
   public final List zzTF;
   public final boolean zzTG;
   @Nullable
   public final zzaak zzTH;
   public final boolean zzSV;
   @Nullable
   public String zzSW;
   public final List zzMd;
   public final boolean zzMe;
   @Nullable
   public final String zzTI;
   @Nullable
   public final zzaeq zzTJ;
   @Nullable
   public final String zzTK;
   public final boolean zzTL;
   public final boolean zzTh;
   private Bundle zzTM;

   public zzaai(zzaae var1, String var2, String var3, List var4, List var5, long var6, boolean var8, long var9, List var11, long var12, int var14, String var15, long var16, String var18, boolean var19, String var20, String var21, boolean var22, boolean var23, boolean var24, boolean var25, boolean var26, String var27, boolean var28, boolean var29, zzaee var30, List var31, List var32, boolean var33, zzaak var34, boolean var35, String var36, List var37, boolean var38, String var39, zzaeq var40, String var41, boolean var42, boolean var43) {
      this(19, var2, var3, var4, -2, var5, var6, var8, var9, var11, var12, var14, var15, var16, var18, var19, var20, var21, var22, var23, var24, var25, var26, (zzaau)null, (String)null, var27, var28, var29, var30, var31, var32, var33, var34, var35, var36, var37, var38, var39, var40, var41, var42, var43, (Bundle)null);
      this.zzMM = var1;
   }

   public zzaai(zzaae var1, String var2, String var3, List var4, List var5, long var6, boolean var8, long var9, List var11, long var12, int var14, String var15, long var16, String var18, String var19, boolean var20, boolean var21, boolean var22, boolean var23, boolean var24, String var25, boolean var26, boolean var27, zzaee var28, List var29, List var30, boolean var31, zzaak var32, boolean var33, String var34, List var35, boolean var36, String var37, zzaeq var38, String var39, boolean var40, boolean var41) {
      this(19, var2, var3, var4, -2, var5, var6, var8, -1L, var11, var12, var14, var15, var16, var18, false, (String)null, var19, var20, var21, var22, var23, false, (zzaau)null, (String)null, var25, var26, var27, var28, var29, var30, var31, var32, var33, var34, var35, var36, var37, var38, var39, var40, var41, (Bundle)null);
      this.zzMM = var1;
   }

   public zzaai(int var1) {
      this(19, (String)null, (String)null, (List)null, var1, (List)null, -1L, false, -1L, (List)null, -1L, -1, (String)null, -1L, (String)null, false, (String)null, (String)null, false, false, false, true, false, (zzaau)null, (String)null, (String)null, false, false, (zzaee)null, (List)null, (List)null, false, (zzaak)null, false, (String)null, (List)null, false, (String)null, (zzaeq)null, (String)null, true, false, (Bundle)null);
   }

   public zzaai(int var1, long var2) {
      this(19, (String)null, (String)null, (List)null, var1, (List)null, -1L, false, -1L, (List)null, var2, -1, (String)null, -1L, (String)null, false, (String)null, (String)null, false, false, false, true, false, (zzaau)null, (String)null, (String)null, false, false, (zzaee)null, (List)null, (List)null, false, (zzaak)null, false, (String)null, (List)null, false, (String)null, (zzaeq)null, (String)null, true, false, (Bundle)null);
   }

   zzaai(int var1, String var2, String var3, List var4, int var5, List var6, long var7, boolean var9, long var10, List var12, long var13, int var15, String var16, long var17, String var19, boolean var20, String var21, String var22, boolean var23, boolean var24, boolean var25, boolean var26, boolean var27, zzaau var28, String var29, String var30, boolean var31, boolean var32, zzaee var33, List var34, List var35, boolean var36, zzaak var37, boolean var38, String var39, List var40, boolean var41, String var42, zzaeq var43, String var44, boolean var45, boolean var46, Bundle var47) {
      this.versionCode = var1;
      this.zzPi = var2;
      this.body = var3;
      this.zzMa = var4 != null ? Collections.unmodifiableList(var4) : null;
      this.errorCode = var5;
      this.zzMb = var6 != null ? Collections.unmodifiableList(var6) : null;
      this.zzTn = var7;
      this.zzTo = var9;
      this.zzTp = var10;
      this.zzTq = var12 != null ? Collections.unmodifiableList(var12) : null;
      this.zzMg = var13;
      this.orientation = var15;
      this.zzTr = var16;
      this.zzTs = var17;
      this.zzTt = var19;
      this.zzTu = var20;
      this.zzTv = var21;
      this.zzTw = var22;
      this.zzTx = var23;
      this.zzAv = var24;
      this.zzSH = var25;
      this.zzTy = var26;
      this.zzTL = var45;
      this.zzTz = var27;
      this.zzTA = var28;
      this.zzTB = var29;
      this.zzTC = var30;
      zzabi var48;
      if (this.body == null && this.zzTA != null && (var48 = (zzabi)this.zzTA.zza(zzabi.CREATOR)) != null && !TextUtils.isEmpty(var48.zzHg)) {
         this.body = var48.zzHg;
      }

      this.zzAw = var31;
      this.zzAx = var32;
      this.zzTD = var33;
      this.zzTE = var34;
      this.zzTF = var35;
      this.zzTG = var36;
      this.zzTH = var37;
      this.zzSV = var38;
      this.zzSW = var39;
      this.zzMd = var40;
      this.zzMe = var41;
      this.zzTI = var42;
      this.zzTJ = var43;
      this.zzTK = var44;
      this.zzTh = var46;
      this.zzTM = var47;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      if (this.zzMM != null && this.zzMM.versionCode >= 9 && !TextUtils.isEmpty(this.body)) {
         this.zzTA = new zzaau(new zzabi(this.body));
         this.body = null;
      }

      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 2, this.zzPi, false);
      zzd.zza(var1, 3, this.body, false);
      zzd.zzb(var1, 4, this.zzMa, false);
      zzd.zzc(var1, 5, this.errorCode);
      zzd.zzb(var1, 6, this.zzMb, false);
      zzd.zza(var1, 7, this.zzTn);
      zzd.zza(var1, 8, this.zzTo);
      zzd.zza(var1, 9, this.zzTp);
      zzd.zzb(var1, 10, this.zzTq, false);
      zzd.zza(var1, 11, this.zzMg);
      zzd.zzc(var1, 12, this.orientation);
      zzd.zza(var1, 13, this.zzTr, false);
      zzd.zza(var1, 14, this.zzTs);
      zzd.zza(var1, 15, this.zzTt, false);
      zzd.zza(var1, 18, this.zzTu);
      zzd.zza(var1, 19, this.zzTv, false);
      zzd.zza(var1, 21, this.zzTw, false);
      zzd.zza(var1, 22, this.zzTx);
      zzd.zza(var1, 23, this.zzAv);
      zzd.zza(var1, 24, this.zzSH);
      zzd.zza(var1, 25, this.zzTy);
      zzd.zza(var1, 26, this.zzTz);
      zzd.zza(var1, 28, this.zzTA, var2, false);
      zzd.zza(var1, 29, this.zzTB, false);
      zzd.zza(var1, 30, this.zzTC, false);
      zzd.zza(var1, 31, this.zzAw);
      zzd.zza(var1, 32, this.zzAx);
      zzd.zza(var1, 33, this.zzTD, var2, false);
      zzd.zzb(var1, 34, this.zzTE, false);
      zzd.zzb(var1, 35, this.zzTF, false);
      zzd.zza(var1, 36, this.zzTG);
      zzd.zza(var1, 37, this.zzTH, var2, false);
      zzd.zza(var1, 38, this.zzSV);
      zzd.zza(var1, 39, this.zzSW, false);
      zzd.zzb(var1, 40, this.zzMd, false);
      zzd.zza(var1, 42, this.zzMe);
      zzd.zza(var1, 43, this.zzTI, false);
      zzd.zza(var1, 44, this.zzTJ, var2, false);
      zzd.zza(var1, 45, this.zzTK, false);
      zzd.zza(var1, 46, this.zzTL);
      zzd.zza(var1, 47, this.zzTh);
      zzd.zza(var1, 48, this.zzTM, false);
      zzd.zzI(var1, var5);
   }
}
