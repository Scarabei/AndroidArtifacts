package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;

public final class zzx extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzy();
   private int version;
   private int zzaHu;
   private int zzaHv;
   String zzaHw;
   IBinder zzaHx;
   Scope[] zzaHy;
   Bundle zzaHz;
   Account zzaHA;
   com.google.android.gms.common.zzc[] zzaHB;

   public zzx(int var1) {
      this.version = 3;
      this.zzaHv = com.google.android.gms.common.zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
      this.zzaHu = var1;
   }

   zzx(int var1, int var2, int var3, String var4, IBinder var5, Scope[] var6, Bundle var7, Account var8, com.google.android.gms.common.zzc[] var9) {
      this.version = var1;
      this.zzaHu = var2;
      this.zzaHv = var3;
      if ("com.google.android.gms".equals(var4)) {
         this.zzaHw = "com.google.android.gms";
      } else {
         this.zzaHw = var4;
      }

      if (var1 < 2) {
         Account var11 = null;
         if (var5 != null) {
            IInterface var13;
            var11 = zza.zza((zzal)(var5 == null ? null : ((var13 = var5.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor")) instanceof zzal ? (zzal)var13 : new zzan(var5))));
         }

         this.zzaHA = var11;
      } else {
         this.zzaHx = var5;
         this.zzaHA = var8;
      }

      this.zzaHy = var6;
      this.zzaHz = var7;
      this.zzaHB = var9;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.version);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzaHu);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.zzaHv);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, (String)this.zzaHw, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, (IBinder)this.zzaHx, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, (Parcelable[])this.zzaHy, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, (Bundle)this.zzaHz, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, (Parcelable)this.zzaHA, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 10, (Parcelable[])this.zzaHB, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
