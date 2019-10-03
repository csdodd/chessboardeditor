package no.bakkenbaeck.chessboardeditor.model


data class DraggedPiece(
    val x: Float,
    val y: Float,
    val cellTag: String,
    val pieceTag: String
)