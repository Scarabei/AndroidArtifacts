package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.VisibleForTesting;

public final class zzbe extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzbf();
   private int zzaku;
   private zzm zzbzv;
   private zzp zzbza;
   private PendingIntent zzaLr;
   /** @deprecated */
   @Deprecated
   private int zzbzx;
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

   @VisibleForTesting
   public zzbe(int var1, IBinder var2, IBinder var3, PendingIntent var4, int var5, String var6, String var7, boolean var8, ClientAppContext var9) {
      this.zzaku = var1;
      IInterface var11;
      this.zzbzv = (zzm)(var2 == null ? null : ((var11 = var2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IMessageListener")) instanceof zzm ? (zzm)var11 : new zzo(var2)));
      this.zzbza = (zzp)(var3 == null ? null : ((var11 = var3.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback")) instanceof zzp ? (zzp)var11 : new zzr(var3)));
      this.zzaLr = var4;
      this.zzbzx = var5;
      this.zzbye = var6;
      this.zzbyW = var7;
      this.zzbyf = var8;
      this.zzbzb = ClientAppContext.zza(var9, var7, var6, var8);
   }

   @VisibleForTesting
   public zzbe(IBinder var1, IBinder var2, PendingIntent var3) {
      this(1, var1, var2, var3, 0, (String)null, (String)null, false, (ClientAppContext)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbzv == null ? null : this.zzbzv.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbza.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaLr, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzbzx);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbye, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbyW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbyf);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbzb, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
