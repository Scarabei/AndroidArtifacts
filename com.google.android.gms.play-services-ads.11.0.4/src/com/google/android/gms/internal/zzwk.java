package com.google.android.gms.internal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.util.zzf;
import java.util.Map;
import java.util.Set;

@zzzn
public final class zzwk extends zzwu {
   private static Set zzNy = zzf.zzb(new String[]{"top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"});
   private String zzNz = "top-right";
   private boolean zzNA = true;
   private int zzNB = 0;
   private int zzNC = 0;
   private int zzrX = -1;
   private int zzND = 0;
   private int zzNE = 0;
   private int zzrW = -1;
   private final Object mLock = new Object();
   private final zzaka zzJH;
   private final Activity zzNo;
   private zziv zzuZ;
   private ImageView zzNF;
   private LinearLayout zzNG;
   private zzwv zzNH;
   private PopupWindow zzNI;
   private RelativeLayout zzNJ;
   private ViewGroup zzNK;

   public zzwk(zzaka var1, zzwv var2) {
      super(var1, "resize");
      this.zzJH = var1;
      this.zzNo = var1.zzis();
      this.zzNH = var2;
   }

   public final void execute(Map var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzNo == null) {
            this.zzan("Not an activity context. Cannot resize.");
         } else if (this.zzJH.zzam() == null) {
            this.zzan("Webview is not yet available, size is not set.");
         } else if (this.zzJH.zzam().zzAt) {
            this.zzan("Is interstitial. Cannot resize an interstitial.");
         } else if (this.zzJH.zziA()) {
            this.zzan("Cannot resize an expanded banner.");
         } else {
            if (!TextUtils.isEmpty((CharSequence)var1.get("width"))) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               this.zzrW = zzagz.zzaJ((String)var1.get("width"));
            }

