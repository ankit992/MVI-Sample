package `in`.co.ankitarora.templatechooser

import androidx.test.espresso.idling.net.UriIdlingResource

object UriIdlingResourceSingleton {
    val uriIdlingResource = UriIdlingResource("Network", 500)
}