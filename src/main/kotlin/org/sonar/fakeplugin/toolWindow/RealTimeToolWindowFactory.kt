package org.sonar.fakeplugin.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class RealTimeToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val realTimeToolWindow = RealTimeToolWindow(project)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content = contentFactory.createContent(realTimeToolWindow.getContent(), "", false)
        content.setDisposer(realTimeToolWindow)
        toolWindow.contentManager.addContent(content)
    }

}