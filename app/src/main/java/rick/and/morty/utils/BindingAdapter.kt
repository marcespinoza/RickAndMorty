package rick.and.morty.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadimage")
fun bindingImage(characterImage: ImageView, imageUri: String){
    Glide.with(characterImage.context)
        .load(imageUri)
        .circleCrop()
        .into(characterImage)
}