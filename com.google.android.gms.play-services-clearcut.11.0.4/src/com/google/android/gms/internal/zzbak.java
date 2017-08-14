package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzbak extends zza {
   public static final Creator CREATOR = new zzbal();
   private String packageName;
   private int zzazj;
   public final int zzazl;
   private String zzazm;
   private String zzazn;
   private boolean zzazU;
   public final String zzazk;
   private boolean zzazo;
   private int zzazp;

   public zzbak(String var1, int var2, int var3, String var4, String var5, boolean var6, String var7, boolean var8, int var9) {
      this.packageName = var1;
      this.zzazj = var2;
      this.zzazl = var3;
      this.zzazm = var4;
      this.zzazn = var5;
      this.zzazU = var6;
      this.zzazk = var7;
      this.zzazo = var8;
      this.zzazp = var9;
   }

   public zzbak(String var1, int var2, int var3, String var4, String var5, String var6, boolean var7, int var8) {
      this.packageName = (String)zzbo.zzu(var1);
      this.zzazj = var2;
      this.zzazl = var3;
      this.zzazk = var4;
      this.zzazm = var5;
      this.zzazn = var6;
      this.zzazU = !var7;
      this.zzazo = var7;
      this.zzazp = var8;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.packageName, false);
      zzd.zzc(var1, 3, this.zzazj);
      zzd.zzc(var1, 4, this.zzazl);
      zzd.zza(var1, 5, this.zzazm, false);
      zzd.zza(var1, 6, this.zzazn, false);
      zzd.zza(var1, 7, this.zzazU);
      zzd.zza(var1, 8, this.zzazk, false);
      zzd.zza(var1, 9, this.zzazo);
      zzd.zzc(var1, 10, this.zzazp);
      zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.packageName, this.zzazj, this.zzazl, this.zzazk, this.zzazm, this.zzazn, this.zzazU, this.zzazo, this.zzazp});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzbak) {
         zzbak var2 = (zzbak)var1;
         return zzbe.equal(this.packageName, var2.packageName) && this.zzazj == var2.zzazj && this.zzazl == var2.zzazl && zzbe.equal(this.zzazk, var2.zzazk) && zzbe.equal(this.zzazm, var2.zzazm) && zzbe.equal(this.zzazn, var2.zzazn) && this.zzazU == var2.zzazU && this.zzazo == var2.zzazo && this.zzazp == var2.zzazp;
      } else {
         return false;
      }
   }

   public final String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder()).append("PlayLoggerContext[");
      var1.append("package=").append(this.packageName).append(',');
      var1.append("packageVersionCode=").append(this.zzazj).append(',');
      var1.append("logSource=").append(this.zzazl).append(',');
      var1.append("logSourceName=").append(this.zzazk).append(',');
      var1.append("uploadAccount=").append(this.zzazm).append(',');
      var1.append("loggingId=").append(this.zzazn).append(',');
      var1.append("logAndroidId=").append(this.zzazU).append(',');
      var1.append("isAnonymous=").append(this.zzazo).append(',');
      var1.append("qosTier=").append(this.zzazp);
      var1.append("]");
      return var1.toString();
   }
}
