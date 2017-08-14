package com.google.android.gms.vision.text;

import java.util.Comparator;
import java.util.Map.Entry;

final class zza implements Comparator {
   zza(TextBlock var1) {
   }

   // $FF: synthetic method
   public final int compare(Object var1, Object var2) {
      Entry var10000 = (Entry)var1;
      Entry var3 = (Entry)var2;
      return ((Integer)var10000.getValue()).compareTo((Integer)var3.getValue());
   }
}
