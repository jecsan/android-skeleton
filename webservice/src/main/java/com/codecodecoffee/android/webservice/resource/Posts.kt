package com.codecodecoffee.android.webservice.resource

import io.ktor.resources.*
import kotlinx.serialization.Serializable


//https://ktor.io/docs/type-safe-request.html
@Serializable
@Resource("/posts")
class Posts {
    @Serializable
    @Resource("new")
    class New(val parent: Posts = Posts())

    @Serializable
    @Resource("{id}")
    class Id(val parent: Posts = Posts(), val id: Long)

}