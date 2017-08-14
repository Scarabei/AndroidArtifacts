package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzasr;
import com.google.android.gms.internal.zzbgb;
import com.google.android.gms.internal.zzeh;
import java.io.IOException;

final class zze implements zzi {
   // $FF: synthetic field
   private Account zzako;
   // $FF: synthetic field
   private String zzakp;
   // $FF: synthetic field
   private Bundle val$options;

   zze(Account var1, String var2, Bundle var3) {
      this.zzako = var1;
      this.zzakp = var2;
      this.val$options = var3;
      super();
   }

   // $FF: synthetic method
   public final Object zzy(IBinder var1) throws RemoteException, IOException, GoogleAuthException {
      Bundle var4;
      TokenData var5;
      if ((var5 = TokenData.zzd(var4 = (Bundle)zzd.zzm(zzeh.zza(var1).zza(this.zzako, this.zzakp, this.val$options)), "tokenDetails")) != null) {
         return var5;
      } else {
         String var6 = var4.getString("Error");
         Intent var7 = (Intent)var4.getParcelable("userRecoveryIntent");
         zzasr var8;
         zzasr var10 = var8 = zzasr.zzbW(var6);
         if (zzasr.zzamP.equals(var10) || zzasr.zzamY.equals(var10) || zzasr.zzanb.equals(var10) || zzasr.zzanc.equals(var10) || zzasr.zzamT.equals(var10) || zzasr.zzane.equals(var10) || zzasr.zzamI.equals(var10) || zzasr.zzanj.equals(var10) || zzasr.zzank.equals(var10) || zzasr.zzanl.equals(var10) || zzasr.zzanm.equals(var10) || zzasr.zzann.equals(var10) || zzasr.zzano.equals(var10) || zzasr.zzanq.equals(var10) || zzasr.zzani.equals(var10) || zzasr.zzanp.equals(var10)) {
            zzbgb var10000 = zzd.zzmp();
            Object[] var10002 = new Object[1];
            String var9 = String.valueOf(var8);
            var10002[0] = (new StringBuilder(31 + String.valueOf(var9).length())).append("isUserRecoverableError status: ").append(var9).toString();
            var10000.zzf("GoogleAuthUtil", var10002);
            throw new UserRecoverableAuthException(var6, var7);
         } else if (zzasr.zzamM.equals(var8) || zzasr.zzamN.equals(var8)) {
            throw new IOException(var6);
         } else {
            throw new GoogleAuthException(var6);
         }
      }
   }
}
