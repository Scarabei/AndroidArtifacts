package com.google.android.gms.awareness.state;

public interface HeadphoneState {
   int PLUGGED_IN = 1;
   int UNPLUGGED = 2;

   int getState();
}
