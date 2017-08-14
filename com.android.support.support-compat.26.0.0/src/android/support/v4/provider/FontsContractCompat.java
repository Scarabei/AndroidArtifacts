package android.support.v4.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Build.VERSION;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class FontsContractCompat {
   private static final String TAG = "FontsContractCompat";
   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final String PARCEL_FONT_RESULTS = "font_results";
   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
   @RestrictTo({Scope.LIBRARY_GROUP})
   public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
   private static final LruCache sTypefaceCache = new LruCache(16);
   private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
   private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, 10000);
   private static final Object sLock = new Object();
   @GuardedBy("sLock")
   private static final SimpleArrayMap sPendingReplies = new SimpleArrayMap();
   private static final Comparator sByteArrayComparator = new Comparator() {
      public int compare(byte[] l, byte[] r) {
         if (l.length != r.length) {
            return l.length - r.length;
         } else {
            for(int i = 0; i < l.length; ++i) {
               if (l[i] != r[i]) {
                  return l[i] - r[i];
               }
            }

            return 0;
         }
      }
   };

   private static Typeface getFontInternal(Context context, FontRequest request, int style) {
      FontsContractCompat.FontFamilyResult result;
      try {
         result = fetchFonts(context, (CancellationSignal)null, request);
      } catch (NameNotFoundException var5) {
         return null;
      }

      return result.getStatusCode() == 0 ? TypefaceCompat.createFromFontInfo(context, (CancellationSignal)null, result.getFonts(), style) : null;
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public static Typeface getFontSync(final Context context, final FontRequest request, @Nullable final TextView targetView, int strategy, int timeout, final int style) {
      final String id = request.getIdentifier() + "-" + style;
      Typeface cached = (Typeface)sTypefaceCache.get(id);
      if (cached != null) {
         return cached;
      } else {
         boolean isBlockingFetch = strategy == 0;
         if (isBlockingFetch && timeout == -1) {
            return getFontInternal(context, request, style);
         } else {
            Callable fetcher = new Callable() {
               public Typeface call() throws Exception {
                  Typeface typeface = FontsContractCompat.getFontInternal(context, request, style);
                  if (typeface != null) {
                     FontsContractCompat.sTypefaceCache.put(id, typeface);
                  }

                  return typeface;
               }
            };
            if (isBlockingFetch) {
               try {
                  return (Typeface)sBackgroundThread.postAndWait(fetcher, timeout);
               } catch (InterruptedException var15) {
                  return null;
               }
            } else {
               final WeakReference textViewWeak = new WeakReference(targetView);
               SelfDestructiveThread.ReplyCallback reply = new SelfDestructiveThread.ReplyCallback() {
                  public void onReply(Typeface typeface) {
                     TextView textView = (TextView)textViewWeak.get();
                     if (textView != null) {
                        targetView.setTypeface(typeface, style);
                     }

                  }
               };
               Object var12 = sLock;
               synchronized(sLock) {
                  if (sPendingReplies.containsKey(id)) {
                     ((ArrayList)sPendingReplies.get(id)).add(reply);
                     return null;
                  }

                  ArrayList pendingReplies = new ArrayList();
                  pendingReplies.add(reply);
                  sPendingReplies.put(id, pendingReplies);
               }

               sBackgroundThread.postAndReply(fetcher, new SelfDestructiveThread.ReplyCallback() {
                  public void onReply(Typeface typeface) {
                     ArrayList replies;
                     synchronized(FontsContractCompat.sLock) {
                        replies = (ArrayList)FontsContractCompat.sPendingReplies.get(id);
                        FontsContractCompat.sPendingReplies.remove(id);
                     }

                     for(int i = 0; i < replies.size(); ++i) {
                        ((SelfDestructiveThread.ReplyCallback)replies.get(i)).onReply(typeface);
                     }

                  }
               });
               return null;
            }
         }
      }
   }

   public static void requestFont(@NonNull final Context context, @NonNull final FontRequest request, @NonNull final FontsContractCompat.FontRequestCallback callback, @NonNull Handler handler) {
      final Handler callerThreadHandler = new Handler();
      handler.post(new Runnable() {
         public void run() {
            FontsContractCompat.FontFamilyResult result;
            try {
               result = FontsContractCompat.fetchFonts(context, (CancellationSignal)null, request);
            } catch (NameNotFoundException var8) {
               callerThreadHandler.post(new Runnable() {
                  public void run() {
                     callback.onTypefaceRequestFailed(-1);
                  }
               });
               return;
            }

            if (result.getStatusCode() != 0) {
               switch(result.getStatusCode()) {
               case 1:
                  callerThreadHandler.post(new Runnable() {
                     public void run() {
                        callback.onTypefaceRequestFailed(-2);
                     }
                  });
                  return;
               case 2:
                  callerThreadHandler.post(new Runnable() {
                     public void run() {
                        callback.onTypefaceRequestFailed(-3);
                     }
                  });
                  return;
               default:
                  callerThreadHandler.post(new Runnable() {
                     public void run() {
                        callback.onTypefaceRequestFailed(-3);
                     }
                  });
               }
            } else {
               FontsContractCompat.FontInfo[] fonts = result.getFonts();
               if (fonts != null && fonts.length != 0) {
                  FontsContractCompat.FontInfo[] var3 = fonts;
                  int var4 = fonts.length;

                  for(int var5 = 0; var5 < var4; ++var5) {
                     FontsContractCompat.FontInfo font = var3[var5];
                     if (font.getResultCode() != 0) {
                        final int resultCode = font.getResultCode();
                        if (resultCode < 0) {
                           callerThreadHandler.post(new Runnable() {
                              public void run() {
                                 callback.onTypefaceRequestFailed(-3);
                              }
                           });
                        } else {
                           callerThreadHandler.post(new Runnable() {
                              public void run() {
                                 callback.onTypefaceRequestFailed(resultCode);
                              }
                           });
                        }

                        return;
                     }
                  }

                  final Typeface typeface = FontsContractCompat.buildTypeface(context, (CancellationSignal)null, fonts);
                  if (typeface == null) {
                     callerThreadHandler.post(new Runnable() {
                        public void run() {
                           callback.onTypefaceRequestFailed(-3);
                        }
                     });
                  } else {
                     callerThreadHandler.post(new Runnable() {
                        public void run() {
                           callback.onTypefaceRetrieved(typeface);
                        }
                     });
                  }
               } else {
                  callerThreadHandler.post(new Runnable() {
                     public void run() {
                        callback.onTypefaceRequestFailed(1);
                     }
                  });
               }
            }
         }
      });
   }

   public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fonts) {
      return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fonts, 0);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   @RequiresApi(19)
   public static Map prepareFontData(Context context, FontsContractCompat.FontInfo[] fonts, CancellationSignal cancellationSignal) {
      HashMap out = new HashMap();
      FontsContractCompat.FontInfo[] var4 = fonts;
      int var5 = fonts.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         FontsContractCompat.FontInfo font = var4[var6];
         if (font.getResultCode() == 0) {
            Uri uri = font.getUri();
            if (!out.containsKey(uri)) {
               ByteBuffer buffer = TypefaceCompatUtil.mmap(context, cancellationSignal, uri);
               out.put(uri, buffer);
            }
         }
      }

      return Collections.unmodifiableMap(out);
   }

   @NonNull
   public static FontsContractCompat.FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest request) throws NameNotFoundException {
      ProviderInfo providerInfo = getProvider(context.getPackageManager(), request, context.getResources());
      if (providerInfo == null) {
         return new FontsContractCompat.FontFamilyResult(1, (FontsContractCompat.FontInfo[])null);
      } else {
         FontsContractCompat.FontInfo[] fonts = getFontFromProvider(context, request, providerInfo.authority, cancellationSignal);
         return new FontsContractCompat.FontFamilyResult(0, fonts);
      }
   }

   @VisibleForTesting
   @RestrictTo({Scope.LIBRARY_GROUP})
   @Nullable
   public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest request, @Nullable Resources resources) throws NameNotFoundException {
      String providerAuthority = request.getProviderAuthority();
      ProviderInfo info = packageManager.resolveContentProvider(providerAuthority, 0);
      if (info == null) {
         throw new NameNotFoundException("No package found for authority: " + providerAuthority);
      } else if (!info.packageName.equals(request.getProviderPackage())) {
         throw new NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + request.getProviderPackage());
      } else {
         PackageInfo packageInfo = packageManager.getPackageInfo(info.packageName, 64);
         List signatures = convertToByteArrayList(packageInfo.signatures);
         Collections.sort(signatures, sByteArrayComparator);
         List requestCertificatesList = getCertificates(request, resources);

         for(int i = 0; i < requestCertificatesList.size(); ++i) {
            List requestSignatures = new ArrayList((Collection)requestCertificatesList.get(i));
            Collections.sort(requestSignatures, sByteArrayComparator);
            if (equalsByteArrayList(signatures, requestSignatures)) {
               return info;
            }
         }

         return null;
      }
   }

   private static List getCertificates(FontRequest request, Resources resources) {
      if (request.getCertificates() != null) {
         return request.getCertificates();
      } else {
         int resourceId = request.getCertificatesArrayResId();
         return FontResourcesParserCompat.readCerts(resources, resourceId);
      }
   }

   private static boolean equalsByteArrayList(List signatures, List requestSignatures) {
      if (signatures.size() != requestSignatures.size()) {
         return false;
      } else {
         for(int i = 0; i < signatures.size(); ++i) {
            if (!Arrays.equals((byte[])signatures.get(i), (byte[])requestSignatures.get(i))) {
               return false;
            }
         }

         return true;
      }
   }

   private static List convertToByteArrayList(Signature[] signatures) {
      List shas = new ArrayList();

      for(int i = 0; i < signatures.length; ++i) {
         shas.add(signatures[i].toByteArray());
      }

      return shas;
   }

   @VisibleForTesting
   @NonNull
   static FontsContractCompat.FontInfo[] getFontFromProvider(Context context, FontRequest request, String authority, CancellationSignal cancellationSignal) {
      ArrayList result = new ArrayList();
      Uri uri = (new Builder()).scheme("content").authority(authority).build();
      Uri fileBaseUri = (new Builder()).scheme("content").authority(authority).appendPath("file").build();
      Cursor cursor = null;

      try {
         if (VERSION.SDK_INT > 16) {
            cursor = context.getContentResolver().query(uri, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{request.getQuery()}, (String)null, cancellationSignal);
         } else {
            cursor = context.getContentResolver().query(uri, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{request.getQuery()}, (String)null);
         }

         if (cursor != null && cursor.getCount() > 0) {
            int resultCodeColumnIndex = cursor.getColumnIndex("result_code");
            result = new ArrayList();
            int idColumnIndex = cursor.getColumnIndex("_id");
            int fileIdColumnIndex = cursor.getColumnIndex("file_id");
            int ttcIndexColumnIndex = cursor.getColumnIndex("font_ttc_index");
            int weightColumnIndex = cursor.getColumnIndex("font_weight");
            int italicColumnIndex = cursor.getColumnIndex("font_italic");

            while(cursor.moveToNext()) {
               int resultCode = resultCodeColumnIndex != -1 ? cursor.getInt(resultCodeColumnIndex) : 0;
               int ttcIndex = ttcIndexColumnIndex != -1 ? cursor.getInt(ttcIndexColumnIndex) : 0;
               Uri fileUri;
               long id;
               if (fileIdColumnIndex == -1) {
                  id = cursor.getLong(idColumnIndex);
                  fileUri = ContentUris.withAppendedId(uri, id);
               } else {
                  id = cursor.getLong(fileIdColumnIndex);
                  fileUri = ContentUris.withAppendedId(fileBaseUri, id);
               }

               int weight = weightColumnIndex != -1 ? cursor.getInt(weightColumnIndex) : 400;
               boolean italic = italicColumnIndex != -1 && cursor.getInt(italicColumnIndex) == 1;
               result.add(new FontsContractCompat.FontInfo(fileUri, ttcIndex, weight, italic, resultCode));
            }
         }
      } finally {
         if (cursor != null) {
            cursor.close();
         }

      }

      return (FontsContractCompat.FontInfo[])result.toArray(new FontsContractCompat.FontInfo[0]);
   }

   public static class FontRequestCallback {
      public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
      public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;
      public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
      public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
      public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
      public static final int FAIL_REASON_MALFORMED_QUERY = 3;

      public void onTypefaceRetrieved(Typeface typeface) {
      }

      public void onTypefaceRequestFailed(int reason) {
      }

      @Retention(RetentionPolicy.SOURCE)
      @RestrictTo({Scope.LIBRARY_GROUP})
      @interface FontRequestFailReason {
      }
   }

   public static class FontFamilyResult {
      public static final int STATUS_OK = 0;
      public static final int STATUS_WRONG_CERTIFICATES = 1;
      public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
      private final int mStatusCode;
      private final FontsContractCompat.FontInfo[] mFonts;

      @RestrictTo({Scope.LIBRARY_GROUP})
      public FontFamilyResult(int statusCode, @Nullable FontsContractCompat.FontInfo[] fonts) {
         this.mStatusCode = statusCode;
         this.mFonts = fonts;
      }

      public int getStatusCode() {
         return this.mStatusCode;
      }

      public FontsContractCompat.FontInfo[] getFonts() {
         return this.mFonts;
      }

      @Retention(RetentionPolicy.SOURCE)
      @RestrictTo({Scope.LIBRARY_GROUP})
      @interface FontResultStatus {
      }
   }

   public static class FontInfo {
      private final Uri mUri;
      private final int mTtcIndex;
      private final int mWeight;
      private final boolean mItalic;
      private final int mResultCode;

      @RestrictTo({Scope.LIBRARY_GROUP})
      public FontInfo(@NonNull Uri uri, @IntRange(from = 0L) int ttcIndex, @IntRange(from = 1L,to = 1000L) int weight, boolean italic, int resultCode) {
         this.mUri = (Uri)Preconditions.checkNotNull(uri);
         this.mTtcIndex = ttcIndex;
         this.mWeight = weight;
         this.mItalic = italic;
         this.mResultCode = resultCode;
      }

      @NonNull
      public Uri getUri() {
         return this.mUri;
      }

      @IntRange(
         from = 0L
      )
      public int getTtcIndex() {
         return this.mTtcIndex;
      }

      @IntRange(
         from = 1L,
         to = 1000L
      )
      public int getWeight() {
         return this.mWeight;
      }

      public boolean isItalic() {
         return this.mItalic;
      }

      public int getResultCode() {
         return this.mResultCode;
      }
   }

   public static final class Columns implements BaseColumns {
      public static final String FILE_ID = "file_id";
      public static final String TTC_INDEX = "font_ttc_index";
      public static final String VARIATION_SETTINGS = "font_variation_settings";
      public static final String WEIGHT = "font_weight";
      public static final String ITALIC = "font_italic";
      public static final String RESULT_CODE = "result_code";
      public static final int RESULT_CODE_OK = 0;
      public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
      public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
      public static final int RESULT_CODE_MALFORMED_QUERY = 3;
   }
}
