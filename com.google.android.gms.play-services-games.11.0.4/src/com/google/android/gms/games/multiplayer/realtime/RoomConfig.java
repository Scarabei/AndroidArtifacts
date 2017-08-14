package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class RoomConfig {
   public abstract RoomUpdateListener getRoomUpdateListener();

   public abstract String getInvitationId();

   public abstract RoomStatusUpdateListener getRoomStatusUpdateListener();

   public abstract RealTimeMessageReceivedListener getMessageReceivedListener();

   public abstract int getVariant();

   public abstract String[] getInvitedPlayerIds();

   public abstract Bundle getAutoMatchCriteria();

   public static RoomConfig.Builder builder(RoomUpdateListener var0) {
      return new RoomConfig.Builder(var0, (zzc)null);
   }

   public static Bundle createAutoMatchCriteria(int var0, int var1, long var2) {
      Bundle var4;
      (var4 = new Bundle()).putInt("min_automatch_players", var0);
      var4.putInt("max_automatch_players", var1);
      var4.putLong("exclusive_bit_mask", var2);
      return var4;
   }

   public static final class Builder {
      final RoomUpdateListener zzbdG;
      RoomStatusUpdateListener zzbdH;
      RealTimeMessageReceivedListener zzbdI;
      String zzbdJ;
      int zzbdu;
      ArrayList zzbdK;
      Bundle zzbdL;

      private Builder(RoomUpdateListener var1) {
         this.zzbdJ = null;
         this.zzbdu = -1;
         this.zzbdK = new ArrayList();
         this.zzbdG = (RoomUpdateListener)zzbo.zzb(var1, "Must provide a RoomUpdateListener");
      }

      public final RoomConfig.Builder setInvitationIdToAccept(String var1) {
         zzbo.zzu(var1);
         this.zzbdJ = var1;
         return this;
      }

      public final RoomConfig.Builder setRoomStatusUpdateListener(RoomStatusUpdateListener var1) {
         this.zzbdH = var1;
         return this;
      }

      public final RoomConfig.Builder setMessageReceivedListener(RealTimeMessageReceivedListener var1) {
         this.zzbdI = var1;
         return this;
      }

      public final RoomConfig.Builder addPlayersToInvite(String... var1) {
         zzbo.zzu(var1);
         this.zzbdK.addAll(Arrays.asList(var1));
         return this;
      }

      public final RoomConfig.Builder addPlayersToInvite(ArrayList var1) {
         zzbo.zzu(var1);
         this.zzbdK.addAll(var1);
         return this;
      }

      public final RoomConfig.Builder setVariant(int var1) {
         zzbo.zzb(var1 == -1 || var1 > 0, "Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
         this.zzbdu = var1;
         return this;
      }

      public final RoomConfig.Builder setAutoMatchCriteria(Bundle var1) {
         this.zzbdL = var1;
         return this;
      }

      public final RoomConfig build() {
         return new zzd(this);
      }

      // $FF: synthetic method
      Builder(RoomUpdateListener var1, zzc var2) {
         this(var1);
      }
   }
}
