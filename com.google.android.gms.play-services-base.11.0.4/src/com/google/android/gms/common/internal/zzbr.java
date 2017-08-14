package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzbr extends zza {
   public static final Creator CREATOR = new zzbs();
   private int zzaku;
   private IBinder zzaIq;
   private ConnectionResult zzaBQ;
   private boolean zzaDm;
   private boolean zzaIr;

   zzbr(int var1, IBinder var2, ConnectionResult var3, boolean var4, boolean var5) {
      this.zzaku = var1;
      this.zzaIq = var2;
      this.zzaBQ = var3;
      this.zzaDm = var4;
      this.zzaIr = var5;
   }

   public final zzal zzrH() {
      IBinder var1 = this.zzaIq;
      if (this.zzaIq == null) {
         return null;
      } else {
         IInterface var2;
         return (zzal)((var2 = var1.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor")) instanceof zzal ? (zzal)var2 : new zzan(var1));
      }
   }

   public final ConnectionResult zzpz() {
      return this.zzaBQ;
   }

   public final boolean zzrI() {
      return this.zzaDm;
   }

   public final boolean zzrJ() {
      return this.zzaIr;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zza(var1, 2, this.zzaIq, false);
      zzd.zza(var1, 3, this.zzaBQ, var2, false);
      zzd.zza(var1, 4, this.zzaDm);
      zzd.zza(var1, 5, this.zzaIr);
      zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzbr)) {
         return false;
      } else {
         zzbr var2 = (zzbr)var1;
         return this.zzaBQ.equals(var2.zzaBQ) && this.zzrH().equals(var2.zzrH());
      }
   }
}
