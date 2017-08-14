package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.Requests;

final class zzby implements Requests.LoadRequestsResult {
   // $FF: synthetic field
   private Status zzakB;

   zzby(zzbx var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final GameRequestBuffer getRequests(int var1) {
      int var2 = this.zzakB.getStatusCode();
      return new GameRequestBuffer(DataHolder.zzau(var2));
   }
}
