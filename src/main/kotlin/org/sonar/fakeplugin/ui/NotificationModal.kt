package org.sonar.fakeplugin.ui

import com.intellij.openapi.ui.DialogWrapper
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*


class NotificationModal(private val textDescription: String): DialogWrapper(true) {

    init {
        super.setTitle("Notification Modal")
        super.init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        val text = JLabel(textDescription, SwingConstants.CENTER)
        text.preferredSize = Dimension(200, 100)

        panel.add(text, BorderLayout.CENTER)

        return panel
    }

    override fun createActions(): Array<Action> {
        return arrayOf(okAction)
    }

}