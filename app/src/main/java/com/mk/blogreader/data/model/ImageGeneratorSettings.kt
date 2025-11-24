package com.mk.blogreader.data.model

data class ImageGeneratorSettings(
    val default_image_id: Int,
    val enabled: Boolean,
    val font: String,
    val template: String
)