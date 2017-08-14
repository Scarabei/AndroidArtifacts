package com.google.android.gms.internal;

import android.database.ContentObserver;
import android.os.Handler;

final class hj extends ContentObserver {
   hj(Handler var1) {
      super((Handler)null);
   }

   public final void onChange(boolean var1) {
      hi.zzEc().set(true);
   }
}
