package service.impl;

import dao.DepartmentsDao;
import dao.StudentsDao;
import dao.TeachersDao;
import dao.UserDao;
import dao.impl.TeachersDaoImpl;
import dao.impl.UserDaoImpl;
import entity.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UsersService;

import javax.annotation.Resource;
import java.util.List;
@Transactional
@Service("userService")


public class UserServiceImpl implements UsersService {

    //自动注入userDao，也可以使用@Autowired
    @Resource(name="userDao")
    private UserDao userDao;
    private UserDaoImpl ud;
    @Resource(name="teachersDao")
    private TeachersDao teachersDao;
    private TeachersDaoImpl td;
    @Resource
    private StudentsDao studentsDao;
    @Resource
    private DepartmentsDao departmentsDao;

    @Override
    public boolean addUser(User user) {
        this.userDao.add(user);
        return true;
    }

    @Override
    public boolean login(User user) {
        List<Teachers> query = this.teachersDao.findByCriteria(
                DetachedCriteria.forClass(Teachers.class).add(
                        Property.forName("tname").eq("viete")));
        System.out.println("query:"+query.get(0).getTname());
        System.out.println("query:"+query.get(0).getPwd());

        return this.userDao.login(user);
    }

    public boolean profile(Integer uid,Integer type,String info){
        switch (type){
            case 3:
                this.studentsDao.executeUpdate("students.editInfo",info,uid);
                return true;
            case 6:
                this.teachersDao.executeUpdate("teachers.editInfo",info,uid);
                return true;
            case 9:
                this.departmentsDao.executeUpdate("departments.editInfo",info,uid);
        }
        return false;
    }

    public boolean changepwd(Integer uid,Integer type,String pwd){
        switch (type){
            case 3:
                this.studentsDao.executeUpdate("students.editPassword", pwd,uid);
                return true;
            case 6:
                this.teachersDao.executeUpdate("teachers.editPassword", pwd,uid);
                return true;
            case 9:
                this.departmentsDao.executeUpdate("departments.editPassword", pwd,uid);
        }
        return false;
    }

    @Override
    public Users login(String account, String pwd, Integer type){
        Users users=new Users();
        users.setType(type);
        switch (type){
            case 3://student
            {
                List<Students> query = this.studentsDao.findByCriteria(
                        DetachedCriteria.forClass(Students.class).add(
                                Property.forName("sid").eq(Integer.parseInt(account)))
                                .add(Property.forName("pwd").eq(pwd)));
                if (query.size() > 0) {
                    users.setName(query.get(0).getSname());
                    users.setUid(query.get(0).getSid());
                    users.setInfo(query.get(0).getSinfo());
                    users.setOther(new Other<Students>(query.get(0)));
                    return users;
                }
                return null;
            }

            case 6://teacher
            {
                List<Teachers> query = this.teachersDao.findByCriteria(
                        DetachedCriteria.forClass(Teachers.class).add(
                                Property.forName("tid").eq(Integer.parseInt(account)))
                                .add(Property.forName("pwd").eq(pwd)));
                if(query.size()>0){
                    users.setName(query.get(0).getTname());
                    users.setUid(query.get(0).getTid());
                    users.setInfo(query.get(0).getTinfo());
                    users.setOther(new Other<Teachers>(query.get(0)));
                    return users;
                }
                return null;
            }

            case 9://depement
            {
                List<Departments> query = this.departmentsDao.findByCriteria(
                        DetachedCriteria.forClass(Departments.class).add(
                                Property.forName("dname").eq(account))
                                .add(Property.forName("pwd").eq(pwd)));
                if(query.size()>0){
                    users.setName(query.get(0).getDname());
                    users.setUid(query.get(0).getDid());
                    users.setInfo(query.get(0).getDinfo());
                    users.setOther(new Other<Departments>(query.get(0)));
                    return users;
                }
                return null;
            }
        }
        return null;
    }

    @Override
    public List getAllUser() {
        return this.userDao.getUser();
    }

    @Override
    public User getUserById(int id) {
        return this.userDao.getUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        this.userDao.update(user);
        return true;

    }

    @Override
    public boolean deleteUser(int id) {
        this.userDao.delete(id);
        return true;
    }

    public void setUd(UserDaoImpl ud) {
        this.ud = ud;
    }

    public UserDaoImpl getUd() {
        return ud;
    }

    public TeachersDaoImpl getTd() {
        return td;
    }

    public void setTd(TeachersDaoImpl td) {
        this.td = td;
    }
}
