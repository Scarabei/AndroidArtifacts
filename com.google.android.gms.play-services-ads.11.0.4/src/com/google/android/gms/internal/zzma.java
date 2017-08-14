package com.google.android.gms.internal;

import android.os.Environment;
import java.util.concurrent.Callable;

final class zzma implements Callable {
   // $FF: synthetic method
   public final Object call() throws Exception {
      return "mounted".equals(Environment.getExternalStorageState());
   }
}
