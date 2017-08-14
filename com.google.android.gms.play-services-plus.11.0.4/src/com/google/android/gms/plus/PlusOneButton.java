package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzbz;
import com.google.android.gms.plus.internal.zzm;

public final class PlusOneButton extends FrameLayout {
   public static final int SIZE_SMALL = 0;
   public static final int SIZE_MEDIUM = 1;
   public static final int SIZE_TALL = 2;
   public static final int SIZE_STANDARD = 3;
   public static final int ANNOTATION_NONE = 0;
   public static final int ANNOTATION_BUBBLE = 1;
   public static final int ANNOTATION_INLINE = 2;
   public static final int DEFAULT_ACTIVITY_REQUEST_CODE = -1;
   private View mContentView;
   private int mSize;
   private int zzbAt;
   private String zzD;
   private int zzbAu;
   private PlusOneButton.OnPlusOneClickListener zzbAv;

   public PlusOneButton(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public PlusOneButton(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mSize = getSize(var1, var2);
      this.zzbAt = getAnnotation(var1, var2);
      this.zzbAu = -1;
      this.zzbk(this.getContext());
      if (!this.isInEditMode()) {
         ;
      }
   }

   protected static int getSize(Context var0, AttributeSet var1) {
      String var2 = zzbz.zza("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", var0, var1, true, false, "PlusOneButton");
      if ("SMALL".equalsIgnoreCase(var2)) {
         return 0;
      } else if ("MEDIUM".equalsIgnoreCase(var2)) {
         return 1;
      } else {
         return "TALL".equalsIgnoreCase(var2) ? 2 : 3;
      }
   }

   protected static int getAnnotation(Context var0, AttributeSet var1) {
      String var2 = zzbz.zza("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", var0, var1, true, false, "PlusOneButton");
      if ("INLINE".equalsIgnoreCase(var2)) {
         return 2;
      } else {
         return "NONE".equalsIgnoreCase(var2) ? 0 : 1;
      }
   }

   private final void zzbk(Context var1) {
      if (this.mContentView != null) {
         this.removeView(this.mContentView);
      }

      this.mContentView = zzm.zza(var1, this.mSize, this.zzbAt, this.zzD, this.zzbAu);
      this.setOnPlusOneClickListener(this.zzbAv);
      this.addView(this.mContentView);
   }

   public final void initialize(String var1, int var2) {
      zzbo.zza(this.getContext() instanceof Activity, "To use this method, the PlusOneButton must be placed in an Activity. Use initialize(String, OnPlusOneClickListener).");
      this.zzD = var1;
      this.zzbAu = var2;
      this.zzbk(this.getContext());
   }

   public final void initialize(String var1, PlusOneButton.OnPlusOneClickListener var2) {
      this.zzD = var1;
      this.zzbAu = 0;
      this.zzbk(this.getContext());
      this.setOnPlusOneClickListener(var2);
   }

   public final void setOnPlusOneClickListener(PlusOneButton.OnPlusOneClickListener var1) {
      this.zzbAv = var1;
      this.mContentView.setOnClickListener(new PlusOneButton.DefaultOnPlusOneClickListener(var1));
   }

   public final void setAnnotation(int var1) {
      this.zzbAt = var1;
      this.zzbk(this.getContext());
   }

   public final void setSize(int var1) {
      this.mSize = var1;
      this.zzbk(this.getContext());
   }

   protected final void onMeasure(int var1, int var2) {
      View var3 = this.mContentView;
      this.measureChild(var3, var1, var2);
      this.setMeasuredDimension(var3.getMeasuredWidth(), var3.getMeasuredHeight());
   }

   protected final void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      this.mContentView.layout(0, 0, var4 - var2, var5 - var3);
   }

   public final void plusOneClick() {
      this.mContentView.performClick();
   }

   public final void setIntent(Intent var1) {
      this.mContentView.setTag(var1);
   }

   public class DefaultOnPlusOneClickListener implements OnClickListener, PlusOneButton.OnPlusOneClickListener {
      private final PlusOneButton.OnPlusOneClickListener zzbAw;
      // $FF: synthetic field
      private PlusOneButton zzbAx;

      public DefaultOnPlusOneClickListener(PlusOneButton.OnPlusOneClickListener var2) {
         this.zzbAx = PlusOneButton.this;
         super();
         this.zzbAw = var2;
      }

      public void onClick(View var1) {
         Intent var2 = (Intent)this.zzbAx.mContentView.getTag();
         if (this.zzbAw != null) {
            this.zzbAw.onPlusOneClick(var2);
         } else {
            this.onPlusOneClick(var2);
         }
      }

      public void onPlusOneClick(Intent var1) {
         Context var2;
         if ((var2 = this.zzbAx.getContext()) instanceof Activity && var1 != null) {
            ((Activity)var2).startActivityForResult(var1, this.zzbAx.zzbAu);
         }

      }
   }

   public interface OnPlusOneClickListener {
      void onPlusOneClick(Intent var1);
   }
}
