package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;

@zzzn
public final class zzhj {
   public static int zzA(String var0) {
      byte[] var1;
      try {
         var1 = var0.getBytes("UTF-8");
      } catch (UnsupportedEncodingException var8) {
         var1 = var0.getBytes();
      }

      int var3 = var1.length;
      byte[] var2 = var1;
      int var4 = 0;
      int var5 = 0 + (var3 & -4);

      int var6;
      for(var6 = 0; var6 < var5; var6 += 4) {
         int var7 = ((var7 = (var2[var6] & 255 | (var2[var6 + 1] & 255) << 8 | (var2[var6 + 2] & 255) << 16 | var2[var6 + 3] << 24) * -862048943) << 15 | var7 >>> 17) * 461845907;
         var4 = ((var4 ^= var7) << 13 | var4 >>> 19) * 5 + -430675100;
      }

      var6 = 0;
      switch(var3 & 3) {
      case 3:
         var6 = (var2[var5 + 2] & 255) << 16;
      case 2:
         var6 |= (var2[var5 + 1] & 255) << 8;
      case 1:
         var6 = ((var6 = (var6 | var2[var5] & 255) * -862048943) << 15 | var6 >>> 17) * 461845907;
         var4 ^= var6;
      default:
         return ((var4 ^ var3 ^ (var4 ^ var3) >>> 16) * -2048144789 ^ (var4 ^ var3 ^ (var4 ^ var3) >>> 16) * -2048144789 >>> 13) * -1028477387 ^ ((var4 ^ var3 ^ (var4 ^ var3) >>> 16) * -2048144789 ^ (var4 ^ var3 ^ (var4 ^ var3) >>> 16) * -2048144789 >>> 13) * -1028477387 >>> 16;
      }
   }

   @Nullable
   public static String[] zzd(@Nullable String var0, boolean var1) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         char[] var3 = var0.toCharArray();
         int var4 = var0.length();
         int var5 = 0;
         int var6 = 0;

         boolean var7;
         int var9;
         for(var7 = false; var5 < var4; var5 += var9) {
            int var8;
            var9 = Character.charCount(var8 = Character.codePointAt(var3, var5));
            UnicodeBlock var11;
            if (Character.isLetter(var8) && ((var11 = UnicodeBlock.of(var8)) == UnicodeBlock.BOPOMOFO || var11 == UnicodeBlock.BOPOMOFO_EXTENDED || var11 == UnicodeBlock.CJK_COMPATIBILITY || var11 == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || var11 == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT || var11 == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || var11 == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || var11 == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || var11 == UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS || var11 == UnicodeBlock.HANGUL_JAMO || var11 == UnicodeBlock.HANGUL_SYLLABLES || var11 == UnicodeBlock.HIRAGANA || var11 == UnicodeBlock.KATAKANA || var11 == UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS || var8 >= 65382 && var8 <= 65437 || var8 >= 65441 && var8 <= 65500)) {
               if (var7) {
                  var2.add(new String(var3, var6, var5 - var6));
               }

               var2.add(new String(var3, var5, var9));
               var7 = false;
            } else if (!Character.isLetterOrDigit(var8) && Character.getType(var8) != 6 && Character.getType(var8) != 8) {
               if (var1 && Character.charCount(var8) == 1 && Character.toChars(var8)[0] == '\'') {
                  if (!var7) {
                     var6 = var5;
                  }

                  var7 = true;
               } else if (var7) {
                  var2.add(new String(var3, var6, var5 - var6));
                  var7 = false;
               }
            } else {
               if (!var7) {
                  var6 = var5;
               }

               var7 = true;
            }
         }

         if (var7) {
            var2.add(new String(var3, var6, var5 - var6));
         }

         String[] var12 = new String[var2.size()];
         return (String[])var2.toArray(var12);
      }
   }
}
