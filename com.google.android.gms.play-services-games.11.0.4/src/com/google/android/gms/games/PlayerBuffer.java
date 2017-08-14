package com.google.android.gms.games;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class PlayerBuffer extends AbstractDataBuffer {
   public PlayerBuffer(DataHolder var1) {
      super(var1);
   }

   public final Player get(int var1) {
      return new PlayerRef(this.zzaCX, var1);
   }
}
