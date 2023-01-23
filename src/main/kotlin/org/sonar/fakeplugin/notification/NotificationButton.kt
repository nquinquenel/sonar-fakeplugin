package org.sonar.fakeplugin.notification

import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

class NotificationButton {

    companion object {
        private val GROUP: NotificationGroup = NotificationGroupManager.getInstance().getNotificationGroup("Hello World Notification")

        fun notifyText(project: Project, content: String) {
            GROUP.createNotification(content, NotificationType.INFORMATION).notify(project)
        }
    }

}