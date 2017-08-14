package com.google.android.gms.games.multiplayer;

import com.google.android.gms.games.Player;
import java.util.ArrayList;

public final class ParticipantUtils {
   public static String getParticipantId(ArrayList var0, String var1) {
      int var2 = 0;

      for(int var3 = var0.size(); var2 < var3; ++var2) {
         Participant var4;
         Player var5;
         if ((var5 = (var4 = (Participant)var0.get(var2)).getPlayer()) != null && var5.getPlayerId().equals(var1)) {
            return var4.getParticipantId();
         }
      }

      return null;
   }
}
