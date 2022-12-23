package ru.vsu.cs.okshina_v_a.enums;

public enum ID {
    KING {
        @Override
        public String toString() {
            return "K";
        }
        public String toFullString() {
            return "King";
        }
    },
    QUEEN {
        @Override
        public String toString() {
            return "Q";
        }
        public String toFullString() {
            return "Queen";
        }
    },
    ROOK {
        @Override
        public String toString() {
            return "R";
        }
        public String toFullString() {
            return "Rook";
        }
    },
    BISHOP {
        @Override
        public String toString() {
            return "B";
        }
        public String toFullString() {
            return "Bishop";
        }
    },
    KNIGHT {
        @Override
        public String toString() {
            return "N";
        }
        public String toFullString() {
            return "Knight";
        }
    },

    CHAMPION {
        @Override
        public String toString() {
            return "C";
        }
        public String toFullString() {
            return "Champion";
        }
    },

    WIZARD {
        @Override
        public String toString() {
            return "W";
        }
        public String toFullString() {
            return "Wizard";
        }
    },

    PAWN {
        @Override
        public String toString() {
            return "";
        }
        public String toFullString() {
            return "Pawn";
        }
    };

    public abstract String toFullString();
}