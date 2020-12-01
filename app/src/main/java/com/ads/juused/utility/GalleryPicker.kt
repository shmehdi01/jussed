package com.ads.juused.utility

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.ads.juused.BuildConfig
import com.ads.juused.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

/**
 *  Created by Syed Hussain Mehdi
 */

class GalleryPicker {
    private var mActivity: Activity
    private var mFragment: Fragment? = null
    private var galleryPickerListener: GalleryPickerListener? = null
    private var mSelectedImage: Uri? = null
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var media =
        Media.IMAGE
    private var shouldRepoenDialog = false
    private var shouldOpenCamera = false
    private var shouldOpenGallery = false

    private constructor(activity: Activity, fragment: Fragment) {
        mFragment = fragment
        mActivity = activity
    }

    private constructor(activity: Activity) {
        mActivity = activity
    }

    fun setListener(galleryPickerListener: GalleryPickerListener?): GalleryPicker {
        this.galleryPickerListener = galleryPickerListener
        return this
    }

    fun setMedia(media: Media): GalleryPicker {
        this.media = media
        return this
    }

    fun showDialog(): GalleryPicker {
        val dialogView =
            LayoutInflater.from(mActivity).inflate(R.layout.dialog_image_chooser, null)
        bottomSheetDialog = BottomSheetDialog(mActivity)
        bottomSheetDialog!!.setContentView(dialogView)
        bottomSheetDialog!!.show()
        initViews(dialogView)
        return this
    }

    fun fetch(requestCode: Int, data: Intent?) {
        if (data == null) return
        when (requestCode) {
            PICK_GALLERY -> mSelectedImage = data.data
            CAPTURE_IMAGE -> if (media == Media.IMAGE) {
                try {
                    val bitmap = data.extras?.get("data") as Bitmap?
                    galleryPickerListener!!.onBitmap(bitmap)
                    mSelectedImage = getImageUri(mActivity, bitmap)

                } catch (e: Exception) {
                    Log.e(TAG, "fetch: empty intent data")
                }
            } else mSelectedImage = data.data
        }
        if (mSelectedImage != null) galleryPickerListener!!.onMediaSelected(
            getImagePath(
                mActivity,
                mSelectedImage
            ), mSelectedImage, media == Media.IMAGE
        )
    }

