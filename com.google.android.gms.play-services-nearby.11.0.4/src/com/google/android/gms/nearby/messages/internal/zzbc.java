package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbc extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzbd();
   private int zzaku;
   private zzaf zzbzp;
   private zzp zzbza;
   /** @deprecated */
   @Deprecated
   private String zzbye;
   /** @deprecated */
   @Deprecated
   private String zzbyW;
   /** @deprecated */
   @Deprecated
   private boolean zzbyf;
   /** @deprecated */
   @Deprecated
   private ClientAppContext zzbzb;

   zzbc(int var1, zzaf var2, IBinder var3, String var4, String var5, boolean var6, ClientAppContext var7) {
      this.zzaku = var1;
      this.zzbzp = var2;
      IInterface var9;
      this.zzbza = (zzp)(var3 == null ? null : ((var9 = var3.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback")) instanceof zzp ? (zzp)var9 : new zzr(var3)));
      this.zzbye = var4;
      this.zzbyW = var5;
      this.zzbyf = var6;
      this.zzbzb = ClientAppContext.zza(var7, var5, var4, var6);
   }

   public zzbc(zzaf var1, IBinder var2, ClientAppContext var3) {
      this(1, var1, var2, (String)null, (String)null, false, var3);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbzp, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbza.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbye, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbyW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbyf);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbzb, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
