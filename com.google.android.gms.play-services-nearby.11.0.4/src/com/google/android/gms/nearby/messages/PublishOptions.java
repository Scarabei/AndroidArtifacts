package com.google.android.gms.nearby.messages;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;

public final class PublishOptions {
   public static final PublishOptions DEFAULT = (new PublishOptions.Builder()).build();
   private final Strategy zzbyj;
   @Nullable
   private final PublishCallback zzbyk;

   private PublishOptions(Strategy var1, @Nullable PublishCallback var2) {
      this.zzbyj = var1;
      this.zzbyk = var2;
   }

   public final Strategy getStrategy() {
      return this.zzbyj;
   }

   @Nullable
   public final PublishCallback getCallback() {
      return this.zzbyk;
   }

   // $FF: synthetic method
   PublishOptions(Strategy var1, PublishCallback var2, zzf var3) {
      this(var1, var2);
   }

   public static class Builder {
      private Strategy zzbyj;
      @Nullable
      private PublishCallback zzbyk;

      public Builder() {
         this.zzbyj = Strategy.DEFAULT;
      }

      public PublishOptions.Builder setStrategy(Strategy var1) {
         this.zzbyj = (Strategy)zzbo.zzu(var1);
         return this;
      }

      public PublishOptions.Builder setCallback(PublishCallback var1) {
         this.zzbyk = (PublishCallback)zzbo.zzu(var1);
         return this;
      }

      public PublishOptions build() {
         return new PublishOptions(this.zzbyj, this.zzbyk, (zzf)null);
      }
   }
}
