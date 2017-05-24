package main.models.dao;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import main.models.dto.UserDto;
import main.models.entities.UserRolesEntity;
import main.models.entities.UserentryEntity;
import main.models.pojo.User;
import main.utils.DatabaseManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    static final Logger logger = LogManager.getLogger(UserDaoImpl.class.getName());

    private DatabaseManager databaseManager;
    private SessionFactory sessionFactory;
    private MapperFacade mapper;

    public UserDaoImpl() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserentryEntity.class, UserDto.class)
                .field("userRoles{role}", "roles{}")
                .byDefault()
                .register();
        mapper = mapperFactory.getMapperFacade();
    }

    @Autowired
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private User createEntity(ResultSet result) throws SQLException {
        User user = new User(
                result.getInt("id"),
                result.getString("first_name"),
                result.getString("second_name"),
                result.getString("last_name"),
                result.getString("phone_number"),
                result.getString("email"),
                result.getBoolean("enabled"),
                result.getString("password"));

        return user;
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>(64);

        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(
                "from UserentryEntity order by id ASC");

        for (Object obj : query.list()) {
            users.add(mapper.map((UserentryEntity)obj, UserDto.class));
        }

        return users;
    }

    @Override
    public UserDto findByEmail(String email)
    {
        UserDto user = null;

        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(
                "from UserentryEntity where email = :email");
        query.setParameter("email", email);

        for (Object obj : query.list()) {
            user = mapper.map((UserentryEntity)obj, UserDto.class);
        }

        currentSession.close();

        return user;
    }

    @Override
    public UserDto findByPhone(String phone) {
        UserDto user = null;

        Session currentSession = sessionFactory.openSession();
        Query query = currentSession.createQuery(
                "from UserentryEntity where phoneNumber = :phoneNumber");
        query.setParameter("phoneNumber", phone);

        for (Object obj : query.list()) {
            user = mapper.map((UserentryEntity)obj, UserDto.class);
        }

        currentSession.close();

        return user;
    }

    @Override
    public UserDto addUser(UserDto user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserentryEntity userEntity = mapper.map(user, UserentryEntity.class);
        System.out.println("------------+++++++++============----------");
        for(UserRolesEntity ure : userEntity.getUserRoles()) {
            System.out.println(ure.getRole() +"****" + ure.getUserentryEntity());
        }
        Serializable id = session.save(userEntity);
        transaction.commit();
//        UserDto savedUser = mapper.map(session.get(UserentryEntity.class, id), UserDto.class);
        session.close();
        return user;
    }


    @Override
    public boolean deleteUser(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete UserentryEntity where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        session.close();
        return result > 0;
    }
}
