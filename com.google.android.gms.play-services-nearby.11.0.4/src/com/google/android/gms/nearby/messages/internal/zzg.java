package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzg extends zzc {
   public zzg(byte[] var1) {
      zzbo.zzb(var1.length == 10 || var1.length == 16, "Bytes must be a namespace (10 bytes), or a namespace plus instance (16 bytes).");
      super(var1);
   }

   public zzg(String var1) {
      this(zzeE(var1));
   }

   private zzg(byte[] var1, byte[] var2) {
      byte[][] var10001 = new byte[2][];
      boolean var10004 = var1.length == 10;
      int var4 = var1.length;
      zzbo.zzb(var10004, (new StringBuilder(62)).append("Namespace length(").append(var4).append(" bytes) must be 10 bytes.").toString());
      var10001[0] = var1;
      var10004 = var2.length == 6;
      var4 = var2.length;
      zzbo.zzb(var10004, (new StringBuilder(61)).append("Instance length(").append(var4).append(" bytes) must be 6 bytes.").toString());
      var10001[1] = var2;
      this(com.google.android.gms.common.util.zzb.zza(var10001));
   }

   public zzg(String var1, String var2) {
      this(zzeE(var1), zzeE(var2));
   }

   public final String toString() {
      String var1 = String.valueOf(this.getHex());
      return (new StringBuilder(26 + String.valueOf(var1).length())).append("EddystoneUidPrefix{bytes=").append(var1).append("}").toString();
   }
}
