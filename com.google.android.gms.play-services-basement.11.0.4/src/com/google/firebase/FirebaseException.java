package com.google.firebase;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbo;

public class FirebaseException extends Exception {
   /** @deprecated */
   @Deprecated
   protected FirebaseException() {
   }

   public FirebaseException(@NonNull String var1) {
      super(zzbo.zzh(var1, "Detail message must not be empty"));
   }

   public FirebaseException(@NonNull String var1, Throwable var2) {
      super(zzbo.zzh(var1, "Detail message must not be empty"), var2);
   }
}
