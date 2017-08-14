package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.util.LruCache;
import android.widget.TextView;

@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompat {
   private static final String TAG = "TypefaceCompat";
   private static final TypefaceCompat.TypefaceCompatImpl sTypefaceCompatImpl;
   private static final LruCache sTypefaceCache;

   public static Typeface findFromCache(Resources resources, int id, int style) {
      return (Typeface)sTypefaceCache.get(createResourceUid(resources, id, style));
   }

   private static String createResourceUid(Resources resources, int id, int style) {
      return resources.getResourcePackageName(id) + "-" + id + "-" + style;
   }

   public static Typeface createFromResourcesFamilyXml(Context context, FontResourcesParserCompat.FamilyResourceEntry entry, Resources resources, int id, int style, @Nullable TextView targetView) {
      Typeface typeface;
      if (entry instanceof FontResourcesParserCompat.ProviderResourceEntry) {
         FontResourcesParserCompat.ProviderResourceEntry providerEntry = (FontResourcesParserCompat.ProviderResourceEntry)entry;
         typeface = FontsContractCompat.getFontSync(context, providerEntry.getRequest(), targetView, providerEntry.getFetchStrategy(), providerEntry.getTimeout(), style);
      } else {
         typeface = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, (FontResourcesParserCompat.FontFamilyFilesResourceEntry)entry, resources, style);
      }

      if (typeface != null) {
         sTypefaceCache.put(createResourceUid(resources, id, style), typeface);
      }

      return typeface;
   }

   @Nullable
   public static Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
      Typeface typeface = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, id, path, style);
      if (typeface != null) {
         sTypefaceCache.put(createResourceUid(resources, id, style), typeface);
      }

      return typeface;
   }

   public static Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fonts, int style) {
      return sTypefaceCompatImpl.createFromFontInfo(context, cancellationSignal, fonts, style);
   }

   static {
      if (VERSION.SDK_INT >= 26) {
         sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
      } else if (VERSION.SDK_INT >= 24 && TypefaceCompatApi24Impl.isUsable()) {
         sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
      } else if (VERSION.SDK_INT >= 21) {
         sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
      } else {
         sTypefaceCompatImpl = new TypefaceCompatBaseImpl();
      }

      sTypefaceCache = new LruCache(16);
   }

   interface TypefaceCompatImpl {
      Typeface createFromFontFamilyFilesResourceEntry(Context var1, FontResourcesParserCompat.FontFamilyFilesResourceEntry var2, Resources var3, int var4);

      Typeface createFromFontInfo(Context var1, @Nullable CancellationSignal var2, @NonNull FontsContractCompat.FontInfo[] var3, int var4);

      Typeface createFromResourcesFontFile(Context var1, Resources var2, int var3, String var4, int var5);
   }
}
