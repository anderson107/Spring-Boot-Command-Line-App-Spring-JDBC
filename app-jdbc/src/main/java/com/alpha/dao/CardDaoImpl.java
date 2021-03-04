package com.alpha.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.alpha.entities.card.Card;

@Repository("cardDao")
public class CardDaoImpl implements CardDao {

	// fields
	private DataSource dataSource;
	private static Logger logger = LoggerFactory.getLogger(CardDaoImpl.class);
	private SelectAllCards selectAllCards;
	private SelectCardById selectCardById;
	private InsertNewCard insertNewCard;
	private DeleteCard deleteCard;
	private UpdateCard updateCard;
	
	//constructors
	public CardDaoImpl() {
		
	}

	@Resource(name="dataSource")
	@Override
	public void setDataSource(DataSource conn) {
		this.dataSource = conn;
		selectAllCards = new SelectAllCards(dataSource);
		selectCardById = new SelectCardById(dataSource);
		insertNewCard = new InsertNewCard(dataSource);
		deleteCard = new DeleteCard(dataSource);
		updateCard = new UpdateCard(dataSource);
	}

	//CRUD methods
	//returns all cards in the DB
	@Override
	public List<Card> findAll() {
		return selectAllCards.execute();
	}

	// it returns card by id
	@Override
	public Card findCard(int id) {
		
		Object[]params = new Object[1];
		params[0] = id;
		List<Card>list = selectCardById.execute(params);
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	// it add a card to the db
	@Override
	public void insert(Card card) {
		Map<String, Object>map = new HashMap<>();
		map.put("customer_id", card.getCustomer().getId());
		map.put("card_number", card.getCardNumber());
		map.put("card_code", card.getCardCode());
		map.put("expire_date", card.getExpireDate());
		KeyHolder holder = new GeneratedKeyHolder();
		insertNewCard.updateByNamedParam(map, holder);
		card.setId(holder.getKey().intValue());
		logger.info("Card was successfully added with id "+ card.getId());
	}

	// it updates card code and expire date
	@Override
	public void update(Card card) {
		
		Map<String, Object>param = new HashMap<>();
		param.put("card_id", card.getId());
		param.put("card_code", card.getCardCode());
		param.put("expire_date", card.getExpireDate());
		updateCard.updateByNamedParam(param);
		logger.info("Card id " + card.getId() + "was updated successfully");
	}

	// it deletes a card by id
	@Override
	public void delete(int id) {

		Map<String, Object>map = new HashMap<>();
		map.put("card_id", id);
		deleteCard.updateByNamedParam(map);
		logger.info("Card id number " + id + "was deleted successfully");
		
	}

}
