package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.zzc;
import java.util.Arrays;

public final class ExperienceEventEntity extends zzc implements ExperienceEvent {
   public static final Creator CREATOR = new zza();
   private final String zzbbM;
   private final GameEntity zzbbN;
   private final String zzbbO;
   private final String zzbbP;
   private final String zzaYf;
   private final Uri zzaXU;
   private final long zzbbQ;
   private final long zzbbR;
   private final long zzbbS;
   private final int zzamr;
   private final int zzbbT;

   public ExperienceEventEntity(ExperienceEvent var1) {
      this.zzbbM = var1.zzuY();
      this.zzbbN = new GameEntity(var1.getGame());
      this.zzbbO = var1.zzuZ();
      this.zzbbP = var1.zzva();
      this.zzaYf = var1.getIconImageUrl();
      this.zzaXU = var1.getIconImageUri();
      this.zzbbQ = var1.zzvb();
      this.zzbbR = var1.zzvc();
      this.zzbbS = var1.zzvd();
      this.zzamr = var1.getType();
      this.zzbbT = var1.zzve();
   }

   ExperienceEventEntity(String var1, GameEntity var2, String var3, String var4, String var5, Uri var6, long var7, long var9, long var11, int var13, int var14) {
      this.zzbbM = var1;
      this.zzbbN = var2;
      this.zzbbO = var3;
      this.zzbbP = var4;
      this.zzaYf = var5;
      this.zzaXU = var6;
      this.zzbbQ = var7;
      this.zzbbR = var9;
      this.zzbbS = var11;
      this.zzamr = var13;
      this.zzbbT = var14;
   }

   public final String zzuY() {
      return this.zzbbM;
   }

   public final Game getGame() {
      return this.zzbbN;
   }

   public final String zzuZ() {
      return this.zzbbO;
   }

   public final String zzva() {
      return this.zzbbP;
   }

   public final String getIconImageUrl() {
      return this.zzaYf;
   }

   public final Uri getIconImageUri() {
      return this.zzaXU;
   }

   public final long zzvb() {
      return this.zzbbQ;
   }

   public final long zzvc() {
      return this.zzbbR;
   }

   public final long zzvd() {
      return this.zzbbS;
   }

   public final int getType() {
      return this.zzamr;
   }

   public final int zzve() {
      return this.zzbbT;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(ExperienceEvent var0) {
      return Arrays.hashCode(new Object[]{var0.zzuY(), var0.getGame(), var0.zzuZ(), var0.zzva(), var0.getIconImageUrl(), var0.getIconImageUri(), var0.zzvb(), var0.zzvc(), var0.zzvd(), var0.getType(), var0.zzve()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(ExperienceEvent var0, Object var1) {
      if (!(var1 instanceof ExperienceEvent)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         ExperienceEvent var2;
         return zzbe.equal((var2 = (ExperienceEvent)var1).zzuY(), var0.zzuY()) && zzbe.equal(var2.getGame(), var0.getGame()) && zzbe.equal(var2.zzuZ(), var0.zzuZ()) && zzbe.equal(var2.zzva(), var0.zzva()) && zzbe.equal(var2.getIconImageUrl(), var0.getIconImageUrl()) && zzbe.equal(var2.getIconImageUri(), var0.getIconImageUri()) && zzbe.equal(var2.zzvb(), var0.zzvb()) && zzbe.equal(var2.zzvc(), var0.zzvc()) && zzbe.equal(var2.zzvd(), var0.zzvd()) && zzbe.equal(var2.getType(), var0.getType()) && zzbe.equal(var2.zzve(), var0.zzve());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(ExperienceEvent var0) {
      return zzbe.zzt(var0).zzg("ExperienceId", var0.zzuY()).zzg("Game", var0.getGame()).zzg("DisplayTitle", var0.zzuZ()).zzg("DisplayDescription", var0.zzva()).zzg("IconImageUrl", var0.getIconImageUrl()).zzg("IconImageUri", var0.getIconImageUri()).zzg("CreatedTimestamp", var0.zzvb()).zzg("XpEarned", var0.zzvc()).zzg("CurrentXp", var0.zzvd()).zzg("Type", var0.getType()).zzg("NewLevel", var0.zzve()).toString();
   }

   public final boolean isDataValid() {
      return true;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzbbM, false);
      zzd.zza(var1, 2, this.zzbbN, var2, false);
      zzd.zza(var1, 3, this.zzbbO, false);
      zzd.zza(var1, 4, this.zzbbP, false);
      zzd.zza(var1, 5, this.getIconImageUrl(), false);
      zzd.zza(var1, 6, this.zzaXU, var2, false);
      zzd.zza(var1, 7, this.zzbbQ);
      zzd.zza(var1, 8, this.zzbbR);
      zzd.zza(var1, 9, this.zzbbS);
      zzd.zzc(var1, 10, this.zzamr);
      zzd.zzc(var1, 11, this.zzbbT);
      zzd.zzI(var1, var5);
   }
}
