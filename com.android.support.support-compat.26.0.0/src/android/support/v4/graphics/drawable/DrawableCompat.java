package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableCompat {
   static final DrawableCompat.DrawableCompatBaseImpl IMPL;

   public static void jumpToCurrentState(@NonNull Drawable drawable) {
      IMPL.jumpToCurrentState(drawable);
   }

   public static void setAutoMirrored(@NonNull Drawable drawable, boolean mirrored) {
      IMPL.setAutoMirrored(drawable, mirrored);
   }

   public static boolean isAutoMirrored(@NonNull Drawable drawable) {
      return IMPL.isAutoMirrored(drawable);
   }

   public static void setHotspot(@NonNull Drawable drawable, float x, float y) {
      IMPL.setHotspot(drawable, x, y);
   }

   public static void setHotspotBounds(@NonNull Drawable drawable, int left, int top, int right, int bottom) {
      IMPL.setHotspotBounds(drawable, left, top, right, bottom);
   }

   public static void setTint(@NonNull Drawable drawable, @ColorInt int tint) {
      IMPL.setTint(drawable, tint);
   }

   public static void setTintList(@NonNull Drawable drawable, @Nullable ColorStateList tint) {
      IMPL.setTintList(drawable, tint);
   }

   public static void setTintMode(@NonNull Drawable drawable, @Nullable Mode tintMode) {
      IMPL.setTintMode(drawable, tintMode);
   }

   public static int getAlpha(@NonNull Drawable drawable) {
      return IMPL.getAlpha(drawable);
   }

   public static void applyTheme(@NonNull Drawable drawable, @NonNull Theme t) {
      IMPL.applyTheme(drawable, t);
   }

   public static boolean canApplyTheme(@NonNull Drawable drawable) {
      return IMPL.canApplyTheme(drawable);
   }

   public static ColorFilter getColorFilter(@NonNull Drawable drawable) {
      return IMPL.getColorFilter(drawable);
   }

   public static void clearColorFilter(@NonNull Drawable drawable) {
      IMPL.clearColorFilter(drawable);
   }

   public static void inflate(@NonNull Drawable drawable, @NonNull Resources res, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Theme theme) throws XmlPullParserException, IOException {
      IMPL.inflate(drawable, res, parser, attrs, theme);
   }

   public static Drawable wrap(@NonNull Drawable drawable) {
      return IMPL.wrap(drawable);
   }

   public static Drawable unwrap(@NonNull Drawable drawable) {
      return drawable instanceof DrawableWrapper ? ((DrawableWrapper)drawable).getWrappedDrawable() : drawable;
   }

   public static boolean setLayoutDirection(@NonNull Drawable drawable, int layoutDirection) {
      return IMPL.setLayoutDirection(drawable, layoutDirection);
   }

   public static int getLayoutDirection(@NonNull Drawable drawable) {
      return IMPL.getLayoutDirection(drawable);
   }

   static {
      if (VERSION.SDK_INT >= 23) {
         IMPL = new DrawableCompat.DrawableCompatApi23Impl();
      } else if (VERSION.SDK_INT >= 21) {
         IMPL = new DrawableCompat.DrawableCompatApi21Impl();
      } else if (VERSION.SDK_INT >= 19) {
         IMPL = new DrawableCompat.DrawableCompatApi19Impl();
      } else if (VERSION.SDK_INT >= 17) {
         IMPL = new DrawableCompat.DrawableCompatApi17Impl();
      } else {
         IMPL = new DrawableCompat.DrawableCompatBaseImpl();
      }

   }

   @RequiresApi(23)
   static class DrawableCompatApi23Impl extends DrawableCompat.DrawableCompatApi21Impl {
      public boolean setLayoutDirection(Drawable drawable, int layoutDirection) {
         return drawable.setLayoutDirection(layoutDirection);
      }

      public int getLayoutDirection(Drawable drawable) {
         return drawable.getLayoutDirection();
      }

      public Drawable wrap(Drawable drawable) {
         return drawable;
      }

      public void clearColorFilter(Drawable drawable) {
         drawable.clearColorFilter();
      }
   }

   @RequiresApi(21)
   static class DrawableCompatApi21Impl extends DrawableCompat.DrawableCompatApi19Impl {
      public void setHotspot(Drawable drawable, float x, float y) {
         drawable.setHotspot(x, y);
      }

      public void setHotspotBounds(Drawable drawable, int left, int top, int right, int bottom) {
         drawable.setHotspotBounds(left, top, right, bottom);
      }

      public void setTint(Drawable drawable, int tint) {
         drawable.setTint(tint);
      }

      public void setTintList(Drawable drawable, ColorStateList tint) {
         drawable.setTintList(tint);
      }

      public void setTintMode(Drawable drawable, Mode tintMode) {
         drawable.setTintMode(tintMode);
      }

      public Drawable wrap(Drawable drawable) {
         return (Drawable)(!(drawable instanceof TintAwareDrawable) ? new DrawableWrapperApi21(drawable) : drawable);
      }

      public void applyTheme(Drawable drawable, Theme t) {
         drawable.applyTheme(t);
      }

      public boolean canApplyTheme(Drawable drawable) {
         return drawable.canApplyTheme();
      }

      public ColorFilter getColorFilter(Drawable drawable) {
         return drawable.getColorFilter();
      }

      public void clearColorFilter(Drawable drawable) {
         drawable.clearColorFilter();
         if (drawable instanceof InsetDrawable) {
            this.clearColorFilter(((InsetDrawable)drawable).getDrawable());
         } else if (drawable instanceof DrawableWrapper) {
            this.clearColorFilter(((DrawableWrapper)drawable).getWrappedDrawable());
         } else if (drawable instanceof DrawableContainer) {
            DrawableContainer container = (DrawableContainer)drawable;
            DrawableContainerState state = (DrawableContainerState)container.getConstantState();
            if (state != null) {
               int i = 0;

               for(int count = state.getChildCount(); i < count; ++i) {
                  Drawable child = state.getChild(i);
                  if (child != null) {
                     this.clearColorFilter(child);
                  }
               }
            }
         }

      }

      public void inflate(Drawable drawable, Resources res, XmlPullParser parser, AttributeSet attrs, Theme t) throws IOException, XmlPullParserException {
         drawable.inflate(res, parser, attrs, t);
      }
   }

   @RequiresApi(19)
   static class DrawableCompatApi19Impl extends DrawableCompat.DrawableCompatApi17Impl {
      public void setAutoMirrored(Drawable drawable, boolean mirrored) {
         drawable.setAutoMirrored(mirrored);
      }

      public boolean isAutoMirrored(Drawable drawable) {
         return drawable.isAutoMirrored();
      }

      public Drawable wrap(Drawable drawable) {
         return (Drawable)(!(drawable instanceof TintAwareDrawable) ? new DrawableWrapperApi19(drawable) : drawable);
      }

      public int getAlpha(Drawable drawable) {
         return drawable.getAlpha();
      }
   }

   @RequiresApi(17)
   static class DrawableCompatApi17Impl extends DrawableCompat.DrawableCompatBaseImpl {
      private static final String TAG = "DrawableCompatApi17";
      private static Method sSetLayoutDirectionMethod;
      private static boolean sSetLayoutDirectionMethodFetched;
      private static Method sGetLayoutDirectionMethod;
      private static boolean sGetLayoutDirectionMethodFetched;

      public boolean setLayoutDirection(Drawable drawable, int layoutDirection) {
         if (!sSetLayoutDirectionMethodFetched) {
            try {
               sSetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
               sSetLayoutDirectionMethod.setAccessible(true);
            } catch (NoSuchMethodException var4) {
               Log.i("DrawableCompatApi17", "Failed to retrieve setLayoutDirection(int) method", var4);
            }

            sSetLayoutDirectionMethodFetched = true;
         }

         if (sSetLayoutDirectionMethod != null) {
            try {
               sSetLayoutDirectionMethod.invoke(drawable, layoutDirection);
               return true;
            } catch (Exception var5) {
               Log.i("DrawableCompatApi17", "Failed to invoke setLayoutDirection(int) via reflection", var5);
               sSetLayoutDirectionMethod = null;
            }
         }

         return false;
      }

      public int getLayoutDirection(Drawable drawable) {
         if (!sGetLayoutDirectionMethodFetched) {
            try {
               sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection");
               sGetLayoutDirectionMethod.setAccessible(true);
            } catch (NoSuchMethodException var3) {
               Log.i("DrawableCompatApi17", "Failed to retrieve getLayoutDirection() method", var3);
            }

            sGetLayoutDirectionMethodFetched = true;
         }

         if (sGetLayoutDirectionMethod != null) {
            try {
               return ((Integer)sGetLayoutDirectionMethod.invoke(drawable)).intValue();
            } catch (Exception var4) {
               Log.i("DrawableCompatApi17", "Failed to invoke getLayoutDirection() via reflection", var4);
               sGetLayoutDirectionMethod = null;
            }
         }

         return 0;
      }
   }

   static class DrawableCompatBaseImpl {
      public void jumpToCurrentState(Drawable drawable) {
         drawable.jumpToCurrentState();
      }

      public void setAutoMirrored(Drawable drawable, boolean mirrored) {
      }

      public boolean isAutoMirrored(Drawable drawable) {
         return false;
      }

      public void setHotspot(Drawable drawable, float x, float y) {
      }

      public void setHotspotBounds(Drawable drawable, int left, int top, int right, int bottom) {
      }

      public void setTint(Drawable drawable, int tint) {
         if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable)drawable).setTint(tint);
         }

      }

      public void setTintList(Drawable drawable, ColorStateList tint) {
         if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable)drawable).setTintList(tint);
         }

      }

      public void setTintMode(Drawable drawable, Mode tintMode) {
         if (drawable instanceof TintAwareDrawable) {
            ((TintAwareDrawable)drawable).setTintMode(tintMode);
         }

      }

      public Drawable wrap(Drawable drawable) {
         return (Drawable)(!(drawable instanceof TintAwareDrawable) ? new DrawableWrapperApi14(drawable) : drawable);
      }

      public boolean setLayoutDirection(Drawable drawable, int layoutDirection) {
         return false;
      }

      public int getLayoutDirection(Drawable drawable) {
         return 0;
      }

      public int getAlpha(Drawable drawable) {
         return 0;
      }

      public void applyTheme(Drawable drawable, Theme t) {
      }

      public boolean canApplyTheme(Drawable drawable) {
         return false;
      }

      public ColorFilter getColorFilter(Drawable drawable) {
         return null;
      }

      public void clearColorFilter(Drawable drawable) {
         drawable.clearColorFilter();
      }

      public void inflate(Drawable drawable, Resources res, XmlPullParser parser, AttributeSet attrs, Theme t) throws IOException, XmlPullParserException {
         drawable.inflate(res, parser, attrs);
      }
   }
}
