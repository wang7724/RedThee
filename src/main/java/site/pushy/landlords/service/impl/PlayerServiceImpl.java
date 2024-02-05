package site.pushy.landlords.service.impl;

import org.springframework.stereotype.Service;
import site.pushy.landlords.common.exception.BadRequestException;
import site.pushy.landlords.core.component.RoomComponent;
import site.pushy.landlords.core.enums.IdentityEnum;
import site.pushy.landlords.core.enums.RoomStatusEnum;
import site.pushy.landlords.pojo.Card;
import site.pushy.landlords.pojo.DO.User;
import site.pushy.landlords.pojo.Player;
import site.pushy.landlords.pojo.Room;
import site.pushy.landlords.service.PlayerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Pushy
 * @since 2019/2/1 18:21
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Resource
    private RoomComponent roomComponent;

    @Override
    public List<Card> getPlayerCards(User curUser) {
        return roomComponent.getUserCards(curUser.getId());
    }

    @Override
    public boolean isPlayerRound(User curUser) {
        Room room = roomComponent.getUserRoom(curUser.getId());
        if (room.getStatus() != RoomStatusEnum.PLAYING) {
            throw new BadRequestException("游戏还未开始");
        }
        Player player = room.getPlayerByUserId(curUser.getId());
        if (room.getStepNum() == -1) {
            // 叫牌未结束，直接返回 false
            return false;
        }
        int remainder = room.getStepNum() % 3;
        if (remainder == 0) {
            if (player.getId() != 3) return false;
        } else {
            if (player.getId() != remainder) return false;
        }
        return true;
    }

    @Override
    public boolean isPlayerReady(User curUser) {
        Room room = roomComponent.getUserRoom(curUser.getId());
        if (room.getStatus() == RoomStatusEnum.PLAYING) {
            throw new BadRequestException("游戏已经开始");
        }
        Player player = room.getPlayerByUserId(curUser.getId());
        return player.isReady();
    }

    @Override
    public boolean canPass(User curUser) {
        Room room = roomComponent.getUserRoom(curUser.getId());
        if (room.getStatus() != RoomStatusEnum.PLAYING) {
            throw new BadRequestException("游戏还未开始");
        }
        Player player = room.getPlayerByUserId(curUser.getId());
        if (room.getPrePlayerId() == 0) {
            return player.getIdentity() != IdentityEnum.LANDLORD;
        }
        return room.getPrePlayerId() != player.getId();
    }

    @Override
    public boolean canBid(User curUser) {
        Room room = roomComponent.getUserRoom(curUser.getId());
        if (room.getStatus() != RoomStatusEnum.PLAYING) {
            throw new BadRequestException("游戏还未开始");
        }
        Player player = room.getPlayerByUserId(curUser.getId());

        if (room.getStepNum() != -1) {
            return false;
        }
        return player.getId() == room.getBiddingPlayer();
    }
}
