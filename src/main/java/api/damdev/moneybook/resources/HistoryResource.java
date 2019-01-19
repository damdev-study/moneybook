package api.damdev.moneybook.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import api.damdev.moneybook.controller.HistoryController;
import api.damdev.moneybook.domain.History;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Author : zenic
 * Created : 2019-01-13
 */
public class HistoryResource extends Resource<History> {

  public HistoryResource(History history, Link... links) {
    super(history, links);
    add(linkTo(HistoryController.class).slash(history.getId()).withSelfRel());
  }
}
