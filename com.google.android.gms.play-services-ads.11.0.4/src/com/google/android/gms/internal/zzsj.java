package com.google.android.gms.internal;

import android.os.Handler;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzzn
final class zzsj {
   private final List zztH = new LinkedList();

   final void zza(zzti var1) {
      Handler var2 = zzagz.zzZr;
      Iterator var3 = this.zztH.iterator();

      while(var3.hasNext()) {
         zzth var4 = (zzth)var3.next();
         var2.post(new zztg(this, var4, var1));
      }

      this.zztH.clear();
   }

   // $FF: synthetic method
   static List zza(zzsj var0) {
      return var0.zztH;
   }
}
