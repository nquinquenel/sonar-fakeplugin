package org.sonar.fakeplugin.analysis

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement

data class AnalyzedElement(val element: PsiElement, val severity: HighlightSeverity, val description: String)