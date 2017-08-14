package com.google.android.gms.cast.games;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzawy;
import org.json.JSONObject;

public final class GameManagerClient {
   public static final int STATUS_INCORRECT_VERSION = 2150;
   public static final int STATUS_TOO_MANY_PLAYERS = 2151;
   public static final int PLAYER_STATE_UNKNOWN = 0;
   public static final int PLAYER_STATE_DROPPED = 1;
   public static final int PLAYER_STATE_QUIT = 2;
   public static final int PLAYER_STATE_AVAILABLE = 3;
   public static final int PLAYER_STATE_READY = 4;
   public static final int PLAYER_STATE_IDLE = 5;
   public static final int PLAYER_STATE_PLAYING = 6;
   public static final int LOBBY_STATE_UNKNOWN = 0;
   public static final int LOBBY_STATE_OPEN = 1;
   public static final int LOBBY_STATE_CLOSED = 2;
   public static final int GAMEPLAY_STATE_UNKNOWN = 0;
   public static final int GAMEPLAY_STATE_LOADING = 1;
   public static final int GAMEPLAY_STATE_RUNNING = 2;
   public static final int GAMEPLAY_STATE_PAUSED = 3;
   public static final int GAMEPLAY_STATE_SHOWING_INFO_SCREEN = 4;
   private final zzawy zzawQ;

   private GameManagerClient(zzawy var1) {
      this.zzawQ = var1;
   }

   public static PendingResult getInstanceFor(GoogleApiClient var0, String var1) throws IllegalArgumentException {
      zzawy var2 = new zzawy(var0, var1, Cast.CastApi);
      GameManagerClient var3 = new GameManagerClient(var2);
      return var2.zza(var3);
   }

   public final PendingResult sendPlayerAvailableRequest(JSONObject var1) throws IllegalStateException {
      return this.zza(this.getLastUsedPlayerId(), 3, var1);
   }

   public final PendingResult sendPlayerAvailableRequest(String var1, JSONObject var2) throws IllegalStateException {
      return this.zza(var1, 3, var2);
   }

   public final PendingResult sendPlayerReadyRequest(JSONObject var1) throws IllegalStateException {
      return this.zza(this.getLastUsedPlayerId(), 4, var1);
   }

   public final PendingResult sendPlayerReadyRequest(String var1, JSONObject var2) throws IllegalStateException {
      return this.zza(var1, 4, var2);
   }

   public final PendingResult sendPlayerPlayingRequest(JSONObject var1) throws IllegalStateException {
      return this.zza(this.getLastUsedPlayerId(), 6, var1);
   }

   public final PendingResult sendPlayerPlayingRequest(String var1, JSONObject var2) throws IllegalStateException {
      return this.zza(var1, 6, var2);
   }

   public final PendingResult sendPlayerIdleRequest(JSONObject var1) throws IllegalStateException {
      return this.zza(this.getLastUsedPlayerId(), 5, var1);
   }

   public final PendingResult sendPlayerIdleRequest(String var1, JSONObject var2) throws IllegalStateException {
      return this.zza(var1, 5, var2);
   }

   public final PendingResult sendPlayerQuitRequest(JSONObject var1) throws IllegalStateException {
      return this.zza(this.getLastUsedPlayerId(), 2, var1);
   }

   public final PendingResult sendPlayerQuitRequest(String var1, JSONObject var2) throws IllegalStateException {
      return this.zza(var1, 2, var2);
   }

   private final PendingResult zza(String var1, int var2, JSONObject var3) throws IllegalStateException {
      return this.zzawQ.zza(var1, var2, var3);
   }

   public final PendingResult sendGameRequest(JSONObject var1) throws IllegalStateException {
      return this.sendGameRequest(this.getLastUsedPlayerId(), var1);
   }

   public final PendingResult sendGameRequest(String var1, JSONObject var2) throws IllegalStateException {
      return this.zzawQ.sendGameRequest(var1, var2);
   }

   public final void sendGameMessage(JSONObject var1) throws IllegalStateException {
      this.sendGameMessage(this.getLastUsedPlayerId(), var1);
   }

   public final void sendGameMessage(String var1, JSONObject var2) throws IllegalStateException {
      this.zzawQ.sendGameMessage(var1, var2);
   }

   public final synchronized GameManagerState getCurrentState() throws IllegalStateException {
      return this.zzawQ.getCurrentState();
   }

   public final String getLastUsedPlayerId() throws IllegalStateException {
      return this.zzawQ.getLastUsedPlayerId();
   }

   public final void setListener(GameManagerClient.Listener var1) {
      this.zzawQ.setListener(var1);
   }

   public final void dispose() {
      this.zzawQ.dispose();
   }

   public final boolean isDisposed() {
      return this.zzawQ.isDisposed();
   }

   public final void setSessionLabel(String var1) {
      this.zzawQ.setSessionLabel(var1);
   }

   public interface GameManagerResult extends Result {
      String getPlayerId();

      long getRequestId();

      JSONObject getExtraMessageData();
   }

   public interface GameManagerInstanceResult extends Result {
      GameManagerClient getGameManagerClient();
   }

   public interface Listener {
      void onStateChanged(GameManagerState var1, GameManagerState var2);

      void onGameMessageReceived(String var1, JSONObject var2);
   }
}
