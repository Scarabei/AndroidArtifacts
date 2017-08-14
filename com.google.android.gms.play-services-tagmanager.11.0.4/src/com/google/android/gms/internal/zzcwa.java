package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbo;
import java.util.HashMap;
import java.util.Map;

public final class zzcwa {
   private zzcwa zzbIR;
   private Map zzbIS;

   public zzcwa() {
      this((zzcwa)null);
   }

   private zzcwa(@Nullable zzcwa var1) {
      this.zzbIS = null;
      this.zzbIR = var1;
   }

   public final zzcwa zzCz() {
      return new zzcwa(this);
   }

   public final boolean has(String var1) {
      while(this.zzbIS == null || !this.zzbIS.containsKey(var1)) {
         if (this.zzbIR == null) {
            return false;
         }

         this = this.zzbIR;
      }

      return true;
   }

   public final dp zzfK(String var1) {
      while(this.zzbIS == null || !this.zzbIS.containsKey(var1)) {
         if (this.zzbIR == null) {
            IllegalStateException var10000 = new IllegalStateException;
            String var10003 = String.valueOf(var1);
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "Trying to get a non existent symbol: ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Trying to get a non existent symbol: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }

         this = this.zzbIR;
      }

      return (dp)this.zzbIS.get(var1);
   }

   public final void zza(String var1, dp var2) {
      if (this.zzbIS == null) {
         this.zzbIS = new HashMap();
      }

      this.zzbIS.put(var1, var2);
   }

   public final void zzb(String var1, dp var2) {
      while(this.zzbIS == null || !this.zzbIS.containsKey(var1)) {
         if (this.zzbIR == null) {
            IllegalStateException var10000 = new IllegalStateException;
            String var10003 = String.valueOf(var1);
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "Trying to modify a non existent symbol: ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Trying to modify a non existent symbol: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }

         this = this.zzbIR;
      }

      this.zzbIS.put(var1, var2);
   }

   public final void remove(String var1) {
      while(true) {
         zzbo.zzae(this.has(var1));
         if (this.zzbIS != null && this.zzbIS.containsKey(var1)) {
            this.zzbIS.remove(var1);
            return;
         }

         this = this.zzbIR;
      }
   }
}
