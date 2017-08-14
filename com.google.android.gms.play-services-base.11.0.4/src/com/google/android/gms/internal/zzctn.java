package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzctn extends zza implements Result {
   public static final Creator CREATOR = new zzcto();
   private int zzaku;
   private int zzbCR;
   private Intent zzbCS;

   zzctn(int var1, int var2, Intent var3) {
      this.zzaku = var1;
      this.zzbCR = var2;
      this.zzbCS = var3;
   }

   public zzctn() {
      this(0, (Intent)null);
   }

   private zzctn(int var1, Intent var2) {
      this(2, 0, (Intent)null);
   }

   public final Status getStatus() {
      return this.zzbCR == 0 ? Status.zzaBm : Status.zzaBq;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zzc(var1, 2, this.zzbCR);
      zzd.zza(var1, 3, this.zzbCS, var2, false);
      zzd.zzI(var1, var5);
   }
}
