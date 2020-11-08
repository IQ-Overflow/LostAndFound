package com.iqoverflow.lostandfound.service.impl;

import com.iqoverflow.lostandfound.dao.CardDao;
import com.iqoverflow.lostandfound.domain.Card;
import com.iqoverflow.lostandfound.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cardService")
public class CardServiceImpl implements CardService {
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

    @Override
    public String getWxByuID(String uID) {
        return cardDao.getWxByuID(uID);
    }
}