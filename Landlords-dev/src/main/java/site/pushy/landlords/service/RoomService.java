package site.pushy.landlords.service;

import site.pushy.landlords.pojo.DO.User;
import site.pushy.landlords.pojo.DTO.RoomDTO;
import site.pushy.landlords.pojo.DTO.RoomOutDTO;
import site.pushy.landlords.pojo.Room;

/**
 * @author Pushy
 * @since 2018/12/29 21:08
 */
public interface RoomService {

    Room getRoomForUser(User curUser);

    RoomOutDTO getRoomById(User curUser, String id);

    Room createRoom(User curUser, String title, String password);

    String joinRoom(User curUser, RoomDTO roomDTO);

    boolean exitRoom(User curUser);
}
