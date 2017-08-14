package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.formats.AdChoicesView;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@zzzn
public final class zzop extends zzox implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener {
   private static String[] zzIs = new String[]{"2011", "1009"};
   private final Object mLock = new Object();
   private final FrameLayout zzIt;
   @Nullable
   FrameLayout zzss;
   private Map zzIu = new HashMap();
   @Nullable
   private View zzIv;
   @Nullable
   private zzny zzHM;
   private boolean zzIw = false;
   private Point zzIx = new Point();
   private Point zzIy = new Point();
   private WeakReference zzIz = new WeakReference((Object)null);

   public zzop(FrameLayout var1, FrameLayout var2) {
      this.zzIt = var1;
      this.zzss = var2;
      com.google.android.gms.ads.internal.zzbs.zzbX();
      zzajv.zza(this.zzIt, (OnGlobalLayoutListener)this);
      com.google.android.gms.ads.internal.zzbs.zzbX();
      zzajv.zza(this.zzIt, (OnScrollChangedListener)this);
      this.zzIt.setOnTouchListener(this);
      this.zzIt.setOnClickListener(this);
      zzmo.initialize(this.zzIt.getContext());
   }

   public final void zzd(String var1, IObjectWrapper var2) {
      View var3 = (View)zzn.zzE(var2);
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzIu != null) {
            if (var3 == null) {
               this.zzIu.remove(var1);
            } else {
               this.zzIu.put(var1, new WeakReference(var3));
               if ("1098".equals(var1)) {
                  return;
               }

               var3.setOnTouchListener(this);
               var3.setClickable(true);
               var3.setOnClickListener(this);
            }

         }
      }
   }

   public final IObjectWrapper zzL(String var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         WeakReference var3;
         return this.zzIu == null ? null : zzn.zzw((var3 = (WeakReference)this.zzIu.get(var1)) == null ? null : (View)var3.get());
      }
   }

   private final void zzg(@Nullable View var1) {
      if (this.zzHM != null) {
         zzny var2;
         if (this.zzHM instanceof zznx) {
            var2 = ((zznx)this.zzHM).zzer();
         } else {
            var2 = this.zzHM;
         }

         if (var2 != null) {
            var2.zzg(var1);
         }
      }

   }

   public final void zze(IObjectWrapper var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         this.zzg((View)null);
         Object var3;
         if (!((var3 = zzn.zzE(var1)) instanceof zzoc)) {
            zzafr.zzaT("Not an instance of native engine. This is most likely a transient error");
         } else {
            if (this.zzss != null) {
               this.zzss.setLayoutParams(new LayoutParams(0, 0));
               this.zzIt.requestLayout();
            }

            this.zzIw = true;
            zzoc var4 = (zzoc)var3;
            zzme var6;
            if (this.zzHM != null) {
               var6 = zzmo.zzFw;
               if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
                  this.zzHM.zzb(this.zzIt, this.zzIu);
               }
            }

            zzoc var7;
            if (this.zzHM instanceof zzoc && (var7 = (zzoc)this.zzHM) != null && var7.getContext() != null && com.google.android.gms.ads.internal.zzbs.zzbY().zzr(this.zzIt.getContext())) {
               zzaev var8;
               if ((var8 = var7.zzew()) != null) {
                  var8.zzu(false);
               }

               zzge var9;
               if ((var9 = (zzge)this.zzIz.get()) != null && var8 != null) {
                  var9.zzb(var8);
               }
            }

            if (this.zzHM instanceof zznx && ((zznx)this.zzHM).zzeq()) {
               ((zznx)this.zzHM).zzc(var4);
            } else {
               this.zzHM = var4;
               if (var4 instanceof zznx) {
                  ((zznx)var4).zzc((zzny)null);
               }
            }

            var6 = zzmo.zzFw;
            if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var6)).booleanValue()) {
               this.zzss.setClickable(false);
            }

            this.zzss.removeAllViews();
            boolean var16 = var4.zzep();
            ViewGroup var18 = null;
            if (var16 && this.zzIu != null) {
               WeakReference var10;
               View var10000 = (var10 = (WeakReference)this.zzIu.get("1098")) != null ? (View)var10.get() : null;
               View var11 = var10000;
               if (var10000 instanceof ViewGroup) {
                  var18 = (ViewGroup)var11;
               }
            }

            boolean var19 = var16 && var18 != null;
            this.zzIv = var4.zza(this, var19);
            if (this.zzIv != null) {
               if (this.zzIu != null) {
                  this.zzIu.put("1007", new WeakReference(this.zzIv));
               }

               if (var19) {
                  var18.removeAllViews();
                  var18.addView(this.zzIv);
               } else {
                  Context var12 = var4.getContext();
                  AdChoicesView var20;
                  (var20 = new AdChoicesView(var12)).setLayoutParams(new LayoutParams(-1, -1));
                  var20.addView(this.zzIv);
                  if (this.zzss != null) {
                     this.zzss.addView(var20);
                  }
               }
            }

            var4.zza((View)this.zzIt, (Map)this.zzIu, (OnTouchListener)this, (OnClickListener)this);
            zzagz.zzZr.post(new zzoq(this, var4));
            this.zzg(this.zzIt);
            if (this.zzHM instanceof zzoc && (var7 = (zzoc)this.zzHM) != null && var7.getContext() != null && com.google.android.gms.ads.internal.zzbs.zzbY().zzr(this.zzIt.getContext())) {
               zzge var17;
               if ((var17 = (zzge)this.zzIz.get()) == null) {
                  var17 = new zzge(this.zzIt.getContext(), this.zzIt);
                  this.zzIz = new WeakReference(var17);
               }

               var17.zza((zzgi)var7.zzew());
            }

         }
      }
   }

   public final void onClick(View var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHM != null) {
            Bundle var3;
            (var3 = new Bundle()).putFloat("x", (float)this.zzm(this.zzIx.x));
            var3.putFloat("y", (float)this.zzm(this.zzIx.y));
            var3.putFloat("start_x", (float)this.zzm(this.zzIy.x));
            var3.putFloat("start_y", (float)this.zzm(this.zzIy.y));
            if (this.zzIv != null && this.zzIv.equals(var1)) {
               if (this.zzHM instanceof zznx) {
                  if (((zznx)this.zzHM).zzer() != null) {
                     ((zznx)this.zzHM).zzer().zza(var1, "1007", var3, this.zzIu, this.zzIt);
                  }
               } else {
                  this.zzHM.zza(var1, "1007", var3, this.zzIu, this.zzIt);
               }
            } else {
               this.zzHM.zza(var1, this.zzIu, var3, this.zzIt);
            }

         }
      }
   }

   public final void onGlobalLayout() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzIw) {
            int var2 = this.zzIt.getMeasuredWidth();
            int var3 = this.zzIt.getMeasuredHeight();
            if (var2 != 0 && var3 != 0 && this.zzss != null) {
               this.zzss.setLayoutParams(new LayoutParams(var2, var3));
               this.zzIw = false;
            }
         }

         if (this.zzHM != null) {
            this.zzHM.zzc(this.zzIt, this.zzIu);
         }

      }
   }

   public final void onScrollChanged() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHM != null) {
            this.zzHM.zzc(this.zzIt, this.zzIu);
         }

      }
   }

   public final boolean onTouch(View var1, MotionEvent var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzHM == null) {
            return false;
         } else {
            int[] var9 = new int[2];
            this.zzIt.getLocationOnScreen(var9);
            float var10 = var2.getRawX() - (float)var9[0];
            float var11 = var2.getRawY() - (float)var9[1];
            Point var4 = new Point((int)var10, (int)var11);
            this.zzIx = var4;
            if (var2.getAction() == 0) {
               this.zzIy = var4;
            }

            MotionEvent var5;
            (var5 = MotionEvent.obtain(var2)).setLocation((float)var4.x, (float)var4.y);
            this.zzHM.zzd(var5);
            var5.recycle();
            return false;
         }
      }
   }

   public final void destroy() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzss != null) {
            this.zzss.removeAllViews();
         }

         this.zzss = null;
         this.zzIu = null;
         this.zzIv = null;
         this.zzHM = null;
         this.zzIx = null;
         this.zzIy = null;
         this.zzIz = null;
      }
   }

   public final void zzb(IObjectWrapper var1, int var2) {
      zzge var3;
      if (com.google.android.gms.ads.internal.zzbs.zzbY().zzr(this.zzIt.getContext()) && this.zzIz != null && (var3 = (zzge)this.zzIz.get()) != null) {
         var3.zzcB();
      }

   }

   private final int zzm(int var1) {
      zzji.zzds();
      return zzaiy.zzd(this.zzHM.getContext(), var1);
   }

   private final void zza(zzoc var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         View var10000;
         label33: {
            var1.zzd(this.zzIu);
            zzop var6 = this;
            if (this.zzIu != null) {
               String[] var7 = zzIs;
               int var8 = zzIs.length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  String var10 = var7[var9];
                  WeakReference var11;
                  if ((var11 = (WeakReference)var6.zzIu.get(var10)) != null) {
                     var10000 = (View)var11.get();
                     break label33;
                  }
               }
            }

            var10000 = null;
         }

         View var3 = var10000;
         if (!(var10000 instanceof FrameLayout)) {
            var1.zzev();
         } else {
            zzor var4 = new zzor(this, var3);
            var1.zza((View)var3, (zznw)var4);
         }
      }
   }

   // $FF: synthetic method
   static void zza(zzop var0, zzoc var1) {
      var0.zza(var1);
   }
}
