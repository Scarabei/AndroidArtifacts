package com.google.android.gms.internal;

import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.util.zzo;
import java.util.Arrays;
import org.json.JSONObject;

public final class zzaxo implements PlayerInfo {
   private final String zzaxn;
   private final int zzaqz;
   private final JSONObject zzaxD;
   private final boolean zzaxE;

   public zzaxo(String var1, int var2, JSONObject var3, boolean var4) {
      this.zzaxn = var1;
      this.zzaqz = var2;
      this.zzaxD = var3;
      this.zzaxE = var4;
   }

   public final int getPlayerState() {
      return this.zzaqz;
   }

   public final JSONObject getPlayerData() {
      return this.zzaxD;
   }

   public final String getPlayerId() {
      return this.zzaxn;
   }

   public final boolean isConnected() {
      switch(this.zzaqz) {
      case 3:
      case 4:
      case 5:
      case 6:
         return true;
      default:
         return false;
      }
   }

   public final boolean isControllable() {
      return this.zzaxE;
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1 instanceof PlayerInfo) {
         PlayerInfo var2 = (PlayerInfo)var1;
         return this.zzaxE == var2.isControllable() && this.zzaqz == var2.getPlayerState() && zzaye.zza(this.zzaxn, var2.getPlayerId()) && zzo.zzc(this.zzaxD, var2.getPlayerData());
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaxn, this.zzaqz, this.zzaxD, this.zzaxE});
   }
}
