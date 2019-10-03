package no.bakkenbaeck.chessboardeditor.view.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TableLayout
import no.bakkenbaeck.chessboardeditor.model.*
import no.bakkenbaeck.chessboardeditor.util.Constants.BOARD_SIZE
import no.bakkenbaeck.chessboardeditor.util.FenUtil
import no.bakkenbaeck.chessboardeditor.view.cell.ChessCellView
import no.bakkenbaeck.chessboardeditor.view.cell.ChessInnerCellView
import no.bakkenbaeck.chessboardeditor.view.cell.ChessSideCellView
import no.bakkenbaeck.chessboardeditor.view.piece.ChessPieceView
import no.bakkenbaeck.chessboardeditor.view.row.ChessInnerRowView
import no.bakkenbaeck.chessboardeditor.view.row.ChessSideRowView
import no.bakkenbaeck.chessboardeditor.view.row.ChessWhiteSpaceRowView
import kotlin.math.min

class ChessBoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TableLayout(context, attrs) {

    companion object {
        const val BOARD_WIDTH_RATIO = 8
        const val BOARD_HEIGHT_RATIO = 11
    }

    private lateinit var position: Position

    private val draggedPieces = mutableListOf<DraggedPiece>()

    init {
        setFen("")
    }

    fun setFen(fen: String) {
        try {
            position = FenUtil.readFEN(fen)
            initBoard()
        } catch (ex: FenParseException) {
            Log.e("ChessBoardView", "Error parsing FEN", ex)
        }
    }

    fun getFen() = FenUtil.toFen(position)

    private fun initBoard() {
        post {
            removeAllViews()
            createBoard()
            loadPieces()
        }
    }

    private fun createBoard() {
        insertSideRow(false)
        insertEmptySpace()
        (0 until BOARD_SIZE).reversed().forEach { insertRow(it) }
        insertEmptySpace()
        insertSideRow(true)
    }

    private fun insertSideRow(isWhite: Boolean) {
        val row = ChessSideRowView(context)
        row.setSide(isWhite, ::pieceDragEnded, ::pieceDragged)
        addView(row)
    }

    private fun insertEmptySpace() {
        addView(ChessWhiteSpaceRowView(context))
    }

    private fun insertRow(rowIndex: Int) {
        val row = ChessInnerRowView(context)
        row.setRow(rowIndex, ::pieceDragEnded, ::pieceDragged)
        addView(row)
    }

    private fun pieceDragEnded(cellTag: String, pieceTag: String) {
        val pieceView = this@ChessBoardView.findViewWithTag<View>(pieceTag) as? ChessPieceView ?: return
        val fromCell = pieceView.parent as? ChessCellView ?: return
        val toCell = this@ChessBoardView.findViewWithTag<View>(cellTag) as? ChessInnerCellView ?: return
        val piece = pieceView.piece ?: return

        if (fromCell is ChessInnerCellView) {
            applyNormalPieceMove(fromCell, toCell, pieceView)
        } else if (fromCell is ChessSideCellView) {
            if (piece is Piece.Delete) applyRemovePieceMove(toCell)
            else applyInsertPieceMove(fromCell, toCell, piece)
        }
    }

    private fun pieceDragged(draggedPiece: DraggedPiece) {
        draggedPieces.add(draggedPiece)
        invalidate()
    }

    private fun applyNormalPieceMove(
        fromCell: ChessInnerCellView,
        toCell: ChessInnerCellView,
        pieceView: ChessPieceView
    ) {
        applyNormalPieceMoveToPosition(fromCell, toCell)
        applyNormalPieceMoveToUI(fromCell, toCell, pieceView)
    }

    private fun applyInsertPieceMove(
        fromCell: ChessSideCellView,
        toCell: ChessInnerCellView,
        piece: Piece
    ) {
        applyInsertPieceMoveToPosition(fromCell, toCell)
        applyInsertPieceMoveToUI(toCell, piece)
    }

    private fun applyRemovePieceMove(removedCell: ChessInnerCellView) {
        applyRemovePieceMoveToPosition(removedCell)
        applyRemovePieceMoveToUI(removedCell)
    }

