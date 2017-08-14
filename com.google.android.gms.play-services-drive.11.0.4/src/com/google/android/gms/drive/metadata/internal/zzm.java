package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.Collection;

public abstract class zzm extends com.google.android.gms.drive.metadata.zza {
   public zzm(String var1, Collection var2, Collection var3, int var4) {
      super(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   protected final void zza(Bundle var1, Object var2) {
      ReflectedParcelable var5 = (ReflectedParcelable)var2;
      var1.putParcelable(this.getName(), var5);
   }

   // $FF: synthetic method
   protected final Object zzq(Bundle var1) {
      return (ReflectedParcelable)var1.getParcelable(this.getName());
   }
}
