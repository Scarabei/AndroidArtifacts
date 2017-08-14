package com.google.android.gms.nearby.messages;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public class MessagesOptions implements Optional {
   @Nullable
   public final String zzbye;
   public final boolean zzbyf;
   public final int zzbyg;
   public final String zzbyh;

   private MessagesOptions(MessagesOptions.Builder var1) {
      this.zzbye = null;
      this.zzbyf = false;
      this.zzbyg = var1.zzbyi;
      this.zzbyh = null;
   }

   // $FF: synthetic method
   MessagesOptions(MessagesOptions.Builder var1, zze var2) {
      this(var1);
   }

   public static class Builder {
      private int zzbyi = -1;

      public MessagesOptions.Builder setPermissions(int var1) {
         this.zzbyi = var1;
         return this;
      }

      public MessagesOptions build() {
         return new MessagesOptions(this, (zze)null);
      }
   }
}
