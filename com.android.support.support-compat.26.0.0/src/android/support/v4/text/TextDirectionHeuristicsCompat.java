package android.support.v4.text;

import java.nio.CharBuffer;
import java.util.Locale;

public final class TextDirectionHeuristicsCompat {
   public static final TextDirectionHeuristicCompat LTR = new TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal((TextDirectionHeuristicsCompat.TextDirectionAlgorithm)null, false);
   public static final TextDirectionHeuristicCompat RTL = new TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal((TextDirectionHeuristicsCompat.TextDirectionAlgorithm)null, true);
   public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
   public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
   public static final TextDirectionHeuristicCompat ANYRTL_LTR;
   public static final TextDirectionHeuristicCompat LOCALE;
   private static final int STATE_TRUE = 0;
   private static final int STATE_FALSE = 1;
   private static final int STATE_UNKNOWN = 2;

   static int isRtlText(int directionality) {
      switch(directionality) {
      case 0:
         return 1;
      case 1:
      case 2:
         return 0;
      default:
         return 2;
      }
   }

   static int isRtlTextOrFormat(int directionality) {
      switch(directionality) {
      case 0:
      case 14:
      case 15:
         return 1;
      case 1:
      case 2:
      case 16:
      case 17:
         return 0;
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      default:
         return 2;
      }
   }

   static {
      FIRSTSTRONG_LTR = new TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat.FirstStrong.INSTANCE, false);
      FIRSTSTRONG_RTL = new TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat.FirstStrong.INSTANCE, true);
      ANYRTL_LTR = new TextDirectionHeuristicsCompat.TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat.AnyStrong.INSTANCE_RTL, false);
      LOCALE = TextDirectionHeuristicsCompat.TextDirectionHeuristicLocale.INSTANCE;
   }

   private static class TextDirectionHeuristicLocale extends TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl {
      public static final TextDirectionHeuristicsCompat.TextDirectionHeuristicLocale INSTANCE = new TextDirectionHeuristicsCompat.TextDirectionHeuristicLocale();

      public TextDirectionHeuristicLocale() {
         super((TextDirectionHeuristicsCompat.TextDirectionAlgorithm)null);
      }

      protected boolean defaultIsRtl() {
         int dir = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault());
         return dir == 1;
      }
   }

   private static class AnyStrong implements TextDirectionHeuristicsCompat.TextDirectionAlgorithm {
      private final boolean mLookForRtl;
      public static final TextDirectionHeuristicsCompat.AnyStrong INSTANCE_RTL = new TextDirectionHeuristicsCompat.AnyStrong(true);
      public static final TextDirectionHeuristicsCompat.AnyStrong INSTANCE_LTR = new TextDirectionHeuristicsCompat.AnyStrong(false);

      public int checkRtl(CharSequence cs, int start, int count) {
         boolean haveUnlookedFor = false;
         int i = start;

         for(int e = start + count; i < e; ++i) {
            switch(TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(cs.charAt(i)))) {
            case 0:
               if (this.mLookForRtl) {
                  return 0;
               }

               haveUnlookedFor = true;
               break;
            case 1:
               if (!this.mLookForRtl) {
                  return 1;
               }

               haveUnlookedFor = true;
            }
         }

         if (haveUnlookedFor) {
            return this.mLookForRtl ? 1 : 0;
         } else {
            return 2;
         }
      }

      private AnyStrong(boolean lookForRtl) {
         this.mLookForRtl = lookForRtl;
      }
   }

   private static class FirstStrong implements TextDirectionHeuristicsCompat.TextDirectionAlgorithm {
      public static final TextDirectionHeuristicsCompat.FirstStrong INSTANCE = new TextDirectionHeuristicsCompat.FirstStrong();

      public int checkRtl(CharSequence cs, int start, int count) {
         int result = 2;
         int i = start;

         for(int e = start + count; i < e && result == 2; ++i) {
            result = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(cs.charAt(i)));
         }

         return result;
      }
   }

   private interface TextDirectionAlgorithm {
      int checkRtl(CharSequence var1, int var2, int var3);
   }

   private static class TextDirectionHeuristicInternal extends TextDirectionHeuristicsCompat.TextDirectionHeuristicImpl {
      private final boolean mDefaultIsRtl;

      TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat.TextDirectionAlgorithm algorithm, boolean defaultIsRtl) {
         super(algorithm);
         this.mDefaultIsRtl = defaultIsRtl;
      }

      protected boolean defaultIsRtl() {
         return this.mDefaultIsRtl;
      }
   }

   private abstract static class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {
      private final TextDirectionHeuristicsCompat.TextDirectionAlgorithm mAlgorithm;

      public TextDirectionHeuristicImpl(TextDirectionHeuristicsCompat.TextDirectionAlgorithm algorithm) {
         this.mAlgorithm = algorithm;
      }

      protected abstract boolean defaultIsRtl();

      public boolean isRtl(char[] array, int start, int count) {
         return this.isRtl((CharSequence)CharBuffer.wrap(array), start, count);
      }

      public boolean isRtl(CharSequence cs, int start, int count) {
         if (cs != null && start >= 0 && count >= 0 && cs.length() - count >= start) {
            return this.mAlgorithm == null ? this.defaultIsRtl() : this.doCheck(cs, start, count);
         } else {
            throw new IllegalArgumentException();
         }
      }

      private boolean doCheck(CharSequence cs, int start, int count) {
         switch(this.mAlgorithm.checkRtl(cs, start, count)) {
         case 0:
            return true;
         case 1:
            return false;
         default:
            return this.defaultIsRtl();
         }
      }
   }
}
