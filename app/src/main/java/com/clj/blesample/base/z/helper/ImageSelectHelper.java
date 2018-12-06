package com.clj.blesample.base.z.helper;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.liuniukeji.singemall.R;
import com.liuniukeji.singemall.util.utilcode.FileUtils;
import com.liuniukeji.singemall.util.utilcode.ToastUtils;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigDialog;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.DialogParams;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description 图片选择帮助类
 * @Author zhugefubin tel:553629767;
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2018-08-10 14:53
 * @CreateBy Android Studio
 * @ModifiedBy
 */

public class ImageSelectHelper {
    static ImageSelectHelper instance = new ImageSelectHelper();
    private AppCompatActivity mActivity;
    private int markWidget = 0;

    /**
     * 获取单例
     *
     * @return
     */
    private static ImageSelectHelper getInstance() {
        return instance;
    }

    //绑定activity
    public static ImageSelectHelper with(AppCompatActivity activity) {
        getInstance().mActivity = activity;
        return getInstance();
    }

    //结果回调接口
    public interface OnResult {
        /**
         * 选择图片
         *
         * @param path
         */
        void onSelect(String path, int mark);

        /**
         * 未选择
         */
        void onSelectNone(int mark);
    }

    /**
     * 结果处理
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     * @param result      结果回调
     */
    public static void withActivityResult(int requestCode, int resultCode, Intent data, OnResult result) {
        switch (requestCode) {
            case REQUEST_CODE_PHOTO:
                if (resultCode == RESULT_OK) {
                    String imagePath = FileUtils.getFilePathFromContentUri(data.getData(), getInstance().mActivity.getContentResolver());
                    result.onSelect(imagePath, getInstance().markWidget);
                } else {
                    result.onSelectNone(getInstance().markWidget);
                }
                break;
            case REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    File fileTemp = new File(
                            getInstance().mActivity.getExternalCacheDir() + "/img.jpg");
                    String imagePath = fileTemp.getAbsolutePath();
                    result.onSelect(imagePath, getInstance().markWidget);
                } else {
                    result.onSelectNone(getInstance().markWidget);
                }
                break;
        }
    }

    /**
     * @param title      显示的标题
     * @param markWidget 标识码,用于区分多个图片控件在一个页面选择时，根据返回值里的mark判断需要给那种哪张图片控件赋值
     */
    public void seletcImage(String title, int markWidget) {
        getInstance().markWidget = markWidget;
        final String[] items = {"拍照", "从相册选择"};
        new CircleDialog.Builder(getInstance().mActivity)
                .configDialog(new ConfigDialog() {
                    @Override
                    public void onConfig(DialogParams params) {
                        //增加弹出动画
                        params.animStyle = R.style.dialogWindowAnim;
                    }
                })
                .setTitle(title)
                .setTitleColor(Color.BLACK)
                .setItems(items, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int
                            position, long id) {
                        if (position == 0) {
                            requestCemera();
                        } else if (position == 1) {
                            Intent intent_photo = new Intent(Intent.ACTION_PICK, null);
                            intent_photo
                                    .setDataAndType(
                                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                            "image/*");
                            getInstance().mActivity.startActivityForResult(intent_photo, REQUEST_CODE_PHOTO);
                        }
                    }
                })
                .setNegative("取消", null)
                .configNegative(new ConfigButton() {
                    @Override
                    public void onConfig(ButtonParams params) {
                        //取消按钮字体颜色
                        params.textColor = Color.RED;
                    }
                })
                .show();
    }

    private static final int REQUEST_CODE_PHOTO = 0x1;    //拍照的标识符
    private static final int REQUEST_CODE_CAMERA = 0x2;   //相机的标识符

    private void requestCemera() {
        if (PermissionsUtil.hasPermission(getInstance().mActivity, Manifest.permission.CAMERA)) {
            Intent intent_pat = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent_pat.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(getInstance().mActivity.getExternalCacheDir(), "img.jpg")));
            getInstance().mActivity.startActivityForResult(intent_pat, REQUEST_CODE_CAMERA);
        } else {
            ToastUtils.showShort("请打开相机权限");
            PermissionsUtil.requestPermission(getInstance().mActivity, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                    Toast.makeText(getInstance().mActivity, "用户授权了访问摄像头", Toast.LENGTH_LONG).show();
                }

                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    Toast.makeText(getInstance().mActivity, "用户拒绝了访问摄像头", Toast.LENGTH_LONG).show();
                }
            }, Manifest.permission.CAMERA);
        }
    }


}
