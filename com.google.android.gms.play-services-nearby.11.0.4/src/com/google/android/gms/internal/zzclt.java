package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;

final class zzclt extends zzcmj {
   // $FF: synthetic field
   private String val$name;
   // $FF: synthetic field
   private String zzbxb;
   // $FF: synthetic field
   private byte[] zzbxg;
   // $FF: synthetic field
   private zzbdw zzbxh;
   // $FF: synthetic field
   private zzbdw zzbxi;

   zzclt(zzclm var1, GoogleApiClient var2, String var3, String var4, byte[] var5, zzbdw var6, zzbdw var7) {
      this.val$name = var3;
      this.zzbxb = var4;
      this.zzbxg = var5;
      this.zzbxh = var6;
      this.zzbxi = var7;
      super(var2, (zzcln)null);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzckm var3 = (zzckm)var1;
      zzbdw var9 = this.zzbxi;
      zzbdw var8 = this.zzbxh;
      byte[] var7 = this.zzbxg;
      String var6 = this.zzbxb;
      String var5 = this.val$name;
      ((zzcnd)var3.zzrf()).zza(new zzcot((new zzclj(this)).asBinder(), (new zzclc(var9)).asBinder(), (new zzcku(var8)).asBinder(), var5, var6, var7, (IBinder)null));
   }
}
