package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;

public class zzl extends com.google.android.gms.drive.metadata.zzb {
   public zzl(String var1, Collection var2, Collection var3, int var4) {
      super(var1, var2, var3, var4);
   }

   protected Collection zzr(Bundle var1) {
      return var1.getParcelableArrayList(this.getName());
   }

   // $FF: synthetic method
   protected final void zza(Bundle var1, Object var2) {
      Collection var5 = (Collection)var2;
      var1.putParcelableArrayList(this.getName(), var5 instanceof ArrayList ? (ArrayList)var5 : new ArrayList(var5));
   }

   // $FF: synthetic method
   protected Object zzq(Bundle var1) {
      return this.zzr(var1);
   }
}
