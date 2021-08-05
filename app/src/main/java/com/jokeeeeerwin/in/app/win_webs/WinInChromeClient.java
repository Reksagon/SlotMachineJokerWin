package com.jokeeeeerwin.in.app.win_webs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.core.content.FileProvider;

import com.jokeeeeerwin.in.app.win_const.WinInConst;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WinInChromeClient extends WebChromeClient {
    Activity activity;
    ProgressBar progressBar;

    public WinInChromeClient(Activity activity, ProgressBar progressBar) {
        this.activity = activity;
        this.progressBar = progressBar;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        progressBar.setProgress(newProgress);
        if(newProgress < 100 && progressBar.getVisibility() == progressBar.GONE)
            progressBar.setVisibility(progressBar.VISIBLE);
        if(newProgress == 100)
            progressBar.setVisibility(ProgressBar.GONE);
    }

    private File WinInImage() throws IOException {
        String winDate = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String winImg = "WinIn" + winDate + "_";
        File winFileToDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(winImg, ".jpg", winFileToDir);
    }

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        Dexter.withContext(activity).withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                                  @Override
                                  public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) { }
                                  @Override
                                  public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) { }
                              }).check();
                        WinInConst.winCallBac = filePathCallback;
        Intent winIntentOne = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File winFile = null;
        try {
            winFile = WinInImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(winFile != null)
        {
            Uri winForFile = FileProvider.getUriForFile(activity, activity.getApplication().getPackageName() + ".provider", winFile);
            WinInConst.winUrl = winForFile;
            winIntentOne.putExtra(MediaStore.EXTRA_OUTPUT, winForFile);
            winIntentOne.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Intent winIntentTwo = new Intent(Intent.ACTION_GET_CONTENT);
            winIntentTwo.addCategory(Intent.CATEGORY_OPENABLE);
            winIntentTwo.setType("image/*");

            Intent[] winIntents = {winIntentTwo};

            Intent winIntentChooser = new Intent(Intent.ACTION_CHOOSER);
            winIntentChooser.putExtra(Intent.EXTRA_INTENT, winIntentOne);
            winIntentChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, winIntents);

            activity.startActivityForResult(winIntentChooser, WinInConst.reqCode);

            return true;
        }
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
    }
}

