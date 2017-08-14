package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbv;
import com.google.android.gms.common.internal.zzbw;
import com.google.android.gms.dynamic.zzq;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SignInButton extends FrameLayout implements OnClickListener {
   public static final int SIZE_STANDARD = 0;
   public static final int SIZE_WIDE = 1;
   public static final int SIZE_ICON_ONLY = 2;
   public static final int COLOR_DARK = 0;
   public static final int COLOR_LIGHT = 1;
   public static final int COLOR_AUTO = 2;
   private int mSize;
   private int mColor;
   private View zzaAv;
   private OnClickListener zzaAw;

   public SignInButton(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SignInButton(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public SignInButton(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.zzaAw = null;
      SignInButton var4 = this;
      TypedArray var7 = var1.getTheme().obtainStyledAttributes(var2, styleable.SignInButton, 0, 0);

      try {
         var4.mSize = var7.getInt(styleable.SignInButton_buttonSize, 0);
         var4.mColor = var7.getInt(styleable.SignInButton_colorScheme, 2);
      } finally {
         var7.recycle();
      }

      this.setStyle(this.mSize, this.mColor);
   }

   public final void setSize(int var1) {
      this.setStyle(var1, this.mColor);
   }

   public final void setColorScheme(int var1) {
      this.setStyle(this.mSize, var1);
   }

   /** @deprecated */
   @Deprecated
   public final void setScopes(Scope[] var1) {
      this.setStyle(this.mSize, this.mColor);
   }

   public final void setStyle(int var1, int var2) {
      this.mSize = var1;
      this.mColor = var2;
      Context var4 = this.getContext();
      SignInButton var3 = this;
      if (this.zzaAv != null) {
         this.removeView(this.zzaAv);
      }

      try {
         var3.zzaAv = zzbv.zzc(var4, var3.mSize, var3.mColor);
      } catch (zzq var9) {
         Log.w("SignInButton", "Sign in button not found, using placeholder instead");
         int var7 = this.mColor;
         int var6 = this.mSize;
         zzbw var8;
         (var8 = new zzbw(var4)).zza(var4.getResources(), var6, var7);
         this.zzaAv = var8;
      }

      this.addView(this.zzaAv);
      this.zzaAv.setEnabled(this.isEnabled());
      this.zzaAv.setOnClickListener(this);
   }

   /** @deprecated */
   @Deprecated
   public final void setStyle(int var1, int var2, Scope[] var3) {
      this.setStyle(var1, var2);
   }

   public final void setOnClickListener(OnClickListener var1) {
      this.zzaAw = var1;
      if (this.zzaAv != null) {
         this.zzaAv.setOnClickListener(this);
      }

   }

   public final void setEnabled(boolean var1) {
      super.setEnabled(var1);
      this.zzaAv.setEnabled(var1);
   }

   public final void onClick(View var1) {
      if (this.zzaAw != null && var1 == this.zzaAv) {
         this.zzaAw.onClick(this);
      }

   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ColorScheme {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ButtonSize {
   }
}
