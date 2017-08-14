package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.view.Choreographer.FrameCallback;

public abstract class hp {
   private Runnable zzbUt;
   private FrameCallback zzbUu;

   public abstract void doFrame(long var1);

   @TargetApi(16)
   final FrameCallback zzEe() {
      if (this.zzbUu == null) {
         this.zzbUu = new hq(this);
      }

      return this.zzbUu;
   }

   final Runnable zzEf() {
      if (this.zzbUt == null) {
         this.zzbUt = new hr(this);
      }

      return this.zzbUt;
   }
}