    private fun applyNormalPieceMoveToPosition(fromCell: ChessInnerCellView, toCell: ChessInnerCellView) {
        val move = Move.NormalPieceMove(
            fromCell.getRowCol(),
            toCell.getRowCol()
        )
        position.makeMove(move)
    }

    private fun applyNormalPieceMoveToUI(
        fromCell: ChessInnerCellView,
        toCell: ChessInnerCellView,
        pieceView: ChessPieceView
    ) {
        fromCell.removePiece()
        toCell.setPiece(pieceView)
    }

    private fun applyInsertPieceMoveToPosition(fromCell: ChessSideCellView, toCell: ChessInnerCellView) {
        val currentRowCol = fromCell.getRowCol()
        val piece = ChessSideRowView.getPieceFromRowCol(currentRowCol.first, currentRowCol.second) ?: return
        val move = Move.InsertPieceMove(
            toCell.getRowCol(),
            piece
        )
        position.makeMove(move)
    }

    private fun applyInsertPieceMoveToUI(
        toCell: ChessInnerCellView,
        piece: Piece
    ) {
        val clonePieceView = ChessPieceView(context)
        clonePieceView.piece = piece
        toCell.setPiece(clonePieceView)
    }

    private fun applyRemovePieceMoveToPosition(removedCell: ChessInnerCellView) {
        val move = Move.RemovePieceMove(removedCell.getRowCol())
        position.makeMove(move)
    }

    private fun applyRemovePieceMoveToUI(removedCell: ChessInnerCellView) {
        removedCell.removePiece()
    }

    private fun loadPieces() {
        position.forEach { row, col, piece ->
            val tag = ChessCellView.getTagFromRowCol(row, col)
            val cell = findViewWithTag<ChessCellView>(tag) ?: return@forEach
            val pieceView = ChessPieceView(context)
            pieceView.piece = piece
            cell.setPiece(pieceView)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val (desiredWidth, desiredHeight) = getDesiredDimensions(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(desiredWidth, desiredHeight)
        setWidthHeight(desiredWidth, desiredHeight)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun getDesiredDimensions(widthMeasureSpec: Int, heightMeasureSpec: Int): Pair<Int, Int> {
        val measuredWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measuredHeight = MeasureSpec.getSize(heightMeasureSpec)
        val normalizedWidth = measuredWidth * BOARD_HEIGHT_RATIO
        val normalizedHeight = measuredHeight * BOARD_WIDTH_RATIO
        val minNormalized = min(normalizedWidth, normalizedHeight)
        val desiredWidth = minNormalized / BOARD_HEIGHT_RATIO
        val desiredHeight = minNormalized / BOARD_WIDTH_RATIO
        return desiredWidth to desiredHeight
    }

    private fun setWidthHeight(width: Int, height: Int) {
        layoutParams.width = width
        layoutParams.height = height
    }

    private val paint: Paint by lazy { initPaint() }

    private fun initPaint(): Paint {
        val paint = Paint()
        paint.strokeWidth = 4.0f
        paint.color = Color.RED
        return paint
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        for (draggedPiece in draggedPieces) {

            val pieceView = this@ChessBoardView.findViewWithTag<View>(draggedPiece.pieceTag) as? ChessPieceView ?: continue
            val piece = pieceView.piece
            val toCell = this@ChessBoardView.findViewWithTag<View>(draggedPiece.cellTag) as? ChessInnerCellView ?: continue
            val legalMoves = piece?.getLegalMovesForPiece(toCell) ?: arrayListOf()
            val parent = toCell.parent as? ChessInnerRowView ?: continue

            for (legalMove in legalMoves) {
                val legalCell = this@ChessBoardView.findViewWithTag<View>(legalMove) as? ChessInnerCellView ?: continue
                val legalParent = legalCell.parent as? ChessInnerRowView ?: continue
                canvas?.drawLine(
                    toCell.x + draggedPiece.x,
                    parent.y + draggedPiece.y,
                    legalCell.x + (legalCell.width / 2),
                    legalParent.y + (legalParent.height / 2),
                    paint)

            }
        }
        draggedPieces.clear()
    }
}