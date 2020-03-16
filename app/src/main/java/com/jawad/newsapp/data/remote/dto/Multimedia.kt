package com.jawad.newsapp.data.remote.dto


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Defined all the variables as @var, because it might happen that backend send some values as
 * null
 *
 * @Parcelize is from kotlin experimental but it is stable, and we use it for Parcelable
 * impersonation
 *
 * Convert your json to kotlin data class in easy simple way, by using
 * @(JSON To Kotlin Class) plugin in android studio, you can install the plugin as any other
 * plugin and then use it, for more details check here:
 * https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-
 */

@Parcelize
@Entity(tableName = "multimedia")
data class Multimedia(

    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("caption")
    var caption: String?, // Students leaving Glacier Peak High School in Snohomish, Wash., on Thursday. Gov. Jay Inslee mandated the closing of schools in the Seattle area for six weeks.
   
    @field:SerializedName("copyright")
    var copyright: String?, // Grant Hindsley for The New York Times
   
    @field:SerializedName("format")
    var format: String?, // Normal
   
    @field:SerializedName("height")
    var height: Int?, // 127
   
    @field:SerializedName("subtype")
    var subtype: String?, // photo
   
    @field:SerializedName("type")
    var type: String?, // image

    @PrimaryKey
    @field:SerializedName("url")
    var url: String?, // https://static01.nyt.com/images/2020/03/12/us/12virus-control01/12virus-control01-articleInline.jpg
   
    @field:SerializedName("width")
    var width: Int? // 190
) : Parcelable