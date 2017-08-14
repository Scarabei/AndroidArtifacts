package com.google.android.gms.games.achievement;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class AchievementBuffer extends AbstractDataBuffer {
   public AchievementBuffer(DataHolder var1) {
      super(var1);
   }

   public final Achievement get(int var1) {
      return new AchievementRef(this.zzaCX, var1);
   }
}
