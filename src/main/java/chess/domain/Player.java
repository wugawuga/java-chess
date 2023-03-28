package chess.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Player {

    private final Color color;
    private final Pieces pieces;

    private Player(final Color color, final Pieces pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public static Player fromWhitePlayer(final Pieces pieces) {
        return new Player(Color.WHITE, pieces);
    }

    public static Player fromBlackPlayer(final Pieces pieces) {
        return new Player(Color.BLACK, pieces);
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }

    public Color getColor() {
        return color;
    }

    public boolean hasPositionPiece(final Position findPosition) {
        return pieces.hasPosition(findPosition);
    }

    public Piece movePiece(final List<Position> allPosition, final Position findPosition, final Position targetPosition) {
        if (pieces.hasPosition(targetPosition)) {
            throw new IllegalArgumentException("상대 기물 위치로만 이동할 수 있습니다.");
        }

        Piece findPiece = pieces.findPiece(findPosition);
        findPiece.move(allPosition, targetPosition, color);
        return findPiece;
    }

    public boolean isKingDead() {
        return pieces.getPieces().stream().noneMatch(piece -> piece.isSameShape(Shape.KING));
    }

    public Optional<Piece> removePiece(final Position removalPosition) {
        return pieces.remove(removalPosition);
    }

    public Score getTotalScore() {
        return Score.subtract(pieces);
    }

    public String getColorName() {
        return color.name();
    }

    @Override
    public String toString() {
        return "Player{" +
                "color='" + color + '\'' +
                ", pieces=" + pieces +
                '}';
    }

}
