package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.List;

public final class zzbtl extends zza {
   public static final Creator CREATOR = new zzbtm();
   private String mSessionId;
   private String zzafe;
   private List zzaRL;
   private boolean zzaRM;
   private boolean zzaRN;
   private boolean zzaRO;
   private String zzaRF;
   private String zzaRP;
   private zzbtt zzaRQ;
   private zzbtr zzaRR;
   private zzbtx zzaRS;
   private zzbtz zzaRT;
   private zzbub zzaRU;
   private zzbtv zzaRV;
   private zzbtp zzaRW;
   private zzbtj zzaRX;
   private zzbth zzaRY;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.mSessionId, false);
      zzd.zza(var1, 3, this.zzafe, false);
      zzd.zzb(var1, 4, this.zzaRL, false);
      zzd.zza(var1, 5, this.zzaRM);
      zzd.zza(var1, 6, this.zzaRF, false);
      zzd.zza(var1, 7, this.zzaRP, false);
      zzd.zza(var1, 8, this.zzaRQ, var2, false);
      zzd.zza(var1, 9, this.zzaRR, var2, false);
      zzd.zza(var1, 10, this.zzaRS, var2, false);
      zzd.zza(var1, 11, this.zzaRT, var2, false);
      zzd.zza(var1, 12, this.zzaRU, var2, false);
      zzd.zza(var1, 13, this.zzaRV, var2, false);
      zzd.zza(var1, 14, this.zzaRW, var2, false);
      zzd.zza(var1, 15, this.zzaRX, var2, false);
      zzd.zza(var1, 16, this.zzaRN);
      zzd.zza(var1, 17, this.zzaRO);
      zzd.zza(var1, 18, this.zzaRY, var2, false);
      zzd.zzI(var1, var5);
   }

   zzbtl(String var1, String var2, List var3, boolean var4, boolean var5, boolean var6, String var7, String var8, zzbtt var9, zzbtr var10, zzbtx var11, zzbtz var12, zzbub var13, zzbtv var14, zzbtp var15, zzbtj var16, zzbth var17) {
      this.mSessionId = var1;
      this.zzafe = var2;
      this.zzaRL = var3;
      this.zzaRM = var4;
      this.zzaRN = var5;
      this.zzaRO = var6;
      this.zzaRF = var7;
      this.zzaRP = var8;
      this.zzaRQ = var9;
      this.zzaRR = var10;
      this.zzaRS = var11;
      this.zzaRT = var12;
      this.zzaRU = var13;
      this.zzaRV = var14;
      this.zzaRW = var15;
      this.zzaRX = var16;
      this.zzaRY = var17;
   }
}
