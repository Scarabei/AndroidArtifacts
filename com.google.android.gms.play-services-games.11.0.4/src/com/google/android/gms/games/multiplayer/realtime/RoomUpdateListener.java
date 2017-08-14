package com.google.android.gms.games.multiplayer.realtime;

public interface RoomUpdateListener {
   void onRoomCreated(int var1, Room var2);

   void onJoinedRoom(int var1, Room var2);

   void onLeftRoom(int var1, String var2);

   void onRoomConnected(int var1, Room var2);
}
