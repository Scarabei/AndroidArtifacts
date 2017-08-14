package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzzn;
import java.util.HashSet;
import java.util.Iterator;
import java.util.AbstractMap.SimpleEntry;
import org.json.JSONObject;

@zzzn
public final class zzak implements zzaj {
   private final zzai zzLB;
   private final HashSet zzLC;

   public zzak(zzai var1) {
      this.zzLB = var1;
      this.zzLC = new HashSet();
   }

   public final void zzb(String var1, JSONObject var2) {
      this.zzLB.zzb(var1, var2);
   }

   public final void zza(String var1, JSONObject var2) {
      this.zzLB.zza(var1, var2);
   }

   public final void zzi(String var1, String var2) {
      this.zzLB.zzi(var1, var2);
   }

   public final void zza(String var1, zzrd var2) {
      this.zzLB.zza(var1, var2);
      this.zzLC.add(new SimpleEntry(var1, var2));
   }

   public final void zzb(String var1, zzrd var2) {
      this.zzLB.zzb(var1, var2);
      this.zzLC.remove(new SimpleEntry(var1, var2));
   }

   public final void zzfe() {
      Iterator var1 = this.zzLC.iterator();

      while(var1.hasNext()) {
         SimpleEntry var2 = (SimpleEntry)var1.next();
         String var10001 = String.valueOf(((zzrd)var2.getValue()).toString());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Unregistering eventhandler: ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Unregistering eventhandler: ");
         }

         zzafr.v(var10000);
         this.zzLB.zzb((String)var2.getKey(), (zzrd)var2.getValue());
      }

      this.zzLC.clear();
   }
}
