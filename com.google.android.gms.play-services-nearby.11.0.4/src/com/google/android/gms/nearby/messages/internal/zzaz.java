package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzaz extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzba();
   private int versionCode;
   private zzp zzbza;
   private zzx zzbzt;
   public boolean zzbzu;
   /** @deprecated */
   @Deprecated
   private String zzbye;
   /** @deprecated */
   @Deprecated
   private ClientAppContext zzbzb;

   zzaz(int var1, IBinder var2, IBinder var3, boolean var4, String var5, ClientAppContext var6) {
      this.versionCode = var1;
      IInterface var8;
      this.zzbza = (zzp)(var2 == null ? null : ((var8 = var2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback")) instanceof zzp ? (zzp)var8 : new zzr(var2)));
      this.zzbzt = (zzx)(var3 == null ? null : ((var8 = var3.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IStatusCallback")) instanceof zzx ? (zzx)var8 : new zzz(var3)));
      this.zzbzu = var4;
      this.zzbye = var5;
      this.zzbzb = ClientAppContext.zza(var6, (String)null, var5, false);
   }

   public zzaz(IBinder var1, IBinder var2) {
      this(1, var1, var2, false, (String)null, (ClientAppContext)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbza.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbzt.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbzu);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbye, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbzb, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
