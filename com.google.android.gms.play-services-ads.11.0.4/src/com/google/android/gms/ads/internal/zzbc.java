package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzoa;
import com.google.android.gms.internal.zzua;
import com.google.android.gms.internal.zzud;
import com.google.android.gms.internal.zzut;

final class zzbc implements Runnable {
   // $FF: synthetic field
   private zzafg zzsW;
   // $FF: synthetic field
   private zzbb zzuQ;

   zzbc(zzbb var1, zzafg var2) {
      this.zzuQ = var1;
      this.zzsW = var2;
      super();
   }

   public final void run() {
      this.zzuQ.zzb(new zzaff(this.zzsW, (zzaka)null, (zzua)null, (zzut)null, (String)null, (zzud)null, (zzoa)null, (String)null));
   }
}
