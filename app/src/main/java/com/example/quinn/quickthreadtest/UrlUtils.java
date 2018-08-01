package com.example.quinn.quickthreadtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UrlUtils {
	/**
	 * 打开指定链接
	 * @param context 上下文
	 * @param url URL字符串
	 */
	public static void open(Context context, String url) {
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

	/**
	 * 在浏览器中打开
	 * @param context 上下文
	 * @param url URL字符串
	 */
	public static void openInBrowser(Context context, String url) {
		Intent i = new Intent();
		i.setAction(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));

		String defaultDrowser = getDefaultBrowser(context);
		Log.e("TAG","default = " + defaultDrowser);
		if (!TextUtils.isEmpty(defaultDrowser)) {
			i.setPackage(defaultDrowser);
		}

		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}

	private static String getDefaultBrowser(Context context) {
		Set<String> packages = getInstalledBrowserPackages(context);
		if (packages.size() > 0) {
			if (packages.contains("com.android.browser")) {
				return "com.android.browser";
			}

			return packages.iterator().next();
		} else {
			return null;
		}
	}

	private static Set<String> getInstalledBrowserPackages(Context context) {
		HashSet<String> packages = new HashSet<>();

		try {
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.addCategory(Intent.CATEGORY_BROWSABLE);
			i.setDataAndType(Uri.parse("http://"), null);

			// 找出手机当前安装的所有浏览器程序
			@SuppressLint("WrongConstant") List<ResolveInfo> resolveInfos = context.getPackageManager()
					.queryIntentActivities(i, PackageManager.GET_INTENT_FILTERS);

			if (null != resolveInfos) {
				for (ResolveInfo resolveInfo: resolveInfos) {
					if ((null != resolveInfo.activityInfo)
							&& (!TextUtils.isEmpty(resolveInfo.activityInfo.packageName))) {
						packages.add(resolveInfo.activityInfo.packageName);
					}
				}
			}
		} catch (Throwable t) {
			//
		}

		return packages;
	}
}
