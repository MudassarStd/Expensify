package com.std.composeexpensetracker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Category(
    @PrimaryKey
    val name: String,
    val icon: String = ""
): Serializable