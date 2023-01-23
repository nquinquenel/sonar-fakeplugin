package org.sonar.fakeplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.sonar.fakeplugin.notification.NotificationButton

class NotificationButtonAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        e.project?.let { NotificationButton.notifyText(it, "Hello World") }
    }

}