    fun onResultPermission(requestCode: Int, grantResults: IntArray) {
        if (requestCode == CAMERA_PERMISSION_CODE || requestCode == GALLERY_PERMISSION_CODE) {
            if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (shouldRepoenDialog) {
                        showDialog()
                        shouldRepoenDialog = false
                    } else if (shouldOpenCamera) {
                        openCamera()
                        shouldOpenCamera = false
                    } else if (shouldOpenGallery) {
                        openGallery()
                        shouldOpenGallery = false
                    }
                }
            }
        }
    }

    fun openCamera(): GalleryPicker {
        if (ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (BuildConfig.DEBUG) Log.e(
                TAG,
                "Camera & Storage Permission Required"
            )
            if (mFragment != null) {
                mFragment!!.requestPermissions(
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), CAMERA_PERMISSION_CODE
                )
                shouldOpenCamera = true
                return this
            }
            ActivityCompat.requestPermissions(
                mActivity, arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                CAMERA_PERMISSION_CODE
            )
            shouldOpenCamera = true
            return this
        }
        fireIntent(
            Option.CAMERA,
            CAPTURE_IMAGE
        )
        return this
    }

    fun openGallery(): GalleryPicker {
        if (ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (BuildConfig.DEBUG) Log.e(
                TAG,
                "Storage Permission Required"
            )
            if (mFragment != null) {
                mFragment!!.requestPermissions(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), GALLERY_PERMISSION_CODE
                )
                shouldOpenGallery = true
                return this
            }
            ActivityCompat.requestPermissions(
                mActivity, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), GALLERY_PERMISSION_CODE
            )
            shouldOpenGallery = true
            return this
        }
        fireIntent(
            Option.GALLERY,
            PICK_GALLERY
        )
        return this
    }

    private fun initViews(view: View) {
        val imageCamera =
            view.findViewById<View>(R.id.layout_camera)
        val imageGallery =
            view.findViewById<View>(R.id.layout_gallery)
        imageCamera.setOnClickListener { v: View? ->
            bottomSheetDialog!!.dismiss()
            if (ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (BuildConfig.DEBUG) Log.e(
                    TAG,
                    "Camera & Storage Permission Required"
                )
                if (mFragment != null) {
                    mFragment!!.requestPermissions(
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ), CAMERA_PERMISSION_CODE
                    )
                    shouldRepoenDialog = true
                    return@setOnClickListener
                }
                ActivityCompat.requestPermissions(
                    mActivity, arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), CAMERA_PERMISSION_CODE
                )
                shouldRepoenDialog = true
                return@setOnClickListener
            }
            fireIntent(
                Option.CAMERA,
                CAPTURE_IMAGE
            )
        }
        imageGallery.setOnClickListener { v: View? ->
            bottomSheetDialog!!.dismiss()
            if (ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (BuildConfig.DEBUG) Log.e(
                    TAG,
                    "Read Storage Permission Required"
                )
                if (mFragment != null) {
                    mFragment!!.requestPermissions(
                        arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ), GALLERY_PERMISSION_CODE
                    )
                    shouldRepoenDialog = true
                    return@setOnClickListener
                }
                ActivityCompat.requestPermissions(
                    mActivity, arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), GALLERY_PERMISSION_CODE
                )
                shouldRepoenDialog = true
                return@setOnClickListener
            }
            fireIntent(
                Option.GALLERY,
                PICK_GALLERY
            )
        }
    }

    private fun fireIntent(
        option: Option,
        requestCode: Int
    ) {
        val intent = Intent()
        if (option == Option.GALLERY) {
            intent.action = Intent.ACTION_PICK
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = media.media
        } else if (option == Option.CAMERA) {
            intent.action =
                if (media == Media.IMAGE) MediaStore.ACTION_IMAGE_CAPTURE else MediaStore.ACTION_VIDEO_CAPTURE
        }
        try {
            if (mFragment != null) mFragment!!.startActivityForResult(
                intent,
                requestCode
            ) else mActivity.startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            Log.d(TAG, "fireIntent: Activity not found")
        }
    }

    private enum class Option {
        CAMERA, GALLERY
    }

    enum class Media(val media: String) {
        IMAGE("image/jpg"), VIDEO("video/*"), BOTH("image/*, video.*");

    }

    interface GalleryPickerListener {
        fun onMediaSelected(
            imagePath: String?,
            uri: Uri?,
            isImage: Boolean
        )

        fun onBitmap(bitmap: Bitmap?) {}
    }

    companion object {
        const val CAPTURE_IMAGE = 100
        const val PICK_GALLERY = 200
        const val CAMERA_PERMISSION_CODE = 1000
        const val GALLERY_PERMISSION_CODE = 2000
        private const val TAG = "GalleryPicker"
        fun with(activity: Activity, fragment: Fragment): GalleryPicker {
            return GalleryPicker(activity, fragment)
        }

        fun with(activity: Activity): GalleryPicker {
            return GalleryPicker(activity)
        }

        private fun getImagePath(activity: Activity, uri: Uri?): String? {
            var path: String? = null
            try {
                if (uri != null) {
                    val projection =
                        arrayOf(MediaStore.Images.Media.DATA)
                    val cursor =
                        activity.contentResolver.query(uri, projection, null, null, null)
                            ?: return null
                    val column_index =
                        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    cursor.moveToFirst()
                    path = cursor.getString(column_index)
                    cursor.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return path
        }

        private fun getImageUri(
            mContext: Context,
            photo: Bitmap?
        ): Uri {
            val bytes = ByteArrayOutputStream()
            photo!!.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path =
                MediaStore.Images.Media.insertImage(mContext.contentResolver, photo, "", null)
            return Uri.parse(path)
        }

        @Throws(IOException::class)
        private fun rotateImageIfRequired(
            context: Context,
            img: Bitmap,
            selectedImage: Uri
        ): Bitmap {
            val input =
                context.contentResolver.openInputStream(selectedImage)
            val ei: ExifInterface
            ei =
                if (Build.VERSION.SDK_INT > 23) ExifInterface(input!!) else ExifInterface(
                    selectedImage.path!!
                )
            val orientation = ei.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            return when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(
                    img,
                    90
                )
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(
                    img,
                    180
                )
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(
                    img,
                    270
                )
                else -> img
            }
        }

        private fun rotateImage(img: Bitmap, degree: Int): Bitmap {
            val matrix = Matrix()
            matrix.postRotate(degree.toFloat())
            val rotatedImg =
                Bitmap.createBitmap(img, 0, 0, img.width, img.height, matrix, true)
            img.recycle()
            return rotatedImg
        }
    }
}