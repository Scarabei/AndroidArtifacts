package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class Response {
   private Result zzaBj;

   public Response() {
   }

   protected Response(@NonNull Result var1) {
      this.zzaBj = var1;
   }

   @NonNull
   protected Result getResult() {
      return this.zzaBj;
   }

   public void setResult(@NonNull Result var1) {
      this.zzaBj = var1;
   }
}
