package com.example.gamelytics.domain;

import java.util.List;

public interface GameRepository {
    Game get(int id);
    List<Game> search(String name);
}
