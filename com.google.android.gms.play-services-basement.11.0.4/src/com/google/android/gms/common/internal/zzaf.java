package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Intent;
import java.util.Arrays;

public final class zzaf {
   private final String zzaeX;
   private final String zzaHN;
   private final ComponentName zzaHO;

   public zzaf(String var1, String var2) {
      this.zzaeX = zzbo.zzcF(var1);
      this.zzaHN = zzbo.zzcF(var2);
      this.zzaHO = null;
   }

   public zzaf(ComponentName var1) {
      this.zzaeX = null;
      this.zzaHN = null;
      this.zzaHO = (ComponentName)zzbo.zzu(var1);
   }

   public final String toString() {
      return this.zzaeX == null ? this.zzaHO.flattenToString() : this.zzaeX;
   }

   public final String getPackage() {
      return this.zzaHN;
   }

   public final ComponentName getComponentName() {
      return this.zzaHO;
   }

   public final Intent zzrB() {
      Intent var1;
      if (this.zzaeX != null) {
         var1 = (new Intent(this.zzaeX)).setPackage(this.zzaHN);
      } else {
         var1 = (new Intent()).setComponent(this.zzaHO);
      }

      return var1;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaeX, this.zzaHN, this.zzaHO});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzaf)) {
         return false;
      } else {
         zzaf var2 = (zzaf)var1;
         return zzbe.equal(this.zzaeX, var2.zzaeX) && zzbe.equal(this.zzaHN, var2.zzaHN) && zzbe.equal(this.zzaHO, var2.zzaHO);
      }
   }
}
