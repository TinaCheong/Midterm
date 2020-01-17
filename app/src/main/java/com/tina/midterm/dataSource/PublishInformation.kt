package com.tina.midterm.dataSource

data class PublishInformation(
    val author: Author,
    val title: String,
    val content: String,
    val createdTime: Long,
    val id: String,
    val tag: String
)