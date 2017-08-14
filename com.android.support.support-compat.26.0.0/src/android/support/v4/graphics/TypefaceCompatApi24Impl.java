package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

@RestrictTo({Scope.LIBRARY_GROUP})
@RequiresApi(24)
class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl {
   private static final String TAG = "TypefaceCompatApi24Impl";
   private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
   private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
   private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
   private static final Class sFontFamily;
   private static final Constructor sFontFamilyCtor;
   private static final Method sAddFontWeightStyle;
   private static final Method sCreateFromFamiliesWithDefault;

   public static boolean isUsable() {
      if (sAddFontWeightStyle == null) {
         Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
      }

      return sAddFontWeightStyle != null;
   }

   private static Object newFamily() {
      try {
         return sFontFamilyCtor.newInstance();
      } catch (InstantiationException | InvocationTargetException | IllegalAccessException var1) {
         throw new RuntimeException(var1);
      }
   }

   private static boolean addFontWeightStyle(Object family, ByteBuffer buffer, int ttcIndex, int weight, boolean style) {
      try {
         Boolean result = (Boolean)sAddFontWeightStyle.invoke(family, buffer, ttcIndex, null, weight, style);
         return result.booleanValue();
      } catch (InvocationTargetException | IllegalAccessException var6) {
         throw new RuntimeException(var6);
      }
   }

   private static Typeface createFromFamiliesWithDefault(Object family) {
      try {
         Object familyArray = Array.newInstance(sFontFamily, 1);
         Array.set(familyArray, 0, family);
         return (Typeface)sCreateFromFamiliesWithDefault.invoke((Object)null, familyArray);
      } catch (InvocationTargetException | IllegalAccessException var2) {
         throw new RuntimeException(var2);
      }
   }

   public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fonts, int style) {
      Object family = newFamily();
      SimpleArrayMap bufferCache = new SimpleArrayMap();
      FontsContractCompat.FontInfo[] var7 = fonts;
      int var8 = fonts.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         FontsContractCompat.FontInfo font = var7[var9];
         Uri uri = font.getUri();
         ByteBuffer buffer = (ByteBuffer)bufferCache.get(uri);
         if (buffer == null) {
            buffer = TypefaceCompatUtil.mmap(context, cancellationSignal, uri);
            bufferCache.put(uri, buffer);
         }

         if (!addFontWeightStyle(family, buffer, font.getTtcIndex(), font.getWeight(), font.isItalic())) {
            return null;
         }
      }

      return createFromFamiliesWithDefault(family);
   }

   public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int style) {
      Object family = newFamily();
      FontResourcesParserCompat.FontFileResourceEntry[] var6 = entry.getEntries();
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         FontResourcesParserCompat.FontFileResourceEntry e = var6[var8];
         ByteBuffer buffer = TypefaceCompatUtil.copyToDirectBuffer(context, resources, e.getResourceId());
         if (!addFontWeightStyle(family, buffer, 0, e.getWeight(), e.isItalic())) {
            return null;
         }
      }

      return createFromFamiliesWithDefault(family);
   }

   static {
      Class fontFamilyClass;
      Constructor fontFamilyCtor;
      Method addFontMethod;
      Method createFromFamiliesWithDefaultMethod;
      try {
         fontFamilyClass = Class.forName("android.graphics.FontFamily");
         fontFamilyCtor = fontFamilyClass.getConstructor();
         addFontMethod = fontFamilyClass.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
         Object familyArray = Array.newInstance(fontFamilyClass, 1);
         createFromFamiliesWithDefaultMethod = Typeface.class.getMethod("createFromFamiliesWithDefault", familyArray.getClass());
      } catch (NoSuchMethodException | ClassNotFoundException var5) {
         Log.e("TypefaceCompatApi24Impl", var5.getClass().getName(), var5);
         fontFamilyClass = null;
         fontFamilyCtor = null;
         addFontMethod = null;
         createFromFamiliesWithDefaultMethod = null;
      }

      sFontFamilyCtor = fontFamilyCtor;
      sFontFamily = fontFamilyClass;
      sAddFontWeightStyle = addFontMethod;
      sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod;
   }
}
