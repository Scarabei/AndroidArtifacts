package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzk {
   private String zzaPN;

   public static zzk zzcS(String var0) {
      zzbo.zzaf(var0 == null || !var0.isEmpty());
      return var0 == null ? null : new zzk(var0);
   }

   private zzk(String var1) {
      this.zzaPN = var1.toLowerCase();
   }

   public final boolean isFolder() {
      return this.zzaPN.equals("application/vnd.google-apps.folder");
   }

   public final boolean zzts() {
      return this.zzaPN.startsWith("application/vnd.google-apps");
   }

   public final boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else if (var1 == this) {
         return true;
      } else if (var1.getClass() != this.getClass()) {
         return false;
      } else {
         zzk var2 = (zzk)var1;
         return this.zzaPN.equals(var2.zzaPN);
      }
   }

   public final int hashCode() {
      return this.zzaPN.hashCode();
   }

   public final String toString() {
      return this.zzaPN;
   }
}
