package com.google.android.gms.internal;

import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

final class zzcch extends zzccm {
   // $FF: synthetic field
   private LocationRequest zzbiD;
   // $FF: synthetic field
   private LocationListener zzbiE;
   // $FF: synthetic field
   private Looper zzbiI;

   zzcch(zzccb var1, GoogleApiClient var2, LocationRequest var3, LocationListener var4, Looper var5) {
      this.zzbiD = var3;
      this.zzbiE = var4;
      this.zzbiI = var5;
      super(var2);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcdj var3 = (zzcdj)var1;
      zzccn var4 = new zzccn(this);
      var3.zza((LocationRequest)this.zzbiD, (zzbdw)zzbea.zzb(this.zzbiE, zzceb.zzb(this.zzbiI), LocationListener.class.getSimpleName()), (zzccu)var4);
   }
}
