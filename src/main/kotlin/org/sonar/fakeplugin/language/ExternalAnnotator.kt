package org.sonar.fakeplugin.language

import com.intellij.lang.Language
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.ExternalAnnotator
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import org.sonar.fakeplugin.analysis.AnalyzedElement
import org.sonar.fakeplugin.analysis.JavaAnalyzer
import org.sonar.fakeplugin.analysis.KotlinAnalyser
import org.sonar.fakeplugin.listener.LiveFindingsListener

class ExternalAnnotator : ExternalAnnotator<PsiFile, List<AnalyzedElement>>() {

    private var publisher : LiveFindingsListener? = null

    override fun collectInformation(file: PsiFile, editor: Editor, hasErrors: Boolean): PsiFile? {
        if (publisher == null) publisher = file.project.messageBus.syncPublisher(LiveFindingsListener.LIVE_FINDINGS_TOPIC)

        return super.collectInformation(file, editor, hasErrors)
    }

    override fun collectInformation(file: PsiFile): PsiFile = file

    override fun doAnnotate(file: PsiFile?): List<AnalyzedElement>? {
        if (file == null) return null

        if (file.language == Language.findLanguageByID("kotlin")) {
            return KotlinAnalyser.parseFile(file)
        } else if (file.language == Language.findLanguageByID("JAVA")) {
            return JavaAnalyzer.parseFile(file)
        }

        return null
    }

    override fun apply(file: PsiFile, annotationResult: List<AnalyzedElement>?, holder: AnnotationHolder) {
        if (annotationResult == null) return

        annotationResult.forEach{ element ->
            holder.newAnnotation(element.severity, element.description)
                .range(element.element.textRange)
                .create()
        }

        publisher?.update(annotationResult.size)
    }

}