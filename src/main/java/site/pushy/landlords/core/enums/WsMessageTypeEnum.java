package site.pushy.landlords.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Pushy
 * @since 2019/1/24 12:25
 */
@AllArgsConstructor
@Getter
public enum WsMessageTypeEnum {

    READY_GAME("玩家准备"),
    UNREADY_GAME("玩家取消准备"),
    START_GAME("开始游戏"),

    PLAYER_JOIN("有玩家加入"),
    PLAYER_EXIT("有玩家退出"),

    BID("叫牌"),
    BID_END("叫牌结束"),

    PLAY_CARD("有玩家出牌"),
    PLEASE_PLAY_CARD("请出牌"),
    PASS("要不起"),

    GAME_END("游戏结束"),

    CHAT("聊天消息"),

    PONG("心跳检测");

    private final String value;
}
