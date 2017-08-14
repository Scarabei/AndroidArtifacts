package android.support.v4.content;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.v4.util.DebugUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Loader {
   int mId;
   Loader.OnLoadCompleteListener mListener;
   Loader.OnLoadCanceledListener mOnLoadCanceledListener;
   Context mContext;
   boolean mStarted = false;
   boolean mAbandoned = false;
   boolean mReset = true;
   boolean mContentChanged = false;
   boolean mProcessingChange = false;

   public Loader(Context context) {
      this.mContext = context.getApplicationContext();
   }

   public void deliverResult(Object data) {
      if (this.mListener != null) {
         this.mListener.onLoadComplete(this, data);
      }

   }

   public void deliverCancellation() {
      if (this.mOnLoadCanceledListener != null) {
         this.mOnLoadCanceledListener.onLoadCanceled(this);
      }

   }

   public Context getContext() {
      return this.mContext;
   }

   public int getId() {
      return this.mId;
   }

   public void registerListener(int id, Loader.OnLoadCompleteListener listener) {
      if (this.mListener != null) {
         throw new IllegalStateException("There is already a listener registered");
      } else {
         this.mListener = listener;
         this.mId = id;
      }
   }

   public void unregisterListener(Loader.OnLoadCompleteListener listener) {
      if (this.mListener == null) {
         throw new IllegalStateException("No listener register");
      } else if (this.mListener != listener) {
         throw new IllegalArgumentException("Attempting to unregister the wrong listener");
      } else {
         this.mListener = null;
      }
   }

   public void registerOnLoadCanceledListener(Loader.OnLoadCanceledListener listener) {
      if (this.mOnLoadCanceledListener != null) {
         throw new IllegalStateException("There is already a listener registered");
      } else {
         this.mOnLoadCanceledListener = listener;
      }
   }

   public void unregisterOnLoadCanceledListener(Loader.OnLoadCanceledListener listener) {
      if (this.mOnLoadCanceledListener == null) {
         throw new IllegalStateException("No listener register");
      } else if (this.mOnLoadCanceledListener != listener) {
         throw new IllegalArgumentException("Attempting to unregister the wrong listener");
      } else {
         this.mOnLoadCanceledListener = null;
      }
   }

   public boolean isStarted() {
      return this.mStarted;
   }

   public boolean isAbandoned() {
      return this.mAbandoned;
   }

   public boolean isReset() {
      return this.mReset;
   }

   public final void startLoading() {
      this.mStarted = true;
      this.mReset = false;
      this.mAbandoned = false;
      this.onStartLoading();
   }

   protected void onStartLoading() {
   }

   public boolean cancelLoad() {
      return this.onCancelLoad();
   }

   protected boolean onCancelLoad() {
      return false;
   }

   public void forceLoad() {
      this.onForceLoad();
   }

   protected void onForceLoad() {
   }

   public void stopLoading() {
      this.mStarted = false;
      this.onStopLoading();
   }

   protected void onStopLoading() {
   }

   public void abandon() {
      this.mAbandoned = true;
      this.onAbandon();
   }

   protected void onAbandon() {
   }

   public void reset() {
      this.onReset();
      this.mReset = true;
      this.mStarted = false;
      this.mAbandoned = false;
      this.mContentChanged = false;
      this.mProcessingChange = false;
   }

   protected void onReset() {
   }

   public boolean takeContentChanged() {
      boolean res = this.mContentChanged;
      this.mContentChanged = false;
      this.mProcessingChange |= res;
      return res;
   }

   public void commitContentChanged() {
      this.mProcessingChange = false;
   }

   public void rollbackContentChanged() {
      if (this.mProcessingChange) {
         this.onContentChanged();
      }

   }

   public void onContentChanged() {
      if (this.mStarted) {
         this.forceLoad();
      } else {
         this.mContentChanged = true;
      }

   }

   public String dataToString(Object data) {
      StringBuilder sb = new StringBuilder(64);
      DebugUtils.buildShortClassTag(data, sb);
      sb.append("}");
      return sb.toString();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder(64);
      DebugUtils.buildShortClassTag(this, sb);
      sb.append(" id=");
      sb.append(this.mId);
      sb.append("}");
      return sb.toString();
   }

   public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
      writer.print(prefix);
      writer.print("mId=");
      writer.print(this.mId);
      writer.print(" mListener=");
      writer.println(this.mListener);
      if (this.mStarted || this.mContentChanged || this.mProcessingChange) {
         writer.print(prefix);
         writer.print("mStarted=");
         writer.print(this.mStarted);
         writer.print(" mContentChanged=");
         writer.print(this.mContentChanged);
         writer.print(" mProcessingChange=");
         writer.println(this.mProcessingChange);
      }

      if (this.mAbandoned || this.mReset) {
         writer.print(prefix);
         writer.print("mAbandoned=");
         writer.print(this.mAbandoned);
         writer.print(" mReset=");
         writer.println(this.mReset);
      }

   }

   public interface OnLoadCanceledListener {
      void onLoadCanceled(Loader var1);
   }

   public interface OnLoadCompleteListener {
      void onLoadComplete(Loader var1, Object var2);
   }

   public final class ForceLoadContentObserver extends ContentObserver {
      public ForceLoadContentObserver() {
         super(new Handler());
      }

      public boolean deliverSelfNotifications() {
         return true;
      }

      public void onChange(boolean selfChange) {
         Loader.this.onContentChanged();
      }
   }
}
