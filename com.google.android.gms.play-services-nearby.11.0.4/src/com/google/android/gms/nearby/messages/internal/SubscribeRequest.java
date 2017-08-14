package com.google.android.gms.nearby.messages.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.nearby.messages.MessageFilter;
import com.google.android.gms.nearby.messages.Strategy;

public final class SubscribeRequest extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzbb();
   private int zzaku;
   private zzm zzbzv;
   private Strategy zzbzq;
   private zzp zzbza;
   private MessageFilter zzbzw;
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
   private byte[] zzbzy;
   /** @deprecated */
   @Deprecated
   private boolean zzbzr;
   private zzaa zzbzz;
   /** @deprecated */
   @Deprecated
   private boolean zzbyf;
   /** @deprecated */
   @Deprecated
   private ClientAppContext zzbzb;
   private boolean zzbyA;
   private int zzbyB;

   public SubscribeRequest(int var1, IBinder var2, Strategy var3, IBinder var4, MessageFilter var5, PendingIntent var6, int var7, String var8, String var9, byte[] var10, boolean var11, IBinder var12, boolean var13, ClientAppContext var14, boolean var15, int var16) {
      this.zzaku = var1;
      IInterface var18;
      this.zzbzv = (zzm)(var2 == null ? null : ((var18 = var2.queryLocalInterface("com.google.android.gms.nearby.messages.internal.IMessageListener")) instanceof zzm ? (zzm)var18 : new zzo(var2)));
      this.zzbzq = var3;
      this.zzbza = (zzp)(var4 == null ? null : ((var18 = var4.queryLocalInterface("com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback")) instanceof zzp ? (zzp)var18 : new zzr(var4)));
      this.zzbzw = var5;
      this.zzaLr = var6;
      this.zzbzx = var7;
      this.zzbye = var8;
      this.zzbyW = var9;
      this.zzbzy = var10;
      this.zzbzr = var11;
      this.zzbzz = (zzaa)(var12 == null ? null : (var12 == null ? null : ((var18 = var12.queryLocalInterface("com.google.android.gms.nearby.messages.internal.ISubscribeCallback")) instanceof zzaa ? (zzaa)var18 : new zzac(var12))));
      this.zzbyf = var13;
      this.zzbzb = ClientAppContext.zza(var14, var9, var8, var13);
      this.zzbyA = var15;
      this.zzbyB = var16;
   }

   public SubscribeRequest(IBinder var1, Strategy var2, IBinder var3, MessageFilter var4, PendingIntent var5, byte[] var6, IBinder var7, boolean var8, int var9) {
      this(3, var1, var2, var3, var4, var5, 0, (String)null, (String)null, var6, false, var7, false, (ClientAppContext)null, var8, var9);
   }

   public SubscribeRequest(IBinder var1, Strategy var2, IBinder var3, MessageFilter var4, PendingIntent var5, byte[] var6, IBinder var7, boolean var8) {
      this(var1, var2, var3, var4, (PendingIntent)null, (byte[])null, var7, var8, 0);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbzv == null ? null : this.zzbzv.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbzq, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbza == null ? null : this.zzbza.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbzw, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzaLr, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzbzx);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzbye, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzbyW, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, this.zzbzy, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.zzbzr);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.zzbzz == null ? null : this.zzbzz.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 13, this.zzbyf);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.zzbzb, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.zzbyA);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 16, this.zzbyB);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzbzv);
      String var2 = String.valueOf(this.zzbzq);
      String var3 = String.valueOf(this.zzbza);
      String var4 = String.valueOf(this.zzbzw);
      String var5 = String.valueOf(this.zzaLr);
      String var10000;
      if (this.zzbzy == null) {
         var10000 = null;
      } else {
         int var6 = this.zzbzy.length;
         var10000 = (new StringBuilder(19)).append("<").append(var6).append(" bytes>").toString();
      }

      String var14 = var10000;
      String var7 = String.valueOf(this.zzbzz);
      boolean var8 = this.zzbyf;
      String var9 = String.valueOf(this.zzbzb);
      boolean var10 = this.zzbyA;
      String var11 = this.zzbye;
      String var12 = this.zzbyW;
      boolean var13 = this.zzbzr;
      return (new StringBuilder(263 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length() + String.valueOf(var5).length() + String.valueOf(var14).length() + String.valueOf(var7).length() + String.valueOf(var9).length() + String.valueOf(var11).length() + String.valueOf(var12).length())).append("SubscribeRequest{messageListener=").append(var1).append(", strategy=").append(var2).append(", callback=").append(var3).append(", filter=").append(var4).append(", pendingIntent=").append(var5).append(", hint=").append(var14).append(", subscribeCallback=").append(var7).append(", useRealClientApiKey=").append(var8).append(", clientAppContext=").append(var9).append(", isDiscardPendingIntent=").append(var10).append(", zeroPartyPackageName=").append(var11).append(", realClientPackageName=").append(var12).append(", isIgnoreNearbyPermission=").append(var13).append("}").toString();
   }
}
