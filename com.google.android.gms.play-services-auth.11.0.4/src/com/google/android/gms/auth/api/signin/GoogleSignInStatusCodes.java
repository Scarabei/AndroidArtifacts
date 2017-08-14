package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.CommonStatusCodes;

public final class GoogleSignInStatusCodes extends CommonStatusCodes {
   public static final int SIGN_IN_FAILED = 12500;
   public static final int SIGN_IN_CANCELLED = 12501;

   public static String getStatusCodeString(int var0) {
      switch(var0) {
      case 12500:
         return "A non-recoverable sign in failure occurred";
      default:
         return CommonStatusCodes.getStatusCodeString(var0);
      }
   }
}
