package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.List;

public final class WakeLockEvent extends StatsEvent {
   public static final Creator CREATOR = new zzd();
   private int zzaku;
   private final long zzaJn;
   private int zzaJo;
   private final String zzaJp;
   private final String zzaJq;
   private final String zzaJr;
   private final int zzaJs;
   private final List zzaJt;
   private final String zzaJu;
   private final long zzaJv;
   private int zzaJw;
   private final String zzaJx;
   private final float zzaJy;
   private final long mTimeout;
   private long zzaJz;

   WakeLockEvent(int var1, long var2, int var4, String var5, int var6, List var7, String var8, long var9, int var11, String var12, String var13, float var14, long var15, String var17) {
      this.zzaku = var1;
      this.zzaJn = var2;
      this.zzaJo = var4;
      this.zzaJp = var5;
      this.zzaJq = var12;
      this.zzaJr = var17;
      this.zzaJs = var6;
      this.zzaJz = -1L;
      this.zzaJt = var7;
      this.zzaJu = var8;
      this.zzaJv = var9;
      this.zzaJw = var11;
      this.zzaJx = var13;
      this.zzaJy = var14;
      this.mTimeout = var15;
   }

   public WakeLockEvent(long var1, int var3, String var4, int var5, List var6, String var7, long var8, int var10, String var11, String var12, float var13, long var14, String var16) {
      this(2, var1, var3, var4, var5, var6, var7, var8, var10, var11, var12, var13, var14, var16);
   }

   public final long getTimeMillis() {
      return this.zzaJn;
   }

   public final int getEventType() {
      return this.zzaJo;
   }

   public final long zzrV() {
      return this.zzaJz;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaJn);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, (String)this.zzaJp, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzaJs);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 6, this.zzaJt, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaJv);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, (String)this.zzaJq, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.zzaJo);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, (String)this.zzaJu, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, (String)this.zzaJx, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 14, this.zzaJw);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.zzaJy);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 16, this.mTimeout);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 17, (String)this.zzaJr, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String zzrW() {
      String var1 = String.valueOf("\t");
      String var2 = String.valueOf(this.zzaJp);
      String var3 = String.valueOf("\t");
      int var4 = this.zzaJs;
      String var5 = String.valueOf("\t");
      String var6 = this.zzaJt == null ? "" : TextUtils.join(",", this.zzaJt);
      String var7 = String.valueOf("\t");
      int var8 = this.zzaJw;
      String var9 = String.valueOf("\t");
      String var10 = this.zzaJq == null ? "" : this.zzaJq;
      String var11 = String.valueOf("\t");
      String var12 = this.zzaJx == null ? "" : this.zzaJx;
      String var13 = String.valueOf("\t");
      float var14 = this.zzaJy;
      String var15 = String.valueOf("\t");
      String var16 = this.zzaJr == null ? "" : this.zzaJr;
      return (new StringBuilder(37 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var5).length() + String.valueOf(var6).length() + String.valueOf(var7).length() + String.valueOf(var9).length() + String.valueOf(var10).length() + String.valueOf(var11).length() + String.valueOf(var12).length() + String.valueOf(var13).length() + String.valueOf(var15).length() + String.valueOf(var16).length())).append(var1).append(var2).append(var3).append(var4).append(var5).append(var6).append(var7).append(var8).append(var9).append(var10).append(var11).append(var12).append(var13).append(var14).append(var15).append(var16).toString();
   }
}
