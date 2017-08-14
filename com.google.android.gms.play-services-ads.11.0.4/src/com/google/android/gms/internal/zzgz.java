package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.common.util.zzq;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
@TargetApi(14)
public final class zzgz extends Thread {
   private boolean mStarted = false;
   private boolean zzyx = false;
   private boolean zzak = false;
   private final Object mLock;
   private final zzgu zzyy;
   private final zzzl zzyz;
   private final int zzyA;
   private final int zzxW;
   private final int zzyB;
   private final int zzxY;
   private final int zzyC;
   private final int zzyD;
   private final int zzyE;
   private final int zzyF;
   private final String zzyG;
   private final boolean zzyH;

   public zzgz(zzgu var1, zzzl var2) {
      this.zzyy = var1;
      this.zzyz = var2;
      this.mLock = new Object();
      zzme var3 = zzmo.zzCU;
      this.zzxW = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).intValue();
      var3 = zzmo.zzCV;
      this.zzyB = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).intValue();
      var3 = zzmo.zzCW;
      this.zzxY = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).intValue();
      var3 = zzmo.zzCX;
      this.zzyC = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).intValue();
      var3 = zzmo.zzDa;
      this.zzyD = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).intValue();
      var3 = zzmo.zzDc;
      this.zzyE = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).intValue();
      var3 = zzmo.zzDd;
      this.zzyF = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).intValue();
      var3 = zzmo.zzCY;
      this.zzyA = ((Integer)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).intValue();
      var3 = zzmo.zzDf;
      this.zzyG = (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3);
      var3 = zzmo.zzDh;
      this.zzyH = ((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var3)).booleanValue();
      this.setName("ContentFetchTask");
   }

   public final void zzcM() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         if (this.mStarted) {
            zzafr.zzaC("Content hash thread already started, quiting...");
            return;
         }

         this.mStarted = true;
      }

      this.start();
   }

   public final void run() {
      while(true) {
         try {
            if (zzcN()) {
               Activity var1;
               if ((var1 = com.google.android.gms.ads.internal.zzbs.zzbC().getActivity()) == null) {
                  zzafr.zzaC("ContentFetchThread: no activity. Sleeping.");
                  this.zzcP();
               } else {
                  Activity var4 = var1;
                  if (var1 != null) {
                     View var5 = null;

                     try {
                        if (var4.getWindow() != null && var4.getWindow().getDecorView() != null) {
                           var5 = var4.getWindow().getDecorView().findViewById(16908290);
                        }
                     } catch (Throwable var10) {
                        com.google.android.gms.ads.internal.zzbs.zzbD().zza(var10, "ContentFetchTask.extractContent");
                        zzafr.zzaC("Failed getting root view of activity. Content not extracted.");
                     }

                     if (var5 != null && var5 != null) {
                        var5.post(new zzha(this, var5));
                     }
                  }
               }
            } else {
               zzafr.zzaC("ContentFetchTask: sleeping");
               this.zzcP();
            }

            Thread.sleep((long)(this.zzyA * 1000));
         } catch (InterruptedException var11) {
            zzafr.zzb("Error in ContentFetchTask", var11);
         } catch (Throwable var12) {
            zzafr.zzb("Error in ContentFetchTask", var12);
            this.zzyz.zza(var12, "ContentFetchTask.run");
         }

         Object var14 = this.mLock;
         synchronized(this.mLock) {
            while(this.zzyx) {
               try {
                  zzafr.zzaC("ContentFetchTask: waiting");
                  this.mLock.wait();
               } catch (InterruptedException var9) {
                  ;
               }
            }
         }
      }
   }

   final void zzf(View var1) {
      try {
         zzgt var2 = new zzgt(this.zzxW, this.zzyB, this.zzxY, this.zzyC, this.zzyD, this.zzyE, this.zzyF);
         zzhd var3 = this.zza(var1, var2);
         var2.zzcJ();
         if (var3.zzyP != 0 || var3.zzyQ != 0) {
            if (var3.zzyQ != 0 || var2.zzcK() != 0) {
               if (var3.zzyQ != 0 || !this.zzyy.zza(var2)) {
                  this.zzyy.zzc(var2);
               }
            }
         }
      } catch (Exception var4) {
         zzafr.zzb("Exception in fetchContentOnUIThread", var4);
         this.zzyz.zza(var4, "ContentFetchTask.fetchContent");
      }
   }

   private static boolean zzcN() {
      // $FF: Couldn't be decompiled
   }

   private final zzhd zza(@Nullable View var1, zzgt var2) {
      if (var1 == null) {
         return new zzhd(this, 0, 0);
      } else {
         Context var3;
         if ((var3 = com.google.android.gms.ads.internal.zzbs.zzbC().getContext()) != null) {
            Resources var10000 = var3.getResources();
            zzme var11 = zzmo.zzDe;
            int var4 = var10000.getIdentifier((String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var11), "id", var3.getPackageName());
            String var5 = (String)var1.getTag(var4);
            if (!TextUtils.isEmpty(this.zzyG) && var5 != null && var5.equals(this.zzyG)) {
               return new zzhd(this, 0, 0);
            }
         }

         Rect var14 = new Rect();
         boolean var15 = var1.getGlobalVisibleRect(var14);
         if (var1 instanceof TextView && !(var1 instanceof EditText)) {
            CharSequence var16;
            if (!TextUtils.isEmpty(var16 = ((TextView)var1).getText())) {
               String var17 = var16.toString();
               var2.zzb(var17, var15, var1.getX(), var1.getY(), (float)var1.getWidth(), (float)var1.getHeight());
               return new zzhd(this, 1, 0);
            } else {
               return new zzhd(this, 0, 0);
            }
         } else if (var1 instanceof WebView && !(var1 instanceof zzaka)) {
            var2.zzcI();
            WebView var12 = (WebView)var1;
            boolean var18;
            if (!zzq.zzsc()) {
               var18 = false;
            } else {
               var2.zzcI();
               var12.post(new zzhb(this, var2, var12, var15));
               var18 = true;
            }

            return var18 ? new zzhd(this, 0, 1) : new zzhd(this, 0, 0);
         } else if (!(var1 instanceof ViewGroup)) {
            return new zzhd(this, 0, 0);
         } else {
            ViewGroup var6 = (ViewGroup)var1;
            int var7 = 0;
            int var8 = 0;

            for(int var9 = 0; var9 < var6.getChildCount(); ++var9) {
               zzhd var10 = this.zza(var6.getChildAt(var9), var2);
               var7 += var10.zzyP;
               var8 += var10.zzyQ;
            }

            return new zzhd(this, var7, var8);
         }
      }
   }

   final void zza(zzgt var1, WebView var2, String var3, boolean var4) {
      var1.zzcH();

      try {
         if (!TextUtils.isEmpty(var3)) {
            String var5 = (new JSONObject(var3)).optString("text");
            if (!this.zzyH && !TextUtils.isEmpty(var2.getTitle())) {
               String var6 = String.valueOf(var2.getTitle());
               var1.zza((new StringBuilder(1 + String.valueOf(var6).length() + String.valueOf(var5).length())).append(var6).append("\n").append(var5).toString(), var4, var2.getX(), var2.getY(), (float)var2.getWidth(), (float)var2.getHeight());
            } else {
               var1.zza(var5, var4, var2.getX(), var2.getY(), (float)var2.getWidth(), (float)var2.getHeight());
            }
         }

         if (var1.zzcC()) {
            this.zzyy.zzb(var1);
         }

      } catch (JSONException var7) {
         zzafr.zzaC("Json string may be malformed.");
      } catch (Throwable var8) {
         zzafr.zza("Failed to get webview content.", var8);
         this.zzyz.zza(var8, "ContentFetchTask.processWebViewContent");
      }
   }

   public final zzgt zzcO() {
      return this.zzyy.zzcL();
   }

   public final void wakeup() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzyx = false;
         this.mLock.notifyAll();
         zzafr.zzaC("ContentFetchThread: wakeup");
      }
   }

   private final void zzcP() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         this.zzyx = true;
         boolean var2 = this.zzyx;
         zzafr.zzaC((new StringBuilder(42)).append("ContentFetchThread: paused, mPause = ").append(var2).toString());
      }
   }

   public final boolean zzcQ() {
      return this.zzyx;
   }
}
