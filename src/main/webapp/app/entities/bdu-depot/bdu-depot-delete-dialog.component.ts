import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBduDepot } from 'app/shared/model/bdu-depot.model';
import { BduDepotService } from './bdu-depot.service';

@Component({
  templateUrl: './bdu-depot-delete-dialog.component.html',
})
export class BduDepotDeleteDialogComponent {
  bduDepot?: IBduDepot;

  constructor(protected bduDepotService: BduDepotService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bduDepotService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bduDepotListModification');
      this.activeModal.close();
    });
  }
}
