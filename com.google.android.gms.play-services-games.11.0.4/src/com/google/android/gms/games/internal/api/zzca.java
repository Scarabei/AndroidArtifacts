package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests;
import java.util.Set;

final class zzca implements Requests.UpdateRequestsResult {
   // $FF: synthetic field
   private Status zzakB;

   zzca(zzbz var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final int getRequestOutcome(String var1) {
      IllegalArgumentException var10000 = new IllegalArgumentException;
      String var10003 = String.valueOf(var1);
      String var10002;
      if (var10003.length() != 0) {
         var10002 = "Unknown request ID ".concat(var10003);
      } else {
         String var10004 = new String;
         var10002 = var10004;
         var10004.<init>("Unknown request ID ");
      }

      var10000.<init>(var10002);
      throw var10000;
   }

   public final Set getRequestIds() {
      return null;
   }
}
