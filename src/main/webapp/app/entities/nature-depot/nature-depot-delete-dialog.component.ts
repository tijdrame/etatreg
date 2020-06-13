import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INatureDepot } from 'app/shared/model/nature-depot.model';
import { NatureDepotService } from './nature-depot.service';

@Component({
  templateUrl: './nature-depot-delete-dialog.component.html',
})
export class NatureDepotDeleteDialogComponent {
  natureDepot?: INatureDepot;

  constructor(
    protected natureDepotService: NatureDepotService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.natureDepotService.delete(id).subscribe(() => {
      this.eventManager.broadcast('natureDepotListModification');
      this.activeModal.close();
    });
  }
}
