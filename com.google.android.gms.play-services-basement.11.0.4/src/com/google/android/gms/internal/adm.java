package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class adm implements Cloneable {
   private adk zzcsu;
   private Object value;
   private List zzcsv = new ArrayList();

   final void zza(adr var1) {
      this.zzcsv.add(var1);
   }

   final Object zzb(adk var1) {
      if (this.value != null) {
         if (!this.zzcsu.equals(var1)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
         }
      } else {
         this.zzcsu = var1;
         this.value = var1.zzX(this.zzcsv);
         this.zzcsv = null;
      }

      return this.value;
   }

   final int zzn() {
      int var1 = 0;
      adr var3;
      if (this.value != null) {
         Object var5 = this.value;
         adk var10001 = this.zzcsu;
         var1 = this.zzcsu.zzav(var5);
      } else {
         for(Iterator var2 = this.zzcsv.iterator(); var2.hasNext(); var1 += 0 + adh.zzcv(var3.tag) + var3.zzbws.length) {
            var3 = (adr)var2.next();
         }
      }

      return var1;
   }

   final void zza(adh var1) throws IOException {
      if (this.value != null) {
         Object var4 = this.value;
         adk var10001 = this.zzcsu;
         this.zzcsu.zza(var4, var1);
      } else {
         Iterator var2 = this.zzcsv.iterator();

         while(var2.hasNext()) {
            adr var3 = (adr)var2.next();
            var1.zzcu(var3.tag);
            var1.zzK(var3.zzbws);
         }

      }
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof adm)) {
         return false;
      } else {
         adm var2 = (adm)var1;
         if (this.value != null && var2.value != null) {
            if (this.zzcsu != var2.zzcsu) {
               return false;
            } else if (!this.zzcsu.zzcjG.isArray()) {
               return this.value.equals(var2.value);
            } else if (this.value instanceof byte[]) {
               return Arrays.equals((byte[])this.value, (byte[])var2.value);
            } else if (this.value instanceof int[]) {
               return Arrays.equals((int[])this.value, (int[])var2.value);
            } else if (this.value instanceof long[]) {
               return Arrays.equals((long[])this.value, (long[])var2.value);
            } else if (this.value instanceof float[]) {
               return Arrays.equals((float[])this.value, (float[])var2.value);
            } else if (this.value instanceof double[]) {
               return Arrays.equals((double[])this.value, (double[])var2.value);
            } else {
               return this.value instanceof boolean[] ? Arrays.equals((boolean[])this.value, (boolean[])var2.value) : Arrays.deepEquals((Object[])this.value, (Object[])var2.value);
            }
         } else if (this.zzcsv != null && var2.zzcsv != null) {
            return this.zzcsv.equals(var2.zzcsv);
         } else {
            try {
               return Arrays.equals(this.toByteArray(), var2.toByteArray());
            } catch (IOException var4) {
               throw new IllegalStateException(var4);
            }
         }
      }
   }

   public final int hashCode() {
      try {
         int var1 = 527 + Arrays.hashCode(this.toByteArray());
         return var1;
      } catch (IOException var3) {
         throw new IllegalStateException(var3);
      }
   }

   private final byte[] toByteArray() throws IOException {
      byte[] var1;
      adh var2 = adh.zzI(var1 = new byte[this.zzn()]);
      this.zza(var2);
      return var1;
   }

   private adm zzLP() {
      adm var1 = new adm();

      try {
         var1.zzcsu = this.zzcsu;
         if (this.zzcsv == null) {
            var1.zzcsv = null;
         } else {
            var1.zzcsv.addAll(this.zzcsv);
         }

         if (this.value != null) {
            if (this.value instanceof adp) {
               var1.value = (adp)((adp)this.value).clone();
            } else if (this.value instanceof byte[]) {
               var1.value = ((byte[])this.value).clone();
            } else {
               int var4;
               if (this.value instanceof byte[][]) {
                  byte[][] var2;
                  byte[][] var3 = new byte[(var2 = (byte[][])this.value).length][];
                  var1.value = var3;

                  for(var4 = 0; var4 < var2.length; ++var4) {
                     var3[var4] = (byte[])var2[var4].clone();
                  }
               } else if (this.value instanceof boolean[]) {
                  var1.value = ((boolean[])this.value).clone();
               } else if (this.value instanceof int[]) {
                  var1.value = ((int[])this.value).clone();
               } else if (this.value instanceof long[]) {
                  var1.value = ((long[])this.value).clone();
               } else if (this.value instanceof float[]) {
                  var1.value = ((float[])this.value).clone();
               } else if (this.value instanceof double[]) {
                  var1.value = ((double[])this.value).clone();
               } else if (this.value instanceof adp[]) {
                  adp[] var6;
                  adp[] var7 = new adp[(var6 = (adp[])this.value).length];
                  var1.value = var7;

                  for(var4 = 0; var4 < var6.length; ++var4) {
                     var7[var4] = (adp)var6[var4].clone();
                  }
               }
            }
         }

         return var1;
      } catch (CloneNotSupportedException var5) {
         throw new AssertionError(var5);
      }
   }

   // $FF: synthetic method
   public final Object clone() throws CloneNotSupportedException {
      return this.zzLP();
   }
}
