package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.nearby.connection.DiscoveryOptions;

final class zzcma extends zzcmj {
   // $FF: synthetic field
   private String zzbxe;
   // $FF: synthetic field
   private zzbdw zzbxm;
   // $FF: synthetic field
   private DiscoveryOptions zzbxn;

   zzcma(zzclm var1, GoogleApiClient var2, String var3, zzbdw var4, DiscoveryOptions var5) {
      this.zzbxe = var3;
      this.zzbxm = var4;
      this.zzbxn = var5;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      DiscoveryOptions var7 = this.zzbxn;
      zzbdw var6 = this.zzbxm;
      String var5 = this.zzbxe;
      ((zzcnd)var3.zzrf()).zza(new zzcoz((new zzclj(this)).asBinder(), (IBinder)null, var5, 0L, var7, (new zzckw(var6)).asBinder()));
   }
}
