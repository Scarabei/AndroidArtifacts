package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class zzc extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private String zzOg;
   public final String url;
   public final String mimeType;
   public final String packageName;
   public final String zzOh;
   public final String zzOi;
   private String zzOj;
   public final Intent intent;

   public zzc(String var1, String var2, String var3, String var4, String var5, String var6, String var7) {
      this(var1, var2, var3, var4, var5, var6, var7, (Intent)null);
   }

   public zzc(Intent var1) {
      this((String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, var1);
   }

   public zzc(String var1, String var2, String var3, String var4, String var5, String var6, String var7, Intent var8) {
      this.zzOg = var1;
      this.url = var2;
      this.mimeType = var3;
      this.packageName = var4;
      this.zzOh = var5;
      this.zzOi = var6;
      this.zzOj = var7;
      this.intent = var8;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzOg, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.url, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.mimeType, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.packageName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzOh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzOi, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzOj, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.intent, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
