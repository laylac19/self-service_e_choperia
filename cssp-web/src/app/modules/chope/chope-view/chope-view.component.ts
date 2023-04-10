import {Component, OnInit} from '@angular/core';
import {ChopeViewListModel} from "../../../model/list/chope-view-list.model";
import {ChopeService} from "../../../shared/service/chope.service";

@Component({
  selector: 'app-chope-view',
  templateUrl: './chope-view.component.html',
  styleUrls: ['./chope-view.component.scss']
})
export class ChopeViewComponent implements OnInit {

  draftBeers: ChopeViewListModel[] = [];

  constructor(private draftBeerService: ChopeService) {
  }

  ngOnInit(): void {
    this.listAllDraftBeers();
  }

  listAllDraftBeers(): void {
    this.draftBeerService.listAllDraftBeers()
      .subscribe(
        (result) => {
          this.resultRequestList(result);
        });
  }

  private resultRequestList(result: ChopeViewListModel[]): void {
    result ? this.draftBeers = result : this.draftBeers = [];
  }

}
