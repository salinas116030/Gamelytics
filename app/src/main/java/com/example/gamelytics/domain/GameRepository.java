package com.example.gamelytics.domain;

import com.example.gamelytics.domain.steam.GameSteam;
import java.util.List;

public interface GameRepository {
    Game get(int id) ;
    List<GameItem> search(String name);
}
