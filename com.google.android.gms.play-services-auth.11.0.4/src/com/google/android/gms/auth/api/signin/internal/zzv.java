package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;

public final class zzv extends zzq {
   private final Context mContext;

   public zzv(Context var1) {
      this.mContext = var1;
   }

   public final void zzmK() {
      if (!GooglePlayServicesUtil.zzf(this.mContext, Binder.getCallingUid())) {
         int var9 = Binder.getCallingUid();
         throw new SecurityException((new StringBuilder(52)).append("Calling UID ").append(var9).append(" is not Google Play services.").toString());
      } else {
         zzy var2;
         GoogleSignInAccount var3 = (var2 = zzy.zzaj(this.mContext)).zzmN();
         GoogleSignInOptions var4 = GoogleSignInOptions.DEFAULT_SIGN_IN;
         if (var3 != null) {
            var4 = var2.zzmO();
         }

         GoogleApiClient var5 = (new Builder(this.mContext)).addApi(Auth.GOOGLE_SIGN_IN_API, var4).build();

         try {
            if (var5.blockingConnect().isSuccess()) {
               if (var3 != null) {
                  Auth.GoogleSignInApi.revokeAccess(var5);
               } else {
                  var5.clearDefaultAccountAndReconnect();
               }
            }
         } finally {
            var5.disconnect();
         }

      }
   }
}
