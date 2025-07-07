package uz.qodirov.camunda;

public class Test {
    public String reversePrefix(String word, char ch) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch) {
                return new StringBuilder(word.substring(0, i + 1)).reverse() + word.substring(i + 1);
            }
        }
        return word;
    }

    public static void main(String[] args) {

    }
}
