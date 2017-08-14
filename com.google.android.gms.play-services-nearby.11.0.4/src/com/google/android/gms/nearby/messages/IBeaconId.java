package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.nearby.messages.internal.zzl;
import java.util.Arrays;
import java.util.UUID;

public class IBeaconId {
   public static final int LENGTH = 20;
   private final zzl zzbxS;

   private IBeaconId(byte[] var1) {
      zzbo.zzb(var1.length == 20, "iBeacon ID must be a UUID, a major, and a minor (20 total bytes).");
      this.zzbxS = new zzl(var1);
   }

   public IBeaconId(UUID var1, short var2, short var3) {
      this.zzbxS = new zzl(var1, var2, var3);
   }

   public static IBeaconId from(Message var0) {
      boolean var10000 = var0.zzeD("__i_beacon_id");
      String var1 = String.valueOf(var0.getType());
      zzbo.zzb(var10000, (new StringBuilder(55 + String.valueOf(var1).length())).append("Message type '").append(var1).append("' is not Message.MESSAGE_TYPE_I_BEACON_ID").toString());
      return new IBeaconId(var0.getContent());
   }

   public UUID getProximityUuid() {
      return this.zzbxS.getProximityUuid();
   }

   public short getMajor() {
      return this.zzbxS.zzzV().shortValue();
   }

   public short getMinor() {
      return this.zzbxS.zzzW().shortValue();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof IBeaconId)) {
         return false;
      } else {
         IBeaconId var2 = (IBeaconId)var1;
         return zzbe.equal(this.zzbxS, var2.zzbxS);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbxS});
   }

   public String toString() {
      String var1 = String.valueOf(this.getProximityUuid());
      short var2 = this.getMajor();
      short var3 = this.getMinor();
      return (new StringBuilder(53 + String.valueOf(var1).length())).append("IBeaconId{proximityUuid=").append(var1).append(", major=").append(var2).append(", minor=").append(var3).append("}").toString();
   }
}
