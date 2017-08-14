package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

public final class QuestBuffer extends zzg {
   public QuestBuffer(DataHolder var1) {
      super(var1);
   }

   protected final String zzqS() {
      return "external_quest_id";
   }

   // $FF: synthetic method
   protected final Object zzi(int var1, int var2) {
      return new QuestRef(this.zzaCX, var1, var2);
   }
}
