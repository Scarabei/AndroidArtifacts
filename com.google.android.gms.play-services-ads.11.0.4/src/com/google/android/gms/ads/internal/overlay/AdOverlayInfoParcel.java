package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzqk;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class AdOverlayInfoParcel extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzv();
   public final zzc zzPd;
   public final zzim zzPe;
   public final zzw zzPf;
   public final zzaka zzPg;
   public final zzqk zzPh;
   public final String zzPi;
   public final boolean zzPj;
   public final String zzPk;
   public final zzag zzPl;
   public final int orientation;
   public final int zzPm;
   public final String url;
   public final zzaje zzvT;
   public final String zzPn;
   public final com.google.android.gms.ads.internal.zzap zzPo;

   public static void zza(Intent var0, AdOverlayInfoParcel var1) {
      Bundle var2;
      (var2 = new Bundle(1)).putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", var1);
      var0.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", var2);
   }

   public static AdOverlayInfoParcel zzb(Intent var0) {
      try {
         Bundle var1;
         (var1 = var0.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo")).setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
         return (AdOverlayInfoParcel)var1.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
      } catch (Exception var2) {
         return null;
      }
   }

   public AdOverlayInfoParcel(zzim var1, zzw var2, zzag var3, zzaka var4, int var5, zzaje var6, String var7, com.google.android.gms.ads.internal.zzap var8) {
      this.zzPd = null;
      this.zzPe = var1;
      this.zzPf = var2;
      this.zzPg = var4;
      this.zzPh = null;
      this.zzPi = null;
      this.zzPj = false;
      this.zzPk = null;
      this.zzPl = var3;
      this.orientation = var5;
      this.zzPm = 1;
      this.url = null;
      this.zzvT = var6;
      this.zzPn = var7;
      this.zzPo = var8;
   }

   public AdOverlayInfoParcel(zzim var1, zzw var2, zzag var3, zzaka var4, boolean var5, int var6, zzaje var7) {
      this.zzPd = null;
      this.zzPe = var1;
      this.zzPf = var2;
      this.zzPg = var4;
      this.zzPh = null;
      this.zzPi = null;
      this.zzPj = var5;
      this.zzPk = null;
      this.zzPl = var3;
      this.orientation = var6;
      this.zzPm = 2;
      this.url = null;
      this.zzvT = var7;
      this.zzPn = null;
      this.zzPo = null;
   }

   public AdOverlayInfoParcel(zzim var1, zzw var2, zzqk var3, zzag var4, zzaka var5, boolean var6, int var7, String var8, zzaje var9) {
      this.zzPd = null;
      this.zzPe = var1;
      this.zzPf = var2;
      this.zzPg = var5;
      this.zzPh = var3;
      this.zzPi = null;
      this.zzPj = var6;
      this.zzPk = null;
      this.zzPl = var4;
      this.orientation = var7;
      this.zzPm = 3;
      this.url = var8;
      this.zzvT = var9;
      this.zzPn = null;
      this.zzPo = null;
   }

   public AdOverlayInfoParcel(zzim var1, zzw var2, zzqk var3, zzag var4, zzaka var5, boolean var6, int var7, String var8, String var9, zzaje var10) {
      this.zzPd = null;
      this.zzPe = var1;
      this.zzPf = var2;
      this.zzPg = var5;
      this.zzPh = var3;
      this.zzPi = var9;
      this.zzPj = var6;
      this.zzPk = var8;
      this.zzPl = var4;
      this.orientation = var7;
      this.zzPm = 3;
      this.url = null;
      this.zzvT = var10;
      this.zzPn = null;
      this.zzPo = null;
   }

   public AdOverlayInfoParcel(zzc var1, zzim var2, zzw var3, zzag var4, zzaje var5) {
      this.zzPd = var1;
      this.zzPe = var2;
      this.zzPf = var3;
      this.zzPg = null;
      this.zzPh = null;
      this.zzPi = null;
      this.zzPj = false;
      this.zzPk = null;
      this.zzPl = var4;
      this.orientation = -1;
      this.zzPm = 4;
      this.url = null;
      this.zzvT = var5;
      this.zzPn = null;
      this.zzPo = null;
   }

   AdOverlayInfoParcel(zzc var1, IBinder var2, IBinder var3, IBinder var4, IBinder var5, String var6, boolean var7, String var8, IBinder var9, int var10, int var11, String var12, zzaje var13, String var14, com.google.android.gms.ads.internal.zzap var15) {
      this.zzPd = var1;
      this.zzPe = (zzim)com.google.android.gms.dynamic.zzn.zzE(com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2));
      this.zzPf = (zzw)com.google.android.gms.dynamic.zzn.zzE(com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var3));
      this.zzPg = (zzaka)com.google.android.gms.dynamic.zzn.zzE(com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var4));
      this.zzPh = (zzqk)com.google.android.gms.dynamic.zzn.zzE(com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var5));
      this.zzPi = var6;
      this.zzPj = var7;
      this.zzPk = var8;
      this.zzPl = (zzag)com.google.android.gms.dynamic.zzn.zzE(com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var9));
      this.orientation = var10;
      this.zzPm = var11;
      this.url = var12;
      this.zzvT = var13;
      this.zzPn = var14;
      this.zzPo = var15;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzPd, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, com.google.android.gms.dynamic.zzn.zzw(this.zzPe).asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, com.google.android.gms.dynamic.zzn.zzw(this.zzPf).asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, com.google.android.gms.dynamic.zzn.zzw(this.zzPg).asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, com.google.android.gms.dynamic.zzn.zzw(this.zzPh).asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzPi, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzPj);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzPk, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, com.google.android.gms.dynamic.zzn.zzw(this.zzPl).asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.orientation);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 12, this.zzPm);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.url, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.zzvT, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 16, this.zzPn, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 17, this.zzPo, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
