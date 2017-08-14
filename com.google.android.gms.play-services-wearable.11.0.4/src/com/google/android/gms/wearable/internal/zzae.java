package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.internal.zzbaz;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.wearable.ChannelApi;

final class zzae implements zzc {
   // $FF: synthetic field
   private IntentFilter[] zzbRX;

   zzae(IntentFilter[] var1) {
      this.zzbRX = var1;
      super();
   }

   // $FF: synthetic method
   public final void zza(zzfw var1, zzbaz var2, Object var3, zzbdw var4) throws RemoteException {
      ChannelApi.ChannelListener var8 = (ChannelApi.ChannelListener)var3;
      var1.zza(var2, var8, var4, (String)null, this.zzbRX);
   }
}
