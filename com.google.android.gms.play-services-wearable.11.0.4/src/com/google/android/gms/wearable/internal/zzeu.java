package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;

final class zzeu implements ChannelApi.ChannelListener {
   private final String zzakv;
   private final ChannelApi.ChannelListener zzbTd;

   zzeu(String var1, ChannelApi.ChannelListener var2) {
      this.zzakv = (String)com.google.android.gms.common.internal.zzbo.zzu(var1);
      this.zzbTd = (ChannelApi.ChannelListener)com.google.android.gms.common.internal.zzbo.zzu(var2);
   }

   public final void onChannelOpened(Channel var1) {
      this.zzbTd.onChannelOpened(var1);
   }

   public final void onChannelClosed(Channel var1, int var2, int var3) {
      this.zzbTd.onChannelClosed(var1, var2, var3);
   }

   public final void onInputClosed(Channel var1, int var2, int var3) {
      this.zzbTd.onInputClosed(var1, var2, var3);
   }

   public final void onOutputClosed(Channel var1, int var2, int var3) {
      this.zzbTd.onOutputClosed(var1, var2, var3);
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzeu)) {
         return false;
      } else {
         zzeu var2 = (zzeu)var1;
         return this.zzbTd.equals(var2.zzbTd) && this.zzakv.equals(var2.zzakv);
      }
   }

   public final int hashCode() {
      return this.zzakv.hashCode() * 31 + this.zzbTd.hashCode();
   }
}
