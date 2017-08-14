package com.google.android.gms.nearby.messages;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface NearbyPermissions {
   int NONE = 0;
   int DEFAULT = -1;
   int MICROPHONE = 1;
   int BLE = 2;
   int BLUETOOTH = 6;
}
