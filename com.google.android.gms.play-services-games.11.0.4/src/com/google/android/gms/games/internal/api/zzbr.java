package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.Quests;

final class zzbr implements Quests.LoadQuestsResult {
   // $FF: synthetic field
   private Status zzakB;

   zzbr(zzbq var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final void release() {
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final QuestBuffer getQuests() {
      int var1 = this.zzakB.getStatusCode();
      return new QuestBuffer(DataHolder.zzau(var1));
   }
}
