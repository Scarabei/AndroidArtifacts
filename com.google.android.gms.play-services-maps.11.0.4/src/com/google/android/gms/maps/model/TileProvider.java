package com.google.android.gms.maps.model;

public interface TileProvider {
   Tile NO_TILE = new Tile(-1, -1, (byte[])null);

   Tile getTile(int var1, int var2, int var3);
}
