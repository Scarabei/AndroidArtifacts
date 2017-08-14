package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public final class zzlt extends zza {
   public static final Creator CREATOR = new zzlu();
   public final int zzBw;
   public final int backgroundColor;
   public final int zzBx;
   public final int zzBy;
   public final int zzBz;
   public final int zzBA;
   public final int zzBB;
   public final int zzBC;
   public final String zzBD;
   public final int zzBE;
   public final String zzBF;
   public final int zzBG;
   public final int zzBH;
   public final String zzBI;

   public zzlt(SearchAdRequest var1) {
      this.zzBw = var1.getAnchorTextColor();
      this.backgroundColor = var1.getBackgroundColor();
      this.zzBx = var1.getBackgroundGradientBottom();
      this.zzBy = var1.getBackgroundGradientTop();
      this.zzBz = var1.getBorderColor();
      this.zzBA = var1.getBorderThickness();
      this.zzBB = var1.getBorderType();
      this.zzBC = var1.getCallButtonColor();
      this.zzBD = var1.getCustomChannels();
      this.zzBE = var1.getDescriptionTextColor();
      this.zzBF = var1.getFontFace();
      this.zzBG = var1.getHeaderTextColor();
      this.zzBH = var1.getHeaderTextSize();
      this.zzBI = var1.getQuery();
   }

   zzlt(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, String var9, int var10, String var11, int var12, int var13, String var14) {
      this.zzBw = var1;
      this.backgroundColor = var2;
      this.zzBx = var3;
      this.zzBy = var4;
      this.zzBz = var5;
      this.zzBA = var6;
      this.zzBB = var7;
      this.zzBC = var8;
      this.zzBD = var9;
      this.zzBE = var10;
      this.zzBF = var11;
      this.zzBG = var12;
      this.zzBH = var13;
      this.zzBI = var14;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzBw);
      zzd.zzc(var1, 3, this.backgroundColor);
      zzd.zzc(var1, 4, this.zzBx);
      zzd.zzc(var1, 5, this.zzBy);
      zzd.zzc(var1, 6, this.zzBz);
      zzd.zzc(var1, 7, this.zzBA);
      zzd.zzc(var1, 8, this.zzBB);
      zzd.zzc(var1, 9, this.zzBC);
      zzd.zza(var1, 10, this.zzBD, false);
      zzd.zzc(var1, 11, this.zzBE);
      zzd.zza(var1, 12, this.zzBF, false);
      zzd.zzc(var1, 13, this.zzBG);
      zzd.zzc(var1, 14, this.zzBH);
      zzd.zza(var1, 15, this.zzBI, false);
      zzd.zzI(var1, var5);
   }
}
