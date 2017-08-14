package com.google.android.gms.awareness.fence;

import java.util.Set;

public interface FenceStateMap {
   Set getFenceKeys();

   FenceState getFenceState(String var1);
}
