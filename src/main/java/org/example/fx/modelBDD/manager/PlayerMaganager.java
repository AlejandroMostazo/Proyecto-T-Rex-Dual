package org.example.fx.modelBDD.manager;

import org.example.fx.modelBDD.dao.Player;

import java.sql.Connection;
import java.util.List;

public interface PlayerMaganager {

    public List<Player> findAll(Connection con);

    public Player findByName(Connection con, String name);

    public Player validatePlayer(Connection con, String name, String contraseña);

    public void insert(Connection con, String nombre, String contraseña);



    }
