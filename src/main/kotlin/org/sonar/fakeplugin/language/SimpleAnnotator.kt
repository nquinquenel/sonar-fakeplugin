package org.sonar.fakeplugin.language

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.PsiElement

class SimpleAnnotator : Annotator {

    companion object {
        const val WORD_ANNOTATED = "SONAR"
    }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val value = element.text ?: return

        if (value != WORD_ANNOTATED) {
            return
        }

        val textRange = element.textRange


        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(textRange)
            .textAttributes(DefaultLanguageHighlighterColors.KEYWORD)
            .create()
    }

}