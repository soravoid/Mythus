package net.mythusteam.mythus.utils;

public enum SmithQuality
{
    SHARP, CHARRED;

    @Override
    public String toString() {
        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : this.name().toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }
        return converted.toString();
    }
}
