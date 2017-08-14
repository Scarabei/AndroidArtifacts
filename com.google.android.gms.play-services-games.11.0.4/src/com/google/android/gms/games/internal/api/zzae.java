package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;

final class zzae implements Invitations.LoadInvitationsResult {
   // $FF: synthetic field
   private Status zzakB;

   zzae(zzad var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final InvitationBuffer getInvitations() {
      return new InvitationBuffer(DataHolder.zzau(14));
   }
}
