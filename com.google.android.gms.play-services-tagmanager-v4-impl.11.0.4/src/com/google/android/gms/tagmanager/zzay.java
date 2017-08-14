package com.google.android.gms.tagmanager;

import java.util.Arrays;

final class zzay {
   final String zzBN;
   final byte[] zzbEw;

   zzay(String var1, byte[] var2) {
      this.zzBN = var1;
      this.zzbEw = var2;
   }

   public final String toString() {
      String var1 = this.zzBN;
      int var2 = Arrays.hashCode(this.zzbEw);
      return (new StringBuilder(54 + String.valueOf(var1).length())).append("KeyAndSerialized: key = ").append(var1).append(" serialized hash = ").append(var2).toString();
   }
}
