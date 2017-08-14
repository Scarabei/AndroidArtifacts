package com.google.android.gms.games.event;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class EventBuffer extends AbstractDataBuffer {
   public EventBuffer(DataHolder var1) {
      super(var1);
   }

   public final Event get(int var1) {
      return new EventRef(this.zzaCX, var1);
   }
}
