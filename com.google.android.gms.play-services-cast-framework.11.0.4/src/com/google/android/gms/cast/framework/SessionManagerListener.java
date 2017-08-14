package com.google.android.gms.cast.framework;

public interface SessionManagerListener {
   void onSessionStarting(Session var1);

   void onSessionStarted(Session var1, String var2);

   void onSessionStartFailed(Session var1, int var2);

   void onSessionEnding(Session var1);

   void onSessionEnded(Session var1, int var2);

   void onSessionResuming(Session var1, String var2);

   void onSessionResumed(Session var1, boolean var2);

   void onSessionResumeFailed(Session var1, int var2);

   void onSessionSuspended(Session var1, int var2);
}
