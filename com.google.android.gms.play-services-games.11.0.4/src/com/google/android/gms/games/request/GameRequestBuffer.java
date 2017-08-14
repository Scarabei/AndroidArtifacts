package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

/** @deprecated */
@Deprecated
public final class GameRequestBuffer extends zzg {
   public GameRequestBuffer(DataHolder var1) {
      super(var1);
   }

   protected final String zzqS() {
      return "external_request_id";
   }

   // $FF: synthetic method
   protected final Object zzi(int var1, int var2) {
      return new zzb(this.zzaCX, var1, var2);
   }
}
