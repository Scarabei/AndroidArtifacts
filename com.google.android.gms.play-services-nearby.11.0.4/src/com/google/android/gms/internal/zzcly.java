package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.nearby.connection.AdvertisingOptions;

final class zzcly extends zzcmh {
   // $FF: synthetic field
   private String val$name;
   // $FF: synthetic field
   private String zzbxe;
   // $FF: synthetic field
   private zzbdw zzbxk;
   // $FF: synthetic field
   private AdvertisingOptions zzbxl;

   zzcly(zzclm var1, GoogleApiClient var2, String var3, String var4, zzbdw var5, AdvertisingOptions var6) {
      this.val$name = var3;
      this.zzbxe = var4;
      this.zzbxk = var5;
      this.zzbxl = var6;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      AdvertisingOptions var8 = this.zzbxl;
      zzbdw var7 = this.zzbxk;
      String var6 = this.zzbxe;
      String var5 = this.val$name;
      ((zzcnd)var3.zzrf()).zza(new zzcox((new zzcll(this)).asBinder(), (IBinder)null, var5, var6, 0L, var8, (new zzcko(var7)).asBinder()));
   }
}
