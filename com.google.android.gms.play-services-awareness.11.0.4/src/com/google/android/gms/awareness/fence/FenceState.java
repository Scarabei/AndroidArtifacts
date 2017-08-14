package com.google.android.gms.awareness.fence;

import android.content.Intent;
import com.google.android.gms.internal.zzbjd;

public abstract class FenceState extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int UNKNOWN = 0;
   public static final int FALSE = 1;
   public static final int TRUE = 2;

   public abstract int getCurrentState();

   public abstract int getPreviousState();

   public abstract long getLastFenceUpdateTimeMillis();

   public abstract String getFenceKey();

   public static FenceState extract(Intent var0) {
      return new zzbjd(var0.getIntExtra("context_fence_current_state", 0), var0.getLongExtra("context_fence_last_updated_time", 0L), var0.getStringExtra("context_fence_key"), var0.getIntExtra("context_fence_previous_state", 0));
   }
}
