package org.sonar.fakeplugin.toolWindow

import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import org.sonar.fakeplugin.listener.LiveFindingsListener
import java.awt.FlowLayout

class RealTimeToolWindow(project: Project) : Disposable {

    private val realFindingsText = "Number of findings in real time: "
    private val twContent = JBPanel<JBPanel<*>>(FlowLayout())
    private val twLabelRealFindings = JBLabel()
    private val twLabelAmount = JBLabel()
    private val busConnection = project.messageBus.connect(project)

    init {
        initContent()
        manageBusConnection()
    }

    override fun dispose() {
        busConnection.disconnect()
    }

    fun getContent(): JBPanel<JBPanel<*>> {
        return twContent
    }

    private fun initContent() {
        twLabelRealFindings.text = realFindingsText
        twLabelAmount.text = "0"

        twContent.add(twLabelRealFindings)
        twContent.add(twLabelAmount)
    }

    private fun manageBusConnection() {
        busConnection.subscribe(LiveFindingsListener.LIVE_FINDINGS_TOPIC, LiveFindingsListener {
            twLabelAmount.text = it.toString()
        })
    }

}