package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api.zzb;
import java.util.ArrayList;

final class zzcsd extends zzcsa.zzf {
   // $FF: synthetic field
   private int[] zzbBS;
   // $FF: synthetic field
   private int zzbBT;
   // $FF: synthetic field
   private String zzbBR;
   // $FF: synthetic field
   private String zzbBP;

   zzcsd(GoogleApiClient var1, int[] var2, int var3, String var4, String var5) {
      this.zzbBS = var2;
      this.zzbBT = var3;
      this.zzbBR = var4;
      this.zzbBP = var5;
      super(var1);
   }

   // $FF: synthetic method
   protected final void zza(zzb var1) throws RemoteException {
      zzcsn var3 = (zzcsn)var1;
      ArrayList var4 = new ArrayList();
      int[] var5 = this.zzbBS;
      int var6 = this.zzbBS.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         int var8 = var5[var7];
         var4.add(var8);
      }

      var3.zza(this.zzbBW, var4, this.zzbBT, this.zzbBR, this.zzbBP);
   }
}
