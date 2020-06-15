import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBduCdb } from 'app/shared/model/bdu-cdb.model';
import { BduCdbService } from './bdu-cdb.service';

@Component({
  templateUrl: './bdu-cdb-delete-dialog.component.html',
})
export class BduCdbDeleteDialogComponent {
  bduCdb?: IBduCdb;

  constructor(protected bduCdbService: BduCdbService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bduCdbService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bduCdbListModification');
      this.activeModal.close();
    });
  }
}
