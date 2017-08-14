package com.google.android.gms.cast.games;

import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

public interface GameManagerState {
   int getLobbyState();

   int getGameplayState();

   JSONObject getGameData();

   CharSequence getGameStatusText();

   CharSequence getApplicationName();

   int getMaxPlayers();

   List getPlayersInState(int var1);

   PlayerInfo getPlayer(String var1);

   Collection getPlayers();

   List getControllablePlayers();

   List getConnectedPlayers();

   List getConnectedControllablePlayers();

   boolean hasLobbyStateChanged(GameManagerState var1);

   boolean hasGameplayStateChanged(GameManagerState var1);

   boolean hasGameDataChanged(GameManagerState var1);

   boolean hasGameStatusTextChanged(GameManagerState var1);

   boolean hasPlayerChanged(String var1, GameManagerState var2);

   boolean hasPlayerStateChanged(String var1, GameManagerState var2);

   boolean hasPlayerDataChanged(String var1, GameManagerState var2);

   Collection getListOfChangedPlayers(GameManagerState var1);
}
