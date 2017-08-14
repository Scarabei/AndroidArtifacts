package com.google.android.gms.common.api;

public abstract class OptionalPendingResult extends PendingResult {
   public abstract boolean isDone();

   public abstract Result get();
}
