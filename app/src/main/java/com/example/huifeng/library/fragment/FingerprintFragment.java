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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

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
    private Dialog mDialog = null;
    private Cipher mCipher;
    private ImageView mDialogIV;
    private TextView mDialogTvInfo;

    @OnClick({R.id.btn_fingerprint})
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
        }
    }

    /**
     * 指纹识别回调类
     */
    private class FingerCallBack extends FingerprintManager.AuthenticationCallback {

        /**
         * 成功
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

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            mDialogIV.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 1600);
        }

        /**
         * 失败
         *
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
        ((MainActivity) mContext).setmTitleText("Fingerprint");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void init() {
        super.init();
        registerFingerprint();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void registerFingerprint() {
        try {
            KeyStore mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator mKeyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            mKeyStore.load(null);
            String DEFAULT_KEY_NAME = "default_key";
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
