package com.example.navigation.network.model


import com.example.navigation.storage.db.models.Backdrops
import com.example.navigation.storage.db.models.Poster
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val posters: List<Poster>
) : BaseResponse() {
    data class Poster(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double,
        @SerializedName("file_path")
        val filePath: String,
        val height: Int,
        @SerializedName("iso_639_1")
        val iso6391: String?,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int,
        val width: Int
    )

    data class Backdrop(
        @SerializedName("aspect_ratio")
        val aspectRatio: Double,
        @SerializedName("file_path")
        val filePath: String
    )
}

fun ImageResponse?.asBackdropDataModel(): Array<Backdrops> {
    if (this == null)
        return emptyArray()
    return backdrops.map {
        Backdrops(mediaId = this.id, aspectRatio = it.aspectRatio, filePath = it.filePath)
    }.toTypedArray()
}


fun ImageResponse?.asPosterDataModel(): Array<Poster> {
    if (this == null)
        return emptyArray()
    return backdrops.map {
        Poster(mediaId = this.id, aspectRatio = it.aspectRatio, filePath = it.filePath)
    }.toTypedArray()
}