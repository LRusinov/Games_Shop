package com.fmi.java.web.games_shop.dto;

import java.time.Instant;
import java.util.Set;

public record GameDto(String name, double price, Set<String> platforms, Set<String> genres, String description,
                      Instant releaseDate, String publisher, String picture) {
}
