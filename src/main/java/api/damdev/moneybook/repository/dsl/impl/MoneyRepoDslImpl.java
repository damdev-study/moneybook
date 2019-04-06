package api.damdev.moneybook.repository.dsl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.google.common.base.Strings;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import api.damdev.moneybook.common.type.ActiveType;
import api.damdev.moneybook.domain.History;
import api.damdev.moneybook.domain.QHistory;
import api.damdev.moneybook.dto.MoneyParam;
import api.damdev.moneybook.repository.dsl.MoneyRepoDsl;

public class MoneyRepoDslImpl  extends QuerydslRepositorySupport implements MoneyRepoDsl{
    @PersistenceContext
    private EntityManager entityManager;

	public MoneyRepoDslImpl() {
		super(MoneyParam.class);
	}
	
	@Override
	public Page<History> getList(MoneyParam param, Pageable pageable) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		
		QHistory history = QHistory.history;
		JPAQuery query = queryFactory.from(history)
//				.where(history.userSeqId.eq(param.getUserSeqId()))
				.where(history.activeType.eq(ActiveType.ACITVE))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
				;
		
		// 분류
		if ( ! Strings.isNullOrEmpty(param.getCategory()) ) {
			query.where(history.category.contains(param.getCategory()));
		}
		
		// 지출 or 수입
		if ( param.getMoneyType() != null ) {
			query.where(history.moneyType.eq(param.getMoneyType()));
		}
		
		// 검색 시작일
		if ( param.getStartDate() != null ) {
			query.where(history.regDate.gt(param.getStartDate()));
		}

		// 검색 종료일
		if ( param.getEndDate() != null ) {
			query.where(history.regDate.lt(param.getEndDate()));
		}
		
		
		if ( ! pageable.getSort().isEmpty() ) {
			if ( pageable.getSort().toString().contains("id: ASC") ) {
				query.orderBy(history.id.asc());
			}
			else if ( pageable.getSort().toString().contains("id: DESC") ) {
				query.orderBy(history.id.desc());
			}
			else if ( pageable.getSort().toString().contains("category: ASC") ) {
				query.orderBy(history.category.asc());
			}
			else if ( pageable.getSort().toString().contains("category: DESC") ) {
				query.orderBy(history.category.desc());
			}
			else if ( pageable.getSort().toString().contains("regDate: ASC") ) {
				query.orderBy(history.regDate.asc());
			}
			else if ( pageable.getSort().toString().contains("regDate: DESC") ) {
				query.orderBy(history.regDate.desc());
			}
		}
		
		QueryResults results = query.fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
	}

}
