package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

public final class adk {
   private int type = 11;
   protected final Class zzcjG;
   public final int tag;
   protected final boolean zzcsp;

   public static adk zza(int var0, Class var1, long var2) {
      return new adk(11, var1, (int)var2, false);
   }

   private adk(int var1, Class var2, int var3, boolean var4) {
      this.zzcjG = var2;
      this.tag = var3;
      this.zzcsp = false;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof adk)) {
         return false;
      } else {
         adk var2 = (adk)var1;
         return this.type == var2.type && this.zzcjG == var2.zzcjG && this.tag == var2.tag;
      }
   }

   public final int hashCode() {
      int var1;
      return (((var1 = 1147 + this.type) * 31 + this.zzcjG.hashCode()) * 31 + this.tag) * 31;
   }

   final Object zzX(List var1) {
      if (var1 == null) {
         return null;
      } else if (var1.isEmpty()) {
         return null;
      } else {
         adr var4 = (adr)var1.get(var1.size() - 1);
         return this.zzcjG.cast(this.zzb(adg.zzH(var4.zzbws)));
      }
   }

   private final Object zzb(adg var1) {
      Class var2 = this.zzcjG;

      String var4;
      try {
         switch(this.type) {
         case 10:
            adp var3 = (adp)var2.newInstance();
            var1.zza(var3, this.tag >>> 3);
            return var3;
         case 11:
            adp var9 = (adp)var2.newInstance();
            var1.zza(var9);
            return var9;
         default:
            int var5 = this.type;
            throw new IllegalArgumentException((new StringBuilder(24)).append("Unknown type ").append(var5).toString());
         }
      } catch (InstantiationException var6) {
         var4 = String.valueOf(var2);
         throw new IllegalArgumentException((new StringBuilder(33 + String.valueOf(var4).length())).append("Error creating instance of class ").append(var4).toString(), var6);
      } catch (IllegalAccessException var7) {
         var4 = String.valueOf(var2);
         throw new IllegalArgumentException((new StringBuilder(33 + String.valueOf(var4).length())).append("Error creating instance of class ").append(var4).toString(), var7);
      } catch (IOException var8) {
         throw new IllegalArgumentException("Error reading extension field", var8);
      }
   }

   protected final void zza(Object var1, adh var2) {
      try {
         var2.zzcu(this.tag);
         switch(this.type) {
         case 10:
            adp var3 = (adp)var1;
            int var4 = this.tag >>> 3;
            var3.zza(var2);
            var2.zzt(var4, 4);
            return;
         case 11:
            adp var5 = (adp)var1;
            var2.zzb(var5);
            return;
         default:
            int var6 = this.type;
            throw new IllegalArgumentException((new StringBuilder(24)).append("Unknown type ").append(var6).toString());
         }
      } catch (IOException var9) {
         throw new IllegalStateException(var9);
      }
   }

   protected final int zzav(Object var1) {
      int var2 = this.tag >>> 3;
      switch(this.type) {
      case 10:
         adp var3 = (adp)var1;
         return (adh.zzct(var2) << 1) + var3.zzLV();
      case 11:
         adp var4 = (adp)var1;
         return adh.zzb(var2, var4);
      default:
         int var5 = this.type;
         throw new IllegalArgumentException((new StringBuilder(24)).append("Unknown type ").append(var5).toString());
      }
   }
}
