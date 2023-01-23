package org.sonar.fakeplugin.ui

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.Action
import javax.swing.JComponent
import javax.swing.SwingConstants


class NotificationModal(private val textDescription: String) : DialogWrapper(true) {

    init {
        super.setTitle("Notification Modal")
        super.init()
    }

    override fun createCenterPanel(): JComponent {
        val panel = JBPanel<JBPanel<*>>(BorderLayout())
        val text = JBLabel(textDescription, SwingConstants.CENTER)
        text.preferredSize = Dimension(200, 100)

        panel.add(text, BorderLayout.CENTER)

        return panel
    }

    override fun createActions(): Array<Action> {
        return arrayOf(okAction)
    }

}