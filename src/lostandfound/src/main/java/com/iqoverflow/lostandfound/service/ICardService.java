package com.iqoverflow.lostandfound.service;

import com.iqoverflow.lostandfound.domain.Card;

import java.sql.Timestamp;

public interface ICardService {

    Card[] findAllCards();

    void postCard(Card card);

    Card findCardByInfo(String stuID,String college,String stuName);
}
