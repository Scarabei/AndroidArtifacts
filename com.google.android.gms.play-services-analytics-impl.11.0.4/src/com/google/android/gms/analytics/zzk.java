package com.google.android.gms.analytics;

import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class zzk {
   private final zzl zzaea;
   protected final zzi zzaeb;
   private final List zzaec;

   protected zzk(zzl var1, com.google.android.gms.common.util.zze var2) {
      zzbo.zzu(var1);
      this.zzaea = var1;
      this.zzaec = new ArrayList();
      zzi var3;
      (var3 = new zzi(this, var2)).zzjy();
      this.zzaeb = var3;
   }

   public zzi zzjj() {
      zzi var1 = this.zzaeb.zzjp();
      this.zzd(var1);
      return var1;
   }

   protected void zza(zzi var1) {
   }

   protected final void zzd(zzi var1) {
      Iterator var2 = this.zzaec.iterator();

      while(var2.hasNext()) {
         var2.next();
      }

   }

   protected final zzl zzjz() {
      return this.zzaea;
   }
}
