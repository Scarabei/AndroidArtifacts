package com.google.android.gms.location.places.internal;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class zzg {
   public static String zzi(Collection var0) {
      return var0 != null && !var0.isEmpty() ? TextUtils.join(", ", var0) : null;
   }

   public static CharSequence zza(String var0, List var1, CharacterStyle var2) {
      if (var2 == null) {
         return var0;
      } else {
         SpannableString var3 = new SpannableString(var0);
         Iterator var4 = var1.iterator();

         while(var4.hasNext()) {
            zzb var5 = (zzb)var4.next();
            var3.setSpan(CharacterStyle.wrap(var2), var5.mOffset, var5.mOffset + var5.mLength, 0);
         }

         return var3;
      }
   }
}
