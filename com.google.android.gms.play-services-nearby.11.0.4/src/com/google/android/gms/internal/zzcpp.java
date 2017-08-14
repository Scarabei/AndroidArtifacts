package com.google.android.gms.internal;

import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.internal.zzak;
import java.util.List;

final class zzcpp extends zzcps {
   // $FF: synthetic field
   private List zzbzF;

   zzcpp(zzcpo var1, List var2) {
      this.zzbzF = var2;
      super();
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      MessageListener var2 = (MessageListener)var1;
      zzak.zza(this.zzbzF, var2);
   }
}
