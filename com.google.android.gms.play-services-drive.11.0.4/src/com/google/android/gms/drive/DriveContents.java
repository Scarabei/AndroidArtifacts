package com.google.android.gms.drive;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import java.io.InputStream;
import java.io.OutputStream;

public interface DriveContents {
   DriveId getDriveId();

   int getMode();

   ParcelFileDescriptor getParcelFileDescriptor();

   InputStream getInputStream();

   OutputStream getOutputStream();

   zzc zzsM();

   void zzsN();

   boolean zzsO();

   PendingResult reopenForWrite(GoogleApiClient var1);

   PendingResult commit(GoogleApiClient var1, MetadataChangeSet var2);

   PendingResult commit(GoogleApiClient var1, MetadataChangeSet var2, ExecutionOptions var3);

   void discard(GoogleApiClient var1);
}
