package com.nine.ninenews.data.model

import com.google.gson.annotations.SerializedName

data class NewsArticle(
    @SerializedName("assetType")
    val assetType: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("authors")
    val authors: List<Any?>,

    @SerializedName("categories")
    val categories: List<Category>?,

    @SerializedName("displayName")
    val displayName: String?,

    @SerializedName("id")
    val id: Long?,

    @SerializedName("lastModified")
    val lastModified: Long?,

    @SerializedName("onTime")
    val onTime: Long?,

    @SerializedName("relatedAssets")
    val relatedAssets: List<Asset>?,

    @SerializedName("relatedImages")
    val relatedImages: List<AssetRelatedImage>?,

    @SerializedName("sponsored")
    val sponsored: Boolean?,

    @SerializedName("assets")
    val assets: List<Asset>,

    @SerializedName("timeStamp")
    val timeStamp: Long?
)

data class Asset(
    @SerializedName("acceptComments")
    val acceptComments: Boolean,

    @SerializedName("assetType")
    val assetType: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("authors")
    val authors: List<Author>,

    @SerializedName("body")
    val body: String,

    @SerializedName("byLine")
    val byLine: String,

    @SerializedName("categories")
    val categories: List<Category>,

    @SerializedName("companies")
    val companies: List<Company>,

    @SerializedName("headline")
    val headline: String,

    @SerializedName("id")
    val id: Long,

    @SerializedName("indexHeadline")
    val indexHeadline: String,

    @SerializedName("lastModified")
    val lastModified: Long,

    @SerializedName("legalStatus")
    val legalStatus: String,

    @SerializedName("live")
    val live: Boolean,

    @SerializedName("liveArticleSummary")
    val liveArticleSummary: String,

    @SerializedName("numberOfComments")
    val numberOfComments: Long,

    @SerializedName("relatedAssets")
    val relatedAssets: List<Asset>,

    @SerializedName("relatedImages")
    val relatedImages: List<AssetRelatedImage>,

    @SerializedName("relatedPosts")
    val relatedPosts: List<Asset>,

    @SerializedName("signPost")
    val signPost: String? = null,

    @SerializedName("sources")
    val sources: List<Source>,

    @SerializedName("sponsored")
    val sponsored: Boolean,

    @SerializedName("theAbstract")
    val theAbstract: String,

    @SerializedName("tabletHeadline")
    val tabletHeadline: String,

    @SerializedName("overrides")
    val overrides: Overrides,

    @SerializedName("extendedAbstract")
    val extendedAbstract: String,

    @SerializedName("timeStamp")
    val timeStamp: Long
)

data class Author(
    @SerializedName("email")
    val email: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("relatedAssets")
    val relatedAssets: List<Asset>,

    @SerializedName("relatedImages")
    val relatedImages: List<AuthorRelatedImage>,

    @SerializedName("title")
    val title: String
)

data class AuthorRelatedImage(
    @SerializedName("assetType")
    val assetType: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("authors")
    val authors: List<Author>,

    @SerializedName("brands")
    val brands: List<Any?>,

    @SerializedName("categories")
    val categories: List<Category>,

    @SerializedName("description")
    val description: String,

    @SerializedName("height")
    val height: Long,

    @SerializedName("id")
    val id: Long,

    @SerializedName("lastModified")
    val lastModified: Long,

    @SerializedName("photographer")
    val photographer: String,

    @SerializedName("sponsored")
    val sponsored: Boolean,

    @SerializedName("type")
    val type: String,

    @SerializedName("width")
    val width: Long,

    @SerializedName("timeStamp")
    val timeStamp: Long
)

data class Category(
    @SerializedName("name")
    val name: String,

    @SerializedName("orderNum")
    val orderNum: Long,

    @SerializedName("sectionPath")
    val sectionPath: String
)

data class Company(
    @SerializedName("abbreviatedName")
    val abbreviatedName: String,

    @SerializedName("companyCode")
    val companyCode: String,

    @SerializedName("companyName")
    val companyName: String,

    @SerializedName("companyNumber")
    val companyNumber: String,

    @SerializedName("exchange")
    val exchange: String,

    @SerializedName("id")
    val id: Long
)

data class Overrides(
    @SerializedName("overrideAbstract")
    val overrideAbstract: String,

    @SerializedName("overrideHeadline")
    val overrideHeadline: String
)

data class AssetRelatedImage(
    @SerializedName("assetType")
    val assetType: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("authors")
    val authors: List<Author>,

    @SerializedName("brands")
    val brands: List<Any>,

    @SerializedName("categories")
    val categories: List<Category>,

    @SerializedName("description")
    val description: String,

    @SerializedName("height")
    val height: Long,

    @SerializedName("id")
    val id: Long,

    @SerializedName("lastModified")
    val lastModified: Long,

    @SerializedName("photographer")
    val photographer: String,

    @SerializedName("sponsored")
    val sponsored: Boolean,

    @SerializedName("type")
    val type: String,

    @SerializedName("width")
    val width: Long,

    @SerializedName("xLarge@2x")
    val xLarge2X: String? = null,

    @SerializedName("large@2x")
    val large2X: String? = null,

    @SerializedName("timeStamp")
    val timeStamp: Long,

    @SerializedName("xLarge")
    val xLarge: String? = null,

    @SerializedName("large")
    val large: String? = null,

    @SerializedName("thumbnail@2x")
    val thumbnail2X: String? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null
)

data class Source(
    @SerializedName("tagId")
    val tagID: String
)