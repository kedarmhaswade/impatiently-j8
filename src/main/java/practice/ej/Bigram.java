package practice.ej;

public class Bigram {

    private final char first;
    private final char second;

    public Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Bigram that) {
        return this.first == that.first && this.second == that.second;
    }
}
