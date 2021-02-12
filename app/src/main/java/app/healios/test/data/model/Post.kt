package app.healios.test.data.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "posts",
    primaryKeys = ["id", "userId"]
)
@Parcelize
data class Post(
    @SerializedName("id")
    val id: Long,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
) : Parcelable
