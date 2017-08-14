package android.support.v4.os;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
interface LocaleListInterface {
   void setLocaleList(@NonNull Locale... var1);

   Object getLocaleList();

   Locale get(int var1);

   boolean isEmpty();

   @IntRange(
      from = 0L
   )
   int size();

   @IntRange(
      from = -1L
   )
   int indexOf(Locale var1);

   boolean equals(Object var1);

   int hashCode();

   String toString();

   String toLanguageTags();

   @Nullable
   Locale getFirstMatch(String[] var1);
}
