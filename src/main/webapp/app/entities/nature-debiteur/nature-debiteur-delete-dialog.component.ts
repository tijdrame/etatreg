import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INatureDebiteur } from 'app/shared/model/nature-debiteur.model';
import { NatureDebiteurService } from './nature-debiteur.service';

@Component({
  templateUrl: './nature-debiteur-delete-dialog.component.html',
})
export class NatureDebiteurDeleteDialogComponent {
  natureDebiteur?: INatureDebiteur;

  constructor(
    protected natureDebiteurService: NatureDebiteurService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.natureDebiteurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('natureDebiteurListModification');
      this.activeModal.close();
    });
  }
}
