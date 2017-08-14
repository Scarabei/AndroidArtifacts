package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.graphics.TypefaceCompat;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
   private static final String TAG = "ResourcesCompat";

   @Nullable
   public static Drawable getDrawable(@NonNull Resources res, @DrawableRes int id, @Nullable Theme theme) throws NotFoundException {
      return VERSION.SDK_INT >= 21 ? res.getDrawable(id, theme) : res.getDrawable(id);
   }

   @Nullable
   public static Drawable getDrawableForDensity(@NonNull Resources res, @DrawableRes int id, int density, @Nullable Theme theme) throws NotFoundException {
      if (VERSION.SDK_INT >= 21) {
         return res.getDrawableForDensity(id, density, theme);
      } else {
         return VERSION.SDK_INT >= 15 ? res.getDrawableForDensity(id, density) : res.getDrawable(id);
      }
   }

   @ColorInt
   public static int getColor(@NonNull Resources res, @ColorRes int id, @Nullable Theme theme) throws NotFoundException {
      return VERSION.SDK_INT >= 23 ? res.getColor(id, theme) : res.getColor(id);
   }

   @Nullable
   public static ColorStateList getColorStateList(@NonNull Resources res, @ColorRes int id, @Nullable Theme theme) throws NotFoundException {
      return VERSION.SDK_INT >= 23 ? res.getColorStateList(id, theme) : res.getColorStateList(id);
   }

   @Nullable
   public static Typeface getFont(@NonNull Context context, @FontRes int id) throws NotFoundException {
      return context.isRestricted() ? null : loadFont(context, id, new TypedValue(), 0, (TextView)null);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static Typeface getFont(@NonNull Context context, @FontRes int id, TypedValue value, int style, @Nullable TextView targetView) throws NotFoundException {
      return context.isRestricted() ? null : loadFont(context, id, value, style, targetView);
   }

   private static Typeface loadFont(@NonNull Context context, int id, TypedValue value, int style, @Nullable TextView targetView) {
      Resources resources = context.getResources();
      resources.getValue(id, value, true);
      Typeface typeface = loadFont(context, resources, value, id, style, targetView);
      if (typeface != null) {
         return typeface;
      } else {
         throw new NotFoundException("Font resource ID #0x" + Integer.toHexString(id));
      }
   }

   private static Typeface loadFont(@NonNull Context context, Resources wrapper, TypedValue value, int id, int style, @Nullable TextView targetView) {
      if (value.string == null) {
         throw new NotFoundException("Resource \"" + wrapper.getResourceName(id) + "\" (" + Integer.toHexString(id) + ") is not a Font: " + value);
      } else {
         String file = value.string.toString();
         if (!file.startsWith("res/")) {
            return null;
         } else {
            Typeface cached = TypefaceCompat.findFromCache(wrapper, id, style);
            if (cached != null) {
               return cached;
            } else {
               try {
                  if (file.toLowerCase().endsWith(".xml")) {
                     XmlResourceParser rp = wrapper.getXml(id);
                     FontResourcesParserCompat.FamilyResourceEntry familyEntry = FontResourcesParserCompat.parse(rp, wrapper);
                     if (familyEntry == null) {
                        Log.e("ResourcesCompat", "Failed to find font-family tag");
                        return null;
                     }

                     return TypefaceCompat.createFromResourcesFamilyXml(context, familyEntry, wrapper, id, style, targetView);
                  }

                  return TypefaceCompat.createFromResourcesFontFile(context, wrapper, id, file, style);
               } catch (XmlPullParserException var10) {
                  Log.e("ResourcesCompat", "Failed to parse xml resource " + file, var10);
               } catch (IOException var11) {
                  Log.e("ResourcesCompat", "Failed to read xml resource " + file, var11);
               }

               return null;
            }
         }
      }
   }
}
