package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
   private static final CompoundButtonCompat.CompoundButtonCompatBaseImpl IMPL;

   public static void setButtonTintList(@NonNull CompoundButton button, @Nullable ColorStateList tint) {
      IMPL.setButtonTintList(button, tint);
   }

   @Nullable
   public static ColorStateList getButtonTintList(@NonNull CompoundButton button) {
      return IMPL.getButtonTintList(button);
   }

   public static void setButtonTintMode(@NonNull CompoundButton button, @Nullable Mode tintMode) {
      IMPL.setButtonTintMode(button, tintMode);
   }

   @Nullable
   public static Mode getButtonTintMode(@NonNull CompoundButton button) {
      return IMPL.getButtonTintMode(button);
   }

   @Nullable
   public static Drawable getButtonDrawable(@NonNull CompoundButton button) {
      return IMPL.getButtonDrawable(button);
   }

   static {
      if (VERSION.SDK_INT >= 23) {
         IMPL = new CompoundButtonCompat.CompoundButtonCompatApi23Impl();
      } else if (VERSION.SDK_INT >= 21) {
         IMPL = new CompoundButtonCompat.CompoundButtonCompatApi21Impl();
      } else {
         IMPL = new CompoundButtonCompat.CompoundButtonCompatBaseImpl();
      }

   }

   @RequiresApi(23)
   static class CompoundButtonCompatApi23Impl extends CompoundButtonCompat.CompoundButtonCompatApi21Impl {
      public Drawable getButtonDrawable(CompoundButton button) {
         return button.getButtonDrawable();
      }
   }

   @RequiresApi(21)
   static class CompoundButtonCompatApi21Impl extends CompoundButtonCompat.CompoundButtonCompatBaseImpl {
      public void setButtonTintList(CompoundButton button, ColorStateList tint) {
         button.setButtonTintList(tint);
      }

      public ColorStateList getButtonTintList(CompoundButton button) {
         return button.getButtonTintList();
      }

      public void setButtonTintMode(CompoundButton button, Mode tintMode) {
         button.setButtonTintMode(tintMode);
      }

      public Mode getButtonTintMode(CompoundButton button) {
         return button.getButtonTintMode();
      }
   }

   static class CompoundButtonCompatBaseImpl {
      private static final String TAG = "CompoundButtonCompat";
      private static Field sButtonDrawableField;
      private static boolean sButtonDrawableFieldFetched;

      public void setButtonTintList(CompoundButton button, ColorStateList tint) {
         if (button instanceof TintableCompoundButton) {
            ((TintableCompoundButton)button).setSupportButtonTintList(tint);
         }

      }

      public ColorStateList getButtonTintList(CompoundButton button) {
         return button instanceof TintableCompoundButton ? ((TintableCompoundButton)button).getSupportButtonTintList() : null;
      }

      public void setButtonTintMode(CompoundButton button, Mode tintMode) {
         if (button instanceof TintableCompoundButton) {
            ((TintableCompoundButton)button).setSupportButtonTintMode(tintMode);
         }

      }

      public Mode getButtonTintMode(CompoundButton button) {
         return button instanceof TintableCompoundButton ? ((TintableCompoundButton)button).getSupportButtonTintMode() : null;
      }

      public Drawable getButtonDrawable(CompoundButton button) {
         if (!sButtonDrawableFieldFetched) {
            try {
               sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable");
               sButtonDrawableField.setAccessible(true);
            } catch (NoSuchFieldException var3) {
               Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", var3);
            }

            sButtonDrawableFieldFetched = true;
         }

         if (sButtonDrawableField != null) {
            try {
               return (Drawable)sButtonDrawableField.get(button);
            } catch (IllegalAccessException var4) {
               Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", var4);
               sButtonDrawableField = null;
            }
         }

         return null;
      }
   }
}
