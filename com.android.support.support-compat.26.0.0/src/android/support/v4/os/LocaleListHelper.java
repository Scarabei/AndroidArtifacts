package android.support.v4.os;

import android.os.Build.VERSION;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.Size;
import android.support.annotation.RestrictTo.Scope;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
@RequiresApi(14)
final class LocaleListHelper {
   private final Locale[] mList;
   @NonNull
   private final String mStringRepresentation;
   private static final Locale[] sEmptyList = new Locale[0];
   private static final LocaleListHelper sEmptyLocaleList = new LocaleListHelper(new Locale[0]);
   private static final String STRING_EN_XA = "en-XA";
   private static final String STRING_AR_XB = "ar-XB";
   private static final Locale LOCALE_EN_XA = new Locale("en", "XA");
   private static final Locale LOCALE_AR_XB = new Locale("ar", "XB");
   private static final int NUM_PSEUDO_LOCALES = 2;
   private static final Locale EN_LATN = LocaleHelper.forLanguageTag("en-Latn");
   private static final Object sLock = new Object();
   @GuardedBy("sLock")
   private static LocaleListHelper sLastExplicitlySetLocaleList = null;
   @GuardedBy("sLock")
   private static LocaleListHelper sDefaultLocaleList = null;
   @GuardedBy("sLock")
   private static LocaleListHelper sDefaultAdjustedLocaleList = null;
   @GuardedBy("sLock")
   private static Locale sLastDefaultLocale = null;

