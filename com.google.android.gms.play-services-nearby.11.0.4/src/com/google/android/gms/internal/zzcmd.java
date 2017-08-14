package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzcmd extends zzcmj {
   // $FF: synthetic field
   private String zzbxb;
   // $FF: synthetic field
   private zzbdw zzbxo;

   zzcmd(zzclm var1, GoogleApiClient var2, String var3, zzbdw var4) {
      this.zzbxb = var3;
      this.zzbxo = var4;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      zzbdw var6 = this.zzbxo;
      String var5 = this.zzbxb;
      ((zzcnd)var3.zzrf()).zza(new zzcki((new zzclj(this)).asBinder(), (IBinder)null, var5, (byte[])null, (new zzclg(var6)).asBinder()));
   }
}
