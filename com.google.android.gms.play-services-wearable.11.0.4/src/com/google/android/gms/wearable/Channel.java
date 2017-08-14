package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import java.io.InputStream;
import java.io.OutputStream;

public interface Channel extends Parcelable {
   String getNodeId();

   String getPath();

   PendingResult close(GoogleApiClient var1);

   PendingResult close(GoogleApiClient var1, int var2);

   PendingResult getInputStream(GoogleApiClient var1);

   PendingResult getOutputStream(GoogleApiClient var1);

   PendingResult receiveFile(GoogleApiClient var1, Uri var2, boolean var3);

   PendingResult sendFile(GoogleApiClient var1, Uri var2);

   PendingResult sendFile(GoogleApiClient var1, Uri var2, long var3, long var5);

   PendingResult addListener(GoogleApiClient var1, ChannelApi.ChannelListener var2);

   PendingResult removeListener(GoogleApiClient var1, ChannelApi.ChannelListener var2);

   public interface GetOutputStreamResult extends Releasable, Result {
      OutputStream getOutputStream();
   }

   public interface GetInputStreamResult extends Releasable, Result {
      InputStream getInputStream();
   }
}
