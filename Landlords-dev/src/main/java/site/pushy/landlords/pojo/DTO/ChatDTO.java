package site.pushy.landlords.pojo.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChatDTO {

    /**
     * 聊天内容
     */
    @NotBlank
    private String content;

    /**
     * 预设的类型, 为 0 的话则不是预设消息
     */
    private int type;

    /**
     * 广播的维度
     */
    @NotBlank
    private String dimension;

    public enum DimensionType {

        /**
         * 所有玩家
         */
        ALL,

        /**
         * 所在的房间
         */
        ROOM
    }
}
