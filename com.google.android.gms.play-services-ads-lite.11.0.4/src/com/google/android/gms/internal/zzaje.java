package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

@zzzn
public final class zzaje extends zza {
   public static final Creator CREATOR = new zzajf();
   public String zzaP;
   public int zzaaO;
   public int zzaaP;
   public boolean zzaaQ;
   public boolean zzaaR;

   public zzaje(int var1, int var2, boolean var3) {
      this(var1, var2, var3, false, false);
   }

   public zzaje(int var1, int var2, boolean var3, boolean var4) {
      this(11020000, var2, true, false, var4);
   }

   private zzaje(int var1, int var2, boolean var3, boolean var4, boolean var5) {
      String var6 = String.valueOf("afma-sdk-a-v");
      String var7 = var3 ? "0" : "1";
      this((new StringBuilder(24 + String.valueOf(var6).length() + String.valueOf(var7).length())).append(var6).append(var1).append(".").append(var2).append(".").append(var7).toString(), var1, var2, var3, var5);
   }

   zzaje(String var1, int var2, int var3, boolean var4, boolean var5) {
      this.zzaP = var1;
      this.zzaaO = var2;
      this.zzaaP = var3;
      this.zzaaQ = var4;
      this.zzaaR = var5;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaP, false);
      zzd.zzc(var1, 3, this.zzaaO);
      zzd.zzc(var1, 4, this.zzaaP);
      zzd.zza(var1, 5, this.zzaaQ);
      zzd.zza(var1, 6, this.zzaaR);
      zzd.zzI(var1, var5);
   }
}
