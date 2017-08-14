package com.google.android.gms.internal;

import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

final class zzcci extends zzccm {
   // $FF: synthetic field
   private LocationRequest zzbiD;
   // $FF: synthetic field
   private LocationCallback zzbiF;
   // $FF: synthetic field
   private Looper zzbiI;

   zzcci(zzccb var1, GoogleApiClient var2, LocationRequest var3, LocationCallback var4, Looper var5) {
      this.zzbiD = var3;
      this.zzbiF = var4;
      this.zzbiI = var5;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      zzccn var4 = new zzccn(this);
      var3.zza((zzcdn)zzcdn.zza(this.zzbiD), (zzbdw)zzbea.zzb(this.zzbiF, zzceb.zzb(this.zzbiI), LocationCallback.class.getSimpleName()), (zzccu)var4);
   }
}
