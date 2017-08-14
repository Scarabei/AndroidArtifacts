package com.google.android.gms.wearable;

public interface MessageEvent {
   int getRequestId();

   String getPath();

   byte[] getData();

   String getSourceNodeId();
}
