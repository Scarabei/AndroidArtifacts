package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

public final class InvitationBuffer extends zzg {
   public InvitationBuffer(DataHolder var1) {
      super(var1);
   }

   protected final String zzqS() {
      return "external_invitation_id";
   }

   // $FF: synthetic method
   protected final Object zzi(int var1, int var2) {
      return new zzb(this.zzaCX, var1, var2);
   }
}
