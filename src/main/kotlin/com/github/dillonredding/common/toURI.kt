package com.github.dillonredding.common

import java.net.URI

fun String.toURI(): URI = URI.create(this)
