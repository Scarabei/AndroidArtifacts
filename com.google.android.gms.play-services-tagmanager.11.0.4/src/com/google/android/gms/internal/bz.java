package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;

public final class bz {
   private final String zzbDw;
   @Nullable
   private final String zzbHI;
   @Nullable
   private final String zzbKq;
   private final boolean zzbKr;
   @Nullable
   private final String zzbKs;
   @Nullable
   private final String zzbFX;

   public bz(String var1, String var2, @Nullable String var3, boolean var4, @Nullable String var5) {
      this(var1, var2, var3, var4, var5, "");
   }

   private bz(String var1, String var2, @Nullable String var3, boolean var4, @Nullable String var5, String var6) {
      zzbo.zzu(var1);
      zzbo.zzu(var6);
      this.zzbDw = var1;
      this.zzbHI = var2;
      this.zzbKq = var3;
      this.zzbKr = var4;
      this.zzbKs = var5;
      this.zzbFX = var6;
   }

   public final String getContainerId() {
      return this.zzbDw;
   }

   public final String zzCI() {
      return this.zzbHI;
   }

   public final String zzCJ() {
      return this.zzbKq;
   }

   public final String zzCK() {
      if (this.zzbKq != null) {
         String var1 = this.zzbKq;
         String var2 = this.zzbDw;
         return (new StringBuilder(1 + String.valueOf(var1).length() + String.valueOf(var2).length())).append(var1).append("_").append(var2).toString();
      } else {
         return this.zzbDw;
      }
   }

   public final boolean zzCL() {
      return this.zzbKr;
   }

   public final String zzCM() {
      return this.zzbKs;
   }

   public final String zzCN() {
      return this.zzbFX;
   }
}
