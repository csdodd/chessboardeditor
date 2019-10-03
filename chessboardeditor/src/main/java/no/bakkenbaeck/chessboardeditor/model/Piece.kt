package no.bakkenbaeck.chessboardeditor.model

import androidx.annotation.DrawableRes
import no.bakkenbaeck.chessboardeditor.R
import no.bakkenbaeck.chessboardeditor.view.cell.ChessCellView
import kotlin.math.max
import kotlin.math.min

sealed class Piece(@DrawableRes val drawableRes: Int) {
    class WhiteKing : Piece(R.drawable.ic_wk) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()
            return arrayListOf(
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second),
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second + 1),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second + 1),
                ChessCellView.getTagFromRowCol(cellPos.first, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first, cellPos.second + 1)
            )
        }
    }

    class WhiteQueen : Piece(R.drawable.ic_wq) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()

            val legalMoves = arrayListOf(
                ChessCellView.getTagFromRowCol(0, cellPos.second),
                ChessCellView.getTagFromRowCol(7, cellPos.second),
                ChessCellView.getTagFromRowCol(cellPos.first, 0),
                ChessCellView.getTagFromRowCol(cellPos.first, 7)
            )

            if (cellPos.first > cellPos.second) {
                val swCol = (7 - cellPos.first) + cellPos.second
                val seCol = max(0, cellPos.second - (7 - cellPos.first))
                val neRow = cellPos.first - cellPos.second
                val nwRow = max(0, cellPos.second - (7 - cellPos.first))

                legalMoves.add(ChessCellView.getTagFromRowCol(7, swCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(min(7, cellPos.first + cellPos.second), seCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(neRow, 0))
                legalMoves.add(ChessCellView.getTagFromRowCol(nwRow, min(7, cellPos.first + cellPos.second)))
            } else {
                val swRow = (7 - cellPos.second) + cellPos.first
                val seCol = max(0, cellPos.first - (7 - cellPos.second))
                val neCol = cellPos.second - cellPos.first
                val nwRow = max(0, cellPos.second - (7 - cellPos.first))

                legalMoves.add(ChessCellView.getTagFromRowCol(swRow, 7))
                legalMoves.add(ChessCellView.getTagFromRowCol(min(7, cellPos.first + cellPos.second), seCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(0, neCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(nwRow, min(7, cellPos.first + cellPos.second)))
            }

            return legalMoves
        }
    }

    class WhiteRook : Piece(R.drawable.ic_wr) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()

            return arrayListOf(
                ChessCellView.getTagFromRowCol(0, cellPos.second),
                ChessCellView.getTagFromRowCol(7, cellPos.second),
                ChessCellView.getTagFromRowCol(cellPos.first, 0),
                ChessCellView.getTagFromRowCol(cellPos.first, 7)
            )
        }
    }

    class WhiteBishop : Piece(R.drawable.ic_wb) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()

            val legalMoves = arrayListOf<String>()

            if (cellPos.first > cellPos.second) {
                val swCol = (7 - cellPos.first) + cellPos.second
                val seCol = max(0, cellPos.second - (7 - cellPos.first))
                val neRow = cellPos.first - cellPos.second
                val nwRow = max(0, cellPos.second - (7 - cellPos.first))

                legalMoves.add(ChessCellView.getTagFromRowCol(7, swCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(min(7, cellPos.first + cellPos.second), seCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(neRow, 0))
                legalMoves.add(ChessCellView.getTagFromRowCol(nwRow, min(7, cellPos.first + cellPos.second)))
            } else {
                val swRow = (7 - cellPos.second) + cellPos.first
                val seCol = max(0, cellPos.first - (7 - cellPos.second))
                val neCol = cellPos.second - cellPos.first
                val nwRow = max(0, cellPos.second - (7 - cellPos.first))

                legalMoves.add(ChessCellView.getTagFromRowCol(swRow, 7))
                legalMoves.add(ChessCellView.getTagFromRowCol(min(7, cellPos.first + cellPos.second), seCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(0, neCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(nwRow, min(7, cellPos.first + cellPos.second)))
            }

            return legalMoves
        }
    }

    class WhiteKnight : Piece(R.drawable.ic_wn) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()
            return arrayListOf(
                ChessCellView.getTagFromRowCol(cellPos.first + 2, cellPos.second + 1),
                ChessCellView.getTagFromRowCol(cellPos.first + 2, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first - 2, cellPos.second + 1),
                ChessCellView.getTagFromRowCol(cellPos.first - 2, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second + 2),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second - 2),
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second + 2),
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second - 2)
            )
        }
    }

    class WhitePawn : Piece(R.drawable.ic_wp) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()
            return arrayListOf(
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second)
            )
        }
    }

    class BlackKing : Piece(R.drawable.ic_bk) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()
            return arrayListOf(
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second),
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second + 1),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second + 1),
                ChessCellView.getTagFromRowCol(cellPos.first, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first, cellPos.second + 1)
            )
        }
    }

    class BlackQueen : Piece(R.drawable.ic_bq) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()

            val legalMoves = arrayListOf(
                ChessCellView.getTagFromRowCol(0, cellPos.second),
                ChessCellView.getTagFromRowCol(7, cellPos.second),
                ChessCellView.getTagFromRowCol(cellPos.first, 0),
                ChessCellView.getTagFromRowCol(cellPos.first, 7)
            )

            if (cellPos.first > cellPos.second) {
                val swCol = (7 - cellPos.first) + cellPos.second
                val seCol = max(0, cellPos.second - (7 - cellPos.first))
                val neRow = cellPos.first - cellPos.second
                val nwRow = max(0, cellPos.second - (7 - cellPos.first))

                legalMoves.add(ChessCellView.getTagFromRowCol(7, swCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(min(7, cellPos.first + cellPos.second), seCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(neRow, 0))
                legalMoves.add(ChessCellView.getTagFromRowCol(nwRow, min(7, cellPos.first + cellPos.second)))
            } else {
                val swRow = (7 - cellPos.second) + cellPos.first
                val seCol = max(0, cellPos.first - (7 - cellPos.second))
                val neCol = cellPos.second - cellPos.first
                val nwRow = max(0, cellPos.second - (7 - cellPos.first))

                legalMoves.add(ChessCellView.getTagFromRowCol(swRow, 7))
                legalMoves.add(ChessCellView.getTagFromRowCol(min(7, cellPos.first + cellPos.second), seCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(0, neCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(nwRow, min(7, cellPos.first + cellPos.second)))
            }

            return legalMoves
        }
    }

    class BlackRook : Piece(R.drawable.ic_br) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()

            return arrayListOf(
                ChessCellView.getTagFromRowCol(0, cellPos.second),
                ChessCellView.getTagFromRowCol(7, cellPos.second),
                ChessCellView.getTagFromRowCol(cellPos.first, 0),
                ChessCellView.getTagFromRowCol(cellPos.first, 7)
            )
        }
    }

    class BlackBishop : Piece(R.drawable.ic_bb) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()

            val legalMoves = arrayListOf<String>()

            if (cellPos.first > cellPos.second) {
                val swCol = (7 - cellPos.first) + cellPos.second
                val seCol = max(0, cellPos.second - (7 - cellPos.first))
                val neRow = cellPos.first - cellPos.second
                val nwRow = max(0, cellPos.second - (7 - cellPos.first))

                legalMoves.add(ChessCellView.getTagFromRowCol(7, swCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(min(7, cellPos.first + cellPos.second), seCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(neRow, 0))
                legalMoves.add(ChessCellView.getTagFromRowCol(nwRow, min(7, cellPos.first + cellPos.second)))
            } else {
                val swRow = (7 - cellPos.second) + cellPos.first
                val seCol = max(0, cellPos.first - (7 - cellPos.second))
                val neCol = cellPos.second - cellPos.first
                val nwRow = max(0, cellPos.second - (7 - cellPos.first))

                legalMoves.add(ChessCellView.getTagFromRowCol(swRow, 7))
                legalMoves.add(ChessCellView.getTagFromRowCol(min(7, cellPos.first + cellPos.second), seCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(0, neCol))
                legalMoves.add(ChessCellView.getTagFromRowCol(nwRow, min(7, cellPos.first + cellPos.second)))
            }

            return legalMoves
        }
    }

    class BlackKnight : Piece(R.drawable.ic_bn) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()
            return arrayListOf(
                ChessCellView.getTagFromRowCol(cellPos.first + 2, cellPos.second + 1),
                ChessCellView.getTagFromRowCol(cellPos.first + 2, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first - 2, cellPos.second + 1),
                ChessCellView.getTagFromRowCol(cellPos.first - 2, cellPos.second - 1),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second + 2),
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second - 2),
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second + 2),
                ChessCellView.getTagFromRowCol(cellPos.first - 1, cellPos.second - 2)
            )
        }
    }

    class BlackPawn : Piece(R.drawable.ic_bp) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String> {
            val cellPos = chessCellView.getRowCol()
            return arrayListOf(
                ChessCellView.getTagFromRowCol(cellPos.first + 1, cellPos.second)
            )
        }
    }

    class Delete : Piece(R.drawable.ic_delete) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView) = arrayListOf<String>()
    }

    abstract fun getLegalMovesForPiece(chessCellView: ChessCellView): ArrayList<String>
}