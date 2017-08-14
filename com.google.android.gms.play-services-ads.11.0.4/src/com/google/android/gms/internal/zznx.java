package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.util.Map;
import org.json.JSONObject;

@zzzn
public final class zznx extends zzoc {
   @Nullable
   private zzvc zzHV;
   @Nullable
   private zzvf zzHW;
   private final zznz zzHX;
   @Nullable
   private zzny zzHY;
   private boolean zzHZ;
   private Object mLock;

   public zznx(Context var1, zznz var2, zzcu var3, zzvc var4, zzoa var5) {
      this(var1, var2, var3, var5);
      this.zzHV = var4;
   }

   public zznx(Context var1, zznz var2, zzcu var3, zzvf var4, zzoa var5) {
      this(var1, var2, var3, var5);
      this.zzHW = var4;
   }

   private zznx(Context var1, zznz var2, zzcu var3, zzoa var4) {
      super(var1, var2, (zzyh)null, var3, (JSONObject)null, var4, (zzaje)null, (String)null);
      this.zzHZ = false;
      this.mLock = new Object();
      this.zzHX = var2;
   }

   @Nullable
   public final View zza(OnClickListener var1, boolean var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHY != null) {
            return this.zzHY.zza(var1, var2);
         } else {
            IObjectWrapper var4 = null;

            try {
               if (this.zzHV != null) {
                  var4 = this.zzHV.zzfw();
               } else if (this.zzHW != null) {
                  var4 = this.zzHW.zzfw();
               }
            } catch (RemoteException var7) {
               zzafr.zzc("Failed to call getAdChoicesContent", var7);
            }

            return var4 != null ? (View)zzn.zzE(var4) : null;
         }
      }
   }

   public final boolean zzep() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzHY != null ? this.zzHY.zzep() : this.zzHX.zzaP();
      }
   }

   public final void zza(View var1, Map var2) {
      zzbo.zzcz("recordImpression must be called on the main UI thread.");
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         super.zzId = true;
         if (this.zzHY != null) {
            this.zzHY.zza(var1, var2);
            this.zzHX.recordImpression();
         } else {
            try {
               if (this.zzHV != null && !this.zzHV.getOverrideImpressionRecording()) {
                  this.zzHV.recordImpression();
                  this.zzHX.recordImpression();
               } else if (this.zzHW != null && !this.zzHW.getOverrideImpressionRecording()) {
                  this.zzHW.recordImpression();
                  this.zzHX.recordImpression();
               }
            } catch (RemoteException var6) {
               zzafr.zzc("Failed to call recordImpression", var6);
            }
         }

      }
   }

   public final void zza(View var1, Map var2, Bundle var3, View var4) {
      zzbo.zzcz("performClick must be called on the main UI thread.");
      Object var5 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHY != null) {
            this.zzHY.zza(var1, var2, var3, var4);
            this.zzHX.onAdClicked();
         } else {
            try {
               if (this.zzHV != null && !this.zzHV.getOverrideClickHandling()) {
                  this.zzHV.zzl(zzn.zzw(var1));
                  this.zzHX.onAdClicked();
               }

               if (this.zzHW != null && !this.zzHW.getOverrideClickHandling()) {
                  this.zzHW.zzl(zzn.zzw(var1));
                  this.zzHX.onAdClicked();
               }
            } catch (RemoteException var8) {
               zzafr.zzc("Failed to call performClick", var8);
            }
         }

      }
   }

   public final void zza(View var1, Map var2, OnTouchListener var3, OnClickListener var4) {
      Object var5 = this.mLock;
      synchronized(this.mLock) {
         this.zzHZ = true;

         try {
            if (this.zzHV != null) {
               this.zzHV.zzm(zzn.zzw(var1));
            } else if (this.zzHW != null) {
               this.zzHW.zzm(zzn.zzw(var1));
            }
         } catch (RemoteException var8) {
            zzafr.zzc("Failed to call prepareAd", var8);
         }

         this.zzHZ = false;
      }
   }

   public final void zzb(View var1, Map var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         try {
            if (this.zzHV != null) {
               this.zzHV.zzn(zzn.zzw(var1));
            } else if (this.zzHW != null) {
               this.zzHW.zzn(zzn.zzw(var1));
            }
         } catch (RemoteException var6) {
            zzafr.zzc("Failed to call untrackView", var6);
         }

      }
   }

   public final boolean zzeq() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzHZ;
      }
   }

   public final void zzc(@Nullable zzny var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzHY = var1;
      }
   }

   public final zzny zzer() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzHY;
      }
   }

   @Nullable
   public final zzaka zzes() {
      return null;
   }

   public final void zzet() {
   }
}
