package news.navigation

import kotlinx.parcelize.Parcelize
import android.os.Parcelable


sealed class Config : Parcelable {
    @Parcelize
    object ArticleList : Config()
    
    @Parcelize
    object CustomColumn : Config()
    
    @Parcelize
    object RememberUpdatedState : Config()

    @Parcelize
    object Home : Config()
}
