package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.Typeface.Builder;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.provider.FontsContractCompat;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
@RequiresApi(26)
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
   private static final String TAG = "TypefaceCompatApi26Impl";
   private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
   private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
   private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
   private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
   private static final String FREEZE_METHOD = "freeze";
   private static final String ABORT_CREATION_METHOD = "abortCreation";
   private static final Class sFontFamily;
   private static final Constructor sFontFamilyCtor;
   private static final Method sAddFontFromAssetManager;
   private static final Method sAddFontFromBuffer;
   private static final Method sFreeze;
   private static final Method sAbortCreation;
   private static final Method sCreateFromFamiliesWithDefault;
   private static final int RESOLVE_BY_FONT_TABLE = -1;

   private static boolean isFontFamilyPrivateAPIAvailable() {
      if (sAddFontFromAssetManager == null) {
         Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
      }

      return sAddFontFromAssetManager != null;
   }

   private static Object newFamily() {
      try {
         return sFontFamilyCtor.newInstance();
      } catch (InstantiationException | InvocationTargetException | IllegalAccessException var1) {
         throw new RuntimeException(var1);
      }
   }

   private static boolean addFontFromAssetManager(Context context, Object family, String fileName, int ttcIndex, int weight, int style) {
      try {
         Boolean result = (Boolean)sAddFontFromAssetManager.invoke(family, context.getAssets(), fileName, Integer.valueOf(0), false, ttcIndex, weight, style, null);
         return result.booleanValue();
      } catch (InvocationTargetException | IllegalAccessException var7) {
         throw new RuntimeException(var7);
      }
   }

   private static boolean addFontFromBuffer(Object family, ByteBuffer buffer, int ttcIndex, int weight, int style) {
      try {
         Boolean result = (Boolean)sAddFontFromBuffer.invoke(family, buffer, ttcIndex, null, weight, style);
         return result.booleanValue();
      } catch (InvocationTargetException | IllegalAccessException var6) {
         throw new RuntimeException(var6);
      }
   }

   private static Typeface createFromFamiliesWithDefault(Object family) {
      try {
         Object familyArray = Array.newInstance(sFontFamily, 1);
         Array.set(familyArray, 0, family);
         return (Typeface)sCreateFromFamiliesWithDefault.invoke((Object)null, familyArray, Integer.valueOf(-1), Integer.valueOf(-1));
      } catch (InvocationTargetException | IllegalAccessException var2) {
         throw new RuntimeException(var2);
      }
   }

   private static boolean freeze(Object family) {
      try {
         Boolean result = (Boolean)sFreeze.invoke(family);
         return result.booleanValue();
      } catch (InvocationTargetException | IllegalAccessException var2) {
         throw new RuntimeException(var2);
      }
   }

   private static boolean abortCreation(Object family) {
      try {
         Boolean result = (Boolean)sAbortCreation.invoke(family);
         return result.booleanValue();
      } catch (InvocationTargetException | IllegalAccessException var2) {
         throw new RuntimeException(var2);
      }
   }

   public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int style) {
      if (!isFontFamilyPrivateAPIAvailable()) {
         return super.createFromFontFamilyFilesResourceEntry(context, entry, resources, style);
      } else {
         Object fontFamily = newFamily();
         FontResourcesParserCompat.FontFileResourceEntry[] var6 = entry.getEntries();
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            FontResourcesParserCompat.FontFileResourceEntry fontFile = var6[var8];
            if (!addFontFromAssetManager(context, fontFamily, fontFile.getFileName(), 0, fontFile.getWeight(), fontFile.isItalic() ? 1 : 0)) {
               abortCreation(fontFamily);
               return null;
            }
         }

         if (!freeze(fontFamily)) {
            return null;
         } else {
            return createFromFamiliesWithDefault(fontFamily);
         }
      }
   }

   public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fonts, int style) {
      if (fonts.length < 1) {
         return null;
      } else if (!isFontFamilyPrivateAPIAvailable()) {
         FontsContractCompat.FontInfo bestFont = this.findBestInfo(fonts, style);
         ContentResolver resolver = context.getContentResolver();

         try {
            ParcelFileDescriptor pfd = resolver.openFileDescriptor(bestFont.getUri(), "r", cancellationSignal);
            Throwable var26 = null;

            Typeface var27;
            try {
               var27 = (new Builder(pfd.getFileDescriptor())).setWeight(bestFont.getWeight()).setItalic(bestFont.isItalic()).build();
            } catch (Throwable var20) {
               var26 = var20;
               throw var20;
            } finally {
               if (pfd != null) {
                  if (var26 != null) {
                     try {
                        pfd.close();
                     } catch (Throwable var19) {
                        var26.addSuppressed(var19);
                     }
                  } else {
                     pfd.close();
                  }
               }

            }

            return var27;
         } catch (IOException var22) {
            return null;
         }
      } else {
         Map uriBuffer = FontsContractCompat.prepareFontData(context, fonts, cancellationSignal);
         Object fontFamily = newFamily();
         boolean atLeastOneFont = false;
         FontsContractCompat.FontInfo[] var8 = fonts;
         int var9 = fonts.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            FontsContractCompat.FontInfo font = var8[var10];
            ByteBuffer fontBuffer = (ByteBuffer)uriBuffer.get(font.getUri());
            if (fontBuffer != null) {
               boolean success = addFontFromBuffer(fontFamily, fontBuffer, font.getTtcIndex(), font.getWeight(), font.isItalic() ? 1 : 0);
               if (!success) {
                  abortCreation(fontFamily);
                  return null;
               }

               atLeastOneFont = true;
            }
         }

         if (!atLeastOneFont) {
            abortCreation(fontFamily);
            return null;
         } else if (!freeze(fontFamily)) {
            return null;
         } else {
            return createFromFamiliesWithDefault(fontFamily);
         }
      }
   }

   @Nullable
   public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
      if (!isFontFamilyPrivateAPIAvailable()) {
         super.createFromResourcesFontFile(context, resources, id, path, style);
      }

      Object fontFamily = newFamily();
      if (!addFontFromAssetManager(context, fontFamily, path, 0, -1, -1)) {
         abortCreation(fontFamily);
         return null;
      } else {
         return !freeze(fontFamily) ? null : createFromFamiliesWithDefault(fontFamily);
      }
   }

   static {
      Class fontFamilyClass;
      Constructor fontFamilyCtor;
      Method addFontMethod;
      Method addFromBufferMethod;
      Method freezeMethod;
      Method abortCreationMethod;
      Method createFromFamiliesWithDefaultMethod;
      try {
         fontFamilyClass = Class.forName("android.graphics.FontFamily");
         fontFamilyCtor = fontFamilyClass.getConstructor();
         addFontMethod = fontFamilyClass.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
         addFromBufferMethod = fontFamilyClass.getMethod("addFontFromBuffer", ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
         freezeMethod = fontFamilyClass.getMethod("freeze");
         abortCreationMethod = fontFamilyClass.getMethod("abortCreation");
         Object familyArray = Array.newInstance(fontFamilyClass, 1);
         createFromFamiliesWithDefaultMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", familyArray.getClass(), Integer.TYPE, Integer.TYPE);
         createFromFamiliesWithDefaultMethod.setAccessible(true);
      } catch (NoSuchMethodException | ClassNotFoundException var8) {
         Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + var8.getClass().getName(), var8);
         fontFamilyClass = null;
         fontFamilyCtor = null;
         addFontMethod = null;
         addFromBufferMethod = null;
         freezeMethod = null;
         abortCreationMethod = null;
         createFromFamiliesWithDefaultMethod = null;
      }

      sFontFamilyCtor = fontFamilyCtor;
      sFontFamily = fontFamilyClass;
      sAddFontFromAssetManager = addFontMethod;
      sAddFontFromBuffer = addFromBufferMethod;
      sFreeze = freezeMethod;
      sAbortCreation = abortCreationMethod;
      sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod;
   }
}
