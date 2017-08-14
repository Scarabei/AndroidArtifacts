package com.google.android.gms.internal;

import java.util.List;

public final class zzcxn extends zzcxq {
   private zzcwa zzbIR = null;
   private final String mName;
   private final List zzbJS;
   private final List zzbJT;

   public zzcxn(zzcwa var1, String var2, List var3, List var4) {
      this.mName = var2;
      this.zzbJS = var3;
      this.zzbJT = var4;
   }

   public final void zza(zzcwa var1) {
      this.zzbIR = var1;
   }

   public final dp zza(zzcwa param1, dp... param2) {
      // $FF: Couldn't be decompiled
   }

   public final String getName() {
      return this.mName;
   }

   public final String toString() {
      String var1 = this.mName;
      String var2 = String.valueOf(this.zzbJS.toString());
      String var3 = String.valueOf(this.zzbJT.toString());
      return (new StringBuilder(26 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length())).append(var1).append("\n\tparams: ").append(var2).append("\n\t: statements: ").append(var3).toString();
   }
}
