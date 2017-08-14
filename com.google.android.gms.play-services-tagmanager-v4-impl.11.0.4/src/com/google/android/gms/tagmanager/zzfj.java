package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.ei;
import com.google.android.gms.internal.em;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class zzfj {
   private final Set zzbGm = new HashSet();
   private final Map zzbGw = new HashMap();
   private final Map zzbGx = new HashMap();
   private final Map zzbGy = new HashMap();
   private final Map zzbGz = new HashMap();
   private ei zzbGA;

   public final Set zzBO() {
      return this.zzbGm;
   }

   public final void zza(em var1) {
      this.zzbGm.add(var1);
   }

   public final Map zzBP() {
      return this.zzbGw;
   }

   public final Map zzBQ() {
      return this.zzbGy;
   }

   public final Map zzBR() {
      return this.zzbGz;
   }

   public final void zza(em var1, ei var2) {
      Object var3;
      if ((var3 = (List)this.zzbGw.get(var1)) == null) {
         var3 = new ArrayList();
         this.zzbGw.put(var1, var3);
      }

      ((List)var3).add(var2);
   }

   public final void zza(em var1, String var2) {
      Object var3;
      if ((var3 = (List)this.zzbGy.get(var1)) == null) {
         var3 = new ArrayList();
         this.zzbGy.put(var1, var3);
      }

      ((List)var3).add(var2);
   }

   public final Map zzBS() {
      return this.zzbGx;
   }

   public final void zzb(em var1, ei var2) {
      Object var3;
      if ((var3 = (List)this.zzbGx.get(var1)) == null) {
         var3 = new ArrayList();
         this.zzbGx.put(var1, var3);
      }

      ((List)var3).add(var2);
   }

   public final void zzb(em var1, String var2) {
      Object var3;
      if ((var3 = (List)this.zzbGz.get(var1)) == null) {
         var3 = new ArrayList();
         this.zzbGz.put(var1, var3);
      }

      ((List)var3).add(var2);
   }

   public final ei zzBT() {
      return this.zzbGA;
   }

   public final void zzb(ei var1) {
      this.zzbGA = var1;
   }
}
