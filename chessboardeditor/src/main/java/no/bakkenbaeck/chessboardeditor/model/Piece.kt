package no.bakkenbaeck.chessboardeditor.model

import androidx.annotation.DrawableRes
import no.bakkenbaeck.chessboardeditor.R
import no.bakkenbaeck.chessboardeditor.view.cell.ChessCellView

sealed class Piece constructor(@DrawableRes val drawableRes: Int) {

    class WhiteKing : Piece(R.drawable.ic_wk) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>{
            val cellPos = chessCellView.getRowCol()
            return arrayListOf(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 1,
                        cellPos.second
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 1,
                        cellPos.second
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 1,
                        cellPos.second - 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 1,
                        cellPos.second + 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 1,
                        cellPos.second - 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 1,
                        cellPos.second + 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first,
                        cellPos.second - 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first,
                        cellPos.second + 1
                    ), true
                )
            )
        }
    }

    class WhiteQueen : Piece(R.drawable.ic_wq) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>{
            val legalMoves = getLegalRookMoves(chessCellView, position, true)
            legalMoves.addAll(getLegalBishopMoves(chessCellView, position, true))
            return legalMoves
        }
    }

    class WhiteRook : Piece(R.drawable.ic_wr) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>{
            return getLegalRookMoves(chessCellView, position, true)
        }
    }

    class WhiteBishop : Piece(R.drawable.ic_wb) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>{
            return getLegalBishopMoves(chessCellView, position, true)
        }
    }

    class WhiteKnight : Piece(R.drawable.ic_wn) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>{
            return getLegalKnightMoves(chessCellView, position, true)
        }
    }

    class WhitePawn : Piece(R.drawable.ic_wp) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>{
            return getLegalPawnMoves(chessCellView, position, true)
        }
    }

    class BlackKing : Piece(R.drawable.ic_bk) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>{
            val cellPos = chessCellView.getRowCol()
            return arrayListOf(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 1,
                        cellPos.second
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 1,
                        cellPos.second
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 1,
                        cellPos.second - 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 1,
                        cellPos.second + 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 1,
                        cellPos.second - 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 1,
                        cellPos.second + 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first,
                        cellPos.second - 1
                    ), true
                ),
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first,
                        cellPos.second + 1
                    ), true
                )
            )
        }
    }

    class BlackQueen : Piece(R.drawable.ic_bq) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove> {
            val legalMoves = getLegalRookMoves(chessCellView, position, false)
            legalMoves.addAll(getLegalBishopMoves(chessCellView, position, false))
            return legalMoves
        }
    }

    class BlackRook : Piece(R.drawable.ic_br) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>{
            return getLegalRookMoves(chessCellView, position, false)
        }
    }

    class BlackBishop : Piece(R.drawable.ic_bb) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove> {
            return getLegalBishopMoves(chessCellView, position, false)
        }
    }

    class BlackKnight : Piece(R.drawable.ic_bn) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove> {
            return getLegalKnightMoves(chessCellView, position, false)
        }
    }

    class BlackPawn : Piece(R.drawable.ic_bp) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>{
            return getLegalPawnMoves(chessCellView, position, false)
        }
    }

    class Delete : Piece(R.drawable.ic_delete) {
        override fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position) = arrayListOf<LegalMove>()
    }

    private fun isWhite(): Boolean {
        return when (this) {
            is WhiteKing -> true
            is WhiteQueen -> true
            is WhiteRook -> true
            is WhiteBishop -> true
            is WhiteKnight -> true
            is WhitePawn -> true
            is BlackKing -> false
            is BlackQueen -> false
            is BlackRook -> false
            is BlackBishop -> false
            is BlackKnight -> false
            is BlackPawn -> false
            else -> false
        }
    }

    private fun isBlack() = !isWhite()

    internal fun getLegalPawnMoves(
        chessCellView: ChessCellView,
        position: Position,
        isWhite: Boolean
    ): ArrayList<LegalMove> {
        val cellPos = chessCellView.getRowCol()
        val forwardDirection = if(isWhite) 1 else -1

        val legalMoves = arrayListOf<LegalMove>()
        if (position.getPiece(cellPos.first + forwardDirection, cellPos.second) == null)
            legalMoves.add(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + forwardDirection,
                        cellPos.second
                    ), true
                )
            )

        position.getPiece(cellPos.first + forwardDirection, cellPos.second + 1)?.let {
            if(canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first + forwardDirection,
                            cellPos.second + 1
                        ), true,
                        isCapture = true
                    )
                )
            }
        }

        position.getPiece(cellPos.first + forwardDirection, cellPos.second - 1)?.let {
            if(canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first + forwardDirection,
                            cellPos.second - 1
                        ), true,
                        isCapture = true
                    )
                )
            }
        }

        return legalMoves
    }

    internal fun getLegalKnightMoves(
        chessCellView: ChessCellView,
        position: Position,
        isWhite: Boolean
    ): ArrayList<LegalMove> {
        val cellPos = chessCellView.getRowCol()
        val legalMoves = arrayListOf<LegalMove>()

        position.getPiece(cellPos.first + 2, cellPos.second + 1)?.let {
            if(canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first + 2,
                            cellPos.second + 1
                        ), true,
                        isCapture = true
                    )
                )
            }
        } ?: run {
            legalMoves.add(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 2,
                        cellPos.second + 1
                    ), true
                )
            )
        }

        position.getPiece(cellPos.first + 2, cellPos.second - 1)?.let {
            if(canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first + 2,
                            cellPos.second - 1
                        ), true,
                        isCapture = true
                    )
                )
            }
        } ?: run {
            legalMoves.add(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 2,
                        cellPos.second - 1
                    ), true
                )
            )
        }

        position.getPiece(cellPos.first - 2, cellPos.second + 1)?.let {
            if(canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first - 2,
                            cellPos.second + 1
                        ), true,
                        isCapture = true
                    )
                )
            }
        } ?: run {
            legalMoves.add(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 2,
                        cellPos.second + 1
                    ), true
                )
            )
        }

        position.getPiece(cellPos.first - 2, cellPos.second - 1)?.let {
            if(canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first - 2,
                            cellPos.second - 1
                        ), true,
                        isCapture = true
                    )
                )
            }
        } ?: run {
            legalMoves.add(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 2,
                        cellPos.second - 1
                    ), true
                )
            )
        }

        position.getPiece(cellPos.first + 1, cellPos.second + 2)?.let {
            if(canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first + 1,
                            cellPos.second + 2
                        ), true,
                        isCapture = true
                    )
                )
            }
        } ?: run {
            legalMoves.add(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 1,
                        cellPos.second + 2
                    ), true
                )
            )
        }

        position.getPiece(cellPos.first + 1, cellPos.second - 2)?.let {
            if (canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first + 1,
                            cellPos.second - 2
                        ), true,
                        isCapture = true
                    )
                )
            }
        } ?: run {
            legalMoves.add(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first + 1,
                        cellPos.second - 2
                    ), true
                )
            )
        }

        position.getPiece(cellPos.first - 1, cellPos.second + 2)?.let {
            if(canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first - 1,
                            cellPos.second + 2
                        ), true,
                        isCapture = true
                    )
                )
            }
        } ?: run {
            legalMoves.add(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 1,
                        cellPos.second + 2
                    ), true
                )
            )
        }

        position.getPiece(cellPos.first - 1, cellPos.second - 2)?.let {
            if(canCapturePiece(it, isWhite)) {
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(
                            cellPos.first - 1,
                            cellPos.second - 2
                        ), true,
                        isCapture = true
                    )
                )
            }
        } ?: run {
            legalMoves.add(
                LegalMove(
                    ChessCellView.getTagFromRowCol(
                        cellPos.first - 1,
                        cellPos.second - 2
                    ), true
                )
            )
        }

        return legalMoves
    }

    internal fun getLegalRookMoves(
        chessCellView: ChessCellView,
        position: Position,
        isWhite: Boolean
    ): ArrayList<LegalMove> {
        val cellPos = chessCellView.getRowCol()

        val legalMoves = arrayListOf<LegalMove>()

        for (i in cellPos.first..7) {
            val piece = position.getPiece(i, cellPos.second)
            if (piece != null) {
                if (canCapturePiece(piece, isWhite)) {
                    legalMoves.add(
                        LegalMove(
                            ChessCellView.getTagFromRowCol(i, cellPos.second),
                            true,
                            isCapture = true
                        )
                    )
                } else {
                    val lastLegalMove = legalMoves.lastOrNull()
                    lastLegalMove?.render = true
                }
                break
            } else {
                val shouldRender = i == 7
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(i, cellPos.second),
                        shouldRender
                    )
                )
            }
        }

        for (i in cellPos.first downTo 0) {
            val piece = position.getPiece(i, cellPos.second)
            if (piece != null) {
                if (canCapturePiece(piece, isWhite)) {
                    legalMoves.add(
                        LegalMove(
                            ChessCellView.getTagFromRowCol(i, cellPos.second),
                            true,
                            isCapture = true
                        )
                    )
                } else {
                    val lastLegalMove = legalMoves.lastOrNull()
                    lastLegalMove?.render = true
                }
                break
            } else {
                val shouldRender = i == 0
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(i, cellPos.second),
                        shouldRender
                    )
                )
            }
        }

        for (i in cellPos.second..7) {
            val piece = position.getPiece(cellPos.first, i)
            if (piece != null) {
                if (canCapturePiece(piece, isWhite)) {
                    legalMoves.add(
                        LegalMove(
                            ChessCellView.getTagFromRowCol(cellPos.first, i),
                            true,
                            isCapture = true
                        )
                    )
                } else {
                    val lastLegalMove = legalMoves.lastOrNull()
                    lastLegalMove?.render = true
                }
                break
            } else {
                val shouldRender = i == 7
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(cellPos.first, i),
                        shouldRender
                    )
                )
            }
        }

        for (i in cellPos.second downTo 0) {
            val piece = position.getPiece(cellPos.first, i)
            if (piece != null) {
                if (canCapturePiece(piece, isWhite)) {
                    legalMoves.add(
                        LegalMove(
                            ChessCellView.getTagFromRowCol(cellPos.first, i),
                            true,
                            isCapture = true
                        )
                    )
                } else {
                    val lastLegalMove = legalMoves.lastOrNull()
                    lastLegalMove?.render = true
                }
                break
            } else {
                val shouldRender = i == 0
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(cellPos.first, i),
                        shouldRender
                    )
                )
            }
        }

        return legalMoves
    }

    internal fun getLegalBishopMoves(
        chessCellView: ChessCellView,
        position: Position,
        isWhite: Boolean
    ): ArrayList<LegalMove> {
        val cellPos = chessCellView.getRowCol()

        val legalMoves = arrayListOf<LegalMove>()

        for (i in cellPos.first..7) {
            if (cellPos.second + (i - cellPos.first) > 7) {
                val lastLegalMove = legalMoves.lastOrNull()
                lastLegalMove?.render = true
                break
            }

            val piece = position.getPiece(i, cellPos.second + (i - cellPos.first))
            if (piece != null) {
                if (canCapturePiece(piece, isWhite)) {
                    legalMoves.add(
                        LegalMove(
                            ChessCellView.getTagFromRowCol(
                                i,
                                cellPos.second + (i - cellPos.first)
                            ),
                            true,
                            isCapture = true
                        )
                    )
                } else {
                    val lastLegalMove = legalMoves.lastOrNull()
                    lastLegalMove?.render = true
                }
                break
            } else {
                val shouldRender = i == 7
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(i, cellPos.second + (i - cellPos.first)),
                        shouldRender
                    )
                )
            }
        }

        for (i in cellPos.first downTo 0) {
            if (cellPos.second + (cellPos.first - i) > 7) {
                val lastLegalMove = legalMoves.lastOrNull()
                lastLegalMove?.render = true
                break
            }

            val piece = position.getPiece(i, cellPos.second + (cellPos.first - i))
            if (piece != null) {
                if (canCapturePiece(piece, isWhite)) {
                    legalMoves.add(
                        LegalMove(
                            ChessCellView.getTagFromRowCol(
                                i,
                                cellPos.second + (cellPos.first - i)
                            ),
                            true,
                            isCapture = true
                        )
                    )
                } else {
                    val lastLegalMove = legalMoves.lastOrNull()
                    lastLegalMove?.render = true
                }
                break
            } else {
                val shouldRender = i == 0
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(i, cellPos.second + (cellPos.first - i)),
                        shouldRender
                    )
                )
            }
        }

        for (i in cellPos.first downTo 0) {
            if (cellPos.second - (cellPos.first - i) < 0) {
                val lastLegalMove = legalMoves.lastOrNull()
                lastLegalMove?.render = true
                break
            }

            val piece = position.getPiece(i, cellPos.second - (cellPos.first - i))
            if (piece != null) {
                if (canCapturePiece(piece, isWhite)) {
                    legalMoves.add(
                        LegalMove(
                            ChessCellView.getTagFromRowCol(
                                i,
                                cellPos.second - (cellPos.first - i)
                            ),
                            true,
                            isCapture = true
                        )
                    )
                } else {
                    val lastLegalMove = legalMoves.lastOrNull()
                    lastLegalMove?.render = true
                }
                break
            } else {
                val shouldRender = i == 0
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(i, cellPos.second - (cellPos.first - i)),
                        shouldRender
                    )
                )
            }
        }

        for (i in cellPos.first..7) {
            if (cellPos.second - (i - cellPos.first) < 0) {
                val lastLegalMove = legalMoves.lastOrNull()
                lastLegalMove?.render = true
                break
            }

            val piece = position.getPiece(i, cellPos.second - (i - cellPos.first))
            if (piece != null) {
                if (canCapturePiece(piece, isWhite)) {
                    legalMoves.add(
                        LegalMove(
                            ChessCellView.getTagFromRowCol(
                                i,
                                cellPos.second - (i - cellPos.first)
                            ),
                            true,
                            isCapture = true
                        )
                    )
                } else {
                    val lastLegalMove = legalMoves.lastOrNull()
                    lastLegalMove?.render = true
                }
                break
            } else {
                val shouldRender = i == 7
                legalMoves.add(
                    LegalMove(
                        ChessCellView.getTagFromRowCol(i, cellPos.second - (i - cellPos.first)),
                        shouldRender
                    )
                )
            }
        }

        return legalMoves
    }

    private fun canCapturePiece(
        piece: Piece,
        isWhite: Boolean
    ) = isWhite && piece.isBlack() || !isWhite && piece.isWhite()

    abstract fun getLegalMovesForPiece(chessCellView: ChessCellView, position: Position): ArrayList<LegalMove>
}