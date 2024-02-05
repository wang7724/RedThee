package site.pushy.landlords.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 牌的类型枚举值
 *
 * @author Pushy
 * @since 2018/12/28 21:22
 */
@AllArgsConstructor
@Getter
public enum CardTypeEnum {

    SPADE("黑桃"),

    HEART("红桃"),

    CLUB("梅花"),

    DIAMOND("方块"),

    SMALL_JOKER("小王"),
    BIG_JOKER("大王");

    private final String name;
}
