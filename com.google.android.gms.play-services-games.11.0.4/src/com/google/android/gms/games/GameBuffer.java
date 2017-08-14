package com.google.android.gms.games;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class GameBuffer extends AbstractDataBuffer {
   public GameBuffer(DataHolder var1) {
      super(var1);
   }

   public final Game get(int var1) {
      return new GameRef(this.zzaCX, var1);
   }
}
