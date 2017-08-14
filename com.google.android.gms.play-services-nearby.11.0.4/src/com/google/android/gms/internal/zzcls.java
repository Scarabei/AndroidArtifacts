package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.Strategy;

final class zzcls extends zzcmj {
   // $FF: synthetic field
   private String zzbxe;
   // $FF: synthetic field
   private long zzbxc;
   // $FF: synthetic field
   private zzbdw zzbxf;

   zzcls(zzclm var1, GoogleApiClient var2, String var3, long var4, zzbdw var6) {
      this.zzbxe = var3;
      this.zzbxc = var4;
      this.zzbxf = var6;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      String var10002 = this.zzbxe;
      long var10003 = this.zzbxc;
      zzbdw var10004 = this.zzbxf;
      DiscoveryOptions var9 = new DiscoveryOptions(Strategy.P2P_CLUSTER);
      zzbdw var8 = var10004;
      long var6 = var10003;
      String var5 = var10002;
      ((zzcnd)var3.zzrf()).zza(new zzcoz((new zzclj(this)).asBinder(), (IBinder)null, var5, var6, var9, (new zzckz(var8)).asBinder()));
   }
}
