package com.google.android.gms.internal;

import android.graphics.Color;
import java.util.List;

@zzzn
public final class zznn {
   private static final int zzHm = Color.rgb(12, 174, 206);
   private static final int zzHn;
   private static int zzHo;
   private static int zzHp;
   private final String zzHq;
   private final List zzHr;
   private final int zzHs;
   private final int mTextColor;
   private final int zzHt;
   private final int zzHu;
   private final int zzHv;
   private final boolean zzHw;

   public zznn(String var1, List var2, Integer var3, Integer var4, Integer var5, int var6, int var7, boolean var8) {
      this.zzHq = var1;
      this.zzHr = var2;
      this.zzHs = var3 != null ? var3.intValue() : zzHo;
      this.mTextColor = var4 != null ? var4.intValue() : zzHp;
      this.zzHt = var5 != null ? var5.intValue() : 12;
      this.zzHu = var6;
      this.zzHv = var7;
      this.zzHw = var8;
   }

   public final String getText() {
      return this.zzHq;
   }

   public final List zzec() {
      return this.zzHr;
   }

   public final int getBackgroundColor() {
      return this.zzHs;
   }

   public final int getTextColor() {
      return this.mTextColor;
   }

   public final int getTextSize() {
      return this.zzHt;
   }

   public final int zzed() {
      return this.zzHu;
   }

   public final int zzee() {
      return this.zzHv;
   }

   public final boolean zzef() {
      return this.zzHw;
   }

   static {
      zzHo = zzHn = Color.rgb(204, 204, 204);
      zzHp = zzHm;
   }
}
