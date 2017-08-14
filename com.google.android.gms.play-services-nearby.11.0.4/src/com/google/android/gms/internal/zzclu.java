package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzclu extends zzcmj {
   // $FF: synthetic field
   private String zzbxb;
   // $FF: synthetic field
   private byte[] zzbxg;
   // $FF: synthetic field
   private zzbdw zzbxi;

   zzclu(zzclm var1, GoogleApiClient var2, String var3, byte[] var4, zzbdw var5) {
      this.zzbxb = var3;
      this.zzbxg = var4;
      this.zzbxi = var5;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      zzbdw var7 = this.zzbxi;
      byte[] var6 = this.zzbxg;
      String var5 = this.zzbxb;
      ((zzcnd)var3.zzrf()).zza(new zzcki((new zzclj(this)).asBinder(), (new zzclc(var7)).asBinder(), var5, var6, (IBinder)null));
   }
}
