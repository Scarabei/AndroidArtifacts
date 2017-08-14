package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

public interface zzz extends IInterface {
   Tile getTile(int var1, int var2, int var3) throws RemoteException;
}
