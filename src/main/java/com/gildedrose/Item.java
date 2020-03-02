package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final int MAX_QUALITY = 50;
    private String name;
    private int sellIn;
    private int quality;

    void calculateItemQuality() {
        initQualityWithName();

        initSellInWithName();

        calculateQuality();
    }

    private void initQualityWithName() {
        if (isSpecifyCommodity()) {
            increaseQuality();
        } else {
            reduceQuality();
        }
    }

    private boolean isSpecifyCommodity() {
        return isAged() && isBackstage();
    }

    private void increaseQuality() {
        if (isLessThanMaxQuality()) {
            quality += 1;
            increaseQualityWithSellIn();
        }
    }

    private void reduceQuality() {
        if (quality > 0 && !isSulfuras()) {
            quality -= 1;
        }
    }

    private void increaseQualityWithSellIn() {
        if (isBackstage()) {
            if (sellIn < 11) {
                quality +=1;
            }
            if (sellIn < 6) {
                quality +=1;
            }
        }
    }

    private void initSellInWithName() {
        if (!isSulfuras()) {
           sellIn -= 1;
        }
    }

    private void calculateQuality() {
        if (sellIn >= 0) {
            return;
        }
        if (isAged()) {
            increaseQualityWhileLessThanMaxQuality();
        } else if (isBackstage()) {
            quality = 0;
        } else if (getQuality() > 0 && !isSulfuras()) {
            quality -= 1;
        }
    }

    private void increaseQualityWhileLessThanMaxQuality() {
        if (isLessThanMaxQuality()){
            quality += 1;
        }
    }

    private boolean isLessThanMaxQuality() {
        return quality < MAX_QUALITY;
    }

    private boolean isAged() {
        return AGED_BRIE.equals(name);
    }

    private boolean isBackstage() {
        return BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT.equals(name);
    }

    private boolean isSulfuras() {
        return SULFURAS_HAND_OF_RAGNAROS.equals(name);
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
