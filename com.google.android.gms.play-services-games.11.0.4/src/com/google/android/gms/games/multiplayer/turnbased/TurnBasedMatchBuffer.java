package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

public final class TurnBasedMatchBuffer extends zzg {
   public TurnBasedMatchBuffer(DataHolder var1) {
      super(var1);
   }

   protected final String zzqS() {
      return "external_match_id";
   }

   // $FF: synthetic method
   protected final Object zzi(int var1, int var2) {
      return new zzd(this.zzaCX, var1, var2);
   }
}
