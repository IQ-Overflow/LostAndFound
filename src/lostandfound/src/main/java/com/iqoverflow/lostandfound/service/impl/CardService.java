package com.iqoverflow.lostandfound.service.impl;

import com.iqoverflow.lostandfound.dao.CardDao;
import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service("cardService")
public class CardService implements ICardService {
    @Autowired
    private CardDao cardDao;

    @Override
    public Card[] findAllCards() {
        return cardDao.findCards();
    }

    @Override
    public void postCard(Card card) {
        cardDao.postCard(card);
    }

    @Override
    public Card findCardByInfo(String stuID, String college, String stuName) {
        Card card = cardDao.findCardByInfo(stuID,college,stuName);
        return card;
    }
}
