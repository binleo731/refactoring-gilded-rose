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
        if (!item.getName().equals(AGED_BRIE)
                && !item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
            if (item.getQuality() > 0) {
                if (!item.getName().equals(SULFURAS_HAND_OF_RAGNAROS)) {
                    item.setQuality(item.getQuality() - 1);
                }
            }
        } else {
            if (item.getQuality() < MAX_QUALITY) {
                item.setQuality(item.getQuality() + 1);
                if (item.getName().equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                    if (item.getSellIn() < 11) {
                        if (item.getQuality() < MAX_QUALITY) {
                            item.setQuality(item.getQuality() + 1);
                        }
                    }

                    if (item.getSellIn() < 6) {
                        if (item.getQuality() < MAX_QUALITY) {
                            item.setQuality(item.getQuality() + 1);
                        }
                    }
                }
            }
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
                if (item.getQuality() < MAX_QUALITY) {
                    item.setQuality(item.getQuality() + 1);
                }
            }
        }
    }
}
