package api.damdev.moneybook.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.damdev.moneybook.domain.History;
import api.damdev.moneybook.dto.MoneyInfo;
import api.damdev.moneybook.repository.MoneyRepo;
import api.damdev.moneybook.resources.HistoryResource;
import lombok.extern.slf4j.Slf4j;

/**
 * Author : zenic
 * Created : 24/12/2018
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/moneybook/history", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class HistoryController {

  private final MoneyRepo moneyRepo;

  private final ModelMapper modelMapper;
  
//  private final HistoryService historyService;
  

  public HistoryController(MoneyRepo moneyRepo, ModelMapper modelMapper/*, HistoryService historyService*/) {
    this.moneyRepo = moneyRepo;
    this.modelMapper = modelMapper;
//    this.historyService = historyService;
  }

  @PostMapping
  public ResponseEntity regHistory(@RequestBody @Valid MoneyInfo moneyInfo, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(errors);
    }
    History history = modelMapper.map(moneyInfo, History.class);
    History newHistory = this.moneyRepo.save(history);
    if (newHistory.getId() == null) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    URI createURL = linkTo(HistoryController.class).slash(newHistory.getId()).toUri();
    HistoryResource historyResource = new HistoryResource(newHistory);
    historyResource.add(linkTo(HistoryController.class).withRel("query-history"));
    historyResource.add(new Link("/docs/index.html#resources-history-create").withRel("profile"));
    return ResponseEntity.created(createURL).body(historyResource);
  }

//  @GetMapping("search")
//  public ResponseEntity getHistoryList() {
//    Page<History> history = moneyRepo.findAll(PageRequest.of(0, 10));
//    return ResponseEntity.ok(history);
//  }
  
  @GetMapping("list")
  public ResponseEntity search(@RequestParam(defaultValue="", required=false) String query, @RequestParam(defaultValue="category", required=false) String target,
		  @RequestParam(defaultValue="0", required=false) Integer pageNum, @RequestParam(defaultValue="10", required=false) Integer pageSize, 
		  @RequestParam(defaultValue="category", required=false) String sortTarget, @RequestParam(defaultValue="0", required=false) Integer sortReverse){
    PageRequest page = PageRequest.of(pageNum, pageSize, 
    		sortReverse==1 ?  Sort.by(sortTarget).descending() : Sort.by(sortTarget).ascending() );
    Page<History> list =null;
    log.warn("tttttttttttttttttarget : " + target + ", query : " + query);
    if ( target.equals("category") ) {
    	list = moneyRepo.findByCategory(query, page);
    }
    
    return ResponseEntity.ok(list);
  }


  @GetMapping("{historyId}")
  public ResponseEntity getHistory(@PathVariable String historyId) {
    History history = moneyRepo.findById(historyId).orElse(new History());

    if (history.getId() == null) {
      return ResponseEntity.notFound().build();
    }

    HistoryResource historyResource = new HistoryResource(history);
    historyResource.add(new Link("/docs/index.html#resources-history-get").withRel("profile"));

    return ResponseEntity.ok(historyResource);
  }

  @PutMapping("{historyId}")
  public ResponseEntity updateHistory(@PathVariable String historyId,
    @RequestBody @Valid MoneyInfo moneyInfo, Errors errors) {
    History history = moneyRepo.findById(historyId).orElse(new History());

    if (history.getId() == null) {
      return ResponseEntity.notFound().build();
    }

    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().body(errors);
    }

    History changeHistory = modelMapper.map(moneyInfo, History.class);
    changeHistory.setId(historyId);

    History changedHistory = moneyRepo.save(changeHistory);

    HistoryResource historyResource = new HistoryResource(changedHistory);
    historyResource.add(new Link("/docs/index.html#resources-history-update").withRel("profile"));

    return ResponseEntity.ok(historyResource);
  }

  @DeleteMapping("{historyId}")
  public ResponseEntity deleteHistory(@PathVariable String historyId) {
    log.info("내역 삭제");
    History history = moneyRepo.findById(historyId).orElse(new History());

    if (history.getId() == null) {
      return ResponseEntity.notFound().build();
    }

    history.setActiveType(ActiveType.INACTIVE);
    History deletedHistory = moneyRepo.save(history);

    return ResponseEntity.ok(deletedHistory);
  }
}
