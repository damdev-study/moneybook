package api.damdev.moneybook.service.impl;

import org.springframework.stereotype.Service;

import api.damdev.moneybook.dto.MoneyInfo;
import api.damdev.moneybook.service.HistoryService;

@Service("historyService")
public class HistoryServiceImpl implements HistoryService {
    @Override
    public int regHistory(MoneyInfo param) {
        return 0;
    }
}
