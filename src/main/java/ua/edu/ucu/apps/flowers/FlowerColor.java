package ua.edu.ucu.apps.flowers;

public enum FlowerColor {
    RED("#FF0000"), GREEN("#00FF00"), BLUE("#0000FF"), 
    YELLOW("#FFFF00"), WHITE("#FFFFFF");
    private String rgb;

    FlowerColor(String rgb) {
        this.rgb = rgb;
    }

    public String toString() {
        return rgb.toString();
    }

    public static FlowerColor getColor(String flowerColor) {
        for (FlowerColor color : FlowerColor.values()) {
            if (color.rgb.equalsIgnoreCase(flowerColor)) {
                return color;
            }
        }

        throw new IllegalArgumentException("Unknown RGB code: " + flowerColor);
    }
}
