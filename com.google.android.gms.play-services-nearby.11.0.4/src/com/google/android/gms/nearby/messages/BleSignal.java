package com.google.android.gms.nearby.messages;

public interface BleSignal {
   int UNKNOWN_TX_POWER = Integer.MIN_VALUE;

   int getRssi();

   int getTxPower();
}
