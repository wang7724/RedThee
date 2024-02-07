package site.pushy.landlords.core;

import lombok.Getter;
import site.pushy.landlords.common.config.properties.LandlordsProperties;
import site.pushy.landlords.common.config.properties.PlayerNumProperties;
import site.pushy.landlords.pojo.Card;

import javax.annotation.Resource;
import java.util.*;

/**
 * 每局开始重新发牌、分牌、获取每个玩家的牌
 *
 * @author Pushy
 * @since 2018/12/30 21:49
 */
public class CardDistribution {

    @Resource
    private PlayerNumProperties properties;

    /**
     * 所有牌
     */
    private final List<Card> allCardList;

    /**
     * 玩家 1 的牌
     */
    private final List<Card> player1Cards = new ArrayList<>();

    /**
     * 玩家 2 的牌
     */
    private final List<Card> player2Cards = new ArrayList<>();

    /**
     * 玩家 3 的牌
     */
    private final List<Card> player3Cards = new ArrayList<>();

    /**
     * 玩家 4 的牌
     */
    private final List<Card> player4Cards = new ArrayList<>();

    /**
     * 玩家 5 的牌
     */
    private final List<Card> player5Cards = new ArrayList<>();

    /**
     * 底牌
     * -- GETTER --
     *  获得地主的三张牌
     *
     *  设为0

     */
    @Getter
    private List<Card> topCards = new ArrayList<>();

    public CardDistribution() {
        allCardList = new Vector<>();
    }

    /**
     * 刷新牌，重新洗牌，分牌
     */
    public synchronized void refresh() {
        clear();
        createCard();
        shuffle();
        deal();
    }

    /**
     * 创建牌
     */
    private void createCard() {
        for (int i = 0; i < 54; i++) {
            int id = i + 1;
            Card card = new Card(id);
            card.setType(ConstructCard.getTypeById(id));  // 设置花色
            card.setNumber(ConstructCard.getNumberById(id));  // 设置牌的数值
            card.setGrade(ConstructCard.getGradeById(id));  // 设置牌的等级
            allCardList.add(card);
        }
    }

    private synchronized void clear() {
        allCardList.clear();
        player1Cards.clear();
        player2Cards.clear();
        player3Cards.clear();
        player4Cards.clear();
        player5Cards.clear();
        topCards.clear();
    }

    /**
     * 洗牌
     */
    private void shuffle() {
        for (int i = 0; i < 1000; i++) {
            Collections.shuffle(allCardList);
        }
    }

    /**
     * 分牌
     * todo 开始游戏后，赢家先得牌。 获取winner
     */
    private void deal() {
        /* 分派给1号玩家11张牌 */
        for (int i = 0; i < 11; i++) {
            Card card = allCardList.get(i * properties.getPlayNum());
            player1Cards.add(card);
        }
        /* 分派给2号玩家11张牌 */
        for (int i = 0; i < 11; i++) {
            Card card = allCardList.get(i * properties.getPlayNum() + 1);
            player2Cards.add(card);
        }
        /* 分派给3号玩家11张牌 */
        for (int i = 0; i < 11; i++) {
            Card card = allCardList.get(i * properties.getPlayNum() + 2);
            player3Cards.add(card);
        }

        /* 分派给4号玩家11张牌 */
        for (int i = 0; i < 11; i++) {
            Card card = allCardList.get(i* properties.getPlayNum() + 3);
            player3Cards.add(card);
        }

        /* 分派给5号玩家10张牌 */
        for (int i = 0; i < 10; i++) {
            Card card = allCardList.get(i* properties.getPlayNum() + 4);
            player3Cards.add(card);
        }

//        /* 将剩余的三张牌添加到地主的牌当中 */
//        topCards = allCardList.subList(51, 54);

        /* 将玩家的牌通过等级由小到大的排序 */
        Collections.sort(player1Cards);
        Collections.sort(player2Cards);
        Collections.sort(player3Cards);
        Collections.sort(player4Cards);
        Collections.sort(player5Cards);

    }

    /**
     * 获取对应玩家该局的牌
     *
     * @param number 玩家在房间中的序号
     */
    public List<Card> getCards(int number) {
        switch (number) {
            case 1:
                return player1Cards;
            case 2:
                return player2Cards;
            case 3:
                return player3Cards;
            case 4:
                return player4Cards;
            case 5:
                return player5Cards;
        }
        return null;
    }

}
