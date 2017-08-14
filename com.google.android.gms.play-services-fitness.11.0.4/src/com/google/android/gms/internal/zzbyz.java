package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.fitness.SessionsApi;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import java.util.concurrent.TimeUnit;

public final class zzbyz implements SessionsApi {
   public final PendingResult startSession(GoogleApiClient var1, Session var2) {
      zzbo.zzb(var2, "Session cannot be null");
      zzbo.zzb(var2.getEndTime(TimeUnit.MILLISECONDS) == 0L, "Cannot start a session which has already ended");
      return var1.zze(new zzbza(this, var1, var2));
   }

   public final PendingResult stopSession(GoogleApiClient var1, String var2) {
      return var1.zze(new zzbzb(this, var1, (String)null, var2));
   }

   public final PendingResult insertSession(GoogleApiClient var1, SessionInsertRequest var2) {
      return var1.zzd(new zzbzc(this, var1, var2));
   }

   public final PendingResult readSession(GoogleApiClient var1, SessionReadRequest var2) {
      return var1.zzd(new zzbzd(this, var1, var2));
   }

   public final PendingResult registerForSessions(GoogleApiClient var1, PendingIntent var2) {
      return var1.zze(new zzbze(this, var1, var2, 0));
   }

   public final PendingResult unregisterForSessions(GoogleApiClient var1, PendingIntent var2) {
      return var1.zze(new zzbzf(this, var1, var2));
   }
}
