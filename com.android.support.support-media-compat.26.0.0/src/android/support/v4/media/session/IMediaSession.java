package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public interface IMediaSession extends IInterface {
   void sendCommand(String var1, Bundle var2, MediaSessionCompat.ResultReceiverWrapper var3) throws RemoteException;

   boolean sendMediaButton(KeyEvent var1) throws RemoteException;

   void registerCallbackListener(IMediaControllerCallback var1) throws RemoteException;

   void unregisterCallbackListener(IMediaControllerCallback var1) throws RemoteException;

   boolean isTransportControlEnabled() throws RemoteException;

   String getPackageName() throws RemoteException;

   String getTag() throws RemoteException;

   PendingIntent getLaunchPendingIntent() throws RemoteException;

   long getFlags() throws RemoteException;

   ParcelableVolumeInfo getVolumeAttributes() throws RemoteException;

   void adjustVolume(int var1, int var2, String var3) throws RemoteException;

   void setVolumeTo(int var1, int var2, String var3) throws RemoteException;

   MediaMetadataCompat getMetadata() throws RemoteException;

   PlaybackStateCompat getPlaybackState() throws RemoteException;

   List getQueue() throws RemoteException;

   CharSequence getQueueTitle() throws RemoteException;

   Bundle getExtras() throws RemoteException;

   int getRatingType() throws RemoteException;

   boolean isCaptioningEnabled() throws RemoteException;

   int getRepeatMode() throws RemoteException;

   boolean isShuffleModeEnabledDeprecated() throws RemoteException;

   int getShuffleMode() throws RemoteException;

   void addQueueItem(MediaDescriptionCompat var1) throws RemoteException;

   void addQueueItemAt(MediaDescriptionCompat var1, int var2) throws RemoteException;

   void removeQueueItem(MediaDescriptionCompat var1) throws RemoteException;

   void removeQueueItemAt(int var1) throws RemoteException;

   void prepare() throws RemoteException;

   void prepareFromMediaId(String var1, Bundle var2) throws RemoteException;

   void prepareFromSearch(String var1, Bundle var2) throws RemoteException;

   void prepareFromUri(Uri var1, Bundle var2) throws RemoteException;

   void play() throws RemoteException;

   void playFromMediaId(String var1, Bundle var2) throws RemoteException;

   void playFromSearch(String var1, Bundle var2) throws RemoteException;

   void playFromUri(Uri var1, Bundle var2) throws RemoteException;

   void skipToQueueItem(long var1) throws RemoteException;

   void pause() throws RemoteException;

   void stop() throws RemoteException;

   void next() throws RemoteException;

   void previous() throws RemoteException;

   void fastForward() throws RemoteException;

   void rewind() throws RemoteException;

   void seekTo(long var1) throws RemoteException;

   void rate(RatingCompat var1) throws RemoteException;

   void rateWithExtras(RatingCompat var1, Bundle var2) throws RemoteException;

   void setCaptioningEnabled(boolean var1) throws RemoteException;

   void setRepeatMode(int var1) throws RemoteException;

   void setShuffleModeEnabledDeprecated(boolean var1) throws RemoteException;

   void setShuffleMode(int var1) throws RemoteException;

   void sendCustomAction(String var1, Bundle var2) throws RemoteException;

   public abstract static class Stub extends Binder implements IMediaSession {
      private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
      static final int TRANSACTION_sendCommand = 1;
      static final int TRANSACTION_sendMediaButton = 2;
      static final int TRANSACTION_registerCallbackListener = 3;
      static final int TRANSACTION_unregisterCallbackListener = 4;
      static final int TRANSACTION_isTransportControlEnabled = 5;
      static final int TRANSACTION_getPackageName = 6;
      static final int TRANSACTION_getTag = 7;
      static final int TRANSACTION_getLaunchPendingIntent = 8;
      static final int TRANSACTION_getFlags = 9;
      static final int TRANSACTION_getVolumeAttributes = 10;
      static final int TRANSACTION_adjustVolume = 11;
      static final int TRANSACTION_setVolumeTo = 12;
      static final int TRANSACTION_getMetadata = 27;
      static final int TRANSACTION_getPlaybackState = 28;
      static final int TRANSACTION_getQueue = 29;
      static final int TRANSACTION_getQueueTitle = 30;
      static final int TRANSACTION_getExtras = 31;
      static final int TRANSACTION_getRatingType = 32;
      static final int TRANSACTION_isCaptioningEnabled = 45;
      static final int TRANSACTION_getRepeatMode = 37;
      static final int TRANSACTION_isShuffleModeEnabledDeprecated = 38;
      static final int TRANSACTION_getShuffleMode = 47;
      static final int TRANSACTION_addQueueItem = 41;
      static final int TRANSACTION_addQueueItemAt = 42;
      static final int TRANSACTION_removeQueueItem = 43;
      static final int TRANSACTION_removeQueueItemAt = 44;
      static final int TRANSACTION_prepare = 33;
      static final int TRANSACTION_prepareFromMediaId = 34;
      static final int TRANSACTION_prepareFromSearch = 35;
      static final int TRANSACTION_prepareFromUri = 36;
      static final int TRANSACTION_play = 13;
      static final int TRANSACTION_playFromMediaId = 14;
      static final int TRANSACTION_playFromSearch = 15;
      static final int TRANSACTION_playFromUri = 16;
      static final int TRANSACTION_skipToQueueItem = 17;
      static final int TRANSACTION_pause = 18;
      static final int TRANSACTION_stop = 19;
      static final int TRANSACTION_next = 20;
      static final int TRANSACTION_previous = 21;
      static final int TRANSACTION_fastForward = 22;
      static final int TRANSACTION_rewind = 23;
      static final int TRANSACTION_seekTo = 24;
      static final int TRANSACTION_rate = 25;
      static final int TRANSACTION_rateWithExtras = 51;
      static final int TRANSACTION_setCaptioningEnabled = 46;
      static final int TRANSACTION_setRepeatMode = 39;
      static final int TRANSACTION_setShuffleModeEnabledDeprecated = 40;
      static final int TRANSACTION_setShuffleMode = 48;
      static final int TRANSACTION_sendCustomAction = 26;

      public Stub() {
         this.attachInterface(this, "android.support.v4.media.session.IMediaSession");
      }

      public static IMediaSession asInterface(IBinder obj) {
         if (obj == null) {
            return null;
         } else {
            IInterface iin = obj.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            return (IMediaSession)(iin != null && iin instanceof IMediaSession ? (IMediaSession)iin : new IMediaSession.Stub.Proxy(obj));
         }
      }

      public IBinder asBinder() {
         return this;
      }

      public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
         RatingCompat _arg0;
         Bundle _arg1;
         String _arg2;
         int _arg0;
         boolean _arg0;
         int _arg1;
         MediaDescriptionCompat _arg0;
         Uri _arg0;
         String _arg0;
         long _arg0;
         IMediaControllerCallback _arg0;
         switch(code) {
         case 1:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readString();
            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            MediaSessionCompat.ResultReceiverWrapper _arg2;
            if (0 != data.readInt()) {
               _arg2 = (MediaSessionCompat.ResultReceiverWrapper)MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(data);
            } else {
               _arg2 = null;
            }

            this.sendCommand(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
         case 2:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            KeyEvent _arg0;
            if (0 != data.readInt()) {
               _arg0 = (KeyEvent)KeyEvent.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            boolean _result = this.sendMediaButton(_arg0);
            reply.writeNoException();
            reply.writeInt(_result ? 1 : 0);
            return true;
         case 3:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = IMediaControllerCallback.Stub.asInterface(data.readStrongBinder());
            this.registerCallbackListener(_arg0);
            reply.writeNoException();
            return true;
         case 4:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = IMediaControllerCallback.Stub.asInterface(data.readStrongBinder());
            this.unregisterCallbackListener(_arg0);
            reply.writeNoException();
            return true;
         case 5:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = this.isTransportControlEnabled();
            reply.writeNoException();
            reply.writeInt(_arg0 ? 1 : 0);
            return true;
         case 6:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = this.getPackageName();
            reply.writeNoException();
            reply.writeString(_arg0);
            return true;
         case 7:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = this.getTag();
            reply.writeNoException();
            reply.writeString(_arg0);
            return true;
         case 8:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            PendingIntent _result = this.getLaunchPendingIntent();
            reply.writeNoException();
            if (_result != null) {
               reply.writeInt(1);
               _result.writeToParcel(reply, 1);
            } else {
               reply.writeInt(0);
            }

            return true;
         case 9:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = this.getFlags();
            reply.writeNoException();
            reply.writeLong(_arg0);
            return true;
         case 10:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            ParcelableVolumeInfo _result = this.getVolumeAttributes();
            reply.writeNoException();
            if (_result != null) {
               reply.writeInt(1);
               _result.writeToParcel(reply, 1);
            } else {
               reply.writeInt(0);
            }

            return true;
         case 11:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readInt();
            _arg1 = data.readInt();
            _arg2 = data.readString();
            this.adjustVolume(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
         case 12:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readInt();
            _arg1 = data.readInt();
            _arg2 = data.readString();
            this.setVolumeTo(_arg0, _arg1, _arg2);
            reply.writeNoException();
            return true;
         case 13:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.play();
            reply.writeNoException();
            return true;
         case 14:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readString();
            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.playFromMediaId(_arg0, _arg1);
            reply.writeNoException();
            return true;
         case 15:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readString();
            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.playFromSearch(_arg0, _arg1);
            reply.writeNoException();
            return true;
         case 16:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (0 != data.readInt()) {
               _arg0 = (Uri)Uri.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.playFromUri(_arg0, _arg1);
            reply.writeNoException();
            return true;
         case 17:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readLong();
            this.skipToQueueItem(_arg0);
            reply.writeNoException();
            return true;
         case 18:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.pause();
            reply.writeNoException();
            return true;
         case 19:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.stop();
            reply.writeNoException();
            return true;
         case 20:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.next();
            reply.writeNoException();
            return true;
         case 21:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.previous();
            reply.writeNoException();
            return true;
         case 22:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.fastForward();
            reply.writeNoException();
            return true;
         case 23:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.rewind();
            reply.writeNoException();
            return true;
         case 24:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readLong();
            this.seekTo(_arg0);
            reply.writeNoException();
            return true;
         case 25:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (0 != data.readInt()) {
               _arg0 = (RatingCompat)RatingCompat.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            this.rate(_arg0);
            reply.writeNoException();
            return true;
         case 26:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readString();
            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.sendCustomAction(_arg0, _arg1);
            reply.writeNoException();
            return true;
         case 27:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            MediaMetadataCompat _result = this.getMetadata();
            reply.writeNoException();
            if (_result != null) {
               reply.writeInt(1);
               _result.writeToParcel(reply, 1);
            } else {
               reply.writeInt(0);
            }

            return true;
         case 28:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            PlaybackStateCompat _result = this.getPlaybackState();
            reply.writeNoException();
            if (_result != null) {
               reply.writeInt(1);
               _result.writeToParcel(reply, 1);
            } else {
               reply.writeInt(0);
            }

            return true;
         case 29:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            List _result = this.getQueue();
            reply.writeNoException();
            reply.writeTypedList(_result);
            return true;
         case 30:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            CharSequence _result = this.getQueueTitle();
            reply.writeNoException();
            if (_result != null) {
               reply.writeInt(1);
               TextUtils.writeToParcel(_result, reply, 1);
            } else {
               reply.writeInt(0);
            }

            return true;
         case 31:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            Bundle _result = this.getExtras();
            reply.writeNoException();
            if (_result != null) {
               reply.writeInt(1);
               _result.writeToParcel(reply, 1);
            } else {
               reply.writeInt(0);
            }

            return true;
         case 32:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = this.getRatingType();
            reply.writeNoException();
            reply.writeInt(_arg0);
            return true;
         case 33:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            this.prepare();
            reply.writeNoException();
            return true;
         case 34:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readString();
            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.prepareFromMediaId(_arg0, _arg1);
            reply.writeNoException();
            return true;
         case 35:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readString();
            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.prepareFromSearch(_arg0, _arg1);
            reply.writeNoException();
            return true;
         case 36:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (0 != data.readInt()) {
               _arg0 = (Uri)Uri.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.prepareFromUri(_arg0, _arg1);
            reply.writeNoException();
            return true;
         case 37:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = this.getRepeatMode();
            reply.writeNoException();
            reply.writeInt(_arg0);
            return true;
         case 38:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = this.isShuffleModeEnabledDeprecated();
            reply.writeNoException();
            reply.writeInt(_arg0 ? 1 : 0);
            return true;
         case 39:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readInt();
            this.setRepeatMode(_arg0);
            reply.writeNoException();
            return true;
         case 40:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = 0 != data.readInt();
            this.setShuffleModeEnabledDeprecated(_arg0);
            reply.writeNoException();
            return true;
         case 41:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (0 != data.readInt()) {
               _arg0 = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            this.addQueueItem(_arg0);
            reply.writeNoException();
            return true;
         case 42:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (0 != data.readInt()) {
               _arg0 = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            _arg1 = data.readInt();
            this.addQueueItemAt(_arg0, _arg1);
            reply.writeNoException();
            return true;
         case 43:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (0 != data.readInt()) {
               _arg0 = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            this.removeQueueItem(_arg0);
            reply.writeNoException();
            return true;
         case 44:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readInt();
            this.removeQueueItemAt(_arg0);
            reply.writeNoException();
            return true;
         case 45:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = this.isCaptioningEnabled();
            reply.writeNoException();
            reply.writeInt(_arg0 ? 1 : 0);
            return true;
         case 46:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = 0 != data.readInt();
            this.setCaptioningEnabled(_arg0);
            reply.writeNoException();
            return true;
         case 47:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = this.getShuffleMode();
            reply.writeNoException();
            reply.writeInt(_arg0);
            return true;
         case 48:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            _arg0 = data.readInt();
            this.setShuffleMode(_arg0);
            reply.writeNoException();
            return true;
         case 51:
            data.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (0 != data.readInt()) {
               _arg0 = (RatingCompat)RatingCompat.CREATOR.createFromParcel(data);
            } else {
               _arg0 = null;
            }

            if (0 != data.readInt()) {
               _arg1 = (Bundle)Bundle.CREATOR.createFromParcel(data);
            } else {
               _arg1 = null;
            }

            this.rateWithExtras(_arg0, _arg1);
            reply.writeNoException();
            return true;
         case 1598968902:
            reply.writeString("android.support.v4.media.session.IMediaSession");
            return true;
         default:
            return super.onTransact(code, data, reply, flags);
         }
      }

      private static class Proxy implements IMediaSession {
         private IBinder mRemote;

         Proxy(IBinder remote) {
            this.mRemote = remote;
         }

         public IBinder asBinder() {
            return this.mRemote;
         }

         public String getInterfaceDescriptor() {
            return "android.support.v4.media.session.IMediaSession";
         }

         public void sendCommand(String command, Bundle args, MediaSessionCompat.ResultReceiverWrapper cb) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeString(command);
               if (args != null) {
                  _data.writeInt(1);
                  args.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               if (cb != null) {
                  _data.writeInt(1);
                  cb.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(1, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public boolean sendMediaButton(KeyEvent mediaButton) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            boolean _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               if (mediaButton != null) {
                  _data.writeInt(1);
                  mediaButton.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(2, _data, _reply, 0);
               _reply.readException();
               _result = 0 != _reply.readInt();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public void registerCallbackListener(IMediaControllerCallback cb) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
               this.mRemote.transact(3, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void unregisterCallbackListener(IMediaControllerCallback cb) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
               this.mRemote.transact(4, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public boolean isTransportControlEnabled() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            boolean _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(5, _data, _reply, 0);
               _reply.readException();
               _result = 0 != _reply.readInt();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public String getPackageName() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            String _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(6, _data, _reply, 0);
               _reply.readException();
               _result = _reply.readString();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public String getTag() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            String _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(7, _data, _reply, 0);
               _reply.readException();
               _result = _reply.readString();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public PendingIntent getLaunchPendingIntent() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            PendingIntent _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(8, _data, _reply, 0);
               _reply.readException();
               if (0 != _reply.readInt()) {
                  _result = (PendingIntent)PendingIntent.CREATOR.createFromParcel(_reply);
               } else {
                  _result = null;
               }
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public long getFlags() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            long _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(9, _data, _reply, 0);
               _reply.readException();
               _result = _reply.readLong();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public ParcelableVolumeInfo getVolumeAttributes() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            ParcelableVolumeInfo _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(10, _data, _reply, 0);
               _reply.readException();
               if (0 != _reply.readInt()) {
                  _result = (ParcelableVolumeInfo)ParcelableVolumeInfo.CREATOR.createFromParcel(_reply);
               } else {
                  _result = null;
               }
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public void adjustVolume(int direction, int flags, String packageName) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeInt(direction);
               _data.writeInt(flags);
               _data.writeString(packageName);
               this.mRemote.transact(11, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void setVolumeTo(int value, int flags, String packageName) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeInt(value);
               _data.writeInt(flags);
               _data.writeString(packageName);
               this.mRemote.transact(12, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public MediaMetadataCompat getMetadata() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            MediaMetadataCompat _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(27, _data, _reply, 0);
               _reply.readException();
               if (0 != _reply.readInt()) {
                  _result = (MediaMetadataCompat)MediaMetadataCompat.CREATOR.createFromParcel(_reply);
               } else {
                  _result = null;
               }
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public PlaybackStateCompat getPlaybackState() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            PlaybackStateCompat _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(28, _data, _reply, 0);
               _reply.readException();
               if (0 != _reply.readInt()) {
                  _result = (PlaybackStateCompat)PlaybackStateCompat.CREATOR.createFromParcel(_reply);
               } else {
                  _result = null;
               }
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public List getQueue() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            ArrayList _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(29, _data, _reply, 0);
               _reply.readException();
               _result = _reply.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public CharSequence getQueueTitle() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            CharSequence _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(30, _data, _reply, 0);
               _reply.readException();
               if (0 != _reply.readInt()) {
                  _result = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(_reply);
               } else {
                  _result = null;
               }
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public Bundle getExtras() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            Bundle _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(31, _data, _reply, 0);
               _reply.readException();
               if (0 != _reply.readInt()) {
                  _result = (Bundle)Bundle.CREATOR.createFromParcel(_reply);
               } else {
                  _result = null;
               }
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public int getRatingType() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            int _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(32, _data, _reply, 0);
               _reply.readException();
               _result = _reply.readInt();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public boolean isCaptioningEnabled() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            boolean _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(45, _data, _reply, 0);
               _reply.readException();
               _result = 0 != _reply.readInt();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public int getRepeatMode() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            int _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(37, _data, _reply, 0);
               _reply.readException();
               _result = _reply.readInt();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public boolean isShuffleModeEnabledDeprecated() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            boolean _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(38, _data, _reply, 0);
               _reply.readException();
               _result = 0 != _reply.readInt();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public int getShuffleMode() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            int _result;
            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(47, _data, _reply, 0);
               _reply.readException();
               _result = _reply.readInt();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

            return _result;
         }

         public void addQueueItem(MediaDescriptionCompat description) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               if (description != null) {
                  _data.writeInt(1);
                  description.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(41, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void addQueueItemAt(MediaDescriptionCompat description, int index) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               if (description != null) {
                  _data.writeInt(1);
                  description.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               _data.writeInt(index);
               this.mRemote.transact(42, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void removeQueueItem(MediaDescriptionCompat description) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               if (description != null) {
                  _data.writeInt(1);
                  description.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(43, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void removeQueueItemAt(int index) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeInt(index);
               this.mRemote.transact(44, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void prepare() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(33, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void prepareFromMediaId(String uri, Bundle extras) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeString(uri);
               if (extras != null) {
                  _data.writeInt(1);
                  extras.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(34, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void prepareFromSearch(String string, Bundle extras) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeString(string);
               if (extras != null) {
                  _data.writeInt(1);
                  extras.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(35, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void prepareFromUri(Uri uri, Bundle extras) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               if (uri != null) {
                  _data.writeInt(1);
                  uri.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               if (extras != null) {
                  _data.writeInt(1);
                  extras.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(36, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void play() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(13, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void playFromMediaId(String uri, Bundle extras) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeString(uri);
               if (extras != null) {
                  _data.writeInt(1);
                  extras.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(14, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void playFromSearch(String string, Bundle extras) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeString(string);
               if (extras != null) {
                  _data.writeInt(1);
                  extras.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(15, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void playFromUri(Uri uri, Bundle extras) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               if (uri != null) {
                  _data.writeInt(1);
                  uri.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               if (extras != null) {
                  _data.writeInt(1);
                  extras.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(16, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void skipToQueueItem(long id) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeLong(id);
               this.mRemote.transact(17, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void pause() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(18, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void stop() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(19, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void next() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(20, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void previous() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(21, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void fastForward() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(22, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void rewind() throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               this.mRemote.transact(23, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void seekTo(long pos) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeLong(pos);
               this.mRemote.transact(24, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void rate(RatingCompat rating) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               if (rating != null) {
                  _data.writeInt(1);
                  rating.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(25, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void rateWithExtras(RatingCompat rating, Bundle extras) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               if (rating != null) {
                  _data.writeInt(1);
                  rating.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               if (extras != null) {
                  _data.writeInt(1);
                  extras.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(51, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void setCaptioningEnabled(boolean enabled) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeInt(enabled ? 1 : 0);
               this.mRemote.transact(46, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void setRepeatMode(int repeatMode) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeInt(repeatMode);
               this.mRemote.transact(39, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void setShuffleModeEnabledDeprecated(boolean shuffleMode) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeInt(shuffleMode ? 1 : 0);
               this.mRemote.transact(40, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void setShuffleMode(int shuffleMode) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeInt(shuffleMode);
               this.mRemote.transact(48, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }

         public void sendCustomAction(String action, Bundle args) throws RemoteException {
            Parcel _data = Parcel.obtain();
            Parcel _reply = Parcel.obtain();

            try {
               _data.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
               _data.writeString(action);
               if (args != null) {
                  _data.writeInt(1);
                  args.writeToParcel(_data, 0);
               } else {
                  _data.writeInt(0);
               }

               this.mRemote.transact(26, _data, _reply, 0);
               _reply.readException();
            } finally {
               _reply.recycle();
               _data.recycle();
            }

         }
      }
   }
}
