import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBduEffet } from 'app/shared/model/bdu-effet.model';
import { BduEffetService } from './bdu-effet.service';

@Component({
  templateUrl: './bdu-effet-delete-dialog.component.html',
})
export class BduEffetDeleteDialogComponent {
  bduEffet?: IBduEffet;

  constructor(protected bduEffetService: BduEffetService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bduEffetService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bduEffetListModification');
      this.activeModal.close();
    });
  }
}
