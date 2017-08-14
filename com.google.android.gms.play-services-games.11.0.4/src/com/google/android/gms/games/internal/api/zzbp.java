package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Quests;

final class zzbp implements Quests.ClaimMilestoneResult {
   // $FF: synthetic field
   private Status zzakB;

   zzbp(zzbo var1, Status var2) {
      this.zzakB = var2;
      super();
   }

   public final Status getStatus() {
      return this.zzakB;
   }

   public final Milestone getMilestone() {
      return null;
   }

   public final Quest getQuest() {
      return null;
   }
}
