package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource {
   private final zzn zzbMe = new zzn();

   public void setResult(Object var1) {
      this.zzbMe.setResult(var1);
   }

   public boolean trySetResult(Object var1) {
      return this.zzbMe.trySetResult(var1);
   }

   public void setException(@NonNull Exception var1) {
      this.zzbMe.setException(var1);
   }

   public boolean trySetException(@NonNull Exception var1) {
      return this.zzbMe.trySetException(var1);
   }

   @NonNull
   public Task getTask() {
      return this.zzbMe;
   }
}
