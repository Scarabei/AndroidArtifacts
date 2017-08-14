package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class dv extends dp {
   public static final dv zzbLr = new dv("BREAK");
   public static final dv zzbLs = new dv("CONTINUE");
   public static final dv zzbLt = new dv("NULL");
   public static final dv zzbLu = new dv("UNDEFINED");
   private final String mName;
   private final boolean zzbLv;
   private final dp zzbLw;

   private dv(String var1) {
      this.mName = var1;
      this.zzbLv = false;
      this.zzbLw = null;
   }

   public dv(dp var1) {
      zzbo.zzu(var1);
      this.mName = "RETURN";
      this.zzbLv = true;
      this.zzbLw = var1;
   }

   public final dp zzDq() {
      return this.zzbLw;
   }

   public final boolean zzDr() {
      return this.zzbLv;
   }

   public final String toString() {
      return this.mName;
   }

   // $FF: synthetic method
   public final Object zzDl() {
      return this.zzbLw;
   }
}
