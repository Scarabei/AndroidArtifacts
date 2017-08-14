package com.google.android.gms.cast.games;

import org.json.JSONObject;

public interface PlayerInfo {
   int getPlayerState();

   JSONObject getPlayerData();

   String getPlayerId();

   boolean isConnected();

   boolean isControllable();
}
