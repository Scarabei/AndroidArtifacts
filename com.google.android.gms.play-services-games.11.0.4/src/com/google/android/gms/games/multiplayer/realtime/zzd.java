package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzbo;

public final class zzd extends RoomConfig {
   private final RoomUpdateListener zzbdG;
   private final RoomStatusUpdateListener zzbdH;
   private final RealTimeMessageReceivedListener zzbdI;
   private final String zzajX;
   private final int zzbdu;
   private final String[] zzbdM;
   private final Bundle zzbdL;

   zzd(RoomConfig.Builder var1) {
      this.zzbdG = var1.zzbdG;
      this.zzbdH = var1.zzbdH;
      this.zzbdI = var1.zzbdI;
      this.zzajX = var1.zzbdJ;
      this.zzbdu = var1.zzbdu;
      this.zzbdL = var1.zzbdL;
      int var2 = var1.zzbdK.size();
      this.zzbdM = (String[])var1.zzbdK.toArray(new String[var2]);
      zzbo.zzb(this.zzbdI, "Must specify a message listener");
   }

   public final RoomUpdateListener getRoomUpdateListener() {
      return this.zzbdG;
   }

   public final String getInvitationId() {
      return this.zzajX;
   }

   public final RoomStatusUpdateListener getRoomStatusUpdateListener() {
      return this.zzbdH;
   }

   public final RealTimeMessageReceivedListener getMessageReceivedListener() {
      return this.zzbdI;
   }

   public final int getVariant() {
      return this.zzbdu;
   }

   public final String[] getInvitedPlayerIds() {
      return this.zzbdM;
   }

   public final Bundle getAutoMatchCriteria() {
      return this.zzbdL;
   }
}
