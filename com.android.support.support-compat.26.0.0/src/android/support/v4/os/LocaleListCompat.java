package android.support.v4.os;

import android.os.LocaleList;
import android.os.Build.VERSION;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.Size;
import java.util.Locale;

public final class LocaleListCompat {
   static final LocaleListInterface IMPL;
   private static final LocaleListCompat sEmptyLocaleList = new LocaleListCompat();

   @RequiresApi(24)
   public static LocaleListCompat wrap(Object object) {
      LocaleListCompat instance = new LocaleListCompat();
      if (object instanceof LocaleList) {
         instance.setLocaleList((LocaleList)object);
      }

      return instance;
   }

   @Nullable
   public Object unwrap() {
      return IMPL.getLocaleList();
   }

   public static LocaleListCompat create(@NonNull Locale... localeList) {
      LocaleListCompat instance = new LocaleListCompat();
      instance.setLocaleListArray(localeList);
      return instance;
   }

   public Locale get(int index) {
      return IMPL.get(index);
   }

   public boolean isEmpty() {
      return IMPL.isEmpty();
   }

   @IntRange(
      from = 0L
   )
   public int size() {
      return IMPL.size();
   }

   @IntRange(
      from = -1L
   )
   public int indexOf(Locale locale) {
      return IMPL.indexOf(locale);
   }

   @NonNull
   public String toLanguageTags() {
      return IMPL.toLanguageTags();
   }

   public Locale getFirstMatch(String[] supportedLocales) {
      return IMPL.getFirstMatch(supportedLocales);
   }

   @NonNull
   public static LocaleListCompat getEmptyLocaleList() {
      return sEmptyLocaleList;
   }

   @NonNull
   public static LocaleListCompat forLanguageTags(@Nullable String list) {
      if (list != null && !list.isEmpty()) {
         String[] tags = list.split(",");
         Locale[] localeArray = new Locale[tags.length];

         for(int i = 0; i < localeArray.length; ++i) {
            localeArray[i] = VERSION.SDK_INT >= 21 ? Locale.forLanguageTag(tags[i]) : LocaleHelper.forLanguageTag(tags[i]);
         }

         LocaleListCompat instance = new LocaleListCompat();
         instance.setLocaleListArray(localeArray);
         return instance;
      } else {
         return getEmptyLocaleList();
      }
   }

   @NonNull
   @Size(
      min = 1L
   )
   public static LocaleListCompat getAdjustedDefault() {
      return VERSION.SDK_INT >= 24 ? wrap(LocaleList.getAdjustedDefault()) : create(Locale.getDefault());
   }

   @NonNull
   @Size(
      min = 1L
   )
   public static LocaleListCompat getDefault() {
      return VERSION.SDK_INT >= 24 ? wrap(LocaleList.getDefault()) : create(Locale.getDefault());
   }

   public boolean equals(Object other) {
      return IMPL.equals(other);
   }

   public int hashCode() {
      return IMPL.hashCode();
   }

   public String toString() {
      return IMPL.toString();
   }

   @RequiresApi(24)
   private void setLocaleList(LocaleList localeList) {
      int localeListSize = localeList.size();
      if (localeListSize > 0) {
         Locale[] localeArrayList = new Locale[localeListSize];

         for(int i = 0; i < localeListSize; ++i) {
            localeArrayList[i] = localeList.get(i);
         }

         IMPL.setLocaleList(localeArrayList);
      }

   }

   private void setLocaleListArray(Locale... localeArrayList) {
      IMPL.setLocaleList(localeArrayList);
   }

   static {
      if (VERSION.SDK_INT >= 24) {
         IMPL = new LocaleListCompat.LocaleListCompatApi24Impl();
      } else {
         IMPL = new LocaleListCompat.LocaleListCompatBaseImpl();
      }

   }

   @RequiresApi(24)
   static class LocaleListCompatApi24Impl implements LocaleListInterface {
      private LocaleList mLocaleList = new LocaleList(new Locale[0]);

      public void setLocaleList(@NonNull Locale... list) {
         this.mLocaleList = new LocaleList(list);
      }

      public Object getLocaleList() {
         return this.mLocaleList;
      }

      public Locale get(int index) {
         return this.mLocaleList.get(index);
      }

      public boolean isEmpty() {
         return this.mLocaleList.isEmpty();
      }

      @IntRange(
         from = 0L
      )
      public int size() {
         return this.mLocaleList.size();
      }

      @IntRange(
         from = -1L
      )
      public int indexOf(Locale locale) {
         return this.mLocaleList.indexOf(locale);
      }

      public boolean equals(Object other) {
         return this.mLocaleList.equals(((LocaleListCompat)other).unwrap());
      }

      public int hashCode() {
         return this.mLocaleList.hashCode();
      }

      public String toString() {
         return this.mLocaleList.toString();
      }

      public String toLanguageTags() {
         return this.mLocaleList.toLanguageTags();
      }

      @Nullable
      public Locale getFirstMatch(String[] supportedLocales) {
         return this.mLocaleList != null ? this.mLocaleList.getFirstMatch(supportedLocales) : null;
      }
   }

   static class LocaleListCompatBaseImpl implements LocaleListInterface {
      private LocaleListHelper mLocaleList = new LocaleListHelper(new Locale[0]);

      public void setLocaleList(@NonNull Locale... list) {
         this.mLocaleList = new LocaleListHelper(list);
      }

      public Object getLocaleList() {
         return this.mLocaleList;
      }

      public Locale get(int index) {
         return this.mLocaleList.get(index);
      }

      public boolean isEmpty() {
         return this.mLocaleList.isEmpty();
      }

      @IntRange(
         from = 0L
      )
      public int size() {
         return this.mLocaleList.size();
      }

      @IntRange(
         from = -1L
      )
      public int indexOf(Locale locale) {
         return this.mLocaleList.indexOf(locale);
      }

      public boolean equals(Object other) {
         return this.mLocaleList.equals(((LocaleListCompat)other).unwrap());
      }

      public int hashCode() {
         return this.mLocaleList.hashCode();
      }

      public String toString() {
         return this.mLocaleList.toString();
      }

      public String toLanguageTags() {
         return this.mLocaleList.toLanguageTags();
      }

      @Nullable
      public Locale getFirstMatch(String[] supportedLocales) {
         return this.mLocaleList != null ? this.mLocaleList.getFirstMatch(supportedLocales) : null;
      }
   }
}
