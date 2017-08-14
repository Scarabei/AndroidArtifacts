package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.zzaq;

final class zzbyw extends zzbvn {
   // $FF: synthetic field
   private zzbyx zzaVZ;
   // $FF: synthetic field
   private zzt zzaWa;
   // $FF: synthetic field
   private PendingIntent zzaVL;

   zzbyw(zzbys var1, GoogleApiClient var2, zzbyx var3, zzt var4, PendingIntent var5) {
      this.zzaVZ = var3;
      this.zzaWa = var4;
      this.zzaVL = var5;
      super(var2);
   }

   protected final Status zzA(Status var1) {
      return var1;
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzbvk var3 = (zzbvk)var1;
      zzbyy var4 = new zzbyy(this, this.zzaVZ, (zzbyt)null);
      ((zzbwt)var3.zzrf()).zza(new zzaq(this.zzaWa, this.zzaVL, var4));
   }

   // $FF: synthetic method
   protected final Result zzb(Status var1) {
      return var1;
   }
}
