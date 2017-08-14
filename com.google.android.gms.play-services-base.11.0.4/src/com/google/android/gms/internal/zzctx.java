package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbr;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzctx extends zza {
   public static final Creator CREATOR = new zzcty();
   private int zzaku;
   private final ConnectionResult zzaBQ;
   private final zzbr zzbCV;

   zzctx(int var1, ConnectionResult var2, zzbr var3) {
      this.zzaku = var1;
      this.zzaBQ = var2;
      this.zzbCV = var3;
   }

   public zzctx(int var1) {
      this(new ConnectionResult(8, (PendingIntent)null), (zzbr)null);
   }

   private zzctx(ConnectionResult var1, zzbr var2) {
      this(1, var1, (zzbr)null);
   }

   public final ConnectionResult zzpz() {
      return this.zzaBQ;
   }

   public final zzbr zzAx() {
      return this.zzbCV;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zza(var1, 2, this.zzaBQ, var2, false);
      zzd.zza(var1, 3, this.zzbCV, var2, false);
      zzd.zzI(var1, var5);
   }
}
