package com.example.huifeng.library.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.ECGenParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 指纹识别
 * <p>
 * Android 指纹识别从6.0开始, 使用场景有两种
 * 1 : 纯本地使用。即用户在本地完成指纹识别后，不需要将指纹的相关信息给后台
 * 2: 与后台交互。用户在本地完成指纹识别后，需要将指纹相关的信息传给后台
 * 由于使用指纹识别功能需要一个加密对象（CryptoObject）该对象一般是由对称加密或者非对称加密获得。
 * 上述两种开发场景的实现大同小异，主要区别在于加密过程中密钥的创建和使用，一般来说，纯本地的使用指纹识别功能，只需要对称加密即可；
 * 而与后台交互则需要使用非对称加密：将私钥用于本地指纹识别，识别成功后将加密信息传给后台，后台开发人员用公钥解密，以获得用户信息。
 * <p>
 * Created by ShineF on 2017/7/19 0019.
 */

public class FingerprintFragment extends BaseFragment {

    @BindView(R.id.btn_fingerprint)
    Button mFingerPrint;
    @BindView(R.id.btn_fingerprint_2)
    Button mFingerPrintT;
    private Dialog mDialog = null;
    private Cipher mCipher;
    private ImageView mDialogIV;
    private TextView mDialogTvInfo;
    private final String DEFAULT_KEY_NAME = "default_key";
    Signature mSignature;


    @OnClick({R.id.btn_fingerprint, R.id.btn_fingerprint_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fingerprint:
                showDialog();
                FingerprintManager.CryptoObject object = new FingerprintManager.CryptoObject(mCipher);
                FingerprintManager mManager = mContext.getSystemService(FingerprintManager.class);
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mManager.authenticate(object, new CancellationSignal(), 0, new FingerCallBack(), null); //启动指纹传感器
                break;
            case R.id.btn_fingerprint_2:
                showDialog();
                FingerprintManager.CryptoObject asymmetricObject = new FingerprintManager.CryptoObject(mSignature);
                FingerprintManager mAsymmetricManager = mContext.getSystemService(FingerprintManager.class);
                mAsymmetricManager.authenticate(asymmetricObject, new CancellationSignal(), 0, new AsymmetricFingerprintCallBack(), null); //启动指纹传感器
                break;
        }
    }

