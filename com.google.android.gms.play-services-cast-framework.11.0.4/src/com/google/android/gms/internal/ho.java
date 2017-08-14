package com.google.android.gms.internal;

import android.os.Looper;
import android.os.Build.VERSION;

final class ho extends ThreadLocal {
   // $FF: synthetic method
   protected final Object initialValue() {
      if (VERSION.SDK_INT >= 16) {
         return new ht();
      } else {
         Looper var1;
         if ((var1 = Looper.myLooper()) == null) {
            throw new IllegalStateException("The current thread must have a looper!");
         } else {
            return new hs(var1);
         }
      }
   }
}
