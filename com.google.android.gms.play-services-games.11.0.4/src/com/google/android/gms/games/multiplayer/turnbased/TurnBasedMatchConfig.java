package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;

public abstract class TurnBasedMatchConfig {
   public abstract int getVariant();

   public abstract int zzvs();

   public abstract String[] getInvitedPlayerIds();

   public abstract Bundle getAutoMatchCriteria();

   public static TurnBasedMatchConfig.Builder builder() {
      return new TurnBasedMatchConfig.Builder((zza)null);
   }

   public static Bundle createAutoMatchCriteria(int var0, int var1, long var2) {
      Bundle var4;
      (var4 = new Bundle()).putInt("min_automatch_players", var0);
      var4.putInt("max_automatch_players", var1);
      var4.putLong("exclusive_bit_mask", var2);
      return var4;
   }

   public static final class Builder {
      int zzbdu;
      ArrayList zzbdK;
      Bundle zzbdL;
      int zzbdU;

      private Builder() {
         this.zzbdu = -1;
         this.zzbdK = new ArrayList();
         this.zzbdL = null;
         this.zzbdU = 2;
      }

      public final TurnBasedMatchConfig.Builder addInvitedPlayer(String var1) {
         zzbo.zzu(var1);
         this.zzbdK.add(var1);
         return this;
      }

      public final TurnBasedMatchConfig.Builder addInvitedPlayers(ArrayList var1) {
         zzbo.zzu(var1);
         this.zzbdK.addAll(var1);
         return this;
      }

      public final TurnBasedMatchConfig.Builder setVariant(int var1) {
         zzbo.zzb(var1 == -1 || var1 > 0, "Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
         this.zzbdu = var1;
         return this;
      }

      public final TurnBasedMatchConfig.Builder setAutoMatchCriteria(Bundle var1) {
         this.zzbdL = var1;
         return this;
      }

      public final TurnBasedMatchConfig build() {
         return new zzb(this);
      }

      // $FF: synthetic method
      Builder(zza var1) {
         this();
      }
   }
}
