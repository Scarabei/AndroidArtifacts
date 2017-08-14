package android.support.test.rule.logging;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AtraceLogger {
   private static final String ATRACE_START = "atrace --async_start -b %d -c %s";
   private static final String ATRACE_DUMP = "atrace --async_dump -b %d -z %s";
   private static final String ATRACE_STOP = "atrace --async_stop -b %d -z %s";
   private static final String ATRACEHELPER_TAG = "AtraceLogger";
   private static final String CATEGORY_SEPARATOR = " ";
   private static final int BUFFER_SIZE = 8192;
   private static volatile AtraceLogger mLoggerInstance;
   private UiAutomation mUiAutomation;
   private String mTraceFileName;
   private List mAtraceDataList;
   private Thread mDumpThread;
   private File mDestAtraceDirectory;
   private boolean mAtraceRunning = false;
   private IOException mDumpIOException;

   private AtraceLogger(Instrumentation instrumentation) {
      this.mUiAutomation = instrumentation.getUiAutomation();
   }

   public static AtraceLogger getAtraceLoggerInstance(Instrumentation instrumentation) {
      if (mLoggerInstance == null) {
         Class var1 = AtraceLogger.class;
         synchronized(AtraceLogger.class) {
            if (mLoggerInstance == null) {
               mLoggerInstance = new AtraceLogger(instrumentation);
            }
         }
      }

      return mLoggerInstance;
   }

   public void atraceStart(Set traceCategoriesSet, int atraceBufferSize, int dumpIntervalSecs, File destDirectory, String traceFileName) throws IOException {
      if (this.mAtraceRunning) {
         throw new IllegalStateException("Attempted multiple atrace start");
      } else if (traceCategoriesSet.isEmpty()) {
         throw new IllegalArgumentException("Empty categories. Should contain atleast one category");
      } else if (destDirectory == null) {
         throw new IllegalArgumentException("Destination directory cannot be null");
      } else if (!destDirectory.exists() && !destDirectory.mkdirs()) {
         throw new IOException("Unable to create the destination directory");
      } else {
         this.mDestAtraceDirectory = destDirectory;
         StringBuffer traceCategoriesList = new StringBuffer();
         Iterator i$ = traceCategoriesSet.iterator();

         while(i$.hasNext()) {
            String traceCategory = (String)i$.next();
            traceCategoriesList.append(traceCategory).append(" ");
         }

         if (traceFileName != null && !traceFileName.isEmpty()) {
            this.mTraceFileName = traceFileName;
         }

         String startCommand = String.format("atrace --async_start -b %d -c %s", atraceBufferSize, traceCategoriesList.toString());
         ByteArrayOutputStream outStream = new ByteArrayOutputStream();

         try {
            this.writeDataToByteStream(this.mUiAutomation.executeShellCommand(startCommand), outStream);
         } finally {
            outStream.close();
         }

         this.mAtraceRunning = true;
         this.mDumpIOException = null;
         this.mAtraceDataList = new ArrayList();
         this.mDumpThread = new Thread(new AtraceLogger.DumpTraceRunnable(traceCategoriesList.toString(), atraceBufferSize, dumpIntervalSecs));
         this.mDumpThread.start();
      }
   }

   private void writeDataToByteStream(ParcelFileDescriptor pfDescriptor, ByteArrayOutputStream outputStream) throws IOException {
      AutoCloseInputStream inputStream = new AutoCloseInputStream(pfDescriptor);

      try {
         byte[] buffer = new byte[8192];

         int length;
         while((length = inputStream.read(buffer)) >= 0) {
            outputStream.write(buffer, 0, length);
         }
      } finally {
         inputStream.close();
      }

   }

   public void atraceStop() throws IOException, InterruptedException {
      if (!this.mAtraceRunning) {
         throw new IllegalStateException("ATrace is not running currently. Start atrace beforestopping.");
      } else {
         boolean var7 = false;

         try {
            var7 = true;
            this.mDumpThread.interrupt();
            this.mDumpThread.join();
            if (this.mDumpIOException != null) {
               throw this.mDumpIOException;
            }

            this.atraceWrite();
            var7 = false;
         } finally {
            if (var7) {
               Iterator i$ = this.mAtraceDataList.iterator();

               while(i$.hasNext()) {
                  ByteArrayOutputStream outStream = (ByteArrayOutputStream)i$.next();
                  outStream.close();
               }

               this.mAtraceRunning = false;
               this.mTraceFileName = null;
            }
         }

         Iterator i$ = this.mAtraceDataList.iterator();

         while(i$.hasNext()) {
            ByteArrayOutputStream outStream = (ByteArrayOutputStream)i$.next();
            outStream.close();
         }

         this.mAtraceRunning = false;
         this.mTraceFileName = null;
      }
   }

   private void atraceWrite() throws IOException {
      int count = 0;

      for(Iterator i$ = this.mAtraceDataList.iterator(); i$.hasNext(); ++count) {
         ByteArrayOutputStream outStream = (ByteArrayOutputStream)i$.next();
         File file = null;
         if (this.mTraceFileName != null) {
            file = new File(this.mDestAtraceDirectory, String.format("%s-atrace-%d.txt", this.mTraceFileName, count));
         } else {
            file = new File(this.mDestAtraceDirectory, String.format("atrace-%d.txt", count));
         }

         FileOutputStream fileOutputStream = new FileOutputStream(file);

         try {
            fileOutputStream.write(outStream.toByteArray());
         } finally {
            fileOutputStream.close();
         }
      }

   }

   private class DumpTraceRunnable implements Runnable {
      private String mTraceCategories;
      private int mBufferSize;
      private int mDumpIntervalInSecs;

      DumpTraceRunnable(String traceCategories, int bufferSize, int dumpIntervalInSecs) {
         this.mTraceCategories = traceCategories;
         this.mBufferSize = bufferSize;
         this.mDumpIntervalInSecs = dumpIntervalInSecs;
      }

      public void run() {
         while(true) {
            try {
               String dumpCommand;
               if (!Thread.currentThread().isInterrupted()) {
                  label18: {
                     try {
                        Thread.sleep((long)(this.mDumpIntervalInSecs * 1000));
                     } catch (InterruptedException var7) {
                        break label18;
                     }

                     dumpCommand = String.format("atrace --async_dump -b %d -z %s", this.mBufferSize, this.mTraceCategories);
                     long startTime = System.currentTimeMillis();
                     ByteArrayOutputStream byteArrayOutStreamx = new ByteArrayOutputStream();
                     AtraceLogger.this.writeDataToByteStream(AtraceLogger.this.mUiAutomation.executeShellCommand(dumpCommand), byteArrayOutStreamx);
                     AtraceLogger.this.mAtraceDataList.add(byteArrayOutStreamx);
                     long endTime = System.currentTimeMillis();
                     Log.i("AtraceLogger", "Time taken by - DumpTraceRunnable " + (endTime - startTime));
                     continue;
                  }
               }

               dumpCommand = String.format("atrace --async_stop -b %d -z %s", this.mBufferSize, this.mTraceCategories);
               ByteArrayOutputStream byteArrayOutStream = new ByteArrayOutputStream();
               AtraceLogger.this.writeDataToByteStream(AtraceLogger.this.mUiAutomation.executeShellCommand(dumpCommand), byteArrayOutStream);
               AtraceLogger.this.mAtraceDataList.add(byteArrayOutStream);
            } catch (IOException var8) {
               AtraceLogger.this.mDumpIOException = var8;
            }

            return;
         }
      }
   }
}
