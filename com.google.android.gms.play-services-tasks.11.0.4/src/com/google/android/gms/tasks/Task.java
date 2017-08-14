package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

public abstract class Task {
   public abstract boolean isComplete();

   public abstract boolean isSuccessful();

   public abstract Object getResult();

   public abstract Object getResult(@NonNull Class var1) throws Throwable;

   @Nullable
   public abstract Exception getException();

   @NonNull
   public abstract Task addOnSuccessListener(@NonNull OnSuccessListener var1);

   @NonNull
   public abstract Task addOnSuccessListener(@NonNull Executor var1, @NonNull OnSuccessListener var2);

   @NonNull
   public abstract Task addOnSuccessListener(@NonNull Activity var1, @NonNull OnSuccessListener var2);

   @NonNull
   public abstract Task addOnFailureListener(@NonNull OnFailureListener var1);

   @NonNull
   public abstract Task addOnFailureListener(@NonNull Executor var1, @NonNull OnFailureListener var2);

   @NonNull
   public abstract Task addOnFailureListener(@NonNull Activity var1, @NonNull OnFailureListener var2);

   @NonNull
   public Task addOnCompleteListener(@NonNull OnCompleteListener var1) {
      throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
   }

   @NonNull
   public Task addOnCompleteListener(@NonNull Executor var1, @NonNull OnCompleteListener var2) {
      throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
   }

   @NonNull
   public Task addOnCompleteListener(@NonNull Activity var1, @NonNull OnCompleteListener var2) {
      throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
   }

   @NonNull
   public Task continueWith(@NonNull Continuation var1) {
      throw new UnsupportedOperationException("continueWith is not implemented");
   }

   @NonNull
   public Task continueWith(@NonNull Executor var1, @NonNull Continuation var2) {
      throw new UnsupportedOperationException("continueWith is not implemented");
   }

   @NonNull
   public Task continueWithTask(@NonNull Continuation var1) {
      throw new UnsupportedOperationException("continueWithTask is not implemented");
   }

   @NonNull
   public Task continueWithTask(@NonNull Executor var1, @NonNull Continuation var2) {
      throw new UnsupportedOperationException("continueWithTask is not implemented");
   }
}
