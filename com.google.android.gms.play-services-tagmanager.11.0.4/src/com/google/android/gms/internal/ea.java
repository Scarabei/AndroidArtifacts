package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.List;

public final class ea extends dp {
   private final String zzbLD;
   private final List zzbLE;

   public ea(String var1, List var2) {
      zzbo.zzb(var1, "Instruction name must be a string.");
      zzbo.zzu(var2);
      this.zzbLD = var1;
      this.zzbLE = var2;
   }

   public final String toString() {
      String var1 = this.zzbLD;
      String var2 = String.valueOf(this.zzbLE.toString());
      return (new StringBuilder(3 + String.valueOf(var1).length() + String.valueOf(var2).length())).append("*").append(var1).append(": ").append(var2).toString();
   }

   public final String zzDv() {
      return this.zzbLD;
   }

   public final List zzDw() {
      return this.zzbLE;
   }

   // $FF: synthetic method
   public final Object zzDl() {
      return this.toString();
   }
}
