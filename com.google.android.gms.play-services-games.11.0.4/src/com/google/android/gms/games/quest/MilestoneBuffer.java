package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.AbstractDataBuffer;

public final class MilestoneBuffer extends AbstractDataBuffer {
   public final Milestone get(int var1) {
      return new zzb(this.zzaCX, var1);
   }
}
