package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.util.zzo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class zzaxn implements GameManagerState {
   private final int zzaxx;
   private final int zzaxw;
   private final String zzaxA;
   private final JSONObject zzaxz;
   private final String zzaxq;
   private final int zzaxr;
   private final Map zzaxC;

   public zzaxn(int var1, int var2, String var3, JSONObject var4, Collection var5, String var6, int var7) {
      this.zzaxx = var1;
      this.zzaxw = var2;
      this.zzaxA = var3;
      this.zzaxz = var4;
      this.zzaxq = var6;
      this.zzaxr = var7;
      this.zzaxC = new HashMap(var5.size());
      Iterator var8 = var5.iterator();

      while(var8.hasNext()) {
         PlayerInfo var9 = (PlayerInfo)var8.next();
         this.zzaxC.put(var9.getPlayerId(), var9);
      }

   }

   public final int getLobbyState() {
      return this.zzaxx;
   }

   public final int getGameplayState() {
      return this.zzaxw;
   }

   public final JSONObject getGameData() {
      return this.zzaxz;
   }

   public final CharSequence getGameStatusText() {
      return this.zzaxA;
   }

   public final CharSequence getApplicationName() {
      return this.zzaxq;
   }

   public final int getMaxPlayers() {
      return this.zzaxr;
   }

   public final List getPlayersInState(int var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.getPlayers().iterator();

      while(var3.hasNext()) {
         PlayerInfo var4;
         if ((var4 = (PlayerInfo)var3.next()).getPlayerState() == var1) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public final PlayerInfo getPlayer(String var1) {
      return var1 == null ? null : (PlayerInfo)this.zzaxC.get(var1);
   }

   public final Collection getPlayers() {
      return Collections.unmodifiableCollection(this.zzaxC.values());
   }

   public final List getControllablePlayers() {
      ArrayList var1 = new ArrayList();
      Iterator var2 = this.getPlayers().iterator();

      while(var2.hasNext()) {
         PlayerInfo var3;
         if ((var3 = (PlayerInfo)var2.next()).isControllable()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public final List getConnectedPlayers() {
      ArrayList var1 = new ArrayList();
      Iterator var2 = this.getPlayers().iterator();

      while(var2.hasNext()) {
         PlayerInfo var3;
         if ((var3 = (PlayerInfo)var2.next()).isConnected()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public final List getConnectedControllablePlayers() {
      ArrayList var1 = new ArrayList();
      Iterator var2 = this.getPlayers().iterator();

      while(var2.hasNext()) {
         PlayerInfo var3;
         if ((var3 = (PlayerInfo)var2.next()).isConnected() && var3.isControllable()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public final boolean hasLobbyStateChanged(GameManagerState var1) {
      return this.zzaxx != var1.getLobbyState();
   }

   public final boolean hasGameplayStateChanged(GameManagerState var1) {
      return this.zzaxw != var1.getGameplayState();
   }

   public final boolean hasGameDataChanged(GameManagerState var1) {
      return !zzo.zzc(this.zzaxz, var1.getGameData());
   }

   public final boolean hasGameStatusTextChanged(GameManagerState var1) {
      return !zzaye.zza(this.zzaxA, var1.getGameStatusText());
   }

   public final boolean hasPlayerChanged(String var1, GameManagerState var2) {
      return !zzaye.zza(this.getPlayer(var1), var2.getPlayer(var1));
   }

   public final boolean hasPlayerStateChanged(String var1, GameManagerState var2) {
      PlayerInfo var3 = this.getPlayer(var1);
      PlayerInfo var4 = var2.getPlayer(var1);
      if (var3 == null && var4 == null) {
         return false;
      } else if (var3 != null && var4 != null) {
         return var3.getPlayerState() != var4.getPlayerState();
      } else {
         return true;
      }
   }

   public final boolean hasPlayerDataChanged(String var1, GameManagerState var2) {
      PlayerInfo var3 = this.getPlayer(var1);
      PlayerInfo var4 = var2.getPlayer(var1);
      if (var3 == null && var4 == null) {
         return false;
      } else if (var3 != null && var4 != null) {
         return !zzo.zzc(var3.getPlayerData(), var4.getPlayerData());
      } else {
         return true;
      }
   }

   public final Collection getListOfChangedPlayers(GameManagerState var1) {
      HashSet var2 = new HashSet();
      Iterator var3 = this.getPlayers().iterator();

      while(true) {
         PlayerInfo var4;
         PlayerInfo var5;
         do {
            if (!var3.hasNext()) {
               var3 = var1.getPlayers().iterator();

               while(var3.hasNext()) {
                  var4 = (PlayerInfo)var3.next();
                  if (this.getPlayer(var4.getPlayerId()) == null) {
                     var2.add(var4.getPlayerId());
                  }
               }

               return var2;
            }

            var4 = (PlayerInfo)var3.next();
         } while((var5 = var1.getPlayer(var4.getPlayerId())) != null && var4.equals(var5));

         var2.add(var4.getPlayerId());
      }
   }

   public final boolean equals(Object var1) {
      if (var1 != null && var1 instanceof GameManagerState) {
         GameManagerState var2 = (GameManagerState)var1;
         if (this.getPlayers().size() != var2.getPlayers().size()) {
            return false;
         } else {
            Iterator var3 = this.getPlayers().iterator();

            boolean var5;
            do {
               if (!var3.hasNext()) {
                  if (this.zzaxx == var2.getLobbyState() && this.zzaxw == var2.getGameplayState() && this.zzaxr == var2.getMaxPlayers() && zzaye.zza(this.zzaxq, var2.getApplicationName()) && zzaye.zza(this.zzaxA, var2.getGameStatusText()) && zzo.zzc(this.zzaxz, var2.getGameData())) {
                     return true;
                  }

                  return false;
               }

               PlayerInfo var4 = (PlayerInfo)var3.next();
               var5 = false;
               Iterator var6 = var2.getPlayers().iterator();

               while(var6.hasNext()) {
                  PlayerInfo var7 = (PlayerInfo)var6.next();
                  if (zzaye.zza(var4.getPlayerId(), var7.getPlayerId())) {
                     if (!zzaye.zza(var4, var7)) {
                        return false;
                     }

                     var5 = true;
                  }
               }
            } while(var5);

            return false;
         }
      } else {
         return false;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaxx, this.zzaxw, this.zzaxC, this.zzaxA, this.zzaxz, this.zzaxq, this.zzaxr});
   }
}
