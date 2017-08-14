package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.AbstractDataBuffer;

public final class ParticipantBuffer extends AbstractDataBuffer {
   public final Participant get(int var1) {
      return new ParticipantRef(this.zzaCX, var1);
   }
}
