package pl.sirox.nxrtchat.regex

import java.util.regex.Pattern

object Regexes {

    val MENTION_REGEX = Pattern.compile("@[a-zA-Z0-9_]{3,16}")

}