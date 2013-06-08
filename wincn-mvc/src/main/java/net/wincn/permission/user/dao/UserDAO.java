package net.wincn.permission.user.dao;

import net.wincn.base.dao.BaseDAO;
import net.wincn.permission.user.bean.User;

public interface UserDAO extends BaseDAO<User, Integer> {
	User findByUsername(String username);
}
