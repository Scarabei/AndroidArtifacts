package com.google.android.gms.nearby.messages;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;

public final class SubscribeOptions {
   public static final SubscribeOptions DEFAULT = (new SubscribeOptions.Builder()).build();
   private final Strategy zzbyj;
   private final MessageFilter zzbyy;
   @Nullable
   private final SubscribeCallback zzbyz;
   public final boolean zzbyA;
   public final int zzbyB;

   private SubscribeOptions(Strategy var1, MessageFilter var2, @Nullable SubscribeCallback var3, boolean var4, int var5) {
      this.zzbyj = var1;
      this.zzbyy = var2;
      this.zzbyz = var3;
      this.zzbyA = var4;
      this.zzbyB = var5;
   }

   public final Strategy getStrategy() {
      return this.zzbyj;
   }

   public final MessageFilter getFilter() {
      return this.zzbyy;
   }

   @Nullable
   public final SubscribeCallback getCallback() {
      return this.zzbyz;
   }

   // $FF: synthetic method
   SubscribeOptions(Strategy var1, MessageFilter var2, SubscribeCallback var3, boolean var4, int var5, zzh var6) {
      this(var1, var2, var3, false, 0);
   }

   public static class Builder {
      private Strategy zzbyj;
      private MessageFilter zzbyy;
      @Nullable
      private SubscribeCallback zzbyz;

      public Builder() {
         this.zzbyj = Strategy.DEFAULT;
         this.zzbyy = MessageFilter.INCLUDE_ALL_MY_TYPES;
      }

      public SubscribeOptions.Builder setStrategy(Strategy var1) {
         this.zzbyj = var1;
         return this;
      }

      public SubscribeOptions.Builder setFilter(MessageFilter var1) {
         this.zzbyy = var1;
         return this;
      }

      public SubscribeOptions.Builder setCallback(SubscribeCallback var1) {
         this.zzbyz = (SubscribeCallback)zzbo.zzu(var1);
         return this;
      }

      public SubscribeOptions build() {
         return new SubscribeOptions(this.zzbyj, this.zzbyy, this.zzbyz, false, 0, (zzh)null);
      }
   }
}
