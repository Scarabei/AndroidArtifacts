package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.location.zzj;
import com.google.android.gms.location.zzk;
import com.google.android.gms.location.zzm;
import com.google.android.gms.location.zzn;

public final class zzcdp extends zza {
   private int zzbja;
   private zzcdn zzbjb;
   private zzm zzbjc;
   private PendingIntent mPendingIntent;
   private zzj zzbjd;
   private zzccu zzbje;
   public static final Creator CREATOR = new zzcdq();

   public static zzcdp zza(zzm var0, @Nullable zzccu var1) {
      return new zzcdp(2, (zzcdn)null, var0.asBinder(), (PendingIntent)null, (IBinder)null, var1 != null ? var1.asBinder() : null);
   }

   public static zzcdp zza(zzj var0, @Nullable zzccu var1) {
      return new zzcdp(2, (zzcdn)null, (IBinder)null, (PendingIntent)null, var0.asBinder(), var1 != null ? var1.asBinder() : null);
   }

   zzcdp(int var1, zzcdn var2, IBinder var3, PendingIntent var4, IBinder var5, IBinder var6) {
      this.zzbja = var1;
      this.zzbjb = var2;
      this.zzbjc = var3 == null ? null : zzn.zzZ(var3);
      this.mPendingIntent = var4;
      this.zzbjd = var5 == null ? null : zzk.zzY(var5);
      IInterface var8;
      this.zzbje = (zzccu)(var6 == null ? null : (var6 == null ? null : ((var8 = var6.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback")) instanceof zzccu ? (zzccu)var8 : new zzccw(var6))));
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzbja);
      zzd.zza(var1, 2, this.zzbjb, var2, false);
      zzd.zza(var1, 3, this.zzbjc == null ? null : this.zzbjc.asBinder(), false);
      zzd.zza(var1, 4, this.mPendingIntent, var2, false);
      zzd.zza(var1, 5, this.zzbjd == null ? null : this.zzbjd.asBinder(), false);
      zzd.zza(var1, 6, this.zzbje == null ? null : this.zzbje.asBinder(), false);
      zzd.zzI(var1, var5);
   }
}
