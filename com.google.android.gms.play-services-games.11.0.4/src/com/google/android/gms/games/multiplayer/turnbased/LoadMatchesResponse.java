package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse {
   private final InvitationBuffer zzbdQ;
   private final TurnBasedMatchBuffer zzbdR;
   private final TurnBasedMatchBuffer zzbdS;
   private final TurnBasedMatchBuffer zzbdT;

   public LoadMatchesResponse(Bundle var1) {
      DataHolder var2;
      if ((var2 = zzc(var1, 0)) != null) {
         this.zzbdQ = new InvitationBuffer(var2);
      } else {
         this.zzbdQ = null;
      }

      DataHolder var3;
      if ((var3 = zzc(var1, 1)) != null) {
         this.zzbdR = new TurnBasedMatchBuffer(var3);
      } else {
         this.zzbdR = null;
      }

      DataHolder var4;
      if ((var4 = zzc(var1, 2)) != null) {
         this.zzbdS = new TurnBasedMatchBuffer(var4);
      } else {
         this.zzbdS = null;
      }

      DataHolder var5;
      if ((var5 = zzc(var1, 3)) != null) {
         this.zzbdT = new TurnBasedMatchBuffer(var5);
      } else {
         this.zzbdT = null;
      }
   }

   private static DataHolder zzc(Bundle var0, int var1) {
      String var10000;
      switch(var1) {
      case 0:
         var10000 = "TURN_STATUS_INVITED";
         break;
      case 1:
         var10000 = "TURN_STATUS_MY_TURN";
         break;
      case 2:
         var10000 = "TURN_STATUS_THEIR_TURN";
         break;
      case 3:
         var10000 = "TURN_STATUS_COMPLETE";
         break;
      default:
         zze.zzz("MatchTurnStatus", (new StringBuilder(38)).append("Unknown match turn status: ").append(var1).toString());
         var10000 = "TURN_STATUS_UNKNOWN";
      }

      String var2 = var10000;
      return !var0.containsKey(var2) ? null : (DataHolder)var0.getParcelable(var2);
   }

   public final InvitationBuffer getInvitations() {
      return this.zzbdQ;
   }

   public final TurnBasedMatchBuffer getMyTurnMatches() {
      return this.zzbdR;
   }

   public final TurnBasedMatchBuffer getTheirTurnMatches() {
      return this.zzbdS;
   }

   public final TurnBasedMatchBuffer getCompletedMatches() {
      return this.zzbdT;
   }

   /** @deprecated */
   @Deprecated
   public final void close() {
      this.release();
   }

   public final void release() {
      if (this.zzbdQ != null) {
         this.zzbdQ.release();
      }

      if (this.zzbdR != null) {
         this.zzbdR.release();
      }

      if (this.zzbdS != null) {
         this.zzbdS.release();
      }

      if (this.zzbdT != null) {
         this.zzbdT.release();
      }

   }

   public final boolean hasData() {
      if (this.zzbdQ != null && this.zzbdQ.getCount() > 0) {
         return true;
      } else if (this.zzbdR != null && this.zzbdR.getCount() > 0) {
         return true;
      } else if (this.zzbdS != null && this.zzbdS.getCount() > 0) {
         return true;
      } else {
         return this.zzbdT != null && this.zzbdT.getCount() > 0;
      }
   }
}
