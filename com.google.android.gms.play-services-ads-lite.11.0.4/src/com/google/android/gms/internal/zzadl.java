package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.dynamic.zzn;

@zzzn
public final class zzadl implements RewardedVideoAd {
   private final zzacy zzWx;
   private final Context mContext;
   private final Object mLock = new Object();
   private RewardedVideoAdListener zzcS;

   public zzadl(Context var1, zzacy var2) {
      this.zzWx = var2;
      this.mContext = var1;
   }

   public final void loadAd(String var1, AdRequest var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzWx != null) {
            try {
               zzacy var10000 = this.zzWx;
               Context var10001 = this.mContext;
               zzla var7 = var2.zzab();
               zzir var9 = zziu.zza(var10001, var7);
               var10000.zza(new zzadj(var9, var1));
            } catch (RemoteException var10) {
               zzajc.zzc("Could not forward loadAd to RewardedVideoAd", var10);
            }

         }
      }
   }

   public final void show() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzWx != null) {
            try {
               this.zzWx.show();
            } catch (RemoteException var4) {
               zzajc.zzc("Could not forward show to RewardedVideoAd", var4);
            }

         }
      }
   }

   public final void setRewardedVideoAdListener(RewardedVideoAdListener var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzcS = var1;
         if (this.zzWx != null) {
            try {
               this.zzWx.zza((zzadd)(new zzadi(var1)));
            } catch (RemoteException var5) {
               zzajc.zzc("Could not forward setRewardedVideoAdListener to RewardedVideoAd", var5);
            }
         }

      }
   }

   public final void setUserId(String var1) {
      zzajc.zzaT("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
   }

   public final boolean isLoaded() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzWx == null) {
            return false;
         } else {
            boolean var10000;
            try {
               var10000 = this.zzWx.isLoaded();
            } catch (RemoteException var4) {
               zzajc.zzc("Could not forward isLoaded to RewardedVideoAd", var4);
               return false;
            }

            return var10000;
         }
      }
   }

   public final void pause() {
      this.pause((Context)null);
   }

   public final void pause(Context var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzWx != null) {
            try {
               this.zzWx.zzf(zzn.zzw(var1));
            } catch (RemoteException var5) {
               zzajc.zzc("Could not forward pause to RewardedVideoAd", var5);
            }

         }
      }
   }

   public final void resume() {
      this.resume((Context)null);
   }

   public final void resume(Context var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzWx != null) {
            try {
               this.zzWx.zzg(zzn.zzw(var1));
            } catch (RemoteException var5) {
               zzajc.zzc("Could not forward resume to RewardedVideoAd", var5);
            }

         }
      }
   }

   public final void destroy() {
      this.destroy((Context)null);
   }

   public final void destroy(Context var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzWx != null) {
            try {
               this.zzWx.zzh(zzn.zzw(var1));
            } catch (RemoteException var5) {
               zzajc.zzc("Could not forward destroy to RewardedVideoAd", var5);
            }

         }
      }
   }

   public final RewardedVideoAdListener getRewardedVideoAdListener() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzcS;
      }
   }

   public final String getUserId() {
      zzajc.zzaT("RewardedVideoAd.getUserId() is deprecated. Please do not call this method.");
      return null;
   }

   public final String getMediationAdapterClassName() {
      try {
         if (this.zzWx != null) {
            return this.zzWx.getMediationAdapterClassName();
         }
      } catch (RemoteException var2) {
         zzajc.zzc("Failed to get the mediation adapter class name.", var2);
      }

      return null;
   }

   public final void setImmersiveMode(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzWx != null) {
            try {
               this.zzWx.setImmersiveMode(var1);
            } catch (RemoteException var5) {
               zzajc.zzc("Could not forward setImmersiveMode to RewardedVideoAd", var5);
            }
         }

      }
   }
}
