package com.google.android.gms.internal;

import com.google.android.gms.awareness.fence.FenceQueryResult;
import com.google.android.gms.awareness.fence.FenceStateMap;
import com.google.android.gms.common.api.Status;

final class zzbkf implements FenceQueryResult {
   // $FF: synthetic field
   private zzbjf zzaLN;
   // $FF: synthetic field
   private Status zzakB;

   zzbkf(zzbkd var1, zzbjf var2, Status var3) {
      this.zzaLN = var2;
      this.zzakB = var3;
      super();
   }

   public final FenceStateMap getFenceStateMap() {
      return this.zzaLN;
   }

   public final Status getStatus() {
      return this.zzakB;
   }
}
