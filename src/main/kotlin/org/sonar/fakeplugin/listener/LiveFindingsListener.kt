package org.sonar.fakeplugin.listener

import com.intellij.util.messages.Topic

fun interface LiveFindingsListener {

    companion object {
        val LIVE_FINDINGS_TOPIC: Topic<LiveFindingsListener> = Topic.create("Live findings", LiveFindingsListener::class.java)
    }

    fun update(amount: Int)

}