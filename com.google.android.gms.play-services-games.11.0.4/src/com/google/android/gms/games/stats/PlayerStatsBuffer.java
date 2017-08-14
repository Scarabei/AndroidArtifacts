package com.google.android.gms.games.stats;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class PlayerStatsBuffer extends AbstractDataBuffer {
   public PlayerStatsBuffer(DataHolder var1) {
      super(var1);
   }

   // $FF: synthetic method
   public final Object get(int var1) {
      return new zzc(this.zzaCX, var1);
   }
}
