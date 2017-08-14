package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzan;

final class zzbyu extends zzbvn {
   // $FF: synthetic field
   private SensorRequest zzaVV;
   // $FF: synthetic field
   private zzt zzaVW;
   // $FF: synthetic field
   private PendingIntent zzaVX;

   zzbyu(zzbys var1, GoogleApiClient var2, SensorRequest var3, zzt var4, PendingIntent var5) {
      this.zzaVV = var3;
      this.zzaVW = var4;
      this.zzaVX = var5;
      super(var2);
   }

   protected final Status zzA(Status var1) {
      return var1;
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvk var3 = (zzbvk)var1;
      zzbzi var4 = new zzbzi(this);
      ((zzbwt)var3.zzrf()).zza(new zzan(this.zzaVV, this.zzaVW, this.zzaVX, var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return var1;
   }
}
