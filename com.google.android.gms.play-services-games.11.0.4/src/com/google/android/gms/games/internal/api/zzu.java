package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;

final class zzu implements Events.LoadEventsResult {
   // $FF: synthetic field
   private Status zzakB;

   zzu(zzt var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final void release() {
   }

   public final EventBuffer getEvents() {
      return new EventBuffer(DataHolder.zzau(14));
   }
}
