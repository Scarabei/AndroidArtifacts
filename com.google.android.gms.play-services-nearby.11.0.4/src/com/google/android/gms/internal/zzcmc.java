package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzcmc extends zzcmj {
   // $FF: synthetic field
   private String val$name;
   // $FF: synthetic field
   private String zzbxb;
   // $FF: synthetic field
   private zzbdw zzbxk;

   zzcmc(zzclm var1, GoogleApiClient var2, String var3, String var4, zzbdw var5) {
      this.val$name = var3;
      this.zzbxb = var4;
      this.zzbxk = var5;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      zzbdw var7 = this.zzbxk;
      String var6 = this.zzbxb;
      String var5 = this.val$name;
      ((zzcnd)var3.zzrf()).zza(new zzcot((new zzclj(this)).asBinder(), (IBinder)null, (IBinder)null, var5, var6, (byte[])null, (new zzcko(var7)).asBinder()));
   }
}
