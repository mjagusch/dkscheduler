package org.autumnridge.disciplekids.dksched.room.data;

import java.util.List;

import org.autumnridge.disciplekids.dksched.room.Room;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoomHibernate implements RoomDao {

	private SessionFactory sf;

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> listRooms() {
		return sf.getCurrentSession().createCriteria(Room.class)
				.list();
	}
	
	@Override
	public Room idRoom(Long id) {
		return (Room) (id == null ? null : sf.getCurrentSession().get(Room.class, id));
	}

	@Override
	public void saveOrUpdateRoom(Room room) {
		sf.getCurrentSession().saveOrUpdate(room);
	}

	@Override
	public void deleteRoom(Room room) {
		sf.getCurrentSession().delete(room);
	}

    @Autowired
    public void setSessionFactory(SessionFactory sf){
        this.sf = sf;
    }
}
