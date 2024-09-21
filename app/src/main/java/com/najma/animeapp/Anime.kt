package com.najma.animeapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val name: String?,
    val rating: String?,
    val genre: String?,
    val description: String?,
    val sinopsis: String?,
    val photo: Int?
) : Parcelable
