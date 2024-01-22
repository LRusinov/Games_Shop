package com.fmi.java.web.games_shop.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class ShoppingCartItemId implements Serializable {

    private String game;
    private String client;
}
