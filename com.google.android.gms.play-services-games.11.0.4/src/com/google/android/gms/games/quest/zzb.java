package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.zze;

public final class zzb extends com.google.android.gms.common.data.zzc implements Milestone {
   zzb(DataHolder var1, int var2) {
      super(var1, var2);
   }

   public final String getMilestoneId() {
      return this.getString("external_milestone_id");
   }

   public final long getCurrentProgress() {
      long var1;
      switch(this.getState()) {
      case 1:
         var1 = 0L;
         break;
      case 2:
         var1 = this.getLong("current_value");
         if (this.getLong("quest_state") != 6L) {
            var1 -= this.getLong("initial_value");
         }
         break;
      case 3:
      case 4:
         var1 = this.getTargetProgress();
         break;
      default:
         var1 = 0L;
      }

      if (var1 < 0L) {
         zze.zzz("MilestoneRef", "Current progress should never be negative");
         var1 = 0L;
      }

      if (var1 > this.getTargetProgress()) {
         zze.zzz("MilestoneRef", "Current progress should never exceed target progress");
         var1 = this.getTargetProgress();
      }

      return var1;
   }

   public final String getEventId() {
      return this.getString("external_event_id");
   }

   public final long getTargetProgress() {
      return this.getLong("target_value");
   }

   public final byte[] getCompletionRewardData() {
      return this.getByteArray("completion_reward_data");
   }

   public final int getState() {
      return this.getInteger("milestone_state");
   }

   public final int hashCode() {
      return MilestoneEntity.zza(this);
   }

   public final boolean equals(Object var1) {
      return MilestoneEntity.zza(this, var1);
   }

   public final String toString() {
      return MilestoneEntity.zzb(this);
   }

   public final int describeContents() {
      return 0;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      ((MilestoneEntity)((Milestone)this.freeze())).writeToParcel(var1, var2);
   }

   // $FF: synthetic method
   public final Object freeze() {
      return new MilestoneEntity(this);
   }
}
