package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

/** @deprecated */
@Deprecated
public final class zzh extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzi();
   private int zzaku;
   private zzp zzbza;
   /** @deprecated */
   @Deprecated
   private String zzbye;
   /** @deprecated */
   @Deprecated
   private ClientAppContext zzbzb;

   zzh(int var1, IBinder var2, String var3, ClientAppContext var4) {
      this.zzaku = var1;
      IInterface var6;
      this.zzbza = (zzp)(var2 == null ? null : ((var6 = var2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback")) instanceof zzp ? (zzp)var6 : new zzr(var2)));
      this.zzbye = var3;
      this.zzbzb = ClientAppContext.zza(var4, (String)null, var3, false);
   }

   zzh(IBinder var1) {
      this(1, var1, (String)null, (ClientAppContext)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbza.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbye, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbzb, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
