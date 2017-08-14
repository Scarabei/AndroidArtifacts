package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

@zzzn
public final class zzadg implements RewardItem {
   private final zzacv zzWw;

   public zzadg(zzacv var1) {
      this.zzWw = var1;
   }

   public final String getType() {
      if (this.zzWw == null) {
         return null;
      } else {
         try {
            return this.zzWw.getType();
         } catch (RemoteException var2) {
            zzajc.zzc("Could not forward getType to RewardItem", var2);
            return null;
         }
      }
   }

   public final int getAmount() {
      if (this.zzWw == null) {
         return 0;
      } else {
         try {
            return this.zzWw.getAmount();
         } catch (RemoteException var2) {
            zzajc.zzc("Could not forward getAmount to RewardItem", var2);
            return 0;
         }
      }
   }
}
