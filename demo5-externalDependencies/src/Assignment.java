class Assignment {
    private final boolean totallyCorrect;
    private final boolean halfCorrect;

    Assignment(boolean totallyCorrect, boolean halfCorrect) {
        this.totallyCorrect = totallyCorrect;
        this.halfCorrect = halfCorrect;
    }

    boolean isHalfCorrect() {
        return halfCorrect;
    }

    boolean isTotallyCorrect() {
        return totallyCorrect;
    }
}
