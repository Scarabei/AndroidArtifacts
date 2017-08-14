package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.provider.FontsContractCompat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestrictTo({Scope.LIBRARY_GROUP})
@RequiresApi(14)
class TypefaceCompatBaseImpl implements TypefaceCompat.TypefaceCompatImpl {
   private static final String TAG = "TypefaceCompatBaseImpl";
   private static final String CACHE_FILE_PREFIX = "cached_font_";

   private static Object findBestFont(Object[] fonts, int style, TypefaceCompatBaseImpl.StyleExtractor extractor) {
      int targetWeight = (style & 1) == 0 ? 400 : 700;
      boolean isTargetItalic = (style & 2) != 0;
      Object best = null;
      int bestScore = Integer.MAX_VALUE;
      Object[] var7 = fonts;
      int var8 = fonts.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         Object font = var7[var9];
         int score = Math.abs(extractor.getWeight(font) - targetWeight) * 2 + (extractor.isItalic(font) == isTargetItalic ? 0 : 1);
         if (best == null || bestScore > score) {
            best = font;
            bestScore = score;
         }
      }

      return best;
   }

   protected FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fonts, int style) {
      return (FontsContractCompat.FontInfo)findBestFont(fonts, style, new TypefaceCompatBaseImpl.StyleExtractor() {
         public int getWeight(FontsContractCompat.FontInfo info) {
            return info.getWeight();
         }

         public boolean isItalic(FontsContractCompat.FontInfo info) {
            return info.isItalic();
         }
      });
   }

   protected Typeface createFromInputStream(Context context, InputStream is) {
      File tmpFile = TypefaceCompatUtil.getTempFile(context);
      if (tmpFile == null) {
         return null;
      } else {
         Typeface var4;
         try {
            if (TypefaceCompatUtil.copyToFile(tmpFile, is)) {
               var4 = Typeface.createFromFile(tmpFile.getPath());
               return var4;
            }

            var4 = null;
         } catch (RuntimeException var9) {
            Object var5 = null;
            return (Typeface)var5;
         } finally {
            tmpFile.delete();
         }

         return var4;
      }
   }

   public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fonts, int style) {
      if (fonts.length < 1) {
         return null;
      } else {
         FontsContractCompat.FontInfo font = this.findBestInfo(fonts, style);
         InputStream is = null;

         Object var8;
         try {
            is = context.getContentResolver().openInputStream(font.getUri());
            Typeface var7 = this.createFromInputStream(context, is);
            return var7;
         } catch (IOException var12) {
            var8 = null;
         } finally {
            TypefaceCompatUtil.closeQuietly(is);
         }

         return (Typeface)var8;
      }
   }

   private FontResourcesParserCompat.FontFileResourceEntry findBestEntry(FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, int style) {
      return (FontResourcesParserCompat.FontFileResourceEntry)findBestFont(entry.getEntries(), style, new TypefaceCompatBaseImpl.StyleExtractor() {
         public int getWeight(FontResourcesParserCompat.FontFileResourceEntry entry) {
            return entry.getWeight();
         }

         public boolean isItalic(FontResourcesParserCompat.FontFileResourceEntry entry) {
            return entry.isItalic();
         }
      });
   }

   @Nullable
   public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int style) {
      FontResourcesParserCompat.FontFileResourceEntry best = this.findBestEntry(entry, style);
      return best == null ? null : TypefaceCompat.createFromResourcesFontFile(context, resources, best.getResourceId(), best.getFileName(), style);
   }

   @Nullable
   public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
      File tmpFile = TypefaceCompatUtil.getTempFile(context);
      if (tmpFile == null) {
         return null;
      } else {
         Typeface var7;
         try {
            if (TypefaceCompatUtil.copyToFile(tmpFile, resources, id)) {
               var7 = Typeface.createFromFile(tmpFile.getPath());
               return var7;
            }

            var7 = null;
         } catch (RuntimeException var12) {
            Object var8 = null;
            return (Typeface)var8;
         } finally {
            tmpFile.delete();
         }

         return var7;
      }
   }

   private interface StyleExtractor {
      int getWeight(Object var1);

      boolean isItalic(Object var1);
   }
}
