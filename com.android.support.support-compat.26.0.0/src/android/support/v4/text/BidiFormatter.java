package android.support.v4.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;

public final class BidiFormatter {
   private static TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC;
   private static final char LRE = '\u202a';
   private static final char RLE = '\u202b';
   private static final char PDF = '\u202c';
   private static final char LRM = '\u200e';
   private static final char RLM = '\u200f';
   private static final String LRM_STRING;
   private static final String RLM_STRING;
   private static final String EMPTY_STRING = "";
   private static final int FLAG_STEREO_RESET = 2;
   private static final int DEFAULT_FLAGS = 2;
   private static final BidiFormatter DEFAULT_LTR_INSTANCE;
   private static final BidiFormatter DEFAULT_RTL_INSTANCE;
   private final boolean mIsRtlContext;
   private final int mFlags;
   private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
   private static final int DIR_LTR = -1;
   private static final int DIR_UNKNOWN = 0;
   private static final int DIR_RTL = 1;

   public static BidiFormatter getInstance() {
      return (new BidiFormatter.Builder()).build();
   }

   public static BidiFormatter getInstance(boolean rtlContext) {
      return (new BidiFormatter.Builder(rtlContext)).build();
   }

   public static BidiFormatter getInstance(Locale locale) {
      return (new BidiFormatter.Builder(locale)).build();
   }

   private BidiFormatter(boolean isRtlContext, int flags, TextDirectionHeuristicCompat heuristic) {
      this.mIsRtlContext = isRtlContext;
      this.mFlags = flags;
      this.mDefaultTextDirectionHeuristicCompat = heuristic;
   }

   public boolean isRtlContext() {
      return this.mIsRtlContext;
   }

   public boolean getStereoReset() {
      return (this.mFlags & 2) != 0;
   }

   private String markAfter(CharSequence str, TextDirectionHeuristicCompat heuristic) {
      boolean isRtl = heuristic.isRtl((CharSequence)str, 0, str.length());
      if (this.mIsRtlContext || !isRtl && getExitDir(str) != 1) {
         return !this.mIsRtlContext || isRtl && getExitDir(str) != -1 ? "" : RLM_STRING;
      } else {
         return LRM_STRING;
      }
   }

   private String markBefore(CharSequence str, TextDirectionHeuristicCompat heuristic) {
      boolean isRtl = heuristic.isRtl((CharSequence)str, 0, str.length());
      if (this.mIsRtlContext || !isRtl && getEntryDir(str) != 1) {
         return !this.mIsRtlContext || isRtl && getEntryDir(str) != -1 ? "" : RLM_STRING;
      } else {
         return LRM_STRING;
      }
   }

   public boolean isRtl(String str) {
      return this.isRtl((CharSequence)str);
   }

   public boolean isRtl(CharSequence str) {
      return this.mDefaultTextDirectionHeuristicCompat.isRtl((CharSequence)str, 0, str.length());
   }

   public String unicodeWrap(String str, TextDirectionHeuristicCompat heuristic, boolean isolate) {
      return str == null ? null : this.unicodeWrap((CharSequence)str, heuristic, isolate).toString();
   }

