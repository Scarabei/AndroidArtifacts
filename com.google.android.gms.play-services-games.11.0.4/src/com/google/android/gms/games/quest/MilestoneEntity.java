package com.google.android.gms.games.quest;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class MilestoneEntity extends com.google.android.gms.games.internal.zzc implements Milestone {
   public static final Creator CREATOR = new zza();
   private final String zzbak;
   private final long zzbef;
   private final long zzbeg;
   private final byte[] zzbeh;
   private final int mState;
   private final String zzaZn;

   public MilestoneEntity(Milestone var1) {
      this.zzbak = var1.getMilestoneId();
      this.zzbef = var1.getCurrentProgress();
      this.zzbeg = var1.getTargetProgress();
      this.mState = var1.getState();
      this.zzaZn = var1.getEventId();
      byte[] var2;
      if ((var2 = var1.getCompletionRewardData()) == null) {
         this.zzbeh = null;
      } else {
         this.zzbeh = new byte[var2.length];
         System.arraycopy(var2, 0, this.zzbeh, 0, var2.length);
      }
   }

   MilestoneEntity(String var1, long var2, long var4, byte[] var6, int var7, String var8) {
      this.zzbak = var1;
      this.zzbef = var2;
      this.zzbeg = var4;
      this.zzbeh = var6;
      this.mState = var7;
      this.zzaZn = var8;
   }

   public final String getEventId() {
      return this.zzaZn;
   }

   public final String getMilestoneId() {
      return this.zzbak;
   }

   public final long getCurrentProgress() {
      return this.zzbef;
   }

   public final long getTargetProgress() {
      return this.zzbeg;
   }

   public final byte[] getCompletionRewardData() {
      return this.zzbeh;
   }

   public final int getState() {
      return this.mState;
   }

   public final Milestone freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(Milestone var0) {
      return Arrays.hashCode(new Object[]{var0.getMilestoneId(), var0.getCurrentProgress(), var0.getTargetProgress(), var0.getState(), var0.getEventId()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(Milestone var0, Object var1) {
      if (!(var1 instanceof Milestone)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         Milestone var2;
         return zzbe.equal((var2 = (Milestone)var1).getMilestoneId(), var0.getMilestoneId()) && zzbe.equal(var2.getCurrentProgress(), var0.getCurrentProgress()) && zzbe.equal(var2.getTargetProgress(), var0.getTargetProgress()) && zzbe.equal(var2.getState(), var0.getState()) && zzbe.equal(var2.getEventId(), var0.getEventId());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(Milestone var0) {
      return zzbe.zzt(var0).zzg("MilestoneId", var0.getMilestoneId()).zzg("CurrentProgress", var0.getCurrentProgress()).zzg("TargetProgress", var0.getTargetProgress()).zzg("State", var0.getState()).zzg("CompletionRewardData", var0.getCompletionRewardData()).zzg("EventId", var0.getEventId()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getMilestoneId(), false);
      zzd.zza(var1, 2, this.getCurrentProgress());
      zzd.zza(var1, 3, this.getTargetProgress());
      zzd.zza(var1, 4, this.getCompletionRewardData(), false);
      zzd.zzc(var1, 5, this.getState());
      zzd.zza(var1, 6, this.getEventId(), false);
      zzd.zzI(var1, var5);
   }
}
