package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.internal.zzadd;
import com.google.android.gms.internal.zzaff;
import com.google.android.gms.internal.zzafg;
import com.google.android.gms.internal.zzafh;
import com.google.android.gms.internal.zzafn;
import com.google.android.gms.internal.zzafp;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzahp;
import com.google.android.gms.internal.zzair;
import com.google.android.gms.internal.zzaiy;
import com.google.android.gms.internal.zzaje;
import com.google.android.gms.internal.zzcu;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzjl;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzky;
import com.google.android.gms.internal.zzlx;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zznh;
import com.google.android.gms.internal.zzon;
import com.google.android.gms.internal.zzpn;
import com.google.android.gms.internal.zzpq;
import com.google.android.gms.internal.zzpz;
import com.google.android.gms.internal.zzzn;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@zzzn
public final class zzbt implements OnGlobalLayoutListener, OnScrollChangedListener {
   final String zzvQ;
   public String zzvR;
   public final Context zzqD;
   final zzcu zzvS;
   public final zzaje zzvT;
   @Nullable
   zzbu zzvU;
   @Nullable
   public zzafp zzvV;
   @Nullable
   public zzahp zzvW;
   public zziv zzvX;
   @Nullable
   public zzaff zzvY;
   public zzafg zzvZ;
   @Nullable
   public zzafh zzwa;
   @Nullable
   zzjl zzwb;
   @Nullable
   zzjo zzwc;
   @Nullable
   zzke zzwd;
   @Nullable
   zzkk zzwe;
   @Nullable
   zzpn zzwf;
   @Nullable
   zzpq zzwg;
   SimpleArrayMap zzwh;
   SimpleArrayMap zzwi;
   zzon zzwj;
   @Nullable
   zzlx zzwk;
   @Nullable
   zzky zzwl;
   @Nullable
   zzpz zzwm;
   @Nullable
   List zzwn;
   @Nullable
   zznh zzwo;
   @Nullable
   zzadd zzwp;
   @Nullable
   List zzwq;
   @Nullable
   public zzafn zzwr;
   @Nullable
   View zzws;
   public int zzwt;
   boolean zzur;
   private HashSet zzwu;
   private int zzwv;
   private int zzww;
   private zzair zzwx;
   private boolean zzwy;
   private boolean zzwz;
   private boolean zzwA;

   public zzbt(Context var1, zziv var2, String var3, zzaje var4) {
      this(var1, var2, var3, var4, (zzcu)null);
   }

   private zzbt(Context var1, zziv var2, String var3, zzaje var4, zzcu var5) {
      this.zzwr = null;
      this.zzws = null;
      this.zzwt = 0;
      this.zzur = false;
      this.zzwu = null;
      this.zzwv = -1;
      this.zzww = -1;
      this.zzwy = true;
      this.zzwz = true;
      this.zzwA = false;
      zzmo.initialize(var1);
      if (zzbs.zzbD().zzhr() != null) {
         List var6 = zzmo.zzdK();
         if (var4.zzaaO != 0) {
            var6.add(Integer.toString(var4.zzaaO));
         }

         zzbs.zzbD().zzhr().zze(var6);
      }

      this.zzvQ = UUID.randomUUID().toString();
      if (!var2.zzAt && !var2.zzAv) {
         this.zzvU = new zzbu(var1, var3, var4.zzaP, this, this);
         this.zzvU.setMinimumWidth(var2.widthPixels);
         this.zzvU.setMinimumHeight(var2.heightPixels);
         this.zzvU.setVisibility(4);
      } else {
         this.zzvU = null;
      }

      this.zzvX = var2;
      this.zzvR = var3;
      this.zzqD = var1;
      this.zzvT = var4;
      this.zzvS = new zzcu(new zzah(this));
      this.zzwx = new zzair(200L);
      this.zzwi = new SimpleArrayMap();
   }

   public final void zza(HashSet var1) {
      this.zzwu = var1;
   }

   public final HashSet zzbZ() {
      return this.zzwu;
   }

   public final void zzca() {
      if (this.zzvY != null && this.zzvY.zzPg != null) {
         this.zzvY.zzPg.destroy();
      }

   }

   public final void zzcb() {
      if (this.zzvY != null && this.zzvY.zzMH != null) {
         try {
            this.zzvY.zzMH.destroy();
            return;
         } catch (RemoteException var1) {
            zzafr.zzaT("Could not destroy mediation adapter.");
         }
      }

   }

   public final boolean zzcc() {
      return this.zzwt == 0;
   }

   public final boolean zzcd() {
      return this.zzwt == 1;
   }

   public final void onGlobalLayout() {
      this.zzd(false);
   }

   public final void onScrollChanged() {
      this.zzd(true);
      this.zzwA = true;
   }

   private final void zzd(boolean var1) {
      if (this.zzvU != null && this.zzvY != null && this.zzvY.zzPg != null && this.zzvY.zzPg.zziw() != null) {
         if (!var1 || this.zzwx.tryAcquire()) {
            if (this.zzvY.zzPg.zziw().zzcn()) {
               int[] var2 = new int[2];
               this.zzvU.getLocationOnScreen(var2);
               zzji.zzds();
               int var3 = zzaiy.zzd(this.zzqD, var2[0]);
               zzji.zzds();
               int var4 = zzaiy.zzd(this.zzqD, var2[1]);
               if (var3 != this.zzwv || var4 != this.zzww) {
                  this.zzwv = var3;
                  this.zzww = var4;
                  this.zzvY.zzPg.zziw().zza(this.zzwv, this.zzww, !var1);
               }
            }

            View var6;
            if (this.zzvU != null && (var6 = this.zzvU.getRootView().findViewById(16908290)) != null) {
               Rect var7 = new Rect();
               Rect var8 = new Rect();
               this.zzvU.getGlobalVisibleRect(var7);
               var6.getGlobalVisibleRect(var8);
               if (var7.top != var8.top) {
                  this.zzwy = false;
               }

               if (var7.bottom != var8.bottom) {
                  this.zzwz = false;
               }
            }

         }
      }
   }

   public final String zzce() {
      if (this.zzwy && this.zzwz) {
         return "";
      } else if (this.zzwy) {
         return this.zzwA ? "top-scrollable" : "top-locked";
      } else if (this.zzwz) {
         return this.zzwA ? "bottom-scrollable" : "bottom-locked";
      } else {
         return "";
      }
   }

   public final void zze(boolean var1) {
      if (this.zzwt == 0 && this.zzvY != null && this.zzvY.zzPg != null) {
         this.zzvY.zzPg.stopLoading();
      }

      if (this.zzvV != null) {
         this.zzvV.cancel();
      }

      if (this.zzvW != null) {
         this.zzvW.cancel();
      }

      if (var1) {
         this.zzvY = null;
      }

   }
}
