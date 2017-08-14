package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Quests;

final class zzbn implements Quests.AcceptQuestResult {
   // $FF: synthetic field
   private Status zzakB;

   zzbn(zzbm var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final Quest getQuest() {
      return null;
   }
}