            if (!TextUtils.isEmpty((CharSequence)var1.get("height"))) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               this.zzrX = zzagz.zzaJ((String)var1.get("height"));
            }

            if (!TextUtils.isEmpty((CharSequence)var1.get("offsetX"))) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               this.zzND = zzagz.zzaJ((String)var1.get("offsetX"));
            }

            if (!TextUtils.isEmpty((CharSequence)var1.get("offsetY"))) {
               com.google.android.gms.ads.internal.zzbs.zzbz();
               this.zzNE = zzagz.zzaJ((String)var1.get("offsetY"));
            }

            if (!TextUtils.isEmpty((CharSequence)var1.get("allowOffscreen"))) {
               this.zzNA = Boolean.parseBoolean((String)var1.get("allowOffscreen"));
            }

            String var14;
            if (!TextUtils.isEmpty(var14 = (String)var1.get("customClosePosition"))) {
               this.zzNz = var14;
            }

            if (this.zzrW < 0 || this.zzrX < 0) {
               this.zzan("Invalid width and height options. Cannot resize.");
            } else {
               Window var3;
               if ((var3 = this.zzNo.getWindow()) != null && var3.getDecorView() != null) {
                  int[] var4;
                  if ((var4 = this.zzfB()) == null) {
                     this.zzan("Resize location out of screen or close button is not visible.");
                  } else {
                     zzji.zzds();
                     int var5 = zzaiy.zzc(this.zzNo, this.zzrW);
                     zzji.zzds();
                     int var6 = zzaiy.zzc(this.zzNo, this.zzrX);
                     ViewParent var7;
                     if ((var7 = this.zzJH.getView().getParent()) != null && var7 instanceof ViewGroup) {
                        ((ViewGroup)var7).removeView(this.zzJH.getView());
                        if (this.zzNI == null) {
                           this.zzNK = (ViewGroup)var7;
                           com.google.android.gms.ads.internal.zzbs.zzbz();
                           Bitmap var8 = zzagz.zzl(this.zzJH.getView());
                           this.zzNF = new ImageView(this.zzNo);
                           this.zzNF.setImageBitmap(var8);
                           this.zzuZ = this.zzJH.zzam();
                           this.zzNK.addView(this.zzNF);
                        } else {
                           this.zzNI.dismiss();
                        }

                        this.zzNJ = new RelativeLayout(this.zzNo);
                        this.zzNJ.setBackgroundColor(0);
                        this.zzNJ.setLayoutParams(new LayoutParams(var5, var6));
                        com.google.android.gms.ads.internal.zzbs.zzbz();
                        this.zzNI = zzagz.zza(this.zzNJ, var5, var6, false);
                        this.zzNI.setOutsideTouchable(true);
                        this.zzNI.setTouchable(true);
                        this.zzNI.setClippingEnabled(!this.zzNA);
                        this.zzNJ.addView(this.zzJH.getView(), -1, -1);
                        this.zzNG = new LinearLayout(this.zzNo);
                        zzji.zzds();
                        int var10002 = zzaiy.zzc(this.zzNo, 50);
                        zzji.zzds();
                        android.widget.RelativeLayout.LayoutParams var17 = new android.widget.RelativeLayout.LayoutParams(var10002, zzaiy.zzc(this.zzNo, 50));
                        String var9 = this.zzNz;
                        byte var10 = -1;
                        switch(var9.hashCode()) {
                        case -1364013995:
                           if (var9.equals("center")) {
                              var10 = 2;
                           }
                           break;
                        case -1012429441:
                           if (var9.equals("top-left")) {
                              var10 = 0;
                           }
                           break;
                        case -655373719:
                           if (var9.equals("bottom-left")) {
                              var10 = 3;
                           }
                           break;
                        case 1163912186:
                           if (var9.equals("bottom-right")) {
                              var10 = 5;
                           }
                           break;
                        case 1288627767:
                           if (var9.equals("bottom-center")) {
                              var10 = 4;
                           }
                           break;
                        case 1755462605:
                           if (var9.equals("top-center")) {
                              var10 = 1;
                           }
                        }

                        switch(var10) {
                        case 0:
                           var17.addRule(10);
                           var17.addRule(9);
                           break;
                        case 1:
                           var17.addRule(10);
                           var17.addRule(14);
                           break;
                        case 2:
                           var17.addRule(13);
                           break;
                        case 3:
                           var17.addRule(12);
                           var17.addRule(9);
                           break;
                        case 4:
                           var17.addRule(12);
                           var17.addRule(14);
                           break;
                        case 5:
                           var17.addRule(12);
                           var17.addRule(11);
                           break;
                        default:
                           var17.addRule(10);
                           var17.addRule(11);
                        }

                        this.zzNG.setOnClickListener(new zzwl(this));
                        this.zzNG.setContentDescription("Close button");
                        this.zzNJ.addView(this.zzNG, var17);

                        try {
                           PopupWindow var10000 = this.zzNI;
                           View var19 = var3.getDecorView();
                           zzji.zzds();
                           int var22 = zzaiy.zzc(this.zzNo, var4[0]);
                           zzji.zzds();
                           var10000.showAtLocation(var19, 0, var22, zzaiy.zzc(this.zzNo, var4[1]));
                        } catch (RuntimeException var15) {
                           String var20 = String.valueOf(var15.getMessage());
                           String var10001;
                           if (var20.length() != 0) {
                              var10001 = "Cannot show popup window: ".concat(var20);
                           } else {
                              String var10003 = new String;
                              var10001 = var10003;
                              var10003.<init>("Cannot show popup window: ");
                           }

                           this.zzan(var10001);
                           this.zzNJ.removeView(this.zzJH.getView());
                           if (this.zzNK != null) {
                              this.zzNK.removeView(this.zzNF);
                              this.zzNK.addView(this.zzJH.getView());
                              this.zzJH.zza(this.zzuZ);
                           }

                           return;
                        }

                        int var21 = var4[0];
                        int var18 = var4[1];
                        int var13 = var21;
                        if (this.zzNH != null) {
                           this.zzNH.zza(var13, var18, this.zzrW, this.zzrX);
                        }

                        this.zzJH.zza(new zziv(this.zzNo, new AdSize(this.zzrW, this.zzrX)));
                        this.zza(var4[0], var4[1]);
                        this.zzap("resized");
                     } else {
                        this.zzan("Webview is detached, probably in the middle of a resize or expand.");
                     }
                  }
               } else {
                  this.zzan("Activity context is not ready, cannot get window or decor view.");
               }
            }
         }
      }
   }

   public final void zzk(boolean var1) {
      Object var2 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzNI != null) {
            this.zzNI.dismiss();
            this.zzNJ.removeView(this.zzJH.getView());
            if (this.zzNK != null) {
               this.zzNK.removeView(this.zzNF);
               this.zzNK.addView(this.zzJH.getView());
               this.zzJH.zza(this.zzuZ);
            }

            if (var1) {
               this.zzap("default");
               if (this.zzNH != null) {
                  this.zzNH.zzaN();
               }
            }

            this.zzNI = null;
            this.zzNJ = null;
            this.zzNK = null;
            this.zzNG = null;
         }

      }
   }

   private final int[] zzfB() {
      int[] var7 = com.google.android.gms.ads.internal.zzbs.zzbz().zzg(this.zzNo);
      int[] var8 = com.google.android.gms.ads.internal.zzbs.zzbz().zzh(this.zzNo);
      int var9 = var7[0];
      int var10 = var7[1];
      boolean var10000;
      if (this.zzrW >= 50 && this.zzrW <= var9) {
         if (this.zzrX >= 50 && this.zzrX <= var10) {
            if (this.zzrX == var10 && this.zzrW == var9) {
               zzafr.zzaT("Cannot resize to a full-screen ad.");
               var10000 = false;
            } else {
               label109: {
                  if (this.zzNA) {
                     String var13 = this.zzNz;
                     byte var14 = -1;
                     switch(var13.hashCode()) {
                     case -1364013995:
                        if (var13.equals("center")) {
                           var14 = 2;
                        }
                        break;
                     case -1012429441:
                        if (var13.equals("top-left")) {
                           var14 = 0;
                        }
                        break;
                     case -655373719:
                        if (var13.equals("bottom-left")) {
                           var14 = 3;
                        }
                        break;
                     case 1163912186:
                        if (var13.equals("bottom-right")) {
                           var14 = 5;
                        }
                        break;
                     case 1288627767:
                        if (var13.equals("bottom-center")) {
                           var14 = 4;
                        }
                        break;
                     case 1755462605:
                        if (var13.equals("top-center")) {
                           var14 = 1;
                        }
                     }

                     int var11;
                     int var12;
                     switch(var14) {
                     case 0:
                        var11 = this.zzNB + this.zzND;
                        var12 = this.zzNC + this.zzNE;
                        break;
                     case 1:
                        var11 = this.zzNB + this.zzND + this.zzrW / 2 - 25;
                        var12 = this.zzNC + this.zzNE;
                        break;
                     case 2:
                        var11 = this.zzNB + this.zzND + this.zzrW / 2 - 25;
                        var12 = this.zzNC + this.zzNE + this.zzrX / 2 - 25;
                        break;
                     case 3:
                        var11 = this.zzNB + this.zzND;
                        var12 = this.zzNC + this.zzNE + this.zzrX - 50;
                        break;
                     case 4:
                        var11 = this.zzNB + this.zzND + this.zzrW / 2 - 25;
                        var12 = this.zzNC + this.zzNE + this.zzrX - 50;
                        break;
                     case 5:
                        var11 = this.zzNB + this.zzND + this.zzrW - 50;
                        var12 = this.zzNC + this.zzNE + this.zzrX - 50;
                        break;
                     default:
                        var11 = this.zzNB + this.zzND + this.zzrW - 50;
                        var12 = this.zzNC + this.zzNE;
                     }

                     if (var11 < 0 || var11 + 50 > var9 || var12 < var8[0] || var12 + 50 > var8[1]) {
                        var10000 = false;
                        break label109;
                     }
                  }

                  var10000 = true;
               }
            }
         } else {
            zzafr.zzaT("Height is too small or too large.");
            var10000 = false;
         }
      } else {
         zzafr.zzaT("Width is too small or too large.");
         var10000 = false;
      }

      if (!var10000) {
         return null;
      } else if (this.zzNA) {
         return new int[]{this.zzNB + this.zzND, this.zzNC + this.zzNE};
      } else {
         int[] var1 = com.google.android.gms.ads.internal.zzbs.zzbz().zzg(this.zzNo);
         int[] var2 = com.google.android.gms.ads.internal.zzbs.zzbz().zzh(this.zzNo);
         int var3 = var1[0];
         int var4 = this.zzNB + this.zzND;
         int var5 = this.zzNC + this.zzNE;
         if (var4 < 0) {
            var4 = 0;
         } else if (var4 + this.zzrW > var3) {
            var4 = var3 - this.zzrW;
         }

         if (var5 < var2[0]) {
            var5 = var2[0];
         } else if (var5 + this.zzrX > var2[1]) {
            var5 = var2[1] - this.zzrX;
         }

         return new int[]{var4, var5};
      }
   }

   public final void zza(int var1, int var2, boolean var3) {
      Object var4 = this.mLock;
      synchronized(this.mLock) {
         this.zzNB = var1;
         this.zzNC = var2;
         if (this.zzNI != null && var3) {
            int[] var5;
            if ((var5 = this.zzfB()) != null) {
               PopupWindow var10000 = this.zzNI;
               zzji.zzds();
               int var10001 = zzaiy.zzc(this.zzNo, var5[0]);
               zzji.zzds();
               var10000.update(var10001, zzaiy.zzc(this.zzNo, var5[1]), this.zzNI.getWidth(), this.zzNI.getHeight());
               this.zza(var5[0], var5[1]);
            } else {
               this.zzk(true);
            }
         }

      }
   }

   private final void zza(int var1, int var2) {
      int var3 = com.google.android.gms.ads.internal.zzbs.zzbz().zzh(this.zzNo)[0];
      this.zzb(var1, var2 - var3, this.zzrW, this.zzrX);
   }

   public final boolean zzfC() {
      Object var1 = this.mLock;
      synchronized(this.mLock) {
         return this.zzNI != null;
      }
   }

   public final void zzb(int var1, int var2) {
      this.zzNB = var1;
      this.zzNC = var2;
   }
}
