package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;

public final class zzb extends TurnBasedMatchConfig {
   private final int zzbdu;
   private final String[] zzbdM;
   private final Bundle zzbdL;
   private final int zzbdU;

   zzb(TurnBasedMatchConfig.Builder var1) {
      this.zzbdu = var1.zzbdu;
      this.zzbdU = var1.zzbdU;
      this.zzbdL = var1.zzbdL;
      int var2 = var1.zzbdK.size();
      this.zzbdM = (String[])var1.zzbdK.toArray(new String[var2]);
   }

   public final int getVariant() {
      return this.zzbdu;
   }

   public final int zzvs() {
      return this.zzbdU;
   }

   public final String[] getInvitedPlayerIds() {
      return this.zzbdM;
   }

   public final Bundle getAutoMatchCriteria() {
      return this.zzbdL;
   }
}
