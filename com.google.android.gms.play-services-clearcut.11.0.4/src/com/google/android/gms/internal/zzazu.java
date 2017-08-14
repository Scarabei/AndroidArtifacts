package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzazu extends zza {
   public static final Creator CREATOR = new zzazv();
   public zzbak zzazE;
   public byte[] zzazF;
   private int[] zzazG;
   private String[] zzazH;
   private int[] zzazI;
   private byte[][] zzazJ;
   private zzcqn[] zzazK;
   private boolean zzazA;
   public final aej zzazB;
   public final zzazr zzazu;
   public final zzazr zzazL;

   public zzazu(zzbak var1, aej var2, zzazr var3, zzazr var4, int[] var5, String[] var6, int[] var7, byte[][] var8, zzcqn[] var9, boolean var10) {
      this.zzazE = var1;
      this.zzazB = var2;
      this.zzazu = var3;
      this.zzazL = null;
      this.zzazG = var5;
      this.zzazH = null;
      this.zzazI = var7;
      this.zzazJ = null;
      this.zzazK = null;
      this.zzazA = var10;
   }

   zzazu(zzbak var1, byte[] var2, int[] var3, String[] var4, int[] var5, byte[][] var6, boolean var7, zzcqn[] var8) {
      this.zzazE = var1;
      this.zzazF = var2;
      this.zzazG = var3;
      this.zzazH = var4;
      this.zzazB = null;
      this.zzazu = null;
      this.zzazL = null;
      this.zzazI = var5;
      this.zzazJ = var6;
      this.zzazK = var8;
      this.zzazA = var7;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzazu) {
         zzazu var2 = (zzazu)var1;
         return zzbe.equal(this.zzazE, var2.zzazE) && Arrays.equals(this.zzazF, var2.zzazF) && Arrays.equals(this.zzazG, var2.zzazG) && Arrays.equals(this.zzazH, var2.zzazH) && zzbe.equal(this.zzazB, var2.zzazB) && zzbe.equal(this.zzazu, var2.zzazu) && zzbe.equal(this.zzazL, var2.zzazL) && Arrays.equals(this.zzazI, var2.zzazI) && Arrays.deepEquals(this.zzazJ, var2.zzazJ) && Arrays.equals(this.zzazK, var2.zzazK) && this.zzazA == var2.zzazA;
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzazE, this.zzazF, this.zzazG, this.zzazH, this.zzazB, this.zzazu, this.zzazL, this.zzazI, this.zzazJ, this.zzazK, this.zzazA});
   }

   public final String toString() {
      return "LogEventParcelable[" + this.zzazE + ", LogEventBytes: " + (this.zzazF == null ? null : new String(this.zzazF)) + ", TestCodes: " + Arrays.toString(this.zzazG) + ", MendelPackages: " + Arrays.toString(this.zzazH) + ", LogEvent: " + this.zzazB + ", ExtensionProducer: " + this.zzazu + ", VeProducer: " + this.zzazL + ", ExperimentIDs: " + Arrays.toString(this.zzazI) + ", ExperimentTokens: " + Arrays.toString(this.zzazJ) + ", ExperimentTokensParcelables: " + Arrays.toString(this.zzazK) + ", AddPhenotypeExperimentTokens: " + this.zzazA + "]";
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzazE, var2, false);
      zzd.zza(var1, 3, this.zzazF, false);
      zzd.zza(var1, 4, this.zzazG, false);
      zzd.zza(var1, 5, this.zzazH, false);
      zzd.zza(var1, 6, this.zzazI, false);
      zzd.zza(var1, 7, this.zzazJ, false);
      zzd.zza(var1, 8, this.zzazA);
      zzd.zza(var1, 9, this.zzazK, var2, false);
      zzd.zzI(var1, var5);
   }
}
