package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zzcq implements zzcp {
   protected MotionEvent zzpP;
   protected LinkedList zzpQ = new LinkedList();
   protected long zzpR = 0L;
   protected long zzpS = 0L;
   protected long zzpT = 0L;
   protected long zzpU = 0L;
   protected long zzpV = 0L;
   protected long zzpW = 0L;
   protected long zzpX = 0L;
   protected double zzpY;
   private double zzpZ;
   private double zzqa;
   protected float zzqb;
   protected float zzqc;
   protected float zzqd;
   protected float zzqe;
   private boolean zzqf = false;
   protected boolean zzqg = false;
   protected DisplayMetrics zzqh;

   protected zzcq(Context var1) {
      try {
         zzbv.zzw();
         this.zzqh = var1.getResources().getDisplayMetrics();
      } catch (Throwable var2) {
         ;
      }
   }

   protected abstract zzax zza(Context var1, zzau var2);

   protected abstract zzax zza(Context var1, View var2);

   protected abstract zzdf zzb(MotionEvent var1) throws zzcy;

   protected abstract long zza(StackTraceElement[] var1) throws zzcy;

   public final String zza(Context var1) {
      if (zzdg.zzS()) {
         zzme var2 = zzmo.zzFb;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var2)).booleanValue()) {
            throw new IllegalStateException("The caller must not be called from the UI thread.");
         }
      }

      return this.zza(var1, (String)null, false, (View)null, (byte[])null);
   }

   public final String zza(Context var1, byte[] var2) {
      if (zzdg.zzS()) {
         zzme var3 = zzmo.zzFb;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue()) {
            throw new IllegalStateException("The caller must not be called from the UI thread.");
         }
      }

      return this.zza(var1, (String)null, false, (View)null, var2);
   }

   public final String zza(Context var1, String var2) {
      return this.zza(var1, var2, (View)null);
   }

   public final String zza(Context var1, String var2, View var3) {
      return this.zza(var1, var2, true, var3, (byte[])null);
   }

   public final void zza(MotionEvent var1) {
      if (this.zzqf) {
         this.zzpV = this.zzpR = this.zzpS = this.zzpT = this.zzpU = 0L;
         this.zzpW = this.zzpX = 0L;
         Iterator var2 = this.zzpQ.iterator();

         while(var2.hasNext()) {
            ((MotionEvent)var2.next()).recycle();
         }

         this.zzpQ.clear();
         this.zzpP = null;
         this.zzqf = false;
      }

      zzme var3 = zzmo.zzEU;
      if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue()) {
         switch(var1.getAction()) {
         case 0:
            this.zzpY = 0.0D;
            this.zzpZ = (double)var1.getRawX();
            this.zzqa = (double)var1.getRawY();
            break;
         case 1:
         case 2:
            double var5 = (double)var1.getRawX();
            double var7 = (double)var1.getRawY();
            double var9 = var5 - this.zzpZ;
            double var11 = var7 - this.zzqa;
            this.zzpY += Math.sqrt(var9 * var9 + var11 * var11);
            this.zzpZ = var5;
            this.zzqa = var7;
         }
      }

      switch(var1.getAction()) {
      case 0:
         var3 = zzmo.zzEV;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue()) {
            this.zzqb = var1.getX();
            this.zzqc = var1.getY();
            this.zzqd = var1.getRawX();
            this.zzqe = var1.getRawY();
         }

         ++this.zzpR;
         break;
      case 1:
         this.zzpP = MotionEvent.obtain(var1);
         this.zzpQ.add(this.zzpP);
         if (this.zzpQ.size() > 6) {
            ((MotionEvent)this.zzpQ.remove()).recycle();
         }

         ++this.zzpT;

         try {
            this.zzpV = this.zza((new Throwable()).getStackTrace());
         } catch (zzcy var13) {
            ;
         }
         break;
      case 2:
         this.zzpS += (long)(var1.getHistorySize() + 1);

         try {
            zzdf var16;
            zzdf var15;
            if ((var16 = var15 = this.zzb(var1)) != null && var16.zzcd != null && var16.zzrd != null) {
               this.zzpW += var15.zzcd.longValue() + var15.zzrd.longValue();
            }

            if (this.zzqh != null && var15 != null && var15.zzcb != null && var15.zzre != null) {
               this.zzpX += var15.zzcb.longValue() + var15.zzre.longValue();
            }
         } catch (zzcy var14) {
            ;
         }
         break;
      case 3:
         ++this.zzpU;
      }

      this.zzqg = true;
   }

   public final void zza(int var1, int var2, int var3) {
      if (this.zzpP != null) {
         this.zzpP.recycle();
      }

      if (this.zzqh != null) {
         this.zzpP = MotionEvent.obtain(0L, (long)var3, 1, (float)var1 * this.zzqh.density, (float)var2 * this.zzqh.density, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
      } else {
         this.zzpP = null;
      }

      this.zzqg = false;
   }

   private final String zza(Context var1, String var2, boolean var3, View var4, byte[] var5) {
      zzau var7 = null;
      if (var5 != null && var5.length > 0) {
         try {
            var7 = (zzau)adp.zza(new zzau(), var5);
         } catch (ado var11) {
            ;
         }
      }

      String var6;
      try {
         zzax var8;
         if (var3) {
            var8 = this.zza(var1, var4);
            this.zzqf = true;
         } else {
            var8 = this.zza(var1, var7);
         }

         if (var8 != null && var8.zzLV() != 0) {
            zzme var10 = zzmo.zzEO;
            var6 = zzbv.zza(var8, var2, ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var10)).booleanValue() && !var3);
         } else {
            var6 = Integer.toString(5);
         }
      } catch (UnsupportedEncodingException | NoSuchAlgorithmException var12) {
         var6 = Integer.toString(7);
      } catch (Throwable var13) {
         var6 = Integer.toString(3);
      }

      return var6;
   }
}
