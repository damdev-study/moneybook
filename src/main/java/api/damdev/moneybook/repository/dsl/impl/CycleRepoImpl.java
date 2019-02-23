package api.damdev.moneybook.repository.dsl.impl;

import api.damdev.moneybook.domain.Cycle;
import api.damdev.moneybook.domain.QCycle;
import api.damdev.moneybook.dto.cycle.CycleParam;
import api.damdev.moneybook.repository.dsl.CycleRepoCustom;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
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
        JPAQuery query = new JPAQuery(entityManager);

        QCycle cycle = QCycle.cycle;

        query.from(cycle);

        return null;
    }
}
