package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @deprecated */
@Deprecated
public final class GameRequestEntity extends zzc implements GameRequest {
   public static final Creator CREATOR = new zza();
   private final GameEntity zzbbN;
   private final PlayerEntity zzbes;
   private final byte[] zzbdY;
   private final String zzQx;
   private final ArrayList zzbet;
   private final int zzamr;
   private final long mCreationTimestamp;
   private final long zzbeu;
   private final Bundle zzbev;
   private final int zzLe;

   public GameRequestEntity(GameRequest var1) {
      this.zzbbN = new GameEntity(var1.getGame());
      this.zzbes = new PlayerEntity(var1.getSender());
      this.zzQx = var1.getRequestId();
      this.zzamr = var1.getType();
      this.mCreationTimestamp = var1.getCreationTimestamp();
      this.zzbeu = var1.getExpirationTimestamp();
      this.zzLe = var1.getStatus();
      byte[] var2;
      if ((var2 = var1.getData()) == null) {
         this.zzbdY = null;
      } else {
         this.zzbdY = new byte[var2.length];
         System.arraycopy(var2, 0, this.zzbdY, 0, var2.length);
      }

      List var3;
      int var4 = (var3 = var1.getRecipients()).size();
      this.zzbet = new ArrayList(var4);
      this.zzbev = new Bundle();

      for(int var5 = 0; var5 < var4; ++var5) {
         Player var6;
         String var7 = (var6 = (Player)((Player)var3.get(var5)).freeze()).getPlayerId();
         this.zzbet.add((PlayerEntity)var6);
         this.zzbev.putInt(var7, var1.getRecipientStatus(var7));
      }

   }

   GameRequestEntity(GameEntity var1, PlayerEntity var2, byte[] var3, String var4, ArrayList var5, int var6, long var7, long var9, Bundle var11, int var12) {
      this.zzbbN = var1;
      this.zzbes = var2;
      this.zzbdY = var3;
      this.zzQx = var4;
      this.zzbet = var5;
      this.zzamr = var6;
      this.mCreationTimestamp = var7;
      this.zzbeu = var9;
      this.zzbev = var11;
      this.zzLe = var12;
   }

   public final String getRequestId() {
      return this.zzQx;
   }

   public final Game getGame() {
      return this.zzbbN;
   }

   public final Player getSender() {
      return this.zzbes;
   }

   public final List getRecipients() {
      return new ArrayList(this.zzbet);
   }

   public final boolean isConsumed(String var1) {
      return this.getRecipientStatus(var1) == 1;
   }

   public final byte[] getData() {
      return this.zzbdY;
   }

   public final int getType() {
      return this.zzamr;
   }

   public final long getCreationTimestamp() {
      return this.mCreationTimestamp;
   }

   public final long getExpirationTimestamp() {
      return this.zzbeu;
   }

   public final int getRecipientStatus(String var1) {
      return this.zzbev.getInt(var1, 0);
   }

   public final int getStatus() {
      return this.zzLe;
   }

   public final GameRequest freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(GameRequest var0) {
      return Arrays.hashCode(new Object[]{var0.getGame(), var0.getRecipients(), var0.getRequestId(), var0.getSender(), zzb(var0), var0.getType(), var0.getCreationTimestamp(), var0.getExpirationTimestamp()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(GameRequest var0, Object var1) {
      if (!(var1 instanceof GameRequest)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         GameRequest var2;
         return zzbe.equal((var2 = (GameRequest)var1).getGame(), var0.getGame()) && zzbe.equal(var2.getRecipients(), var0.getRecipients()) && zzbe.equal(var2.getRequestId(), var0.getRequestId()) && zzbe.equal(var2.getSender(), var0.getSender()) && Arrays.equals(zzb(var2), zzb(var0)) && zzbe.equal(var2.getType(), var0.getType()) && zzbe.equal(var2.getCreationTimestamp(), var0.getCreationTimestamp()) && zzbe.equal(var2.getExpirationTimestamp(), var0.getExpirationTimestamp());
      }
   }

   private static int[] zzb(GameRequest var0) {
      List var1;
      int var2;
      int[] var3 = new int[var2 = (var1 = var0.getRecipients()).size()];

      for(int var4 = 0; var4 < var2; ++var4) {
         var3[var4] = var0.getRecipientStatus(((Player)var1.get(var4)).getPlayerId());
      }

      return var3;
   }

   public final String toString() {
      return zzc(this);
   }

   static String zzc(GameRequest var0) {
      return zzbe.zzt(var0).zzg("Game", var0.getGame()).zzg("Sender", var0.getSender()).zzg("Recipients", var0.getRecipients()).zzg("Data", var0.getData()).zzg("RequestId", var0.getRequestId()).zzg("Type", var0.getType()).zzg("CreationTimestamp", var0.getCreationTimestamp()).zzg("ExpirationTimestamp", var0.getExpirationTimestamp()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getGame(), var2, false);
      zzd.zza(var1, 2, this.getSender(), var2, false);
      zzd.zza(var1, 3, this.getData(), false);
      zzd.zza(var1, 4, this.getRequestId(), false);
      zzd.zzc(var1, 5, this.getRecipients(), false);
      zzd.zzc(var1, 7, this.getType());
      zzd.zza(var1, 9, this.getCreationTimestamp());
      zzd.zza(var1, 10, this.getExpirationTimestamp());
      zzd.zza(var1, 11, this.zzbev, false);
      zzd.zzc(var1, 12, this.getStatus());
      zzd.zzI(var1, var5);
   }
}
