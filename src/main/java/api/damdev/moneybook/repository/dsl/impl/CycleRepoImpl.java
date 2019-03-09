package api.damdev.moneybook.repository.dsl.impl;

import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.domain.QCycle;
import api.damdev.moneybook.dto.cycle.CycleParam;
import api.damdev.moneybook.repository.dsl.CycleRepoCustom;
import com.google.common.base.Strings;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CycleRepoImpl extends QuerydslRepositorySupport implements CycleRepoCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public CycleRepoImpl() {
        super(Cycle.class);
    }

    @Override
    public Page<Cycle> getUserList(CycleParam cycleParam, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        QCycle cycle = QCycle.cycle;

        JPAQuery query = queryFactory.from(cycle)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        if(cycleParam.getCycleName() != null && !cycleParam.getCycleName().isEmpty()) {
            query.where(cycle.cycleName.contains(cycleParam.getCycleName()));
        }

        if(cycleParam.getMoneyType() != null) {
            query.where(cycle.moneyType.eq(cycleParam.getMoneyType()));
        }

        if(cycleParam.getCycleType() != null) {
            query.where(cycle.cycleType.eq(cycleParam.getCycleType()));
        }

        QueryResults results = query.fetchResults();


        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
