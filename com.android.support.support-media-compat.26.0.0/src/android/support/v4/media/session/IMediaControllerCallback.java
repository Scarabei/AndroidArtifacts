package android.support.v4.media.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.text.TextUtils;
import java.util.List;

public interface IMediaControllerCallback extends IInterface {
   void onEvent(String var1, Bundle var2) throws RemoteException;

   void onSessionDestroyed() throws RemoteException;

   void onPlaybackStateChanged(PlaybackStateCompat var1) throws RemoteException;

   void onMetadataChanged(MediaMetadataCompat var1) throws RemoteException;

   void onQueueChanged(List var1) throws RemoteException;

   void onQueueTitleChanged(CharSequence var1) throws RemoteException;

   void onExtrasChanged(Bundle var1) throws RemoteException;

   void onVolumeInfoChanged(ParcelableVolumeInfo var1) throws RemoteException;

   void onRepeatModeChanged(int var1) throws RemoteException;

   void onShuffleModeChangedDeprecated(boolean var1) throws RemoteException;

   void onCaptioningEnabledChanged(boolean var1) throws RemoteException;

   void onShuffleModeChanged(int var1) throws RemoteException;

   public abstract static class Stub extends Binder implements IMediaControllerCallback {
      private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaControllerCallback";
      static final int TRANSACTION_onEvent = 1;
      static final int TRANSACTION_onSessionDestroyed = 2;
      static final int TRANSACTION_onPlaybackStateChanged = 3;
      static final int TRANSACTION_onMetadataChanged = 4;
      static final int TRANSACTION_onQueueChanged = 5;
      static final int TRANSACTION_onQueueTitleChanged = 6;
      static final int TRANSACTION_onExtrasChanged = 7;
      static final int TRANSACTION_onVolumeInfoChanged = 8;
      static final int TRANSACTION_onRepeatModeChanged = 9;
      static final int TRANSACTION_onShuffleModeChangedDeprecated = 10;
      static final int TRANSACTION_onCaptioningEnabledChanged = 11;
      static final int TRANSACTION_onShuffleModeChanged = 12;

      public Stub() {
         this.attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
      }

      public static IMediaControllerCallback asInterface(IBinder obj) {
         if (obj == null) {
            return null;
         } else {
            IInterface iin = obj.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
            return (IMediaControllerCallback)(iin != null && iin instanceof IMediaControllerCallback ? (IMediaControllerCallback)iin : new IMediaControllerCallback.Stub.Proxy(obj));
         }
      }

      public IBinder asBinder() {
         return this;
      }

      public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
         int _arg0;
         boolean _arg0;
         switch(code) {
         case 1:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            String _arg0 = data.readString();
            Bundle _arg1;
            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.onEvent(_arg0, _arg1);
            return true;
         case 2:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            this.onSessionDestroyed();
            return true;
         case 3:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            PlaybackStateCompat _arg0;
            if (0 != data.readInt()) {
               _arg0 = (PlaybackStateCompat)PlaybackStateCompat.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            this.onPlaybackStateChanged(_arg0);
            return true;
         case 4:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            MediaMetadataCompat _arg0;
            if (0 != data.readInt()) {
               _arg0 = (MediaMetadataCompat)MediaMetadataCompat.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            this.onMetadataChanged(_arg0);
            return true;
         case 5:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            List _arg0 = data.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
            this.onQueueChanged(_arg0);
            return true;
         case 6:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            CharSequence _arg0;
            if (0 != data.readInt()) {
               _arg0 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            this.onQueueTitleChanged(_arg0);
            return true;
         case 7:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            Bundle _arg0;
            if (0 != data.readInt()) {
               _arg0 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            this.onExtrasChanged(_arg0);
            return true;
         case 8:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            ParcelableVolumeInfo _arg0;
            if (0 != data.readInt()) {
               _arg0 = (ParcelableVolumeInfo)ParcelableVolumeInfo.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            this.onVolumeInfoChanged(_arg0);
            return true;
         case 9:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            _arg0 = data.readInt();
            this.onRepeatModeChanged(_arg0);
            return true;
         case 10:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            _arg0 = 0 != data.readInt();
            this.onShuffleModeChangedDeprecated(_arg0);
            return true;
         case 11:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            _arg0 = 0 != data.readInt();
            this.onCaptioningEnabledChanged(_arg0);
            return true;
         case 12:
            data.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            _arg0 = data.readInt();
            this.onShuffleModeChanged(_arg0);
            return true;
         case 1598968902:
            reply.writeString("android.support.v4.media.session.IMediaControllerCallback");
            return true;
         default:
            return super.onTransact(code, data, reply, flags);
         }
      }

      private static class Proxy implements IMediaControllerCallback {
         private IBinder mRemote;

         Proxy(IBinder remote) {
            this.mRemote = remote;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "android.support.v4.media.session.IMediaControllerCallback";
         }

         public void onEvent(String event, Bundle extras) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               _data.writeString(event);
               if (extras != null) {
                  _data.writeInt(1);
                  extras.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(1, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onSessionDestroyed() throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               this.mRemote.transact(2, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onPlaybackStateChanged(PlaybackStateCompat state) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               if (state != null) {
                  _data.writeInt(1);
                  state.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(3, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onMetadataChanged(MediaMetadataCompat metadata) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               if (metadata != null) {
                  _data.writeInt(1);
                  metadata.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(4, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onQueueChanged(List queue) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               _data.writeTypedList(queue);
               this.mRemote.transact(5, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onQueueTitleChanged(CharSequence title) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               if (title != null) {
                  _data.writeInt(1);
                  TextUtils.writeToParcel(title, _data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(6, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onExtrasChanged(Bundle extras) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               if (extras != null) {
                  _data.writeInt(1);
                  extras.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(7, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onVolumeInfoChanged(ParcelableVolumeInfo info) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               if (info != null) {
                  _data.writeInt(1);
                  info.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(8, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onRepeatModeChanged(int repeatMode) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               _data.writeInt(repeatMode);
               this.mRemote.transact(9, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onShuffleModeChangedDeprecated(boolean enabled) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               _data.writeInt(enabled ? 1 : 0);
               this.mRemote.transact(10, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onCaptioningEnabledChanged(boolean enabled) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               _data.writeInt(enabled ? 1 : 0);
               this.mRemote.transact(11, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }

         public void onShuffleModeChanged(int shuffleMode) throws RemoteException {
            Parcel _data = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
               _data.writeInt(shuffleMode);
               this.mRemote.transact(12, _data, (Parcel)null, 1);
            } finally {
               _data.recycle();
            }

         }
      }
   }
}
