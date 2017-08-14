package android.support.v4.app;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v4.content.Loader.OnLoadCanceledListener;
import android.support.v4.content.Loader.OnLoadCompleteListener;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl extends LoaderManager {
   static final String TAG = "LoaderManager";
   static boolean DEBUG = false;
   final SparseArrayCompat mLoaders = new SparseArrayCompat();
   final SparseArrayCompat mInactiveLoaders = new SparseArrayCompat();
   final String mWho;
   boolean mStarted;
   boolean mRetaining;
   boolean mRetainingStarted;
   boolean mCreatingLoader;
   FragmentHostCallback mHost;

   LoaderManagerImpl(String who, FragmentHostCallback host, boolean started) {
      this.mWho = who;
      this.mHost = host;
      this.mStarted = started;
   }

   void updateHostController(FragmentHostCallback host) {
      this.mHost = host;
   }

   private LoaderManagerImpl.LoaderInfo createLoader(int id, Bundle args, LoaderManager.LoaderCallbacks callback) {
      LoaderManagerImpl.LoaderInfo info = new LoaderManagerImpl.LoaderInfo(id, args, callback);
      Loader loader = callback.onCreateLoader(id, args);
      info.mLoader = loader;
      return info;
   }

   private LoaderManagerImpl.LoaderInfo createAndInstallLoader(int id, Bundle args, LoaderManager.LoaderCallbacks callback) {
      LoaderManagerImpl.LoaderInfo var5;
      try {
         this.mCreatingLoader = true;
         LoaderManagerImpl.LoaderInfo info = this.createLoader(id, args, callback);
         this.installLoader(info);
         var5 = info;
      } finally {
         this.mCreatingLoader = false;
      }

      return var5;
   }

   void installLoader(LoaderManagerImpl.LoaderInfo info) {
      this.mLoaders.put(info.mId, info);
      if (this.mStarted) {
         info.start();
      }

   }

   public Loader initLoader(int id, Bundle args, LoaderManager.LoaderCallbacks callback) {
      if (this.mCreatingLoader) {
         throw new IllegalStateException("Called while creating a loader");
      } else {
         LoaderManagerImpl.LoaderInfo info = (LoaderManagerImpl.LoaderInfo)this.mLoaders.get(id);
         if (DEBUG) {
            Log.v("LoaderManager", "initLoader in " + this + ": args=" + args);
         }

         if (info == null) {
            info = this.createAndInstallLoader(id, args, callback);
            if (DEBUG) {
               Log.v("LoaderManager", "  Created new loader " + info);
            }
         } else {
            if (DEBUG) {
               Log.v("LoaderManager", "  Re-using existing loader " + info);
            }

            info.mCallbacks = callback;
         }

         if (info.mHaveData && this.mStarted) {
            info.callOnLoadFinished(info.mLoader, info.mData);
         }

         return info.mLoader;
      }
   }

   public Loader restartLoader(int id, Bundle args, LoaderManager.LoaderCallbacks callback) {
      if (this.mCreatingLoader) {
         throw new IllegalStateException("Called while creating a loader");
      } else {
         LoaderManagerImpl.LoaderInfo info = (LoaderManagerImpl.LoaderInfo)this.mLoaders.get(id);
         if (DEBUG) {
            Log.v("LoaderManager", "restartLoader in " + this + ": args=" + args);
         }

         if (info != null) {
            LoaderManagerImpl.LoaderInfo inactive = (LoaderManagerImpl.LoaderInfo)this.mInactiveLoaders.get(id);
            if (inactive != null) {
               if (info.mHaveData) {
                  if (DEBUG) {
                     Log.v("LoaderManager", "  Removing last inactive loader: " + info);
                  }

                  inactive.mDeliveredData = false;
                  inactive.destroy();
                  info.mLoader.abandon();
                  this.mInactiveLoaders.put(id, info);
               } else {
                  if (info.cancel()) {
                     if (DEBUG) {
                        Log.v("LoaderManager", "  Current loader is running; configuring pending loader");
                     }

                     if (info.mPendingLoader != null) {
                        if (DEBUG) {
                           Log.v("LoaderManager", "  Removing pending loader: " + info.mPendingLoader);
                        }

                        info.mPendingLoader.destroy();
                        info.mPendingLoader = null;
                     }

                     if (DEBUG) {
                        Log.v("LoaderManager", "  Enqueuing as new pending loader");
                     }

                     info.mPendingLoader = this.createLoader(id, args, callback);
                     return info.mPendingLoader.mLoader;
                  }

                  if (DEBUG) {
                     Log.v("LoaderManager", "  Current loader is stopped; replacing");
                  }

                  this.mLoaders.put(id, (Object)null);
                  info.destroy();
               }
            } else {
               if (DEBUG) {
                  Log.v("LoaderManager", "  Making last loader inactive: " + info);
               }

               info.mLoader.abandon();
               this.mInactiveLoaders.put(id, info);
            }
         }

         info = this.createAndInstallLoader(id, args, callback);
         return info.mLoader;
      }
   }

   public void destroyLoader(int id) {
      if (this.mCreatingLoader) {
         throw new IllegalStateException("Called while creating a loader");
      } else {
         if (DEBUG) {
            Log.v("LoaderManager", "destroyLoader in " + this + " of " + id);
         }

         int idx = this.mLoaders.indexOfKey(id);
         LoaderManagerImpl.LoaderInfo info;
         if (idx >= 0) {
            info = (LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(idx);
            this.mLoaders.removeAt(idx);
            info.destroy();
         }

         idx = this.mInactiveLoaders.indexOfKey(id);
         if (idx >= 0) {
            info = (LoaderManagerImpl.LoaderInfo)this.mInactiveLoaders.valueAt(idx);
            this.mInactiveLoaders.removeAt(idx);
            info.destroy();
         }

         if (this.mHost != null && !this.hasRunningLoaders()) {
            this.mHost.mFragmentManager.startPendingDeferredFragments();
         }

      }
   }

   public Loader getLoader(int id) {
      if (this.mCreatingLoader) {
         throw new IllegalStateException("Called while creating a loader");
      } else {
         LoaderManagerImpl.LoaderInfo loaderInfo = (LoaderManagerImpl.LoaderInfo)this.mLoaders.get(id);
         if (loaderInfo != null) {
            return loaderInfo.mPendingLoader != null ? loaderInfo.mPendingLoader.mLoader : loaderInfo.mLoader;
         } else {
            return null;
         }
      }
   }

   void doStart() {
      if (DEBUG) {
         Log.v("LoaderManager", "Starting in " + this);
      }

      if (this.mStarted) {
         RuntimeException e = new RuntimeException("here");
         e.fillInStackTrace();
         Log.w("LoaderManager", "Called doStart when already started: " + this, e);
      } else {
         this.mStarted = true;

         for(int i = this.mLoaders.size() - 1; i >= 0; --i) {
            ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).start();
         }

      }
   }

   void doStop() {
      if (DEBUG) {
         Log.v("LoaderManager", "Stopping in " + this);
      }

      if (!this.mStarted) {
         RuntimeException e = new RuntimeException("here");
         e.fillInStackTrace();
         Log.w("LoaderManager", "Called doStop when not started: " + this, e);
      } else {
         for(int i = this.mLoaders.size() - 1; i >= 0; --i) {
            ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).stop();
         }

         this.mStarted = false;
      }
   }

   void doRetain() {
      if (DEBUG) {
         Log.v("LoaderManager", "Retaining in " + this);
      }

      if (!this.mStarted) {
         RuntimeException e = new RuntimeException("here");
         e.fillInStackTrace();
         Log.w("LoaderManager", "Called doRetain when not started: " + this, e);
      } else {
         this.mRetaining = true;
         this.mStarted = false;

         for(int i = this.mLoaders.size() - 1; i >= 0; --i) {
            ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).retain();
         }

      }
   }

   void finishRetain() {
      if (this.mRetaining) {
         if (DEBUG) {
            Log.v("LoaderManager", "Finished Retaining in " + this);
         }

         this.mRetaining = false;

         for(int i = this.mLoaders.size() - 1; i >= 0; --i) {
            ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).finishRetain();
         }
      }

   }

   void doReportNextStart() {
      for(int i = this.mLoaders.size() - 1; i >= 0; --i) {
         ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).mReportNextStart = true;
      }

   }

   void doReportStart() {
      for(int i = this.mLoaders.size() - 1; i >= 0; --i) {
         ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).reportStart();
      }

   }

   void doDestroy() {
      int i;
      if (!this.mRetaining) {
         if (DEBUG) {
            Log.v("LoaderManager", "Destroying Active in " + this);
         }

         for(i = this.mLoaders.size() - 1; i >= 0; --i) {
            ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).destroy();
         }

         this.mLoaders.clear();
      }

      if (DEBUG) {
         Log.v("LoaderManager", "Destroying Inactive in " + this);
      }

      for(i = this.mInactiveLoaders.size() - 1; i >= 0; --i) {
         ((LoaderManagerImpl.LoaderInfo)this.mInactiveLoaders.valueAt(i)).destroy();
      }

      this.mInactiveLoaders.clear();
      this.mHost = null;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder(128);
      sb.append("LoaderManager{");
      sb.append(Integer.toHexString(System.identityHashCode(this)));
      sb.append(" in ");
      DebugUtils.buildShortClassTag(this.mHost, sb);
      sb.append("}}");
      return sb.toString();
   }

   public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
      String innerPrefix;
      int i;
      LoaderManagerImpl.LoaderInfo li;
      if (this.mLoaders.size() > 0) {
         writer.print(prefix);
         writer.println("Active Loaders:");
         innerPrefix = prefix + "    ";

         for(i = 0; i < this.mLoaders.size(); ++i) {
            li = (LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i);
            writer.print(prefix);
            writer.print("  #");
            writer.print(this.mLoaders.keyAt(i));
            writer.print(": ");
            writer.println(li.toString());
            li.dump(innerPrefix, fd, writer, args);
         }
      }

      if (this.mInactiveLoaders.size() > 0) {
         writer.print(prefix);
         writer.println("Inactive Loaders:");
         innerPrefix = prefix + "    ";

         for(i = 0; i < this.mInactiveLoaders.size(); ++i) {
            li = (LoaderManagerImpl.LoaderInfo)this.mInactiveLoaders.valueAt(i);
            writer.print(prefix);
            writer.print("  #");
            writer.print(this.mInactiveLoaders.keyAt(i));
            writer.print(": ");
            writer.println(li.toString());
            li.dump(innerPrefix, fd, writer, args);
         }
      }

   }

   public boolean hasRunningLoaders() {
      boolean loadersRunning = false;
      int count = this.mLoaders.size();

      for(int i = 0; i < count; ++i) {
         LoaderManagerImpl.LoaderInfo li = (LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i);
         loadersRunning |= li.mStarted && !li.mDeliveredData;
      }

      return loadersRunning;
   }

   final class LoaderInfo implements OnLoadCompleteListener, OnLoadCanceledListener {
      final int mId;
      final Bundle mArgs;
      LoaderManager.LoaderCallbacks mCallbacks;
      Loader mLoader;
      boolean mHaveData;
      boolean mDeliveredData;
      Object mData;
      boolean mStarted;
      boolean mRetaining;
      boolean mRetainingStarted;
      boolean mReportNextStart;
      boolean mDestroyed;
      boolean mListenerRegistered;
      LoaderManagerImpl.LoaderInfo mPendingLoader;

      public LoaderInfo(int id, Bundle args, LoaderManager.LoaderCallbacks callbacks) {
         this.mId = id;
         this.mArgs = args;
         this.mCallbacks = callbacks;
      }

      void start() {
         if (this.mRetaining && this.mRetainingStarted) {
            this.mStarted = true;
         } else if (!this.mStarted) {
            this.mStarted = true;
            if (LoaderManagerImpl.DEBUG) {
               Log.v("LoaderManager", "  Starting: " + this);
            }

            if (this.mLoader == null && this.mCallbacks != null) {
               this.mLoader = this.mCallbacks.onCreateLoader(this.mId, this.mArgs);
            }

            if (this.mLoader != null) {
               if (this.mLoader.getClass().isMemberClass() && !Modifier.isStatic(this.mLoader.getClass().getModifiers())) {
                  throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + this.mLoader);
               }

               if (!this.mListenerRegistered) {
                  this.mLoader.registerListener(this.mId, this);
                  this.mLoader.registerOnLoadCanceledListener(this);
                  this.mListenerRegistered = true;
               }

               this.mLoader.startLoading();
            }

         }
      }

      void retain() {
         if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Retaining: " + this);
         }

         this.mRetaining = true;
         this.mRetainingStarted = this.mStarted;
         this.mStarted = false;
         this.mCallbacks = null;
      }

      void finishRetain() {
         if (this.mRetaining) {
            if (LoaderManagerImpl.DEBUG) {
               Log.v("LoaderManager", "  Finished Retaining: " + this);
            }

            this.mRetaining = false;
            if (this.mStarted != this.mRetainingStarted && !this.mStarted) {
               this.stop();
            }
         }

         if (this.mStarted && this.mHaveData && !this.mReportNextStart) {
            this.callOnLoadFinished(this.mLoader, this.mData);
         }

      }

      void reportStart() {
         if (this.mStarted && this.mReportNextStart) {
            this.mReportNextStart = false;
            if (this.mHaveData && !this.mRetaining) {
               this.callOnLoadFinished(this.mLoader, this.mData);
            }
         }

      }

      void stop() {
         if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Stopping: " + this);
         }

         this.mStarted = false;
         if (!this.mRetaining && this.mLoader != null && this.mListenerRegistered) {
            this.mListenerRegistered = false;
            this.mLoader.unregisterListener(this);
            this.mLoader.unregisterOnLoadCanceledListener(this);
            this.mLoader.stopLoading();
         }

      }

      boolean cancel() {
         if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Canceling: " + this);
         }

         if (this.mStarted && this.mLoader != null && this.mListenerRegistered) {
            boolean cancelLoadResult = this.mLoader.cancelLoad();
            if (!cancelLoadResult) {
               this.onLoadCanceled(this.mLoader);
            }

            return cancelLoadResult;
         } else {
            return false;
         }
      }

      void destroy() {
         if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "  Destroying: " + this);
         }

         this.mDestroyed = true;
         boolean needReset = this.mDeliveredData;
         this.mDeliveredData = false;
         if (this.mCallbacks != null && this.mLoader != null && this.mHaveData && needReset) {
            if (LoaderManagerImpl.DEBUG) {
               Log.v("LoaderManager", "  Resetting: " + this);
            }

            String lastBecause = null;
            if (LoaderManagerImpl.this.mHost != null) {
               lastBecause = LoaderManagerImpl.this.mHost.mFragmentManager.mNoTransactionsBecause;
               LoaderManagerImpl.this.mHost.mFragmentManager.mNoTransactionsBecause = "onLoaderReset";
            }

            try {
               this.mCallbacks.onLoaderReset(this.mLoader);
            } finally {
               if (LoaderManagerImpl.this.mHost != null) {
                  LoaderManagerImpl.this.mHost.mFragmentManager.mNoTransactionsBecause = lastBecause;
               }

            }
         }

         this.mCallbacks = null;
         this.mData = null;
         this.mHaveData = false;
         if (this.mLoader != null) {
            if (this.mListenerRegistered) {
               this.mListenerRegistered = false;
               this.mLoader.unregisterListener(this);
               this.mLoader.unregisterOnLoadCanceledListener(this);
            }

            this.mLoader.reset();
         }

         if (this.mPendingLoader != null) {
            this.mPendingLoader.destroy();
         }

      }

      public void onLoadCanceled(Loader loader) {
         if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "onLoadCanceled: " + this);
         }

         if (this.mDestroyed) {
            if (LoaderManagerImpl.DEBUG) {
               Log.v("LoaderManager", "  Ignoring load canceled -- destroyed");
            }

         } else if (LoaderManagerImpl.this.mLoaders.get(this.mId) != this) {
            if (LoaderManagerImpl.DEBUG) {
               Log.v("LoaderManager", "  Ignoring load canceled -- not active");
            }

         } else {
            LoaderManagerImpl.LoaderInfo pending = this.mPendingLoader;
            if (pending != null) {
               if (LoaderManagerImpl.DEBUG) {
                  Log.v("LoaderManager", "  Switching to pending loader: " + pending);
               }

               this.mPendingLoader = null;
               LoaderManagerImpl.this.mLoaders.put(this.mId, (Object)null);
               this.destroy();
               LoaderManagerImpl.this.installLoader(pending);
            }

         }
      }

      public void onLoadComplete(Loader loader, Object data) {
         if (LoaderManagerImpl.DEBUG) {
            Log.v("LoaderManager", "onLoadComplete: " + this);
         }

         if (this.mDestroyed) {
            if (LoaderManagerImpl.DEBUG) {
               Log.v("LoaderManager", "  Ignoring load complete -- destroyed");
            }

         } else if (LoaderManagerImpl.this.mLoaders.get(this.mId) != this) {
            if (LoaderManagerImpl.DEBUG) {
               Log.v("LoaderManager", "  Ignoring load complete -- not active");
            }

         } else {
            LoaderManagerImpl.LoaderInfo pending = this.mPendingLoader;
            if (pending != null) {
               if (LoaderManagerImpl.DEBUG) {
                  Log.v("LoaderManager", "  Switching to pending loader: " + pending);
               }

               this.mPendingLoader = null;
               LoaderManagerImpl.this.mLoaders.put(this.mId, (Object)null);
               this.destroy();
               LoaderManagerImpl.this.installLoader(pending);
            } else {
               if (this.mData != data || !this.mHaveData) {
                  this.mData = data;
                  this.mHaveData = true;
                  if (this.mStarted) {
                     this.callOnLoadFinished(loader, data);
                  }
               }

               LoaderManagerImpl.LoaderInfo info = (LoaderManagerImpl.LoaderInfo)LoaderManagerImpl.this.mInactiveLoaders.get(this.mId);
               if (info != null && info != this) {
                  info.mDeliveredData = false;
                  info.destroy();
                  LoaderManagerImpl.this.mInactiveLoaders.remove(this.mId);
               }

               if (LoaderManagerImpl.this.mHost != null && !LoaderManagerImpl.this.hasRunningLoaders()) {
                  LoaderManagerImpl.this.mHost.mFragmentManager.startPendingDeferredFragments();
               }

            }
         }
      }

      void callOnLoadFinished(Loader loader, Object data) {
         if (this.mCallbacks != null) {
            String lastBecause = null;
            if (LoaderManagerImpl.this.mHost != null) {
               lastBecause = LoaderManagerImpl.this.mHost.mFragmentManager.mNoTransactionsBecause;
               LoaderManagerImpl.this.mHost.mFragmentManager.mNoTransactionsBecause = "onLoadFinished";
            }

            try {
               if (LoaderManagerImpl.DEBUG) {
                  Log.v("LoaderManager", "  onLoadFinished in " + loader + ": " + loader.dataToString(data));
               }

               this.mCallbacks.onLoadFinished(loader, data);
            } finally {
               if (LoaderManagerImpl.this.mHost != null) {
                  LoaderManagerImpl.this.mHost.mFragmentManager.mNoTransactionsBecause = lastBecause;
               }

            }

            this.mDeliveredData = true;
         }

      }

      public String toString() {
         StringBuilder sb = new StringBuilder(64);
         sb.append("LoaderInfo{");
         sb.append(Integer.toHexString(System.identityHashCode(this)));
         sb.append(" #");
         sb.append(this.mId);
         sb.append(" : ");
         DebugUtils.buildShortClassTag(this.mLoader, sb);
         sb.append("}}");
         return sb.toString();
      }

      public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
         writer.print(prefix);
         writer.print("mId=");
         writer.print(this.mId);
         writer.print(" mArgs=");
         writer.println(this.mArgs);
         writer.print(prefix);
         writer.print("mCallbacks=");
         writer.println(this.mCallbacks);
         writer.print(prefix);
         writer.print("mLoader=");
         writer.println(this.mLoader);
         if (this.mLoader != null) {
            this.mLoader.dump(prefix + "  ", fd, writer, args);
         }

         if (this.mHaveData || this.mDeliveredData) {
            writer.print(prefix);
            writer.print("mHaveData=");
            writer.print(this.mHaveData);
            writer.print("  mDeliveredData=");
            writer.println(this.mDeliveredData);
            writer.print(prefix);
            writer.print("mData=");
            writer.println(this.mData);
         }

         writer.print(prefix);
         writer.print("mStarted=");
         writer.print(this.mStarted);
         writer.print(" mReportNextStart=");
         writer.print(this.mReportNextStart);
         writer.print(" mDestroyed=");
         writer.println(this.mDestroyed);
         writer.print(prefix);
         writer.print("mRetaining=");
         writer.print(this.mRetaining);
         writer.print(" mRetainingStarted=");
         writer.print(this.mRetainingStarted);
         writer.print(" mListenerRegistered=");
         writer.println(this.mListenerRegistered);
         if (this.mPendingLoader != null) {
            writer.print(prefix);
            writer.println("Pending Loader ");
            writer.print(this.mPendingLoader);
            writer.println(":");
            this.mPendingLoader.dump(prefix + "  ", fd, writer, args);
         }

      }
   }
}
