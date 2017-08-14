package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Process;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.awareness.AwarenessOptions;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.util.zzd;
import java.util.Arrays;

public final class zzbkb extends zza {
   public static final Creator CREATOR = new zzbkc();
   public final String zzaLx;
   public final String packageName;
   private int uid;
   public final String moduleId;
   private int zzaLy;
   private int zzaLz;
   private String zzaLA;
   private String zzaLB;
   private int zzaLC;
   private int pid;
   private zzej zzaLD;

   public static zzbkb zza(Context var0, String var1, AwarenessOptions var2) {
      if (var2.getAccount() != null) {
         var1 = var2.getAccount().name;
      }

      return new zzbkb(var1, var0.getPackageName(), Process.myUid(), var2.zzmQ(), zzd.zzA(var0, var0.getPackageName()), var2.zzmR(), var2.zzmS(), var2.zzmT(), var2.zzmU(), Process.myPid());
   }

   public zzbkb(String var1, String var2, int var3, String var4, int var5, int var6, String var7, String var8, int var9, int var10) {
      this.zzaLx = var1;
      this.packageName = var2;
      this.uid = var3;
      this.moduleId = var4;
      this.zzaLy = var5;
      this.zzaLz = var6;
      this.zzaLA = var7;
      this.zzaLB = var8;
      this.zzaLC = var9;
      this.pid = var10;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaLx, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.packageName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.uid);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.moduleId, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzaLy);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzaLz);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaLA, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaLB, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.zzaLC);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.pid);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      zzej var10000;
      if (this.zzaLx == null) {
         var10000 = null;
      } else {
         if (this.zzaLD == null) {
            this.zzaLD = new zzej(this.zzaLx);
         }

         var10000 = this.zzaLD;
      }

      String var1 = String.valueOf(var10000);
      String var2 = this.packageName;
      int var3 = this.uid;
      String var4 = this.moduleId;
      int var5 = this.zzaLy;
      String var6 = String.valueOf(Integer.toString(this.zzaLz));
      String var7 = this.zzaLA;
      String var8 = this.zzaLB;
      int var9 = this.pid;
      return (new StringBuilder(89 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var4).length() + String.valueOf(var6).length() + String.valueOf(var7).length() + String.valueOf(var8).length())).append("(accnt=").append(var1).append(", ").append(var2).append("(").append(var3).append("):").append(var4).append(", vrsn=").append(var5).append(", ").append(var6).append(", 3pPkg = ").append(var7).append(" ,  3pMdlId = ").append(var8).append(" ,  pid = ").append(var9).append(")").toString();
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaLx, this.packageName, this.uid, this.moduleId, this.zzaLy, this.zzaLz, this.zzaLA, this.zzaLB, this.zzaLC});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzbkb)) {
         return false;
      } else {
         zzbkb var2 = (zzbkb)var1;
         return this.uid == var2.uid && this.zzaLy == var2.zzaLy && this.zzaLz == var2.zzaLz && this.zzaLC == var2.zzaLC && TextUtils.equals(this.zzaLx, var2.zzaLx) && TextUtils.equals(this.packageName, var2.packageName) && TextUtils.equals(this.moduleId, var2.moduleId) && TextUtils.equals(this.zzaLA, var2.zzaLA) && TextUtils.equals(this.zzaLB, var2.zzaLB);
      }
   }
}
