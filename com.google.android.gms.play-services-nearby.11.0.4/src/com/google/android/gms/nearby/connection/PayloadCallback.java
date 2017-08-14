package com.google.android.gms.nearby.connection;

public abstract class PayloadCallback {
   public abstract void onPayloadReceived(String var1, Payload var2);

   public abstract void onPayloadTransferUpdate(String var1, PayloadTransferUpdate var2);
}