   @RestrictTo({Scope.LIBRARY_GROUP})
   Locale get(int index) {
      return 0 <= index && index < this.mList.length ? this.mList[index] : null;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   boolean isEmpty() {
      return this.mList.length == 0;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @IntRange(
      from = 0L
   )
   int size() {
      return this.mList.length;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @IntRange(
      from = -1L
   )
   int indexOf(Locale locale) {
      for(int i = 0; i < this.mList.length; ++i) {
         if (this.mList[i].equals(locale)) {
            return i;
         }
      }

      return -1;
   }

   public boolean equals(Object other) {
      if (other == this) {
         return true;
      } else if (!(other instanceof LocaleListHelper)) {
         return false;
      } else {
         Locale[] otherList = ((LocaleListHelper)other).mList;
         if (this.mList.length != otherList.length) {
            return false;
         } else {
            for(int i = 0; i < this.mList.length; ++i) {
               if (!this.mList[i].equals(otherList[i])) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      int result = 1;

      for(int i = 0; i < this.mList.length; ++i) {
         result = 31 * result + this.mList[i].hashCode();
      }

      return result;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("[");

      for(int i = 0; i < this.mList.length; ++i) {
         sb.append(this.mList[i]);
         if (i < this.mList.length - 1) {
            sb.append(',');
         }
      }

      sb.append("]");
      return sb.toString();
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @NonNull
   String toLanguageTags() {
      return this.mStringRepresentation;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   LocaleListHelper(@NonNull Locale... list) {
      if (list.length == 0) {
         this.mList = sEmptyList;
         this.mStringRepresentation = "";
      } else {
         Locale[] localeList = new Locale[list.length];
         HashSet seenLocales = new HashSet();
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < list.length; ++i) {
            Locale l = list[i];
            if (l == null) {
               throw new NullPointerException("list[" + i + "] is null");
            }

            if (seenLocales.contains(l)) {
               throw new IllegalArgumentException("list[" + i + "] is a repetition");
            }

            Locale localeClone = (Locale)l.clone();
            localeList[i] = localeClone;
            sb.append(LocaleHelper.toLanguageTag(localeClone));
            if (i < list.length - 1) {
               sb.append(',');
            }

            seenLocales.add(localeClone);
         }

         this.mList = localeList;
         this.mStringRepresentation = sb.toString();
      }

   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   LocaleListHelper(@NonNull Locale topLocale, LocaleListHelper otherLocales) {
      if (topLocale == null) {
         throw new NullPointerException("topLocale is null");
      } else {
         int inputLength = otherLocales == null ? 0 : otherLocales.mList.length;
         int topLocaleIndex = -1;

         int outputLength;
         for(outputLength = 0; outputLength < inputLength; ++outputLength) {
            if (topLocale.equals(otherLocales.mList[outputLength])) {
               topLocaleIndex = outputLength;
               break;
            }
         }

         outputLength = inputLength + (topLocaleIndex == -1 ? 1 : 0);
         Locale[] localeList = new Locale[outputLength];
         localeList[0] = (Locale)topLocale.clone();
         int i;
         if (topLocaleIndex == -1) {
            for(i = 0; i < inputLength; ++i) {
               localeList[i + 1] = (Locale)otherLocales.mList[i].clone();
            }
         } else {
            for(i = 0; i < topLocaleIndex; ++i) {
               localeList[i + 1] = (Locale)otherLocales.mList[i].clone();
            }

            for(i = topLocaleIndex + 1; i < inputLength; ++i) {
               localeList[i] = (Locale)otherLocales.mList[i].clone();
            }
         }

         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < outputLength; ++i) {
            sb.append(LocaleHelper.toLanguageTag(localeList[i]));
            if (i < outputLength - 1) {
               sb.append(',');
            }
         }

         this.mList = localeList;
         this.mStringRepresentation = sb.toString();
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @NonNull
   static LocaleListHelper getEmptyLocaleList() {
      return sEmptyLocaleList;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @NonNull
   static LocaleListHelper forLanguageTags(@Nullable String list) {
      if (list != null && !list.isEmpty()) {
         String[] tags = list.split(",");
         Locale[] localeArray = new Locale[tags.length];

         for(int i = 0; i < localeArray.length; ++i) {
            localeArray[i] = LocaleHelper.forLanguageTag(tags[i]);
         }

         return new LocaleListHelper(localeArray);
      } else {
         return getEmptyLocaleList();
      }
   }

   private static String getLikelyScript(Locale locale) {
      if (VERSION.SDK_INT >= 21) {
         String script = locale.getScript();
         return !script.isEmpty() ? script : "";
      } else {
         return "";
      }
   }

   private static boolean isPseudoLocale(String locale) {
      return "en-XA".equals(locale) || "ar-XB".equals(locale);
   }

   private static boolean isPseudoLocale(Locale locale) {
      return LOCALE_EN_XA.equals(locale) || LOCALE_AR_XB.equals(locale);
   }

   @IntRange(
      from = 0L,
      to = 1L
   )
   private static int matchScore(Locale supported, Locale desired) {
      if (supported.equals(desired)) {
         return 1;
      } else if (!supported.getLanguage().equals(desired.getLanguage())) {
         return 0;
      } else if (!isPseudoLocale(supported) && !isPseudoLocale(desired)) {
         String supportedScr = getLikelyScript(supported);
         String supportedRegion;
         if (!supportedScr.isEmpty()) {
            supportedRegion = getLikelyScript(desired);
            return supportedScr.equals(supportedRegion) ? 1 : 0;
         } else {
            supportedRegion = supported.getCountry();
            return !supportedRegion.isEmpty() && !supportedRegion.equals(desired.getCountry()) ? 0 : 1;
         }
      } else {
         return 0;
      }
   }

   private int findFirstMatchIndex(Locale supportedLocale) {
      for(int idx = 0; idx < this.mList.length; ++idx) {
         int score = matchScore(supportedLocale, this.mList[idx]);
         if (score > 0) {
            return idx;
         }
      }

      return Integer.MAX_VALUE;
   }

   private int computeFirstMatchIndex(Collection supportedLocales, boolean assumeEnglishIsSupported) {
      if (this.mList.length == 1) {
         return 0;
      } else if (this.mList.length == 0) {
         return -1;
      } else {
         int bestIndex = Integer.MAX_VALUE;
         if (assumeEnglishIsSupported) {
            int idx = this.findFirstMatchIndex(EN_LATN);
            if (idx == 0) {
               return 0;
            }

            if (idx < bestIndex) {
               bestIndex = idx;
            }
         }

         Iterator var8 = supportedLocales.iterator();

         while(var8.hasNext()) {
            String languageTag = (String)var8.next();
            Locale supportedLocale = LocaleHelper.forLanguageTag(languageTag);
            int idx = this.findFirstMatchIndex(supportedLocale);
            if (idx == 0) {
               return 0;
            }

            if (idx < bestIndex) {
               bestIndex = idx;
            }
         }

         if (bestIndex == Integer.MAX_VALUE) {
            return 0;
         } else {
            return bestIndex;
         }
      }
   }

   private Locale computeFirstMatch(Collection supportedLocales, boolean assumeEnglishIsSupported) {
      int bestIndex = this.computeFirstMatchIndex(supportedLocales, assumeEnglishIsSupported);
      return bestIndex == -1 ? null : this.mList[bestIndex];
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @Nullable
   Locale getFirstMatch(String[] supportedLocales) {
      return this.computeFirstMatch(Arrays.asList(supportedLocales), false);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   int getFirstMatchIndex(String[] supportedLocales) {
      return this.computeFirstMatchIndex(Arrays.asList(supportedLocales), false);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @Nullable
   Locale getFirstMatchWithEnglishSupported(String[] supportedLocales) {
      return this.computeFirstMatch(Arrays.asList(supportedLocales), true);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   int getFirstMatchIndexWithEnglishSupported(Collection supportedLocales) {
      return this.computeFirstMatchIndex(supportedLocales, true);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   int getFirstMatchIndexWithEnglishSupported(String[] supportedLocales) {
      return this.getFirstMatchIndexWithEnglishSupported((Collection)Arrays.asList(supportedLocales));
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   static boolean isPseudoLocalesOnly(@Nullable String[] supportedLocales) {
      if (supportedLocales == null) {
         return true;
      } else if (supportedLocales.length > 3) {
         return false;
      } else {
         String[] var1 = supportedLocales;
         int var2 = supportedLocales.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            String locale = var1[var3];
            if (!locale.isEmpty() && !isPseudoLocale(locale)) {
               return false;
            }
         }

         return true;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @NonNull
   @Size(
      min = 1L
   )
   static LocaleListHelper getDefault() {
      Locale defaultLocale = Locale.getDefault();
      Object var1 = sLock;
      synchronized(sLock) {
         if (!defaultLocale.equals(sLastDefaultLocale)) {
            sLastDefaultLocale = defaultLocale;
            if (sDefaultLocaleList != null && defaultLocale.equals(sDefaultLocaleList.get(0))) {
               return sDefaultLocaleList;
            }

            sDefaultLocaleList = new LocaleListHelper(defaultLocale, sLastExplicitlySetLocaleList);
            sDefaultAdjustedLocaleList = sDefaultLocaleList;
         }

         return sDefaultLocaleList;
      }
   }

   @NonNull
   @Size(
      min = 1L
   )
   static LocaleListHelper getAdjustedDefault() {
      getDefault();
      Object var0 = sLock;
      synchronized(sLock) {
         return sDefaultAdjustedLocaleList;
      }
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   static void setDefault(@NonNull @Size(min = 1L) LocaleListHelper locales) {
      setDefault(locales, 0);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   static void setDefault(@NonNull @Size(min = 1L) LocaleListHelper locales, int localeIndex) {
      if (locales == null) {
         throw new NullPointerException("locales is null");
      } else if (locales.isEmpty()) {
         throw new IllegalArgumentException("locales is empty");
      } else {
         Object var2 = sLock;
         synchronized(sLock) {
            sLastDefaultLocale = locales.get(localeIndex);
            Locale.setDefault(sLastDefaultLocale);
            sLastExplicitlySetLocaleList = locales;
            sDefaultLocaleList = locales;
            if (localeIndex == 0) {
               sDefaultAdjustedLocaleList = sDefaultLocaleList;
            } else {
               sDefaultAdjustedLocaleList = new LocaleListHelper(sLastDefaultLocale, sDefaultLocaleList);
            }

         }
      }
   }
}
