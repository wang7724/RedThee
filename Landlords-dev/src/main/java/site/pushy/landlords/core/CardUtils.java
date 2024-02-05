package site.pushy.landlords.core;

import site.pushy.landlords.core.enums.TypeEnum;
import site.pushy.landlords.pojo.Card;

import java.util.Comparator;
import java.util.List;

/**
 * @author Pushy
 * @since 2018/12/29 15:52
 */
public class CardUtils {

    /**
     * 用于排序的比较器，让牌列表能够按照等级从小到大的排列
     */
    private static final Comparator<Card> CARD_COMPARATOR = Comparator.comparingInt(Card::getGradeValue);

    /**
     * 比较两张牌的等级值，返回1则代表card > other，-1 代表 card < other，0代表等级相同
     */
    public static int compareGrade(Card card, Card other) {
        if (card.getGrade() == null || other.getGrade() == null) {
            return 0;
        }
        return Integer.compare(card.getGradeValue(), other.getGradeValue());
    }

    /**
     * 判断一张牌的等级是否大于另一张
     */
    public static boolean compareGradeTo(Card card, Card other) {
        return compareGrade(card, other) == 1;
    }

    /**
     * 对牌进行从小到大地排序
     */
    public static void sortCards(List<Card> cards) {
        cards.sort(CARD_COMPARATOR);
    }

    /**
     * 判断几张牌的类型
     *
     * @see TypeEnum
     */
    public static TypeEnum getCardsType(List<Card> cards) {
        TypeEnum type = null;
        if (cards != null && cards.size() != 0) {
            if (TypeJudgement.isSingle(cards)) {
                type = TypeEnum.SINGLE;
            } else if (TypeJudgement.isPair(cards)) {
                type = TypeEnum.PAIR;
            } else if (TypeJudgement.isThree(cards)) {
                type = TypeEnum.THREE;
            } else if (TypeJudgement.isThreeWithOne(cards)) {
                type = TypeEnum.THREE_WITH_ONE;
            } else if (TypeJudgement.isThreeWithPair(cards)) {
                type = TypeEnum.THREE_WITH_PAIR;
            } else if (TypeJudgement.isStraight(cards)) {
                type = TypeEnum.STRAIGHT;
            } else if (TypeJudgement.isStraightPair(cards)) {
                type = TypeEnum.STRAIGHT_PAIR;
            } else if (TypeJudgement.isFourWithTwo(cards)) {
                type = TypeEnum.FOUR_WITH_TWO;
            } else if (TypeJudgement.isBomb(cards)) {
                type = TypeEnum.BOMB;
            } else if (TypeJudgement.isJokerBomb(cards)) {
                type = TypeEnum.JOKER_BOMB;
            } else if (TypeJudgement.isAircraft(cards)) {
                type = TypeEnum.AIRCRAFT;
            } else if (TypeJudgement.isAircraftWithWing(cards)) {
                type = TypeEnum.AIRCRAFT_WITH_WINGS;
            }
        }
        return type;
    }
}
