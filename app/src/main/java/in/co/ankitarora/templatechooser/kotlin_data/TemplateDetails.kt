package `in`.co.ankitarora.templatechooser.kotlin_data

import com.google.gson.annotations.SerializedName

data class TemplateDetails(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("meta") val meta: TemplateMeta,
    @SerializedName("screenshots") val screenshotLinks: ScreenshotLinks,
    @SerializedName("variations") val variations: List<Variation>
)

data class Variation(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("icon-type") val iconType: String,
    @SerializedName("screenshots") val screenshotLinks: ScreenshotLinks
) {
    fun getIconColor(): String {
        return if (icon.length == 4)
            icon + icon.substring(1)
        else
            icon
    }
}

data class ScreenshotLinks(@SerializedName("iphone") val iphone: String)

data class TemplateMeta(@SerializedName("color") val color: String)