    private class AsymmetricFingerprintCallBack extends FingerprintManager.AuthenticationCallback{
        private static final String TAG = "ShineF";

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
        }

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
//            try {
//                Field field = result.getClass().getDeclaredField("mFingerprint");
//                field.setAccessible(true);
//                Object fingerPrint = field.get(result);
//
//                Class<?> clzz = Class.forName("android.hardware.fingerprint.Fingerprint");
//                Method getName = clzz.getDeclaredMethod("getName");
//                Method getFingerId = clzz.getDeclaredMethod("getFingerId");
//                Method getGroupId = clzz.getDeclaredMethod("getGroupId");
//                Method getDeviceId = clzz.getDeclaredMethod("getDeviceId");
//
//                CharSequence name = (CharSequence) getName.invoke(fingerPrint);
//                int fingerId = (int) getFingerId.invoke(fingerPrint);
//                int groupId = (int) getGroupId.invoke(fingerPrint);
//                long deviceId = (long) getDeviceId.invoke(fingerPrint);
//
////                Log.d(TAG, "name: " + name);
//                Log.d(TAG, "fingerId: " + fingerId);
//                Log.d(TAG, "groupId: " + groupId);
//                Log.d(TAG, "deviceId: " + deviceId);
//            } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
        }
    }

    /**
     * 指纹识别回调类 对称加密
     */
    private class FingerCallBack extends FingerprintManager.AuthenticationCallback {

        /**
         * 失败
         */
        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            mDialogIV.setImageResource(R.drawable.ic_fingerprint_error);
            mDialogTvInfo.setText("指纹验证失败");
            mDialogTvInfo.setTextColor(
                    mDialogTvInfo.getResources().getColor(R.color.warning_color, null));
            mDialogTvInfo.removeCallbacks(mAuthenticateRunnable);
            mDialogTvInfo.postDelayed(mAuthenticateRunnable, 2000);
        }

        /**
         *
         * 当系统回调了onAuthenticationError()方法关闭传感器后,
         * 这种情况下再次调用authenticate()会有一段时间的禁用期,
         * 也就是说这段时间里是无法再次使用指纹识别的。
         * 当然，具体的禁用时间由手机厂商的系统不同而有略微差别，有的是1分钟，有的是30秒等等。
         * 而且，由于手机厂商的系统区别，有些系统上调用了onAuthenticationError()后,
         * 在禁用时间内，其他APP里面的指纹识别功能也无法使用，甚至系统的指纹解锁功能也无法使用。
         * 而有的系统上，在禁用时间内调用其他APP的指纹解锁功能，或者系统的指纹解锁功能，就能立即重置指纹识别功能。
         *
         * 失败五次以上的回调
         */

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            mDialogIV.setImageResource(R.drawable.ic_fingerprint_error);
            mDialogTvInfo.setText("您错误次数太多,请稍候在尝试");
            mDialogTvInfo.setTextColor(
                    mDialogTvInfo.getResources().getColor(R.color.warning_color, null));
            mDialogIV.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, 1000);
        }

        /**
         * 成功
         *当系统调用了onAuthenticationError()和onAuthenticationSucceeded()后，传感器会关闭,
         * 只有我们重新授权，再次调用authenticate()方法后才能继续使用指纹识别功能。
         * @param result
         */
        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            mDialogTvInfo.removeCallbacks(mAuthenticateRunnable);
            mDialogIV.setImageResource(R.drawable.ic_fingerprint_success);
            mDialogTvInfo.setTextColor(
                    mDialogTvInfo.getResources().getColor(R.color.success_color, null));
            mDialogTvInfo.setText("指纹验证成功");
            mDialogIV.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, 1000);


        }

        private Runnable mAuthenticateRunnable = new Runnable() {
            @Override
            public void run() {
                mDialogTvInfo.setTextColor(
                        mDialogTvInfo.getResources().getColor(R.color.hint_color, null));
                mDialogTvInfo.setText("触摸传感器");
                mDialogIV.setImageResource(R.mipmap.ic_fp_40px);
            }
        };
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_fingerprint;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setmTitleText("指纹");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void init() {
        super.init();
        registerFingerprint();
        registAsymmetricFingerprint();
    }

    /**
     * 注册对称加密,指纹识别
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void registerFingerprint() {
        try {
            KeyStore mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator mKeyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            mKeyStore.load(null);
            KeyGenParameterSpec.Builder spec = new KeyGenParameterSpec.Builder(DEFAULT_KEY_NAME, //生成Key
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                spec.setInvalidatedByBiometricEnrollment(true);
            }
            mKeyGenerator.init(spec.build());
            mKeyGenerator.generateKey();
            mCipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            mKeyStore.load(null);
            SecretKey key = (SecretKey) mKeyStore.getKey(DEFAULT_KEY_NAME, null);
            mCipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException
                | CertificateException | IOException | InvalidAlgorithmParameterException
                | NoSuchPaddingException | UnrecoverableKeyException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册非对称加密指纹识别
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void registAsymmetricFingerprint() {
        try {
            KeyPairGenerator mKeyPG = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore");
            KeyStore mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            mKeyPG.initialize(
                    new KeyGenParameterSpec.Builder(DEFAULT_KEY_NAME,
                            KeyProperties.PURPOSE_SIGN)
                            .setDigests(KeyProperties.DIGEST_SHA256)
                            .setAlgorithmParameterSpec(new ECGenParameterSpec("secp256r1"))
                            // Require the user to authenticate with a fingerprint to authorize
                            // every use of the private key
                            .setUserAuthenticationRequired(true)
                            .build());
            mKeyPG.generateKeyPair();
            mKeyStore.load(null);
            PrivateKey key = (PrivateKey) mKeyStore.getKey(DEFAULT_KEY_NAME, null);
            mSignature = Signature.getInstance("SHA256withECDSA");
            mSignature.initSign(key);
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException | CertificateException | UnrecoverableKeyException | KeyStoreException | IOException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }


    /**
     * Dialog 显示
     */
    public void showDialog() {
        View view = View.inflate(mContext, R.layout.view_fingerprint_dialog_content, null);
        mDialogIV = (ImageView) view.findViewById(R.id.fingerprint_icon);
        mDialogTvInfo = (TextView) view.findViewById(R.id.fingerprint_status);
        Button cancelBtn = (Button) view.findViewById(R.id.cancel_button);
        mDialog = new AlertDialog.Builder(mContext)
                .setView(view)
                .create();
        mDialog.setCanceledOnTouchOutside(false);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }
}
