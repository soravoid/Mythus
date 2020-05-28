package mod.mythusteam.mythus.utils;

import com.mojang.realmsclient.gui.ChatFormatting;

public enum SmithQuality
{
    SHARP(ChatFormatting.AQUA), CHARRED(ChatFormatting.DARK_RED);

    ChatFormatting color;

    SmithQuality(ChatFormatting color)
    {
        this.color = color;
    }

    //TODO Add colors to names?
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
        return color + converted.toString();
    }
}
