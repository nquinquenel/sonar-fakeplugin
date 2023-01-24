package org.sonar.fakeplugin.language

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtPackageDirective

class SimpleAnnotator : Annotator {

    companion object {
        const val WORD_ANNOTATED = "SONAR"
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val value = element.text ?: return
        val textRange = element.textRange

        if (element is KtPackageDirective) {
            holder.newAnnotation(HighlightSeverity.WARNING, "This is a package")
                .range(textRange)
                .highlightType(ProblemHighlightType.WEAK_WARNING)
                .create()
        } else if (element is KtImportDirective) {
            holder.newAnnotation(HighlightSeverity.INFORMATION, "This is an import")
                .range(textRange)
                .highlightType(ProblemHighlightType.GENERIC_ERROR)
                .tooltip("This is a tooltip")
                .create()
        } else if (value == WORD_ANNOTATED) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(textRange)
                .textAttributes(DefaultLanguageHighlighterColors.STATIC_FIELD)
                .create()
        }
    }

}