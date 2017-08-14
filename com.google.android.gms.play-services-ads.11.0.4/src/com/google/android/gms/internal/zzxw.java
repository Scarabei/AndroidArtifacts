package com.google.android.gms.internal;

public final class zzxw extends Exception {
   private final int mErrorCode;

   public zzxw(String var1, int var2) {
      super(var1);
      this.mErrorCode = var2;
   }

   public final int getErrorCode() {
      return this.mErrorCode;
   }
}
