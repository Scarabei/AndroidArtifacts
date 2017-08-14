package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public abstract class TransformedResult {
   @NonNull
   public abstract TransformedResult then(@NonNull ResultTransform var1);

   public abstract void andFinally(@NonNull ResultCallbacks var1);
}
