package service;

import entity.User;
import entity.Users;

import java.util.List;
public interface UsersService {

    public boolean addUser(User user);

    public boolean login(User user);

    public Users login(String account, String pwd, Integer type);

    public boolean profile(Integer uid,Integer type,String info);

    public boolean changepwd(Integer uid,Integer type,String pwd);

    public List getAllUser();

    public User getUserById(int id);

    public boolean updateUser(User user);

    public boolean deleteUser(int id);
}
