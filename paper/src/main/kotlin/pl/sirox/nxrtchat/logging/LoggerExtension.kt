package pl.sirox.nxrtchat.logging

import org.slf4j.Logger

inline fun <reified T> LoggerFactory.logger(): Logger {
    return get(T::class.java)
}

inline fun <reified T> LoggerFactory.logger(name: String): Logger {
    return get(name)
}