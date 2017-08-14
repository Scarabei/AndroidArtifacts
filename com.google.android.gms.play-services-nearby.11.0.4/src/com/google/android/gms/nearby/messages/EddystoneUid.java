package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public class EddystoneUid {
   public static final int LENGTH = 16;
   public static final int NAMESPACE_LENGTH = 10;
   public static final int INSTANCE_LENGTH = 6;
   private final com.google.android.gms.nearby.messages.internal.zzg zzbxR;

   private EddystoneUid(byte[] var1) {
      zzbo.zzb(var1.length == 16, "Bytes must be a namespace plus instance (16 bytes).");
      this.zzbxR = new com.google.android.gms.nearby.messages.internal.zzg(var1);
   }

   public EddystoneUid(String var1) {
      this(com.google.android.gms.nearby.messages.internal.zzc.zzeE(var1));
   }

   public EddystoneUid(String var1, String var2) {
      this.zzbxR = new com.google.android.gms.nearby.messages.internal.zzg(var1, var2);
   }

   public static EddystoneUid from(Message var0) {
      boolean var10000 = var0.zzeD("__eddystone_uid");
      String var1 = String.valueOf(var0.getType());
      zzbo.zzb(var10000, (new StringBuilder(58 + String.valueOf(var1).length())).append("Message type '").append(var1).append("' is not Message.MESSAGE_TYPE_EDDYSTONE_UID.").toString());
      return new EddystoneUid(var0.getContent());
   }

   public String getNamespace() {
      return com.google.android.gms.nearby.messages.internal.zzc.zzo(Arrays.copyOfRange(this.zzbxR.getBytes(), 0, 10));
   }

   public String getInstance() {
      byte[] var1;
      return (var1 = this.zzbxR.getBytes()).length < 16 ? null : com.google.android.gms.nearby.messages.internal.zzc.zzo(Arrays.copyOfRange(var1, 10, 16));
   }

   public String getHex() {
      return this.zzbxR.getHex();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof EddystoneUid)) {
         return false;
      } else {
         EddystoneUid var2 = (EddystoneUid)var1;
         return zzbe.equal(this.zzbxR, var2.zzbxR);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbxR});
   }

   public String toString() {
      String var1 = String.valueOf(this.getHex());
      return (new StringBuilder(17 + String.valueOf(var1).length())).append("EddystoneUid{id=").append(var1).append("}").toString();
   }
}
