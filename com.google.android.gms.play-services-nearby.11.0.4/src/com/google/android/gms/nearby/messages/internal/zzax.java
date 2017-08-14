package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.nearby.messages.Strategy;

public final class zzax extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzay();
   private int zzaku;
   private zzaf zzbzp;
   private Strategy zzbzq;
   private zzp zzbza;
   /** @deprecated */
   @Deprecated
   private String zzbye;
   /** @deprecated */
   @Deprecated
   private String zzbyW;
   /** @deprecated */
   @Deprecated
   private boolean zzbzr;
   private zzu zzbzs;
   /** @deprecated */
   @Deprecated
   private boolean zzbyf;
   /** @deprecated */
   @Deprecated
   private ClientAppContext zzbzb;

   zzax(int var1, zzaf var2, Strategy var3, IBinder var4, String var5, String var6, boolean var7, IBinder var8, boolean var9, ClientAppContext var10) {
      this.zzaku = var1;
      this.zzbzp = var2;
      this.zzbzq = var3;
      IInterface var12;
      this.zzbza = (zzp)(var4 == null ? null : ((var12 = var4.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback")) instanceof zzp ? (zzp)var12 : new zzr(var4)));
      this.zzbye = var5;
      this.zzbyW = var6;
      this.zzbzr = var7;
      this.zzbzs = (zzu)(var8 == null ? null : (var8 == null ? null : ((var12 = var8.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IPublishCallback")) instanceof zzu ? (zzu)var12 : new zzw(var8))));
      this.zzbyf = var9;
      this.zzbzb = ClientAppContext.zza(var10, var6, var5, var9);
   }

   public zzax(zzaf var1, Strategy var2, IBinder var3, IBinder var4) {
      this(2, var1, var2, var3, (String)null, (String)null, false, var4, false, (ClientAppContext)null);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbzp, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbzq, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbza.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbye, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbyW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzbzr);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbzs == null ? null : this.zzbzs.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbyf);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzbzb, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
