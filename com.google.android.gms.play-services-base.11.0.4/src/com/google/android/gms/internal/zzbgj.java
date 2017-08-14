package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Map;

public final class zzbgj extends zza {
   private final int zzaku;
   protected final int zzaIH;
   protected final boolean zzaII;
   protected final int zzaIJ;
   protected final boolean zzaIK;
   protected final String zzaIL;
   protected final int zzaIM;
   protected final Class zzaIN;
   private String zzaIO;
   private zzbgo zzaIP;
   private zzbgk zzaIQ;
   public static final zzbgm CREATOR = new zzbgm();

   zzbgj(int var1, int var2, boolean var3, int var4, boolean var5, String var6, int var7, String var8, zzbgc var9) {
      this.zzaku = var1;
      this.zzaIH = var2;
      this.zzaII = var3;
      this.zzaIJ = var4;
      this.zzaIK = var5;
      this.zzaIL = var6;
      this.zzaIM = var7;
      if (var8 == null) {
         this.zzaIN = null;
         this.zzaIO = null;
      } else {
         this.zzaIN = zzbgt.class;
         this.zzaIO = var8;
      }

      if (var9 == null) {
         this.zzaIQ = null;
      } else {
         this.zzaIQ = var9.zzrK();
      }
   }

   private zzbgj(int var1, boolean var2, int var3, boolean var4, String var5, int var6, Class var7, zzbgk var8) {
      this.zzaku = 1;
      this.zzaIH = var1;
      this.zzaII = var2;
      this.zzaIJ = var3;
      this.zzaIK = var4;
      this.zzaIL = var5;
      this.zzaIM = var6;
      this.zzaIN = var7;
      if (var7 == null) {
         this.zzaIO = null;
      } else {
         this.zzaIO = var7.getCanonicalName();
      }

      this.zzaIQ = var8;
   }

   public final int zzrM() {
      return this.zzaIM;
   }

   private String zzrN() {
      return this.zzaIO == null ? null : this.zzaIO;
   }

   public final boolean zzrO() {
      return this.zzaIQ != null;
   }

   public final void zza(zzbgo var1) {
      this.zzaIP = var1;
   }

   public final Map zzrP() {
      zzbo.zzu(this.zzaIO);
      zzbo.zzu(this.zzaIP);
      return this.zzaIP.zzcJ(this.zzaIO);
   }

   public final Object convertBack(Object var1) {
      return this.zzaIQ.convertBack(var1);
   }

   public static zzbgj zzj(String var0, int var1) {
      return new zzbgj(0, false, 0, false, var0, var1, (Class)null, (zzbgk)null);
   }

   public static zzbgj zzk(String var0, int var1) {
      return new zzbgj(6, false, 6, false, var0, var1, (Class)null, (zzbgk)null);
   }

   public static zzbgj zzl(String var0, int var1) {
      return new zzbgj(7, false, 7, false, var0, var1, (Class)null, (zzbgk)null);
   }

   public static zzbgj zza(String var0, int var1, Class var2) {
      return new zzbgj(11, false, 11, false, var0, var1, var2, (zzbgk)null);
   }

   public static zzbgj zzb(String var0, int var1, Class var2) {
      return new zzbgj(11, true, 11, true, var0, var1, var2, (zzbgk)null);
   }

   public static zzbgj zza(String var0, int var1, zzbgk var2, boolean var3) {
      return new zzbgj(7, false, 0, false, var0, var1, (Class)null, var2);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zzc(var1, 2, this.zzaIH);
      zzd.zza(var1, 3, this.zzaII);
      zzd.zzc(var1, 4, this.zzaIJ);
      zzd.zza(var1, 5, this.zzaIK);
      zzd.zza(var1, 6, this.zzaIL, false);
      zzd.zzc(var1, 7, this.zzaIM);
      zzd.zza(var1, 8, this.zzrN(), false);
      zzd.zza(var1, 9, this.zzaIQ == null ? null : zzbgc.zza(this.zzaIQ), var2, false);
      zzd.zzI(var1, var5);
   }

   public final String toString() {
      zzbg var1 = zzbe.zzt(this).zzg("versionCode", this.zzaku).zzg("typeIn", this.zzaIH).zzg("typeInArray", this.zzaII).zzg("typeOut", this.zzaIJ).zzg("typeOutArray", this.zzaIK).zzg("outputFieldName", this.zzaIL).zzg("safeParcelFieldId", this.zzaIM).zzg("concreteTypeName", this.zzrN());
      Class var2 = this.zzaIN;
      if (this.zzaIN != null) {
         var1.zzg("concreteType.class", var2.getCanonicalName());
      }

      if (this.zzaIQ != null) {
         var1.zzg("converterName", this.zzaIQ.getClass().getCanonicalName());
      }

      return var1.toString();
   }

   // $FF: synthetic method
   static zzbgk zzc(zzbgj var0) {
      return var0.zzaIQ;
   }
}
