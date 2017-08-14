package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;

final class zzcqh extends zzcqb {
   // $FF: synthetic field
   private Context zztF;
   // $FF: synthetic field
   private Uri zzbzR;
   // $FF: synthetic field
   private zzcqa zzbzS;

   zzcqh(Context var1, Uri var2, zzcqa var3) {
      this.zztF = var1;
      this.zzbzR = var2;
      this.zzbzS = var3;
      super();
   }

   public final void zza(int var1, Bundle var2, int var3, Intent var4) throws RemoteException {
      zzcqe.zzc(this.zztF, this.zzbzR);
      this.zzbzS.zza(var1, var2, var3, var4);
   }
}
