package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;
import java.util.LinkedList;

@zzzn
final class zztn {
   private final LinkedList zzKr;
   private zzir zzKs;
   private final String zztV;
   private final int zzKt;
   private boolean zzKu;

   zztn(zzir var1, String var2, int var3) {
      zzbo.zzu(var1);
      zzbo.zzu(var2);
      this.zzKr = new LinkedList();
      this.zzKs = var1;
      this.zztV = var2;
      this.zzKt = var3;
   }

   final zzir zzeI() {
      return this.zzKs;
   }

   final int getNetworkType() {
      return this.zzKt;
   }

   final String getAdUnitId() {
      return this.zztV;
   }

   final zzto zzm(@Nullable zzir var1) {
      if (var1 != null) {
         this.zzKs = var1;
      }

      return (zzto)this.zzKr.remove();
   }

   final int size() {
      return this.zzKr.size();
   }

   final int zzeJ() {
      int var1 = 0;
      Iterator var2 = this.zzKr.iterator();

      while(var2.hasNext()) {
         if (((zzto)var2.next()).zzKz) {
            ++var1;
         }
      }

      return var1;
   }

   final boolean zzb(zzsi var1) {
      zzto var2 = new zzto(this, var1);
      this.zzKr.add(var2);
      return var2.load();
   }

   final int zzeK() {
      int var1 = 0;
      Iterator var2 = this.zzKr.iterator();

      while(var2.hasNext()) {
         if (((zzto)var2.next()).load()) {
            ++var1;
         }
      }

      return var1;
   }

   final void zza(zzsi var1, zzir var2) {
      zzto var3 = new zzto(this, var1, var2);
      this.zzKr.add(var3);
   }

   final void zzeL() {
      this.zzKu = true;
   }

   final boolean zzeM() {
      return this.zzKu;
   }

   // $FF: synthetic method
   static String zza(zztn var0) {
      return var0.zztV;
   }

   // $FF: synthetic method
   static zzir zzb(zztn var0) {
      return var0.zzKs;
   }
}
