package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.compat.R.styleable;
import android.support.v4.provider.FontRequest;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Xml;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({Scope.LIBRARY_GROUP})
public class FontResourcesParserCompat {
   private static final int NORMAL_WEIGHT = 400;
   private static final int ITALIC = 1;
   public static final int FETCH_STRATEGY_BLOCKING = 0;
   public static final int FETCH_STRATEGY_ASYNC = 1;
   public static final int INFINITE_TIMEOUT_VALUE = -1;
   private static final int DEFAULT_TIMEOUT_MILLIS = 500;

   @Nullable
   public static FontResourcesParserCompat.FamilyResourceEntry parse(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
      int type;
      while((type = parser.next()) != 2 && type != 1) {
         ;
      }

      if (type != 2) {
         throw new XmlPullParserException("No start tag found");
      } else {
         return readFamilies(parser, resources);
      }
   }

   @Nullable
   private static FontResourcesParserCompat.FamilyResourceEntry readFamilies(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
      parser.require(2, (String)null, "font-family");
      String tag = parser.getName();
      if (tag.equals("font-family")) {
         return readFamily(parser, resources);
      } else {
         skip(parser);
         return null;
      }
   }

   @Nullable
   private static FontResourcesParserCompat.FamilyResourceEntry readFamily(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
      AttributeSet attrs = Xml.asAttributeSet(parser);
      TypedArray array = resources.obtainAttributes(attrs, styleable.FontFamily);
      String authority = array.getString(styleable.FontFamily_fontProviderAuthority);
      String providerPackage = array.getString(styleable.FontFamily_fontProviderPackage);
      String query = array.getString(styleable.FontFamily_fontProviderQuery);
      int certsId = array.getResourceId(styleable.FontFamily_fontProviderCerts, 0);
      int strategy = array.getInteger(styleable.FontFamily_fontProviderFetchStrategy, 1);
      int timeoutMs = array.getInteger(styleable.FontFamily_fontProviderFetchTimeout, 500);
      array.recycle();
      if (authority != null && providerPackage != null && query != null) {
         while(parser.next() != 3) {
            skip(parser);
         }

         List certs = readCerts(resources, certsId);
         return new FontResourcesParserCompat.ProviderResourceEntry(new FontRequest(authority, providerPackage, query, certs), strategy, timeoutMs);
      } else {
         ArrayList fonts = new ArrayList();

         while(parser.next() != 3) {
            if (parser.getEventType() == 2) {
               String tag = parser.getName();
               if (tag.equals("font")) {
                  fonts.add(readFont(parser, resources));
               } else {
                  skip(parser);
               }
            }
         }

         if (fonts.isEmpty()) {
            return null;
         } else {
            return new FontResourcesParserCompat.FontFamilyFilesResourceEntry((FontResourcesParserCompat.FontFileResourceEntry[])fonts.toArray(new FontResourcesParserCompat.FontFileResourceEntry[fonts.size()]));
         }
      }
   }

   public static List readCerts(Resources resources, @ArrayRes int certsId) {
      List certs = null;
      if (certsId != 0) {
         TypedArray typedArray = resources.obtainTypedArray(certsId);
         if (typedArray.length() > 0) {
            certs = new ArrayList();
            boolean isArrayOfArrays = typedArray.getResourceId(0, 0) != 0;
            if (isArrayOfArrays) {
               for(int i = 0; i < typedArray.length(); ++i) {
                  int certId = typedArray.getResourceId(i, 0);
                  String[] certsArray = resources.getStringArray(certId);
                  List certsList = toByteArrayList(certsArray);
                  certs.add(certsList);
               }
            } else {
               String[] certsArray = resources.getStringArray(certsId);
               List certsList = toByteArrayList(certsArray);
               certs.add(certsList);
            }
         }

         typedArray.recycle();
      }

      return (List)(certs != null ? certs : Collections.emptyList());
   }

   private static List toByteArrayList(String[] stringArray) {
      List result = new ArrayList();
      String[] var2 = stringArray;
      int var3 = stringArray.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String item = var2[var4];
         result.add(Base64.decode(item, 0));
      }

      return result;
   }

   private static FontResourcesParserCompat.FontFileResourceEntry readFont(XmlPullParser parser, Resources resources) throws XmlPullParserException, IOException {
      AttributeSet attrs = Xml.asAttributeSet(parser);
      TypedArray array = resources.obtainAttributes(attrs, styleable.FontFamilyFont);
      int weight = array.getInt(styleable.FontFamilyFont_fontWeight, 400);
      boolean isItalic = 1 == array.getInt(styleable.FontFamilyFont_fontStyle, 0);
      int resourceId = array.getResourceId(styleable.FontFamilyFont_font, 0);
      String filename = array.getString(styleable.FontFamilyFont_font);
      array.recycle();

      while(parser.next() != 3) {
         skip(parser);
      }

      return new FontResourcesParserCompat.FontFileResourceEntry(filename, weight, isItalic, resourceId);
   }

   private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
      int depth = 1;

      while(depth > 0) {
         switch(parser.next()) {
         case 2:
            ++depth;
            break;
         case 3:
            --depth;
         }
      }

   }

   public static final class FontFamilyFilesResourceEntry implements FontResourcesParserCompat.FamilyResourceEntry {
      @NonNull
      private final FontResourcesParserCompat.FontFileResourceEntry[] mEntries;

      public FontFamilyFilesResourceEntry(@NonNull FontResourcesParserCompat.FontFileResourceEntry[] entries) {
         this.mEntries = entries;
      }

      @NonNull
      public FontResourcesParserCompat.FontFileResourceEntry[] getEntries() {
         return this.mEntries;
      }
   }

   public static final class FontFileResourceEntry {
      @NonNull
      private final String mFileName;
      private int mWeight;
      private boolean mItalic;
      private int mResourceId;

      public FontFileResourceEntry(@NonNull String fileName, int weight, boolean italic, int resourceId) {
         this.mFileName = fileName;
         this.mWeight = weight;
         this.mItalic = italic;
         this.mResourceId = resourceId;
      }

      @NonNull
      public String getFileName() {
         return this.mFileName;
      }

      public int getWeight() {
         return this.mWeight;
      }

      public boolean isItalic() {
         return this.mItalic;
      }

      public int getResourceId() {
         return this.mResourceId;
      }
   }

   public static final class ProviderResourceEntry implements FontResourcesParserCompat.FamilyResourceEntry {
      @NonNull
      private final FontRequest mRequest;
      private final int mTimeoutMs;
      private final int mStrategy;

      public ProviderResourceEntry(@NonNull FontRequest request, int strategy, int timeoutMs) {
         this.mRequest = request;
         this.mStrategy = strategy;
         this.mTimeoutMs = timeoutMs;
      }

      @NonNull
      public FontRequest getRequest() {
         return this.mRequest;
      }

      public int getFetchStrategy() {
         return this.mStrategy;
      }

      public int getTimeout() {
         return this.mTimeoutMs;
      }
   }

   public interface FamilyResourceEntry {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface FetchStrategy {
   }
}
