package com.clj.blesample.base.z.helper.rxtools;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.liuniukeji.singemall.R;
import com.liuniukeji.singemall.util.ImageLoader;
import com.liuniukeji.singemall.util.utilcode.FileUtils;

/**
 * @author vondear
 * @date 2016/1/24
 * 图像工具类
 */

public class RxImageTool {
    /**
     * 显示大图
     *
     * @param context
     * @param uri     图片的Uri
     */
    public static void showBigImageView(Context context, Uri uri) {
        final RxDialog rxDialog = new RxDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.big_image, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialog.cancel();
            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.page_item);
//        imageView.setImageURI(uri);
        if (FileUtils.isFile(uri.getPath())) {
            ImageLoader.loadImageLocal(context, imageView, uri.getPath());
        } else {
            ImageLoader.loadImage(context, imageView, uri.getPath());
        }
        rxDialog.setContentView(view);
        rxDialog.show();
        rxDialog.setFullScreen();
    }

}
