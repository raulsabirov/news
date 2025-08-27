package news.Tabs

import kotlinx.serialization.Serializable


@Serializable
sealed interface ConfigTabs  {
    @Serializable
    data object ArticleList : ConfigTabs
    
    @Serializable
    data object CustomColumn : ConfigTabs
    
    @Serializable
    data object RememberUpdatedState : ConfigTabs

}
