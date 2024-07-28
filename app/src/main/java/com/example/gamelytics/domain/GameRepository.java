package com.example.gamelytics.domain;

import java.util.List;

public interface GameRepository {
    GameItem get(int id);
    List<GameItem> search(String name);
}
