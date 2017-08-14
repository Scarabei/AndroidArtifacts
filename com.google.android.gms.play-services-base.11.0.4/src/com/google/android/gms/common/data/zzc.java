package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;

public class zzc {
   protected final DataHolder zzaCX;
   protected int zzaFx;
   private int zzaFy;

   public zzc(DataHolder var1, int var2) {
      this.zzaCX = (DataHolder)zzbo.zzu(var1);
      this.zzar(var2);
   }

   protected final void zzar(int var1) {
      zzbo.zzae(var1 >= 0 && var1 < this.zzaCX.zzaFG);
      this.zzaFx = var1;
      this.zzaFy = this.zzaCX.zzat(this.zzaFx);
   }

   public boolean isDataValid() {
      return !this.zzaCX.isClosed();
   }

   public final boolean zzcv(String var1) {
      return this.zzaCX.zzcv(var1);
   }

   protected final long getLong(String var1) {
      return this.zzaCX.zzb(var1, this.zzaFx, this.zzaFy);
   }

   protected final int getInteger(String var1) {
      return this.zzaCX.zzc(var1, this.zzaFx, this.zzaFy);
   }

   protected final boolean getBoolean(String var1) {
      return this.zzaCX.zze(var1, this.zzaFx, this.zzaFy);
   }

   protected final String getString(String var1) {
      return this.zzaCX.zzd(var1, this.zzaFx, this.zzaFy);
   }

   protected final float getFloat(String var1) {
      return this.zzaCX.zzf(var1, this.zzaFx, this.zzaFy);
   }

   protected final byte[] getByteArray(String var1) {
      return this.zzaCX.zzg(var1, this.zzaFx, this.zzaFy);
   }

   protected final Uri zzcw(String var1) {
      int var4 = this.zzaFy;
      int var3 = this.zzaFx;
      String var5;
      return (var5 = this.zzaCX.zzd(var1, var3, var4)) == null ? null : Uri.parse(var5);
   }

   protected final void zza(String var1, CharArrayBuffer var2) {
      this.zzaCX.zza(var1, this.zzaFx, this.zzaFy, var2);
   }

   protected final boolean zzcx(String var1) {
      return this.zzaCX.zzh(var1, this.zzaFx, this.zzaFy);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaFx, this.zzaFy, this.zzaCX});
   }

   public boolean equals(Object var1) {
      if (var1 instanceof zzc) {
         zzc var2;
         return zzbe.equal((var2 = (zzc)var1).zzaFx, this.zzaFx) && zzbe.equal(var2.zzaFy, this.zzaFy) && var2.zzaCX == this.zzaCX;
      } else {
         return false;
      }
   }
}
