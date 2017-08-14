package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.R.attr;
import com.google.android.gms.R.layout;
import com.google.android.gms.R.style;
import com.google.android.gms.R.styleable;
import com.google.android.gms.cast.framework.IntroductoryOverlay;

public final class zzauw extends RelativeLayout implements IntroductoryOverlay {
   private final boolean zzasC;
   private Activity mActivity;
   private int zzasH;
   private boolean zzasE;
   private IntroductoryOverlay.OnOverlayDismissedListener zzaso;
   private final zzauz zzasI;

   public zzauw(IntroductoryOverlay.Builder var1) {
      this(var1, (AttributeSet)null, attr.castIntroOverlayStyle);
   }

   @TargetApi(14)
   private zzauw(IntroductoryOverlay.Builder var1, AttributeSet var2, int var3) {
      super(var1.getActivity(), (AttributeSet)null, var3);
      this.mActivity = var1.getActivity();
      this.zzasC = var1.zznD();
      this.zzaso = var1.zznB();
      TypedArray var4 = this.mActivity.getTheme().obtainStyledAttributes((AttributeSet)null, styleable.CastIntroOverlay, var3, style.CastIntroOverlay);
      if (var1.zznA() != null) {
         Rect var5 = new Rect();
         var1.zznA().getGlobalVisibleRect(var5);
         this.zzasI = new zzauz((zzaux)null);
         this.zzasI.x = var5.centerX();
         this.zzasI.y = var5.centerY();
         zzauz var10000 = this.zzasI;
         PorterDuffXfermode var9 = new PorterDuffXfermode(Mode.MULTIPLY);
         Paint var10;
         (var10 = new Paint()).setColor(-1);
         var10.setAlpha(0);
         var10.setXfermode(var9);
         var10.setAntiAlias(true);
         var10000.zzasL = var10;
         this.zzasI.zzasM = var1.zznG();
         if (this.zzasI.zzasM == 0.0F) {
            this.zzasI.zzasM = var4.getDimension(styleable.CastIntroOverlay_castFocusRadius, 0.0F);
         }
      } else {
         this.zzasI = null;
      }

      LayoutInflater.from(this.mActivity).inflate(layout.cast_intro_overlay, this);
      this.zzasH = var1.zznC();
      if (this.zzasH == 0) {
         this.zzasH = var4.getColor(styleable.CastIntroOverlay_castBackgroundColor, Color.argb(0, 0, 0, 0));
      }

      TextView var6 = (TextView)this.findViewById(com.google.android.gms.R.id.textTitle);
      if (!TextUtils.isEmpty(var1.zznE())) {
         var6.setText(var1.zznE());
         int var7;
         if ((var7 = var4.getResourceId(styleable.CastIntroOverlay_castTitleTextAppearance, 0)) != 0) {
            var6.setTextAppearance(this.mActivity, var7);
         }
      }

      String var15 = var1.zznF();
      String var11 = var15;
      if (TextUtils.isEmpty(var15)) {
         var11 = var4.getString(styleable.CastIntroOverlay_castButtonText);
      }

      int var12 = var4.getColor(styleable.CastIntroOverlay_castButtonBackgroundColor, Color.argb(0, 0, 0, 0));
      Button var13;
      (var13 = (Button)this.findViewById(com.google.android.gms.R.id.button)).setText(var11);
      var13.getBackground().setColorFilter(var12, Mode.MULTIPLY);
      int var14;
      if ((var14 = var4.getResourceId(styleable.CastIntroOverlay_castButtonTextAppearance, 0)) != 0) {
         var13.setTextAppearance(this.mActivity, var14);
      }

      var13.setOnClickListener(new zzaux(this));
      var4.recycle();
      this.setFitsSystemWindows(true);
   }

   public final void show() {
      if (this.mActivity != null) {
         if (!zzaus.zzao(this.mActivity)) {
            if (this.zzasC && IntroductoryOverlay.zza.zzam(this.mActivity)) {
               this.mActivity = null;
               this.zzaso = null;
            } else {
               if (!this.zzasE) {
                  this.zzasE = true;
                  ((ViewGroup)this.mActivity.getWindow().getDecorView()).addView(this);
               }

            }
         }
      }
   }

   public final void remove() {
      if (this.mActivity != null) {
         ((ViewGroup)this.mActivity.getWindow().getDecorView()).removeView(this);
         this.mActivity = null;
      }

      this.zzaso = null;
   }

   protected final void dispatchDraw(Canvas var1) {
      Bitmap var2 = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Config.ARGB_8888);
      Canvas var3;
      (var3 = new Canvas(var2)).drawColor(this.zzasH);
      if (this.zzasI != null) {
         var3.drawCircle((float)this.zzasI.x, (float)this.zzasI.y, this.zzasI.zzasM, this.zzasI.zzasL);
      }

      var1.drawBitmap(var2, 0.0F, 0.0F, (Paint)null);
      var2.recycle();
      super.dispatchDraw(var1);
   }

   public final boolean onTouchEvent(MotionEvent var1) {
      return true;
   }

   protected final void onDetachedFromWindow() {
      if (this.mActivity != null) {
         this.mActivity = null;
      }

      super.onDetachedFromWindow();
   }

   private final void zznM() {
      IntroductoryOverlay.zza.zzal(this.mActivity);
      if (this.zzaso != null) {
         this.zzaso.onOverlayDismissed();
         this.zzaso = null;
      }

      this.remove();
   }

   // $FF: synthetic method
   static void zza(zzauw var0) {
      var0.zznM();
   }
}
