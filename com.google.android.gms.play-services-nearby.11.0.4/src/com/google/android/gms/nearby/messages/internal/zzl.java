package com.google.android.gms.nearby.messages.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.nio.ByteBuffer;
import java.util.UUID;

public final class zzl extends zzc {
   public zzl(byte[] var1) {
      zzbo.zzb(var1.length == 16 || var1.length == 18 || var1.length == 20, "Prefix must be a UUID, a UUID and a major, or a UUID, a major, and a minor.");
      super(var1);
   }

   public zzl(UUID var1, @Nullable Short var2, @Nullable Short var3) {
      ByteBuffer var7;
      (var7 = ByteBuffer.allocate(16 + (var2 == null ? 0 : 2) + (var3 == null ? 0 : 2))).putLong(var1.getMostSignificantBits()).putLong(var1.getLeastSignificantBits());
      if (var2 != null) {
         var7.putShort(var2.shortValue());
      }

      if (var3 != null) {
         var7.putShort(var3.shortValue());
      }

      this(var7.array());
   }

   public final UUID getProximityUuid() {
      ByteBuffer var1 = ByteBuffer.wrap(this.getBytes());
      return new UUID(var1.getLong(), var1.getLong());
   }

   public final Short zzzV() {
      byte[] var1;
      return (var1 = this.getBytes()).length >= 18 ? ByteBuffer.wrap(var1).getShort(16) : null;
   }

   public final Short zzzW() {
      byte[] var1;
      return (var1 = this.getBytes()).length == 20 ? ByteBuffer.wrap(var1).getShort(18) : null;
   }

   public final String toString() {
      String var1 = String.valueOf(this.getProximityUuid());
      String var2 = String.valueOf(this.zzzV());
      String var3 = String.valueOf(this.zzzW());
      return (new StringBuilder(47 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length())).append("IBeaconIdPrefix{proximityUuid=").append(var1).append(", major=").append(var2).append(", minor=").append(var3).append("}").toString();
   }
}
