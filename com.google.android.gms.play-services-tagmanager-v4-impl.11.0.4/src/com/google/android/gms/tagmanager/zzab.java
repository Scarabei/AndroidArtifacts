package com.google.android.gms.tagmanager;

final class zzab implements zzac {
   private Long zzbDY;
   // $FF: synthetic field
   private boolean zzbDZ;
   // $FF: synthetic field
   private zzy zzbDX;

   zzab(zzy var1, boolean var2) {
      this.zzbDX = var1;
      this.zzbDZ = var2;
      super();
   }

   public final boolean zzb(Container var1) {
      if (this.zzbDZ) {
         long var10000 = var1.getLastRefreshTime();
         if (this.zzbDY == null) {
            this.zzbDY = zzy.zzc(this.zzbDX).zzAS();
         }

         return var10000 + this.zzbDY.longValue() >= zzy.zzd(this.zzbDX).currentTimeMillis();
      } else {
         return !var1.isDefault();
      }
   }
}
