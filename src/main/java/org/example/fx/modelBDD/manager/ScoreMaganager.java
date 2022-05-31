package org.example.fx.modelBDD.manager;



import org.example.fx.modelBDD.dao.Score;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public interface ScoreMaganager {

    public List<Score> findAll(Connection con);

    public void Insert(Connection con, int puntuacion, LocalDateTime fecha, int idplayer);
}
