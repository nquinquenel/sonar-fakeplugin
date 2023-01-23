package org.sonar.fakeplugin.ui

import com.intellij.openapi.ui.DialogWrapper
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.Action
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants


class NotificationModal(private val textDescription: String) : DialogWrapper(true) {

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