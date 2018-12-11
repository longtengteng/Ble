package com.clj.blesample.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.clj.blesample.R;
import com.clj.blesample.net.UrlUtils;

/**
 * Copyright (c) 山东六牛网络科技有限公司 https://liuniukeji.com
 *
 * @Description
 * @Author 与天同行的观测者
 * @Copyright Copyright (c) 山东六牛网络科技有限公司 保留所有版权(https://www.liuniukeji.com)
 * @Date 2017/12/11 0011 14:18
 */

public class ImageLoader {

    public static void loadHead(final Context context, ImageView view, String url) {
        try {
            if (!TextUtils.isEmpty(url) && !url.contains("http")) {
                url = UrlUtils.APIHTTP + url;
            }
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.mipmap.default_avatar)
                    .error(R.mipmap.default_avatar)
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            view.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImage(Context context, ImageView view, String url) {
        try {
            if (!TextUtils.isEmpty(url) && !url.contains("http")) {
                url = UrlUtils.APIHTTP + url;
            }
            Glide.with(context)
                    .load(url)
                    .placeholder(R.mipmap.default_image)
                    .error(R.mipmap.default_image)
                    .into(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadHeadLocal(final Context context, ImageView view, String url) {
        try {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .centerCrop()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.mipmap.default_avatar)
                    .error(R.mipmap.default_avatar)
                    .into(new BitmapImageViewTarget(view) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            view.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageLocal(final Context context, ImageView view, String url) {
        try {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.mipmap.default_image)
                    .error(R.mipmap.default_image)
                    .into(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
