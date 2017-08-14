package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.Strategy;

final class zzclr extends zzcmh {
   // $FF: synthetic field
   private String val$name;
   // $FF: synthetic field
   private long zzbxc;
   // $FF: synthetic field
   private zzbdw zzbxd;

   zzclr(zzclm var1, GoogleApiClient var2, String var3, long var4, zzbdw var6) {
      this.val$name = var3;
      this.zzbxc = var4;
      this.zzbxd = var6;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      String var10002 = this.val$name;
      long var10004 = this.zzbxc;
      zzbdw var10005 = this.zzbxd;
      AdvertisingOptions var10 = new AdvertisingOptions(Strategy.P2P_CLUSTER);
      zzbdw var9 = var10005;
      long var7 = var10004;
      String var6 = "__LEGACY_SERVICE_ID__";
      String var5 = var10002;
      ((zzcnd)var3.zzrf()).zza(new zzcox((new zzcll(this)).asBinder(), (new zzcks(var9)).asBinder(), var5, var6, var7, var10, (IBinder)null));
   }
}
