package org.sonar.fakeplugin.notification

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

class NotificationButton {

    companion object {
        fun notifyText(project: Project, content: String) {
            NotificationGroupManager.getInstance().getNotificationGroup("Hello World Notification")
                .createNotification(content, NotificationType.INFORMATION)
                .notify(project)
        }
    }

}