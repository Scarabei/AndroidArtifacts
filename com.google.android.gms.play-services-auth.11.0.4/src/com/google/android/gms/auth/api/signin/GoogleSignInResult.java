package com.google.android.gms.auth.api.signin;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public class GoogleSignInResult implements Result {
   private Status mStatus;
   private GoogleSignInAccount zzamg;

   public GoogleSignInResult(@Nullable GoogleSignInAccount var1, @NonNull Status var2) {
      this.zzamg = var1;
      this.mStatus = var2;
   }

   @Nullable
   public GoogleSignInAccount getSignInAccount() {
      return this.zzamg;
   }

   @NonNull
   public Status getStatus() {
      return this.mStatus;
   }

   public boolean isSuccess() {
      return this.mStatus.isSuccess();
   }
}
