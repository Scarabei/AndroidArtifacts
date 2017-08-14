package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.DowngradeableSafeParcel;

public abstract class GamesDowngradeableSafeParcel extends DowngradeableSafeParcel {
   protected static boolean zze(Integer var0) {
      if (var0 == null) {
         return false;
      } else {
         return var0.intValue() >= 3200000;
      }
   }
}
