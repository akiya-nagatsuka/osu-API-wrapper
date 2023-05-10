package com.github.nagatsukaakiya.osuapi.auth

import platform.UIKit.UIApplication
import platform.Foundation.NSURL

actual fun webAuthenticate(url: String) {
    UIApplication.sharedApplication.openURL(NSURL(string = url))
}
