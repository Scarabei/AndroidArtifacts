package com.google.android.gms.internal;

import java.util.Map;

final class zzaih extends zzar {
   // $FF: synthetic field
   private byte[] zzaah;
   // $FF: synthetic field
   private Map zzaai;

   zzaih(zzaie var1, int var2, String var3, zzv var4, zzu var5, byte[] var6, Map var7) {
      this.zzaah = var6;
      this.zzaai = var7;
      super(var2, var3, var4, var5);
   }

   public final byte[] zzg() throws zza {
      return this.zzaah == null ? super.zzg() : this.zzaah;
   }

   public final Map getHeaders() throws zza {
      return this.zzaai == null ? super.getHeaders() : this.zzaai;
   }
}