   public CharSequence unicodeWrap(CharSequence str, TextDirectionHeuristicCompat heuristic, boolean isolate) {
      if (str == null) {
         return null;
      } else {
         boolean isRtl = heuristic.isRtl((CharSequence)str, 0, str.length());
         SpannableStringBuilder result = new SpannableStringBuilder();
         if (this.getStereoReset() && isolate) {
            result.append(this.markBefore(str, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
         }

         if (isRtl != this.mIsRtlContext) {
            result.append((char)(isRtl ? '\u202b' : '\u202a'));
            result.append(str);
            result.append('\u202c');
         } else {
            result.append(str);
         }

         if (isolate) {
            result.append(this.markAfter(str, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
         }

         return result;
      }
   }

   public String unicodeWrap(String str, TextDirectionHeuristicCompat heuristic) {
      return this.unicodeWrap(str, heuristic, true);
   }

   public CharSequence unicodeWrap(CharSequence str, TextDirectionHeuristicCompat heuristic) {
      return this.unicodeWrap(str, heuristic, true);
   }

   public String unicodeWrap(String str, boolean isolate) {
      return this.unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, isolate);
   }

   public CharSequence unicodeWrap(CharSequence str, boolean isolate) {
      return this.unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, isolate);
   }

   public String unicodeWrap(String str) {
      return this.unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, true);
   }

   public CharSequence unicodeWrap(CharSequence str) {
      return this.unicodeWrap(str, this.mDefaultTextDirectionHeuristicCompat, true);
   }

   private static boolean isRtlLocale(Locale locale) {
      return TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
   }

   private static int getExitDir(CharSequence str) {
      return (new BidiFormatter.DirectionalityEstimator(str, false)).getExitDir();
   }

   private static int getEntryDir(CharSequence str) {
      return (new BidiFormatter.DirectionalityEstimator(str, false)).getEntryDir();
   }

   // $FF: synthetic method
   BidiFormatter(boolean x0, int x1, TextDirectionHeuristicCompat x2, Object x3) {
      this(x0, x1, x2);
   }

   static {
      DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
      LRM_STRING = Character.toString('\u200e');
      RLM_STRING = Character.toString('\u200f');
      DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
      DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
   }

   private static class DirectionalityEstimator {
      private static final int DIR_TYPE_CACHE_SIZE = 1792;
      private static final byte[] DIR_TYPE_CACHE = new byte[1792];
      private final CharSequence text;
      private final boolean isHtml;
      private final int length;
      private int charIndex;
      private char lastChar;

      DirectionalityEstimator(CharSequence text, boolean isHtml) {
         this.text = text;
         this.isHtml = isHtml;
         this.length = text.length();
      }

      int getEntryDir() {
         this.charIndex = 0;
         int embeddingLevel = 0;
         int embeddingLevelDir = 0;
         int firstNonEmptyEmbeddingLevel = 0;

         while(this.charIndex < this.length && firstNonEmptyEmbeddingLevel == 0) {
            switch(this.dirTypeForward()) {
            case 0:
               if (embeddingLevel == 0) {
                  return -1;
               }

               firstNonEmptyEmbeddingLevel = embeddingLevel;
               break;
            case 1:
            case 2:
               if (embeddingLevel == 0) {
                  return 1;
               }

               firstNonEmptyEmbeddingLevel = embeddingLevel;
               break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            default:
               firstNonEmptyEmbeddingLevel = embeddingLevel;
            case 9:
               break;
            case 14:
            case 15:
               ++embeddingLevel;
               embeddingLevelDir = -1;
               break;
            case 16:
            case 17:
               ++embeddingLevel;
               embeddingLevelDir = 1;
               break;
            case 18:
               --embeddingLevel;
               embeddingLevelDir = 0;
            }
         }

         if (firstNonEmptyEmbeddingLevel == 0) {
            return 0;
         } else if (embeddingLevelDir != 0) {
            return embeddingLevelDir;
         } else {
            while(this.charIndex > 0) {
               switch(this.dirTypeBackward()) {
               case 14:
               case 15:
                  if (firstNonEmptyEmbeddingLevel == embeddingLevel) {
                     return -1;
                  }

                  --embeddingLevel;
                  break;
               case 16:
               case 17:
                  if (firstNonEmptyEmbeddingLevel == embeddingLevel) {
                     return 1;
                  }

                  --embeddingLevel;
                  break;
               case 18:
                  ++embeddingLevel;
               }
            }

            return 0;
         }
      }

      int getExitDir() {
         this.charIndex = this.length;
         int embeddingLevel = 0;
         int lastNonEmptyEmbeddingLevel = 0;

         while(this.charIndex > 0) {
            switch(this.dirTypeBackward()) {
            case 0:
               if (embeddingLevel == 0) {
                  return -1;
               }

               if (lastNonEmptyEmbeddingLevel == 0) {
                  lastNonEmptyEmbeddingLevel = embeddingLevel;
               }
               break;
            case 1:
            case 2:
               if (embeddingLevel == 0) {
                  return 1;
               }

               if (lastNonEmptyEmbeddingLevel == 0) {
                  lastNonEmptyEmbeddingLevel = embeddingLevel;
               }
               break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            default:
               if (lastNonEmptyEmbeddingLevel == 0) {
                  lastNonEmptyEmbeddingLevel = embeddingLevel;
               }
            case 9:
               break;
            case 14:
            case 15:
               if (lastNonEmptyEmbeddingLevel == embeddingLevel) {
                  return -1;
               }

               --embeddingLevel;
               break;
            case 16:
            case 17:
               if (lastNonEmptyEmbeddingLevel == embeddingLevel) {
                  return 1;
               }

               --embeddingLevel;
               break;
            case 18:
               ++embeddingLevel;
            }
         }

         return 0;
      }

      private static byte getCachedDirectionality(char c) {
         return c < 1792 ? DIR_TYPE_CACHE[c] : Character.getDirectionality(c);
      }

      byte dirTypeForward() {
         this.lastChar = this.text.charAt(this.charIndex);
         if (Character.isHighSurrogate(this.lastChar)) {
            int codePoint = Character.codePointAt(this.text, this.charIndex);
            this.charIndex += Character.charCount(codePoint);
            return Character.getDirectionality(codePoint);
         } else {
            ++this.charIndex;
            byte dirType = getCachedDirectionality(this.lastChar);
            if (this.isHtml) {
               if (this.lastChar == '<') {
                  dirType = this.skipTagForward();
               } else if (this.lastChar == '&') {
                  dirType = this.skipEntityForward();
               }
            }

            return dirType;
         }
      }

      byte dirTypeBackward() {
         this.lastChar = this.text.charAt(this.charIndex - 1);
         if (Character.isLowSurrogate(this.lastChar)) {
            int codePoint = Character.codePointBefore(this.text, this.charIndex);
            this.charIndex -= Character.charCount(codePoint);
            return Character.getDirectionality(codePoint);
         } else {
            --this.charIndex;
            byte dirType = getCachedDirectionality(this.lastChar);
            if (this.isHtml) {
               if (this.lastChar == '>') {
                  dirType = this.skipTagBackward();
               } else if (this.lastChar == ';') {
                  dirType = this.skipEntityBackward();
               }
            }

            return dirType;
         }
      }

      private byte skipTagForward() {
         int initialCharIndex = this.charIndex;

         while(true) {
            do {
               if (this.charIndex >= this.length) {
                  this.charIndex = initialCharIndex;
                  this.lastChar = '<';
                  return 13;
               }

               this.lastChar = this.text.charAt(this.charIndex++);
               if (this.lastChar == '>') {
                  return 12;
               }
            } while(this.lastChar != '"' && this.lastChar != '\'');

            char quote = this.lastChar;

            while(this.charIndex < this.length && (this.lastChar = this.text.charAt(this.charIndex++)) != quote) {
               ;
            }
         }
      }

      private byte skipTagBackward() {
         int initialCharIndex = this.charIndex;

         while(this.charIndex > 0) {
            this.lastChar = this.text.charAt(--this.charIndex);
            if (this.lastChar == '<') {
               return 12;
            }

            if (this.lastChar == '>') {
               break;
            }

            if (this.lastChar == '"' || this.lastChar == '\'') {
               char quote = this.lastChar;

               while(this.charIndex > 0 && (this.lastChar = this.text.charAt(--this.charIndex)) != quote) {
                  ;
               }
            }
         }

         this.charIndex = initialCharIndex;
         this.lastChar = '>';
         return 13;
      }

      private byte skipEntityForward() {
         while(this.charIndex < this.length && (this.lastChar = this.text.charAt(this.charIndex++)) != ';') {
            ;
         }

         return 12;
      }

      private byte skipEntityBackward() {
         int initialCharIndex = this.charIndex;

         while(this.charIndex > 0) {
            this.lastChar = this.text.charAt(--this.charIndex);
            if (this.lastChar == '&') {
               return 12;
            }

            if (this.lastChar == ';') {
               break;
            }
         }

         this.charIndex = initialCharIndex;
         this.lastChar = ';';
         return 13;
      }

      static {
         for(int i = 0; i < 1792; ++i) {
            DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
         }

      }
   }

   public static final class Builder {
      private boolean mIsRtlContext;
      private int mFlags;
      private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

      public Builder() {
         this.initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
      }

      public Builder(boolean rtlContext) {
         this.initialize(rtlContext);
      }

      public Builder(Locale locale) {
         this.initialize(BidiFormatter.isRtlLocale(locale));
      }

      private void initialize(boolean isRtlContext) {
         this.mIsRtlContext = isRtlContext;
         this.mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
         this.mFlags = 2;
      }

      public BidiFormatter.Builder stereoReset(boolean stereoReset) {
         if (stereoReset) {
            this.mFlags |= 2;
         } else {
            this.mFlags &= -3;
         }

         return this;
      }

      public BidiFormatter.Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat heuristic) {
         this.mTextDirectionHeuristicCompat = heuristic;
         return this;
      }

      private static BidiFormatter getDefaultInstanceFromContext(boolean isRtlContext) {
         return isRtlContext ? BidiFormatter.DEFAULT_RTL_INSTANCE : BidiFormatter.DEFAULT_LTR_INSTANCE;
      }

      public BidiFormatter build() {
         return this.mFlags == 2 && this.mTextDirectionHeuristicCompat == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC ? getDefaultInstanceFromContext(this.mIsRtlContext) : new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat);
      }
   }
}
