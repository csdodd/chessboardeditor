package no.bakkenbaeck.chessboardeditor.model


data class LegalMove(
    val toCellTag: String,
    var render: Boolean = false,
    val isCapture: Boolean = false
)