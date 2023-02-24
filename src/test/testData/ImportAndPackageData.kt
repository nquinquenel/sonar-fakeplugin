package org.sonar.fakeplugin.analysis

import com.intellij.lang.annotation.HighlightSeverity

data class ImportAndPackageData(val element: PsiElement, val severity: HighlightSeverity, val sonar: String)