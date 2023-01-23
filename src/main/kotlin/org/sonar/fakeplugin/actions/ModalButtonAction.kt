package org.sonar.fakeplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.sonar.fakeplugin.ui.NotificationModal

class ModalButtonAction: AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        NotificationModal("Hello World").show()
    }

}