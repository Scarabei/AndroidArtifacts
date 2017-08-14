package com.google.android.gms.internal;

import com.google.android.gms.awareness.fence.FenceQueryResult;
import com.google.android.gms.awareness.fence.FenceStateMap;
import com.google.android.gms.common.api.Status;

final class zzbjw implements FenceQueryResult {
   // $FF: synthetic field
   private Status zzakB;

   zzbjw(zzbjv var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final FenceStateMap getFenceStateMap() {
      return null;
   }

   public final Status getStatus() {
      return this.zzakB;
   }
}
