package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.pdf.PdfDocument.Page;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Build.VERSION;
import android.os.CancellationSignal.OnCancelListener;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PrintHelper {
   public static final int SCALE_MODE_FIT = 1;
   public static final int SCALE_MODE_FILL = 2;
   public static final int COLOR_MODE_MONOCHROME = 1;
   public static final int COLOR_MODE_COLOR = 2;
   public static final int ORIENTATION_LANDSCAPE = 1;
   public static final int ORIENTATION_PORTRAIT = 2;
   private final PrintHelper.PrintHelperVersionImpl mImpl;

   public static boolean systemSupportsPrint() {
      return VERSION.SDK_INT >= 19;
   }

   public PrintHelper(Context context) {
      if (VERSION.SDK_INT >= 24) {
         this.mImpl = new PrintHelper.PrintHelperApi24(context);
      } else if (VERSION.SDK_INT >= 23) {
         this.mImpl = new PrintHelper.PrintHelperApi23(context);
      } else if (VERSION.SDK_INT >= 20) {
         this.mImpl = new PrintHelper.PrintHelperApi20(context);
      } else if (VERSION.SDK_INT >= 19) {
         this.mImpl = new PrintHelper.PrintHelperApi19(context);
      } else {
         this.mImpl = new PrintHelper.PrintHelperStub();
      }

   }

   public void setScaleMode(int scaleMode) {
      this.mImpl.setScaleMode(scaleMode);
   }

   public int getScaleMode() {
      return this.mImpl.getScaleMode();
   }

   public void setColorMode(int colorMode) {
      this.mImpl.setColorMode(colorMode);
   }

   public int getColorMode() {
      return this.mImpl.getColorMode();
   }

   public void setOrientation(int orientation) {
      this.mImpl.setOrientation(orientation);
   }

   public int getOrientation() {
      return this.mImpl.getOrientation();
   }

   public void printBitmap(String jobName, Bitmap bitmap) {
      this.mImpl.printBitmap(jobName, (Bitmap)bitmap, (PrintHelper.OnPrintFinishCallback)null);
   }

   public void printBitmap(String jobName, Bitmap bitmap, PrintHelper.OnPrintFinishCallback callback) {
      this.mImpl.printBitmap(jobName, bitmap, callback);
   }

   public void printBitmap(String jobName, Uri imageFile) throws FileNotFoundException {
      this.mImpl.printBitmap(jobName, (Uri)imageFile, (PrintHelper.OnPrintFinishCallback)null);
   }

   public void printBitmap(String jobName, Uri imageFile, PrintHelper.OnPrintFinishCallback callback) throws FileNotFoundException {
      this.mImpl.printBitmap(jobName, imageFile, callback);
   }

   @RequiresApi(24)
   private static class PrintHelperApi24 extends PrintHelper.PrintHelperApi23 {
      PrintHelperApi24(Context context) {
         super(context);
         this.mIsMinMarginsHandlingCorrect = true;
         this.mPrintActivityRespectsOrientation = true;
      }
   }

   @RequiresApi(23)
   private static class PrintHelperApi23 extends PrintHelper.PrintHelperApi20 {
      protected Builder copyAttributes(PrintAttributes other) {
         Builder b = super.copyAttributes(other);
         if (other.getDuplexMode() != 0) {
            b.setDuplexMode(other.getDuplexMode());
         }

         return b;
      }

      PrintHelperApi23(Context context) {
         super(context);
         this.mIsMinMarginsHandlingCorrect = false;
      }
   }

   @RequiresApi(20)
   private static class PrintHelperApi20 extends PrintHelper.PrintHelperApi19 {
      PrintHelperApi20(Context context) {
         super(context);
         this.mPrintActivityRespectsOrientation = false;
      }
   }

   @RequiresApi(19)
   private static class PrintHelperApi19 implements PrintHelper.PrintHelperVersionImpl {
      private static final String LOG_TAG = "PrintHelperApi19";
      private static final int MAX_PRINT_SIZE = 3500;
      final Context mContext;
      Options mDecodeOptions = null;
      private final Object mLock = new Object();
      protected boolean mPrintActivityRespectsOrientation = true;
      protected boolean mIsMinMarginsHandlingCorrect = true;
      int mScaleMode = 2;
      int mColorMode = 2;
      int mOrientation;

      PrintHelperApi19(Context context) {
         this.mContext = context;
      }

      public void setScaleMode(int scaleMode) {
         this.mScaleMode = scaleMode;
      }

      public int getScaleMode() {
         return this.mScaleMode;
      }

      public void setColorMode(int colorMode) {
         this.mColorMode = colorMode;
      }

      public void setOrientation(int orientation) {
         this.mOrientation = orientation;
      }

      public int getOrientation() {
         return this.mOrientation == 0 ? 1 : this.mOrientation;
      }

      public int getColorMode() {
         return this.mColorMode;
      }

      private static boolean isPortrait(Bitmap bitmap) {
         return bitmap.getWidth() <= bitmap.getHeight();
      }

      protected Builder copyAttributes(PrintAttributes other) {
         Builder b = (new Builder()).setMediaSize(other.getMediaSize()).setResolution(other.getResolution()).setMinMargins(other.getMinMargins());
         if (other.getColorMode() != 0) {
            b.setColorMode(other.getColorMode());
         }

         return b;
      }

      public void printBitmap(final String jobName, final Bitmap bitmap, final PrintHelper.OnPrintFinishCallback callback) {
         if (bitmap != null) {
            final int fittingMode = this.mScaleMode;
            PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
            MediaSize mediaSize;
            if (isPortrait(bitmap)) {
               mediaSize = MediaSize.UNKNOWN_PORTRAIT;
            } else {
               mediaSize = MediaSize.UNKNOWN_LANDSCAPE;
            }

            PrintAttributes attr = (new Builder()).setMediaSize(mediaSize).setColorMode(this.mColorMode).build();
            printManager.print(jobName, new PrintDocumentAdapter() {
               private PrintAttributes mAttributes;

               public void onLayout(PrintAttributes oldPrintAttributes, PrintAttributes newPrintAttributes, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
                  this.mAttributes = newPrintAttributes;
                  PrintDocumentInfo info = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build();
                  boolean changed = !newPrintAttributes.equals(oldPrintAttributes);
                  layoutResultCallback.onLayoutFinished(info, changed);
               }

               public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
                  PrintHelperApi19.this.writeBitmap(this.mAttributes, fittingMode, bitmap, fileDescriptor, cancellationSignal, writeResultCallback);
               }

               public void onFinish() {
                  if (callback != null) {
                     callback.onFinish();
                  }

               }
            }, attr);
         }
      }

      private Matrix getMatrix(int imageWidth, int imageHeight, RectF content, int fittingMode) {
         Matrix matrix = new Matrix();
         float scale = content.width() / (float)imageWidth;
         if (fittingMode == 2) {
            scale = Math.max(scale, content.height() / (float)imageHeight);
         } else {
            scale = Math.min(scale, content.height() / (float)imageHeight);
         }

         matrix.postScale(scale, scale);
         float translateX = (content.width() - (float)imageWidth * scale) / 2.0F;
         float translateY = (content.height() - (float)imageHeight * scale) / 2.0F;
         matrix.postTranslate(translateX, translateY);
         return matrix;
      }

      private void writeBitmap(final PrintAttributes attributes, final int fittingMode, final Bitmap bitmap, final ParcelFileDescriptor fileDescriptor, final CancellationSignal cancellationSignal, final WriteResultCallback writeResultCallback) {
         final PrintAttributes pdfAttributes;
         if (this.mIsMinMarginsHandlingCorrect) {
            pdfAttributes = attributes;
         } else {
            pdfAttributes = this.copyAttributes(attributes).setMinMargins(new Margins(0, 0, 0, 0)).build();
         }

         (new AsyncTask() {
            protected Throwable doInBackground(Void... params) {
               try {
                  if (cancellationSignal.isCanceled()) {
                     return null;
                  } else {
                     PrintedPdfDocument pdfDocument = new PrintedPdfDocument(PrintHelperApi19.this.mContext, pdfAttributes);
                     Bitmap maybeGrayscale = PrintHelperApi19.this.convertBitmapForColorMode(bitmap, pdfAttributes.getColorMode());
                     if (cancellationSignal.isCanceled()) {
                        return null;
                     } else {
                        Page dummyPage;
                        try {
                           Page page = pdfDocument.startPage(1);
                           RectF contentRect;
                           if (PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                              contentRect = new RectF(page.getInfo().getContentRect());
                           } else {
                              PrintedPdfDocument dummyDocument = new PrintedPdfDocument(PrintHelperApi19.this.mContext, attributes);
                              dummyPage = dummyDocument.startPage(1);
                              contentRect = new RectF(dummyPage.getInfo().getContentRect());
                              dummyDocument.finishPage(dummyPage);
                              dummyDocument.close();
                           }

                           Matrix matrix = PrintHelperApi19.this.getMatrix(maybeGrayscale.getWidth(), maybeGrayscale.getHeight(), contentRect, fittingMode);
                           if (!PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                              matrix.postTranslate(contentRect.left, contentRect.top);
                              page.getCanvas().clipRect(contentRect);
                           }

                           page.getCanvas().drawBitmap(maybeGrayscale, matrix, (Paint)null);
                           pdfDocument.finishPage(page);
                           if (cancellationSignal.isCanceled()) {
                              dummyPage = null;
                              return dummyPage;
                           }

                           pdfDocument.writeTo(new FileOutputStream(fileDescriptor.getFileDescriptor()));
                           dummyPage = null;
                        } finally {
                           pdfDocument.close();
                           if (fileDescriptor != null) {
                              try {
                                 fileDescriptor.close();
                              } catch (IOException var16) {
                                 ;
                              }
                           }

                           if (maybeGrayscale != bitmap) {
                              maybeGrayscale.recycle();
                           }

                        }

                        return dummyPage;
                     }
                  }
               } catch (Throwable var18) {
                  return var18;
               }
            }

            protected void onPostExecute(Throwable throwable) {
               if (cancellationSignal.isCanceled()) {
                  writeResultCallback.onWriteCancelled();
               } else if (throwable == null) {
                  writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
               } else {
                  Log.e("PrintHelperApi19", "Error writing printed content", throwable);
                  writeResultCallback.onWriteFailed((CharSequence)null);
               }

            }
         }).execute(new Void[0]);
      }

      public void printBitmap(final String jobName, final Uri imageFile, final PrintHelper.OnPrintFinishCallback callback) throws FileNotFoundException {
         final int fittingMode = this.mScaleMode;
         PrintDocumentAdapter printDocumentAdapter = new PrintDocumentAdapter() {
            private PrintAttributes mAttributes;
            AsyncTask mLoadBitmap;
            Bitmap mBitmap = null;

            public void onLayout(final PrintAttributes oldPrintAttributes, final PrintAttributes newPrintAttributes, final CancellationSignal cancellationSignal, final LayoutResultCallback layoutResultCallback, Bundle bundle) {
               synchronized(this) {
                  this.mAttributes = newPrintAttributes;
               }

               if (cancellationSignal.isCanceled()) {
                  layoutResultCallback.onLayoutCancelled();
               } else if (this.mBitmap != null) {
                  PrintDocumentInfo info = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build();
                  boolean changed = !newPrintAttributes.equals(oldPrintAttributes);
                  layoutResultCallback.onLayoutFinished(info, changed);
               } else {
                  this.mLoadBitmap = (new AsyncTask() {
                     protected void onPreExecute() {
                        cancellationSignal.setOnCancelListener(new OnCancelListener() {
                           public void onCancel() {
                              cancelLoad();
                              cancel(false);
                           }
                        });
                     }

                     protected Bitmap doInBackground(Uri... uris) {
                        try {
                           return PrintHelperApi19.this.loadConstrainedBitmap(imageFile);
                        } catch (FileNotFoundException var3) {
                           return null;
                        }
                     }

                     protected void onPostExecute(Bitmap bitmap) {
                        super.onPostExecute(bitmap);
                        if (bitmap != null && (!PrintHelperApi19.this.mPrintActivityRespectsOrientation || PrintHelperApi19.this.mOrientation == 0)) {
                           MediaSize mediaSize;
                           synchronized(this) {
                              mediaSize = mAttributes.getMediaSize();
                           }

                           if (mediaSize != null && mediaSize.isPortrait() != PrintHelper.PrintHelperApi19.isPortrait(bitmap)) {
                              Matrix rotation = new Matrix();
                              rotation.postRotate(90.0F);
                              bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), rotation, true);
                           }
                        }

                        mBitmap = bitmap;
                        if (bitmap != null) {
                           PrintDocumentInfo info = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build();
                           boolean changed = !newPrintAttributes.equals(oldPrintAttributes);
                           layoutResultCallback.onLayoutFinished(info, changed);
                        } else {
                           layoutResultCallback.onLayoutFailed((CharSequence)null);
                        }

                        mLoadBitmap = null;
                     }

                     protected void onCancelled(Bitmap result) {
                        layoutResultCallback.onLayoutCancelled();
                        mLoadBitmap = null;
                     }
                  }).execute(new Uri[0]);
               }
            }

            private void cancelLoad() {
               synchronized(PrintHelperApi19.this.mLock) {
                  if (PrintHelperApi19.this.mDecodeOptions != null) {
                     PrintHelperApi19.this.mDecodeOptions.requestCancelDecode();
                     PrintHelperApi19.this.mDecodeOptions = null;
                  }

               }
            }

            public void onFinish() {
               super.onFinish();
               this.cancelLoad();
               if (this.mLoadBitmap != null) {
                  this.mLoadBitmap.cancel(true);
               }

               if (callback != null) {
                  callback.onFinish();
               }

               if (this.mBitmap != null) {
                  this.mBitmap.recycle();
                  this.mBitmap = null;
               }

            }

            public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor fileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
               PrintHelperApi19.this.writeBitmap(this.mAttributes, fittingMode, this.mBitmap, fileDescriptor, cancellationSignal, writeResultCallback);
            }
         };
         PrintManager printManager = (PrintManager)this.mContext.getSystemService("print");
         Builder builder = new Builder();
         builder.setColorMode(this.mColorMode);
         if (this.mOrientation != 1 && this.mOrientation != 0) {
            if (this.mOrientation == 2) {
               builder.setMediaSize(MediaSize.UNKNOWN_PORTRAIT);
            }
         } else {
            builder.setMediaSize(MediaSize.UNKNOWN_LANDSCAPE);
         }

         PrintAttributes attr = builder.build();
         printManager.print(jobName, printDocumentAdapter, attr);
      }

      private Bitmap loadConstrainedBitmap(Uri uri) throws FileNotFoundException {
         if (uri != null && this.mContext != null) {
            Options opt = new Options();
            opt.inJustDecodeBounds = true;
            this.loadBitmap(uri, opt);
            int w = opt.outWidth;
            int h = opt.outHeight;
            if (w > 0 && h > 0) {
               int imageSide = Math.max(w, h);

               int sampleSize;
               for(sampleSize = 1; imageSide > 3500; sampleSize <<= 1) {
                  imageSide >>>= 1;
               }

               if (sampleSize > 0 && 0 < Math.min(w, h) / sampleSize) {
                  Object var8 = this.mLock;
                  Options decodeOptions;
                  synchronized(this.mLock) {
                     this.mDecodeOptions = new Options();
                     this.mDecodeOptions.inMutable = true;
                     this.mDecodeOptions.inSampleSize = sampleSize;
                     decodeOptions = this.mDecodeOptions;
                  }

                  boolean var18 = false;

                  Bitmap var23;
                  try {
                     var18 = true;
                     var23 = this.loadBitmap(uri, decodeOptions);
                     var18 = false;
                  } finally {
                     if (var18) {
                        Object var12 = this.mLock;
                        synchronized(this.mLock) {
                           this.mDecodeOptions = null;
                        }
                     }
                  }

                  Object var9 = this.mLock;
                  synchronized(this.mLock) {
                     this.mDecodeOptions = null;
                     return var23;
                  }
               } else {
                  return null;
               }
            } else {
               return null;
            }
         } else {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
         }
      }

      private Bitmap loadBitmap(Uri uri, Options o) throws FileNotFoundException {
         if (uri != null && this.mContext != null) {
            InputStream is = null;

            Bitmap var4;
            try {
               is = this.mContext.getContentResolver().openInputStream(uri);
               var4 = BitmapFactory.decodeStream(is, (Rect)null, o);
            } finally {
               if (is != null) {
                  try {
                     is.close();
                  } catch (IOException var11) {
                     Log.w("PrintHelperApi19", "close fail ", var11);
                  }
               }

            }

            return var4;
         } else {
            throw new IllegalArgumentException("bad argument to loadBitmap");
         }
      }

      private Bitmap convertBitmapForColorMode(Bitmap original, int colorMode) {
         if (colorMode != 1) {
            return original;
         } else {
            Bitmap grayscale = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Config.ARGB_8888);
            Canvas c = new Canvas(grayscale);
            Paint p = new Paint();
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0.0F);
            ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
            p.setColorFilter(f);
            c.drawBitmap(original, 0.0F, 0.0F, p);
            c.setBitmap((Bitmap)null);
            return grayscale;
         }
      }
   }

   private static final class PrintHelperStub implements PrintHelper.PrintHelperVersionImpl {
      int mScaleMode;
      int mColorMode;
      int mOrientation;

      private PrintHelperStub() {
         this.mScaleMode = 2;
         this.mColorMode = 2;
         this.mOrientation = 1;
      }

      public void setScaleMode(int scaleMode) {
         this.mScaleMode = scaleMode;
      }

      public int getScaleMode() {
         return this.mScaleMode;
      }

      public int getColorMode() {
         return this.mColorMode;
      }

      public void setColorMode(int colorMode) {
         this.mColorMode = colorMode;
      }

      public void setOrientation(int orientation) {
         this.mOrientation = orientation;
      }

      public int getOrientation() {
         return this.mOrientation;
      }

      public void printBitmap(String jobName, Bitmap bitmap, PrintHelper.OnPrintFinishCallback callback) {
      }

      public void printBitmap(String jobName, Uri imageFile, PrintHelper.OnPrintFinishCallback callback) {
      }

      // $FF: synthetic method
      PrintHelperStub(Object x0) {
         this();
      }
   }

   interface PrintHelperVersionImpl {
      void setScaleMode(int var1);

      int getScaleMode();

      void setColorMode(int var1);

      int getColorMode();

      void setOrientation(int var1);

      int getOrientation();

      void printBitmap(String var1, Bitmap var2, PrintHelper.OnPrintFinishCallback var3);

      void printBitmap(String var1, Uri var2, PrintHelper.OnPrintFinishCallback var3) throws FileNotFoundException;
   }

   @Retention(RetentionPolicy.SOURCE)
   private @interface Orientation {
   }

   @Retention(RetentionPolicy.SOURCE)
   private @interface ColorMode {
   }

   @Retention(RetentionPolicy.SOURCE)
   private @interface ScaleMode {
   }

   public interface OnPrintFinishCallback {
      void onFinish();
   }
}
