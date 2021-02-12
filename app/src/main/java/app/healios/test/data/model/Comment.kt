package app.healios.test.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "comments",
    primaryKeys = ["id", "postId"]
)
data class Comment(
    @SerializedName("id")
    val id: Long,
    @SerializedName("postId")
    val postId: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String
)
