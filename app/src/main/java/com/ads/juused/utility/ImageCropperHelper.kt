package com.ads.juused.utility

import android.app.Activity
import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ads.juused.R
import com.yalantis.ucrop.UCrop
import java.io.File


private fun cropImageUi(activity: Activity, compressionQuality: Int = 80): UCrop.Options {
    val options = UCrop.Options()
    options.setCompressionQuality(compressionQuality)

    options.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark))
    options.setStatusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark))
    options.setToolbarWidgetColor(ContextCompat.getColor(activity, R.color.colorWhite))

    return options
}

private fun queryName(resolver: ContentResolver, uri: Uri): String {
    val returnCursor = resolver.query(uri, null, null, null, null)!!
    val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    returnCursor.moveToFirst()
    val name = returnCursor.getString(nameIndex)
    returnCursor.close()
    return name
}

private fun Uri.getDestinationUri(activity: Activity) =
    Uri.fromFile(
        File(
            activity.cacheDir,
            queryName(
                activity.contentResolver,
                this
            )
        )
    )


fun Activity.createCropper(
    sourceUri: Uri,
    quality: Int = 80,
    aspectX: Float = 16f,
    aspectY: Float = 9f
): UCrop {

    val destinationUri = sourceUri.getDestinationUri(this)

    cropImageUi(this, compressionQuality = quality).apply {
        withAspectRatio(aspectX, aspectY)

    }.also {
        return UCrop.of(sourceUri, destinationUri)
            .withOptions(it)
    }
}

fun Fragment.openCropper(
    sourceUri: Uri,
    quality: Int = 80,
    aspectX: Float = 16f,
    aspectY: Float = 9f
) {
    requireActivity().createCropper(
        sourceUri = sourceUri,
        quality = quality,
        aspectX = aspectX,
        aspectY = aspectY
    ).start(requireContext(), this)
}


fun Activity.openCropper(
    sourceUri: Uri,
    quality: Int = 80,
    aspectX: Float = 16f,
    aspectY: Float = 9f
) {
    createCropper(
        sourceUri = sourceUri,
        quality = quality,
        aspectX = aspectX,
        aspectY = aspectY
    ).start(this)
}

