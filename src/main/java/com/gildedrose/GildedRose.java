package com.gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final int MAX_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            calculateQuality(item);
        }
    }

    private void calculateQuality(Item item) {
        if (isSpecifyCommodity(item)) {
            increaseQuality(item);
        } else {
            reduceQuality(item);
        }

        if (!item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
            item.setSellIn(item.getSellIn() - 1);
        }

        if (item.getSellIn() < 0) {
            if (!item.getName().equals(AGED_BRIE)) {
                if (!item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                    if (item.getQuality() > 0) {
                        if (!item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
                            item.setQuality(item.getQuality() - 1);
                        }
                    }
                } else {
                    item.setQuality(0);
                }
            } else {
                if (isLessThanMaxQuality(item.getQuality())) {
                    item.setQuality(item.getQuality() + 1);
                }
            }
        }
    }

    private void increaseQuality(Item item) {
        if (isLessThanMaxQuality(item.getQuality())) {
            item.setQuality(item.getQuality() + 1);
            if (item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                if (item.getSellIn() < 11) {
                    if (isLessThanMaxQuality(item.getQuality())) {
                        item.setQuality(item.getQuality() + 1);
                    }
                }

                if (item.getSellIn() < 6) {
                    if (isLessThanMaxQuality(item.getQuality())) {
                        item.setQuality(item.getQuality() + 1);
                    }
                }
            }
        }
    }

    private void reduceQuality(Item item) {
        if (item.getQuality() > 0 && !item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private boolean isLessThanMaxQuality(int quality) {
        return quality < MAX_QUALITY;
    }

    private boolean isSpecifyCommodity(Item item) {
        return item.getName().equals(AGED_BRIE)
                && item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT);
    }
}
