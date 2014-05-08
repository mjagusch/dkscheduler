package org.autumnridge.disciplekids.dksched.room.data;

import java.util.List;

import org.autumnridge.disciplekids.dksched.room.Room;

public interface RoomDao {
	/**
	 * find a room with a given id
	 */
	Room idRoom(Long id);

	/**
	 * save an idable or update it
	 */
	void saveOrUpdateRoom(Room room);

	/**
	 * List the available rooms
	 * @return
	 */
	List<Room> listRooms();

	void deleteRoom(Room room);

}
