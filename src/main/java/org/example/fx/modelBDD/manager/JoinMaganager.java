package org.example.fx.modelBDD.manager;

import org.example.fx.modelBDD.dao.Join;

import java.sql.Connection;
import java.util.List;

public interface JoinMaganager {

    public List<Join> findAll(Connection con);

    public List<Join> findById(Connection con, int id);

}
