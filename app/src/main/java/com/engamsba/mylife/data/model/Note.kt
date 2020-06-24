package com.engamsba.mylife.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "notes"
 )
data class Note(

    @ColumnInfo(name = "title_note")
    var titleNote: String?,

    @ColumnInfo(name = "desc_note")
    var descNote: String? ,

    @ColumnInfo(name = "creation_date")
    val creationDate: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "categories")
    val categories: String


) {
    @PrimaryKey(autoGenerate = true)
    var id: Long  = 0

}