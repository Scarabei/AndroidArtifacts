package com.google.android.gms.wearable;

import java.io.IOException;

public class ChannelIOException extends IOException {
   private final int zzbQZ;
   private final int zzbRa;

   public ChannelIOException(String var1, int var2, int var3) {
      super(var1);
      this.zzbQZ = var2;
      this.zzbRa = var3;
   }

   public int getCloseReason() {
      return this.zzbQZ;
   }

   public int getAppSpecificErrorCode() {
      return this.zzbRa;
   }
}
