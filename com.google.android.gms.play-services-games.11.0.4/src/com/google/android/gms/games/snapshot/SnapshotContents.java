package com.google.android.gms.games.snapshot;

import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.IOException;

public interface SnapshotContents extends Parcelable {
   ParcelFileDescriptor getParcelFileDescriptor();

   com.google.android.gms.drive.zzc zzsM();

   void close();

   boolean isClosed();

   byte[] readFully() throws IOException;

   boolean writeBytes(byte[] var1);

   boolean modifyBytes(int var1, byte[] var2, int var3, int var4);
}
